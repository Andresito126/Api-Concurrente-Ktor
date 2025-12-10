package student.infrastructure.dependencies

import student.application.CreateStudentUseCase
import student.application.GetAllStudentsWithSubjectsUseCase
import student.application.GetStudentByIdUseCase
import student.infrastructure.adapters.InMemoryStudentAdapter
import student.infrastructure.controllers.CreateStudentController
import student.infrastructure.controllers.GetAllStudentsController
import student.infrastructure.controllers.GetStudentByIdController
import subject.infrastructure.adapters.InMemorySubjectAdapter

object StudentDependencies {
    
    private val studentRepository = InMemoryStudentAdapter()
    private val subjectRepository = InMemorySubjectAdapter()
    
    private val getAllStudentsWithSubjectsUseCase = GetAllStudentsWithSubjectsUseCase(studentRepository, subjectRepository)
    private val getStudentByIdUseCase = GetStudentByIdUseCase(studentRepository)
    private val createStudentUseCase = CreateStudentUseCase(studentRepository, subjectRepository)
    
    val getAllStudentsController = GetAllStudentsController(getAllStudentsWithSubjectsUseCase)
    val getStudentByIdController = GetStudentByIdController(getStudentByIdUseCase)
    val createStudentController = CreateStudentController(createStudentUseCase)
}
