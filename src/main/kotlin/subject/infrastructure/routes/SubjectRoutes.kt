package subject.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import subject.domain.Subject
import subject.infrastructure.dependencies.SubjectDependencies

fun Route.subjectRoutes() {
    
    val dependencies = SubjectDependencies
    
    route("/subjects") {
        
        get {
            val subjects = dependencies.getAllSubjectsController.handle()
            call.respond(HttpStatusCode.OK, subjects)
        }
        
        get("/{id}") {
            val id = call.parameters["id"] 
                ?: return@get call.respond(HttpStatusCode.BadRequest, "ID requerido")
            
            val subject = dependencies.getSubjectByIdController.handle(id)
            
            if (subject != null) {
                call.respond(HttpStatusCode.OK, subject)
            } else {
                call.respond(HttpStatusCode.NotFound, "Materia no encontrada")
            }
        }
        
        post {
            try {
                val subject = call.receive<Subject>()
                val created = dependencies.createSubjectController.handle(subject)
                call.respond(HttpStatusCode.Created, created)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.Conflict, e.message ?: "Error")
            }
        }
    }
}
