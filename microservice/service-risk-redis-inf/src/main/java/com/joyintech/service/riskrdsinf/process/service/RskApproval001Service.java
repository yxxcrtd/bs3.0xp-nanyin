package com.joyintech.service.riskrdsinf.process.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCode;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval001Dto;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 预占风险审批
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval001Service
 * @since 1.0
 */
@Service("RskApproval001Service")
public class RskApproval001Service extends RskApprovalService<RskApproval001Dto> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setParams(WorkFlowParameter<RskApproval001Dto> params, RskApproval001Dto dto) {
        // 修改时为画面所选记录的委托编号。
        params.setBusinessCode(StringUtil.isEmpty(dto.getEntrustCode()) ? dto.getEventIdStr() : dto.getEntrustCode());
        // 业务类型
        String bsType = getBusinessType(dto.getAssetType(), dto.getPs(), dto.getEventType());
        params.setBusinessType(bsType);
        // 业务说明
        params.setComment(
            entity -> SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.共通业务类型, bsType)
                  //    + GlobalConstant.SPLIT + SysDataUtil.getNameFromDataDict(InfDictCode.交易方向, entity.getPs())
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getStartDate())
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getEndDate()));
    }

	@Override
    @DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void startProcess(RskApproval001Dto dto, String loginUser) throws WorkFlowException {
		super.startProcessDtx(dto, loginUser);
	}

	@Override
    @DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean isInWorkflow(RskApproval001Dto dto) {
		return super.isInWorkflow(dto);
	}
}
