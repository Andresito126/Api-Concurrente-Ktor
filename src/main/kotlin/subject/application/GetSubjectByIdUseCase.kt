package subject.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import subject.application.repositories.ISubjectRepository
import subject.domain.Subject

class GetSubjectByIdUseCase(private val repository: ISubjectRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)
    suspend fun execute(id: String): Subject? = withContext(Dispatchers.IO) {
        logger.info("buscando materia id: $id en hilo: ${Thread.currentThread().name}")
        repository.findById(id)
    }
}
