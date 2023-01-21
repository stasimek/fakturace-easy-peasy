package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.controller.access.Access
import cz.stasimek.fakturaceeasypeasy.entity.Invoice
import cz.stasimek.fakturaceeasypeasy.service.InvoiceService
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
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @GetMapping("/invoices/issued/{year}")
    fun getIssuedInvoices(
        @PathVariable year: Int,
        @AuthenticationPrincipal principal: OAuth2User
    ): List<Invoice> {
        val user = Access.getUser(principal)
        return IterableUtils.toList(invoiceService.findIssued(user, year))
    }

    @GetMapping("/invoices/received/{year}")
    fun getReceivedInvoices(
        @PathVariable year: Int,
        @AuthenticationPrincipal principal: OAuth2User
    ): List<Invoice> {
        val user = Access.getUser(principal)
        return IterableUtils.toList(invoiceService.findReceived(user, year))
    }

    @GetMapping("/invoices/generate-new-number")
    fun generateNewInvoiceNumber(
        @AuthenticationPrincipal principal: OAuth2User
    ): String {
        return invoiceService.generateNewInvoiceNumber(Access.getUser(principal))
    }

    @GetMapping("/invoice/{id}")
    fun getInvoice(
        @PathVariable id: UUID,
        @AuthenticationPrincipal principal: OAuth2User
    ): Invoice {
        Access.check(id, principal, "get", invoiceService)
        return invoiceService.findById(id)
    }

    @PostMapping("/invoice")
    @Throws(URISyntaxException::class)
    fun createInvoice(
        @RequestBody invoice: Invoice,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        invoice.user = Access.getUser(principal)
        val newInvoice = invoiceService.create(invoice)
        return ResponseEntity
            .created(URI("/invoice/" + newInvoice.id))
            .body(newInvoice)
    }

    @PutMapping("/invoice/{id}")
    fun updateInvoice(
        @PathVariable id: UUID,
        @RequestBody invoice: Invoice,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        Access.check(id, principal, "update", invoiceService)
        invoice.id = id
        return ResponseEntity.ok(invoiceService.update(invoice))
    }

    @DeleteMapping("/invoice/{id}")
    fun deleteInvoice(
        @PathVariable id: UUID,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<*> {
        Access.check(id, principal, "delete", invoiceService)
        invoiceService.deleteById(id)
        return ResponseEntity.noContent().build<Any>()
    }
}