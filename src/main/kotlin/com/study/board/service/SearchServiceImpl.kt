package com.study.board.service

import com.study.board.domain.elasticsearch.SearchRepository
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    private val searchRepository: SearchRepository
): SearchService {
    override fun search() {
        searchRepository.search()
    }
}
