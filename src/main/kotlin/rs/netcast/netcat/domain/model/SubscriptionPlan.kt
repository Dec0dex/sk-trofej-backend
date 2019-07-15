package rs.netcast.netcat.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "subscription_plan_table")
@EntityListeners(AuditingEntityListener::class)
data class SubscriptionPlan(
    var fee: Double,

    var title: String,
    var description: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    var createdAt: Date = Date(),


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @LastModifiedDate
    var updatedAt: Date = Date(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "subscriptionPlan"
    )
    val applications: MutableList<Company> = ArrayList()
)