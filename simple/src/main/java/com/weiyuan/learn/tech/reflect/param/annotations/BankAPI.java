/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param.annotations;

import java.lang.annotation.*;

/**
 * 接口描述
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:35 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";
    String url() default "";
}
