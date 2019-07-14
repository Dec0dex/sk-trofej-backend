package rs.netcast.netcat.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import rs.netcast.netcat.domain.model.SubscriptionPlan

interface SubscriptionPlanDao: JpaRepository<SubscriptionPlan, Long>