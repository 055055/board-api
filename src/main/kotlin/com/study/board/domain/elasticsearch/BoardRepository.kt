package com.study.board.domain.elasticsearch

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.study.board.config.logger
import com.study.board.web.dto.SearchParam
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.index.query.QueryBuilders.*
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Repository


private const val INDEX_NAME = "board"
private const val TYPE_NAME = "_doc"

@Repository
class BoardRepository(
    private val client: RestHighLevelClient,
    private val objectMapper: ObjectMapper,
) {

    fun search(searchParamReq: SearchParam.Req): List<BoardDocument> {
        val offset = searchParamReq.pageNumber * searchParamReq.totalElements

        val searchSourceBuilder = SearchSourceBuilder()
        searchSourceBuilder.from(offset)
        searchSourceBuilder.size(searchParamReq.totalElements)
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
        return getBoardDocument(client.search(searchRequest, RequestOptions.DEFAULT))
    }

    fun bulkDocuments(boardDocuments: List<BoardDocument>) {
        logger.info("boardDocument: $boardDocuments")
        val bulkRequest = BulkRequest()
        boardDocuments.forEach {
            val indexRequest = IndexRequest(INDEX_NAME)
            indexRequest.source(getBoardMap(it))
            bulkRequest.add(indexRequest)
        }

        client.bulk(bulkRequest, RequestOptions.DEFAULT)
    }

    private fun getBoardMap(boardDocument: BoardDocument): Map<String, Any> {

        return objectMapper.convertValue(boardDocument,
            object : TypeReference<Map<String, Any>>() {})
    }

    private fun getBoardDocument(searchResponse: SearchResponse): List<BoardDocument> {
        logger.info("searchResponse: $searchResponse")
        return searchResponse.hits.hits.map {
            objectMapper.convertValue(it.sourceAsMap, BoardDocument::class.java)
        }
    }
}
