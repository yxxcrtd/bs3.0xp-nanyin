/*
 * 文件名：ValFinExercisePara002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.finexercisepara.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;

/**
 * 含权债摊销参数设置
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara002Mapper
 * @since 1.0
 */
public interface ValFinExercisePara002Mapper {

    ValFinExercisePara002Dto selectByPrimaryKey(ValFinExercisePara002Dto dto);
    
    int insertSelective(ValFinExercisePara002Dto dto);
    
    int updateByPrimaryKeySelective(ValFinExercisePara002Dto dto);
    
    /**
     * 检索账套代码
     * @param map
     * @return
     */
    List<SelectOptionDto> findBooksetId(Map<String, Object> map);

    /**
     * 检索账套资产（债券）
     * @param param
     * @return
     */
    List<SelectOptionDto> findFinprod(Map<String, Object> param);
    

}
