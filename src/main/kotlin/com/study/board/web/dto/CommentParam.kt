package com.study.board.web.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CommentParam {
    data class SaveReq(
        @field:NotNull(message = "seq is not null") val postSeq: Long,
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String,
        @field:NotBlank(message = "content is not blank") val content: String,
    )

    data class SaveRes(
        val seq: Long,
        val author: String,
        val content: String,
        val postSeq: Long,
    )

    data class UpdateReq(
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String,
        @field:NotBlank(message = "content is not blank") val content: String,
    )

    data class UpdateRes(
        val seq: Long,
        val author: String,
        val content: String,
    )

    data class DeleteReq(
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String,
    )

    data class GetRes(
        val seq: Long,
        val author: String,
        val content: String,
    )
}
