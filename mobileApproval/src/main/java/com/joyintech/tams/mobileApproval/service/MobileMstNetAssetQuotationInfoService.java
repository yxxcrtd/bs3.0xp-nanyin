/**
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
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstnetassetquotationinfo.dto.MstNetAssetQuotationInfo001Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
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
public class MobileMstNetAssetQuotationInfoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	MstNetAssetQuotationInfo001Dto dto = JsonUtils.json2Object(businessData, MstNetAssetQuotationInfo001Dto.class);
        //2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
    	dto.setProfitType(SysDataUtil.getNameFromDataDict(DictCode.收益类型, dto.getProfitType()));
    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
