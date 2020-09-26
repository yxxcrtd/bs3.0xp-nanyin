/*
 * 文件名：CurrentDepositBaseInfoProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.CurrentDepositCommonResult;
/**
 * 活期存款利息基本信息Processor返回共通结果
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月22日
 * @see CurrentDepositBaseInfoProcessorResult
 * @since 1.0
 */
public class CurrentDepositBaseInfoProcessorResult extends BaseEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 现金流计算规则表
	 */
	private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

	/**
	 * 现金流利率信息表
	 */
	private List<FinRateInfo> listValCashIntInfo = new ArrayList<FinRateInfo>();
	
	/**
	 * 日志
	 */
	private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
	
	/**
	 * 重载构造方法 从valuation-common中返回的result重新赋值
	 * 
	 * @param rtnResult
	 *            共通结果
	 */
	public CurrentDepositBaseInfoProcessorResult(CurrentDepositCommonResult result) {
		this.listValCashCalcRule = result.getListValCashCalcRule();
		this.listValCashIntInfo = result.getListValCashIntInfo();
		this.valInterflog = result.getValInterflog();
	}

	/**
	 * 获取 listValCashCalcRule
	 * @return listValCashCalcRule.
	 */
	public List<FinCashCalcRule> getListValCashCalcRule() {
		return listValCashCalcRule;
	}

	/**
	 * 设置 listValCashCalcRule
	 * @param listValCashCalcRule listValCashCalcRule
	 */
	public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
		this.listValCashCalcRule = listValCashCalcRule;
	}

	/**
	 * 获取 listValCashIntInfo
	 * @return listValCashIntInfo.
	 */
	public List<FinRateInfo> getListValCashIntInfo() {
		return listValCashIntInfo;
	}

	/**
	 * 设置 listValCashIntInfo
	 * @param listValCashIntInfo listValCashIntInfo
	 */
	public void setListValCashIntInfo(List<FinRateInfo> listValCashIntInfo) {
		this.listValCashIntInfo = listValCashIntInfo;
	}

	/**
	 * 获取 valInterflog
	 * @return valInterflog.
	 */
	public List<ValInterflog> getValInterflog() {
		return valInterflog;
	}	

	/**
	 * 获取 serialversionuid
	 * @return serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
