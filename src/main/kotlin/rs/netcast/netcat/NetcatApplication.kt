package rs.netcast.netcat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NetcatApplication

fun main(args: Array<String>) {
    runApplication<NetcatApplication>(*args)
}
