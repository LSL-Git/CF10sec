package com.lsl.ssm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于保存Token或删除Token
 * @author LSL
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD}) // 只能作用在方法上
@Retention(RetentionPolicy.RUNTIME) // jvm运行时保留
public @interface Token {

	boolean save() default false;
	boolean remove() default false;
}
