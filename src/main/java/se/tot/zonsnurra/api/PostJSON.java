package se.tot.zonsnurra.api;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestJSON(
	method = RequestMethod.POST,
	status = HttpStatus.CREATED,
	consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public @interface PostJSON {

	@AliasFor(annotation = RequestMapping.class)
	String[] value() default {};
}