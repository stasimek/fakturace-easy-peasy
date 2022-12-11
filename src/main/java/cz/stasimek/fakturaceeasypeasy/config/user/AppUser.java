package cz.stasimek.fakturaceeasypeasy.config.user;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AppUser extends OAuth2User {
	User getUser();
}
