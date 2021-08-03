# Springboot2.x 中使用时序数据库InfluxDB
https://mp.weixin.qq.com/s/ZU41h1oOMhoUHfKM5I5WEQ

InfluxDb简介
时间序列数据库主要用于处理带有时间标签的数据。
应用场景：
产生频率快
严重依赖时间
测点多信息量大 （每天生产几十G的数据量）

InfluxDb是比较流行开源时序数据库

重要名词：
database：数据库
measurement：类似table
points：类似row（一行数据）

其中一个points由三部分组成：
time：时间戳
tags：索引的属性
fields：记录的值

influxDb安装部署

wget https://dl.influxdata.com/influxdb/releases/influxdb-1.8.2.x86_64.rpm
sudo yum -y localinstall influxdb-1.8.2.x86_64.rpm

https://www.jianshu.com/p/e3eba4dc7439

安装完成后，在/usr/bin/有如下文件：
influxd influxdb服务器
influx influxdb命令行客户端
influx_inspect 查看工具
influx_stress 压力测试工具
influx_tsm 数据库转换工具（将数据库从b1或者bz1格式转换成tsm1格式）

启动
sudo service influxdb start
# or
systemctl start influxdb

#在/etc/influxdb/influxdb.conf中
        [http]
        auth-enabled=true

#添加一个管理员用户
create user "admin" with password 'h12345678' with all privileges

influxdb中使用用户名密码登录
influx -username  admin -password h12345678

创建database
create database "monitor";

运行程序后：

select * from ApiQPS order by time desc;


代码在
com.weiyuan.learn.influxdb.monitor.Monitor中
maven添加
<dependency>
<groupId>org.influxdb</groupId>
<artifactId>influxdb-java</artifactId>
</dependency>
springboot2.x版本不用指定sdk版本号


autogen参数的含义：配置这个表的存储策略，表示数据永不删除且备份数replication为1。




