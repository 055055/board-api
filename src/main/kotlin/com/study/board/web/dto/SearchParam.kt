package com.study.board.web.dto

import javax.validation.constraints.NotBlank

class SearchParam {
    data class Req(
        @field:NotBlank(message = "search keyword is not blank") val keyword: String,
        val totalElements: Int = 1000,
        val pageNumber: Int = 0,
    )

    data class Res(
        val totalElements: Int,
        val pageNumber: Int,
        val searchResponse: List<SearchResponse>
    ) {
        data class SearchResponse(
            val seq: Long,
            val author: String,
            val title: String,
            val content: String,
            val url: String,
        )
    }
}
