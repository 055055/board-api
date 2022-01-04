package com.study.board.web

import com.study.board.service.PostsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/posts")
@RestController
class PostsController(
        private val postsService: PostsService
) {

    @GetMapping
    fun findQuestion(): String = postsService.findPosts()

}