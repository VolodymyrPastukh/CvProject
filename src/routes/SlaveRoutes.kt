package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.DependencyProvider
import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.vovan.interactor.GetAllRespond
import com.vovan.interactor.GetOneRespond
import com.vovan.interactor.Query
import com.vovan.interactor.SearchEngine
import com.vovan.models.CvData
import exceptions.AbsentElementException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*

fun Application.registerGetRoutes() {
    routing {
        getAllCvData()
        getCvDataByTitle()
    }
}

private fun Route.getAllCvData() {
    get("/{query}") {
        val query = call.parameters["query"] ?:
            return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)

        try {
            val respond = getcase.getRequest(query) as GetAllRespond
            val result = respond.data
            call.respond(MustacheContent("${query}.hbs", mapOf(query to result)))
        } catch (e: AbsentElementException) {
            call.respond(MustacheContent("error.hbs", null))
        }
    }

    static("static") {
        resources("css")
    }
    static("pictures") {
        resources("files")
    }
}

private fun Route.getCvDataByTitle() {
    get("/{query}/{title}") {
        val query =
            call.parameters["query"] ?: return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)

        val title =
            call.parameters["title"] ?: return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)
        try {
            val respond = getcase.getRequest(query, title) as GetOneRespond
            val result = respond.data
            call.respond(MustacheContent("single${query.capitalize()}.hbs", mapOf(query to result)))
        } catch (e: AbsentElementException) {
            call.respond(MustacheContent("error.hbs", null))
        }
    }

    get("/{smth}/{query}/{title}"){
        val query =
            call.parameters["query"] ?: return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)

        val title =
            call.parameters["title"] ?: return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)
        try {
            val respond = getcase.getRequest(query, title) as GetOneRespond
            val result = respond.data
            call.respond(MustacheContent("single${query.capitalize()}.hbs", mapOf(query to result)))
        } catch (e: AbsentElementException) {
            call.respond(MustacheContent("error.hbs", null))
        }
    }

    static("static") {
        resources("css")
    }
    static("pictures") {
        resources("files")
    }
}



val getcase = DependencyProvider.usecase
