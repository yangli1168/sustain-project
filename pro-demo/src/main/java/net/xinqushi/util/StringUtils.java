package net.xinqushi.util;

import java.util.List;
import java.util.Set;

public class StringUtils {


	
	public static boolean isNull(String value) {
		return value == null || value.length() == 0;
	}
	
	public static boolean isNotNull(String value) {
		return value != null && value.length() > 0;
	}
	
	public static String toHexString(byte[] datas) {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < datas.length; i++ ) {
			String hex = Integer.toHexString(datas[i] & 0xFF);
			if ( hex.length() <= 1 ) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public static String toJson(List<?> list) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for ( int i = 0; i < list.size(); i++ ) {
			if ( i != 0 ) {
				sb.append(',');
			}
			Object o = list.get(i);
			if ( o instanceof String ) {
				String os = (String)o;
				// " => \", however regex expression need double, so it become complex 
				os = os.replaceAll("\"", "\\\\\"");	
				sb.append('"').append(os).append('"');
			} else {
				sb.append(list.get(i));
			}
		}
		sb.append(']');
		return sb.toString();
	}
	
	public static String toJson(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if ( !set.isEmpty() ) {
			for ( String key : set ) {
				sb.append("\"").append(key).append("\",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(']');
		return sb.toString();
	}
	
	/**
	 * 按顺序 简单的替换{1}{2}...
	 * @param src
	 * @param args
	 * @return
	 */
    public static String msgFormat(String src,String[] args){
    	if(src!=null && args!=null && args.length>0){
    		for(int i = 0 ;i < args.length;i++){
    			src = src.replaceFirst("\\{\\d+\\}", args[i]);
    		}
    	}
		return src;
    }
}
