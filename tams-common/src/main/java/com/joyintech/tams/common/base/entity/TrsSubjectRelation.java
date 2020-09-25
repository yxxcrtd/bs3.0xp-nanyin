package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrsSubjectRelation extends BaseEntity {
    /**
     * 流水
     */
    private String seqno;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 核算分类
     */
    private String bookType;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    private String busiDetailType;

    /**
     * 收益类型，净值、预期收益、货币
     */
    private String profitType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 成本科目
     */
    private String cbSubno;

    /**
     * 应收利息科目
     */
    private String yslxSubno;

    /**
     * 利息调整科目
     */
    private String lxtzSubno;

    /**
     * 公允价值变动科目
     */
    private String gyjzbdSubno;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水
     * @param seqno 流水
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 核算分类
     * @return 核算分类
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置 核算分类
     * @param bookType 核算分类
     */
    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
    }

    /**
     * 取得 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     * @return 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     * @param busiDetailType 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
    }

    /**
     * 取得 收益类型，净值、预期收益、货币
     * @return 收益类型，净值、预期收益、货币
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，净值、预期收益、货币
     * @param profitType 收益类型，净值、预期收益、货币
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 成本科目
     * @return 成本科目
     */
    public String getCbSubno() {
        return cbSubno;
    }

    /**
     * 设置 成本科目
     * @param cbSubno 成本科目
     */
    public void setCbSubno(String cbSubno) {
        this.cbSubno = cbSubno == null ? null : cbSubno.trim();
    }

    /**
     * 取得 应收利息科目
     * @return 应收利息科目
     */
    public String getYslxSubno() {
        return yslxSubno;
    }

    /**
     * 设置 应收利息科目
     * @param yslxSubno 应收利息科目
     */
    public void setYslxSubno(String yslxSubno) {
        this.yslxSubno = yslxSubno == null ? null : yslxSubno.trim();
    }

    /**
     * 取得 利息调整科目
     * @return 利息调整科目
     */
    public String getLxtzSubno() {
        return lxtzSubno;
    }

    /**
     * 设置 利息调整科目
     * @param lxtzSubno 利息调整科目
     */
    public void setLxtzSubno(String lxtzSubno) {
        this.lxtzSubno = lxtzSubno == null ? null : lxtzSubno.trim();
    }

    /**
     * 取得 公允价值变动科目
     * @return 公允价值变动科目
     */
    public String getGyjzbdSubno() {
        return gyjzbdSubno;
    }

    /**
     * 设置 公允价值变动科目
     * @param gyjzbdSubno 公允价值变动科目
     */
    public void setGyjzbdSubno(String gyjzbdSubno) {
        this.gyjzbdSubno = gyjzbdSubno == null ? null : gyjzbdSubno.trim();
    }
}