package student.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import student.domain.Student
import student.infrastructure.dependencies.StudentDependencies

fun Route.studentRoutes() {
    
    val dependencies = StudentDependencies
    
    route("/students") {
        
        get {
            val students = dependencies.getAllStudentsController.handle()
            call.respond(HttpStatusCode.OK, students)
        }
        
        get("/{id}") {
            val id = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.BadRequest, "ID requerido")
            
            val student = dependencies.getStudentByIdController.handle(id)
            
            if (student != null) {
                call.respond(HttpStatusCode.OK, student)
            } else {
                call.respond(HttpStatusCode.NotFound, "Estudiante no encontrado")
            }
        }
        
        post {
            try {
                val student = call.receive<Student>()
                val created = dependencies.createStudentController.handle(student)
                call.respond(HttpStatusCode.Created, created)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Error")
            }
        }
    }
}
