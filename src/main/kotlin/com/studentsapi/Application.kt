package com.studentsapi

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import student.infrastructure.routes.studentRoutes
import subject.infrastructure.routes.subjectRoutes
import ErrorResponse
import kotlinx.serialization.Serializable


@Serializable
data class StatusResponse(
    val service: String,
    val status: String,
    val endpoints: List<String>
)
private val logger = LoggerFactory.getLogger("Application")

fun main() {
    logger.info("inciando api ")
    
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configurePlugins()
        configureRouting()
    }.start(wait = true)
}

fun Application.configurePlugins() {
    
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    
    install(CallLogging) {
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val method = call.request.local.method.value
            val uri = call.request.local.uri
            "→ $method $uri | Status: $status"
        }
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logger.error("Error: ${cause.message}", cause)

            val errorMessage = cause.message ?: "Error interno del servidor"

            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(error = errorMessage) // Usamos la variable o el cálculo
            )
        }
    }
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(
                HttpStatusCode.OK,
                StatusResponse(
                    service = "Students API",
                    status = "running",
                    endpoints = listOf(
                        "GET  /students",
                        "GET  /students/{id}",
                        "POST /students",
                        "GET  /subjects",
                        "GET  /subjects/{id}",
                        "POST /subjects"
                    )
                )
            )
        }


        studentRoutes()
        subjectRoutes()
    }
}
