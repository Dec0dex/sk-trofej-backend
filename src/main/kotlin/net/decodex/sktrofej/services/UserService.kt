package net.decodex.sktrofej.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import net.decodex.sktrofej.domain.dao.UserDao
import net.decodex.sktrofej.domain.dto.UserDto
import net.decodex.sktrofej.domain.dto.UserRegistrationDto
import net.decodex.sktrofej.domain.model.User
import net.decodex.sktrofej.exceptions.ResourceNotFoundException

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserDao

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun getUserById(id: Long): UserDto {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw ResourceNotFoundException()
        }

        return UserDto(user.get())
    }

    fun getUserByUsername(username: String): UserDto {
        val user = userRepository.findByUsername(username) ?: throw ResourceNotFoundException()

        return UserDto(user)
    }

    fun deleteUserById(id: Long) {
        return userRepository.deleteById(id)
    }

    fun createUser(userDto: UserRegistrationDto): UserDto {
        val user = User(userDto.username, passwordEncoder.encode(userDto.password), userDto.email)
        return UserDto(userRepository.save(user))
    }

    fun updateUser(userDto: UserRegistrationDto, id: Long): UserDto {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw ResourceNotFoundException()
        }

        user.get().email = userDto.email
        user.get().password = passwordEncoder.encode(userDto.password)

        return UserDto(userRepository.save(user.get()))
    }
}
