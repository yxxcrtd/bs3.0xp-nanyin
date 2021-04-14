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
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service.PtlChlFundAllocation002Service;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobilePtlChlFundAllocationService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;

    @Autowired
	private PtlChlFundAllocation002Service ptlChlFundAllocation002Service;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	PtlChlFundAllocationDto dto = JsonUtils.json2Object(businessData, PtlChlFundAllocationDto.class);
    	List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList=dto.getBussTrdTransferOrder();
    	
    	//2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));

		//付款账户余额查询
//		if(null!=dto.getPortfolioId()&&null!=dto.getMyAcctNo()&&null!=dto.getSettleDate()
//				&&null!=dto.getCcy()&&null!=dto.getPayType()&&null!=dto.getChlId()){
//
//			Map<String,Object> param = new HashMap<>();
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("acctId",dto.getMyAcctNo());
//			param.put("cdate", DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//			param.put("ccy",dto.getCcy());
//			param.put("payType",dto.getPayType());
//			param.put("chlId",dto.getChlId());
//			param.put("calcAcctName",dto.getMyAcctNo());
//
//			BigDecimal result = ptlChlFundAllocation002Service.getAmount(param);
//			dto.setMyAcctAmount(result);
//
//		}

		dto.setCcy(dto.getCcyStr());
    	dto.setPayType(dto.getPayTypeStr());
    	
    	if(bussTrdTransferOrderDtoList.size()>0) {
    		BussTrdTransferOrderDto bdto=dto.getBussTrdTransferOrder().get(0);
    		dto.setMyAcctNo(bdto.getPayAcctName());
        	dto.setCounterAcctNo(bdto.getRecAcctName());

			for (BussTrdTransferOrderDto vo : dto.getBussTrdTransferOrder()) {
				vo.setCcy(vo.getCcyText());
				vo.setPayType(vo.getPayTypeText());
				vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, vo.getIsNeedSend()));
		//		vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
			}
        	
    	}
    	
    	if(dto.getPrinAmt()==null) {
    		dto.setPrinAmt(new BigDecimal(0.0));
    	}
    	if(dto.getIntAmt()==null) {
    		dto.setIntAmt(new BigDecimal(0.0));
    	}
    	
    	dto.setChlName(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
