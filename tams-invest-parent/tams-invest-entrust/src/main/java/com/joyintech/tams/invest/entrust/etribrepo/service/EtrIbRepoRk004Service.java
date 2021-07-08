/*
 * 文件名：CeAssetGroupManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribrepo.service;

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
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk005Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see EtrIbRepoRk004Service
 * @since 1.0
 */
@Service
public class EtrIbRepoRk004Service extends CommonExportService<EtrIbRepoRk004Dto> 
        implements BaseProcessService<EtrIbRepoRk004Dto>{
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(EtrIbRepoRk004Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "inserttotal";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/" + action, entity,
            Integer.class);
        
    }
    
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getTreeNodeList(Map<String,Object> params){
    	return restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/findAllTask",
                params, TreeNode.class);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<TreeNode<EtrIbRepoRk004Dto>> getAssetName(Map<String, Object> param) {
        List<TreeNode<EtrIbRepoRk004Dto>> relist = new ArrayList<>();
        Page<TreeNode<EtrIbRepoRk004Dto>> page = new Page<>();
        // 获得查询的质押券库名称
//        List<EtrIbRepoRk004Dto> list = restTemplate.postForList(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/getAssetName", param,
//            EtrIbRepoRk004Dto.class);
        List<EtrIbRepoRk004Dto> list = restTemplate.postForList(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/getAssetName", param,
				EtrIbRepoRk004Dto.class);
        if (!StringUtil.isEmptyList(list)) {
            // 讲查询到的结果赋值树节点
            for (EtrIbRepoRk004Dto EtrIbRepoRk004Dto : list) {
                TreeNode data = new TreeNode<>();
                data.setId(EtrIbRepoRk004Dto.getPledgeStockId());
                data.setName(EtrIbRepoRk004Dto.getPledgeStockName());
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
    public Page<EtrIbRepoRk005Dto> getAssetDetailBySeqno(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/getAssetDetailBySeqno" ,param,
            EtrIbRepoRk005Dto.class);
        
    }
    
    /**
     * <一句话功能简述>
     * 查看明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public EtrIbRepoRk004Dto getName(String pledgeStockId) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                          + "/EtrIbRepoRk004/getName",
                                          pledgeStockId, EtrIbRepoRk004Dto.class);
    }
}
