package cz.stasimek.fakturaceeasypeasy.controller;

import cz.stasimek.fakturaceeasypeasy.controller.access.Access;
import cz.stasimek.fakturaceeasypeasy.entity.Invoice;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.service.InvoiceService;
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
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/invoices/issued/{year}")
	public List<Invoice> getIssuedInvoices(
			@PathVariable int year,
			@AuthenticationPrincipal OAuth2User principal
	) {
		User user = Access.getUser(principal);
		return IterableUtils.toList(invoiceService.findIssued(user, year));
	}

	@GetMapping("/invoices/received/{year}")
	public List<Invoice> getReceivedInvoices(
			@PathVariable int year,
			@AuthenticationPrincipal OAuth2User principal
	) {
		User user = Access.getUser(principal);
		return IterableUtils.toList(invoiceService.findReceived(user, year));
	}

	@GetMapping("/invoices/generate-new-number")
	public String generateNewInvoiceNumber(@AuthenticationPrincipal OAuth2User principal) {
		return invoiceService.generateNewInvoiceNumber(Access.getUser(principal));
	}

	@GetMapping("/invoice/{id}")
	public Invoice getInvoice(@PathVariable UUID id) {
		return invoiceService.findById(id);
	}

	@PostMapping("/invoice")
	public ResponseEntity createInvoice(@RequestBody Invoice invoice) throws URISyntaxException {
		Invoice newInvoice = invoiceService.create(invoice);
		return ResponseEntity.created(new URI("/invoice/" + newInvoice.getId())).body(newInvoice);
	}

	@PutMapping("/invoice/{id}")
	public ResponseEntity updateInvoice(@PathVariable UUID id, @RequestBody Invoice invoice) {
		Invoice updatedInvoice = invoiceService.update(invoice);
		return ResponseEntity.ok(updatedInvoice);
	}

	@DeleteMapping("/invoice/{id}")
	public ResponseEntity deleteInvoice(@PathVariable UUID id) {
		invoiceService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
