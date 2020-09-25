package com.jixu.common.types;

import java.util.HashMap;

public class NetAssetType {
	public final static int netAsset = 1;
	public final static int totalAsset =2 ;
	public final static int totalIncome = 3;
	public final static HashMap<Integer, String> netAssetHM=new HashMap<Integer, String>();
	static{
		netAssetHM.put(netAsset, "净资产");
		netAssetHM.put(totalAsset, "总净值");
		netAssetHM.put(totalIncome, "总收入");
	}
}
