package com.jixu.common.types;

import java.util.HashMap;

import com.jixu.common.type.AssetTypes;

public class ValuationType {
	public final static int FV = 1;
	public final static int AMORTIZE=2;
	
	public final static int NOT_VALUATION=3;  //不估值
	public final static int CASHFLOW_VALUATION=4;  //未来现金流折价
	
	public final static int VALSIDE_SHORT=0;//估值方向-空方
	public final static int VALSIDE_LONG=1;//估值方向-多方
	
	private static HashMap<Integer, String> valuationHM;
	private static HashMap<Integer, String> valuationSideHM;
	
	public final static HashMap<Integer, String> getOptions(String AssetType){
		if(AssetType.equals(AssetTypes.Bond)){
			valuationHM=new HashMap<Integer, String>();
			valuationHM.put(FV, "公允价值法");
			valuationHM.put(AMORTIZE, "摊余成本法");
		}else if(AssetType.equals(AssetTypes.FORWARD_CURRENCY)||AssetType.equals(AssetTypes.SWAP_INTEREST)||AssetType.equals(AssetTypes.OPTION)){
			valuationHM=new HashMap<Integer, String>();
			valuationHM.put(NOT_VALUATION, "不估值");
			valuationHM.put(CASHFLOW_VALUATION, "模型法");
			valuationHM.put(FV, "市价法");
		}else{
			valuationHM=new HashMap<Integer, String>();
			valuationHM.put(NOT_VALUATION, "不估值");
			valuationHM.put(CASHFLOW_VALUATION, "未来现金流折价");
			valuationHM.put(FV, "公允价值法");
		}
		return valuationHM;
	}
	
	public final static HashMap<Integer, String> getOptionSide(){
		valuationSideHM=new HashMap<Integer, String>();
		valuationSideHM.put(VALSIDE_LONG, "多方");
		valuationSideHM.put(VALSIDE_SHORT, "空方");
		return valuationSideHM;
	}
}
