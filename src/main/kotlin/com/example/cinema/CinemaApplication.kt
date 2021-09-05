package com.example.cinema

import org.apache.log4j.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CinemaApplication

val LOG = Logger.getLogger("Main")


fun main(args: Array<String>) {
    runApplication<CinemaApplication>(*args)
}
