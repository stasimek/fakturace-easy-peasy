package cz.stasimek.fakturaceeasypeasy.config;

import java.io.IOException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = RollbackException.class)
	protected ModelAndView handleRollbackException(
			RollbackException ex,
			HttpServletRequest request,
			HttpServletResponse response,
			@Nullable Object handler
	) throws IOException {
		if (ex.getCause() instanceof Exception) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getCause().getMessage());
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return new ModelAndView();
	}

}
