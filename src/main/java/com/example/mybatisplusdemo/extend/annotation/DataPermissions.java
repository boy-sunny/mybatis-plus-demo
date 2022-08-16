package com.example.mybatisplusdemo.extend.annotation;

import cn.hutool.core.text.CharSequenceUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解
 *
 * @author Matrix
 * @date 2022/8/15 11:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermissions {

    /**
     * 自定义表达式
     */
    String expression() default CharSequenceUtil.EMPTY;

}
