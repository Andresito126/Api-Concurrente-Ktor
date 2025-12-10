package subject.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import subject.application.repositories.ISubjectRepository
import subject.domain.Subject

class GetAllSubjectsUseCase(private val repository: ISubjectRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    suspend fun execute(): List<Subject> = withContext(Dispatchers.IO) {

        repository.findAll()
    }
}
