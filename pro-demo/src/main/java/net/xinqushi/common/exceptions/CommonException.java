package net.xinqushi.common.exceptions;
/**
 *	封装一般异常 
 * @author yangli
 */
public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3180887004308718968L;

	public CommonException(String message) {
		super(message);
	}

	public int getStatusCode() {
		return 500;
	}
}
