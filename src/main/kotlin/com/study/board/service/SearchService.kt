package com.study.board.service

import com.study.board.web.dto.SearchParam

interface SearchService {

    fun search(searchParamReq: SearchParam.Req)
}
