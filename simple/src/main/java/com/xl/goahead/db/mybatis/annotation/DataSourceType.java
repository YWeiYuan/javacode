/*
 * Copyright 2012-2022
 */
package com.xl.goahead.db.mybatis.annotation;

import com.xl.goahead.db.mybatis.constant.DataSourceConstants;

import java.lang.annotation.*;

/**
 * 自定义数据源类型注解
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/14 11:04 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceType {
    String value() default DataSourceConstants.DATASOURCE_1;
}
