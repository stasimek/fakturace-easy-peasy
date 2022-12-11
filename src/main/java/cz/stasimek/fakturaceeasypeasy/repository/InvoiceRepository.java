package cz.stasimek.fakturaceeasypeasy.repository;

import cz.stasimek.fakturaceeasypeasy.entity.Invoice;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, UUID> {

	@Query(
			"SELECT i"
			+ " FROM Invoice i"
			+ " WHERE"
			+ "   i.user = :user"
			+ "   AND i.type = 'ISSUED'"
			+ "   AND i.deleted = false"
			+ " ORDER BY dueDate DESC"
	)
	List<Invoice> findLastIssuedByUser(@Param("user") User user, Pageable pageable);

	@Query(
			"SELECT i"
			+ " FROM Invoice i"
			+ " WHERE "
			+ "   i.user = :user"
			+ "   AND i.type = 'ISSUED'"
			+ "   AND year(i.issueDate) = :year"
			+ "   AND deleted = false"
	)
	Iterable<Invoice> findIssuedByUserAndYear(
			@Param("user") User user,
			@Param("year") int year
	);

	@Query(
			"SELECT i"
			+ " FROM Invoice i"
			+ " WHERE "
			+ "   i.user = :user"
			+ "   AND i.type = 'RECEIVED'"
			+ "   AND year(i.receiveDate) = :year"
			+ "   AND deleted = false"
	)
	Iterable<Invoice> findReceivedByUserAndYear(
			@Param("user") User user,
			@Param("year") int year
	);

}
