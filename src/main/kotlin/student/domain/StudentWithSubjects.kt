package student.domain

import kotlinx.serialization.Serializable
import subject.domain.Subject

@Serializable
data class StudentWithSubjects(
    val student: Student,
    val subjects: List<Subject>
)
