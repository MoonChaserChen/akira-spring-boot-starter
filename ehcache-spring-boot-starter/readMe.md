### 使用说明

* pom.xml
    ```
        <dependency>
            <groupId>ink.akira</groupId>
            <artifactId>ehcache-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    ```

* java code:
    ```java
        @org.springframework.boot.test.context.SpringBootTest
        @RunWith(SpringRunner.class)
        public class SpringBootTest {
            @Autowired
            private EhcacheDAO ehcacheDAO;
        
            @Test
            public void testEhcache(){
                ehcacheDAO.put("my_key", "my_value");
                Object myValue = ehcacheDAO.get("my_key");
                System.out.println(myValue);
                System.out.println((String) myValue);
            }
        }
    ```

* demo

    [使用demo](https://github.com/MoonChaserChen/demo/tree/master/demo-ehcache-spring-boot-starter)

* 配置项
![config.png](http://image.akira.ink/md/akira-spring-boot-starter/ehcache-spring-boot-starter/config.png)
