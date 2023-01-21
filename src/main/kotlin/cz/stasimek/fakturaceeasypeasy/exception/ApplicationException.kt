package cz.stasimek.fakturaceeasypeasy.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ApplicationException(message: String) : RuntimeException(message)