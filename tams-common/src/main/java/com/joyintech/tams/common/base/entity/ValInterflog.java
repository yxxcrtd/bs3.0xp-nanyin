package com.joyintech.tams.common.base.entity;

public class ValInterflog extends ValInterflogKey {
    /**
     * Y:是;N:否
     */
    private String isCheck;

    /**
     * 校验内容
     */
    private String detail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 接口行为日志关联字段
     */
    private String parentSeqno;

    /**
     * 取得 Y:是;N:否
     * @return Y:是;N:否
     */
    public String getIsCheck() {
        return isCheck;
    }

    /**
     * 设置 Y:是;N:否
     * @param isCheck Y:是;N:否
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
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

    /**
     * 取得 接口行为日志关联字段
     * @return 接口行为日志关联字段
     */
    public String getParentSeqno() {
        return parentSeqno;
    }

    /**
     * 设置 接口行为日志关联字段
     * @param parentSeqno 接口行为日志关联字段
     */
    public void setParentSeqno(String parentSeqno) {
        this.parentSeqno = parentSeqno == null ? null : parentSeqno.trim();
    }
}