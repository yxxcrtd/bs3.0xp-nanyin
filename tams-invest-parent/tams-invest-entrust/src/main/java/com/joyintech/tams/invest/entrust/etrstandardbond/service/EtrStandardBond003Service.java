/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class EtrStandardBond003Service extends BaseService<EtrStandardBond003Dto> implements BaseProcessService<EtrStandardBond003Dto> {


    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(EtrStandardBond003Dto entity, OperationType operationType) {
        // do nothing
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(EtrStandardBond003Dto input) {
        // do nothing
    }

    /**
     * 工作流中doDelete
     *
     * @param workflowId workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        // do nothing
    }

    /**
     * 工作流审核完成doComplete
     *
     * @param input         entity
     * @param operationType operationType
     */
    @Override
    public void doComplete(EtrStandardBond003Dto input, OperationType operationType) {
        // do nothing
        restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/etrSecPrioritySave",
                    input, Long.class);
    }

//    public Long etrSecPrioritySave(EtrStandardBond003Dto input) {
//        Long insertCount = 0L;
//        insertCount += restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/etrSecPrioritySave",
//                    input, Long.class);
//         return insertCount ;
//    }
    /**
     * 检索可质押债券列表
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public EtrStandardBond003Dto selectEtrSecPriDetailList() {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectEtrSecPriDetailList",null, 
            EtrStandardBond003Dto.class);
          
    }
}
