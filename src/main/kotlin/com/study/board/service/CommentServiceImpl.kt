package com.study.board.service

import com.study.board.domain.jpa.CommentEntity
import com.study.board.domain.jpa.CommentRepository
import com.study.board.domain.jpa.PostRepository
import com.study.board.helper.checkAuthorization
import com.study.board.helper.encodePassword
import com.study.board.web.dto.CommentParam
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository
) : CommentService {

    override fun saveComment(request: CommentParam.SaveReq): CommentParam.SaveRes {
        val postEntity = postRepository.findById(request.postSeq).orElseThrow { IllegalAccessException("not found") }
        val commentEntity = commentRepository.save(
            CommentEntity(
                post = postEntity,
                author = request.author,
                password = encodePassword(request.password),
                content = request.content,
            )
        )

        return CommentParam.SaveRes(
            seq = commentEntity.seq!!,
            author = commentEntity.author,
            content = commentEntity.content,
            postSeq = request.postSeq,
        )
    }

    override fun updateComment(request: CommentParam.UpdateReq, seq: Long): CommentParam.UpdateRes {
        val commentEntity = commentRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }

        checkAuthorization(
            originAuthor = commentEntity.author,
            originPassword = commentEntity.password,
            requestAuthor = request.author,
            requestPassword = request.password,
        )

        commentEntity.update(
            author = request.author,
            password = request.password,
            content = request.content,
        )

        return CommentParam.UpdateRes(
            seq = commentEntity.seq!!,
            author = commentEntity.author,
            content = commentEntity.content,
        )
    }

    override fun deleteComment(request: CommentParam.DeleteReq, seq: Long) {
        val commentEntity = commentRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }

        checkAuthorization(
            originAuthor = commentEntity.author,
            originPassword = commentEntity.password,
            requestAuthor = request.author,
            requestPassword = request.password,
        )

        commentRepository.delete(commentEntity)
    }

    override fun getComment(seq: Long): CommentParam.GetRes {
        val commentEntity = commentRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return CommentParam.GetRes(
            seq = commentEntity.seq!!,
            author = commentEntity.author,
            content = commentEntity.content,
        )
    }
}
