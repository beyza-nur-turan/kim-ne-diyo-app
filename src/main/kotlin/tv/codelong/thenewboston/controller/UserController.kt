package tv.codelong.thenewboston.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.service.TokenService
import tv.codelong.thenewboston.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(
        private val userService: UserService,
        private val tokenService: TokenService
    ) {

    @GetMapping("/get-by-id/{id}")
    fun getUserById(@PathVariable id: Long): ResponseDto<UserDto> {
        return userService.findById(id)?.let {ResponseDto(
            resultCode = ResultCode.Success,
            data = it
        )} ?: ResponseDto(
            resultCode = ResultCode.NotFound,
            message = "User not found with id: $id"
        )
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updateDto: UserDto): ResponseDto<Nothing> {
        val message = userService.updateUser(id, updateDto)
        message?.let {
            return ResponseDto(resultCode = ResultCode.Forbidden, message = it)
        } ?: return ResponseDto(resultCode = ResultCode.Success)
    }

    @DeleteMapping("/delete-user/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseDto<UserDto>? {
        return userService.deleteUser(id)?.let { ResponseDto(
            resultCode = ResultCode.NotFound,
            message = "User not found."
        )} ?: ResponseDto(
            resultCode = ResultCode.Success,
            message = "User deleted with id: $id"
        )
    }

    @GetMapping("/get-all")
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        val users = userService.getAllUsers().map { it.toDto() }
        return ResponseEntity(users, HttpStatus.OK)
    }
}


//    @GetMapping("/user/{id}")
//    fun user(@PathVariable id: Long): UserDto {
//        val user = userService.findById(id) ?: throw UserNotFoundException()
//        return user.toDto()
//    }
//
//    @GetMapping("/username")
//    fun user(): String {
//        val loggedInUsername = getLoggedInUsername()
//        if (loggedInUsername != null) {
//            val user = userService.findByName(loggedInUsername) ?: throw UserNotFoundException()
//            return user.name
//        }
//        throw ApiException(401, "User not authenticated")
//    }
//
//    private fun getLoggedInUsername(): String? {
//        val authentication = SecurityContextHolder.getContext().authentication
//        if (authentication != null && authentication.isAuthenticated) {
//            return (authentication.principal as? User)?.name
//        }
//        return null
//    }


