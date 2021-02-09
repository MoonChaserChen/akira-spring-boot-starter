package ink.akira.boot.webcore.constant;

/**
 * 异常码
 *
 * @author 雪行
 * @date 2021/2/9 3:00 下午
 */
public enum CodeEnum {
    /**
     * 异常码
     */
    SYSTEM_ERROR(-1, "系统异常"),
    NPE(-2, "系统异常"),
    PARAM_ERROR(-3, "参数异常"),

    BIZ_ERROR(1, "业务异常"),
    FREQUENT_OPERATION(2, "操作过于频繁"),
    ;

    /**
     * 通用异常码，范围：[-100， 1000]
     * @see ink.akira.boot.webcore.WebResult
     */
    private int code;
    private String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
