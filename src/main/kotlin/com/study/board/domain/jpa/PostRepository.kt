package com.study.board.domain.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository:JpaRepository<PostEntity, Long> {
}
