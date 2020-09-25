/*
 * 文件名：FinProductBuild001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenxiaoqiu
 * 修改时间：2019-09-29
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 预览展现dto
 * 
 * @author chenxiaoqiu
 * @version 1.0 
 * @see FinNetValueProductBuild001Dto
 * @since 1.0
 */
public class FinNetValueProductBuild005Dto extends FinProduct implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6378395144237248960L;
    
    //	期次开始日
    private Date vdateStr;
    
    //期次结束日
    private Date vdateEnd;

    private String operateType;
    
    private transient List<FinNetValueProductBuild005Dto> selectedItem; 

	public Date getVdateStr() {
		return vdateStr;
	}
	
	public String getVdateStrStr() {
		return DateUtil.format(this.getVdate());
	}

	public void setVdateStr(Date vdateStr) {
		this.vdateStr = vdateStr;
	}

	public Date getVdateEnd() {
		return vdateEnd;
	}

	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<FinNetValueProductBuild005Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<FinNetValueProductBuild005Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public void setVdateEnd(Date vdateEnd) {
		this.vdateEnd = vdateEnd;
	}
	private String  investmentCycle;
    
	private String termType;
	
	private String  openMdateStr;
	
    public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}
	  /**
     * 是否周期型
     */
    private String isCycle;


    public String getIsCycle() {
		return isCycle;
	}

	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}
	public Date getOpenVdate() {
		return openVdate;
	}

	public void setOpenVdate(Date openVdate) {
		this.openVdate = openVdate;
	}

	public Date getOpenEdate() {
		return openEdate;
	}

	public void setOpenEdate(Date openEdate) {
		this.openEdate = openEdate;
	}

	private Date openVdate;
	
	private Date openEdate;
	
	public String getOpenVdateStr() {
		return DateUtil.format(this.getOpenVdate());
	}
	
	public String getOpenMdateStr() {
		return openMdateStr;
	}

	public void setOpenMdateStr(String openMdateStr) {
		this.openMdateStr = openMdateStr;
	}

	@Override
	public String toString() {
		return "FinNetValueProductBuild005Dto [vdateStr=" + vdateStr + ", vdateEnd=" + vdateEnd + ", investmentCycle="
				+ investmentCycle + ", termType=" + termType + ", isCycle=" + isCycle + "]";
	}


 
}
