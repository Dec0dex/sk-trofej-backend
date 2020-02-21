package net.decodex.sktrofej.domain.dto

import net.decodex.sktrofej.domain.model.User
import java.util.*

class UserRegistrationDto() {

    constructor(user: User) : this() {
        username = user.username
        email = user.email
    }

    lateinit var username: String
    lateinit var password: String
    lateinit var email: String

}