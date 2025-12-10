package student.domain

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: String,
    val name: String,
    val enrollment: String,
    val semester: Int,
    val career: String,
    val subjectIds: List<String>
)
