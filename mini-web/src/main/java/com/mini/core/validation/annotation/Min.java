package com.mini.core.validation.annotation;

import com.mini.core.validation.Constraint;
import com.mini.core.validation.constraint.MinConstraintValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.mini.core.web.util.ResponseCode.VERIFY;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 带注解的元素必须是一个大于或者等于指定最小值的数字
 * <br />
 * 支持的类型
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code Long}</li>
 *     <li>{@code Integer}</li>
 *     <li>{@code Short}</li>
 *     <li>{@code Byte}</li>
 *     <li>{@code long}</li>
 *     <li>{@code int}</li>
 *     <li>{@code short}</li>
 *     <li>{@code byte}</li>
 *     <li>{@code Double}</li>
 *     <li>{@code Float}</li>
 *     <li>{@code double}</li>
 *     <li>{@code float}</li>
 * </ul>
 * @author xchao
 */
@Documented
@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@Constraint(MinConstraintValidation.class)
public @interface Min {
	int error() default VERIFY;
	
	String message() default "";
	
	boolean require() default true;
	
	/**
	 * 最小值
	 * @return 最小值
	 */
	String value() default "";
}
