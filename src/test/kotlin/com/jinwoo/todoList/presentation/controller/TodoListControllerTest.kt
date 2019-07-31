package com.jinwoo.todoList.presentation.controller

import com.jinwoo.todoList.domain.entity.TodoEntity
import com.jinwoo.todoList.domain.service.TodoListService
import org.amshove.kluent.*
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.JsonPathResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

class WithKeyword {
    infix fun `json path`(expression: String) = jsonPath("\$" + expression)
}

val With = WithKeyword()

class ThatKeyword {
    infix fun `status is http`(value: Int) = status().`is`(value)
}

val That = ThatKeyword()

infix fun JsonPathResultMatchers.`that the value is`(value: Any) = this.value(value)

infix fun WebTestClient.`do a get request to`(uri: String)
        = this.get().uri(uri)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)

infix fun WebTestClient.`do a post request to`(uri: String) = this.post().uri(uri)

infix fun WebTestClient.`do a delete request to`(uri: String) = this.delete().uri(uri)

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TodoListControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var todoListService: TodoListService

    @Test
    fun `we should Get a list of todo`() {

        When calling todoListService.getTodoList() `it returns` listOf(
                TodoEntity(1, "ㅎㅇ", "ㅎㅇ", "송진우")).toFlux()

        (webTestClient `do a get request to` "/todo/list")
                .expectBodyList<TodoEntity>()

        With `json path` "[0]todoId" `that the value is` 1


        Verify on todoListService that todoListService.getTodoList() was called
        `Verify no further interactions` on todoListService

        Mockito.reset(todoListService)
    }

    @Test
    fun `we should CREATE a todo`() {

    }

    @Test
    fun `we should DELETE a todo`() {

    }
}