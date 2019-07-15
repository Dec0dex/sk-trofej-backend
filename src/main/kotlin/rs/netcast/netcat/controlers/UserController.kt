package rs.netcast.netcat.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dto.UserDto
import rs.netcast.netcat.domain.dto.UserRegistrationDto
import rs.netcast.netcat.services.UserService

@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/company/{companyId}")
    fun getAllUsersForCompany(@PathVariable companyId: Long): List<UserDto> {
        return userService.getAllUsersForCompanyId(companyId)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {
        return userService.getUserById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) {
        return userService.deleteUserById(id)
    }

    @PostMapping
    fun createUser(@RequestBody userDto: UserRegistrationDto): UserDto {
        return userService.createUser(userDto)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long, @RequestBody userDto: UserRegistrationDto
    ): UserDto {
        return userService.updateUser(userDto, id)
    }

}