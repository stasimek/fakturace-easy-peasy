package cz.stasimek.fakturaceeasypeasy.service

import cz.stasimek.fakturaceeasypeasy.entity.Subject
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.repository.SubjectRepository
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService
import org.springframework.stereotype.Service
import java.util.*
import javax.validation.constraints.NotNull

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository
) : AppService<Subject> {

    fun create(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    fun update(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    fun deleteById(id: UUID) {
        subjectRepository.deleteById(id)
    }

    override fun findById(id: UUID): Subject {
        return subjectRepository.findById(id).orElseThrow()
    }

    fun findAll(user: User): Iterable<Subject> {
        return subjectRepository.findByUser(user)
    }

    fun findAllCustomers(user: User): Iterable<Subject> {
        return subjectRepository.findAllCustomersByUser(user)
    }

    fun findAllSuppliers(user: User): Iterable<Subject> {
        return subjectRepository.findAllSuppliersByUser(user)
    }
}