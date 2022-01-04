package com.study.board.domain

import javax.persistence.*

@Entity
@Table(name = "question")
class QuestionEntity(author: String, password: String, title: String, content: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var seq: Long? = null
    var author: String = author
    var password: String = password
    var title: String = title
    var content: String = content
}