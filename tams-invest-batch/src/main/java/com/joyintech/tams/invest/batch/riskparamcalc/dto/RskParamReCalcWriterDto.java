/*
 * 文件名：RskParamReCalcWriterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jjpei
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;


/**
 * 批处理-指标参数重新更新-WriterDto
 * 
 * @author jjpei
 * @version 1.0 2020年4月17日
 * @see RskParamReCalcWriterDto
 * @since 1.0
 */
public class RskParamReCalcWriterDto extends NeedToWriteEntity {

    /**
     * 参数值计算流水
     */
    private List<RskIndicatorParaCalcdetail> paraCalList;

    /**
     * 取得 参数值计算流水
     * 
     * @return 参数值计算流水
     */
	public List<RskIndicatorParaCalcdetail> getParaCalList() {
		return paraCalList;
	}

	/**
	 * 设置 参数值计算流水
	 * 
	 * @param paraCalList 参数值计算流水
	 */
	public void setParaCalList(List<RskIndicatorParaCalcdetail> paraCalList) {
		this.paraCalList = paraCalList;
	}
}
