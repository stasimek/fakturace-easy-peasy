package cz.stasimek.fakturaceeasypeasy.entity

import cz.stasimek.fakturaceeasypeasy.enumeration.*
import org.hibernate.annotations.SQLDelete
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "`user`")
@SQLDelete(sql = "UPDATE APP.\"user\" SET deleted = true WHERE id = ?")
@Validated
open class User : BaseEntity() {

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    open var provider: Provider? = null

    @Column(length = 255, nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 255)
    open var login: String? = null

    @Column(length = 255)
    @Size(max = 255)
    open var email: String? = null

    @Column(length = 255)
    @Size(max = 255)
    open var name: String? = null

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    open var legalForm: SubjectLegalForm? = null

    @Column(length = 255)
    @Size(max = 255)
    open var companyName: String? = null

    @Column(length = 100)
    @Size(max = 100)
    open var street: String? = null

    @Column(length = 100)
    @Size(max = 100)
    open var city: String? = null

    // PSČ
    @Column(length = 10)
    @Size(max = 10)
    open var zip: String? = null

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    open var country: Country? = null

    // IČO
    @Column(length = 20)
    @Size(max = 20)
    open var companyNumber: String? = null

    // DIČ
    @Column(length = 20)
    @Size(max = 20)
    open var vatNumber: String? = null

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    open var vatType: VatType? = null

    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    open var vatPeriod: VatPeriod? = null

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    open var currency = Currency.CZK

    // Finanční úřad - územní pracoviště
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    open var taxOffice: TaxOffice? = null

    // E.g.: "Podnikatel zapsán v živ. rejstříku Městská část Praha 11"
    @Column(length = 255)
    @Size(max = 255)
    open var note: String? = null

    @Column(length = 20)
    @Size(max = 20)
    open var phone: String? = null

    // Hlavní činnost
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    open var czNace: CzNace? = null

    @Column(length = 30)
    @Size(max = 30)
    open var bankName: String? = null

    @Column(length = 30)
    @Size(max = 30)
    open var bankAcountNumber: String? = null

    @Column(length = 34)
    @Size(max = 34)
    open var iban: String? = null

    // BIC (SWIFT)
    @Column(length = 11)
    @Size(max = 11)
    open var bic: String? = null

    // Výchozí doba splatnosti faktury
    @Column
    open var defaultDuePeriod = 14

    // Výchozí jednotka
    @Column(length = 50)
    @Size(max = 50)
    open var defaultUnit: String? = null

    // Výchozí jednotková cena
    @Column
    open var defaultUnitPrice: BigDecimal? = null
}