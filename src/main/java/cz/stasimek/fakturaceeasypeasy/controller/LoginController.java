package cz.stasimek.fakturaceeasypeasy.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		Map<String, Object> map = new HashMap<>();
		if (principal != null) {
			if (principal.getAttribute("name") != null) {
				map.put("name", principal.getAttribute("name"));
			} else if (principal.getAttribute("login") != null) {
				map.put("name", principal.getAttribute("login"));
			} else if (principal.getAttribute("email") != null) {
				map.put("name", principal.getAttribute("email"));
			}
			//map.put("principal", principal);
		}
		return map;
	}

	@GetMapping("/error")
	public String error(HttpServletRequest request) {
		String message = (String) request.getSession().getAttribute("login.errorMessage");
		request.getSession().removeAttribute("login.errorMessage");
		return message;
	}

}
