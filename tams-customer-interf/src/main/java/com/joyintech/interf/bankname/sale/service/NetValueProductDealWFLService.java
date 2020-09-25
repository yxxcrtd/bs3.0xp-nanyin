package com.joyintech.interf.bankname.sale.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
@Service
public class NetValueProductDealWFLService {
	/**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	public Map<String, String> save(FinNetProductDurationDto dto){
		
		Map<String, String> res = new HashMap<>();
		WorkFlowParameter<FinNetProductDurationDto> params = new WorkFlowParameter<FinNetProductDurationDto>();
		params.setPageId("FinNetProductDuration");
		params.setElementId("FinNetProductDuration_btn_save");
		params.setBusinessCode(dto.getTradeId());
		params.setServiceName("finNetProductDurationService");

		params.setBusinessType(BussinessDictCodeList.共同业务类型_净值型产品存续期交易.getValue());
		//销售系统接口数据默认审批人（第一保存）（sys_para参数：sale_handle_userid）
//		String userName = CacheUtil.getSysParam(TisConstants.SALE_HANDLE_USERID);
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "净值型产品存续期交易，产品代码" + "(" + entity.getFinprodId() 
			+ ")-交易类型 ：" + SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, entity.getDealType()));
		try{
			WorkFlowUtil.startProcess(params, "system", "system", dto.getCreateUser(), OperationType.NEW, false,dto);
			logger.info("{}插入流程成功",params.getComment());
			res.put("status", "1");
			res.put("massage", "插入流程成功"+params.getComment());
		}catch(Exception d){
			logger.error("插入流程失败:{}",params.getComment());
			d.printStackTrace();
			res.put("status", "0");
			res.put("massage", "插入流程失败"+params.getComment());
	   	}
		return res;
    }
	
	/**
	 * 净值型产品分红保存
	 * @param dto
	 * @return
	 */
	public Map<String, String> saveBonusSchemeInfo(FinNetProductBonusSchemeDto dto){
		
		Map<String, String> res = new HashMap<>();
		ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
		WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
		if(dto.getDealType().equals("bonusAmtConfirm")){//分红总金额确认
			params.setPageId("FinNetProductBonusScheme003");
			// 设置流程业务说明, entity即传入的dto
			params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
					+ "分红方案编号" + "(" + dto.getBonusPlanId() + ")-分红金额确认");
		}
		if(dto.getDealType().equals("bonusCash")){//现金红利下发
			params.setPageId("FinNetProductBonusScheme004");
			// 设置流程业务说明, entity即传入的dto
	        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
	                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-现金分红");
		}
		if(dto.getDealType().equals("bonusShareConfirm")){//再投资份额确认
			params.setPageId("FinNetProductBonusScheme005");
			// 设置流程业务说明, entity即传入的dto
	        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodId() + ")-" 
	                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-红利再投");
		}
		params.setServiceName("finNetProductBonusSchemeService");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getTradeId());
		params.setBusinessType(BussinessDictCodeList.共同业务类型_净值型产品分红方案管理.getValue());
		processParameter.setWorkFlowParameter(params);
		try{
			WorkFlowUtil.startProcess(params, "system", "system", dto.getCreateUser(), OperationType.NEW, false,dto);
			logger.info("{}插入流程成功",params.getComment());
			res.put("status", "1");
			res.put("massage", "插入流程成功"+params.getComment());
		}catch(Exception d){
			logger.error("插入流程失败:{}",params.getComment());
			d.printStackTrace();	
			res.put("status", "0");
			res.put("massage", "插入流程失败"+params.getComment());
	   	}
		return res;
	}
	
}
