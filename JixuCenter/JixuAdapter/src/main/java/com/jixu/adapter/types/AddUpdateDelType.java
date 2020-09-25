package com.jixu.adapter.types;

import java.util.HashMap;

public class AddUpdateDelType {
	public final static int add = 1;
	public final static int update = 2;
	public final static int delete=3;
	
	public final static String addStr="ADD";
	public final static String updateStr="UPDATE";
	public final static String deleteStr="DELETE";
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(add, "增加");
		optionHM.put(update, "更新");
		optionHM.put(delete, "删除");
	}
	
	public final static HashMap<Integer, String> actionMsgHM=new HashMap<Integer, String>();
	static{
		actionMsgHM.put(add, addStr);
		actionMsgHM.put(update, updateStr);
		actionMsgHM.put(delete, deleteStr);
	}
}
