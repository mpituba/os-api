package br.com.mpituba.osapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.mpituba.osapi.domain.exception.OSBusinessException;
import br.com.mpituba.osapi.domain.exception.OSEntityInUseException;
import br.com.mpituba.osapi.domain.exception.OSEntityNotFoundException;





@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String END_USER_GENERIC_ERROR
	= "A system error has been ocurred. Try again and if "
			+ "the error persists, please contact the systems administrator.";
	
	/**
	 * If exception cannot be caught, then handle 500 Response Status
	**/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
		String detail = END_USER_GENERIC_ERROR;

		ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	/**
	 * if there is no handle, then the resource does not exists, and send 404 a
	 * not found Response status.
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = String.format("The resource %s, does not exists.", 
				ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	/**
	 * Handle with OsBusinessException that is a Business Rule. It send a 400 Bad Request
	 * in its response when a business rule violation is broken.
	 * It depends on handleExceptionInternal.
	 * */
	@ExceptionHandler(OSBusinessException.class)
	public ResponseEntity<?> handleBusiness(OSBusinessException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.BUSINESS_RULE_VIOLATION;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
		
	/**

	 *  Return a 404 not found if the Entity does not exists.
	 */
	@ExceptionHandler(OSEntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFound(OSEntityNotFoundException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * TODO Test when cidade controller were created.
	 * Handle with exceptions generated when associated records are in use and
	 * cannot be deleted. It generates a conflict response status. 
	 *
	 */
	@ExceptionHandler(OSEntityInUseException.class)
	public ResponseEntity<?> handlerEntityInUse(OSEntityInUseException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * This method depends on handleMethodArgumentTypeMismatch.
	 * This handle deals with data type mismatches. 
	 */
	
	 @Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch(
					(MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
	
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	 
	/**
	 * This method is a dependency of handleTypeMismatch .
	 */ 
	 
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.INVALID_PARAMETER;

		String detail = String.format("The URL's parameter '%s' got the value '%s', "
				+ "and is a invalid type. You must inform a correct value with valid type %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(END_USER_GENERIC_ERROR)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	 
	
	/**
	 * Handles with HttpMessageNotReadable that meens the message format is invalid on
	 * the request body of message.
	 * It also handles with InvalidFormatException and PropertyBindException.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request); 
		}
		
		ProblemType problemType = ProblemType.INVALID_MESSAGE;
		String detail = "The request body is invalid. Verify if there's a sintaxe error "
				+ "on your request.";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	/**
	 * It is a dependency for handleHttpMessageNotReadable
	 * 
	 */
	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.INVALID_MESSAGE;
		String detail = String.format("The '%s' property does not exists. "
				+ "You must correct or remove this property."
				+ " Then, try again.", path);

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	/**
	 * It is a dependency for handleHttpMessageNotReadable
	 */
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.INVALID_MESSAGE;
		String detail = String.format("The '%s' property got the value '%s', "
				+ "and this is a wrong type. Correct it's value to a %s type and try again.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	/**
	 * Its a dependency for a lot of exceptionhandlers methods.
	 * If object's body is null it gets the title in reason phrase. If not, it get its
	 * body on the title's body.	
	 * */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		
		if (body == null) {
			body = Problem.builder()
				.timeStamp(OffsetDateTime.now())
				.title(status.getReasonPhrase())
				.status(status.value())
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		
		} else if (body instanceof String) {
			body = Problem.builder()
				.timeStamp(OffsetDateTime.now())
				.title((String) body)
				.status(status.value())
				.userMessage(END_USER_GENERIC_ERROR)
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	//It's a dependency for a lot of methos.
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
			ProblemType problemType, String detail) {
		
		return Problem.builder()
			.timeStamp(OffsetDateTime.now())
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail);
	}

	
	//Its a dependency for a lot of methods	
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
	
}
