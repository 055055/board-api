package com.study.board.service

import com.study.board.domain.elasticsearch.BoardDocument
import com.study.board.domain.jpa.PostEntity
import com.study.board.web.dto.SearchParam

interface BoardService {

    fun search(searchParamReq: SearchParam.Req): SearchParam.Res
    fun convertToDocument(postEntity: PostEntity): BoardDocument
    fun getWeeklyPopularPosts()
}
