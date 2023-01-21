package cz.stasimek.fakturaceeasypeasy.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.SQLDelete
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE APP.invoice_item SET deleted = true WHERE id = ?")
@Validated
open class InvoiceItem : BaseEntity() {

    @JsonBackReference
    @ManyToOne(optional = false)
    @NotNull
    open var invoice: Invoice? = null

    @Column
    open var quantity: BigDecimal? = null

    @Column(length = 20)
    @Size(max = 20)
    open var unit: String? = null

    @Column(length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    open var description: String? = null

    @Column(nullable = false)
    @NotNull
    open var unitPrice: BigDecimal? = null

    // Sazba DPH (%)
    @Column(nullable = false)
    @NotNull
    open var vatRate: BigDecimal? = null
}