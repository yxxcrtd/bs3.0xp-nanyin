package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dao;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

@Repository("disclosureFtpDetailMapper")
public interface FtpDetailMapper {

	RepFtpDetail selectFtpDetatil(RepFtpDetail dto);

	RepProdInfo selectProdInfo(String prodId);
}
