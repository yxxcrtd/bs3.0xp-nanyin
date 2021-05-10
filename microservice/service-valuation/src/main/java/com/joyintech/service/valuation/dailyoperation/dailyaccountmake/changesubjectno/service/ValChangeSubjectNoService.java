/*
 * 文件名：ValIncomeStatisticsQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.changesubjectno.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.changesubjectno.dao.ValChangeSubjectNoMapper;
import com.joyintech.tams.common.base.entity.BokSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 科目号变更
 * @author yanya
 * @version 1.0 2020-12-30
 * @see ValChangeSubjectNoService
 * @since 1.0
 */
@Service(value="serviceValChangeSubjectNoService")
public class ValChangeSubjectNoService extends CommonExportService<BokSubject> {

    @Autowired
    private ValChangeSubjectNoMapper valChangeSubjectNoMapper;

    /**
     * 根据科目号list查询科目数据
     * @param list
     * @return
     */
    public List<BokSubject> getSubjectList(List<String> list) {
        return valChangeSubjectNoMapper.getSubjectList(list);
    }

    /**
     * 查询所有账套ID
     * @param map
     * @return
     */
    public List<String> getBooksetIds(Map<String, Object> map) {
        return valChangeSubjectNoMapper.getBooksetIds(map);
    }

    /**
     * 科目信息下拉框
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getSubjectIdByPage(int page, int length, Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page,length,()-> valChangeSubjectNoMapper.getSubjectId(param));
    }

    /**
     * 账套下拉框
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetIdByPage(int page, int length, Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page,length,()-> valChangeSubjectNoMapper.findBooksetId(param));
    }

    public Page<SelectOptionDto> selectDataDictType(int page, int length, Map<String, Object> params){
        return this.queryForPageForSelectReturn(page,length,()->valChangeSubjectNoMapper.selectDataDictType(params));
    }

}
