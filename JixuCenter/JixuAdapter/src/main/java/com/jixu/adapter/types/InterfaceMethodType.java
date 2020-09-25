package com.jixu.adapter.types;

import java.util.HashMap;

public class InterfaceMethodType {
	public final static int manual=1;
	public final static int auto=2;
	
	public final static HashMap<Integer, String> interfaceMethodTypeHM=new HashMap<Integer, String>();
	static{
		interfaceMethodTypeHM.put(manual, "手动");
		interfaceMethodTypeHM.put(auto, "自动");
	}
}
