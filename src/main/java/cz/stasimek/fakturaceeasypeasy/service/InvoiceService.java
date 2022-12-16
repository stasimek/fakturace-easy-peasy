package cz.stasimek.fakturaceeasypeasy.service;

import cz.stasimek.fakturaceeasypeasy.entity.Invoice;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.repository.InvoiceItemRepository;
import cz.stasimek.fakturaceeasypeasy.repository.InvoiceRepository;
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService;
import cz.stasimek.fakturaceeasypeasy.util.InvoiceNumberGenerator;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService implements AppService<Invoice> {

	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;

	@Transactional
	public Invoice create(Invoice invoice) {
		Invoice createdInvoice = invoiceRepository.save(invoice);
		invoice.getItems().stream().forEach(
				item -> {
					item.setInvoice(createdInvoice);
					invoiceItemRepository.save(item);
				}
		);
		return createdInvoice;
	}

	@Transactional
	public Invoice update(Invoice invoice) {
		// Delete missing invoice items.
		Invoice originalInvoice = findById(invoice.getId());
		originalInvoice.getItems().stream().forEach(
				item -> {
					if (!invoice.getItems().contains(item)) {
						invoiceItemRepository.delete(item);
					}
				}
		);
		// Update invoice and items.
		Invoice updatedInvoice = invoiceRepository.save(invoice);
		invoice.getItems().stream().forEach(
				item -> {
					item.setInvoice(updatedInvoice);
					invoiceItemRepository.save(item);
				}
		);
		return updatedInvoice;
	}

	public void deleteById(UUID id) {
		invoiceRepository.deleteById(id);
	}

	@Override
	public Invoice findById(UUID id) {
		return invoiceRepository.findById(id).orElseThrow();
	}

	public Invoice findLastIssued(User user) {
		List<Invoice> lastInvoice = invoiceRepository.findLastIssuedByUser(
				user, PageRequest.ofSize(1)
		);
		if (!lastInvoice.isEmpty()) {
			return lastInvoice.get(0);
		}
		return null;
	}

	public Iterable<Invoice> findIssued(User user, int year) {
		return invoiceRepository.findIssuedByUserAndYear(user, year);
	}

	public Iterable<Invoice> findReceived(User user, int year) {
		return invoiceRepository.findReceivedByUserAndYear(user, year);
	}

	public String generateNewInvoiceNumber(User user) {
		Invoice lastInvoice = findLastIssued(user);
		int currentYear = Year.now().getValue();
		return InvoiceNumberGenerator.generate(
				lastInvoice == null ? null : lastInvoice.getNumber(),
				currentYear
		);
	}

}
