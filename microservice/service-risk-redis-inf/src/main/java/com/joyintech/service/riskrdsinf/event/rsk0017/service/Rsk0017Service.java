/*
 * 文件名：Rsk007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk0017.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
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
import com.joyintech.service.riskrdsinf.process.entity.RskApproval004Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval001Service;
import com.joyintech.service.riskrdsinf.process.service.RskApproval002Service;
import com.joyintech.service.riskrdsinf.process.service.RskApproval003Service;
import com.joyintech.service.riskrdsinf.process.service.RskApproval004Service;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量风险审批接口详细处理
 * 
 * @author chaowei
 * @version 1.0 2020年5月10日
 * @see Rsk0017Service
 * @since 1.0
 */
@Service
@INF("RSK0017")
public class Rsk0017Service extends EventBaseService<Rsk007InEntity> {

    /**
     * 事件类型
     */
    public static final String EVENT_TYPE = "eventType";

    public static final String PROD_ID = "portfolioCode";

    public static final String CONCLUSION = "风险审批";

    public static final String DATALIST = "RSK_LIST";


    /**
     * 实占批量审批Service
     */
    @Autowired
    public RskApproval004Service approval004Service;



    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, Rsk007InEntity>> handle(ClientData request,
                                                              ClientData response)
        throws InterfException {
        try {

            Map<String, Object> mapData = new HashMap<>();
            mapData.putAll(request.getBody());
            // 启动实占审批流程
            startApproval002(mapData);
            response.setBodyField("flowStatus", "0");
        }
        catch (Exception e) {
            getLogger().error("流程启动异常", e);
            response.setBodyField("flowStatus", "1");
            throw new InterfException(e.getCause().getMessage());
        }
        return null;
    }

    private void startApproval002(Map<String, Object> rskMap)
        throws Exception {
        String orderId = (String)rskMap.get("orderID");

        List<Map> rsklist = JsonUtils.json2List((String) rskMap.get(DATALIST), Map.class);

        if(StringUtil.isEmptyList(rsklist)) {
            throw new InterfException("业务数据为空");
        }
        /**封装审批页面的基本信息 begin*/
        Map<String, Object> dataMap = rsklist.get(0);

        String manager = (String)dataMap.get("manager");

        Map<String, Object> prod = getAssetInfo(dataMap);
        if (!CollectionUtils.isEmpty(prod)) {
            dataMap.putAll(prod);
        }
        RskApproval004Dto dto = createDto2(dataMap);
        dto.setOrderID(orderId);
        dto.setManager(manager);
        /**封装审批页面的基本信息 end*/

        /**封装审批页面的组合列表 begin*/
        dto.setRsklist(rsklist);
        /**封装审批页面的组合列表 end*/



        dto.setPageId("RskApproval004");
        dto.setElementId("RskApproval004_btn_save");
        dto.setSysCode(getSysCode());
        approval004Service.setSysDate();
        approval004Service.startProcess(dto, (String)dataMap.get("trader"));
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
    private RskApproval004Dto createDto2(Map<String, Object> dataMap) {
        RskApproval004Dto dto = InfMapValUtil.mapDataToObject(dataMap, RskApproval004Dto.class);
        // 产品代码
        dto.setProdId((String)dataMap.get(PROD_ID));
        dto.setMarket(StringUtil.isEmpty(dto.getMarket()) ? (String) dataMap.get("tradePlace") : dto.getMarket());
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
