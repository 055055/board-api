package com.study.board.domain.elasticsearch

import java.time.LocalDateTime

data class BoardDocument(
    val seq: Long,
    val author: String,
    val title: String,
    val content: String,
    val hits: Long,
    val comments: List<Comment>?,
    val createdDateTime: LocalDateTime,
    val modifiedDateTime: LocalDateTime,
) {
    data class Comment(
        val seq: Long,
        val author: String,
        val content: String,
        val createdDateTime: LocalDateTime,
        val modifiedDateTime: LocalDateTime,
    )
}
