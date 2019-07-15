package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.User
import java.util.*

class UserDto() {

    constructor(user: User) : this() {
        username = user.username
        email = user.email
        id = user.id
        createdAt = user.createdAt
        updatedAt = user.updatedAt
        company = CompanyDto(user.company)
    }

    lateinit var username: String
    lateinit var email: String

    lateinit var createdAt: Date
    lateinit var updatedAt: Date

    var id: Long = 0

    lateinit var company: CompanyDto
}