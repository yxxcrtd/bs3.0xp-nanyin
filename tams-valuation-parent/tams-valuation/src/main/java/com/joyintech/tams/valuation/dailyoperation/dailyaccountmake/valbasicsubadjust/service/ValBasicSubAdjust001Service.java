package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto.ValBasicSubAdjust001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValBasicSubAdjust001Service extends CommonExportService<ValBasicSubAdjust001Dto> {

    /**
     * 查询账套代码
     */
    public Page<SelectOptionDto> selectBooksetIds(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/selectBooksetIds",
                param, SelectOptionDto.class);
    }

    /**
     * 查询科目号
     */
    public Page<SelectOptionDto> selectSubjectNo(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/selectSubjectNo",
                param, SelectOptionDto.class);
    }

    /**
     * 查询基础科目性质
     */
    public Page<SelectOptionDto> selectBaseSubjectNature(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/selectBaseSubjectNature",
                param, SelectOptionDto.class);
    }

    /**
     * 账务历史表数据更新
     */
    public int updateBokBookdetailAll(ValBasicSubAdjust001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/updateBokBookdetailAll",
                dto, Integer.class);
    }

    /**
     * 账务当前表数据更新
     */
    public int updateBokBookdetailCurrent(ValBasicSubAdjust001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/updateBokBookdetailCurrent",
                dto, Integer.class);
    }

    /**
     * 明细科目数据更新
     */
    public int updateBokSubjectDetail(ValBasicSubAdjust001Dto dto){
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBasicSubAdjust001/updateBokSubjectDetail",
                dto, Integer.class);
    }
}
