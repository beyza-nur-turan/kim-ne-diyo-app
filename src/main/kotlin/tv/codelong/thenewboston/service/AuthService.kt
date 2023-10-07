package tv.codelong.thenewboston.service

import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.RegisterDto
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.dto.toUser
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.repository.UserRepo

@Service
class AuthService (
    private val userService: UserService,
    private val tokenService: TokenService,
    private val userRepo: UserRepo,
    private val hashService: HashService,
){
    fun addUser(registerDto: RegisterDto): ResponseDto<RegisterDto> {
        val user = registerDto.toUser()
        return if (
            userService.existsByName(registerDto.name.trim()) ||
            userService.existsByPhone(registerDto.userPhone.trim()) ||
            userService.existsByMail(registerDto.userMail.trim()) ||
            registerDto.password != registerDto.confirmPassword
            ){
            ResponseDto(resultCode = ResultCode.ValidationError, message = "Registration failed!")
        }else{
            user.password = hashService.hashBcrypt(user.password)
            val savedUser = userRepo.save(user)
            ResponseDto(resultCode = ResultCode.Success, data = RegisterDto(
                id = savedUser.id,
                name = savedUser.name,
                userName = savedUser.userName,
                userSurname = savedUser.userSurname,
                userMail = savedUser.userMail,
                userPhone = savedUser.userPhone,
                password = savedUser.password,
            ))
        }
    }
}
