package cz.stasimek.fakturaceeasypeasy.controller.access

import cz.stasimek.fakturaceeasypeasy.config.user.AppUser
import cz.stasimek.fakturaceeasypeasy.controller.access.Access.check
import cz.stasimek.fakturaceeasypeasy.entity.Subject
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.exception.BadRequestException
import cz.stasimek.fakturaceeasypeasy.service.SubjectService
import cz.stasimek.fakturaceeasypeasy.service.UserService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class AccessTest {

    private lateinit var john: User
    private lateinit var sarah: User
    private lateinit var johnsCustomer: Subject
    private lateinit var sarahsCustomer: Subject
    private lateinit var johnsPrincipal: AppUser
    private lateinit var userService: UserService
    private lateinit var subjectService: SubjectService

    @BeforeEach
    fun setUp() {
        john = User()
        john.id = UUID.randomUUID()
        john.name = "John"
        johnsCustomer = Subject()
        johnsCustomer.id = UUID.randomUUID()
        johnsCustomer.companyName = "John's Customer Company"
        johnsCustomer.user = john

        sarah = User()
        sarah.id = UUID.randomUUID()
        sarah.name = "Sarah"
        sarahsCustomer = Subject()
        sarahsCustomer.id = UUID.randomUUID()
        sarahsCustomer.companyName = "Sarah's Customer Company"
        sarahsCustomer.user = sarah

        johnsPrincipal = Mockito.mock(AppUser::class.java)
        Mockito.`when`(johnsPrincipal.user).thenReturn(john)

        userService = Mockito.mock(UserService::class.java)
        Mockito.`when`(userService.findById(john.id!!)).thenReturn(john)
        Mockito.`when`(userService.findById(sarah.id!!)).thenReturn(sarah)

        subjectService = Mockito.mock(SubjectService::class.java)
        Mockito.`when`(subjectService.findById(johnsCustomer.id!!)).thenReturn(johnsCustomer)
        Mockito.`when`(subjectService.findById(sarahsCustomer.id!!)).thenReturn(sarahsCustomer)
    }

    @Test
    fun check_johnUpdatesHisUser_shouldNotThrowException() {
        Assertions.assertDoesNotThrow {
            check(
                john.id!!, johnsPrincipal, "update", userService
            )
        }
    }

    @Test
    fun check_johnUpdatesSarah_shouldThrowException() {
        val thrown: Exception = Assertions.assertThrows(
            BadRequestException::class.java
        ) { check(sarah.id!!, johnsPrincipal, "update", userService) }
        assertThat(
            thrown.message,
            `is`("Unable to update user setting. You can update only yourself.")
        )
    }

    @Test
    fun check_johnUpdatesHisSubject_shouldNotThrowException() {
        Assertions.assertDoesNotThrow {
            check(
                johnsCustomer.id!!, johnsPrincipal, "update", subjectService
            )
        }
    }

    @Test
    fun check_johnUpdatesSarahsCustomer_shouldThrowException() {
        val thrown: Exception = Assertions.assertThrows(
            BadRequestException::class.java
        ) { check(sarahsCustomer.id!!, johnsPrincipal, "update", subjectService) }
        assertThat(
            thrown.message,
            `is`("Unable to update subject. You can update only your own subjects.")
        )
    }
}