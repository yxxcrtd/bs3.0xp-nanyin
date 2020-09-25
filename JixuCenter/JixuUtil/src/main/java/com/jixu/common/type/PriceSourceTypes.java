package com.jixu.common.type;

import java.util.HashMap;

public class PriceSourceTypes {
	public final static int all_price=0;		//全部
	public final static int ShangJS_price=1;	//上交所
	public final static int ShenJS_price=2;		//深交所
	public final static int DaZH_price=3;		//大智慧
	public static HashMap<Integer, String> typesHM=new HashMap<Integer, String>();
	
	public final static HashMap<Integer, String> getPriceSourceTypes (boolean all) {
		if(all){
			typesHM.put(all_price, "全部");
		}
		typesHM.put(ShangJS_price, "上交所");
		typesHM.put(ShenJS_price, "深交所");
		typesHM.put(DaZH_price, "大智慧");
		return typesHM;
	}
}
