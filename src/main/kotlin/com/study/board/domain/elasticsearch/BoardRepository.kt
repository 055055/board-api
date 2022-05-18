package com.study.board.domain.elasticsearch

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.study.board.config.logger
import com.study.board.web.dto.SearchParam
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders.*
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


private const val INDEX_NAME = "board"
private const val TYPE_NAME = "_doc"

@Repository
class BoardRepository(
    private val client: RestHighLevelClient,
    private val objectMapper: ObjectMapper,
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

    fun bulkDocuments(boardDocuments: List<BoardDocument>){
        val bulkRequest = BulkRequest()
        boardDocuments.forEach {
            val indexRequest = IndexRequest(INDEX_NAME)
            indexRequest.source(getBoardMap(it))
            bulkRequest.add(IndexRequest(INDEX_NAME).source(XContentType.JSON)
            )
        }

        client.bulk(bulkRequest, RequestOptions.DEFAULT)
    }

    private fun getBoardMap(boardDocument: BoardDocument) = objectMapper.convertValue(boardDocument, Map::class.java)
}
