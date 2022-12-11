package cz.stasimek.fakturaceeasypeasy.repository;

import cz.stasimek.fakturaceeasypeasy.entity.Subject;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends CrudRepository<Subject, UUID> {

	@Query(
			"SELECT s"
			+ " FROM Subject s"
			+ " WHERE "
			+ "   s.user = :user"
			+ "   AND type IN ('CUSTOMER', 'BOTH')"
			+ "   AND deleted = false"
	)
	Iterable<Subject> findAllCustomersByUser(@Param("user") User user);

	@Query(
			"SELECT s"
			+ " FROM Subject s"
			+ " WHERE "
			+ "   s.user = :user"
			+ "   AND type IN ('SUPPLIER', 'BOTH')"
			+ "   AND deleted = false"
	)
	Iterable<Subject> findAllSuppliersByUser(@Param("user") User user);
}
