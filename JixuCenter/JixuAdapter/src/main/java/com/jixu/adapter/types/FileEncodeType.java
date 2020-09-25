package com.jixu.adapter.types;

import java.util.HashMap;

/**
 * @ClassName FileEncodeType
 * @author Pan Wei
 * @Date 2017年3月13日 下午3:22:03
 * @Description []
 */

public class FileEncodeType {
	public final static int gb2312 = 0;
	public final static int gbk = 1;
	public final static int utf8 = 2;
	public final static int utf16 = 3;
	public final static int iso = 4;

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(gb2312, "gb2312");
		optionHM.put(gbk, "GBK");
		optionHM.put(utf8, "UTF8");
		optionHM.put(utf16, "UTF16");
		optionHM.put(iso, "ISO");
	}
}
