package com.popup_the_local.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableMongoAuditing
class ServerApplication

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}
