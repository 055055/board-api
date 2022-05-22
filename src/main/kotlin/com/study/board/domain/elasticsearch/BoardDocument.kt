package com.study.board.domain.elasticsearch

data class BoardDocument(
    val seq: Long,
    val author: String,
    val title: String,
    val content: String,
    val hits: Long,
    val comments: List<Comment>?,
) {
    data class Comment(
        val seq: Long,
        val author: String,
        val content: String,
    )
}
