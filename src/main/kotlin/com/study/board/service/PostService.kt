package com.study.board.service

import com.study.board.domain.PostEntity
import com.study.board.web.dto.PostParam

interface PostService {
    fun savePost(postsParamSaveReq: PostParam.SaveReq):PostEntity
    fun updatePost(postParamUpdateReq: PostParam.UpdateReq, seq: Long):PostEntity
    fun deletePost(postParamDeleteReq: PostParam.DeleteReq, seq: Long)
    fun findPost(seq: Long):PostParam.FindRes
}
