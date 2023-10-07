package tv.codelong.thenewboston.dto

data class AddUserDto (
    val id: Long,
    val name: String,
    val userName: String,
    val userSurname: String,
    val userMail: String,
    val userPhone: String,
)