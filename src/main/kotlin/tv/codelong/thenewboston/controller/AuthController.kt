package tv.codelong.thenewboston.controller


import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.service.AuthService
import tv.codelong.thenewboston.service.HashService
import tv.codelong.thenewboston.service.TokenService
import tv.codelong.thenewboston.service.UserService

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:3000"])
class AuthController(
        private val hashService: HashService,
        private val tokenService: TokenService,
        private val userService: UserService,
        private val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByName(payload.name) ?: throw ApiException(ResultCode.ValidationError, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(ResultCode.ValidationError, "Login failed")
        }

        return LoginResponseDto(
                token = tokenService.createToken(user),
        )
    }
    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): ResponseDto<RegisterDto> {
        return authService.addUser(payload)
    }

}
