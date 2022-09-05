package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dao;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

import java.util.List;

@Repository("disclosureFtpDetailMapper")
public interface FtpDetailMapper {

	RepFtpDetail selectFtpDetatil(RepFtpDetail dto);

	RepProdInfo selectProdInfo(String prodId);

	RepProdInfo selectProdInfoByProdId(String prodId);

	/**
	 * 根据母产品代码查询发行成功的子产品
	 * */
	List<RepProdInfo> selectProdInfoByPProdId(String pProdId);

	//通过子产品查询母产品
	RepProdInfo selectPProdInfoByCProdId(String prodId);

    String selectProdIdByProdId(@Param("prodId") String prodId);
}
