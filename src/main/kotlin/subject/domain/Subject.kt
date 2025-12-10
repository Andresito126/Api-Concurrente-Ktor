    package subject.domain

    import kotlinx.serialization.Serializable

    @Serializable
    data class Subject(
        val id: String,
        val name: String,
        val code: String,
        val credits: Int,
        val professor: String
    )
