package tv.codelong.thenewboston.exception

class ApiException (
    val resultCode: ResultCode,
    override val message: String
) : Throwable(message)
