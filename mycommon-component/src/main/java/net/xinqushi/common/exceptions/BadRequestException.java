package net.xinqushi.common.exceptions;
/**
 * @author yangli
 * @Description: 错误请求异常【自定义】，code【400】
 */
public class BadRequestException extends CommonException {

    /**
     * 
     */
    private static final long serialVersionUID = 7085359489142621913L;

    public BadRequestException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 400;
    }
}
