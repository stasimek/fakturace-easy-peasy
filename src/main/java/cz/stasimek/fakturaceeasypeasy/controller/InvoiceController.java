package cz.stasimek.fakturaceeasypeasy.controller;

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

	public InvoiceController(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@GetMapping("/invoices")
	public List<Invoice> getInvoices() {
		return IterableUtils.toList(invoiceRepository.findAll());
	}

	@GetMapping("/invoices/generate-new-number")
	public String generateNewInvoiceNumber(@AuthenticationPrincipal OAuth2User principal) {
		return invoiceService.generateNewInvoiceNumber(Access.getUser(principal));
	}

	@GetMapping("/invoice/{id}")
	public Invoice getInvoice(@PathVariable UUID id) {
		return invoiceRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping("/invoice")
	public ResponseEntity createInvoice(@RequestBody Invoice invoice) throws URISyntaxException {
		Invoice savedInvoice = invoiceRepository.save(invoice);
		return ResponseEntity.created(new URI("/invoice/" + savedInvoice.getId())).body(savedInvoice);
	}

	@PutMapping("/invoice/{id}")
	public ResponseEntity updateInvoice(@PathVariable UUID id, @RequestBody Invoice invoice) {
		Invoice currentInvoice = invoiceRepository.findById(id).orElseThrow(RuntimeException::new);
		currentInvoice.setNumber(invoice.getNumber());
		currentInvoice = invoiceRepository.save(invoice);

		return ResponseEntity.ok(currentInvoice);
	}

	@DeleteMapping("/invoice/{id}")
	public ResponseEntity deleteInvoice(@PathVariable UUID id) {
		invoiceRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
