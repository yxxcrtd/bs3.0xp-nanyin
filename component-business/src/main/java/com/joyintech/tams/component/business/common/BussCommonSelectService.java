/*
 * 文件名：BussCommonSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.common;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

import java.util.List;
import java.util.Map;

/**
 * 下拉框业务共通组件
 */
public interface BussCommonSelectService {

    List<SuggestDto> getCommonSelect(String selectTarget, Map<String, Object> param);

    List<SelectOptionDto> selectCommonSelect(String selectTarget, Map<String, Object> param);

    Page<SelectOptionDto> qryCommonSelect(String selectTarget, Map<String, Object> param);

    Page<SelectOptionDto> selectCommonSelectForPage(String selectTarget, Map<String, Object> param);
}
