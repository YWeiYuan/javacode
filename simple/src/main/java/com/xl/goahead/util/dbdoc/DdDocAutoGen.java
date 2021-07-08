/*
 * Copyright 2012-2022
 */
package com.xl.goahead.util.dbdoc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.xl.goahead.util.PropertiesUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库文档生成
 * 在线文档在这里 https://gitee.com/leshalv/screw.git
 * 需要引入mysql数据库驱动 + 数据库连接池 + screw 螺丝钉
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/21 5:40 下午
 */
public class DdDocAutoGen {

    public static void main(String[] args) {
        autoGenDbDoc();
    }

    public static  void autoGenDbDoc(){
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("/Users/huanglongquan/tmp")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        //忽略表
        List<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        //忽略表前缀
        List<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        //忽略表后缀
        List<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        ProcessConfig processConfig = ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("代步车数据库设计")
                //数据源
                .dataSource(primaryDataSource())
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置信息
     * @return
     */
    private static DataSource primaryDataSource() {
        Properties pros = PropertiesUtil.loadProperties("screw.properties");
        HikariConfig config = new HikariConfig();
        String driverClassName = pros.getProperty("datasource.driver-class-name");
        config.setDriverClassName(driverClassName);
        String url = pros.getProperty("datasource.url");
        config.setJdbcUrl(url);
        String username = pros.getProperty("datasource.username");
        config.setUsername(username);
        String password = pros.getProperty("datasource.password");
        config.setPassword(password);
        //设置可以获取tables remarks信息
        config.addDataSourceProperty("useInformationSchema", "true");
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(5);
        return new HikariDataSource(config);
    }
}
