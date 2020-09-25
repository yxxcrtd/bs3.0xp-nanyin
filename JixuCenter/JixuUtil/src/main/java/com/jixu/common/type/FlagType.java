/**
 * 
 */
package com.jixu.common.type;

import java.util.HashMap;

/**
 * @ClassName FlagType
 * @author Pan Wei
 * @Date 2016年11月30日 上午10:47:13
 * @Description []
 */

public class FlagType {
	public final static boolean trueFlag = true;
	public final static boolean falseFlag = false;

	public final static HashMap<Boolean, String> operationsHM = new HashMap<Boolean, String>();
	static {
		operationsHM.put(trueFlag, "是");
		operationsHM.put(falseFlag, "否");
	}
}
