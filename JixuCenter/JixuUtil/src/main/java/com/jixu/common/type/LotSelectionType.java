package com.jixu.common.type;

import java.util.HashMap;

public class LotSelectionType {
	public final static int MoveAvgStatCost = 1;
	public final static int LowestCost=2;
	public final static int HighestCost=3;
	public final static int FIFO = 4;
	public final static int LIFO=5;
	public final static int SpecificLot=6;
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(MoveAvgStatCost, "移动加权平均成本");
		optionHM.put(LowestCost, "最低成本");
		optionHM.put(HighestCost, "最高成本");
		optionHM.put(FIFO, "先进先出");
		optionHM.put(LIFO, "后进先出");
		optionHM.put(SpecificLot, "特定批次");
	}
}
