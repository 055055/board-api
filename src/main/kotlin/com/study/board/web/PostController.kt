package com.study.board.web

import com.study.board.domain.jpa.PostEntity
import com.study.board.service.PostService
import com.study.board.web.dto.PostParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RequestMapping("/v1/posts")
@RestController
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun savePost(@Valid @RequestBody postParamSaveReq: PostParam.SaveReq): ResponseEntity<PostParam.SaveRes> {
        val response = postService.savePost(postParamSaveReq)
        return ResponseEntity.created(URI.create("/v1/posts/"+response.seq)).body(response)
    }

    @PatchMapping("/{seq}")
    fun updatePost(@PathVariable seq: Long, @Valid @RequestBody postParamUpdateReq: PostParam.UpdateReq): ResponseEntity<PostParam.UpdateRes> =
        ResponseEntity.ok(postService.updatePost(postParamUpdateReq, seq))

    @DeleteMapping("/{seq}")
    fun deletePost(@PathVariable seq: Long, @Valid @RequestBody postParamDeleteReq: PostParam.DeleteReq): ResponseEntity<Any> {
        postService.deletePost(postParamDeleteReq, seq)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{seq}")
    fun getPost(@PathVariable seq: Long): ResponseEntity<PostParam.GetRes> = ResponseEntity.ok(postService.getPost(seq))

    @GetMapping
    fun getAllPosts() : ResponseEntity<List<PostEntity>> = ResponseEntity.ok(postService.getAllPosts())
}
