package cz.stasimek.fakturaceeasypeasy.controller;

import cz.stasimek.fakturaceeasypeasy.controller.access.Access;
import cz.stasimek.fakturaceeasypeasy.entity.Subject;
import cz.stasimek.fakturaceeasypeasy.service.SubjectService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping("/subjects")
	public List<Subject> getSubjects(@AuthenticationPrincipal OAuth2User principal) {
		return IterableUtils.toList(subjectService.findAll(Access.getUser(principal)));
	}

	@GetMapping("/subjects/customers")
	public List<Subject> getCustomers(@AuthenticationPrincipal OAuth2User principal) {
		return IterableUtils.toList(subjectService.findAllCustomers(Access.getUser(principal)));
	}

	@GetMapping("/subjects/suppliers")
	public List<Subject> getSuppliers(@AuthenticationPrincipal OAuth2User principal) {
		return IterableUtils.toList(subjectService.findAllSuppliers(Access.getUser(principal)));
	}

	@GetMapping("/subject/{id}")
	public Subject getSubject(
			@PathVariable UUID id, @AuthenticationPrincipal OAuth2User principal
	) {
		Access.check(id, principal, "get", subjectService);
		return subjectService.findById(id);
	}

	@PostMapping("/subject")
	public ResponseEntity createSubject(
			@RequestBody Subject subject, @AuthenticationPrincipal OAuth2User principal
	) throws URISyntaxException {
		subject.setUser(Access.getUser(principal));
		Subject newSubject = subjectService.create(subject);
		return ResponseEntity
				.created(new URI("/subject/" + newSubject.getId()))
				.body(newSubject);
	}

	@PutMapping("/subject/{id}")
	public ResponseEntity updateSubject(
			@PathVariable UUID id,
			@RequestBody Subject subject,
			@AuthenticationPrincipal OAuth2User principal
	) {
		Access.check(id, principal, "update", subjectService);
		subject.setId(id);
		return ResponseEntity.ok(subjectService.update(subject));
	}

	@DeleteMapping("/subject/{id}")
	public ResponseEntity deleteSubject(@
			PathVariable UUID id, @AuthenticationPrincipal OAuth2User principal
	) {
		Access.check(id, principal, "delete", subjectService);
		subjectService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
