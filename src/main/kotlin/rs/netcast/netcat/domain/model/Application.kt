package rs.netcast.netcat.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "application_table")
@EntityListeners(AuditingEntityListener::class)
data class Application(
    var name: String,

    var description: String,
    @Column(name = "access_token")
    var accessToken: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    val company: Company,

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

    @ManyToMany
    @JoinTable(
        name = "application_devices_table",
        joinColumns = [JoinColumn(name = "application_id")],
        inverseJoinColumns = [JoinColumn(name = "device_id")]
    )
    val devices: List<Device> = ArrayList()
)