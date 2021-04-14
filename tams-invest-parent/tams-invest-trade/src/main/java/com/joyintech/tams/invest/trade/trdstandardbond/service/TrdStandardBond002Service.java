/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdstandardbond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PtlSecPositionFlowMapper;
import com.joyintech.tams.common.base.dao.TrdStasecFlowDetailMapper;
import com.joyintech.tams.common.base.dao.TrdStasecFlowMapper;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond002Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond003Dto;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrdStandardBond002Service extends BaseService<TrdStandardBond002Dto> implements BaseProcessService<TrdStandardBond002Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;
    
    @Autowired
    private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;
    
    @Autowired
    private TrdStasecFlowDetailMapper trdStasecFlowDetailMapper;
        
    @Autowired
    private BaseGetBussinessId baseGetBussinessId;
    
    @Autowired
    private TrdStasecFlowMapper trdStasecFlowMapper;

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(TrdStandardBond002Dto entity, OperationType operationType) {
        //出库不考虑撤销
        if (StringUtil.equals(entity.getOperateType(), "submit")&&!StringUtil.isEmptyList(entity.getPledgeBondList())) {
            entity.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSave",
                    entity, Long.class);
        }
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(TrdStandardBond002Dto input) {
    	//1. 生成反向流水，并删除之前的质押信息
    	processOldFlow(input);
    	//2. 生成信息交易编号信息
    	setTradeIdList(input);
        //3.如果存在质押券信息，则需要重新入库
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSave",
                    input, Long.class);
        }
    }

    /**
     * 工作流中doBack
     *
     * @param input
     */
    @Override
    public void doBack(TrdStandardBond002Dto input) {
    	//1. 生成反向流水
    	processOldFlow(input);
    	//2. 交易编号信息置空
    	input.setTradeIdList(null);
    	//3. 更新留痕表
        ProcessMark newEntity;
        try {
            // 获取新的PK
        	newEntity = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
        			input);
            // 工作流ID
            newEntity.setWorkflowId(input.getWorkflowId());
            newEntity.setStatus(input.getStatus());
            newEntity.setBusinessData(JsonUtils.object2Json(input));
            // 更新业务表数据
            // 操作留痕表
            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //4. 按照原流程，撤回时还会处理指令流水
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
        	input.setIsBack(true);
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSubmit",
                    input, Long.class);
        }
    }

    /**
     * 工作流中doDelete
     *
     * @param workflowId workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        // do nothing
    }

    /**
     * 工作流审核完成doComplete
     *
     * @param input         entity
     * @param operationType operationType
     */
    @Override
    public void doComplete(TrdStandardBond002Dto input, OperationType operationType) {
    	//1. 生成反向流水
    	processOldFlow(input);
    	//2. 重新生成交易编号信息
    	setTradeIdList(input);
    	//3. 重新入表
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSave",
                    input, Long.class);
        }
    }

    /**
     * 检索可质押债券列表
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdStandardBond002Dto> selectAvaPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));

        // 多选下拉框值处理
        // 组合代码
        if (params.get("bondCode") != null) {
            params.put("bondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("bondCode")));
        } else {
            params.put("bondCodeList", new ArrayList<>());
        }
        params.put("searchDate", platformDateImpl.getSysDateStr());
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/selectAvaPledgeBondList",
                params, TrdStandardBond002Dto.class);
    }
    
    /**
     * 根据保存的交易编号生成反向的交易流水
     * @param entity
     */
    private void processOldFlow(TrdStandardBond002Dto entity) {
    	if(entity.getTradeIdList()!=null&&entity.getTradeIdList().size()>0) {
    		//1. 获取之前所有的流程中流水
    		List<PtlSecPositionFlow> secFlows = new ArrayList<>();
    		Map<String,Object> param = new HashMap<>();
    		param.put("holdingType", DictCodeList.持仓类型_质押.getValue());
    		for(String tradeId:entity.getTradeIdList()) {
    			param.put("finprodId", tradeId);
    			secFlows.addAll(ptlSecPositionFlowMapper.selectSecFlow(param));
    			//删除质押信息
    			trdStasecFlowMapper.deleteByPrimaryKey(tradeId);
    			trdStasecFlowDetailMapper.deleteBySeq(tradeId);
    		}
    		//2. 流水金额反向
    		List<PtlSecPositionFlowDto> ptlSecPositionFlowDtos = new ArrayList<>();
    		for(PtlSecPositionFlow flow:secFlows) {
    			PtlSecPositionFlowDto dto = new PtlSecPositionFlowDto();
    			BeanUtils.copyProperties(flow, dto);
    		    ptlSecPositionFlowDtos.add(dto);
    		}
    		//3. 提交处理
    		if(ptlSecPositionFlowDtos.size()>0) {
                PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
                ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlowDtos);
                restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                        ptlPositionHoldDto, Boolean.class);
    		}
    	}
    }
    
    /**
     * 给实体类设置 tradeIdList
     * @param entity
     */
    public void setTradeIdList(TrdStandardBond002Dto entity) {
    	List<String> tradeIdList = new ArrayList<>();
    	if (!StringUtil.isEmptyList(entity.getPledgeBondList())) {
    		//结果中根据"组合代码,产品代码"进行分组设置tradeId
    		//1. 获取所有分组
    		Map<String, List<TrdStandardBond002Dto>> dtoMap = entity.getPledgeBondList().stream().collect(JoyinCollectors.groupingBy(x -> x.getPortfolioId() + "," + x.getSecAcctId()));
    		//2. 根据分组，生成所有的tradeId
    		for(int i=0;i<dtoMap.keySet().size();i++) {
                BussinessCodeParameter parameter = new BussinessCodeParameter();
                parameter.setSeqNo("9999");
                String seqNo = baseGetBussinessId.buildBussinessCode(parameter);
                tradeIdList.add(seqNo);
    		}
    	}
		//3. 设置 tradeIdList
		entity.setTradeIdList(tradeIdList);
    }
}
