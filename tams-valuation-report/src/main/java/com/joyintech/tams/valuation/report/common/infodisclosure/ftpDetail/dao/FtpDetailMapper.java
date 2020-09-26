package com.joyintech.tams.valuation.report.common.infodisclosure.ftpDetail.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

@Repository("valuationFtpDetailMapper")
public interface FtpDetailMapper {

	RepFtpDetail selectFtpDetatil(RepFtpDetail dto);

}
