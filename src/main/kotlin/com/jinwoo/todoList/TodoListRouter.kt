package com.jinwoo.todoList

import com.jinwoo.todoList.presentation.TodoListController
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class TodoListRouter(private val todoListController: TodoListController) {

    @Bean
    fun todoListRoutes() = router {
        "/todo".nest {
            GET("/list", todoListController::getTodoList)
            POST("/", todoListController::saveTodo)
            DELETE("/{id}", todoListController::deleteTodo)
        }
    }

}