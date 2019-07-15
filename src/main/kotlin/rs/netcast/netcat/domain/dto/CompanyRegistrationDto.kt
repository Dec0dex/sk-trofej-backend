package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.Company
import java.util.*

class CompanyRegistrationDto() {

    constructor(company: Company):this() {
        name = company.name
        subscriptionPlanId = company.subscriptionPlan.id
        subscriptionUntil = company.subscribedUntil
        country = company.country
        city = company.city
        address = company.address
        postalCode = company.postalCode
    }

    lateinit var name: String
    lateinit var subscriptionUntil: Date
    lateinit var country: String
    lateinit var city: String
    lateinit var address: String
    lateinit var postalCode: String

    var subscriptionPlanId: Long = 0
    var id: Long = 0

}