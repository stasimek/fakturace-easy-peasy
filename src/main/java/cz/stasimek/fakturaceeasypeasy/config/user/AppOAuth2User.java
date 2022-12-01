package cz.stasimek.fakturaceeasypeasy.config.user;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AppOAuth2User implements OAuth2User {

	private OAuth2User oAuth2User;
	private UUID appUserId;

	public AppOAuth2User(OAuth2User oAuth2User, User user) {
		this.oAuth2User = oAuth2User;
		appUserId = user.getId();
	}

	@Override
	public Object getAttribute(String name) {
		if ("appUserId".equals(name)) {
			return appUserId;
		}
		return oAuth2User.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getName();
	}

}
