/*
 * Copyright 2012-2022
 */
package com.xl.goahead.db.mybatis.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义多数据源配置类
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/14 11:07 上午
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSource 数据源名称
     */
    public static void setDataSource(String dataSource){
        DATA_SOURCE_HOLDER.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDatasource() {
        return DATA_SOURCE_HOLDER.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        DATA_SOURCE_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DATA_SOURCE_HOLDER.get();
    }
}
