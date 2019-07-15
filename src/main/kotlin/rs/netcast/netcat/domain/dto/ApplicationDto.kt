package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.Application
import java.util.*

class ApplicationDto() {

    constructor(application: Application) : this() {
        id = application.id
        name = application.name
        description = application.description
        accessToken = application.accessToken

        company = CompanyDto(application.company)

        createdAt = application.createdAt
        updatedAt = application.updatedAt
        iconImage = application.iconImage
    }

    var id: Long = 0
    lateinit var name: String
    lateinit var description: String
    lateinit var accessToken: String
    var iconImage: String? = null

    lateinit var createdAt: Date
    lateinit var updatedAt: Date

    lateinit var company: CompanyDto

}