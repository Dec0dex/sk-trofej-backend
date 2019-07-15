package rs.netcast.netcat.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dto.ApplicationCreationDto
import rs.netcast.netcat.domain.dto.ApplicationDto
import rs.netcast.netcat.services.ApplicationService

@RestController
@RequestMapping("/api/application")
class ApplicationController {

    @Autowired
    lateinit var applicationService: ApplicationService

    @GetMapping("/company/{companyId}")
    fun getAllApplicationsForCompany(@PathVariable companyId: Long): List<ApplicationDto> {
        return applicationService.getAllApplicationsForCompanyId(companyId)
    }

    @GetMapping("/{id}")
    fun getApplicationById(@PathVariable id: Long): ApplicationDto {
        return applicationService.getApplicationById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteApplicationById(@PathVariable id: Long) {
        return applicationService.deleteApplicationById(id)
    }

    @PostMapping
    fun createApplication(@RequestBody applicationDto: ApplicationCreationDto): ApplicationDto {
        return applicationService.createApplication(applicationDto)
    }

    @PutMapping("/{id}")
    fun updateApplication(
        @PathVariable id: Long, @RequestBody applicationDto: ApplicationCreationDto
    ): ApplicationDto {
        return applicationService.updateApplication(applicationDto, id)
    }
}