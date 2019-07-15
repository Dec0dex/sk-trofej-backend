package rs.netcast.netcat.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.ApplicationDao
import rs.netcast.netcat.domain.dao.CompanyDao
import rs.netcast.netcat.domain.dto.ApplicationCreationDto
import rs.netcast.netcat.domain.dto.ApplicationDto
import rs.netcast.netcat.domain.model.Application
import rs.netcast.netcat.exceptions.ResourceNotFoundException
import rs.netcast.netcat.security.SecurityConstants

@Service
class ApplicationService {

    @Autowired
    lateinit var companyRepository: CompanyDao

    @Autowired
    lateinit var applicationRespository: ApplicationDao

    fun getAllApplicationsForCompanyId(companyId: Long): List<ApplicationDto> {
        val company = companyRepository.findById(companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        return applicationRespository.findAllByCompany(company.get()).map { ApplicationDto(it) }
    }

    fun getApplicationById(id: Long): ApplicationDto {
        val application = applicationRespository.findById(id)

        if (application.isEmpty) {
            throw ResourceNotFoundException()
        }

        return ApplicationDto(application.get())
    }

    fun deleteApplicationById(id: Long) {
        return applicationRespository.deleteById(id)
    }

    fun createApplication(applicationDto: ApplicationCreationDto): ApplicationDto {
        val company = companyRepository.findById(applicationDto.companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        var application =
            Application(applicationDto.name, applicationDto.description, "", company.get(), applicationDto.iconImage)
        application = applicationRespository.save(application)
        application.accessToken = generateTokenForApplication(application.id)
        return ApplicationDto(applicationRespository.save(application))
    }

    fun updateApplication(applicationDto: ApplicationCreationDto, id: Long): ApplicationDto {
        val company = companyRepository.findById(applicationDto.companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        val application = applicationRespository.findById(id)

        if (application.isEmpty) {
            throw ResourceNotFoundException()
        }

        application.get().name = applicationDto.name
        application.get().description = applicationDto.description
        application.get().iconImage = applicationDto.iconImage

        return ApplicationDto(applicationRespository.save(application.get()))
    }

    private fun generateTokenForApplication(applicationId: Long): String {
        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
            .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
            .setIssuer(SecurityConstants.TOKEN_ISSUER)
            .setAudience(SecurityConstants.TOKEN_AUDIENCE)
            .setSubject(applicationId.toString())
            .compact()
    }
}