package com.jinwoo.todoList.domain.service

import com.jinwoo.todoList.data.TodoListRepository
import com.jinwoo.todoList.domain.entity.TodoEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TodoListServiceImpl(private val todoListRepo: TodoListRepository): TodoListService {

    override fun getTodoList(): Flux<TodoEntity>
            = todoListRepo.getTodoList()

    override fun deleteTodo(todoId: Int): Mono<Boolean>
            = todoListRepo.deleteTodo(todoId).map { it.deletedCount > 0 }

    override fun saveTodo(todoEntity: Mono<TodoEntity>): Mono<TodoEntity>
            = todoListRepo.saveTodo(todoEntity)
}