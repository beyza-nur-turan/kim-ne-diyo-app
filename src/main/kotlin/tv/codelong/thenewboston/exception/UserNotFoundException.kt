package tv.codelong.thenewboston.exception

class UserNotFoundException (
    val resultCode: ResultCode,
    override val message: String = "User not found!"
) : Throwable(message)
