package rs.netcast.netcat.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "device_table")
@EntityListeners(AuditingEntityListener::class)
data class Device(
    @Id
    val mac: String,
    val os: String,
    var ip: String,

    var email: String? = null,

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
        mappedBy = "device"
    )
    val logs: List<Log>? = ArrayList(),

    @ManyToMany(mappedBy = "devices")
    val applications: MutableList<Application>? = ArrayList()
)