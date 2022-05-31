package com.study.board.batch

import com.study.board.batch.job.BoardJob
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class JobScheduler(
    private val jobLauncher: JobLauncher,
    private val boardJob: BoardJob,
) {

    @Scheduled(cron = "0 */5 * * * *")
    fun runJob() {
        val jobParameters = JobParameters(mutableMapOf("createdDateTimeParam" to JobParameter(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))))
        jobLauncher.run(boardJob.boardJob(), jobParameters)
    }
}
