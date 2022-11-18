package cz.stasimek.fakturaceeasypeasy.repository;

import cz.stasimek.fakturaceeasypeasy.entity.Invoice;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, UUID> {

}
