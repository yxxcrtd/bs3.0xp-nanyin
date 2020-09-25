package com.jixu.adapter.types;

import java.util.HashMap;

public class AdapterRunStatus {
	public final static int stop=1;
	public final static int running=2;
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(stop, "停止");
		optionHM.put(running, "运行中");
	}
	
	public final static boolean success=true;
	public final static boolean failed=false;
	
	public final static HashMap<Boolean, String> successFailedHM=new HashMap<Boolean, String>();
	static{
		successFailedHM.put(success, "成功");
		successFailedHM.put(failed, "失败");
	}
}
