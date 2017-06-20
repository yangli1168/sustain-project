package net.xinqushi.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author yangli
 * @date 2017年5月16日 - 下午10:47:54
 * @Description: 验证码生成类
 */
public class IdentifyingCodeUtils {

	private static Random random = new Random();
	
	/**
	 * 生成汉字机内码
	 */
	private static byte[] makeInnerCode(){
		
		String[] rBase = {"0","1","2","3","4","5","6","7","8","9",
				"a","b","c","d","e","f"};
		
		//生成第一位的区码
		int r1 = random.nextInt(3) + 11;
		String str_r1 = rBase[r1];
		
		//生成第二位的区码
		int r2;
		if(r1 == 13){
			r2 = random.nextInt(7);
		} else {
			r2 = random.nextInt(16);
		}
		String str_r2 = rBase[r2];
		
		//生成第一位的位码
		int r3 = random.nextInt(6) + 10;
		String str_r3 = rBase[r3];
		
		//生成第二位的位码
		int r4;
		if (r3 == 10) {
			r4 = random.nextInt(15) + 1;
		} else if (r3 == 15) {
			r4 = random.nextInt(15);
		} else {
			r4 = random.nextInt(16);
		}
		String str_r4 = rBase[r4];
		
		//拼接
		byte[] bytes = new byte[2];
		String str_r12 = str_r1 + str_r2;
		String str_r34 = str_r3 + str_r4;
		int tempLow = Integer.parseInt(str_r12, 16);
		int tempHigh = Integer.parseInt(str_r34, 16);
		bytes[0] = (byte) tempLow;
		bytes[1] = (byte) tempHigh;
		
		return bytes;
	}
	
	/**
	 * 生成汉字
	 * @param bytes
	 * @return 
	 */
	private static String makeWord(byte[] bytes){
		
		String ctmp = null;
		try {
			ctmp = new String(bytes, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("生成汉字：" + ctmp);
		
		return ctmp;
	}
	
	
	public static void main(String[] args) {
		makeIdentifyingWords(4);
		
	}
	
	/**
	 * 生成汉字验证码
	 * @param wordsLength 验证码长度
	 */
	public static String makeIdentifyingWords(int wordsLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= wordsLength; i++) {
			String str = makeWord(makeInnerCode());
			sb.append(str);
		}
		
		System.out.println("汉字验证码：" + sb);
		
		return sb.toString();
	}
}
