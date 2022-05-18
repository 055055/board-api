package com.study.board.batch.job

import com.study.board.domain.elasticsearch.BoardRepository
import com.study.board.domain.jpa.PostEntity
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.annotation.DateTimeFormat
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
    private val entityManagerFactory: EntityManagerFactory,
    private val boardRepository: BoardRepository,
) {

    @Bean
    fun commentJob() =
        jobBuilderFactory.get(BOARD_INDEX_NAME)
            .start(addDocumentStep())
            .build()

    @Bean
    fun addDocumentStep() =
        stepBuilderFactory.get(ADD_DOCUMENT_STEP)
            .chunk<PostEntity, PostEntity>(CHUNK_SIE)
            .reader(readJpaPagingItem(null))
            .writer(writeItem())
            .build()

    @Bean
    @StepScope
    fun readJpaPagingItem(
        @Value("#{jobParameters[createdDateTimeParam]}") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") createdDateTimeParam: LocalDateTime?
    ): JpaPagingItemReader<PostEntity> {

        var createdDateTime = createdDateTimeParam?.minusMinutes(5) ?: LocalDateTime.now().minusMinutes(5)

        return JpaPagingItemReaderBuilder<PostEntity>()
            .name(ITEM_READER_NAME)
            .pageSize(PAGE_SIZE)
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT p FROM PostEntity p WHERE p.createdDateTime >= " +
                ":createdDateTime ORDER BY p.seq desc")
            .parameterValues(mapOf("createdDateTime" to createdDateTime) as Map<String, Any>)
            .build()
    }

    @Bean
    fun writeItem(): ItemWriter<PostEntity> = ItemWriter {
//        boardRepository.addDocument(it)
    }
}
