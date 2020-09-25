package com.jixu.adapter.dao.intf;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.FileBackupRecord;

public interface FileBackupRecordDao extends CommFuncDAO{
	public void createBackupRecord(FileBackupRecord fileBackupRecor);
}
