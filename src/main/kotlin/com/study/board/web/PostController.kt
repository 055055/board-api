package com.study.board.web

import com.study.board.domain.PostEntity
import com.study.board.service.PostService
import com.study.board.web.dto.PostParam
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/v1/posts")
@RestController
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun savePost(@Valid @RequestBody postParamSaveReq: PostParam.SaveReq): PostEntity = postService.savePost(postParamSaveReq)

    @PatchMapping("/{seq}")
    fun updatePost(@PathVariable seq: Long, @Valid @RequestBody postParamUpdateReq: PostParam.UpdateReq): PostEntity =
        postService.updatePost(postParamUpdateReq, seq)

    @DeleteMapping("/{seq}")
    fun deletePost(@PathVariable seq: Long, @Valid @RequestBody postParamDeleteReq: PostParam.DeleteReq) =
        postService.deletePost(postParamDeleteReq, seq)

    @GetMapping("/{seq}")
    fun findPost(@PathVariable seq: Long): PostParam.FindRes = postService.findPost(seq)
}
