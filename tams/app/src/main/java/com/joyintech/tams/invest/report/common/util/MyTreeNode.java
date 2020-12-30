/**  
 * @Project     tams-app  
 * @File        MyTreeNode.java  
 * @Package     com.joyintech.tams.invest.report.common.util  
 * @Version     V1.0  
 * @Date        2019年8月14日 下午4:37:49  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.invest.report.common.util;

import com.joyintech.framework.select.TreeNode;

/**  
 * @Description TODO  
 * @ClassName   MyTreeNode  
 * @Date        2019年8月14日 下午4:37:49  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */

public class MyTreeNode<T> extends TreeNode<T>{
	
	private String click;
	
	private boolean chkDisabled;
	
	

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月15日 下午4:54:33  
	 * @return chkDisabled  
	 */
	
	public boolean getChkDisabled() {
		return chkDisabled;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月15日 下午4:54:33  
	 * @param chkDisabled chkDisabled  
	 */
	
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月14日 下午4:38:33  
	 * @return click  
	 */
	
	public String getClick() {
		return click;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月14日 下午4:38:33  
	 * @param click click  
	 */
	
	public void setClick(String click) {
		this.click = click;
	}
	
	
}
