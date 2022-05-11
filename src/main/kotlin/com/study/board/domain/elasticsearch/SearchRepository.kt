package com.study.board.domain.elasticsearch

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.stereotype.Repository


private const val INDEX_NAME = "board"
private const val TYPE_NAME = "_doc"

@Repository
class SearchRepository(
    private val restHighLevelClient: RestHighLevelClient
) {


}
