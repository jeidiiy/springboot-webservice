package io.jeidiiy.springbootwebservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class SpringbootWebserviceApplication

fun main(args: Array<String>) {
    runApplication<SpringbootWebserviceApplication>(*args)
}
