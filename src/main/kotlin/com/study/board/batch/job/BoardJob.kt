package com.study.board.batch.job

import com.study.board.domain.jpa.CommentEntity
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import javax.persistence.EntityManagerFactory

const val BOARD_INDEX_NAME = "board"
const val ADD_DOCUMENT_STEP = "addDocument"
const val ITEM_READER_NAME = "commentItemReader"

const val CHUNK_SIE = 1000
const val PAGE_SIZE = 1000

@Configuration
class BoardJob(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory
) {

    @Bean
    fun commentJob() =
        jobBuilderFactory.get(BOARD_INDEX_NAME)
            .start(addDocumentStep())
            .build()

    @Bean
    fun addDocumentStep() =
        stepBuilderFactory.get(ADD_DOCUMENT_STEP)
            .chunk<CommentEntity, CommentEntity>(CHUNK_SIE)
            .reader(readJpaPagingItem())
            .writer(writeItem())
            .build()

    @Bean
    fun readJpaPagingItem(): JpaPagingItemReader<CommentEntity> {

        val jpaPagingItemReader = JpaPagingItemReader<CommentEntity>()
        jpaPagingItemReader.setName(ITEM_READER_NAME)
        jpaPagingItemReader.pageSize = PAGE_SIZE
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory)
//        jpaPagingItemReader.setQueryString("SELECT c FROM Comment c WHERE c.createdDateTime >= :createdDateTime")
        jpaPagingItemReader.setQueryString("SELECT c FROM CommentEntity c")
//        jpaPagingItemReader.setParameterValues(mutableMapOf("createdDateTime" to LocalDateTime.now().minusMinutes(5)) as Map<String, Any>)

        return jpaPagingItemReader
    }

    @Bean
    fun writeItem():ItemWriter<CommentEntity> = ItemWriter{
        println("writer : $it")
    }


}
