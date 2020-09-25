package com.jixu.adapter.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.dao.intf.FileBackupRecordDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.FileBackupRecord;

@Repository("fileBackupRecordDao")
public class FileBackupRecordDaoImpl extends CommFuncDAOImpl implements FileBackupRecordDao{

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createBackupRecord(FileBackupRecord fileBackupRecor) {
		this.saveEntityBean(fileBackupRecor);
	}
}
