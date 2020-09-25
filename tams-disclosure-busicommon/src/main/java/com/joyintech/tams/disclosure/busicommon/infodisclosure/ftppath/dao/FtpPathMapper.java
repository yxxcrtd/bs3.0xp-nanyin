package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpPath;

@Repository("disclosureFtpPathMapper")
public interface FtpPathMapper {

	List<RepFtpPath> getReportCategory();

}
