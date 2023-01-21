package cz.stasimek.fakturaceeasypeasy.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api")
class LoginController {

    @GetMapping("/login/user")
    fun user(
        @AuthenticationPrincipal principal: OAuth2User?
    ): Map<String, Any> {
        val map: MutableMap<String, Any> = HashMap()
        if (principal != null) {
            val name = principal.getAttribute<Any?>("name")
            val login = principal.getAttribute<Any?>("login")
            val email = principal.getAttribute<Any?>("email")
            if (name != null) {
                map["name"] = name
            } else if (login != null) {
                map["name"] = login
            } else if (email != null) {
                map["name"] = email
            }
            //map["principal"] = principal;
        }
        return map
    }

    @GetMapping("/login/error")
    fun error(request: HttpServletRequest): String? {
        val message = request.session.getAttribute("login.errorMessage")
        request.session.removeAttribute("login.errorMessage")
        return message as String
    }
}