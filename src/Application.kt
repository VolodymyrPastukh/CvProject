package com.vovan

import io.ktor.application.*
import com.github.mustachejava.DefaultMustacheFactory
import com.jetbrains.handson.httpapi.routes.registerGetRoutes
import io.ktor.features.*
import io.ktor.mustache.Mustache
import io.ktor.serialization.*
import routes.registerNavigationRoutes
import routes.registerPostRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates/mustache")
    }
    install(ContentNegotiation) {
        json()
    }
    registerGetRoutes()
    registerPostRoutes()
    registerNavigationRoutes()
}



