package com.study.board.service

import com.study.board.domain.elasticsearch.BoardDocument
import com.study.board.domain.elasticsearch.BoardRepository
import com.study.board.domain.elasticsearch.Comment
import com.study.board.domain.jpa.PostEntity
import com.study.board.web.dto.SearchParam
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
) : BoardService {
    override fun search(searchParamReq: SearchParam.Req) {
        boardRepository.search(searchParamReq)
    }

    override fun convertToDocument(postEntity: PostEntity): BoardDocument =
        postEntity?.let { post ->
            BoardDocument(
                seq = post.seq!!,
                author = post.author,
                title = post.title,
                content = post.content,
                hits = post.hits,
                comments = post.comment?.map {
                    Comment(
                        seq = it.seq!!,
                        content = it.content,
                        author = it.author
                    )
                }
            )
        }
}

