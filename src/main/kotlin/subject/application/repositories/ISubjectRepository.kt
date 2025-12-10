package subject.application.repositories

import subject.domain.Subject

interface ISubjectRepository {
    suspend fun findAll(): List<Subject>
    suspend fun findById(id: String): Subject?
    suspend fun save(subject: Subject): Subject
    suspend fun existsById(id: String): Boolean
}
