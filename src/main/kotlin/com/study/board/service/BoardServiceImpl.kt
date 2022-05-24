package com.study.board.service

import com.study.board.domain.elasticsearch.BoardDocument
import com.study.board.domain.elasticsearch.BoardRepository
import com.study.board.domain.jpa.PostEntity
import com.study.board.web.dto.SearchParam
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.streams.toList


@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository,
   @Value("\${app.url}") private val appUrl:String
) : BoardService {

    val searchPostUrl: String by lazy {
        "$appUrl/v1/posts/"
    }

    override fun search(searchParamReq: SearchParam.Req): SearchParam.Res {
        val searchResponse = boardRepository.search(searchParamReq)
            .stream().map {
                SearchParam.Res.SearchResponse(
                    seq = it.seq,
                    author = it.author,
                    title = it.title,
                    content = it.content,
                    url = "$searchPostUrl${it.seq}"
                )
            }.toList()

        return SearchParam.Res(
            totalElements = searchResponse.size,
            pageNumber = searchParamReq.pageNumber,
            searchResponse = searchResponse
        )
    }

    override fun convertToDocument(postEntity: PostEntity): BoardDocument =
        postEntity.let { post ->
            BoardDocument(
                seq = post.seq!!,
                author = post.author,
                title = post.title,
                content = post.content,
                hits = post.hits,
                comments = post.comment.map {
                    BoardDocument.Comment(
                        seq = it.seq!!,
                        content = it.content,
                        author = it.author
                    )
                }
            )
        }
}
