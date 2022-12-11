package cz.stasimek.fakturaceeasypeasy.config.user;

import cz.stasimek.fakturaceeasypeasy.entity.User;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class AppOidcUser implements OidcUser, AppUser {

	private final OidcUser oidcUser;

	@Getter
	private final User user;

	public AppOidcUser(OidcUser oidcUser, User user) {
		this.oidcUser = oidcUser;
		this.user = user;
	}

	@Override
	public Object getAttribute(String name) {
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
