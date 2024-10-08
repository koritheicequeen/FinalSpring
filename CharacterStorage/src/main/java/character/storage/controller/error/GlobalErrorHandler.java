package character.storage.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
private enum LogStatus {
	STACK_TRACE, MESSAGE_ONLY
}
@Data
private class ExceptionMessage{
	private String message;
	private String statusReason;
	private int statusCode;
	private String timeStamp;
	private String uri;
}

@ExceptionHandler(NoSuchElementException.class)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public ExceptionMessage handleNoSuchElementException (NoSuchElementException ex, WebRequest webrequest) {
	return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, webrequest, LogStatus.MESSAGE_ONLY);
}


private ExceptionMessage buildExceptionMessage(NoSuchElementException ex, HttpStatus status, WebRequest webrequest,
		LogStatus logstatus) {
	String message = ex.toString();
	String statusReason = status.toString();
	int statusCode = status.value();
	String uri = null;
	String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
	if (webrequest instanceof ServletWebRequest swr) {
		uri = swr.getRequest().getRequestURI();
	}
	if (logstatus == LogStatus.MESSAGE_ONLY) {
		log.error("Exception: {}", ex.toString());
	} else {
		log.error("Exception: ", ex);
	}
	ExceptionMessage em = new ExceptionMessage();
	em.setMessage(message);
	em.setStatusCode(statusCode);
	em.setStatusReason(statusReason);
	em.setTimeStamp(timestamp);
	em.setUri(uri);
		
	return em;
}
}
