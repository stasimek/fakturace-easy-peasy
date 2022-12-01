package cz.stasimek.fakturaceeasypeasy.config.user;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class AppOidcUser implements OidcUser {

	private OidcUser oidcUser;
	private UUID appUserId;

	public AppOidcUser(OidcUser oidcUser, User user) {
		this.oidcUser = oidcUser;
		appUserId = user.getId();
	}

	@Override
	public Object getAttribute(String name) {
		if ("appUserId".equals(name)) {
			return appUserId;
		}
		return oidcUser.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oidcUser.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oidcUser.getAuthorities();
	}

	@Override
	public String getName() {
		return oidcUser.getName();
	}

	@Override
	public Map<String, Object> getClaims() {
		return oidcUser.getClaims();
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return oidcUser.getUserInfo();
	}

	@Override
	public OidcIdToken getIdToken() {
		return oidcUser.getIdToken();
	}

}
