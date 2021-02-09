package ink.akira.boot.webcore;

import com.fasterxml.jackson.annotation.JsonFilter;
import ink.akira.boot.webcore.constant.CodeEnum;

/**
 * 前后端接口返回值规范
 *
 * @author 雪行
 * @date 2021/2/9 2:44 下午
 */
@JsonFilter(value = "webResultFilter")
public class WebResult {
    public static final int SUCCESS = 0;
    public static final int FAIL    = CodeEnum.SYSTEM_ERROR.getCode();
    /**
     * 异常码。业务异常（比如权限、状态异常）为正数，系统异常（比如NPE）为负数。
     * 通用异常码：0成功，-1失败
     * 系统保留异常范围：[-100， 1000]，自定义异常码请不要使用此范围
     * @see CodeEnum
     */
    private int             code;
    /**
     * 异常信息，用于前端展示（通常为中文）
     */
    private String          message;
    /**
     * 异常信息，用于调试（通常为英文）
     */
    private String          debugMsg;
    /**
     * 接口返回数据
     */
    private Object          data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static WebResult success(Object data) {
        WebResult webResult = new WebResult();
        webResult.setData(data);
        return webResult;
    }

    public static WebResult fail(String message) {
        return fail(FAIL, message);
    }

    public static WebResult fail(CodeEnum codeEnum) {
        return fail(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static WebResult fail(int code, String message) {
        return fail(code, message, null);
    }

    public static WebResult fail(CodeEnum codeEnum, String debugMsg) {
        return fail(codeEnum.getCode(), codeEnum.getMessage(), debugMsg);
    }

    public static WebResult fail(int code, String message, String debugMsg) {
        WebResult webResult = new WebResult();
        webResult.setCode(code);
        webResult.setMessage(message);
        webResult.setDebugMsg(debugMsg);
        return webResult;
    }
}
