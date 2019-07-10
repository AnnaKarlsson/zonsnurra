package se.tot.zonsnurra.api;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(
	produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseStatus
public @interface RequestJSON {

	@AliasFor(annotation = RequestMapping.class)
	RequestMethod[] method();

	@AliasFor(annotation = ResponseStatus.class, attribute = "code")
  HttpStatus status();

	@AliasFor(annotation = RequestMapping.class)
	String[] consumes() default {};
}
