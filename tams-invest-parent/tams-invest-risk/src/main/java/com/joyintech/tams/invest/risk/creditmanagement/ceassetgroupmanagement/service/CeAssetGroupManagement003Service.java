/*
 * 文件名：CeAssetGroupManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagement003Service
 * @since 1.0
 */
@Service
public class CeAssetGroupManagement003Service extends CommonExportService<CeAssetGroupManagement002Dto> 
        implements BaseProcessService<CeAssetGroupManagement002Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(CeAssetGroupManagement002Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "deleteAssetPool";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insertAssetPool";
        }
        else {
            action = "updateAssetPool";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/" + action, entity,
            Integer.class);
        
    }

    /**
     * <一句话功能简述> 查询资产代码联想框
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAssetInfo(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/queryAssetInfo",
            pageParam, SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> selectAssetInfo(Map<String, Object> parameters) {
        if (StringUtil.isNotEmpty(parameters.get("finprodId"))) {
            List<String> splitToList = StringUtil.splitToList(null, (String)parameters.get("finprodId"));
            parameters.put("finprodId", splitToList);
        }
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/selectAssetInfo",
            parameters, CeAssetGroupManagement002Dto.class);
    }

    /**
     * <一句话功能简述>
     * 查询某系列下已有的资产明细
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> getExistAssetBySeqno(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/getExistAssetBySeqno",
            param, CeAssetGroupManagement002Dto.class);
    }

    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistAssetPool(String finprodId) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/checkIsExistAssetPool",
            finprodId, Integer.class);
    }
    
    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加到其他系列中
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public String checkIsExistAssetPoolNew(String finprodId) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/checkIsExistAssetPoolNew",
            finprodId, String.class);
    }

    
    public int checkisExistAssetByCredit(String finprodId) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/checkisExistAssetByCredit",
            finprodId, Integer.class);
    }
    
    /**
     * <一句话功能简述>
     * 查询流程中的工作流代码
     * @param pageId
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> getBusinessWorkflowId(String pageId) {
        return restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME
                                       + "/CeAssetGroupManagement/getBusinessWorkflowId?pageId="
                                       + pageId,
            CeAssetGroupManagement002Dto.class);
    }
    

}
