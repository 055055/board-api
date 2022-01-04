package com.study.board.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository:JpaRepository<Long, PostsEntity> {
}