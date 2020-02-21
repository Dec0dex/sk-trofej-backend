package net.decodex.sktrofej

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SkTrofej

fun main(args: Array<String>) {
    runApplication<SkTrofej>(*args)
}
