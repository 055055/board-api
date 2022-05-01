package com.study.board.service

import com.study.board.domain.PostEntity
import com.study.board.domain.PostRepository
import com.study.board.helper.checkAuthorization
import com.study.board.helper.encodePassword
import com.study.board.web.dto.PostParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    override fun savePost(req: PostParam.SaveReq): PostEntity =
        postRepository.save(
            PostEntity(
                req.author,
                encodePassword(req.password),
                req.title,
                req.content
            )
        )

    @Transactional
    override fun updatePost(req: PostParam.UpdateReq, seq: Long): PostEntity {
        val entity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        checkAuthorization(entity.author, entity.password, req.author, req.password)
        entity.update(req.author, req.password, req.title, req.content)
        return entity
    }

    @Transactional
    override fun deletePost(req: PostParam.DeleteReq, seq: Long) {
        val entity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        checkAuthorization(entity.author, entity.password, req.author, req.password)
        postRepository.delete(entity)
    }

    @Transactional
    override fun findPost(seq: Long): PostParam.FindRes {
        val findById = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return PostParam.FindRes(findById.seq!!, findById.author, findById.password, findById.title, findById
            .content, findById.addHits())
    }
}
