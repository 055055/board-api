package com.study.board.service

import com.study.board.domain.elasticsearch.BoardRepository
import com.study.board.web.dto.SearchParam
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    private val boardRepository: BoardRepository
): SearchService {
    override fun search(searchParamReq: SearchParam.Req) {
        boardRepository.search(searchParamReq)
    }
}
