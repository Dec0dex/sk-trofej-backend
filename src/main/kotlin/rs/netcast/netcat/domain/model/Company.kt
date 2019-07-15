package rs.netcast.netcat.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "company_table")
@EntityListeners(AuditingEntityListener::class)
data class Company(
    var name: String,

    @Column(name = "subscribed_until")
    var subscribedUntil: Date,

    var country: String,

    var city: String,
    var address: String,
    @Column(name = "postal_code")
    var postalCode: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    var subscriptionPlan: SubscriptionPlan,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    val createdAt: Date = Date(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @LastModifiedDate
    var updatedAt: Date = Date(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "company"
    )
    val creditCards: MutableList<CreditCard> = ArrayList(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "company"
    )
    val users: MutableList<User> = ArrayList(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "company"
    )
    val applications: MutableList<Application> = ArrayList()
)