package student.infrastructure.controllers

import student.application.GetStudentByIdUseCase
import student.domain.Student

class GetStudentByIdController(private val useCase: GetStudentByIdUseCase) {
    suspend fun handle(id: String): Student? {
        return useCase.execute(id)
    }
}
