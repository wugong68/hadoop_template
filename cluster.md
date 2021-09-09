## env 
~~~
vim /etc/profile

export HADOOP_HOME=/mnt/d/uliunx/hadoop-3.1.4
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
~~~

## config 配置

### /mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/hadoop-env.sh
~~~
export JAVA_HOME=/mnt/d/uliunx/java/jdk1.8.0_144
export HADOOP_HOME=/mnt/d/uliunx/hadoop-3.1.4
# 解决Unable to load native-hadoop library for your platform...
export HADOOP_OPTS="-Djava.library.path=${HADOOP_HOME}/lib/native"

export HDFS_NAMENODE_USER=user
export HDFS_DATANODE_USER=user
export HDFS_SECONDARYNAMENODE_USER=user
export YARN_RESOURCEMANAGER_USER=user
export YARN_NODEMANAGER_USER=user
~~~

### /mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/core-site.xml
~~~
<configuration>

<property>
  <name>fs.defaultFS</name>
  <value>hdfs://localhost:8020</value>
</property>
<property>
  <name>hadoop.tmp.dir</name>
  <value>/mnt/d/uliunx/data</value>
</property>
<property>
  <name>hadoop.http.staticuser.user</name>
  <value>user</value>
</property>

</configuration>
~~~

### /mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/hdfs-site.xml
~~~
<configuration>

<property>
  <name>dfs.namenode.secondary.http-address</name>
  <value>localhost:9868</value>
</property>
</configuration>
~~~

### /mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/mapred-site.xml
~~~

<configuration>
<property>
  <name>mapredduce.framework.name</name>
  <value>yarn</value>
</property>
<property>
  <name>mapredduce.jobhistory.address</name>
  <value>localhost:10020</value>
</property>
<property>
  <name>mapredduce.jobhistory.webapp.address</name>
  <value>localhost:19888</value>
</property>
<property>
  <name>yarn.app.mapreduce.am.env</name>
  <value>HADOOP_MAPRED_HOME=${HADOOP_HOME}</value>
</property>
<!-- map task 环境变量 -->
<property>
  <name>mapreduce.map.env</name>
  <value>HADOOP_MAPRED_HOME=${HADOOP_HOME}</value>
</property>
<!-- reduce task 环境变量 -->
<property>
  <name>mapreduce.reduce.env</name>
  <value>HADOOP_MAPRED_HOME=${HADOOP_HOME}</value>
</property>


</configuration>

~~~

### /mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/yarn-site.xml
~~~
<configuration>
<!-- 集群主机运行机器 -->
<property>
  <name>yarn.resourcemanager.hostname</name>
  <value>localhost</value>
</property>
<!-- nodemanager 上运行的附属服务 徐娅萍配置成 mapreduce_shuffle-->
<property>
  <name>yarn.nodemanager.aux-services</name>
  <value>mapreduce_shuffle</value>
</property>
<property>
  <name>yarn.nodemanager.aux-services</name>
  <value>mapreduce_shuffle</value>
</property>
<!-- 虚拟内存/物理内存比率 -->
<property>
  <name>yarn.nodemanager.vmem-pmem-ratio</name>
  <value>4</value>
</property>
<!-- 开启日志聚集 -->
<property>
  <name>yarn.log-aggregation-enable</name>
  <value>true</value>
</property>
<!-- 日志保留时间 -->
<property>
  <name>yarn.log-aggregation.retain-seconds</name>
  <value>86400</value>
</property>
<property>
  <name>yarn.log.server.url</name>
  <value>http://localhost:19888/jobhistory/logs</value>
</property>
</configuration>

~~~

## init 
~~~
hdfs namenode -format


wait moment for word:
    common.Storage: Storage directory /mnt/d/uliunx/data/dfs/name has been successfully formatted.
~~~

## run
~~~
#HDFS
start-dfs.sh
stop-dfs.sh

#YARN
start-yarn.sh
stop-yarn.sh

#Hadoop(run all used this)
start-all.sh
stop-all.sh

~~~