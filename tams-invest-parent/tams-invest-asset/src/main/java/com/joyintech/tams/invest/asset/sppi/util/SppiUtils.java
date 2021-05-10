/*
 * 文件名：SppiUtils
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-15
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.asset.sppi.util;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.entity.SppiTestQa;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.asset.sppi.dto.SppiTemplateDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * SPPI测试共通功能工具类
 *
 * @author Jiaoxujin
 * @version 1.0 2020-10-15
 */
public class SppiUtils {

    /**
     * 根据资产类型和系统参数，选择相应的SPPI测试模板页面的pageId
     * @param assetType String
     * @param bondSysParam String
     * @param notBondSysParam String
     * @return String
     */
    public static String selectPageId(String assetType, String bondSysParam, String notBondSysParam) {
        String result = "";

        if (StringUtils.equals(assetType, DictCodeList.平安资产类型_债券.getValue())) {
            switch (bondSysParam) {
                case BussinessConstant.SPPI_TEMPLATE_PWC:
                    result = "SppiTemplateBondPwc";
                    break;
                case BussinessConstant.SPPI_TEMPLATE_DTT:
                    result = "SppiTemplateBondDtt";
                    break;
                case BussinessConstant.SPPI_TEMPLATE_KPMG:
                    result = "SppiTemplateBondKpmg";
                    break;
            }
        }
        else if (StringUtils.equals(assetType, DictCodeList.平安资产类型_项目类资产.getValue()) ||
                StringUtils.equals(assetType, DictCodeList.平安资产类型_计划类资产_利率型.getValue())) {
            switch (notBondSysParam) {
                case BussinessConstant.SPPI_TEMPLATE_PWC:
                    result = "SppiTemplateNotBondPwc";
                    break;
                case BussinessConstant.SPPI_TEMPLATE_DTT:
                    result = "SppiTemplateNotBondDtt";
                    break;
                case BussinessConstant.SPPI_TEMPLATE_KPMG:
                    result = "SppiTemplateNotBondKpmg";
                    break;
            }
        }

        return result;
    }

    /**
     * 设置明细列表
     * @param dto SppiTemplateDto
     * @param getMessage Function&lt;String, String&gt;
     */
    public static void setQaList(SppiTemplateDto dto, Function<String, String> getMessage) {
        dto.setSppiTestId(IDGenerator.generate());
        dto.setTestDate(DateUtil.platDateFromCache());

        if (MapUtils.isNotEmpty(dto.getInputAttrMap()) && MapUtils.isNotEmpty(dto.getInputValueMap())) {
            List<SppiTestQa> qaList = new ArrayList<>();

            dto.getInputAttrMap().entrySet().forEach(p -> {
                String fieldName = p.getKey();

                // 值为空的就不入库了
                if (StringUtils.isBlank(MapUtils.getString(dto.getInputValueMap(), fieldName))) {
                    return;
                }

                SppiTestQa qaObj = new SppiTestQa();

                qaObj.setSppiTestId(dto.getSppiTestId());
                qaObj.setFinprodId(dto.getFinprodId());
                qaObj.setQuestionNo(fieldName);
                switch (p.getValue()) {
                    case BussinessConstant.INPUT_TYPE_RADIO:
                        qaObj.setAnswerType(BussinessConstant.SPPI_TEMPLATE_RADIO_BOX);
                        break;
                    case BussinessConstant.INPUT_TYPE_TEXT:
                        qaObj.setAnswerType(BussinessConstant.SPPI_TEMPLATE_TEXT_BOX);
                        break;
                }
                qaObj.setAnswerValue(MapUtils.getString(dto.getInputValueMap(), fieldName));
                if (fieldName.contains("Q")) {
                    //sppiTest002Q27 -->SppiTest002.q27
                    qaObj.setRemark(getMessage.apply(StringUtils.replaceOnce(
                            fieldName,"sppiTest002Q","SppiTest002.q")));
                }
                else if (fieldName.contains("C")) {
                    //sppiTest002C6 -> SppiTest002.c6
                    qaObj.setRemark(getMessage.apply(StringUtils.replaceOnce(
                            fieldName,"sppiTest002C","SppiTest002.c")));
                }

                qaList.add(qaObj);
            });

            dto.setQaList(qaList);
        }
    }
}
