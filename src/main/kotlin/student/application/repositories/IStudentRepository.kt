package student.application.repositories

import student.domain.Student

interface IStudentRepository {
    suspend fun findAll(): List<Student>
    suspend fun findById(id: String): Student?
    suspend fun save(student: Student): Student
    suspend fun existsById(id: String): Boolean
}
