package com.study.board.domain.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {
    fun <T> findByKey(key: String, clazz: Class<T>):T? {
        return objectMapper.convertValue(redisTemplate.opsForValue()[key], clazz)
    }

    fun save(key: String, value: Any, timeout: Long = 1000L, timeUnit: TimeUnit = TimeUnit.MILLISECONDS) {
        redisTemplate.opsForValue()[key, value, timeout] = timeUnit
    }
}
