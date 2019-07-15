package rs.netcast.netcat.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dto.LogCreationDto
import rs.netcast.netcat.domain.dto.LogDto
import rs.netcast.netcat.services.LogService

@RestController
@RequestMapping("/api/log")
class LogController {

    @Autowired
    lateinit var logService: LogService

    @GetMapping("/{id}")
    fun getLogById(@PathVariable id: Long): LogDto {
        return logService.getLogById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteLogById(@PathVariable id: Long) {
        return logService.deleteLogById(id)
    }

    @DeleteMapping("/application/{id}")
    fun deleteLogByApplicationId(@PathVariable id: Long) {
        return logService.deleteLogByApplicationId(id)
    }

    @PostMapping
    fun createLog(@RequestBody logDto: LogCreationDto): LogDto {
        return logService.createLog(logDto)
    }
}