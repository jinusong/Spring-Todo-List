package com.jinwoo.todoList.data

import com.jinwoo.todoList.domain.entity.TodoEntity
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TodoListRepository(private val template: ReactiveMongoTemplate) {

    fun getTodoList(): Flux<TodoEntity>
            = template.findAll(TodoEntity::class.java)

    fun saveTodo(todoEntity: Mono<TodoEntity>): Mono<TodoEntity>
            = template.save(todoEntity)

    fun deleteTodo(todoId: Int): Mono<DeleteResult>
            = template.remove<TodoEntity>(Query(where("todoId").isEqualTo(todoId)))

}