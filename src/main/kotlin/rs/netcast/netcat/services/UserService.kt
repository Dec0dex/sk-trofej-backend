package rs.netcast.netcat.services

import org.springframework.beans.factory.annotation.Autowired
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

    fun getAllUsersForCompanyId(companyId: Long): List<UserDto> {
        val company = companyRepository.findById(companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        val usersDataset = userRepository.findAllByCompany(company.get())
        return usersDataset.map { UserDto(it) }
    }

    fun getUserById(id: Long): UserDto {
        val user = userRepository.findById(id)

        if (user.isEmpty) {
            throw ResourceNotFoundException()
        }

        return UserDto(user.get())
    }

    fun deleteUserById(id: Long) {
        return userRepository.deleteById(id)
    }

    fun createUser(userDto: UserRegistrationDto): UserDto {
        val company = companyRepository.findById(userDto.companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        val user = User(userDto.username, userDto.password, userDto.email, company.get())
        return UserDto(userRepository.save(user))
    }

    fun updateUser(userDto: UserRegistrationDto, id: Long): UserDto {
        val company = companyRepository.findById(userDto.companyId)

        if (company.isEmpty) {
            throw ResourceNotFoundException()
        }

        val user = userRepository.findById(id)

        if (user.isEmpty) {
            throw ResourceNotFoundException()
        }

        user.get().email = userDto.email
        user.get().password = userDto.password

        return UserDto(userRepository.save(user.get()))
    }
}