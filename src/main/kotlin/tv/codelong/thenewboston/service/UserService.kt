package tv.codelong.thenewboston.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.RegisterDto
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.dto.UserDto
import tv.codelong.thenewboston.dto.toDto
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.repository.UserRepo

@Service
class UserService(
    private val userRepo: UserRepo,
    private val hashService: HashService,
    private val securityService: SecurityService,
) {
    fun findById(id: Long): UserDto? {
        return userRepo.findByIdOrNull(id)?.toDto()
    }

    fun updateUser(id: Long, dto: UserDto): String? {
        val user = securityService.getLoggedInUser()
        if (user?.id == id) {
            // val existingUser = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException("User not found with id: $id")
            val updatedUser = user.copy(
                name = dto.name,
                userSurname = dto.userSurname,
                userMail = dto.userMail,
                userPhone = dto.userPhone,
            )
            return null
        } else {
            return "Permission Denied"
        }
    }

    fun deleteUser(id: Long): UserDto?{
        val existingUser = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException(ResultCode.NotFound)
        userRepo.deleteById(existingUser.id)
        return null
    }

    fun getAllUsers(): List<User> {
        return userRepo.findAll().toList()
    }

    fun findByName(name: String): User? {
        return userRepo.findByName(name)
    }

    fun existsByName(name: String): Boolean {
        return userRepo.existsByName(name)
    }

    fun existsByMail(userMail: String): Boolean{
        return userRepo.existsByUserMail(userMail)
    }
    fun existsByPhone(userPhone: String): Boolean{
        return userRepo.existsByUserPhone(userPhone)
    }

    fun updatePassword(id: Long, newPassword: String) {
        val user = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException(ResultCode.NotFound)
        user.password = hashService.hashBcrypt(newPassword)
        userRepo.save(user)
    }
}
