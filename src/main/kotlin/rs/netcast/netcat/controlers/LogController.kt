package rs.netcast.netcat.controlers

import com.querydsl.core.types.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dao.LogDao
import rs.netcast.netcat.domain.dto.LogCreationDto
import rs.netcast.netcat.domain.dto.LogDto
import rs.netcast.netcat.domain.model.Log
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

    @GetMapping
    fun getLogs(
        @QuerydslPredicate(
            root = Log::class,
            bindings = LogDao::class
        ) predicate: Predicate?, pageable: Pageable
    ): Page<LogDto> {
        return logService.getLogs(pageable, predicate)
    }
}