package net.xinqushi.common.exceptions;
/**
 * 自定义版本异常类
 * @author yangli
 */
public class VersionException extends CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 新增版本下载路径*/
	private String downloadUrl;

	public VersionException(String message) {
		super(message);
	}
	
	public int getStatusCode() {
        return 408;
    }
	
	/** 新增包含下载路径的构造方法*/
	public VersionException(String message, String downloadUrl) {
		super(message);
		this.downloadUrl = downloadUrl;
	}
	
	

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
}
