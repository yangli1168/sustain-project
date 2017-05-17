package net.xinqushi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.xinqushi.common.enums.IdentityCardCheckType;
import net.xinqushi.util.HTTPSender;

/**
 * 模拟请求工具类
 * 
 * @author yangli
 */
public class HttpSenderUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpSenderUtils.class);

	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	public static void main(String[] args) throws Exception {

		String url = "http://localhost:8066/city/cache/list";
		String url2 = "http://localhost:8066/city/mapper/list";

		Map obj = new HashMap();
		obj.put("pageSize", 5);
		obj.put("pageNum", 2);

		String result = net(url, "POST", null, obj);
		System.out.println("result = " + result);

		String net = net(url2, "GET", null, null);
		System.out.println("net = " + net);

	}

	/**
	 * 模拟请求
	 * 
	 * @param strUrl
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param headerParams
	 *            请求头参数
	 * @param requestParams
	 *            请求参数
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String net(String strUrl, String method, Map requestParams, Map headerParams) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				if (null != requestParams) {
					strUrl = strUrl + "?" + urlencode(requestParams);
				}
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
//			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (requestParams != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(requestParams));
				} catch (Exception e) {
					logger.info("fail to handle request params", e);
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return sb.toString();
	}

	// 将map型转为请求参数型
	@SuppressWarnings("rawtypes")
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}