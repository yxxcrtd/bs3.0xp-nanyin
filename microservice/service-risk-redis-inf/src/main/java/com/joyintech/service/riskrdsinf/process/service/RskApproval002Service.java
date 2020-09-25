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
import com.joyintech.service.riskrdsinf.process.entity.RskApproval002Dto;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 实占审批
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval002Service
 * @since 1.0
 */
@Service("RskApproval002Service")
public class RskApproval002Service extends RskApprovalService<RskApproval002Dto> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setParams(WorkFlowParameter<RskApproval002Dto> params, RskApproval002Dto dto) {
        // 修改时为画面所选记录的委托编号。
        params.setBusinessCode(dto.getEventIdStr());
        // 业务类型
        String bsType = getBusinessType(dto.getAssetType(), dto.getPs(), dto.getEventType());
        params.setBusinessType(bsType);
        // 业务说明
        params.setComment(
            entity -> SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.共通业务类型, bsType)
                      + (StringUtil.isNotEmpty(entity.getAssetCode()) ? 
                    		  (GlobalConstant.SPLIT + entity.getAssetName()
                      + GlobalConstant.SPLIT + GlobalConstant.OPEN_PARENTHESIS + entity.getAssetCode() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                      + GlobalConstant.SPLIT + DateUtil.format(entity.getEventDate()));
    }

	@Override
    @DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void startProcess(RskApproval002Dto dto, String loginUser) throws WorkFlowException {
		super.startProcessDtx(dto, loginUser);
	}

	@Override
    @DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean isInWorkflow(RskApproval002Dto dto) {
		return super.isInWorkflow(dto);
	}
}
