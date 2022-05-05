package com.study.board.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
}
