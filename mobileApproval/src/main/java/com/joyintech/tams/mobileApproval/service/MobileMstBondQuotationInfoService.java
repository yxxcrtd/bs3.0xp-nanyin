/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
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
public class MobileMstBondQuotationInfoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	MstBondQuotationInfoDto dto = JsonUtils.json2Object(businessData, MstBondQuotationInfoDto.class);
        //2、处理数据字典
    	dto.setValueSource(dto.getValueSourceStr());
    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
