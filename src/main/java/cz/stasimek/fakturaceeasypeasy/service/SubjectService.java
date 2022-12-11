package cz.stasimek.fakturaceeasypeasy.service;

import cz.stasimek.fakturaceeasypeasy.entity.Subject;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.repository.SubjectRepository;
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements AppService<Subject> {

	@Autowired
	private SubjectRepository subjectRepository;

	public Subject create(Subject subject) {
		return subjectRepository.save(subject);
	}

	public Subject update(Subject subject) {
		return subjectRepository.save(subject);
	}

	public void deleteById(UUID id) {
		subjectRepository.deleteById(id);
	}

	@Override
	public Subject findById(UUID id) {
		return subjectRepository.findById(id).orElseThrow();
	}

	public Iterable<Subject> findAll(User user) {
		return subjectRepository.findByUser(user);
	}

	public Iterable<Subject> findAllCustomers(User user) {
		return subjectRepository.findAllCustomersByUser(user);
	}

	public Iterable<Subject> findAllSuppliers(User user) {
		return subjectRepository.findAllSuppliersByUser(user);
	}

}
