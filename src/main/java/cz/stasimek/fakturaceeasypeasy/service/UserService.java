package cz.stasimek.fakturaceeasypeasy.service;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException;
import cz.stasimek.fakturaceeasypeasy.repository.UserRepository;
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements AppService<User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager em;

	public User createUserIfNotExist(Provider provider, OAuth2User oAuth2User) {
		String login = oAuth2User.getAttribute("login");
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");
		if (login == null) {
			login = email;
		}
		if (login == null) {
			throw new ApplicationException("Cannot create user, login is missing.");
		}
		User user = find(provider, login, email);
		if (user == null) {
			user = new User();
			user.setProvider(provider);
			user.setLogin(login);
			user.setEmail(email);
			user.setName(name);
			user = create(user);
		}
		return user;
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		User originalUser = findById(user.getId());
		// Ensure that these values haven't been changed (login, provider, createdAt, deleted).
		String[] propertiesToIgnore = {"login", "provider", "createdAt", "deleted"};
		BeanUtils.copyProperties(user, originalUser, propertiesToIgnore);
		return userRepository.save(originalUser);
	}

	public void delete(UUID id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findById(UUID id) {
		return  userRepository.findById(id).orElseThrow();
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
