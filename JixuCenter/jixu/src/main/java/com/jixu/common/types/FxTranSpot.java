package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName FxTranSpot
 * @author Pan Wei
 * @Date 2016年10月3日 下午10:42:52
 * @Description []
 */

public class FxTranSpot {
	public final static int invest = 0;
	public final static int hedging = 1;

	public final static HashMap<Integer, String> fxTranSpotOption = new HashMap<Integer, String>();
	static {
		fxTranSpotOption.put(invest, "投资");
		fxTranSpotOption.put(hedging, "对冲风险");
	}
}
