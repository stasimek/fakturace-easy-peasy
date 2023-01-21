package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.controller.access.Access
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/user")
    fun user(
        @AuthenticationPrincipal principal: OAuth2User?
    ): User? {
        if (principal == null) {
            // TODO: load anonymous
            return null
        } else {
            return userService.findById(Access.getUser(principal).id!!)
        }
    }

    @PutMapping("/user/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @RequestBody user: User,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        Access.check(id, principal, "update", userService)
        user.id = id
        return ResponseEntity.ok(userService.update(user))
    }
}