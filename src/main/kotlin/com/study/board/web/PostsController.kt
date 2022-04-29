package com.study.board.web

import com.study.board.domain.PostsEntity
import com.study.board.service.PostsService
import com.study.board.web.dto.PostsParam
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/posts")
@RestController
class PostsController(
    private val postsService: PostsService
) {

    @PostMapping
    fun savePosts(@RequestBody postParamSaveReq: PostsParam.SaveReq): PostsEntity = postsService.savePosts(postParamSaveReq)

    @PatchMapping("/{seq}")
    fun updatePosts(@PathVariable seq: Long, @RequestBody postParamUpdateReq: PostsParam.UpdateReq): PostsEntity = postsService.updatePosts(postParamUpdateReq, seq)

    @DeleteMapping("/{seq}")
    fun deletePosts(@PathVariable seq: Long, @RequestBody postParamDeleteReq: PostsParam.DeleteReq) {
        postsService.deletePosts(postParamDeleteReq, seq)
    }

    @GetMapping("/{seq}")
    fun findPosts(@PathVariable seq: Long): PostsParam.FindRes = postsService.findPosts(seq)
}
