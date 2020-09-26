package com.joyintech.tams.valuation.common.interf.base.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class Interflog extends BaseEntity {
    /**
     * 文件类型
     */
    private String fileType;

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
     * Y:是;N:否
     */
    private String check;

    /**
     * 校验内容
     */
    private String detail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 文件类型
     * @return 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件类型
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
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

    /**
     * 取得 Y:是;N:否
     * @return Y:是;N:否
     */
    public String getCheck() {
        return check;
    }

    /**
     * 设置 Y:是;N:否
     * @param check Y:是;N:否
     */
    public void setCheck(String check) {
        this.check = check == null ? null : check.trim();
    }

    /**
     * 取得 校验内容
     * @return 校验内容
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置 校验内容
     * @param detail 校验内容
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}