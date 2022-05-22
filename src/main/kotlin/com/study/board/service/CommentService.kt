package com.study.board.service

import com.study.board.web.dto.CommentParam

interface CommentService {
    fun saveComment(commentParamSaveReq: CommentParam.SaveReq): CommentParam.SaveRes
    fun updateComment(commentParamUpdateReq: CommentParam.UpdateReq, seq: Long): CommentParam.UpdateRes
    fun deleteComment(commentParamDeleteReq: CommentParam.DeleteReq, seq: Long)
    fun getComment(seq: Long): CommentParam.GetRes
}
