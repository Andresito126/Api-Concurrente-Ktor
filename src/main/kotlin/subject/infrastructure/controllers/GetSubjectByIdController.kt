package subject.infrastructure.controllers

import subject.application.GetSubjectByIdUseCase
import subject.domain.Subject

class GetSubjectByIdController(private val useCase: GetSubjectByIdUseCase) {
    suspend fun handle(id: String): Subject? {
        return useCase.execute(id)
    }
}
