package com.study.board.web.dto

class PostsParam {
    data class SaveReq(val author: String, val password: String, val title: String, val content: String)
    data class SaveRes(val seq: Long, val author: String, val password: String, val title: String, val content: String)
    data class UpdateReq(val author: String, val password: String, val title: String, val content: String)
    data class FindRes(val seq: Long?, val author: String, val password: String, val title: String, val content: String)
}