package com.study.board.service

import com.study.board.domain.elasticsearch.SearchRepository
import com.study.board.web.dto.SearchParam
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    private val searchRepository: SearchRepository
): SearchService {
    override fun search(searchParamReq: SearchParam.Req) {
        searchRepository.search(searchParamReq)
    }
}
