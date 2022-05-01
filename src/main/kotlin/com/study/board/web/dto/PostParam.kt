package com.study.board.web.dto

import javax.validation.constraints.NotBlank

class PostParam {
    data class SaveReq(
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String,
        @field:NotBlank(message = "title is not blank") val title: String,
        @field:NotBlank(message = "content is not blank") val content: String
    )

    data class SaveRes(
        val seq: Long,
        val author: String,
        val password: String,
        val title: String,
        val content: String
    )

    data class UpdateReq(
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String,
        @field:NotBlank(message = "title is not blank") val title: String,
        @field:NotBlank(message = "content is not blank") val content: String
    )

    data class DeleteReq(
        @field:NotBlank(message = "author is not blank") val author: String,
        @field:NotBlank(message = "password is not blank") val password: String
    )

    data class FindRes(
        val seq: Long,
        val author: String,
        val password: String,
        val title: String,
        val content: String,
        val hits: Long,
    )
}
