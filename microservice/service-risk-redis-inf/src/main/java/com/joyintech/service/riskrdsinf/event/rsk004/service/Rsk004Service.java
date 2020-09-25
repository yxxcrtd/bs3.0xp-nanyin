/*
 * 文件名：Rsk004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk004.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.common.service.OccupyReleasService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk004.entity.Rsk004InEntity;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval002Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval002Service;

/**
 * 实占释放接口处理详细
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月5日
 * @see Rsk004Service
 * @since 1.0
 */
@Service
@INF("RSK004")
public class Rsk004Service extends OccupyReleasService<Rsk004InEntity> {

	@Autowired
	private RskApproval002Service processSrc;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveData(Map<String, Object> mapData, boolean isApprove, String user)
        throws InterfException {
        try {
            String portfolioCode = (String)mapData.get(PORTFOLIOCODE);
            // 备份组合代码
            mapData.put(PROD_ID, portfolioCode);
            Rsk004InEntity record = InfMapValUtil.mapDataToObject(mapData, Rsk004InEntity.class);
            // 设置备份数据
            record.setProdId(StringUtil.isNotEmpty(portfolioCode)?portfolioCode:StringUtil.EMPTY); 
            record.setAmount(record.getAmountOld());
            record.setTradeNum(record.getTradeNumOld());
            getBusiService().saveOcpInfo(record, isApprove, STATUS_RELEASE, user);
        }
        catch (Exception e) {
            getLogger().error("实占释放信息保存异常", e);
            throw new InterfException("实占释放信息保存异常");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getOccupyType() {
        return REL_OCCUPY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk004InEntity obj, ClientData response) {
    	RskApproval002Dto dto = new RskApproval002Dto();
    	dto.setEventId(obj.getEventId());
    	if (processSrc.isInWorkflow(dto)) {
    		doException(response, "风险审批流程中的交易不允许撤销", null);
    		return true;
    	}
        return false;
    }
}
