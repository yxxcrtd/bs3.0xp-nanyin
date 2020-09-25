/*
 * 文件名：BussFinProductLayeredInfoImplService.java
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

import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductLayeredInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;

/**
 * 产品投向描述实现类
 * 
 * @author yejiejie
 * @version 1.0 2019年1月23日
 * @see BussFinProductLayeredInfoImplService
 * @since 1.0
 */
@Service
public class BussFinProductLayeredInfoImplService implements BussFinProductLayeredInfoService {

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<BussFinProductLayeredInfoDto> productLayeredInfoQuery(String finprodId) {
//        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                                       + "/FinProductLayeredInfo/queryFinProdLayeredInfo?finprodId="
//                                       + finprodId,
//            BussFinProductLayeredInfoDto.class);
    	return null;
    }

    @Override
    public void insert(BussFinProductLayeredInfoDto bussFinProductLayeredInfoDto) {
//        restTemplate.postForObject(
//            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductLayeredInfo/insertFinProdLayeredInfo",
//            bussFinProductLayeredInfoDto, Integer.class);

    }

    @Override
    public void delete(String finprodId) {
//        restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
//                                  + "/FinProductLayeredInfo/deleteFinProdLayeredInfo?finprodId="
//                                  + finprodId,
//            Integer.class);

    }

    @Override
    public void update(BussFinProductLayeredInfoDto bussFinProductLayeredInfoDto) {
//        restTemplate.postForObject(
//            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductLayeredInfo/updateFinProdLayeredInfo",
//            bussFinProductLayeredInfoDto, Integer.class);      
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductLayeredInfoDto> productLayeredInfoQueryByWflId(String workflowId,
                                                                       String beanName) {
        List<BussFinProductLayeredInfoDto> resList = new ArrayList<BussFinProductLayeredInfoDto>();
        // 父画面传入的工作流代码
        BussFinProductLayeredInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
            workflowId, beanName, BussFinProductLayeredInfoDto.class);

        if (dto != null) {
            //resList = dto.getFinProductPtlLayeredDtoList();
        }

        return resList;
    }

}
