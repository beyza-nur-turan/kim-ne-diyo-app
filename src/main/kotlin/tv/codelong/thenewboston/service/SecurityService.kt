package tv.codelong.thenewboston.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.model.User

@Service
class SecurityService {
    fun getLoggedInUser(): User? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            return authentication.principal as? User
        }
        return null
    }
}