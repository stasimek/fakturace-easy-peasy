package cz.stasimek.fakturaceeasypeasy.service;

import cz.stasimek.fakturaceeasypeasy.entity.Invoice;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.repository.InvoiceRepository;
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService;
import cz.stasimek.fakturaceeasypeasy.util.InvoiceNumberGenerator;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements AppService<Invoice> {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public Invoice create(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	public Invoice update(Invoice invoice) {
		return invoiceRepository.save(invoice);
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
