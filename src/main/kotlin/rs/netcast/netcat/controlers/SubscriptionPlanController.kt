package rs.netcast.netcat.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import rs.netcast.netcat.domain.dto.SubscriptionPlanDto
import rs.netcast.netcat.services.SubscriptionPlanService

@RestController
@RequestMapping("/api/subscription-plan")
class SubscriptionPlanController {

    @Autowired
    lateinit var subscriptionPlanService: SubscriptionPlanService

    @GetMapping
    fun getAllSubscriptionPlans(): List<SubscriptionPlanDto> {
        return subscriptionPlanService.getAllSubscriptionPlans()
    }

    @GetMapping("/{id}")
    fun getSubscriptionPlanByID(@PathVariable id: Long): SubscriptionPlanDto {
        return subscriptionPlanService.getSubscriptionPlanById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteSubscriptionPlanById(@PathVariable id: Long) {
        return subscriptionPlanService.deleteSubscriptionPlan(id)
    }

    @PostMapping
    fun createSubscriptionPlan(@RequestBody subscriptionPlanDto: SubscriptionPlanDto): SubscriptionPlanDto {
        return subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDto)
    }

    @PutMapping("/{id}")
    fun updateSubscriptionPlan(
        @PathVariable id: Long, @RequestBody subscriptionPlanDto: SubscriptionPlanDto
    ): SubscriptionPlanDto {
        return subscriptionPlanService.updateSubscriptionPlan(id, subscriptionPlanDto)
    }
}