package com.study.board.web.dto

import javax.validation.constraints.NotBlank

class PostParam {
    data class SaveReq(
            @NotBlank val author: String,
            @NotBlank val password: String,
            @NotBlank val title: String,
            @NotBlank val content: String)
    data class SaveRes(val seq: Long, val author: String, val password: String, val title: String, val content: String)
    data class UpdateReq(val author: String, val password: String, val title: String, val content: String)
    data class DeleteReq(val author: String, val password: String)
    data class FindRes(val seq: Long?, val author: String, val password: String, val title: String, val content: String)
}
