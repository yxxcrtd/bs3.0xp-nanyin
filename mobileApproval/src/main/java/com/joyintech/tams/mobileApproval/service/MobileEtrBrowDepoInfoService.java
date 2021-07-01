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
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
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
public class MobileEtrBrowDepoInfoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	EtrBrowDepoInfoDto dto = JsonUtils.json2Object(businessData, EtrBrowDepoInfoDto.class);
    	
//		2、处理数据字典
//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
//		资产二级分类
    	dto.setAssetType1(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getAssetType1()));
//		资产三级分类
    	dto.setAssetType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getAssetType10()));
//		资产四级分类
    	dto.setAssetType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getAssetType11()));

//    	通道
    	if(dto.getChlId()!=null) {
    		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	}

//		查询组合T日，T+1日可用现金。
//		if(StringUtil.isNotEmpty(dto.getPortfolioId())&&StringUtil.isNotEmpty(dto.getCcy())){
//			if(null!=dto.getPs()&&"买入".equals(dto.getPs())){
//				Map<String,Object> param = new HashMap<>();
//				param.put("portfolioId",dto.getPortfolioId());
//				param.put("ccy",dto.getCcy());
//
//			}
//		}


//    	委托投资人员信息
//    	交易对手
    	if(null!=dto.getBussEtrInvestPersonInfoDto()&&dto.getBussEtrInvestPersonInfoDto().getCounterId()!=null) {
    		dto.getBussEtrInvestPersonInfoDto().setCounterId(mobileApprovalMapper.getCustomerName(dto.getBussEtrInvestPersonInfoDto().getCounterId()));
    	}

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
