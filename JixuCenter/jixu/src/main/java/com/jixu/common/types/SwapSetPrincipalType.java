package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName SwapSetPrincipalType
 * @author Pan Wei
 * @Date 2018年8月21日 上午11:05:02
 * @Description [互换的现金结转类型]
 */

public class SwapSetPrincipalType {
	public final static int NoneSwap = 0;
	public final static int FrontSwap = 1;
	public final static int BackSwap = 2;
	public final static int BothSwap = 3;
	public final static int principalExchange = 4;//本金交换
	
	public final static int NCA_SETTLE = 0;//非差额清算
	public final static int CA_SETTLE = 1;//差额清算
	

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(NoneSwap, "无/不结转");
		optionHM.put(FrontSwap, "前端结转");
		optionHM.put(BackSwap, "后端结转");
		optionHM.put(BothSwap, "前后和后端结转");
	}
	
	public final static HashMap<Integer, String> optionSwapFXHM = new HashMap<Integer, String>();
	static {
		optionSwapFXHM.put(NoneSwap, "远期/远期");
		optionSwapFXHM.put(FrontSwap, "即期/远期");
		optionSwapFXHM.put(BothSwap, "即期/即期");
	}
}
