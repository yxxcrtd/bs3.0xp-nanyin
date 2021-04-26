/*
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileEtrBoutRepoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	EtrBoutRepoDto dto = JsonUtils.json2Object(businessData, EtrBoutRepoDto.class);
    	
    	
//		2、处理数据字典
//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, dto.getPs()));
//		期限品种
    	dto.setTermType(SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, dto.getTermType()));
//		价格模式
    	dto.setPriceModel(SysDataUtil.getNameFromDataDict(DictCode.价格模式, dto.getPriceModel()));
//		清算速度
    	dto.setSettleSpreed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpreed()));
//    	获取交易日历
    	if(dto.getCalendarId()!=null) {
    		dto.setCalendarId(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
    	}
//    	证券账户
    	if(dto.getSecuritiesCust()!=null) {
    		dto.setSecuritiesCust(mobileApprovalMapper.getSecAcctName(dto.getSecuritiesCust()));
    	}
//    	通道
    	if(dto.getChlId()!=null) {
    		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	}
    	
    	
//    	委托投资人员信息
//    	交易对手
    	if(null!=dto.getEtrInvestPersonInfo()&&dto.getEtrInvestPersonInfo().getCounterId()!=null) {
    		dto.getEtrInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getEtrInvestPersonInfo().getCounterId()));
    	}

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
