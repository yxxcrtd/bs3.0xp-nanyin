/*
 * 文件名：FinSubmittedModule002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 申报附件方案Service
 * 可以进行添加申报附件模板以及文件的上传和下载
 * @author liuwei
 * @version 1.0 2018年8月22日
 * @see FinSubmittedModule002Service
 * @since 1.0
 */
@Service
@Transactional
public class FinSubmittedModule002Service extends CommonExportService<FinSubmittedModule002Dto> implements 
    BaseProcessService<FinSubmittedModule002Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(FinSubmittedModule002Dto dto, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModuleDetail/" + action, dto,
                Integer.class);
    }

    /**
     * 删除申报附件方案表信息
     * 
     * @param schemeId 申报附件方案代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public int delete(String schemeId) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinSubmittedModuleDetail/delete?schemeId=" + schemeId,
                Integer.class);
    }

    /**
     * 表格查询操作
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案代码查询）
     * @param map 查询参数
     * @return 附件类型、模板类型和备注
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<FinSubmittedModule002Dto> selectForPage(Map<String, Object> map) 
        throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectForPage",
                map,FinSubmittedModule002Dto.class);
    }

    /**
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案流水号查询）
     * 
     * @param seqno 流水号
     * @return 附件类型、模板类型和备注
     * @see
     * @since 1.0
     */
    public FinSubmittedModule002Dto selectDocTpl(String seqno) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectDocTpl?seqno="+
                        seqno,FinSubmittedModule002Dto.class);
    }
    /**
     * 根据点击行的附件、模板类型和申报方案代码查询对应的流水号
     * 
     * @param dto 查询参数
     * @return 流水号
     * @see
     * @since 1.0
     */
    public String selectSeqno(FinSubmittedModule002Dto dto) {
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectSeqno",
                dto,String.class);
    }

    /**
     * 附件明细表格查询操作
     * 
     * @param map 查询参数
     * @return 文件名和业务编码
     * @throws Exception 异常
     * @see
     * @since 1.0
     *//*
    public Page<FinSubmittedModule002Dto> selectFile(Map<String, Object> map) 
        throws Exception {
        Page<FinSubmittedModule002Dto> page = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectFile",
                map,FinSubmittedModule002Dto.class);
        return page;
    }*/

    /**
     * 根据业务编码删除文件信息
     * 
     * @param businessId 业务编码
     * @see
     * @since 1.0
     */
    public int deleteFile(String businessId) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/deleteFile?businessId="+businessId,
                Integer.class);
    }

    /**
     * 查询上传文件信息流水号
     * 在上传时校验该模板是否已上传过文件
     * @param businessId 业务编码
     * @return 流水号
     * @see
     * @since 1.0
     */
    public String selectSeqNo(String businessId) {
        String seqNo = restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectSeqNo?businessId="+businessId,
                String.class);
        return seqNo;
    }

    /**
     * 查询同样的附件模板是否上传过
     *
     * @param dto 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    public String selectByDocAndTpl(FinSubmittedModule002Dto dto) {
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectByDocAndTpl",dto,
                String.class);
    }

    /**
     * 验证该附件模板是否被理财产品使用
     *
     * @param schemeId 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    public int selectFinProdId(String schemeId) {
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME +
                        "/FinSubmittedModuleDetail/selectFinProdId?schemeId="+schemeId,
                int.class);
    }
}

