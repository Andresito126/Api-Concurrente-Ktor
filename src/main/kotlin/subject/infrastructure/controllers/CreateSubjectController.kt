package subject.infrastructure.controllers

import subject.application.CreateSubjectUseCase
import subject.domain.Subject

class CreateSubjectController(private val useCase: CreateSubjectUseCase) {
    suspend fun handle(subject: Subject): Subject {
        return useCase.execute(subject)
    }
}
