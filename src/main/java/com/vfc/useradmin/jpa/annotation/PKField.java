package com.vfc.useradmin.jpa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface PKField {
	
/*	"ORCLE_SEQUENCE",
	"MYSQL_AUTO_INCREASING",
	"SELECTED_INCREASING",
	"CURE_UUID"*/
	PkGeneratorEnum pkGenerator() default PkGeneratorEnum.SEQUENCE;
	
	String sequenceName() default "";

}
