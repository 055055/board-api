package com.study.board.web

import com.study.board.service.BoardService
import com.study.board.web.dto.SearchParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/board")
@RestController
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/search")
    fun search(searchParamReq: SearchParam.Req) = boardService.search(searchParamReq)

    @GetMapping("/weekly")
    fun getWeeklyPopularPosts() = boardService.getWeeklyPopularPosts()
}
