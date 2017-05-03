package net.xinqushi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.xinqushi.common.enums.IdentityCardCheckType;

/**
 * 身份证验证类
 * 
 * @author yangli
 */
public class IdentityCardValidate {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	public static final String APPKEY = "6ce457c3aeae929450c56fcbfc6f8584";

	/**
	 * 实名认证方法
	 * @param appkey
	 * @param realname
	 * @param idcard
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean identification(String appkey, String realname, String idcard) {
		Boolean flag = false;
		String result = null;
		String url = "http://op.juhe.cn/idcard/query";
		Map params = new HashMap();// 请求参数
		params.put("idcard", idcard);
		params.put("realname", realname);
		params.put("key", appkey);

		JSONObject object = null;
		try {
			result = net(url, params, "GET");
			object = JSONObject.parseObject(result);
			if (object.getIntValue("error_code") == 0) {
				System.out.println(object.get("result"));
				flag = true;
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {

//		String json = "{\"reason\":\"成功1\",\"result\":{	\"realname\":\"董好帅\",\"idcard\":\"330329199001020012\",\"res\":1},	\"error_code\":0}";
//		JSONObject jo = JSON.parseObject(json);
//		System.out.println("reason = " + jo.getString("reason"));
//		System.out.println("result = " + jo.getJSONObject("result"));
//		System.out.println("error_code = " + jo.getInteger("error_code"));
//		System.out.println("result.realname = " + jo.getJSONObject("result").getString("realname"));
	
		System.out.println(identification(APPKEY, "苏莉", "500383198701176829"));
	}

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
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
		return rs;
	}

	// 将map型转为请求参数型
	@SuppressWarnings("rawtypes")
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}