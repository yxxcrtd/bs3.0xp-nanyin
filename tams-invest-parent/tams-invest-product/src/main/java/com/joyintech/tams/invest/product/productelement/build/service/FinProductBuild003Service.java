/*
 * 文件名：FinProductBuild003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 发行状态管理
 * 
 * @author zhangliang
 * @version 1.0 2018年10月29日
 * @see FinProductBuild003Service
 * @since 1.0
 */
@Service
public class FinProductBuild003Service extends BaseService<FinProductBuild003Dto> implements BaseProcessService<FinProductBuild003Dto> {

    /**
     * 查询发行状态管理对象
     * 
     * @param finprodIdStr 金融产品代码拼接字符串
     * @return 行状态管理对象
     * @see
     * @since 1.0
     */
    public List<FinProductBuild003Dto> selectForList(String finprodIdStr) {
        List<String> finprodId = StringUtil.splitToList(null, finprodIdStr);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("userId", SecurityUtil.getLoginUser().getUserName());

        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/selectForList", map,
            FinProductBuild003Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductBuild003Dto entity, OperationType type) {
        if (!StringUtil.isEmptyList(entity.getSelectedItem())) {
            for (FinProductBuild003Dto finProductBuild003Dto : entity.getSelectedItem()) {
                restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                           + "/FinProductBuild/updateIssueStatusByFinprodId",
                    finProductBuild003Dto, Integer.class);
            }
        }
        else {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductBuild/updateIssueStatusByFinprodId",
                entity, Integer.class);
        }
    }

}
