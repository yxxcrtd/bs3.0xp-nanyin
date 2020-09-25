package com.joyintech.tams.invest.product.productduration.addreduce.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.product.addreduce.FinProductAddReduceDto;
import com.joyintech.tams.invest.product.productduration.addreduce.validator.FinProductAddReduce001DtoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.dto
 * @Description: 强增强减管理检索的grid数据模型
 * @date Date : 2018年09月07日 9:19
 */
@PageId("FinProductAddReduce001")
@ValidLogic(targetClass = FinProductAddReduce001DtoValidator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class FinProductAddReduce001Dto extends FinProductAddReduceDto implements ProcessEntity {
	
    /**
     * 汇总交易编号
     */
    private String totleTradeId;
    /**
     * 获取 psString
     *
     * @return psString
     */
    public String getPsString() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    
    /**
     * 获取 effectStatusString
     *
     * @return effectStatusString
     */
    public String getEffectStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, super.getEffectStatus());
    }

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

}
