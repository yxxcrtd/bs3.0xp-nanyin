/*
 * 文件名：FinProductPayInt001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.prdrisklevel.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.prdrisklevel.dto.Page2Dto;
import com.joyintech.tams.invest.product.prdrisklevel.dto.PrdRiskLevel002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 新发产品风险等级评定查询service yangxiaofeng
 */
@Service
public class PrdRiskLevel002Service extends BaseService<PrdRiskLevel002Dto> implements BaseProcessService<PrdRiskLevel002Dto> {

    /**
     * 根据产品需求编号查询
     * @param requirementId
     * @return
     */
    public PrdRiskLevel002Dto selectByPk(String pk){
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/selectByPk", pk,
                PrdRiskLevel002Dto.class);
    }

	@Override
	public void doComplete(PrdRiskLevel002Dto entity, OperationType type) {

		if (type == OperationType.DELETE) {
			restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/delete", entity,
					Integer.class);
        }
		
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/update", entity,
				Integer.class);
	}
	
	/**
	 * 查询系统生成参数
	 */
    public String calcRiskGrade(String prdDesignId) {
    	return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/calcRiskGrade", prdDesignId,
				String.class);
    }
	
    /**
     * 根据设计编码自动填充
     */
    public Page2Dto selectPageParam(String prdDesignId) {
    	return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/selectPageParam", prdDesignId,
    			Page2Dto.class);
    }
}
