package student.infrastructure.controllers

import student.application.CreateStudentUseCase
import student.domain.Student

class CreateStudentController(private val useCase: CreateStudentUseCase) {
    suspend fun handle(student: Student): Student {
        return useCase.execute(student)
    }
}
