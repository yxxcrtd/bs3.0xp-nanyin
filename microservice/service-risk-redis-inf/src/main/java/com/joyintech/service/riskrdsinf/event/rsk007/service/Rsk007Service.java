/*
 * 文件名：Rsk007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk007.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.common.service.EventBaseService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCodeList;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk007.entity.Rsk007InEntity;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval001Dto;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval002Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval001Service;
import com.joyintech.service.riskrdsinf.process.service.RskApproval002Service;
import com.joyintech.service.riskrdsinf.process.service.RskApproval003Service;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 风险审批接口详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk007Service
 * @since 1.0
 */
@Service
@INF("RSK007")
public class Rsk007Service extends EventBaseService<Rsk007InEntity> {

    /**
     * 事件类型
     */
    public static final String EVENT_TYPE = "eventType";

    public static final String PROD_ID = "portfolioCode";

    public static final String CONCLUSION = "风险审批";

    /**
     * 模糊委托审批Service
     */
    @Autowired
    public RskApproval003Service approval003Service;

    /**
     * 实占审批Service
     */
    @Autowired
    public RskApproval002Service approval002Service;

    /**
     * 预占审批Service
     */
    @Autowired
    public RskApproval001Service approval001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, Rsk007InEntity>> handle(ClientData request,
                                                              ClientData response)
        throws InterfException {
        try {
            String eventType = (String)request.getBodyField(EVENT_TYPE);
            Map<String, Object> mapData = new HashMap<>();
            mapData.putAll(request.getBody());
            startApproval(mapData, eventType);
            response.setBodyField("flowStatus", "0");
        }
        catch (WorkFlowException e) {
            getLogger().error("流程启动异常", e);
            response.setBodyField("flowStatus", "1");
            throw new InterfException(e.getCause().getMessage());
        }
        return null;
    }

    public void startApproval(Map<String, Object> dataMap, String eventType)
        throws WorkFlowException {
        if (CommonUtil.isType(eventType, InfDictCodeList.事件类型_投资委托)) {
            // 启动预占审批流程
            startApproval001(dataMap);
        }
        else if (CommonUtil.isType(eventType, InfDictCodeList.事件类型_交易拆分)
                 || CommonUtil.isType(eventType, InfDictCodeList.事件类型_投资交易)) {
            // 启动实占审批流程
            startApproval002(dataMap);
        }
    }

    private void startApproval001(Map<String, Object> dataMap)
        throws WorkFlowException {
        Map<String, Object> prod = getAssetInfo(dataMap);
        if (!CollectionUtils.isEmpty(prod)) {
            dataMap.putAll(prod);
        }
        RskApproval001Dto dto = createDto1(dataMap);
        dto.setPageId("RskApproval001");
        dto.setElementId("RskApproval001_btn_save");
        dto.setSysCode(getSysCode());
        approval001Service.setSysDate();
        approval001Service.startProcess(dto, getInfId());
    }

    private void startApproval002(Map<String, Object> dataMap)
        throws WorkFlowException {
        Map<String, Object> prod = getAssetInfo(dataMap);
        if (!CollectionUtils.isEmpty(prod)) {
            dataMap.putAll(prod);
        }
        RskApproval002Dto dto = createDto2(dataMap);
        dto.setPageId("RskApproval002");
        dto.setElementId("RskApproval002_btn_save");
        dto.setSysCode(getSysCode());
        approval002Service.setSysDate();
        approval002Service.startProcess(dto, getInfId());
    }

    /**
     * 转换实占Dto
     * 
     * @param dataMap 接口输入
     * @return 实占Dto
     * @throws IOException 读写异常
     * @see
     * @since 1.0
     */
    private RskApproval001Dto createDto1(Map<String, Object> dataMap) {
        RskApproval001Dto dto = InfMapValUtil.mapDataToObject(dataMap, RskApproval001Dto.class);
        // eventID
        dto.setEventId(/*getSysCode() + GlobalConstant.COLON + */dto.getEventId());
        // 事件日期
        String sysDate = InterfCacheUtil.getSysDate();
        Date dt = DateUtil.parse(sysDate);
        dto.setMarket(StringUtil.isEmpty(dto.getMarket()) ? (String) dataMap.get("tradePlace") : dto.getMarket());
        dto.setEventDate(dt);
        dto.setStartDate(dto.getStartDate() == null ? dt : dto.getStartDate());
        dto.setEndDate(dto.getEndDate() == null ? dto.getOccupyDate() : dto.getEndDate());
        // 产品代码
        dto.setProdId((String)dataMap.get(PROD_ID));
        // 计算结论
        dto.setConclusion(CONCLUSION);
        return dto;
    }

    /**
     * 转换实占Dto
     * 
     * @param dataMap 接口输入
     * @return 实占Dto
     * @throws IOException 读写异常
     * @see
     * @since 1.0
     */
    private RskApproval002Dto createDto2(Map<String, Object> dataMap) {
        RskApproval002Dto dto = InfMapValUtil.mapDataToObject(dataMap, RskApproval002Dto.class);
        // 产品代码
        dto.setProdId((String)dataMap.get(PROD_ID));
        dto.setMarket(StringUtil.isEmpty(dto.getMarket()) ? (String) dataMap.get("tradePlace") : dto.getMarket());
        // eventID
        dto.setEventId(/*getSysCode() + GlobalConstant.COLON + */dto.getEventId());
        // 交易日
        String tradeDate = (String)dataMap.get("tradeDate");
        // 
        dto.setEventDate(StringUtil.isEmpty(tradeDate) ? dto.getEventDate() : DateUtil.parse(tradeDate));
        // 计算结论
        dto.setConclusion(CONCLUSION);
        return dto;
    }

    @Override
	protected boolean isCalcAmount() {
		return false;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk007InEntity obj, ClientData response) {
        return false;
    }
}
