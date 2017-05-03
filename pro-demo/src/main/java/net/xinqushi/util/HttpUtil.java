package net.xinqushi.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private static final String ENCODING = "UTF-8";
	
	private static  Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String post(String url, Map<String, String> paramsMap) {
	    CloseableHttpClient client = HttpClients.createDefault();
	    String responseText = "";
	    CloseableHttpResponse response = null;
	    try {
	        HttpPost method = new HttpPost(url);
	        if (paramsMap != null) {
	            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	            for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	                NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
	                paramList.add(pair);
	            }
	            method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
	        }
	        response = client.execute(method);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            responseText = EntityUtils.toString(entity);
	        }
	    } catch (Exception e) {
	    	logger.error("http request failed",e);
	    } finally {
	        try {
	            response.close();
	        } catch (Exception e) {
	        	logger.error("",e);
	        }
	    }
	        return responseText;
	    }
	
	public static String get(String url, Map<String, String> paramsMap) {
	    CloseableHttpClient client = HttpClients.createDefault();
	    String responseText = "";
	    CloseableHttpResponse response = null;
	    try {
	    	String getUrl = url+"?";
	        if (paramsMap != null) {
	            for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	            	getUrl += param.getKey() + "=" + URLEncoder.encode(param.getValue(), ENCODING)+"&";
	            }
	        }
	        HttpGet method = new HttpGet(getUrl);
	        response = client.execute(method);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            responseText = EntityUtils.toString(entity);
	        }
	    } catch (Exception e) {
	    	logger.error("http request failed",e);
	    } finally {
	        try {
	            response.close();
	        } catch (Exception e) {
	        	logger.error("",e);
	        }
	    }
	        return responseText;
	    }
}
