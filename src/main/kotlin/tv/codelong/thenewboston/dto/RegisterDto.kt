package tv.codelong.thenewboston.dto

data class RegisterDto (
    val id: Long,
    val name: String,
    val userName: String,
    val userSurname: String,
    val userMail: String,
    val userPhone: String,
    val password: String,
    val confirmPassword: String? = null
)