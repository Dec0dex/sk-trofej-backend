package net.decodex.sktrofej.domain.dto

import net.decodex.sktrofej.domain.model.User
import java.util.*

class UserDto() {

    constructor(user: User) : this() {
        username = user.username
        email = user.email
        id = user.id
        createdAt = user.createdAt
        updatedAt = user.updatedAt
    }

    lateinit var username: String
    lateinit var email: String

    lateinit var createdAt: Date
    lateinit var updatedAt: Date

    var id: Long = 0

}