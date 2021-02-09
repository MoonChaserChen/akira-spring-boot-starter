# webcore-spring-boot-starter
存放一些web开发核心的相关信息，可作为开发规范，包括以下部分：
1. 异常定义
2. Web前后端接口规范：WebResult
3. 通用常量

## 其它配置
- webcore.exception-advice.enable
系统异常与业务异常统一处理，同时转换成WebResult返回给前端
- webcore.debug.enable
开启debug模式后，WebResult中的debugMsg将会返回，反之则不会。
