import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String,
    val timestamp: Long = System.currentTimeMillis()
)