# baiduUidGenerator
对百度开源雪花算法，全局唯一id生成器UidGeneratorr的封装
## 使用方式
- 已封装为starter，maven添加：

```xml
        <!-- mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
        <!--唯一id生成器-->
        <dependency>
            <groupId>com.github.Shirtiny</groupId>
            <artifactId>baiduUidGenerator</artifactId>
            <version>e26810d2b2</version>
        </dependency>
        <!-- 工具包 Apache Commons-->
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.2</version>
        </dependency>
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
```

```xml
<repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
</repositories>
```

- **数据库建表**

```sql
DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE
(
ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
PORT VARCHAR(64) NOT NULL COMMENT 'port',
TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
CREATED TIMESTAMP NOT NULL COMMENT 'created time',
PRIMARY KEY(ID)
)
 COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;
```

- **配置数据库连接**

```properties
spring.datasource.url=xxx
spring.datasource.username=xxx
spring.datasource.password=xxx
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

- **mapper扫描**

```java
package cn.shirtiny.community.SHcommunity;

import ...

@SpringBootApplication
@MapperScan({"com.baidu.fsg.uid.worker.dao"})
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
```

- 以上配置完成后，直接注入使用即可。

```java
@Autowired
private UidGenerateService uidGenerateService;
```

```java
@GetMapping("/shApi/getId")
public String test() {
	long id = uidGenerateService.generateUid();
   	return String.valueOf(id);
}
```

我的博客地址：[如何封装一个唯一id生成器](https://shirtiny.cn/2019/12/05/%E5%94%AF%E4%B8%80id%E7%94%9F%E6%88%90%E5%99%A8%E5%B0%81%E8%A3%85/)
