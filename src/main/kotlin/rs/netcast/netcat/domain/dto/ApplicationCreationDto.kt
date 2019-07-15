package rs.netcast.netcat.domain.dto

class ApplicationCreationDto {

    lateinit var name: String
    lateinit var description: String
    var iconImage: String? = null
    var companyId: Long = 0
}