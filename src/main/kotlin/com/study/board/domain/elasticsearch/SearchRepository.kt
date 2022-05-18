package com.study.board.domain.elasticsearch

import com.study.board.config.logger
import com.study.board.web.dto.SearchParam
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchRequestBuilder
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilders.*
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Repository


private const val INDEX_NAME = "board"
private const val TYPE_NAME = "_doc"

@Repository
class SearchRepository(
    private val client: RestHighLevelClient
) {

    fun search(searchParamReq: SearchParam.Req) {

        val searchSourceBuilder = SearchSourceBuilder()
        searchSourceBuilder.from(0)
        searchSourceBuilder.size(100)
        searchSourceBuilder.query(
            boolQuery()
                .must(
                    matchQuery("content", searchParamReq.keyword)
                )
                .should(
                    matchPhraseQuery("content", searchParamReq.keyword)
                )
        )
        val searchRequest = SearchRequest(INDEX_NAME)
        searchRequest.source(searchSourceBuilder)

        logger.info { client.search(searchRequest, RequestOptions.DEFAULT) }
    }

}
