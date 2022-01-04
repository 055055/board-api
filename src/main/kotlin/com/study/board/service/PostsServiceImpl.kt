package com.study.board.service

import com.study.board.domain.PostsEntity
import com.study.board.domain.PostsRepository
import com.study.board.web.dto.PostsParam
import org.springframework.stereotype.Service

@Service
class PostsServiceImpl(private val postsRepository: PostsRepository) : PostsService {
    override fun savePosts(postsParamSaveReq: PostsParam.SaveReq): PostsEntity =
            postsRepository.save(PostsEntity(postsParamSaveReq.author, postsParamSaveReq.password, postsParamSaveReq.title, postsParamSaveReq.content))

    override fun updatePosts(): String {
        TODO("Not yet implemented")
    }

    override fun deletePosts(): String {
        TODO("Not yet implemented")
    }

    override fun findPosts(seq: Long): PostsParam.FindRes {
        val findById = postsRepository.findById(seq).orElseThrow { IllegalAccessException("not found") }
        return PostsParam.FindRes(findById.seq, findById.author, findById.password, findById.title, findById.content)
    }
}