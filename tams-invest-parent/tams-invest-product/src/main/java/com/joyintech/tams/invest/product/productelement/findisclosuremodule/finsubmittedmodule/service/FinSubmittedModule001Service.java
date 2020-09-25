/*
 * 文件名：FinSubmittedModule001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule001Dto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 申报方案Service
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule001Service
 * @since 1.0
 */
@Service
@Transactional
public class FinSubmittedModule001Service extends CommonExportService<FinSubmittedModule001Dto> implements BaseProcessService<FinSubmittedModule001Dto> {

    /**
     * 申报附件方案名称查询事件 根据查询框输入的参数进行树节点的初始化
     * 
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    public List<TreeNode<FinSubmittedModule001Dto>> getSchemeName(Map<String,Object> map) {
        List<TreeNode<FinSubmittedModule001Dto>> retList = new ArrayList<>();
        // 获得查询得到的申报附件方案名称
        List<FinSubmittedModule001Dto> list = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModule/getSchemeName",
                map,FinSubmittedModule001Dto.class);
        if (!StringUtil.isEmptyList(list)) {
            // 将查询到的结果赋值树节点
            for (FinSubmittedModule001Dto dto : list) {
                TreeNode data = new TreeNode();
                data.setId(dto.getSchemeId());
                data.setName(dto.getSchemeName());
                retList.add(data);
            }
            return retList;
        }
        else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinSubmittedModule001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/" + action, entity,
                Integer.class);
    }

    /**
     * 查询申报附件方案代码和申报附件方案名称
     * 
     * @param param 申报附件方案代码
     * @return 申报附件方案代码和申报附件方案名称
     * @see
     * @since 1.0
     */
    public FinSubmittedModule001Dto selectScheme(String param) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/selectScheme?param="+param,
                FinSubmittedModule001Dto.class);
    }

    /**
     * 申报附件方案编辑事件
     * 
     * @param dto 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int update(FinSubmittedModule001Dto dto) {
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/update",dto,
                Integer.class);
    }

    /**
     * 删除验证
     *
     * @param schemeId 编辑参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public String deleteCheck(String schemeId) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/deleteCheck?schemeId="+schemeId,
                String.class);
    }

    /**
     * 删除
     *
     * @param dto 删除参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int delete(FinSubmittedModule001Dto dto) {
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/delete",dto,
                Integer.class);
    }

    /**
     * 查询该申报附件方案名称
     *
     * @param map 申报附件方案名称
     * @return 结果
     * @see
     * @since 1.0
     */
    public String selectSchemeName(Map<String,Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModule/selectSchemeName",map,
                String.class);
    }
}
