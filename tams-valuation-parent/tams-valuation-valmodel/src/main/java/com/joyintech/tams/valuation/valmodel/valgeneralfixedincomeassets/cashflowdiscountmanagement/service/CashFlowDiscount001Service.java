/*
 * 文件名：ValAssetBook001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.service;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.AssetInfoDto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.ModAseetMappingDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 现金流折现模型曲线服务类
 *
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see CashFlowDiscount001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CashFlowDiscount001Service extends CommonExportService<CashFlowDiscount001Dto> implements BaseProcessService<CashFlowDiscount001Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(CashFlowDiscount001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        }
        restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/CashFlowDiscount001/" + action, entity,
                Integer.class);
    }

    public List<CashFlowDiscount001Dto> selectForExport(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/CashFlowDiscount001/selectForExport", paraMap,
                CashFlowDiscount001Dto.class);
    }

    /**
     * 检查资产类别、资产分类和资产代码的唯一性
     *
     * @param dto 现金流折现模型曲线配置
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean haveDataCheck(CashFlowDiscount001Dto dto) {
        return restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME + "/CashFlowDiscount001/haveDataCheck",dto, boolean.class);
    }
    /**
     * 根据资产类型、资产细分类和资产代码查找结果数据
     *
     * @param params
     * @return List<CashFlowDiscount001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<CashFlowDiscount001Dto> getAllCashFlowDiscountPage(Map<String,Object> params) throws Exception {
//        System.out.println(ServiceApplication.LOSS_MODEL_SERVICE_NAME);
        Page<CashFlowDiscount001Dto> rstobj = restTemplate.postForPage(ServiceApplication.VAL_MODEL_SERVICE_NAME+"/CashFlowDiscount001/getCashFlowDiscountListPage", params, CashFlowDiscount001Dto.class);
        return rstobj;
    }

    /**
     * 根据seqNo查询数据
     * @param paraMap
     * @return
     */
    public CashFlowDiscount001Dto getCashFlowDataBySeqNo(Map<String, Object> paraMap) {
        return restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME+"/CashFlowDiscount001/getCashFlowDataBySeqNo", paraMap, CashFlowDiscount001Dto.class);
    }

    /**
     * 格式化创建SelectOptionDto对象
     *
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    public List<SuggestDto> getFinprodIdForValuation(Map<String, Object> parameters) {

        List<SuggestDto> resultList = new ArrayList<SuggestDto>();
        // 赋值映射表的输入参数
		ModAseetMappingDto qModAseetMappingDto=new ModAseetMappingDto();
		qModAseetMappingDto.setInput1(parameters.get("secTypeModel").toString());
        qModAseetMappingDto.setInput2(parameters.get("secDetailTypeModel").toString());
        // 查询资产分类映射表数据
		List<ModAseetMappingDto> tgfls=restTemplate.postForList(
				ServiceApplication.VAL_MODEL_SERVICE_NAME + "/Modelvaluation/getModAseetMappingByModel", qModAseetMappingDto,
				ModAseetMappingDto.class);

        AssetInfoDto qAssetInfoDto=new AssetInfoDto();
        if(parameters.get("search")!=null&&!"".equals(parameters.get("search"))){
            qAssetInfoDto.setSearch(parameters.get("search").toString());
        }
        if(parameters.get("initValue")!=null&&!"".equals(parameters.get("initValue"))){
            qAssetInfoDto.setInitValue(parameters.get("initValue").toString());
        }
		qAssetInfoDto.setType12List(tgfls.stream().map(ModAseetMappingDto::getOutput5).collect(Collectors.toList()));
        List<AssetInfoDto> aseetS = restTemplate.postForList(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/Modelvaluation/getAssetNoClassify", qAssetInfoDto,
                AssetInfoDto.class);

        // 剔除同时不含赎回权和利率调整权的永续债
        Iterator<AssetInfoDto> iter = aseetS.iterator();
        while(iter.hasNext()){
            AssetInfoDto temp = iter.next();
            if(temp.getIsRateAdjust() != null && temp.getIsRateAdjust().equals("N") // 是否含有利率调整权
                    && temp.getIsSus() != null && temp.getIsSus().equals("Y")  // 是否永续债
                    && temp.getIsRight() != null && temp.getIsRight().equals("N") // 是否含权
            ){
                iter.remove();
            }
        }

        resultList = aseetS.stream().map(operationStation -> {
            SuggestDto stationDictVO = new SuggestDto();
            stationDictVO.setCode(operationStation.getFinprodId());
            stationDictVO.setShortName(operationStation.getFinprodAbbr());
            return stationDictVO;
        }).collect(Collectors.toList());

        return resultList;
    }
}