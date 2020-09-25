package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ValInterflogKey extends BaseEntity {
    /**
     * 模板名称
     */
    private String tmpName;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 处理日期
     */
    private Date exDate;

    /**
     * 错误所在文件行号
     */
    private Long num;

    /**
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTmpName() {
        return tmpName;
    }

    /**
     * 设置 模板名称
     * @param tmpName 模板名称
     */
    public void setTmpName(String tmpName) {
        this.tmpName = tmpName == null ? null : tmpName.trim();
    }

    /**
     * 取得 文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 处理日期
     * @return 处理日期
     */
    public Date getExDate() {
        return exDate;
    }

    /**
     * 设置 处理日期
     * @param exDate 处理日期
     */
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    /**
     * 取得 错误所在文件行号
     * @return 错误所在文件行号
     */
    public Long getNum() {
        return num;
    }

    /**
     * 设置 错误所在文件行号
     * @param num 错误所在文件行号
     */
    public void setNum(Long num) {
        this.num = num;
    }
}