package com.woodpecker.webframework.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface AuthorityInstruction {
	/**
     * 接口编号.
     */
    String ifcode() default "";

    /**
     * 权限类型
     * high 高 所有权限都验证. 最高安全级别
     * middle 中 只验证是否登陆 中等安全级别
     * low 低 不做任何验证.
     * Permission type
     */
    PerType perType();

    /**
     * 权限开启状态
     */
    OpenStatus status();

}
