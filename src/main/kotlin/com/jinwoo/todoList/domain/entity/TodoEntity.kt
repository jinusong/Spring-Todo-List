package com.jinwoo.todoList.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Todo")
data class TodoEntity(
        @Id
        val todoId: Int,
        val todoTitle: String,
        val todoContent: String,
        val writer: String)