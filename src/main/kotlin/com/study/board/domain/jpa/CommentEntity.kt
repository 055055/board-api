package com.study.board.domain.jpa

import javax.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity(post: PostEntity, author: String, password: String, content: String) : BaseEntity() {
    @Id
    @GeneratedValue
    var seq: Long? = null
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    var post: PostEntity = post
        protected set
    var author: String = author
        protected set
    var password: String = password
        protected set
    var content: String = content
        protected set

    fun update(author: String, password: String, content: String) {
        this.author = author
        this.password = password
        this.content = content
    }
}
