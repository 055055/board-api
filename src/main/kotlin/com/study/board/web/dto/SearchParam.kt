package com.study.board.web.dto

import javax.validation.constraints.NotBlank

class SearchParam {
    data class Req(
        @field:NotBlank(message = "search keyword is not blank") val keyword: String,
    )

    data class Res(
        val seq: Long,
        val author: String,
        val title: String,
        val content: String,
        val url: String,
    )
}
