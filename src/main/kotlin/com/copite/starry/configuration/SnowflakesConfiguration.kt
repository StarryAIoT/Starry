package com.copite.starry.configuration

import com.copite.starry.util.Snowflake
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SnowflakesConfiguration {

    @Bean
    fun snowflake(): Snowflake {
        return Snowflake(1, 1)
    }
}
