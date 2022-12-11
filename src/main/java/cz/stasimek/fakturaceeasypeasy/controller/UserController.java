package cz.stasimek.fakturaceeasypeasy.controller;

import cz.stasimek.fakturaceeasypeasy.config.user.AppUser;
import cz.stasimek.fakturaceeasypeasy.controller.access.Access;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public User user(@AuthenticationPrincipal OAuth2User principal) {
		if (principal == null) {
			// TODO: load anonymous
			return null;
		}
		return userService.findById(Access.getUser(principal).getId());
	}

	@PutMapping("/user/{id}")
	public ResponseEntity updateUser(
			@PathVariable UUID id,
			@RequestBody User user,
			@AuthenticationPrincipal OAuth2User principal
	) {
		Access.check(id, principal, "update", userService);
		user.setId(id);
		return ResponseEntity.ok(userService.update(user));
	}

}
