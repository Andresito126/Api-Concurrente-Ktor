package student.infrastructure.adapters

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import student.application.repositories.IStudentRepository
import student.domain.Student

class InMemoryStudentAdapter : IStudentRepository {
    
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    private val storage = mutableMapOf(
        "101" to Student("101", "Alexis ", "213456", 7, "Ingeniería en Software", listOf("1", "2", "3", "4")),
        "102" to Student("102", "André ", "213457", 7, "Ingeniería en Software", listOf("1", "2", "4")),
        "103" to Student("103", "Poyoyon", "213458", 7, "Ingeniería en Software", listOf("1", "3", "4")),
        "104" to Student("104", "Karlita", "213459", 5, "Ingeniería en Software", listOf("2", "4"))
    )
    
    override suspend fun findAll(): List<Student> {
        logger.debug("student en findAll() en hilo: ${Thread.currentThread().name}")
        delay(120)
        return storage.values.toList()
    }
    
    override suspend fun findById(id: String): Student? {
        logger.debug("findById($id) en hilo: ${Thread.currentThread().name}")
        delay(80)
        return storage[id]
    }
    
    override suspend fun save(student: Student): Student {
        logger.debug("save(${student.name}) en hilo: ${Thread.currentThread().name}")
        delay(180)
        storage[student.id] = student
        return student
    }
    
    override suspend fun existsById(id: String): Boolean {
        logger.debug("existsById($id) en hilo: ${Thread.currentThread().name}")
        delay(40)
        return storage.containsKey(id)
    }
}
