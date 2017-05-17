package net.xinqushi.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HTTPSender {
	
	private static Logger logger = LoggerFactory.getLogger(HTTPSender.class);
	
	public static int sendPost_JSON(String url, JSONObject obj) {
        int  result = 0 ;
		OutputStream out = null;
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置允许输出
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = conn.getOutputStream();
			// 发送请求参数
			out.write((obj.toString()).getBytes());
			// flush输出流的缓冲
			out.flush();
			result = conn.getResponseCode();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				logger.error("fail to close outputStream", ex);
			}
		}
		return result;
	}
}
