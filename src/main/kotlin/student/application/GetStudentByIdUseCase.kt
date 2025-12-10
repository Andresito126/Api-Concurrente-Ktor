package student.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import student.application.repositories.IStudentRepository
import student.domain.Student

class GetStudentByIdUseCase(private val repository: IStudentRepository) {
    
    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(id: String): Student? = withContext(Dispatchers.IO) {
        logger.info("$id hilo: ${Thread.currentThread().name}")
        repository.findById(id)
    }
}
