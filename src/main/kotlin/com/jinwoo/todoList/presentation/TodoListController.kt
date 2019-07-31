package com.jinwoo.todoList.presentation

import com.jinwoo.todoList.domain.service.TodoListService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono

@Component
class TodoListController(private val todoListService: TodoListService) {

    fun getTodoList(serverRequest: ServerRequest)
            = ok().header(MediaType.APPLICATION_JSON_VALUE)
            .body(todoListService.getTodoList()
                .flatMap { ok().body(fromObject(it)) })

    fun saveTodo(serverRequest: ServerRequest)
            = todoListService.saveTodo(serverRequest.bodyToMono())
            .flatMap { ok().build() }

    fun deleteTodo(serverRequest: ServerRequest)
            = todoListService.deleteTodo(serverRequest.pathVariable("id").toInt())
            .flatMap {
                if (it) ok().build()
                else status(HttpStatus.NOT_FOUND).build()
            }

}