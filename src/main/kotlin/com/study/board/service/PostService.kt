package com.study.board.service

import com.study.board.domain.jpa.PostEntity
import com.study.board.web.dto.PostParam

interface PostService {
    fun savePost(postsParamSaveReq: PostParam.SaveReq): PostParam.SaveRes
    fun updatePost(postParamUpdateReq: PostParam.UpdateReq, seq: Long): PostParam.UpdateRes
    fun deletePost(postParamDeleteReq: PostParam.DeleteReq, seq: Long)
    fun getPost(seq: Long): PostParam.GetRes
    fun getAllPosts(): List<PostEntity>
    fun getWeeklyPopularPosts():List<PostEntity>
}
