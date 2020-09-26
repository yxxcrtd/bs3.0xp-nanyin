package com.joyintech.tams.valuation.report.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;


/**
 * 从jar包中抽取的方法
 * @author Administrator
 *
 */
@Repository
public interface DaoFromJarMapper {
	
	SysUploadFileInfo selectByPrimaryKey(String seqNo);
	
	int insert(SysUploadFileInfo record);

}
