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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.TreeNode;
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
 * @see CeAssetGroupManagement001Service
 * @since 1.0
 */
@Service
public class CeAssetGroupManagement001Service extends CommonExportService<CeAssetGroupManagement001Dto> 
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
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<TreeNode<CeAssetGroupManagement001Dto>> getAssetName(Map<String, Object> param) {
        List<TreeNode<CeAssetGroupManagement001Dto>> relist = new ArrayList<>();
        Page<TreeNode<CeAssetGroupManagement001Dto>> page = new Page<>();
        // 获得查询的资产系列名称
        List<CeAssetGroupManagement001Dto> list = restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/getAssetName", param,
            CeAssetGroupManagement001Dto.class);
        if (!StringUtil.isEmptyList(list)) {
            // 讲查询到的结果赋值树节点
            for (CeAssetGroupManagement001Dto ceAssetGroupManagement001Dto : list) {
                TreeNode data = new TreeNode<>();
                data.setId(ceAssetGroupManagement001Dto.getSeqno());
                data.setName(ceAssetGroupManagement001Dto.getAssetpoolName());
                relist.add(data);
            }
            page.setRoot(relist);
            page.setRecords(relist.size());
            return relist;
        }
        else {
            return null;
        }

    }

    /**
     * <一句话功能简述>
     * 查询明细
     * @param param
     * @see
     * @since 1.0
     */
    public Page<CeAssetGroupManagement001Dto> getAssetDetailBySeqno(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/getAssetDetailBySeqno" ,param,
            CeAssetGroupManagement001Dto.class);
        
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/creditmanagement/ceassetgroupmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 导出数据查询
     */
    @Override
    public Page<CeAssetGroupManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/selectForPage",
            selectParam, CeAssetGroupManagement001Dto.class);
    }

}
