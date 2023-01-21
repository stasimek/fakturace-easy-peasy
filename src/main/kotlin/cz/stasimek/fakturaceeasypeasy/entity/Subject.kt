package cz.stasimek.fakturaceeasypeasy.entity

import cz.stasimek.fakturaceeasypeasy.entity.interfaces.HasUser
import cz.stasimek.fakturaceeasypeasy.enumeration.*
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.SQLDelete
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@SQLDelete(sql = "UPDATE APP.subject SET deleted = true WHERE id = ?")
@Getter
@Setter
@Validated
open class Subject : BaseEntity(), HasUser {

    @ManyToOne(optional = false)
    @NotNull
    open override var user: User? = null

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    open var type: SubjectType? = null

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    open var legalForm: SubjectLegalForm? = null

    @Column(length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    open var companyName: String? = null

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
    open var street: String? = null

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
    open var city: String? = null

    // PSČ
    @Column(length = 10, nullable = false)
    @NotNull
    @Size(max = 10)
    open var zip: String? = null

    @Column(length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    open var country: Country? = null

    // IČO
    @Column(length = 20)
    @Size(max = 20)
    open var companyNumber: String? = null

    // DIČ
    @Column(length = 20)
    @Size(max = 20)
    open var vatNumber: String? = null

    // Výchozí doba splatnosti faktury pro daný subjekt
    @Column
    open var defaultDuePeriod: Int? = null

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    open var defaultCurrency: Currency? = null

    // Výchozí jednotková cena
    @Column
    open var defaultUnitPrice: BigDecimal? = null
}