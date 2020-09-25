/*
 * 文件名：PrdIndexCalculateProcessorIntoResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;

/**
 * 信息披露-产品指标计算(数据插入)-processor返回值
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see DisReportProdIndexIntoResult
 * @since 1.0
 */
public class DisReportProdIndexIntoResult extends NeedToWriteEntity implements Serializable {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 产品指标数据
	 */
	private List<DisReportProdIndexResult> repProdIndexList = new ArrayList<DisReportProdIndexResult>();

	/**
     * 参数配置
     */
    private Map<String,Object> param;
    
    public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
	public List<DisReportProdIndexResult> getRepProdIndexList() {
		return repProdIndexList;
	}

	public void setRepProdIndexList(List<DisReportProdIndexResult> repProdIndexList) {
		this.repProdIndexList = repProdIndexList;
	}

}
