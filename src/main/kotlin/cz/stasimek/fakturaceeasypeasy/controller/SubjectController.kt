package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.controller.access.Access
import cz.stasimek.fakturaceeasypeasy.entity.Subject
import cz.stasimek.fakturaceeasypeasy.service.SubjectService
import org.apache.commons.collections4.IterableUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException
import java.util.*

@RestController
@RequestMapping("/api")
class SubjectController(
    private val subjectService: SubjectService
) {

    @GetMapping("/subjects")
    fun getSubjects(
        @AuthenticationPrincipal principal: OAuth2User
    ): List<Subject> {
        return IterableUtils.toList(subjectService.findAll(Access.getUser(principal)))
    }

    @GetMapping("/subjects/customers")
    fun getCustomers(
        @AuthenticationPrincipal principal: OAuth2User
    ): List<Subject> {
        return IterableUtils.toList(subjectService.findAllCustomers(Access.getUser(principal)))
    }

    @GetMapping("/subjects/suppliers")
    fun getSuppliers(
        @AuthenticationPrincipal principal: OAuth2User
    ): List<Subject> {
        return IterableUtils.toList(subjectService.findAllSuppliers(Access.getUser(principal)))
    }

    @GetMapping("/subject/{id}")
    fun getSubject(
        @PathVariable id: UUID,
        @AuthenticationPrincipal principal: OAuth2User
    ): Subject {
        Access.check(id, principal, "get", subjectService)
        return subjectService.findById(id)
    }

    @PostMapping("/subject")
    @Throws(URISyntaxException::class)
    fun createSubject(
        @RequestBody subject: Subject,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        subject.user = Access.getUser(principal)
        val newSubject = subjectService.create(subject)
        return ResponseEntity
            .created(URI("/subject/" + newSubject.id))
            .body(newSubject)
    }

    @PutMapping("/subject/{id}")
    fun updateSubject(
        @PathVariable id: UUID,
        @RequestBody subject: Subject,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        Access.check(id, principal, "update", subjectService)
        subject.id = id
        return ResponseEntity.ok(subjectService.update(subject))
    }

    @DeleteMapping("/subject/{id}")
    fun deleteSubject(
        @PathVariable id: UUID,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        Access.check(id, principal, "delete", subjectService)
        subjectService.deleteById(id)
        return ResponseEntity.noContent().build<Any>()
    }
}