package student.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import student.application.repositories.IStudentRepository
import student.domain.Student
import subject.application.repositories.ISubjectRepository

class CreateStudentUseCase(
    private val studentRepository: IStudentRepository,
    private val subjectRepository: ISubjectRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(student: Student): Student = withContext(Dispatchers.IO) {
        logger.info("registrando: ${student.name} en hilo: ${Thread.currentThread().name}")
        
        if (studentRepository.existsById(student.id)) {
            throw IllegalArgumentException("ya existe un estudiante con el id: ${student.id}")
        }
        
        // validar que todas las materias existan
        student.subjectIds.forEach { subjectId ->
            if (!subjectRepository.existsById(subjectId)) {
                throw IllegalArgumentException("no existe la materia con id: $subjectId")
            }
        }
        
        studentRepository.save(student)
    }
}
