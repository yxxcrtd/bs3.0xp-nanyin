package com.joyintech.tams.settle.batch.datadown.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.dbf.DBFRow;

/**
 * @author 何阳阳
 * @date 2020/7/2
 * @see DbfDataRow
 * @since 1.0
 */
public class DbfDataRow extends NeedOperateEntity {
    /**
     * 行数据
     */
    private DBFRow rowData;

    private String fileName;

    public DbfDataRow(DBFRow rowData, String fileName) {
        this.rowData = rowData;
        this.fileName = fileName;
    }

    public DBFRow getRowData() {
        return rowData;
    }

    public void setRowData(DBFRow rowData) {
        this.rowData = rowData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
