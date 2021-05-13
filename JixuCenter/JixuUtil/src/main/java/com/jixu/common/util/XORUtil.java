package com.jixu.common.util;

public class XORUtil {  
//异或工具类  第一个字符串是密码   第二个字符串是要加密的字符串     加密成功后如果想要找回securityname 则再次调用该方法， 只需把securityname换成加密后的字符串即可得到
	public static String twoStringXor(String word, String securityname) {
		byte b1[] = word.getBytes();
        byte b2[] = securityname.getBytes();
        byte longbytes[],shortbytes[];
        if(b1.length>=b2.length){
            longbytes = b1;
            shortbytes = b2;
        }else{
            longbytes = b2;
            shortbytes = b1;
        }
        byte xorstr[] = new byte[longbytes.length];
        int i = 0;
        for (; i < shortbytes.length; i++) {//按照长度短的进行循环
            xorstr[i] = (byte)(shortbytes[i]^longbytes[i]);
        }
        for (;i<longbytes.length;i++){
            xorstr[i] = longbytes[i];
        }
        String res[] = {new String(xorstr).trim()};
        return res[0];
	}
	
	public static void main(String[] args) {
		XORUtil xor=new XORUtil();
//		String str=xor.twoStringXor("123", "jgH23");
//		System.out.println(str);
	}
}
