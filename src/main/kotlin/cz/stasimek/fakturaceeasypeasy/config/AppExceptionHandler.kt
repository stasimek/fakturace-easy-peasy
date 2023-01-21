package cz.stasimek.fakturaceeasypeasy.config

import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.io.IOException
import javax.persistence.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class AppExceptionHandler {

    @ExceptionHandler(value = [RollbackException::class])
    @Throws(
        IOException::class
    )
    protected fun handleRollbackException(
        ex: RollbackException,
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?
    ): ModelAndView {
        if (ex.cause is Exception) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, (ex.cause as Exception).message)
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.message)
        }
        return ModelAndView()
    }
}