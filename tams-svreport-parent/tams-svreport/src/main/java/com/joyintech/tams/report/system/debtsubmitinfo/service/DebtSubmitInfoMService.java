/*
 * 文件名：SystemFeedback001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.debtsubmitinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.system.debtsubmitinfo.dto.DebtSubmitInfo001Dto;

/**
 * 中债系统报送信息
 * 〈功能详细描述〉
  * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see DebtSubmitInfoMService
 * @since 1.0
 */
@Service
public class DebtSubmitInfoMService extends BaseService<DebtSubmitInfo001Dto>{

    
    /**
    *
    * 取得查询数据
    *
    * @return 存在于查询条件的数据
    */
    public Page<DebtSubmitInfo001Dto> searchInfo(Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/findPage", param,
	       		DebtSubmitInfo001Dto.class);
    }
    
    /**
    *
    * 取得查询数据Detail
    *
    * @return 存在于查询条件的数据
    */
    public Page<DebtSubmitInfo001Dto> viewDetailPage(Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/findPageDetail", param,
	       		DebtSubmitInfo001Dto.class);
    }
    
	/**
	 * 查询持有信息登记中对应的中债反馈状态为中债退回的数据(选中)
	 * @param map
	 * @return
	 */
    public List<InvestorsHoldInfo001Dto> selectInvestHoldInfoFeedBackData(Map<String, Object> param) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/selectInvestHoldInfoFeedBackData", param,
	       		InvestorsHoldInfo001Dto.class);
    }
    
	/**
	 * 查询身份信息登记中的中债反馈信息为中债退回的数据(选中)
	 * @param map
	 * @return
	 */
    public List<EquityInfo001Dto> selectEquityInfoFeedBackData(Map<String, Object> param) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/selectEquityInfoFeedBackData", param,
	       		EquityInfo001Dto.class);
    }
    
	/**
	 * 查询持有信息登记中对应的中债反馈状态为中债退回的数据(全部)
	 * @param map
	 * @return
	 */
    public List<InvestorsHoldInfo001Dto> selectAllInvestHoldInfoFeedBackData(Map<String, Object> param) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/selectAllInvestHoldInfoFeedBackData", param,
	       		InvestorsHoldInfo001Dto.class);
    }
    
	/**
	 * 查询身份信息登记中的中债反馈信息为中债退回的数据(全部)
	 * @param map
	 * @return
	 */
    public List<EquityInfo001Dto> selectAllEquityInfoFeedBackData(Map<String, Object> param) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/DebtSubmitInfo/selectAllEquityInfoFeedBackData", param,
	       		EquityInfo001Dto.class);
    }
    
    
}
