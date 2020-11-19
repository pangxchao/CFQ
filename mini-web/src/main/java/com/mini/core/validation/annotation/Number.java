package com.mini.core.validation.annotation;

import com.mini.core.validation.Constraint;
import com.mini.core.validation.constraint.NumberConstraintValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.mini.core.web.util.ResponseCode.VERIFY;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 带注解的元素必须只包含数字
 * <br />
 * 支持的类型
 * <ul>
 *     <li>{@code String}</li>
 * </ul>
 * @author xchao
 */
@Documented
@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@Constraint(NumberConstraintValidation.class)
public @interface Number {
	int error() default VERIFY;
	
	String message() default "";
	
	boolean require() default true;
}
