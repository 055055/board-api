package com.study.board.domain

import javax.persistence.*

@Entity
@Table(name = "posts")
class PostsEntity(author: String, password: String, title: String, content: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var seq: Long? = null
        protected set
    var author: String = author
        protected set
    var password: String = password
        protected set
    var title: String = title
        protected set
    var content: String = content
        protected set

    fun update(author: String, password: String, title: String, content: String) {
       this.author = author
       this.password = password
       this.title = title
       this.content = content
    }
}