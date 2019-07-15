package rs.netcast.netcat.domain.model

import org.springframework.boot.logging.LogLevel
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity(name = "log_table")
@EntityListeners(AuditingEntityListener::class)
data class Log(
    val level: LogLevel,

    val timeStamp: Long,

    val tag: String,

    @Column(columnDefinition = "TEXT")
    val message: String,

    val affectedVersion: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    val device: Device,

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
    var updatedAt: Date = Date()
)