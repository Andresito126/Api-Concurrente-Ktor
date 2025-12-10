package subject.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import subject.application.repositories.ISubjectRepository
import subject.domain.Subject

class CreateSubjectUseCase(private val repository: ISubjectRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(subject: Subject): Subject = withContext(Dispatchers.IO) {

        logger.info("creando: ${subject.name} en hilo: ${Thread.currentThread().name}")
        
        if (repository.existsById(subject.id)) {
            throw IllegalArgumentException("ya existe una materia con id: ${subject.id}")
        }
        repository.save(subject)
    }
}
