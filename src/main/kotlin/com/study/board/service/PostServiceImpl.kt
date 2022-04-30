package com.study.board.service

import com.study.board.domain.PostEntity
import com.study.board.domain.PostRepository
import com.study.board.web.dto.PostParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(private val postRepository: PostRepository) : PostService {
    override fun savePost(postParamSaveReq: PostParam.SaveReq): PostEntity =
            postRepository.save(PostEntity(postParamSaveReq.author, postParamSaveReq.password, postParamSaveReq.title, postParamSaveReq.content))

    @Transactional
    override fun updatePost(postsParamUpdateReq: PostParam.UpdateReq, seq: Long): PostEntity {
        val postsEntity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        postsEntity.update(postsParamUpdateReq.author, postsParamUpdateReq.password, postsParamUpdateReq.title, postsParamUpdateReq.content)
        return postsEntity
    }

    @Transactional
    override fun deletePost(postParamDeleteReq: PostParam.DeleteReq, seq: Long) {
        val postsEntity = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        postRepository.delete(postsEntity)
    }

    @Transactional(readOnly = true)
    override fun findPost(seq: Long): PostParam.FindRes {
        val findById = postRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return PostParam.FindRes(findById.seq, findById.author, findById.password, findById.title, findById.content)
    }
}
