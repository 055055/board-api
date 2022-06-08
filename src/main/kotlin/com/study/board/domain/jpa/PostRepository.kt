package com.study.board.domain.jpa

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.*

private const val weeklyPopularPostsPeriod: Long = 7

interface PostRepository : JpaRepository<PostEntity, Long> {

    @Query("""
            SELECT p FROM PostEntity p 
            WHERE p.createdDateTime >= :startDateTime
            AND p.createdDateTime <= :endDateTime
            ORDER BY p.hits DESC
        """)
    fun findWeeklyPopularPosts(
        pageable:Pageable,
        @Param("startDateTime") startDateTime: LocalDateTime = LocalDateTime.now().minusDays(weeklyPopularPostsPeriod),
        @Param("endDateTime") endDateTime: LocalDateTime = LocalDateTime.now()
    ): List<PostEntity>
}
