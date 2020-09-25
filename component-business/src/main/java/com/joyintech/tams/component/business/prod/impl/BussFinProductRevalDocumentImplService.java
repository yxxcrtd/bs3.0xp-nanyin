/*
 * 文件名：BussFinProductRevalDocumentImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PrdFileKey;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductRevalDocumentService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 信息披露文档组件
 * 
 * @author wangzehui
 * @version 1.0 2018年10月19日
 * @see BussFinProductRevalDocumentImplService
 * @since 1.0
 */
@Service
public class BussFinProductRevalDocumentImplService extends BaseService<BussFinProductRevalDocumentDto> implements BussFinProductRevalDocumentService {

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductRevalDocument/delete?finprodId="
                                         + finprodId,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteByPrimaryKey(PrdFileKey key) {
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductRevalDocument/deleteByPrimaryKey",
            key, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductRevalDocumentDto> selectByProductId(String finprodId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductRevalDocument/queryForList?finprodId=" + finprodId, BussFinProductRevalDocumentDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductRevalDocumentDto> selectByWorkflowId(String workflowId, String beanName) {
        List<BussFinProductRevalDocumentDto> resList = new ArrayList<BussFinProductRevalDocumentDto>();

        // 父画面传入的工作流代码
        BussFinProductRevalDocumentDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
            workflowId, beanName, BussFinProductRevalDocumentDto.class);

        if (dto != null) {
            resList = dto.getFinProductRevalDocumentDtoList();
        }

        return resList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SelectOptionDto> selectByFileType(String fileType, String initValue) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductRevalDocument/queryByType?fileType=" + fileType + "&initValue=" + initValue, SelectOptionDto.class);
    }
}
