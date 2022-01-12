package com.study.board.service

import com.study.board.domain.PostsEntity
import com.study.board.web.dto.PostsParam

interface PostsService {
    fun savePosts(postsParamSaveReq: PostsParam.SaveReq):PostsEntity
    fun updatePosts(postParamUpdateReq: PostsParam.UpdateReq, seq: Long):PostsEntity
    fun deletePosts(postParamDeleteReq: PostsParam.DeleteReq, seq: Long)
    fun findPosts(seq: Long):PostsParam.FindRes
}