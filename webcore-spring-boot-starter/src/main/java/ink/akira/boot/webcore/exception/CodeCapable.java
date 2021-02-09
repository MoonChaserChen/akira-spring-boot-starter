package ink.akira.boot.webcore.exception;

/**
 * 能获取Code
 *
 * @author 雪行
 * @date 2021/2/9 2:57 下午
 */
public interface CodeCapable {
    /**
     * 返回Code
     * @see CodeCapableBizException
     *
     * @return Code
     */
    int getCode();
}
