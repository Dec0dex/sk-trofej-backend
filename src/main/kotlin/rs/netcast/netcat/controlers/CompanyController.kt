package rs.netcast.netcat.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dto.CompanyDto
import rs.netcast.netcat.domain.dto.CompanyRegistrationDto
import rs.netcast.netcat.services.CompanyService

@RestController
@RequestMapping("/api/company")
class CompanyController {

    @Autowired
    lateinit var companyService: CompanyService

    @GetMapping
    fun getAllCompanies(): List<CompanyDto> {
        return companyService.getAllCompanies()
    }

    @GetMapping("/{id}")
    fun getCompanyById(@PathVariable id: Long): CompanyDto {
        return companyService.getCompanyById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(@PathVariable id: Long) {
        return companyService.deleteCompany(id)
    }

    @PostMapping
    fun createCompany(@RequestBody companyRegistrationDto: CompanyRegistrationDto): CompanyDto {
        return companyService.createCompany(companyRegistrationDto)
    }

    @PutMapping("/{id}")
    fun updateCompany(
        @PathVariable id: Long, @RequestBody companyRegistrationDto: CompanyRegistrationDto
    ): CompanyDto {
        return companyService.updateCompany(companyRegistrationDto, id)
    }
}