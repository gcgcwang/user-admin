package com.vfc.useradmin.jpa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vfc.useradmin.jpa.enums.JoinTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface JoinColumn {

	String name() default "";//外键
	JoinTypeEnum joinType() default JoinTypeEnum.LEFT_JOIN;
	String refName() default "";//外表映射字段名
	String refTableName() default "";//外表映射表名
	
}
