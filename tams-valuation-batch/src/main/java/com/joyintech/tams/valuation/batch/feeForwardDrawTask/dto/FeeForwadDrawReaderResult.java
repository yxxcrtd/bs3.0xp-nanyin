package com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfig;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;

public class FeeForwadDrawReaderResult extends DrawBaseInfoSelectComResult {
	/**
     *  费用预提基础配置信息
     */
    private List<FeeForwadDrawConfigDto> listAccountMaintFeeBookReaderDto = new ArrayList<FeeForwadDrawConfigDto>();

	public List<FeeForwadDrawConfigDto> getListAccountMaintFeeBookReaderDto() {
		return listAccountMaintFeeBookReaderDto;
	}

	public void setListAccountMaintFeeBookReaderDto(List<FeeForwadDrawConfigDto> listAccountMaintFeeBookReaderDto) {
		this.listAccountMaintFeeBookReaderDto = listAccountMaintFeeBookReaderDto;
	}

}
