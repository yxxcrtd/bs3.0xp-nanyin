package com.jixu.common.type;

import java.util.HashMap;

/** 
 * @ClassName 		LongShortType
 * @author  		Pan Wei 
 * @Date 			2016年8月24日 下午5:21:08 	
 * @Description		[]  
 * @return  
 */

public class LongShortType {
	public final static int longTerm = 1;
	public final static int ShortTerm = 2;
	public final static int MiniTerm = 3;
	
	private static HashMap<Integer, String> optionHM;
	
	public final static HashMap<Integer, String> getOptions(){
		if(optionHM==null){
			optionHM=new HashMap<Integer, String>();
			optionHM.put(longTerm, "买多");
			optionHM.put(ShortTerm, "卖空");
		}
		return optionHM;
	}
}
