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


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagement002Service
 * @since 1.0
 */
@Service
public class CeAssetGroupManagement002Service extends CommonExportService<CeAssetGroupManagement001Dto> 
        implements BaseProcessService<CeAssetGroupManagement001Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(CeAssetGroupManagement001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/" + action, entity,
            Integer.class);
        
    }

    /**
     * <一句话功能简述>
     * 查看明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public CeAssetGroupManagement001Dto getBusinessInfoBySeqno(String seqno) {
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME
                                          + "/CeAssetGroupManagement/getBusinessInfoBySeqno",
            seqno, CeAssetGroupManagement001Dto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param empty
     * @param assetpoolName
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistByassetpoolName(String seqno, String assetpoolName) {
        Map<String, Object> map = new HashMap<>();
        map.put("seqno", seqno);
        map.put("assetpoolName", assetpoolName);
        
        //modify by wj  增加流程中的判断。
        map.put("pageId", "CeAssetGroupManagement002");
        
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME
                                          + "/CeAssetGroupManagement/checkIsExistByassetpoolName",
            map, Integer.class);
    }

    /**
     * <一句话功能简述>
     * 判断是否关联授信客户编号
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkisExistId(String seqno) {
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME
                                          + "/CeAssetGroupManagement/checkisExistId",
                                          seqno, Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistDetail(String seqno) {
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME
            + "/CeAssetGroupManagement/checkIsExistDetail",
            seqno, Integer.class);
    }

}
