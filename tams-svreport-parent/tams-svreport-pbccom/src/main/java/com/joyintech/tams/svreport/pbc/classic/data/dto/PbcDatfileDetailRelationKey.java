package com.joyintech.tams.svreport.pbc.classic.data.dto;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PbcDatfileDetailRelationKey extends BaseEntity {
    /**
     * 明细文件编号
     */
    private String detailid;

    /**
     * 文件代码
     */
    private String filecode;

    /**
     * 生成日期
     */
    private Date gendate;

    /**
     * 关联代码
     */
    private String busiId;

    /**
     * 取得 明细文件编号
     * @return 明细文件编号
     */
    public String getDetailid() {
        return detailid;
    }

    /**
     * 设置 明细文件编号
     * @param detailid 明细文件编号
     */
    public void setDetailid(String detailid) {
        this.detailid = detailid == null ? null : detailid.trim();
    }

    /**
     * 取得 文件代码
     * @return 文件代码
     */
    public String getFilecode() {
        return filecode;
    }

    /**
     * 设置 文件代码
     * @param filecode 文件代码
     */
    public void setFilecode(String filecode) {
        this.filecode = filecode == null ? null : filecode.trim();
    }

    /**
     * 取得 生成日期
     * @return 生成日期
     */
    public Date getGendate() {
        return gendate;
    }

    /**
     * 设置 生成日期
     * @param gendate 生成日期
     */
    public void setGendate(Date gendate) {
        this.gendate = gendate;
    }

    /**
     * 取得 关联代码
     * @return 关联代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 关联代码
     * @param busiId 关联代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }
}