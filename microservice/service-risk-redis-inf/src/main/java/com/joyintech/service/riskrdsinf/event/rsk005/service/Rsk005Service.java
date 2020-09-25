/*
 * 文件名：Rsk005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk005.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.riskrdsinf.common.service.OccupyReleasService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk005.entity.Rsk005InEntity;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval001Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval001Service;

/**
 * 预占释放接口详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月27日
 * @see Rsk005Service
 * @since 1.0
 */
@INF("RSK005")
@Service
public class Rsk005Service extends OccupyReleasService<Rsk005InEntity> {

	@Autowired
	private RskApproval001Service processSrc;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveData(Map<String, Object> mapData, boolean isApprove, String user)
        throws InterfException {
        try {
            Rsk005InEntity record = InfMapValUtil.mapDataToObject(mapData, Rsk005InEntity.class);
            // 设置备份数据
            record.setAmount(record.getAmountOld());
            record.setTradeNum(record.getTradeNumOld());
            getBusiService().savePreOcpInfo(record, isApprove, STATUS_RELEASE, user);
        }
        catch (Exception e) {
            getLogger().error("预占释放信息保存异常", e);
            throw new InterfException("预占释放信息保存异常");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk005InEntity obj, ClientData response) {
    	RskApproval001Dto dto = new RskApproval001Dto();
    	dto.setEventId(obj.getEventId());
    	if (processSrc.isInWorkflow(dto)) {
    		doException(response, "风险审批流程中的委托不允许撤销", null);
    		return true;
    	}
        return false;
    }

}
