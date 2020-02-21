package net.decodex.sktrofej.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import net.decodex.sktrofej.domain.model.User

interface UserDao : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}