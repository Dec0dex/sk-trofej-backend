package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.User
import java.util.*

class UserRegistrationDto() {

    constructor(user: User) : this() {
        username = user.username
        email = user.email
        id = user.id
        createdAt = user.createdAt
        updatedAt = user.updatedAt
        companyId = user.company.id
    }

    lateinit var username: String
    lateinit var password: String
    lateinit var email: String

    lateinit var createdAt: Date
    lateinit var updatedAt: Date

    var id: Long = 0

    var companyId: Long = 0

}