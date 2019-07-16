package rs.netcast.netcat.services

import com.querydsl.core.types.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.ApplicationDao
import rs.netcast.netcat.domain.dao.DeviceDao
import rs.netcast.netcat.domain.dto.DeviceDto
import rs.netcast.netcat.exceptions.ResourceNotFoundException
import rs.netcast.netcat.services.querydsl.QueryDslProvider

@Service
class DeviceService {

    @Autowired
    lateinit var deviceRepository: DeviceDao

    @Autowired
    lateinit var applicationRepository: ApplicationDao

    @Autowired
    lateinit var queryDslProvider: QueryDslProvider

    fun getDevices(pageable: Pageable, predicate: Predicate?): Page<DeviceDto> {
        return deviceRepository.findAll(predicate, pageable).map { DeviceDto(it) }
    }

    fun getDevicesForApplicationId(pageable: Pageable, predicate: Predicate?, applicationId: Long): Page<DeviceDto> {
        val application = applicationRepository.findById(applicationId)

        if (application.isEmpty) {
            throw ResourceNotFoundException()
        }

        val calculatedPredicate = queryDslProvider.addConstraintApplicationToDevice(predicate, application.get())
        return deviceRepository.findAll(calculatedPredicate, pageable).map { DeviceDto(it) }
    }

}