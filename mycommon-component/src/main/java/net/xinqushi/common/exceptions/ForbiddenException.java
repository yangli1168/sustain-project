package net.xinqushi.common.exceptions;

import net.xinqushi.common.exceptions.CommonException;

/**
 * @author yangli
 * @Description: 禁止访问异常【自定义】，code【403】
 */
public class ForbiddenException extends CommonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441348533220216691L;

	public ForbiddenException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 403;
    }
}
