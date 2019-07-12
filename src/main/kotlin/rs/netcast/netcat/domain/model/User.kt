package rs.netcast.netcat.domain.model

import javax.persistence.*

@Entity(name = "user_table")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(unique = true)
    val username: String,

    val password: String,

    @Column(unique = true)
    val email: String
)