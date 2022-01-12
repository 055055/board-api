package com.study.board.service

import com.study.board.domain.PostsEntity
import com.study.board.domain.PostsRepository
import com.study.board.web.dto.PostsParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsServiceImpl(private val postsRepository: PostsRepository) : PostsService {
    override fun savePosts(postsParamSaveReq: PostsParam.SaveReq): PostsEntity =
            postsRepository.save(PostsEntity(postsParamSaveReq.author, postsParamSaveReq.password, postsParamSaveReq.title, postsParamSaveReq.content))

    @Transactional
    override fun updatePosts(postsParamUpdateReq: PostsParam.UpdateReq, seq: Long): PostsEntity {
        val postsEntity = postsRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        postsEntity.update(postsParamUpdateReq.author, postsParamUpdateReq.password, postsParamUpdateReq.title, postsParamUpdateReq.content)
        return postsEntity
    }

    @Transactional
    override fun deletePosts(postParamDeleteReq: PostsParam.DeleteReq, seq: Long) {
        val postsEntity = postsRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        postsRepository.delete(postsEntity)
    }

    @Transactional(readOnly = true)
    override fun findPosts(seq: Long): PostsParam.FindRes {
        val findById = postsRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return PostsParam.FindRes(findById.seq, findById.author, findById.password, findById.title, findById.content)
    }
}