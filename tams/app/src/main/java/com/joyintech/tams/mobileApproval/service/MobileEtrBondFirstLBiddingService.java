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
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
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
public class MobileEtrBondFirstLBiddingService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	EtrBondFirstLBiddingDto dto = JsonUtils.json2Object(businessData, EtrBondFirstLBiddingDto.class);

    	//2、处理数据字典
//		代码组合
//    	dto.setPortfolioId(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getPortfolioId()));
//		资产二级分类
    	dto.setAssetType1(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getAssetType1()));
//		资产三级分类
    	dto.setAssetType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getAssetType10()));
//		资产四级分类
    	dto.setAssetType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getAssetType11()));
//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
//		缴款方式
//    	dto.setEtrPayType(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getEtrPayType()));
//		投资目的
    	dto.setInvAim(SysDataUtil.getNameFromDataDict(DictCode.投资目的, dto.getInvAim()));
//		主承销商
    	if(dto.getMainConsignee()!=null) {
    		dto.setMainConsignee(mobileApprovalMapper.getCustomerName(dto.getMainConsignee()));
    	}

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
