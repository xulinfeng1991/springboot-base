
#### 线上环境
 * 对外端口： `28249`
 * 对内域名和端口： `sb_spring:8080`

#### 本地跑单测的命令解释
##### 1. 启动docker容器(mysql+redis)，并且初始化数据库结构
`sh test-deps.sh`

##### 2. 跑单测(指定配置文件为testci，也可以直接在IDEA上操作)
`SPRING_PROFILES_ACTIVE=testci mvn clean integration-test`

##### 3. 停止docker容器（可选）
`docker-compose -p armstrong -f docker/testci/docker-compose.yml stop`
