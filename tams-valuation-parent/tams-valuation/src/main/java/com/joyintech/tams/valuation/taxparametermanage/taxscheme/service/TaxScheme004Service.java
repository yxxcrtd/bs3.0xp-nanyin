/*
 * 文件名：TaxScheme004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.service;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import java.util.Arrays;
import java.util.List;

/**
 * 附加税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme004Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxScheme004Service extends CommonExportService<TaxScheme004Dto> implements BaseProcessService<TaxScheme004Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme004Dto entity, OperationType type) {

        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
            entity.setCreateInst(createInst);
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(entity.getTaxId(), searchOrgCodeList, editOrgCodeList, "TAXADT");
        }

        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme004/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * 查询 附加税则表
     * 
     * @param seqNo
     * @return
     */
    public TaxScheme004Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme004/findByPrimaryKey?seqNo=" + seqNo,
            TaxScheme004Dto.class);
    }

    /**
     * 查询 附加税则表
     *
     * @return
     */
    public String getPrimaryKey() {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TaxScheme004/getPrimaryKey",
                String.class);
    }
}
