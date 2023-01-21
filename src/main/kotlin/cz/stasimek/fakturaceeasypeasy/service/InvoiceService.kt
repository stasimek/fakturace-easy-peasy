package cz.stasimek.fakturaceeasypeasy.service

import cz.stasimek.fakturaceeasypeasy.entity.Invoice
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.repository.InvoiceItemRepository
import cz.stasimek.fakturaceeasypeasy.repository.InvoiceRepository
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService
import cz.stasimek.fakturaceeasypeasy.util.InvoiceNumberGenerator
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Year
import java.util.*

@Service
class InvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val invoiceItemRepository: InvoiceItemRepository
) : AppService<Invoice> {

    @Transactional
    fun create(invoice: Invoice): Invoice {
        val createdInvoice = invoiceRepository.save(invoice)
        invoice.items.forEach {
            it.invoice = createdInvoice
            invoiceItemRepository.save(it)
        }
        return createdInvoice
    }

    @Transactional
    fun update(invoice: Invoice): Invoice {
        // Delete missing invoice items.
        val originalInvoice = findById(invoice.id!!)
        originalInvoice.items.forEach {
            if (!invoice.items.contains(it)) {
                invoiceItemRepository.delete(it)
            }
        }
        // Update invoice and items.
        val updatedInvoice = invoiceRepository.save(invoice)
        invoice.items.forEach {
            it.invoice = updatedInvoice
            invoiceItemRepository.save(it)
        }
        return updatedInvoice
    }

    fun deleteById(id: UUID) {
        invoiceRepository.deleteById(id)
    }

    override fun findById(id: UUID): Invoice {
        return invoiceRepository.findById(id).orElseThrow()
    }

    fun findLastIssued(user: User): Invoice? {
        val lastInvoice = invoiceRepository.findLastIssuedByUser(
            user, PageRequest.ofSize(1)
        )
        if (lastInvoice.isNotEmpty()) {
            return lastInvoice[0]
        }
        return null
    }

    fun findIssued(user: User, year: Int): Iterable<Invoice> {
        return invoiceRepository.findIssuedByUserAndYear(user, year)
    }

    fun findReceived(user: User, year: Int): Iterable<Invoice> {
        return invoiceRepository.findReceivedByUserAndYear(user, year)
    }

    fun generateNewInvoiceNumber(user: User): String {
        val lastInvoice = findLastIssued(user)
        val currentYear = Year.now().value
        return InvoiceNumberGenerator.generate(
            lastInvoice?.number,
            currentYear
        )
    }
}