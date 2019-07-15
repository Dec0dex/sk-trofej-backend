package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.Company
import java.util.*

class CompanyDto() {

    constructor(company: Company) : this() {
        name = company.name
        subscriptionPlan = SubscriptionPlanDto(company.subscriptionPlan)
        subscriptionUntil = company.subscribedUntil
        country = company.country
        city = company.city
        address = company.address
        postalCode = company.postalCode

        createdAt = company.createdAt
        updatedAt = company.updatedAt
    }

    lateinit var name: String
    lateinit var subscriptionUntil: Date
    lateinit var country: String
    lateinit var city: String
    lateinit var address: String
    lateinit var postalCode: String
    lateinit var subscriptionPlan: SubscriptionPlanDto

    var id: Long = 0

    lateinit var createdAt: Date
    lateinit var updatedAt: Date
}