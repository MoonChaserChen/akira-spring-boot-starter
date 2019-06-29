### Spring Boot 快速集成CAS

### 如何使用cas-spring-boot-starter

* 引入依赖
```
<dependency>
    <groupId>ink.akira</groupId>
    <artifactId>cas-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

* 增加配置
```
# CAS服务器信息
cas.server.url.prefix=http://localhost:9000/cas
# 当前项目配置信息
cas.client.url.prefix=http://localhost:8080/hsb
```

* 获取登录用户信息

```java
Assertion assertion = AssertionHolder.getAssertion();
AttributePrincipal principal = assertion.getPrincipal();
String name = principal.getName();
Map<String, Object> attributes = principal.getAttributes();
```
