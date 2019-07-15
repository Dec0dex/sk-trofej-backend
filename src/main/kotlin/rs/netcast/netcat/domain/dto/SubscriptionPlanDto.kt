package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.SubscriptionPlan
import java.util.*

class SubscriptionPlanDto() {

    constructor(subscriptionPlan: SubscriptionPlan) : this() {
        this.id = subscriptionPlan.id
        this.fee = subscriptionPlan.fee
        this.title = subscriptionPlan.title
        this.description = subscriptionPlan.description
        this.createdAt = subscriptionPlan.createdAt
        this.updatedAt = subscriptionPlan.updatedAt
    }

    var id: Long = 0
    var fee: Double = 0.0
    lateinit var title: String
    lateinit var description: String
    var createdAt: Date? = null
    var updatedAt: Date? = null

}