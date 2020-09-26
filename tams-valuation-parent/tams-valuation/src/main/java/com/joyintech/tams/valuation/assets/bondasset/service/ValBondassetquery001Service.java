/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.bondasset.dao.ValBondassetquery001Mapper;
import com.joyintech.tams.valuation.assets.bondasset.dao.ValBondassetquery002Mapper;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValBondassetquery001Service extends CommonExportService<ValBondassetquery001Dto> implements BaseProcessService<ValBondassetquery001Dto> {
    
    /**
     * ValBondassetquery001Mapper
     */
//    @Autowired
//    private ValBondassetquery001Mapper mapper;
    
//    @Autowired
//    private ValBondassetquery002Mapper valBondassetquery002Mapper;
    
    /**
     * 债券下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBondId(Map<String, Object> params) { 
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery001/getBondId",
                params, SelectOptionDto.class);
    }

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValBondassetquery001Dto> findPage(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery001/findPage",
                params, ValBondassetquery001Dto.class);
    }

    /**
     * 导出功能
     * <功能详细描述>
     * @param paraMap
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery001Dto> findPageout(Map<String, Object> params) throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondassetquery001/findPageout",
                params, ValBondassetquery001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValBondassetquery001Dto entity, OperationType type) {
//        // 1.删除标的类金融产品表
//        valBondassetquery002Mapper.deleteFinProductByFinProdId(entity.getBondid());
//        // 2.删除标的类金融产品扩展表
//        valBondassetquery002Mapper.deleteFinProductExtByFinProdId(entity.getBondid());
//        // 3.删除金融产品分类表
//        valBondassetquery002Mapper.deleteFinProductTypeByFinProdId(entity.getBondid());
//        // 4.删除行权计划表
//        valBondassetquery002Mapper.deleteFinExercisePlanByFinProdId(entity.getBondid());
//        // 5.删除现金流计划表
//        valBondassetquery002Mapper.deleteFinCashPlanByFinProdId(entity.getBondid());
//        // 6.删除现金流信息表
//        valBondassetquery002Mapper.deleteFinCashInfoByFinProdId(entity.getBondid());
//        // 7.删除现金流计算规则表
//        valBondassetquery002Mapper.deleteFinCashCalcRuleByFinProdId(entity.getBondid());
//        // 8.删除现金流支付规则表
//        valBondassetquery002Mapper.deleteFinCashPayRuleByFinProdId(entity.getBondid());
    	restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery001/doComplete",
                entity, Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param result
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public AjaxResult doDeleteCheck(BindingResult result, ValBondassetquery001Dto dto) throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult wr = new AjaxResult();
        
        //判断当前流程是否存在
        ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
        int count = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondassetquery001/getDataInWorklow",
                processMark, Integer.class);
//        int count = mapper.getDataInWorklow(processMark);
        if(count > 0) {
           //流程存在情况
           return new AjaxResult("该资产存在流程中数据，不能删除！", MessageType.错误消息);
        }
        wr.setData(this.getMessage("COMMON_C_0001", "删除"));
        return wr;
    }
}

