package com.jixu.common.types;

import java.util.HashMap;

public class PriceDecideType {
	public final static int market = 1;
	public final static int cost = 2;
	public final static int model = 3;
	
	public final static HashMap<Integer, String> priceDesideHM=new HashMap<Integer, String>();
	static{
		priceDesideHM.put(market, "市场");
		priceDesideHM.put(cost, "成本");
		priceDesideHM.put(model, "模型");
	}
}
