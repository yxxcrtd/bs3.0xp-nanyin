package com.jixu.common.types;

import java.util.HashMap;

public class LotType {
	public final static String SingleLot="Y";			//单lot
	public final static String MultiLot="N";			//多lot
	
	public final static int  singleLot=0;  //单lot  期货无负债结转时调用
	public final static int  multiLot=1;  //多lot
	
	public final static HashMap<Integer, String> getLotChoices(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(singleLot, "单批次");
		optionHM.put(multiLot, "多批次");
		return optionHM;
	}
	
}
