## env config（单机和集群都需配置）
~~~
vim /etc/profile

export HADOOP_HOME=/mnt/d/uliunx/hadoop-3.1.4
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
~~~


## ***单机运行方式（修改以下配置即可本机运行）【本方式适用 wsl】***
~~~
/mnt/d/uliunx/hadoop-3.1.4/etc/hadoop/hadoop-env.sh

export JAVA_HOME=/mnt/d/uliunx/java/jdk1.8.0_144
export HADOOP_HOME=/mnt/d/uliunx/hadoop-3.1.4

~~~ 


## hadoop jar run  (local model)
~~~
cd target 
hadoop jar de-1.0.jar com/hdp/WordCountDriver  /home/user/javaproject/bigda/data/input/ /home/user/javaproject/bigda/data/output
~~~