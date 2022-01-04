package com.study.board.service

interface BoardService {
    fun saveQuestion():String
    fun updateQuestion():String
    fun deleteQuestion():String
    fun findQuestion():String
}