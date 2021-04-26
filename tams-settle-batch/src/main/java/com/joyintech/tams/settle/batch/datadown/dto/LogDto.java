package com.joyintech.tams.settle.batch.datadown.dto;

import com.joyintech.tams.common.dbf.DBFRow;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see LogDto
 * @since 1.0
 */
public class LogDto {

    private String dataFile;

    private long totalCount;

    private long errorCount;

    private long warningCount;

    /**
     * 解析后的原始数据
     */
    private List<DBFRow> data;

    public LogDto(String dataFile) {
        this.dataFile = dataFile;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void incrementErrorCount() {
        this.errorCount ++;
    }

    public long getWarningCount() {
        return warningCount;
    }

    public void incrementWarningCount() {
        this.warningCount ++;
    }

    public List<DBFRow> getData() {
        return data;
    }

    public void setData(List<DBFRow> data) {
        this.data = data;
        this.totalCount = data.size();
    }
}
