package com.joyintech.service.riskrdsinf.process.service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval002Dto;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval004Dto;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.TaskAuthentication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 实占批量审批
 * 
 * @author chaowei
 * @version 1.0 2020年5月20日
 * @see RskApproval004Service
 * @since 1.0
 */
@Service("RskApproval004Service")
public class RskApproval004Service extends RskApprovalService<RskApproval004Dto> {

	/**
	 * 日志
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * {@inheritDoc}
     */
    @Override
    protected void setParams(WorkFlowParameter<RskApproval004Dto> params, RskApproval004Dto dto) {
        // 修改时为画面所选记录的委托编号。
        params.setBusinessCode(dto.getOrderID());
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
	public void startProcess(RskApproval004Dto dto, String loginUser) throws WorkFlowException {
		super.startProcessDtx(dto, loginUser,"RskApprovalPage004Service");
	}

	@Override
    @DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean isInWorkflow(RskApproval004Dto dto) {
		return super.isInWorkflow(dto);
	}
}
