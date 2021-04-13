/*
 * 文件名：BalanceDetailInfoEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 金融产品实物交易- 实体类
 * 
 * @author sjj
 * @version 1.0 2021年4月12日
 * @see TrdFinProductBarterDto
 * @since 1.0
 */
public class TrdFinProductBarterDto extends BaseEntity{
    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 单位净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 交易本金，债券券面等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 投资目的
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 单位净价
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 交易本金，债券券面等
     * @return 交易本金，债券券面等
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 交易本金，债券券面等
     * @param prinAmt 交易本金，债券券面等
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 交易份额，债券数量等
     * @return 交易份额，债券数量等
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，债券数量等
     * @param shareAmt 交易份额，债券数量等
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 投资目的
     * @param intAmt 投资目的
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
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
     * 交易编号
     */
    private String tradeId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 分类1
     */
    private String type1;

    /**
     * 分类2
     */
    private String type2;

    /**
     * 分类3
     */
    private String type3;

    /**
     * 分类4
     */
    private String type4;

    /**
     * 分类5
     */
    private String type5;

    /**
     * 投管一级类型
     */
    private String type6;

    /**
     * 投管二级类型
     */
    private String type7;

    /**
     * 分类8（投管资产大类）
     */
    private String type8;

    /**
     * 行内二级类型
     */
    private String type9;

    /**
     * 行内三级类型
     */
    private String type10;

    /**
     * 行内四级类型
     */
    private String type11;

    /**
     * 行内五级类型
     */
    private String type12;

    /**
     * 取得 分类1
     * @return 分类1
     */
    public String getType1() {
        return type1;
    }

    /**
     * 设置 分类1
     * @param type1 分类1
     */
    public void setType1(String type1) {
        this.type1 = type1 == null ? null : type1.trim();
    }

    /**
     * 取得 分类2
     * @return 分类2
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 分类2
     * @param type2 分类2
     */
    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    /**
     * 取得 分类3
     * @return 分类3
     */
    public String getType3() {
        return type3;
    }

    /**
     * 设置 分类3
     * @param type3 分类3
     */
    public void setType3(String type3) {
        this.type3 = type3 == null ? null : type3.trim();
    }

    /**
     * 取得 分类4
     * @return 分类4
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 分类4
     * @param type4 分类4
     */
    public void setType4(String type4) {
        this.type4 = type4 == null ? null : type4.trim();
    }

    /**
     * 取得 分类5
     * @return 分类5
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 分类5
     * @param type5 分类5
     */
    public void setType5(String type5) {
        this.type5 = type5 == null ? null : type5.trim();
    }

    /**
     * 取得 投管一级类型
     * @return 投管一级类型
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 投管一级类型
     * @param type6 投管一级类型
     */
    public void setType6(String type6) {
        this.type6 = type6 == null ? null : type6.trim();
    }

    /**
     * 取得 投管二级类型
     * @return 投管二级类型
     */
    public String getType7() {
        return type7;
    }

    /**
     * 设置 投管二级类型
     * @param type7 投管二级类型
     */
    public void setType7(String type7) {
        this.type7 = type7 == null ? null : type7.trim();
    }

    /**
     * 取得 分类8（投管资产大类）
     * @return 分类8（投管资产大类）
     */
    public String getType8() {
        return type8;
    }

    /**
     * 设置 分类8（投管资产大类）
     * @param type8 分类8（投管资产大类）
     */
    public void setType8(String type8) {
        this.type8 = type8 == null ? null : type8.trim();
    }

    /**
     * 取得 行内二级类型
     * @return 行内二级类型
     */
    public String getType9() {
        return type9;
    }

    /**
     * 设置 行内二级类型
     * @param type9 行内二级类型
     */
    public void setType9(String type9) {
        this.type9 = type9 == null ? null : type9.trim();
    }

    /**
     * 取得 行内三级类型
     * @return 行内三级类型
     */
    public String getType10() {
        return type10;
    }

    /**
     * 设置 行内三级类型
     * @param type10 行内三级类型
     */
    public void setType10(String type10) {
        this.type10 = type10 == null ? null : type10.trim();
    }

    /**
     * 取得 行内四级类型
     * @return 行内四级类型
     */
    public String getType11() {
        return type11;
    }

    /**
     * 设置 行内四级类型
     * @param type11 行内四级类型
     */
    public void setType11(String type11) {
        this.type11 = type11 == null ? null : type11.trim();
    }

    /**
     * 取得 行内五级类型
     * @return 行内五级类型
     */
    public String getType12() {
        return type12;
    }

    /**
     * 设置 行内五级类型
     * @param type12 行内五级类型
     */
    public void setType12(String type12) {
        this.type12 = type12 == null ? null : type12.trim();
    }
    /**
     * 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    private String busiId;

    /**
     * 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    private String busiDetailType;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 四级科目尾号，不含三级科目
     */
    private String detailSubjectNoTail;

    /**
     * 四级科目尾号名称
     */
    private String detailSubnameTail;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

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

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

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
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }
}
