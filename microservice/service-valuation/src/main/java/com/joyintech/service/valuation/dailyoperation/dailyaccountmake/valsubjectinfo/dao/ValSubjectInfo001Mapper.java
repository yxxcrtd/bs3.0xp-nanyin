/*
 * 文件名：ValSubjectInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;

/**
 * 科目信息表Mapper 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValSubjectInfo001Mapper
 * @since 1.0
 */
@Repository
public interface ValSubjectInfo001Mapper {

    /**
     * 科目信息表页面table查询 <功能详细描述>
     * 
     * @param params
     * @return List<ValSubjectInfo001Dto>
     * @see
     * @since 1.0
     */
    List<ValSubjectInfo001Dto> queryPageList(Map<String, Object> params);

    /**
     * 页面产品下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);

}
