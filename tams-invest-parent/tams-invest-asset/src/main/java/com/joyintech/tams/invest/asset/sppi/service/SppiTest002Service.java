/*
 * 文件名：SppiTest002Service
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-14
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.asset.sppi.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.invest.asset.sppi.dto.SppiTemplateDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SPPI测试服务<br />
 * 主要是对SPPI_开头的表进行存、取操作
 * @author Jiaoxujin
 * @version 1.0 2020-10-14
 */
@Service
public class SppiTest002Service extends BaseService<SppiTemplateDto> implements BaseProcessService<SppiTemplateDto> {
    /**
     * 嵌套查询SPPI测试详细信息
     * @param sppiTestId String
     * @param finprodId String
     * @return SppiTemplateDto
     */
    public SppiTemplateDto selectSppiTestDetail(String sppiTestId, String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/selectSppiTestDetail?sppiTestId="
                        + sppiTestId + "&finprodId=" + finprodId, SppiTemplateDto.class);
    }

    /**
     * 提交流程启动时调用方法
     * @param entity
     * @param type
     */
    @Override
    public void doInsert(SppiTemplateDto entity, OperationType type){
        restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/saveSppiTest",
                entity, Integer.class);
    }

    /**
     * 代办中修改时流程
     * @param entity
     */
    @Override
    public void doUpdate(SppiTemplateDto entity){
        restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/updateSppiTest",
                entity, Integer.class);
    }

    /**
     * 流程结束时调用方法
     * @param entity
     * @param type
     */
    @Override
    public void doComplete(SppiTemplateDto entity, OperationType type) {
        //审核完成时，FIN_PRODCUT表中的SPPI测试状态更新为‘测试通过’或‘测试不通过’（测试不通过时，也要提交审核）
        restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/auditCompleteSppiTest",
                entity, Integer.class);
    }

    /**
     * 作废
     * @param workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        SppiTemplateDto dto = getBussinessEntityByWorkflowId(workflowId);

        //真的删
        if (dto != null) {
            restTemplate.postForObject(
                    ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/deleteSppiTest",
                    dto, Integer.class);
        }
    }

    /**
     * 退回
     * @param entity
     */
    @Override
    public void doBack(SppiTemplateDto entity) {
        //不删除，仍然测试中，更新状态为：已退回
        //entity.setTestStatus(DictCodeListExt3.SPPI测试状态_已退回.getValue());
        entity.setTestStatus(DictCodeListExt3.SPPI测试状态_测试中.getValue());

        // 更新
        restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/updateSppiTestStatus",
                entity, Integer.class);
    }

    /**
     * 撤消
     * @param entity
     */
    @Override
    public void doRevoke(SppiTemplateDto entity) {
        //真的删
        restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest002/deleteSppiTest",
                entity, Integer.class);
    }
}
