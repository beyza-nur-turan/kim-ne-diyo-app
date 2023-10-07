package tv.codelong.thenewboston.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tv.codelong.thenewboston.dto.ResponseDto

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<ResponseDto<UserNotFoundException>> {
        val responseDto = ResponseDto(resultCode = ex.resultCode, message = ex.message, data = ex)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto)
    }

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseEntity<ResponseDto<ApiException>> {
        val responseDto = ResponseDto(resultCode = ex.resultCode, message = ex.message, data = ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto)
    }

}

