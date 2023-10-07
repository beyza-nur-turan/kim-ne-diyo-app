package tv.codelong.thenewboston.exception

enum class ResultCode {
    Success, //200
    ValidationError, //400
    NotFound, //404
    Forbidden, //403
    Conflict //409
}
