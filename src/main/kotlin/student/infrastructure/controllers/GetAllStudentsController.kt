package student.infrastructure.controllers

import student.application.GetAllStudentsWithSubjectsUseCase
import student.domain.StudentWithSubjects

class GetAllStudentsController(private val useCase: GetAllStudentsWithSubjectsUseCase) {
    suspend fun handle(): List<StudentWithSubjects> {
        return useCase.execute()
    }
}
