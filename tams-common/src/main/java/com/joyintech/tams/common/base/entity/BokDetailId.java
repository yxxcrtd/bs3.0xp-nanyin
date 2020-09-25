package com.joyintech.tams.common.base.entity;

public class BokDetailId extends BokDetailIdKey {
    /**
     * 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    private String busiId;

    /**
     * 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    private String busiDetailType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 交易编号，指定成本核算时用
     */
    private String tradeId;

    /**
     * 四级科目尾号，不含三级科目
     */
    private String detailSubjectNoTail;

    /**
     * 四级科目尾号名称
     */
    private String detailSubnameTail;

    /**
     * 分支序号，无分支序号的存0
     */
    private Long branch;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     * @return 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     * @param busiId 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
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
     * 取得 证券管理户代码
     * @return 证券管理户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券管理户代码
     * @param secManageAcctId 证券管理户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 交易编号，指定成本核算时用
     * @return 交易编号，指定成本核算时用
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号，指定成本核算时用
     * @param tradeId 交易编号，指定成本核算时用
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 四级科目尾号，不含三级科目
     * @return 四级科目尾号，不含三级科目
     */
    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }

    /**
     * 设置 四级科目尾号，不含三级科目
     * @param detailSubjectNoTail 四级科目尾号，不含三级科目
     */
    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail == null ? null : detailSubjectNoTail.trim();
    }

    /**
     * 取得 四级科目尾号名称
     * @return 四级科目尾号名称
     */
    public String getDetailSubnameTail() {
        return detailSubnameTail;
    }

    /**
     * 设置 四级科目尾号名称
     * @param detailSubnameTail 四级科目尾号名称
     */
    public void setDetailSubnameTail(String detailSubnameTail) {
        this.detailSubnameTail = detailSubnameTail == null ? null : detailSubnameTail.trim();
    }

    /**
     * 取得 分支序号，无分支序号的存0
     * @return 分支序号，无分支序号的存0
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号，无分支序号的存0
     * @param branch 分支序号，无分支序号的存0
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}