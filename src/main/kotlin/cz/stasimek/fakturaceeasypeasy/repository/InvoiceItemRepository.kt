package cz.stasimek.fakturaceeasypeasy.repository

import cz.stasimek.fakturaceeasypeasy.entity.InvoiceItem
import org.springframework.data.repository.CrudRepository
import java.util.*

interface InvoiceItemRepository : CrudRepository<InvoiceItem, UUID>