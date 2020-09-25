package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SttDataReady extends BaseEntity {
    /**
     * 数据就绪表代码
     */
    private Long seqNo;

    /**
     * 资管系统简称
     */
    private String sys;

    /**
     * 主机名
     */
    private String hostNm;

    /**
     * 批次
     */
    private String batchNum;

    /**
     * 数据日期
     */
    private Date dataDate;

    /**
     * 插入该记录的当前时间戳
     */
    private Date readyTime;

    /**
     * 取得 数据就绪表代码
     * @return 数据就绪表代码
     */
    public Long getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 数据就绪表代码
     * @param seqNo 数据就绪表代码
     */
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 取得 资管系统简称
     * @return 资管系统简称
     */
    public String getSys() {
        return sys;
    }

    /**
     * 设置 资管系统简称
     * @param sys 资管系统简称
     */
    public void setSys(String sys) {
        this.sys = sys == null ? null : sys.trim();
    }

    /**
     * 取得 主机名
     * @return 主机名
     */
    public String getHostNm() {
        return hostNm;
    }

    /**
     * 设置 主机名
     * @param hostNm 主机名
     */
    public void setHostNm(String hostNm) {
        this.hostNm = hostNm == null ? null : hostNm.trim();
    }

    /**
     * 取得 批次
     * @return 批次
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 设置 批次
     * @param batchNum 批次
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getDataDate() {
        return dataDate;
    }

    /**
     * 设置 数据日期
     * @param dataDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    /**
     * 取得 插入该记录的当前时间戳
     * @return 插入该记录的当前时间戳
     */
    public Date getReadyTime() {
        return readyTime;
    }

    /**
     * 设置 插入该记录的当前时间戳
     * @param readyTime 插入该记录的当前时间戳
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
    }
}