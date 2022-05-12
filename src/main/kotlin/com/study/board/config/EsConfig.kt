package com.study.board.config

import org.elasticsearch.client.ElasticsearchClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import java.time.Duration

@Configuration
class EsConfig: AbstractElasticsearchConfiguration() {

    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .withConnectTimeout(Duration.ofSeconds(10))
            .withSocketTimeout(Duration.ofSeconds(10))
            .build()

        return RestClients.create(clientConfiguration).rest()
    }
}
