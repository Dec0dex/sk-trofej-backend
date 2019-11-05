package rs.netcast.netcat.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.CompanyDao
import rs.netcast.netcat.domain.dao.UserDao
import rs.netcast.netcat.domain.dto.UserDto
import rs.netcast.netcat.domain.dto.UserRegistrationDto
import rs.netcast.netcat.domain.model.User
import rs.netcast.netcat.exceptions.ResourceNotFoundException

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserDao

    @Autowired
    lateinit var companyRepository: CompanyDao

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun getAllUsersForCompanyId(companyId: Long): List<UserDto> {
        val company = companyRepository.findById(companyId)

        if (!company.isPresent) {
            throw ResourceNotFoundException()
        }

        val usersDataset = userRepository.findAllByCompany(company.get())
        return usersDataset.map { UserDto(it) }
    }

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
        val company = companyRepository.findById(userDto.companyId)

        if (!company.isPresent) {
            throw ResourceNotFoundException()
        }

        val user = User(userDto.username, passwordEncoder.encode(userDto.password), userDto.email, company.get())
        return UserDto(userRepository.save(user))
    }

    fun updateUser(userDto: UserRegistrationDto, id: Long): UserDto {
        val company = companyRepository.findById(userDto.companyId)

        if (!company.isPresent) {
            throw ResourceNotFoundException()
        }

        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw ResourceNotFoundException()
        }

        user.get().email = userDto.email
        user.get().password = passwordEncoder.encode(userDto.password)

        return UserDto(userRepository.save(user.get()))
    }
}
