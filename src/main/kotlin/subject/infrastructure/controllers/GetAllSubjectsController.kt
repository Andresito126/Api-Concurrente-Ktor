package subject.infrastructure.controllers

import subject.application.GetAllSubjectsUseCase
import subject.domain.Subject

class GetAllSubjectsController(private val useCase: GetAllSubjectsUseCase) {
    suspend fun handle(): List<Subject> {
        return useCase.execute()
    }
}
