package cz.stasimek.fakturaceeasypeasy.controller.access;

import cz.stasimek.fakturaceeasypeasy.config.user.AppUser;
import cz.stasimek.fakturaceeasypeasy.entity.BaseEntity;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.HasUser;
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException;
import cz.stasimek.fakturaceeasypeasy.exception.BadRequestException;
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService;
import cz.stasimek.fakturaceeasypeasy.util.AppStringUtils;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Access {

	private Access() {
	}

	public static void check(
			UUID id, OAuth2User principal, String action, AppService service
	) {
		BaseEntity original = service.findById(id);
		User user;
		if (original instanceof User) {
			user = (User) original;
		} else if (original instanceof HasUser) {
			user = ((HasUser) original).getUser();
		} else {
			throw new ApplicationException(
					"Entity " + original.getClass().getName() + " isn't instance of User or HasUser."
			);
		}

		if (!user.getId().equals(getUser(principal).getId())) {
			String entityWords = AppStringUtils.camelCaseToWords(original.getClass().getSimpleName());
			throw new BadRequestException(
					original instanceof User
							? "Unable to " + action + " user setting. "
							+ "You can " + action + " only yourself."
							: "Unable to " + action + " " + entityWords + "."
							+ " You can " + action + " only your own " + entityWords + "s."
			);
		}
	}

	public static User getUser(OAuth2User principal) {
		if (!(principal instanceof AppUser)) {
			throw new ApplicationException("Principal must be instance of AppUser.");
		}

		return ((AppUser) principal).getUser();
	}
}
