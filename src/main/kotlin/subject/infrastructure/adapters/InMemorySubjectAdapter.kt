package subject.infrastructure.adapters

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import subject.application.repositories.ISubjectRepository
import subject.domain.Subject

class InMemorySubjectAdapter : ISubjectRepository {
    
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    private val storage = mutableMapOf(
        "1" to Subject("1", "Programación Concurrente", "PC-401", 5, "Ali López"),
        "2" to Subject("2", "Lenguajes y Autómatas", "LA-402", 4, "Alejandro Domínguez"),
        "3" to Subject("3", "Redes", "AS-403", 4, "Alonso"),
        "4" to Subject("4", "Inglés ", "IN-407", 3, "Ulber")
    )
    
    override suspend fun findAll(): List<Subject> {
        logger.debug("findAll() en hilo: ${Thread.currentThread().name}")
        delay(150)
        return storage.values.toList()
    }
    
    override suspend fun findById(id: String): Subject? {
        logger.debug("findById($id) en hilo: ${Thread.currentThread().name}")
        delay(100)
        return storage[id]
    }
    
    override suspend fun save(subject: Subject): Subject {
        logger.debug(" save(${subject.name}) en hilo: ${Thread.currentThread().name}")
        delay(200)
        storage[subject.id] = subject
        return subject
    }
    
    override suspend fun existsById(id: String): Boolean {
        logger.debug("ya existsById($id) en hilo: ${Thread.currentThread().name}")
        delay(50)
        return storage.containsKey(id)
    }
}
