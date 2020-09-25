package com.joyintech.service.riskrdsinf.event.rsk008.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;

@Repository
public interface RSK008Mapper {

	void updateDimEntrust(RskDimEntrustInfo info);
}
