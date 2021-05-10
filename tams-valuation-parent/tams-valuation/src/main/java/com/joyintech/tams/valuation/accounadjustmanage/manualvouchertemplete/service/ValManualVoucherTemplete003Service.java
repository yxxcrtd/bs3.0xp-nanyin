/*
 * 文件名：ValManualVoucher003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.*;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 手工凭证新建Service
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月9日
 * @see ValManualVoucherTemplete003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucherTemplete003Service extends CommonExportService<ValManualVoucherTemplete003Dto> implements BaseProcessService<ValManualVoucherTemplete003Dto> {

    /**
     *
     * 根据模板编号获取信息
     * @param tmpCode 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public ValManualVoucherTemplete001Dto getByTmpCode(String tmpCode) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/getByTmpCode", tmpCode, ValManualVoucherTemplete001Dto.class);
    }

    /**
     * 获取详情中列表信息
     *
     * @param tmpCode 模板编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValManualVoucherTemplete003AddDto> searchList(String tmpCode) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/searchList", tmpCode, ValManualVoucherTemplete003AddDto.class);
    }

    /**
     * 将数据格式化，为录入表格
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValManualVoucherTemplete003AddDto initTableDto(ValManualVoucherTemplete003AddValidatorDto dto) {
        ValManualVoucherTemplete003AddDto returndto = new ValManualVoucherTemplete003AddDto();
        return returndto;
    }

    /**
     * 科目号联想模糊查询
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySubctNo(Map<String, Object> para){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/querySubctNo", para, SelectOptionDto.class);
    }

    /**
     * 科目名称获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public ValManualVoucherTemplete003SubjectDto getSubjectNo(ValManualVoucherTemplete003SubjectDto dto) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/getSubjectNo", dto, ValManualVoucherTemplete003SubjectDto.class);
    }

    /**
     * 流程审核完成 {@inheritDoc}
     */
    @Override
    public void doComplete(ValManualVoucherTemplete003Dto entity, OperationType type) {
        // 新建操作
        if (OperationType.NEW.equals(type)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/doSubmit",
                    entity, Integer.class);
        }
        //编辑操作
        if (OperationType.MODIFY.equals(type)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/doUpdate",
                    entity, Integer.class);
        }
    }

    public boolean save(ValManualVoucherTemplete003Dto entity) {
        // 新建操作
        if ("0".equals(entity.getPageModel())) {
            return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/doSubmit",
                    entity, boolean.class);
        }
        //编辑操作
        if ("1".equals(entity.getPageModel())) {
            return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/doUpdate",
                    entity, boolean.class);
        }
        return false;
    }

    public int queryPdSequences(){
       return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/queryPdSequences", null,Integer.class);
    }

    /**
     * 修改时，模板名称重复校验
     * @param map
     */
    public int checkByTmpName( Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/checkByTmpName", map,Integer.class);
    }

    /**
     * 新建时，模板名称重复校验
     * @param tmpName
     */
    public int checkTmpName( String tmpName){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/checkTmpName", tmpName,Integer.class);
    }
}
