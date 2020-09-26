package com.joyintech.tams.valuation.report.common.infodisclosure.ftppath.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpPath;

@Repository("valuationFtpPathMapper")
public interface FtpPathMapper {

	List<RepFtpPath> getReportCategory();

}
