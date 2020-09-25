package com.jixu.adapter.types;

import java.util.HashMap;

public class ActiveType {
	public final static int active = 1;
	public final static int inActive = 0;
	public final static HashMap<Integer, String> activeTypeHM=new HashMap<Integer, String>();
	static{
		activeTypeHM.put(active, "有效");
		activeTypeHM.put(inActive, "非有效");
	}
}
