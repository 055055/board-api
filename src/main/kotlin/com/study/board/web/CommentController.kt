package com.study.board.web

import com.study.board.service.CommentService
import com.study.board.web.dto.CommentParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RequestMapping("/v1/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun saveComment(@Valid @RequestBody request: CommentParam.SaveReq): ResponseEntity<CommentParam.SaveRes> {
        val response = commentService.saveComment(request)
        return ResponseEntity.created(URI.create("/v1/comments/" + response.seq)).body(response)
    }

    @PatchMapping("/{seq}")
    fun updateComment(@PathVariable seq: Long, @Valid @RequestBody request: CommentParam.UpdateReq): ResponseEntity<CommentParam.UpdateRes> =
        ResponseEntity.ok(commentService.updateComment(request, seq))

    @DeleteMapping("/{seq}")
    fun deleteComment(@PathVariable seq: Long, @Valid @RequestBody request: CommentParam.DeleteReq): ResponseEntity<Any> {
        commentService.deleteComment(request, seq)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{seq}")
    fun getComment(@PathVariable seq: Long): ResponseEntity<CommentParam.GetRes> = ResponseEntity.ok(commentService.getComment(seq))
}
