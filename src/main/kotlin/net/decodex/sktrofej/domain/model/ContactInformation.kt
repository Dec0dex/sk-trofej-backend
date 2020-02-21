package net.decodex.sktrofej.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity(name = "contact_information_table")
@EntityListeners(AuditingEntityListener::class)
data class ContactInformation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        var name: String,

        var address: String,

        var locationLatitude: Long,

        var locationLongitude: Long,

        var contactInformation: String,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at", nullable = false, updatable = false)
        @CreatedDate
        val createdAt: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_at")
        @LastModifiedDate
        var updatedAt: Date = Date()
)