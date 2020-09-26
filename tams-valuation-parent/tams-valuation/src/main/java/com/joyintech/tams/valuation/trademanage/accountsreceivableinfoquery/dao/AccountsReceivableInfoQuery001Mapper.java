/*
 * 文件名：AccountsReceivableInfoQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.dto.AccountsReceivableInfoQuery001Dto;


/**
 * 应收款项信息查询
 * @author zhangzhao
 * @version 1.0 2018年10月9日
 * @see AccountsReceivableInfoQuery001Mapper
 * @since 1.0
 */
public interface AccountsReceivableInfoQuery001Mapper {

	/**
     * 标的资产下拉框数据选择
     * @param pageParam
     * @return
     */
	List<SelectOptionDto> getBdFinprodId(Map<String, Object> pageParam);
	
	
	/**
     * 根据条件查询
     * @param params
     * @return
     */
	 List<AccountsReceivableInfoQuery001Dto>  queryPageList(Map<String, Object> params);
	 
    
    
}
