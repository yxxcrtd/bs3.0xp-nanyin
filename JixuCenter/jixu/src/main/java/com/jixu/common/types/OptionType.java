package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName OptionType
 * @author Pan Wei
 * @Date 2017年8月6日 上午12:55:08
 * @Description []
 */

public class OptionType {
	public final static int put = 0;
	public final static int call = 1;

	public final static int zgBank = 0;
	public final static int netAsset = 1;

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(put, "认购期权");
		optionHM.put(call, "认沽期权");
	}

	public final static HashMap<Integer, String> portProductTypesHM = new HashMap<Integer, String>();
	static {
		portProductTypesHM.put(netAsset, "净值型");
		portProductTypesHM.put(zgBank, "预期收益型");
	}
}
