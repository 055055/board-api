package com.study.board.config.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory =
        LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6379))

    @Bean(name = ["redisTemplate"])
    fun restTemplate(): RedisTemplate<String, Any> {
        val redisTemplate: RedisTemplate<String, Any> = RedisTemplate()
        val jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<Any> = Jackson2JsonRedisSerializer(Any::class.java)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer
        return redisTemplate
    }
}
