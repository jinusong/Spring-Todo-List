package com.jinwoo.todoList.domain.service

import com.jinwoo.todoList.domain.entity.TodoEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface TodoListService {
    fun getTodoList(): Flux<TodoEntity>

    fun deleteTodo(todoId: Int): Mono<Boolean>

    fun saveTodo(todoEntity: Mono<TodoEntity>): Mono<TodoEntity>
}