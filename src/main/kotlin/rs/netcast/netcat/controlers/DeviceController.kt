package rs.netcast.netcat.controlers

import com.querydsl.core.types.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.netcast.netcat.domain.dao.DeviceDao
import rs.netcast.netcat.domain.dto.DeviceDto
import rs.netcast.netcat.domain.model.Device
import rs.netcast.netcat.services.DeviceService

@RestController
@RequestMapping("/api/device")
class DeviceController {

    @Autowired
    lateinit var deviceService: DeviceService

    @GetMapping
    fun getDevices(
        @QuerydslPredicate(
            root = Device::class,
            bindings = DeviceDao::class
        ) predicate: Predicate?, pageable: Pageable
    ): Page<DeviceDto> {
        return deviceService.getDevices(pageable, predicate)
    }

    @GetMapping("/application/{applicationId}")
    fun getDevicesForApplicationId(
        @QuerydslPredicate(
            root = Device::class,
            bindings = DeviceDao::class
        ) predicate: Predicate?, pageable: Pageable, @PathVariable applicationId: Long
    ): Page<DeviceDto> {
        return deviceService.getDevicesForApplicationId(pageable, predicate, applicationId)
    }

}