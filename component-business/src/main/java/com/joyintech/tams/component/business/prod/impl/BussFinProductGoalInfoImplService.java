/*
 * 文件名：BussFinProductGoalInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductGoalInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductGoalInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 产品投向描述实现类
 * 
 * @author zhangliang
 * @version 1.0 2018年9月29日
 * @see BussFinProductGoalInfoImplService
 * @since 1.0
 */
@Service
public class BussFinProductGoalInfoImplService extends BaseService<PtlGoal> implements BussFinProductGoalInfoService {

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<BussFinProductGoalInfoDto> productGoalInfoQuery(String finprodId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductGoalInfo/queryFinProdGoalInfo?finprodId="
                                       + finprodId,
            BussFinProductGoalInfoDto.class);
    }

    @Override
    public void insert(BussFinProductGoalInfoDto bussFinProductGoalInfoDto) {
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductGoalInfo/insertFinProdGoalInfo",
            bussFinProductGoalInfoDto, Integer.class);

    }

    @Override
    public void delete(String finprodId) {
        restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                  + "/FinProductGoalInfo/deleteFinProdGoalInfo?finprodId="
                                  + finprodId,
            Integer.class);

    }

    @Override
    public void update(BussFinProductGoalInfoDto bussFinProductGoalInfoDto) {
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductGoalInfo/updateFinProdGoalInfo",
            bussFinProductGoalInfoDto, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductGoalInfoDto> productGoalInfoQueryByWflId(String workflowId,
                                                                       String beanName) {
        List<BussFinProductGoalInfoDto> resList = new ArrayList<BussFinProductGoalInfoDto>();
        // 父画面传入的工作流代码
        BussFinProductGoalInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
            workflowId, beanName, BussFinProductGoalInfoDto.class);

        if (dto != null) {
            resList = dto.getFinProductPtlGoalDtoList();
        }

        return resList;
    }

}
