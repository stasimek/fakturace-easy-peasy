package cz.stasimek.fakturaceeasypeasy.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.HasUser
import cz.stasimek.fakturaceeasypeasy.enumeration.*
import cz.stasimek.fakturaceeasypeasy.enumeration.Currency
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE APP.invoice SET deleted = true WHERE id = ?")
@Validated
open class Invoice : BaseEntity(), HasUser {

    @ManyToOne(optional = false)
    @NotNull
    open override var user: User? = null

    // Číslo faktury
    @Column(length = 20, nullable = false)
    @NotNull
    @Size(max = 20)
    open var number: String? = null

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    open var type: InvoiceType? = null

    @ManyToOne(optional = false)
    @NotNull
    open var subject: Subject? = null

    // Datum vystavení
    @Column(nullable = false)
    @NotNull
    open var issueDate: LocalDate? = null

    // Datum znanitelného plnění
    @Column
    open var taxableSupplyDate: LocalDate? = null

    // Datum splatnosti
    @Column(nullable = false)
    @NotNull
    open var dueDate: LocalDate? = null

    // Datum přijetí - if InvoiceType = RECEIVED
    @Column
    open var receiveDate: LocalDate? = null

    // Variabilní symbol
    @Column
    open var variableSymbol: Long? = null

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    open var currency: Currency? = null

    @Column
    open var exchangeRate: BigDecimal? = null

    // Číslo objednávky
    @Column(length = 20)
    @Size(max = 20)
    open var orderNumber: String? = null

    @Column(length = 255)
    @Size(max = 255)
    open var description: String? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", targetEntity = InvoiceItem::class)
    @Where(clause = "deleted = false")
    open val items: List<InvoiceItem> = ArrayList<InvoiceItem>()
}