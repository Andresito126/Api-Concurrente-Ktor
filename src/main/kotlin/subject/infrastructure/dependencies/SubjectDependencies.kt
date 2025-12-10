package subject.infrastructure.dependencies

import subject.application.CreateSubjectUseCase
import subject.application.GetAllSubjectsUseCase
import subject.application.GetSubjectByIdUseCase
import subject.infrastructure.adapters.InMemorySubjectAdapter
import subject.infrastructure.controllers.CreateSubjectController
import subject.infrastructure.controllers.GetAllSubjectsController
import subject.infrastructure.controllers.GetSubjectByIdController

object SubjectDependencies {
    
    private val subjectRepository = InMemorySubjectAdapter()
    
    private val getAllSubjectsUseCase = GetAllSubjectsUseCase(subjectRepository)
    private val getSubjectByIdUseCase = GetSubjectByIdUseCase(subjectRepository)
    private val createSubjectUseCase = CreateSubjectUseCase(subjectRepository)
    
    val getAllSubjectsController = GetAllSubjectsController(getAllSubjectsUseCase)
    val getSubjectByIdController = GetSubjectByIdController(getSubjectByIdUseCase)
    val createSubjectController = CreateSubjectController(createSubjectUseCase)
}
