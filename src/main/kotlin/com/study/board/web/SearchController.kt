package com.study.board.web

import com.study.board.service.SearchService
import com.study.board.web.dto.SearchParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/search")
@RestController
class SearchController(
    private val searchService: SearchService
) {

    @GetMapping
    fun search(searchParamReq: SearchParam.Req) = searchService.search(searchParamReq)


}
