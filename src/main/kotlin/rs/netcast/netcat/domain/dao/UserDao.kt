package rs.netcast.netcat.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import rs.netcast.netcat.domain.model.Company
import rs.netcast.netcat.domain.model.User

interface UserDao : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?
    fun findAllByCompany(company: Company): List<User>

}