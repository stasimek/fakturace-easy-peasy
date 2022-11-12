package cz.stasimek.fakturaceeasypeasy.repository;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {

	User findByProviderAndLogin(Provider provider, String login);

	User findByProviderAndEmail(Provider provider, String email);
}
