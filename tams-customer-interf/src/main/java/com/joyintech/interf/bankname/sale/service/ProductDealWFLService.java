package com.joyintech.interf.bankname.sale.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt003Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
@Service
public class ProductDealWFLService {
	/**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 业务标号生成共通
     */
    @Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;
    
	/**
	 * 中间付息提交审批
	 * @param dto
	 * @throws Exception
	 */
	public Map<String, String> save(FinProductPayInt003Dto dto) throws Exception {
		
		Map<String, String> res = new HashMap<>();
        if (StringUtil.isBlank(dto.getBussId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);
                dto.setBussId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setBussId(IDGenerator.generate());
            }
        }
        WorkFlowParameter<FinProductPayInt003Dto> params = new WorkFlowParameter<FinProductPayInt003Dto>();
        params.setPageId("FinProductPayInt003");
        params.setElementId("FinProductPayInt003_btn_save");
        params.setBusinessCode(dto.getBussId());
        params.setServiceName("finProductPayInt003Service");
        params.setBusinessType(BussinessDictCodeList.共同业务类型_中间付息管理.getValue());
        // 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "利润型产品中间付息交易，产品代码" + "(" + entity.getFinprodId() 
		       + ")-交易类型 ：" + "中间付息");
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
	 * 收益型产品产品赎回以及到期,提交审批
	 * @param dto
	 * @throws Exception
	 */
	public Map<String, String> saveExpireInfo(FinProductExpire002Dto dto) throws Exception {
		
		Map<String, String> res = new HashMap<>();
		WorkFlowParameter<FinProductExpire002Dto> params = new WorkFlowParameter<FinProductExpire002Dto>();
        params.setPageId("FinProductExpire002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        params.setServiceName("finProductExpire002Service");
        if(dto.getPs().equals(DictCodeList.共通业务类型_产品赎回.getValue())){//赎回
        	params.setBusinessType(BussinessDictCodeList.共同业务类型_产品赎回.getValue());
        	// 设置流程业务说明, entity即传入的dto
        	params.setComment(entity -> "利润型产品赎回交易，产品代码" + "(" + entity.getFinprodId() 
        	+ ")-交易类型 ：" + "产品赎回");
        }else if(dto.getPs().equals(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue())){//到期
        	params.setBusinessType(BussinessDictCodeList.共同业务类型_产品到期管理.getValue());
            // 设置流程业务说明, entity即传入的dto
    		params.setComment(entity -> "利润型产品到期交易，产品代码" + "(" + entity.getFinprodId() 
    		       + ")-交易类型 ：" + "产品到期");
        }
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
	 * 收益型产品认申购保存审批
	 * @param dto
	 * @return
	 */
	public Map<String, String> saveProEstablishInfo(FinProductEstablish002Dto dto)  throws Exception {
		
		Map<String, String> res = new HashMap<>();
		WorkFlowParameter<FinProductEstablish002Dto> params = new WorkFlowParameter<FinProductEstablish002Dto>();
		params.setPageId("FinProductEstablish002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getTradeId());
		params.setServiceName("finProductEstablish002Service");
		params.setBusinessType(BussinessDictCodeList.共同业务类型_产品成立管理.getValue());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "收益型产品存续期交易，产品代码" + "(" + entity.getFinprodId() 
			+ ")-交易类型 ：" + SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, entity.getPs()));
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
