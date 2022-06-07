package com.study.board.service

import com.study.board.domain.jpa.PostEntity
import com.study.board.domain.jpa.PostRepository
import com.study.board.helper.checkAuthorization
import com.study.board.helper.encodePassword
import com.study.board.web.dto.CommentParam
import com.study.board.web.dto.PostParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    override fun savePost(request: PostParam.SaveReq): PostParam.SaveRes {
        val postEntity = postRepository.save(
            PostEntity(
                author = request.author,
                password = encodePassword(request.password),
                title = request.title,
                content = request.content
            )
        )
        return PostParam.SaveRes(
            seq = postEntity.seq!!,
            author = postEntity.author,
            title = postEntity.title,
            content = postEntity.content,
        )
    }

    @Transactional
    override fun updatePost(request: PostParam.UpdateReq, seq: Long): PostParam.UpdateRes {
        val postEntity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        checkAuthorization(
            originAuthor = postEntity.author,
            originPassword = postEntity.password,
            requestAuthor = request.author,
            requestPassword = request.password
        )

        postEntity.update(
            author = request.author,
            password = request.password,
            title = request.title,
            content = request.content
        )

        return PostParam.UpdateRes(
            seq = postEntity.seq!!,
            author = postEntity.author,
            title = postEntity.title,
            content = postEntity.content,
        )
    }

    @Transactional
    override fun deletePost(request: PostParam.DeleteReq, seq: Long) {
        val entity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        checkAuthorization(
            originAuthor = entity.author,
            originPassword = entity.password,
            requestAuthor = request.author,
            requestPassword = request.password
        )
        postRepository.delete(entity)
    }

    @Transactional
    override fun getPost(seq: Long): PostParam.GetRes {
        val postEntity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return PostParam.GetRes(
            seq = postEntity.seq!!,
            author = postEntity.author,
            title = postEntity.title,
            content = postEntity.content,
            hits = postEntity.addHits(),
            comments = postEntity.comment.map {
                CommentParam.GetRes(
                    seq = it.seq!!,
                    author = it.author,
                    content = it.content
                )
            }
        )
    }

    @Transactional(readOnly = true)
    override fun getAllPosts(): List<PostEntity> = postRepository.findAll()

    @Transactional
    override fun getWeeklyPopularPosts(): List<PostEntity> =
        postRepository.findWeeklyPopularPosts().orElseThrow { IllegalAccessException("not found") }

}
