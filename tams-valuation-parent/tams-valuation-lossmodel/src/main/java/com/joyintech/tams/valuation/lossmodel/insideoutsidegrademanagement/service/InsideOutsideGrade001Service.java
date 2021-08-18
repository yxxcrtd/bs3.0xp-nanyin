
package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 内外部评级映射管理服务类
 *
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see InsideOutsideGrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InsideOutsideGrade001Service extends CommonExportService<InsideOutsideGrade001Dto> implements BaseProcessService<InsideOutsideGrade001Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(InsideOutsideGrade001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/InsideOutsideGrade001/" + action, entity,
                Integer.class);
    }

    public List<InsideOutsideGrade001Dto> selectForExport(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/InsideOutsideGrade001/selectForExport", paraMap,
                InsideOutsideGrade001Dto.class);
    }

    /**
     * 检查资产类别、资产分类和资产代码的唯一性
     *
     * @param dto 现金流折现模型曲线配置
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean haveDataCheck(InsideOutsideGrade001Dto dto) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/InsideOutsideGrade001/haveDataCheck",dto, boolean.class);
    }
    /**
     * 根据资产类型、资产细分类和资产代码查找结果数据
     *
     * @param params
     * @return List<CashFlowDiscount001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<InsideOutsideGrade001Dto> getAllInsideOutsideGradePage(Map<String,Object> params) throws Exception {
        Page<InsideOutsideGrade001Dto> rstobj = restTemplate.postForPage(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/InsideOutsideGrade001/getQueryDataListPage", params, InsideOutsideGrade001Dto.class);
        return rstobj;
    }

    /**
     * 根据seqNo查询数据
     * @param params
     * @return
     */
    public List<InsideOutsideGrade001Dto> getInsideOutSideDataBySeqNo(Map<String,Object> params) {
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/InsideOutsideGrade001/getDataBySeqNo", params, InsideOutsideGrade001Dto.class);
    }
}