package com.joyintech.tams.invest.product.nolimit.totalMaintain.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

/**
 * @program: TotalMaintain001Service
 * @description: 非标限额
 * @author: jyl
 * @create: 2019-12-19 11:43
 **/
@Service
public class TotalMaintain002Service extends BaseService<TotalMaintain001Dto> implements BaseProcessService<TotalMaintain001Dto> {
    /**
     * 校验指定日期是否存在数据
     *
     * @param effDate
     * @return int
     */
    public int effDateCheck(String effDate) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/effDateCheck?effDate=" + effDate,
                Integer.class);

    }

    /**
     * 重写审批流程完成方法
     *
     * @param dto
     * @param operationType
     */
    @Override
    public void doComplete(TotalMaintain001Dto dto, OperationType operationType) {
        if (operationType.name() == "NEW") {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/insertInfo", dto, Integer.class);
        } else if (operationType.name() == "MODIFY") {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/updateInfo", dto, Integer.class);
        } else {
            restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/deleteInfo?effDate=" + dto.getEffDate(), Integer.class);
        }

    }

    /**
     * 新建
     *
     * @param dto
     */
    public void insertInfo(TotalMaintain001Dto dto) {
        restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/insertInfo", dto, Integer.class);
    }

    /**
     * 修改
     *
     * @param dto
     */
    public void updateInfo(TotalMaintain001Dto dto) {
        restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/updateInfo", dto, Integer.class);
    }

    /**
     * 删除
     *
     * @param param
     */
    public void deleteInfo(String param) {
        restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/deleteInfo?effDate=" + param, Integer.class);
    }
}
