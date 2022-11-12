package cz.stasimek.fakturaceeasypeasy.service;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import cz.stasimek.fakturaceeasypeasy.repository.UserRepository;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager em;

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void delete(UUID id) {
		userRepository.deleteById(id);
	}

	public User find(Provider provider, String login, String email) {
		User user = null;
		if (login != null) {
			user = userRepository.findByProviderAndLogin(provider, login);
		}
		if (user == null && email != null) {
			user = userRepository.findByProviderAndEmail(provider, email);
		}
		return user;
	}

	public Iterable<User> findAll(boolean deleted) {
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedFilter");
		filter.setParameter("deleted", deleted);
		Iterable<User> users = userRepository.findAll();
		session.disableFilter("deletedFilter");
		return users;
	}
}
