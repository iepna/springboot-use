一、安装influxdb
1.下载
       wget https://dl.influxdata.com/influxdb/releases/influxdb-1.7.7_linux_amd64.tar.gz

2.解压
       tar zxvf influxdb-1.7.7_linux_amd64.tar.gz

3.启动
       cd influxdb-1.7.7-1/usr/bin
         ./influxd

启动后打开 web 管理界面 http://192.168.2.183:8083/ 默认用户名和密码是 root 和 root. InfluxDB 的 Web 管理界面端口是 8083，HTTP API 监听端口是 8086，如果需要更改这些默认设定，修改 InfluxDB 的配置文件（/etc/influxdb/influxdb.conf）并重启就可以了。

二、安装grafana
1.下载
      wget https://dl.grafana.com/oss/release/grafana-6.2.5.linux-amd64.tar.gz 

2.解压
      tar zxvf grafana-6.2.5.linux-amd64.tar.gz

3.启动
      cd grafana-6.2.5/bin
      ./grafana-server 

4.登录
      打开浏览器，输入IP+端口，3000为Grafana的默认侦听端口。
      系统默认用户名和密码为admin/admin，第一次登陆系统会要求修改密码