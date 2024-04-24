package com.copite.starry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StarryApplication

fun main(args: Array<String>) {
    runApplication<StarryApplication>(*args)
}
