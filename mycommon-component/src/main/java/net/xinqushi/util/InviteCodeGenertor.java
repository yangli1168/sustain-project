package net.xinqushi.util;

import java.util.Random;

/**
 * 邀请码生成器，算法原理：<br/>
 * 1) 获取id
 * 2) 使用自定义进制转换，自定义进制不含o,补齐用 ，不含数字0，1，与字母o，l难以区分（ 即59进制）
 * 3) 若转换后字符串不足4位则进行补齐：
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'X'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。<br/>
 */
public class InviteCodeGenertor {
	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
    private static final char[] r=new char[]{'C','q','L', 'w','B', 'e', 'H','8','W','Y', 'O','a', 'R','s', '2', 'E','d','U','V',
    	'z', 'x', '9', 'Q','c', 'T','Z','7', 'p','o', '5', 'i', 'k', 'G','M','3', 'm', 'K','j', 'u', 'f', 'P','S','r', '4', 'J',
    	'v', 'F','N','y', 'l', 't', 'n', 'A','D','6', 'b', 'g', 'I','h'};
 
    /** (不能与自定义进制有重复) */
    private static final char b='X';
 
    /** 进制长度 */
    private static final int binLen=r.length;
 
    /** 序列最小长度 */
    private static final int minlen=4;
	
    /**
     * 根据ID生成邀请码
     * @param id ID
     * @return 邀请码
     */
    public static String IDToInviteCode(long id) {
    	int maxByteLen = 32;
        char[] buf=new char[maxByteLen];
        int charPos=maxByteLen;
 
        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (maxByteLen - charPos));
        // 不够长度的自动随机补全
        if(str.length() < minlen) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < minlen - str.length(); i++) {
            sb.append(r[rnd.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }
 
    /**
     *根据邀请码码反向获取id
     * @param code
     * @return
     */
    public static long InviteCodeToID(String code) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }
    
    public static void main(String args[]){
    	String a = IDToInviteCode(85728);
    	System.out.println(a);
    }
}
