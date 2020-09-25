package com.jixu.common.type;

import java.util.HashMap;

public class BookNDaysType {
	public final static int back = 0;
	public final static int front = 1;
	
	public final static HashMap<Integer, String> bookNDaysHM=new HashMap<Integer, String>();
	static{
		bookNDaysHM.put(back, "向后计提");
		bookNDaysHM.put(front, "向前计提");
	}
}
