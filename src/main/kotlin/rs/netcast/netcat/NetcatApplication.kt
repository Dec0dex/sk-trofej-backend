package rs.netcast.netcat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NetcatApplication

fun main(args: Array<String>) {
    runApplication<NetcatApplication>(*args)
}
