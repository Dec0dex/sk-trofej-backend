package rs.netcast.netcat.services

import com.querydsl.core.types.Predicate
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.ApplicationDao
import rs.netcast.netcat.domain.dao.DeviceDao
import rs.netcast.netcat.domain.dao.LogDao
import rs.netcast.netcat.domain.dto.LogCreationDto
import rs.netcast.netcat.domain.dto.LogDto
import rs.netcast.netcat.domain.model.Application
import rs.netcast.netcat.domain.model.Device
import rs.netcast.netcat.domain.model.Log
import rs.netcast.netcat.exceptions.ResourceNotFoundException
import rs.netcast.netcat.security.SecurityConstants
import rs.netcast.netcat.services.querydsl.QueryDslProvider

@Service
class LogService {

    @Autowired
    lateinit var deviceRepository: DeviceDao

    @Autowired
    lateinit var logRepository: LogDao

    @Autowired
    lateinit var applicationRepository: ApplicationDao

    @Autowired
    lateinit var queryDslProvider: QueryDslProvider

    fun getLogById(id: Long): LogDto {
        val log = logRepository.findById(id)

        if (!log.isPresent) {
            throw ResourceNotFoundException()
        }

        return LogDto(log.get())
    }

    fun deleteLogById(id: Long) {
        logRepository.deleteById(id)
    }

    fun getLogs(pageable: Pageable, predicate: Predicate?): Page<LogDto> {
        return logRepository.findAll(predicate, pageable).map { LogDto(it) }
    }

    fun getLogsForApplicationId(pageable: Pageable, predicate: Predicate?, applicationId: Long): Page<LogDto> {
        val application = applicationRepository.findById(applicationId)

        if (!application.isPresent) {
            throw ResourceNotFoundException()
        }

        val calculatedPredicate = queryDslProvider.addConstraintApplicationToLog(predicate, application.get())
        return logRepository.findAll(calculatedPredicate, pageable).map { LogDto(it) }
    }

    fun deleteLogByApplicationId(id: Long) {
        val application = applicationRepository.findById(id)

        if (!application.isPresent) {
            throw ResourceNotFoundException()
        }

        logRepository.deleteAllByApplication(application.get())
    }

    fun createLog(logDto: LogCreationDto): LogDto {
        val application = getApplicationFromAccessToken(logDto.accessToken)
        val device = getUpdatedDevice(logDto.mac, logDto.os, logDto.ip, logDto.email, application)
        application.devices.add(device)
        applicationRepository.save(application)

        val log = updateLogOrCreateNew(logDto, device, application)
        return LogDto(logRepository.save(log))
    }

    private fun updateLogOrCreateNew(logDto: LogCreationDto, device: Device, application: Application): Log {
        val repoLog = logRepository.findLogByMessageAndTagAndAffectedVersion(logDto.message, logDto.tag, logDto.affectedVersion)

        val log = if (repoLog.isPresent) {
            val result = repoLog.get()
            result.occurances += 1
            result
        } else {
            Log(logDto.level, logDto.timestamp, logDto.tag, logDto.message, logDto.affectedVersion, 1, device, application)
        }

        return logRepository.save(log)
    }

    private fun getApplicationFromAccessToken(token: String): Application {
        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        val parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)

        val applicationId = parsedToken.body.subject.toLong()
        val application = applicationRepository.findById(applicationId)

        if (!application.isPresent) {
            throw ResourceNotFoundException()
        }

        return application.get()
    }

    private fun getUpdatedDevice(
            mac: String,
            os: String,
            ip: String,
            email: String?,
            application: Application
    ): Device {
        return if (deviceRepository.existsById(mac)) {
            val device = deviceRepository.findById(mac)
            device.get().ip = ip
            device.get().email = email
            device.get().applications?.add(application)
            deviceRepository.save(device.get())
        } else {
            val device = Device(mac, os, ip, email)
            device.applications?.add(application)
            deviceRepository.save(device)
        }
    }
}