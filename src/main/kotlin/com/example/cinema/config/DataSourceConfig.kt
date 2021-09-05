package com.example.cinema.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig(
    @Value("\${db.url}") private val url: String,
    @Value("\${db.user}") private val user: String,
    @Value("\${db.password}") private val password: String,
    @Value("\${db.driver}") private val driver: String) {

    @Bean
    fun dataSource(): DataSource? {
        return try {
            DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(user)
                .password(password)
                .build()
        } catch (e: Exception) {
            println(e.message)
            null
        }

    }
}
