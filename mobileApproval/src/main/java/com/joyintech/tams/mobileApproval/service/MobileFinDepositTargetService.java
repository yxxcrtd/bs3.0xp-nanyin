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
import com.joyintech.tams.invest.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.invest.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobileFinBondTargetAssetService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileFinDepositTargetService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	FinDepositTargetDto dto = JsonUtils.json2Object(businessData, FinDepositTargetDto.class);
    	
    	//2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
//    	获取交易日历
    	dto.setCalendarName(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
//		获取发行人名称
    	dto.setIssuerName(mobileApprovalMapper.getCustomerName(dto.getIssuer()));
//		发行方式    	
    	dto.setIssueType(SysDataUtil.getNameFromDataDict(DictCode.发行方式, dto.getIssueType()));
//		境内外
    	dto.setBlnArea(SysDataUtil.getNameFromDataDict(DictCode.境内外, dto.getBlnArea()));
//    	资产来源
    	dto.setAssetSource(SysDataUtil.getNameFromDataDict(DictCode.资产来源, dto.getAssetSource()));
//    	获取担保人名称
    	dto.setSponsor(mobileApprovalMapper.getCustomerName(dto.getSponsor()));
//------------------------------------------------------- 	
    	
//		计息要素    	
//    	息票品种
    	dto.getFinInterestFactorDto().setCouponSpecies(SysDataUtil.getNameFromDataDict(DictCode.息票品种, dto.getFinInterestFactorDto().getCouponSpecies()));
//    	利率类型
    	dto.getFinInterestFactorDto().setIntType(SysDataUtil.getNameFromDataDict(DictCode.利率类型, dto.getFinInterestFactorDto().getIntType()));
//    	利息分配方式
    	dto.getFinInterestFactorDto().setBasisStyle(SysDataUtil.getNameFromDataDict(DictCode.利息分配方式, dto.getFinInterestFactorDto().getBasisStyle()));
//    	基准利率调整规则
    	dto.getFinInterestFactorDto().setResetType(SysDataUtil.getNameFromDataDict(DictCode.重置类型_基准利率调整规则, dto.getFinInterestFactorDto().getResetType()));
//    	延后时间单位
    	dto.getFinInterestFactorDto().setObserveBefUnit(SysDataUtil.getNameFromDataDict(DictCode.提前观察单位_延后时间单位, dto.getFinInterestFactorDto().getObserveBefUnit()));
//		浮动利率
    	dto.getFinInterestFactorDto().setBenchmarkId(SysDataUtil.getNameFromDataDict(DictCode.利率行情, dto.getFinInterestFactorDto().getBenchmarkId()));
//---------------------------------------------------------------------    	
    	
    	
//		付息信息
//    	营业日准则
    	dto.getFinPayInterestStdInfoDto().setPayTdayRule(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, dto.getFinPayInterestStdInfoDto().getPayTdayRule()));
//    	付息频率
    	dto.getFinPayInterestStdInfoDto().setPayCycle(SysDataUtil.getNameFromDataDict(DictCode.频率, dto.getFinPayInterestStdInfoDto().getPayCycle()));
//    	推算方式
    	dto.getFinPayInterestStdInfoDto().setCalcRule(SysDataUtil.getNameFromDataDict(DictCode.推算方式, dto.getFinPayInterestStdInfoDto().getCalcRule()));
//-------------------------------------------------------------------
    	
    	
    	
//    	资产/负债类别
    	dto.getBussRepDepositInfoDto().setRepAssetType(SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, dto.getBussRepDepositInfoDto().getRepAssetType()));
// 		交易流通场所
    	dto.getBussRepDepositInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, dto.getBussRepDepositInfoDto().getRepTradeMarket()));
//    	存款类型
    	dto.getBussRepDepositInfoDto().setRepDepoType(SysDataUtil.getNameFromDataDict(DictCode.存款类型, dto.getBussRepDepositInfoDto().getRepDepoType()));
    	
    	
    	//组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
