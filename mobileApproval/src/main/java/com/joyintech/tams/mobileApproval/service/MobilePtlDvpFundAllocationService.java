/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobilePtlDvpFundAllocationService {

    @Autowired
    private MobileApprovalService mobileApprovalService;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	PtlDvpFundAllocationDto dto = JsonUtils.json2Object(businessData, PtlDvpFundAllocationDto.class);
    	
    	List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList=dto.getBussTrdTransferOrder();
    	
    	//2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
    	dto.setCcy(dto.getCcyStr());
    	dto.setPayType(dto.getPayTypeStr());
    	
    	if(bussTrdTransferOrderDtoList.size()>0) {
    		BussTrdTransferOrderDto bdto=dto.getBussTrdTransferOrder().get(0);
    		dto.setMyAcctNo(bdto.getPayAcctName());
        	dto.setCounterAcctNo(bdto.getRecAcctName());
        	
        	MobileUtils.dealBussTrdTransferOrder(bussTrdTransferOrderDtoList);
        	
    	}
    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
