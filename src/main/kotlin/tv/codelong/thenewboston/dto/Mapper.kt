package tv.codelong.thenewboston.dto

import tv.codelong.thenewboston.model.Item
import tv.codelong.thenewboston.model.User

fun Item.toDto(): ItemDto {
    return ItemDto(id, name, count, note)
}

fun User.toDto(): UserDto {
    return UserDto(id, name, userName, userSurname, userMail, userPhone)
}

fun RegisterDto.toUser(): User {
    return User(name = name, userName = userName, userSurname = userSurname, userMail = userMail, userPhone = userPhone, password = password)
}
