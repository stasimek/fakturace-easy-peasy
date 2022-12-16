package cz.stasimek.fakturaceeasypeasy.repository;

import cz.stasimek.fakturaceeasypeasy.entity.InvoiceItem;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, UUID> {

}
