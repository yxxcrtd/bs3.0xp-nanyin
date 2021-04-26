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
import com.joyintech.tams.common.base.entity.PtlValParam;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
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
public class MobilePtlManagementInfoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
        PtlManagementInfoDto dto = JsonUtils.json2Object(businessData, PtlManagementInfoDto.class);
        //2、处理数据字典
        dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
        dto.setProfitType(SysDataUtil.getNameFromDataDict(DictCode.收益类型, dto.getProfitType()));
        dto.setIsGuarantee(dto.getIsGuaranteeStr());
        dto.setOpenType(dto.getOpenTypeStr());
        dto.setRaiseType(dto.getRaiseTypeStr());
        dto.setInvestNature(dto.getInvestNatureStr());
        //资金账户信息
        List<FundAccountInfoDto> d01 = dto.getFundAccountInfoDto();
        for(FundAccountInfoDto vo :d01){
            vo.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, vo.getCcy()));
            vo.setAcctType(SysDataUtil.getNameFromDataDict(DictCode.资金账户类型, vo.getAcctType()));
        }
        //证券账户信息
        List<SecuritiesAccountInfoDto> d02 = dto.getSecuritiesAccountInfoDto();
        for(SecuritiesAccountInfoDto vo :d02){
            vo.setSecAcctSource(vo.getSecAcctSourceStr());
            vo.setRegistOrg(vo.getRegistOrgStr());

        }
        //估值信息
        PtlValParam d03 = dto.getPtlValParam();
        d03.setUnitvalueMode(SysDataUtil.getNameFromDataDict(DictCode.尾数处理方式, d03.getUnitvalueMode()));
        d03.setRatevalueMode(SysDataUtil.getNameFromDataDict(DictCode.尾数处理方式, d03.getRatevalueMode()));
        d03.setMilionProfitDecMode(SysDataUtil.getNameFromDataDict(DictCode.尾数处理方式, d03.getMilionProfitDecMode()));
        d03.setIsCountingCurInt(SysDataUtil.getNameFromDataDict(DictCode.是否, d03.getIsCountingCurInt()));

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
