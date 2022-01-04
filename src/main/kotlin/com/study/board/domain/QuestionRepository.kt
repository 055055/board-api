package com.study.board.domain

import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository:JpaRepository<Long, QuestionEntity> {
}