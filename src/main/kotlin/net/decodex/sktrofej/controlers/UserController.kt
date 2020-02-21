package net.decodex.sktrofej.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import net.decodex.sktrofej.domain.dto.UserDto
import net.decodex.sktrofej.domain.dto.UserRegistrationDto
import net.decodex.sktrofej.services.UserService

@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAuthenticatedUserInfo(auth: Authentication): UserDto {
        return userService.getUserByUsername(auth.principal as String)
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