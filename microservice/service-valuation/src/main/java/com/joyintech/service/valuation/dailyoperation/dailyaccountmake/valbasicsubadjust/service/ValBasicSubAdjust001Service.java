package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dao.ValBasicSubAdjust001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto.ValBasicSubAdjust001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("backValBasicSubAdjust001Service")
public class ValBasicSubAdjust001Service extends CommonExportService<ValBasicSubAdjust001Dto> {
    @Autowired
    private ValBasicSubAdjust001Mapper valBasicSubAdjust001Mapper;

//    /**
//     * 查询账套代码
//     */
//    public Page<SelectOptionDto> selectBooksetIds(int page, int length, Map<String, Object> params){
//        return this.queryForPageForSelectReturn(page,length,()->valBasicSubAdjust001Mapper.selectBooksetIds(params));
//    }

    /**
     * 查询科目号
     */
    public Page<SelectOptionDto> selectSubjectNo(int page, int length, Map<String, Object> params){
        return this.queryForPageForSelectReturn(page,length,()->valBasicSubAdjust001Mapper.selectSubjectNo(params));
    }

    /**
     * 查询调整基础科目性质
     */
    public Page<SelectOptionDto> selectBaseSubjectNature(int page, int length, Map<String, Object> params){
        return this.queryForPageForSelectReturn(page,length,()->valBasicSubAdjust001Mapper.selectBaseSubjectNature(params));
    }

    /**
     * 账务历史表数据更新
     */
    public int updateBokBookdetailAll(ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Mapper.updateBokBookdetailAll(dto);
    }

    /**
     *账务当前表数据更新
     */
    public int updateBokBookdetailCurrent(ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Mapper.updateBokBookdetailCurrent(dto);
    }

    /**
     *明细科目数据更新
     */
    public int updateBokSubjectDetail(ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Mapper.updateBokSubjectDetail(dto);
    }
}
