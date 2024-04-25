package com.copite.starry.configuration

import com.copite.starry.util.Snowflake
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


// Usage:
// @Id
// @GeneratedValue(generator = "SnowflakeIdGenerator")
// @GenericGenerator(name = "SnowflakeIdGenerator", strategy = "com.copite.starry.configuration.SnowflakeIdGenerator")

@Component
class SnowflakeIdGenerator : IdentifierGenerator {

    @Autowired
    private lateinit var snowflake: Snowflake

    override fun generate(session: SharedSessionContractImplementor, any: Any): Any {
        try {
            val id = any.javaClass.getDeclaredField("id")
            id.isAccessible = true
            val value = id.get(any)
            if (value != null) {
                return value
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return snowflake.nextId()
    }
}
