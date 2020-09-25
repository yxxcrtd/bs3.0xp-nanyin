/*
 * 文件名：CommonSelectImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.common.impl;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.component.business.common.BussCommonSelectService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 共通下拉框实现类
 *
 * @author 杨智
 */
@Service
public class BussCommonSelectImplService implements BussCommonSelectService {

    /**
     * restTemplate
     */
    @Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public List<SuggestDto> getCommonSelect(String selectTarget, Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/get" + selectTarget, param,
            SuggestDto.class);
    }

    @Override
    public List<SelectOptionDto> selectCommonSelect(String selectTarget,
                                                    Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/select" + selectTarget, param,
            SelectOptionDto.class);
    }

    @Override
    public Page<SelectOptionDto> selectCommonSelectForPage(String selectTarget,
                                                    Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/select" + selectTarget, param,
            SelectOptionDto.class);
    }

    @Override
    public Page<SelectOptionDto> qryCommonSelect(String selectTarget, Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/get" + selectTarget, param,
            SelectOptionDto.class);
    }
}
