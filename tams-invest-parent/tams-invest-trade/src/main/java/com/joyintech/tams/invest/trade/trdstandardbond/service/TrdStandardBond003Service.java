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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.common.base.dao.PtlSecPositionFlowMapper;
import com.joyintech.tams.common.base.dao.TrdStasecFlowDetailMapper;
import com.joyintech.tams.common.base.dao.TrdStasecFlowMapper;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond003Dto;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrdStandardBond003Service extends BaseService<TrdStandardBond003Dto> implements BaseProcessService<TrdStandardBond003Dto> {

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
    public void doInsert(TrdStandardBond003Dto entity, OperationType operationType) {
    	//1. 只有提交时会处理
    	if ("submit".equals(entity.getOperateType())&&!StringUtil.isEmptyList(entity.getOutBondList())) {
    		//2. 设置新的tradeId
    		//setTradeIdList(entity);
    		//3. 调用微服务入流水表
            Long insertCount = 0L;
            entity.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/outSave",
            		entity, Long.class);
    	}
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(TrdStandardBond003Dto input) {
    	//1. 删除之前的流水表
    	processOldFlow(input);
    	//2. 设置新的tradeId
    	setTradeIdList(input);
    	//3. 如果非空，需要入流水表
    	if(!StringUtil.isEmptyList(input.getOutBondList())) {
            Long insertCount = 0L;
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/outSave",
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
    
    @Override
    public void doBack(TrdStandardBond003Dto entity) {
    	//1. 生成反向流水
    	processOldFlow(entity);
    	//2. 交易编号清空
    	entity.setTradeIdList(null);
    	//3. 更新留痕表
        ProcessMark newEntity;
        try {
            // 获取新的PK
        	newEntity = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
            		entity);
            // 工作流ID
            newEntity.setWorkflowId(entity.getWorkflowId());
            newEntity.setStatus(entity.getStatus());
            newEntity.setBusinessData(JsonUtils.object2Json(entity));
            // 更新业务表数据
            // 操作留痕表
            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 工作流审核完成doComplete
     *
     * @param input         entity
     * @param operationType operationType
     */
    @Override
    public void doComplete(TrdStandardBond003Dto input, OperationType operationType) {
    	//1. 删除之前的流水表
    	processOldFlow(input);
    	//2. 设置新的tradeId
    	setTradeIdList(input);
    	//3. 调用微服务进行处理
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getOutBondList())) {
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/outSave",
                    input, Long.class);
        }
    }

    /**
     * 质押券出库信息一览查询
     *
     * @param pageParam 查询参数
     * @return List<TrdStandardBond003Dto>
     * @see
     * @since 1.0
     */
    public List<TrdStandardBond003Dto> selectOutListData(Map<String, Object> pageParam) {
        pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("isPreciseSearch", true);

        // 组合代码
        if (pageParam.get("portfolioId") != null) {
            pageParam.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        } else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        // 债券对应金融产品代码
        if (pageParam.get("bondCode") != null) {
            pageParam.put("bondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("bondCode")));
        } else {
            pageParam.put("bondCodeList", new ArrayList<>());
        }
        if (pageParam.get("initBondCode") != null) {
            pageParam.put("initBondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("initBondCode")));
        } else {
            pageParam.put("initBondCodeList", new ArrayList<>());
        }
        if (pageParam.get("initPortfolioId") != null) {
            pageParam.put("initPortfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("initPortfolioId")));
        } else {
            pageParam.put("initPortfolioIdList", new ArrayList<>());
        }
        // 交易场所
        if (pageParam.get("tradePlace") != null) {
            pageParam.put("tradePlaceList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("tradePlace")));
        } else {
            pageParam.put("tradePlaceList", new ArrayList<>());
        }
        // 证券账户
        if (pageParam.get("secAcctId") != null) {
            pageParam.put("secAcctIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("secAcctId")));
        } else {
            pageParam.put("secAcctIdList", new ArrayList<>());
        }
        pageParam.put("searchDate", platformDateImpl.getSysDateStr());
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/selectOutListData",
                pageParam, TrdStandardBond003Dto.class);
    }
    
    /**
     * 根据保存的交易编号生成反向的交易流水
     * @param entity
     */
    private void processOldFlow(TrdStandardBond003Dto entity) {
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
    public void setTradeIdList(TrdStandardBond003Dto entity) {
    	List<String> tradeIdList = new ArrayList<>();
    	if (!StringUtil.isEmptyList(entity.getOutBondList())) {
    		//结果中根据"组合代码,产品代码"进行分组设置tradeId
    		//1. 获取所有分组
    		Map<String, List<TrdStandardBond003Dto>> dtoMap = entity.getOutBondList().stream().collect(JoyinCollectors.groupingBy(x -> x.getPortfolioId() + "," + x.getSecAcctId()));
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
