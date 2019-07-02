ELK日志系统部署教程

一、ELK应用场景
在复杂的企业应用服务群中,记录日志方式多种多样，并且不易归档以及提供日志监控的机制。无论是开发人员还是运维人员都无法准确的定位服务、服务器上面出现的种种问题，也没有高效搜索日志内容从而快速定位问题的方式。因此需要一个集中式、独立的、搜集管理各个服务和服务器上的日志信息，集中管理，并提供良好的UI界面进行数据展示，处理分析。

得此：ELK提供一套开源的解决方案，能高效、简便的满足以上场景。

二、ELK日志系统介绍
1、ELK分别是Elasticsearch、Logstash、Kibana三个开源框架缩写。

框架	简介	作用
Elasticsearch	开源分布式搜索引擎，提供存储、分析、搜索功能。特点：分布式、基于reasful风格、支持海量高并发的准实时搜索场景、稳定、可靠、快速、使用方便等。	接收搜集的海量结构化日志数据，并提供给kibana查询分析
Logstash	开源日志搜集、分析、过滤框架，支持多种数据输入输出方式。	用于收集日志，对日志进行过滤形成结构化数据，并转发到elasticsearch中
Kibana	开源日志报表系统，对elasticsearch以及logstash有良好的web页面支持。	对elasticsearch提供的数据进行分析展示

2、ELK经典应用如下



ELK经典架构

如图

Logstash部署至服务主机，对各个服务的日志进行采集、过滤、推送。
Elasticsearch存储Logstash传送的结构化数据，提供给Kibana。
Kibana提供用户UIweb页面进行，数据展示和分析形成图表等。
备注：logs 泛指，各种日志文件以及日志信息：windows，negix，tomcat，webserver等等。

3、ELK改进

由于Logstash消耗资源大，而服务器资源相当宝贵，所以引进另一个轻量级日志采集框架Beats，其中包含以下6种

Packetbeat	用于搜集网络流量数据
Heartbeat

用于运行时间监控
Filebeat	用于搜集文件数据
Winlogbeat	用于搜集winodws事件数据
Metricbeat	用于指标
Auditbeat	用于审计数据

改良ELK
4、进一步思考

传统web项目中，经常使用log4j以及logback（性能更高）等成熟日志插件进行日志的记录，是否提供更好的解决方案。



ELK升级1.0

如图

日志采集新增Logback直接发送日志到Logstash的形式。如果采用此方式，web服务可减少部分生成log文件配置，提高实时性和日志推送效率
5、高并发场景

由于logstash消耗性能，所以高并发场景容易遇到流量上的瓶颈，及时使用logstash集群也是如此，所以可以添加中间件进行日志缓存处理。由于logstash数据源具有多种方式，所有中间件也可以很多选择，常见的有kafka，redis。



ELK升级2.0

如图

host1、中间件、host2 均为高可用服务集群   为简单显示未画出
logback出现的业务数据可以通过写入redis或者kafka等中间件进行缓存，再通过合理限制流量阀值输送至logstash进行过滤
beats 如果是filebeat其日志若无实时性要求，可以通过控制log文件更新速度限制Beats传输日志流量

三 ELK搭建（非集群）
1、下载ELK（保持版本一致）！

Elasticsearch	官网elasticsearch-6.3.0.tar	elasticsearch官方文档
Kibana	官网kibana-6.3.0下载 linux64位	kibana官方文档
Logstash	官网logstash-6.3.0.tar	logstash官方文档
Filebeat	官网filebeat-6.3.0 linux64位	beats官方文档
备注:演示为centos7 即linux版本，请按实际需求更改

通过rz命令上传至centos7虚拟机




2、解压

tar -zxvf elasticsearch-6.3.0.tar.gz          
tar -zxvf kibana-6.3.0-linux-x86_64.tar.gz
tar -zxvf filebeat-6.3.0-linux-x86_64.tar.gz  
tar -zxvf logstash-6.3.0.tar.gz
备注:tar不支持指定解压目标目录 可以通过mv 命令进行迁移。本教程迁移至/home目录下
3、java环境搭建

推荐使用jdk1.8jdk环境配置

4、安装elasticsearch

修改配置文件

vi /home/elasticsearch-6.3.0/config/elasticsearch.yml
# ---------------------------------- Network -----------------------------------
#
# Set the bind address to a specific IP (IPv4 or IPv6):
#
network.host: 0.0.0.0           ##服务器ip 本机
#
# Set a custom port for HTTP:
#
http.port: 9200                 ##服务端口
#
# For more information, consult the network module documentation.
#
启动elasticsearch

/home/elasticsearch-6.3.0/bin/elasticsearch   #命令窗运行
/home/elasticsearch-6.3.0/bin/elasticsearch  -d  #后台线程运行
关闭elasticsearch

ctrl+c                                   #命令窗关闭
ps -ef | grep elastic                    #后台线程关闭
kill -9 4442                             ##pid 4442为查处线程的pid 


常见问题解决elasticsearch启动常见问题

验证elasticsearch启动





5、安装kibana

修改配置文件

vi /home/kibana-6.3.0-linux-x86_64/config/kibana.yml
server.port: 5601       ##服务端口
server.host: "0.0.0.0"  ##服务器ip  本机
 
elasticsearch.url: "http://localhost:9200" ##elasticsearch服务地址 与elasticsearch对应
启动kibana
/home/kibana-6.3.0-linux-x86_64/bin/kibana       #命令窗启动
nohup ./kibana-6.3.0-linux-x86_64/bin/kibana &   #后台线程启动
关闭kibana

ctrl+c                                   #命令窗关闭
ps -ef | grep kibana                    #后台线程关闭
kill -9 4525                             ##pid 4525 为查处线程的pid 


备注：常见问题多为 端口占用，以及目录未授权，需要同elasticsearch 使用目录运行执行的用户去执行 未配置则为root用户
验证kibana启动

6、安装logstash

新建配置文件

vi /home/logstash-6.3.0/config/logback-es.conf
input {
    tcp {  
        port => 9601  
        codec => json_lines         
    }
}
output {
        elasticsearch {
                hosts => "localhost:9200"
        }
        stdout { codec => rubydebug}
}
备注:上述文件复制时必须去除多余空格，保持yml文件规范。



备注：上图与配置部分一一对应
input {                                ##input 输入源配置
    tcp {                              ##使用tcp输入源      官网有详细文档
        port => 9601                   ##服务器监听端口9061 接受日志  默认ip localhost
        codec => json_lines            ##使用json解析日志    需要安装json解析插件
    }
} 
filter ｛                              ##数据处理
｝                                
output {                               ##output 数据输出配置
        elasticsearch {                ##使用elasticsearch接收
            hosts => "localhost:9200"  ##集群地址  多个用，隔开
        }
        stdout { codec => rubydebug}   ##输出到命令窗口
}
logstash官方输入源支持以及下载

安装logstash json插件

/home/logstash-6.3.0/bin/logstash-plugin install logstash-codec-json_lines
启动logstash

 /home/logstash-6.3.0/bin/logstash -f /home/logstash-6.3.0/config/logback-es.conf         ##命令窗形式
nohup /home/logstash-6.3.0/bin/logstash -f /home/logstash-6.3.0/config/logback-es.conf &  ##后台线程形式


关闭logstash

ctrl+c                                   #命令窗关闭
ps -ef | grep logstash                    #后台线程关闭
kill -9 4617                              ##pid 4617 为查处线程的pid 

7 使用logback 传输日志到logstash

建立springboot项目（为了快速使用）



pom文件依赖

               <dependency>
			<groupId>net.logstash.logback</groupId>
			<artifactId>logstash-logback-encoder</artifactId>
			<version>4.11</version>
		</dependency>
logback.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration>
<configuration>
    <appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>192.168.253.6:9601</destination>     <!--指定logstash ip：监听端口 tcpAppender  可自己实现如kafka传输等-->
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder" />
    </appender>
 
    <include resource="org/springframework/boot/logging/logback/base.xml"/>      <!--引用springboot默认配置-->
 
    <root level="INFO">
        <appender-ref ref="LOGSTASH" />                                           <!--使用上述订阅logstash数据tcp传输 -->
        <appender-ref ref="CONSOLE" />                                            <!--使用springboot默认配置 调试窗口输出-->
    </root>
 
</configuration>
SpringbootLogbackApplication.java 测试
package com.zyj;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class SpringbootLogbackApplication {
	private final static Logger logger = LoggerFactory.getLogger(SpringbootLogbackApplication.class);
 
	public static void main(String[] args) {
		new Thread(()->{
			for (int i=0;i<100;i++){
				logger.info("---test---"+i);
			}
		}).start();
		SpringApplication.run(SpringbootLogbackApplication.class, args);
	}
}
9 验证ELK

为演示方便，我们简单展示一下，单位时间线程打印某日志的速度。主要通过kibana过滤出结构化数据，通过以数据的时间戳为x轴，以count统计函数为y轴进行图表展示。

（1）后台启动elasticsearch  kibana logstash 并验证启动成功

（2）启动springboot项目



（3）logstash输出控制台记录  此为默认无过滤器打印logback包装的全部信息



（4）kibana日志显示

添加elasticsearch日志数据



使用时间戳显示 单位时间线程记录日志数量



四 、思考拓展
1、本文未详细介绍logback详细配置，以及自定义日志传输，将后续写入kafka以及redis方案，log4j亦可以使用elk因性能问题不做深究。

2、本文未详细介绍elasticsearch，logstash，kibana，beats的详细使用，仅罗列架构以及初步elk使用。beats常用为filebeat，对已经生成文档的日志进行传输。

3、没有完美的架构，只有合适的用法，针对不同的业务环境需要对架构进行微调，整体思路不变。elk为单独高可用服务群，服务器群与beats或者logback亦是独立高可用。

4、根据业务需要，在logback打印的日志中可以进行结构化处理，亦或者在logstash的filter中对数据进行结构化处理。业务场景有待考究，初步考虑异常分析以及sql回滚等。
