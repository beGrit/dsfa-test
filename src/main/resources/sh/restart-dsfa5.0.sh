#!/bin/sh
source /etc/profile
cd /opt/workenv/services/dsfa5.0
ID=`ps aux | grep dsfa-service-core-1.0.0-SNAPSHOT.jar | grep -v grep |awk '{print $2}'`
if [ -n "$ID" ] ;then
    kill -9 $ID
fi
nohup java -Xms1g -Xmx1g -jar dsfa-service-core-1.0.0-SNAPSHOT.jar > server.log 2>&1 &
sleep 60
ps aux | grep dsfa-service-core-1.0.0-SNAPSHOT.jar | grep -v grep > /dev/null
if [ $? -eq 0 ] ;then
    echo "dsfa5.0 启动成功！"
else
    echo "dsfa5.0 启动失败！"
    tail -1000 server.log
    exit 1
fi