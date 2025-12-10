package student.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import student.application.repositories.IStudentRepository
import student.domain.StudentWithSubjects
import subject.application.repositories.ISubjectRepository

class GetAllStudentsWithSubjectsUseCase(
    private val studentRepository: IStudentRepository,
    private val subjectRepository: ISubjectRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)


    suspend fun execute(): List<StudentWithSubjects> = withContext(Dispatchers.IO) {
        logger.info("hilo principal: ${Thread.currentThread().name}")
        val students = studentRepository.findAll()
        logger.info("se encontraron ${students.size} estudiantes")

        val studentsWithSubjects = coroutineScope {
            val deferredResults = students.map { student ->
                async(Dispatchers.IO) {
                    logger.info(" buscando materias para '${student.name}' en hilo: ${Thread.currentThread().name}")
                    //busca en paralelo
                    val subjects = student.subjectIds.mapNotNull { subjectId ->
                        subjectRepository.findById(subjectId)
                    }
                    logger.info(" el studiante '${student.name}' tiene ${subjects.size} materias")
                    StudentWithSubjects(student, subjects)
                }
            }
            deferredResults.awaitAll()
        }

        studentsWithSubjects
    }
}
