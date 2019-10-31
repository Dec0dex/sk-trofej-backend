package rs.netcast.netcat.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.dao.SubscriptionPlanDao
import rs.netcast.netcat.domain.dto.SubscriptionPlanDto
import rs.netcast.netcat.domain.model.SubscriptionPlan
import rs.netcast.netcat.exceptions.ResourceNotFoundException

@Service
class SubscriptionPlanService {

    @Autowired
    lateinit var repository: SubscriptionPlanDao

    fun getAllSubscriptionPlans(): List<SubscriptionPlanDto> {
        val dataSet = repository.findAll()
        return dataSet.map { SubscriptionPlanDto(it) }
    }

    fun getSubscriptionPlanById(id: Long): SubscriptionPlanDto {
        val data = repository.findById(id)

        if (!data.isPresent) {
            throw ResourceNotFoundException()
        }

        return SubscriptionPlanDto(data.get())
    }

    fun deleteSubscriptionPlan(id: Long) {
        return repository.deleteById(id)
    }

    fun createSubscriptionPlan(subscriptionPlanDto: SubscriptionPlanDto): SubscriptionPlanDto {
        val subscriptionPlan =
            SubscriptionPlan(subscriptionPlanDto.fee, subscriptionPlanDto.title, subscriptionPlanDto.description)
        return SubscriptionPlanDto(repository.save(subscriptionPlan))
    }

    fun updateSubscriptionPlan(id: Long, subscriptionPlanDto: SubscriptionPlanDto): SubscriptionPlanDto {
        val subscriptionPlan = repository.findById(id)

        if (!subscriptionPlan.isPresent) {
            throw ResourceNotFoundException()
        }

        subscriptionPlan.get().fee = subscriptionPlanDto.fee
        subscriptionPlan.get().title = subscriptionPlanDto.title
        subscriptionPlan.get().description = subscriptionPlanDto.description

        return SubscriptionPlanDto(repository.save(subscriptionPlan.get()))
    }
}