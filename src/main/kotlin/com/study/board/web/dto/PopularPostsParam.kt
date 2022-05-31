package com.study.board.web.dto

class PopularPostsParam {
    data class Res(
        val popularResponse: List<PopularResponse>
    ) {
        data class PopularResponse(
            val rank: Long,
            val title: String,
            val url: String,
        )
    }
}
