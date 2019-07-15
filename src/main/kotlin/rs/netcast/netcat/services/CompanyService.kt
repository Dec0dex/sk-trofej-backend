package rs.netcast.netcat.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.CompanyDao
import rs.netcast.netcat.domain.dao.SubscriptionPlanDao
import rs.netcast.netcat.domain.dto.CompanyDto
import rs.netcast.netcat.domain.dto.CompanyRegistrationDto
import rs.netcast.netcat.domain.model.Company
import rs.netcast.netcat.exceptions.ResourceNotFoundException

@Service
class CompanyService {

    @Autowired
    lateinit var companyRepository: CompanyDao

    @Autowired
    lateinit var subscriptionPlanRepository: SubscriptionPlanDao

    fun getAllCompanies(): List<CompanyDto> {
        val dataSet = companyRepository.findAll()
        return dataSet.map { CompanyDto(it) }
    }

    fun getCompanyById(id: Long): CompanyDto {
        val company = companyRepository.findById(id)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        return CompanyDto(company.get())
    }

    fun deleteCompany(id: Long) {
        return companyRepository.deleteById(id)
    }

    fun createCompany(companyDto: CompanyRegistrationDto): CompanyDto {
        val subscriptionPlan = subscriptionPlanRepository.findById(companyDto.subscriptionPlanId)

        if (subscriptionPlan.isEmpty) {
            throw ResourceNotFoundException()
        }

        val company = Company(
            companyDto.name,
            companyDto.subscriptionUntil,
            companyDto.country,
            companyDto.city,
            companyDto.address,
            companyDto.postalCode,
            subscriptionPlan.get()
        )

        return CompanyDto(companyRepository.save(company))
    }

    fun updateCompany(companyDto: CompanyRegistrationDto, id: Long): CompanyDto {
        val subscriptionPlan = subscriptionPlanRepository.findById(companyDto.subscriptionPlanId)

        if (subscriptionPlan.isEmpty) {
            throw ResourceNotFoundException()
        }

        val company = companyRepository.findById(id)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        company.get().subscriptionPlan = subscriptionPlan.get()
        company.get().name = companyDto.name
        company.get().subscribedUntil = companyDto.subscriptionUntil
        company.get().country = companyDto.country
        company.get().city = companyDto.city
        company.get().address = companyDto.address
        company.get().postalCode = companyDto.postalCode

        return CompanyDto(companyRepository.save(company.get()))
    }
}