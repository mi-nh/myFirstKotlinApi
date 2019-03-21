package com.minh

import com.fasterxml.jackson.databind.SerializationFeature
import com.minh.dao.CourseDaoImpl
import com.minh.extensions.log
import com.minh.model.ErrorMessage
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val coursedao = CourseDaoImpl.instance

    val server = embeddedServer(Tomcat, 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        routing {
            get("/") {
                call.respondText("Welcome to my brand new server !", ContentType.Text.Plain)
            }
            get("/courses") {
                val allCourses = coursedao.findAll();
                allCourses.forEach { course -> course.log() }
                call.respond(allCourses)
            }
            get("/course/top") {
                val topCourse = coursedao.findCourseByLevelMax()
                topCourse?.let { topCourse.log(); call.respond(it) }
            }
            get("/course/{id}") {
                try {
                    val idCourse = call.parameters["id"]?.toInt()
                    if (idCourse != null) {
                        val course = coursedao.findCourseById(idCourse)
                        if (course != null) {
                            course.log()
                            call.respond(course)
                        } else {
                            val errorMsg = ErrorMessage(HttpStatusCode.NotFound.value, "Sorry! No course were found...")
                            errorMsg.log()
                            call.respond(errorMsg)
                        }
                    }
                } catch (exception: Exception) {
                    exception.log() //Extension sur l'objet java.lang.Exception
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
    server.start(wait = true);
}

