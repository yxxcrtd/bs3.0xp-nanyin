/*
 * 文件名：Rsk007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk008.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.service.InfBaseService;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk006.service.Rsk006Service;
import com.joyintech.service.riskrdsinf.event.rsk008.dao.RSK008Mapper;
import com.joyintech.service.riskrdsinf.event.rsk008.entity.Rsk008InEntity;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval003AssetDto;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval003Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval003Service;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.info.service.AssetInfoRedisService;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 风险审批接口详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk008Service
 * @since 1.0
 */
@Service
@INF("RSK008")
public class Rsk008Service extends InfBaseService<Rsk008InEntity> {

    /**
     * 事件类型
     */
    public static final String EVENT_TYPE = "eventType";

    public static final String PROD_ID = "portfolioCode";

    public static final String CONCLUSION = "风险审批";

    /**
     * 资产基本信息更新Redis服务
     */
    @Autowired
    private AssetInfoRedisService assetInfoSrc;

    /**
     * 模糊委托审批Service
     */
    @Autowired
    public RskApproval003Service approval003Service;
    
    @Autowired
    private RSK008Mapper rsk008Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, Rsk008InEntity>> handle(ClientData request,
                                                              ClientData response)
        throws InterfException {
        try {
            Map<String, Object> mapData = new HashMap<>();
            mapData.putAll(request.getBody());
            startApproval(mapData);
            response.setBodyField("flowStatus", "0");
        }
        catch (WorkFlowException e) {
            getLogger().error("流程启动异常", e);
            response.setBodyField("flowStatus", "1");
            throw new InterfException(e.getCause().getMessage());
        }
        catch (IOException e) {
            getLogger().error("解析异常", e);
            response.setBodyField("flowStatus", "1");
            throw new InterfException("解析异常");
        }
        return null;
    }

    public void startApproval(Map<String, Object> dataMap)
        throws WorkFlowException, IOException {
        // 启动模糊审批流程
        startApproval003(dataMap);
    }

    /**
     * 启动模糊委托工作流
     * 
     * @param dataMap 接口输入
     * @throws WorkFlowException 工作流异常
     * @throws IOException 读写异常
     * @see
     * @since 1.0
     */
    private void startApproval003(Map<String, Object> dataMap)
        throws WorkFlowException, IOException {
        Map<String, Object> prod = getAssetInfo((String)dataMap.get("assetCode"));
        if (!CollectionUtils.isEmpty(prod)) {
            dataMap.putAll(prod);
        }
        RskApproval003Dto dto = createDto(dataMap);
        dto.setPageId("RskApproval003");
        dto.setElementId("RskApproval003_btn_save");
        dto.setSysCode(getSysCode());
        approval003Service.setSysDate();
        approval003Service.startProcess(dto, getInfId());
        dto.setWflStatus(BussinessDictCodeListRds.有效状态_待处理.getValue());
        rsk008Mapper.updateDimEntrust(dto);
    }

    /**
     * 转换模糊委托Dto
     * 
     * @param dataMap 接口输入
     * @return 模糊委托Dto
     * @throws IOException 读写异常
     * @see
     * @since 1.0
     */
    private RskApproval003Dto createDto(Map<String, Object> dataMap)
        throws IOException {
        RskApproval003Dto dto = InfMapValUtil.mapDataToObject(dataMap, RskApproval003Dto.class);
        // 事件日期
        String sysDate = InterfCacheUtil.getSysDate();
        dto.setEventDate(DateUtil.parse(sysDate));
        // 产品代码
        dto.setProdId((String)dataMap.get(PROD_ID));
        // 计算结论
        dto.setConclusion(CONCLUSION);
        // 模糊委托
        dto.setEventType("3");
        setAssetList(dto, dataMap);
        return dto;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setAssetList(RskApproval003Dto dto, Map<String, Object> dataMap)
        throws IOException {
        Object obj = dataMap.get(Rsk006Service.RSK_CALC_LIST);
        if (obj instanceof String) {
            List<RskApproval003AssetDto> assetList = JsonUtils.json2List(obj.toString(),
                RskApproval003AssetDto.class);
            dto.setAssetList(assetList);
        }
        else if (obj instanceof List) {
            List<RskApproval003AssetDto> assetList = new ArrayList<>();
            List oList = (List)obj;
            oList.forEach(o->{
            	if (o instanceof Map) {
            		RskApproval003AssetDto item =BeanUtil.mapToObject((Map)o, RskApproval003AssetDto.class);
            		 assetList.add(item);
				} else {
					RskApproval003AssetDto item = new RskApproval003AssetDto();
	                BeanUtil.copyPropertiesIgnoreNull(item, o);
	                assetList.add(item);
				}
            });
            dto.setAssetList(assetList);
        }
    }

    /**
     * 取得 资产基本信息
     * 
     * @param prodId 资产代码
     * @return 资产基本信息
     * @see
     * @since 1.0
     */
    protected Map<String, Object> getAssetInfo(String prodId) {
        return assetInfoSrc.getAssetInfoAsMap(prodId);
    }

    @Override
	protected boolean isCalcAmount() {
		return false;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk008InEntity obj, ClientData response) {
        return false;
    }
}
