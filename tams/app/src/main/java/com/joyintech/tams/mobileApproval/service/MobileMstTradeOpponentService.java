package com.joyintech.tams.mobileApproval.service;

import java.util.List;

import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.FinCashDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.StlCapacctDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileMstTradeOpponentService {
	
	@Autowired
    private MobileApprovalService mobileApprovalService;

    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;
	
	public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
        MstTradeOpponentDto dto = JsonUtils.json2Object(businessData, MstTradeOpponentDto.class);
        //2、处理数据字典
        dto.setCounterType(dto.getCounterTypeStr());
        dto.setType1(dto.getType1Str());
        dto.setType2(dto.getType2Str());
        dto.setRepYieldFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getRepYieldFlag()));
        dto.setRepIsCtrlBttom(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getRepIsCtrlBttom()));
        dto.setRepPlanProp(SysDataUtil.getNameFromDataDict(DictCode.资管计划属性, dto.getRepPlanProp()));
        dto.setRepCapiInvest(SysDataUtil.getNameFromDataDict(DictCode.资金实际投向, dto.getRepCapiInvest()));
        dto.setRepBuySruct(SysDataUtil.getNameFromDataDict(DictCode.购买结构, dto.getRepBuySruct()));
        dto.setRepManageType(SysDataUtil.getNameFromDataDict(DictCode.管理方式, dto.getRepManageType()));
        
        //账户信息
        List<StlCapacctDto> selectStlCapacct = dto.getSelectStlCapacct();
        if(selectStlCapacct!=null) {
        	for (StlCapacctDto stlCapacctDto : selectStlCapacct) {
            	stlCapacctDto.setAccountType(SysDataUtil.getNameFromDataDict(DictCode.资金账户类型, stlCapacctDto.getAccountType()));
            	stlCapacctDto.setIsOpen(SysDataUtil.getNameFromDataDict(DictCode.使用状态, stlCapacctDto.getIsOpen()));
            }
        }
//      市场机构显示为名称
        dto.setCustomerId(mobileApprovalMapper.getCustomerIdName(dto.getCustomerId()));

        //费用设置
        List<FinCashDto> selectFinCash = dto.getSelectFinCash();
        if(selectFinCash!=null) {
        	for (FinCashDto finCashDto : selectFinCash) {
            	finCashDto.setBasis(SysDataUtil.getNameFromDataDict(DictCode.计息基础,finCashDto.getBasis()));
            	finCashDto.setBasicBill(SysDataUtil.getNameFromDataDict(DictCode.计费基数_费用品种,finCashDto.getBasicBill()));
            	finCashDto.setFeeType(SysDataUtil.getNameFromDataDict(DictCode.费用品种类型,finCashDto.getFeeType()));
    		}
        }
        
        dto.setRepCapUseIndu(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getRepCapUseIndu()));
        dto.setpTypeOne(SysDataUtil.getNameFromDataDict(DictCode.客户一级类型, dto.getpTypeOne()));
        dto.setpTypeTwo(SysDataUtil.getNameFromDataDict(DictCode.客户二级类型, dto.getpTypeTwo()));
      
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
