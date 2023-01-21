package cz.stasimek.fakturaceeasypeasy.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(message: String) : RuntimeException(message)