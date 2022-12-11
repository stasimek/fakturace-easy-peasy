package cz.stasimek.fakturaceeasypeasy.controller.access;

import cz.stasimek.fakturaceeasypeasy.config.user.AppUser;
import cz.stasimek.fakturaceeasypeasy.entity.Subject;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.exception.BadRequestException;
import cz.stasimek.fakturaceeasypeasy.service.SubjectService;
import cz.stasimek.fakturaceeasypeasy.service.UserService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccessTest {

	private User john;
	private User sarah;
	private Subject johnsCustomer;
	private Subject sarahsCustomer;
	private AppUser johnsPrincipal;
	private UserService userService;
	private SubjectService subjectService;

	@BeforeEach
	public void setUp() {
		john = new User();
		john.setId(UUID.randomUUID());
		john.setName("John");

		johnsCustomer = new Subject();
		johnsCustomer.setId(UUID.randomUUID());
		johnsCustomer.setCompanyName("John's Customer Company");
		johnsCustomer.setUser(john);

		sarah = new User();
		sarah.setId(UUID.randomUUID());
		sarah.setName("Sarah");

		sarahsCustomer = new Subject();
		sarahsCustomer.setId(UUID.randomUUID());
		sarahsCustomer.setCompanyName("Sarah's Customer Company");
		sarahsCustomer.setUser(sarah);

		johnsPrincipal = mock(AppUser.class);
		when(johnsPrincipal.getUser()).thenReturn(john);

		userService = mock(UserService.class);
		when(userService.findById(john.getId())).thenReturn(john);
		when(userService.findById(sarah.getId())).thenReturn(sarah);

		subjectService = mock(SubjectService.class);
		when(subjectService.findById(johnsCustomer.getId())).thenReturn(johnsCustomer);
		when(subjectService.findById(sarahsCustomer.getId())).thenReturn(sarahsCustomer);
	}

	@Test
	public void check_johnUpdatesHisUser_shouldNotThrowException() {
		assertDoesNotThrow(
				() -> Access.check(john.getId(), johnsPrincipal, "update", userService)
		);
	}

	@Test
	public void check_johnUpdatesSarah_shouldThrowException() {
		Exception thrown = assertThrows(
				BadRequestException.class,
				() -> Access.check(sarah.getId(), johnsPrincipal, "update", userService)
		);
		assertThat(
				thrown.getMessage(),
				is("Unable to update user setting. You can update only yourself.")
		);
	}

	@Test
	public void check_johnUpdatesHisSubject_shouldNotThrowException() {
		assertDoesNotThrow(
				() -> Access.check(johnsCustomer.getId(), johnsPrincipal, "update", subjectService)
		);
	}

	@Test
	public void check_johnUpdatesSarahsCustomer_shouldThrowException() {
		Exception thrown = assertThrows(
				BadRequestException.class,
				() -> Access.check(sarahsCustomer.getId(), johnsPrincipal, "update", subjectService)
		);
		assertThat(
				thrown.getMessage(),
				is("Unable to update subject. You can update only your own subjects.")
		);
	}
}
