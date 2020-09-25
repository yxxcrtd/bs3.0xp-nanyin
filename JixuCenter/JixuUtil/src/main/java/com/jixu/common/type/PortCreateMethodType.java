package com.jixu.common.type;

import java.util.HashMap;

/** 
 * @ClassName 		PortCreateMethodType
 * @author  		Pan Wei 
 * @Date 			2018年4月15日 下午2:05:40 	
 * @Description		[增值系统的投资组合创建的方式 1.系统创建 2.估值表创建	]  
 * @return  
 */

public class PortCreateMethodType {
	//估值系统创建
	public final static int account=1;
	
	//估值表上传创建
	public final static int valuationReport=2;
	
	public static HashMap<Integer, String> methodTypeHM;
	
	public final static HashMap<Integer, String> getOptions() {
		if(methodTypeHM==null){
			methodTypeHM=new HashMap<Integer, String>();
			methodTypeHM.put(account, "估值系统");
			methodTypeHM.put(valuationReport, "估值表上传");
		}
		return methodTypeHM;
	}
}
