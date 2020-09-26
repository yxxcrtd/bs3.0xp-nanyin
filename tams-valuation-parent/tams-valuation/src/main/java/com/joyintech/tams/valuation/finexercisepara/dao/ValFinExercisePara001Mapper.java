/*
 * 文件名：ValFinExercisePara001Mapper.java
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

import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara001Dto;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;

/**
 * 含权债摊销参数设置
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara001Mapper
 * @since 1.0
 */
public interface ValFinExercisePara001Mapper {

    List<ValFinExercisePara001Dto> selectAll(Map<String, Object> map);
    
    int deleteByPrimaryKey(ValFinExercisePara002Dto dto);

}
