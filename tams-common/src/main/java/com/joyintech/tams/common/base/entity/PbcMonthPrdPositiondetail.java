package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PbcMonthPrdPositiondetail extends BaseEntity {
    /**
     * 主键uuid
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 机构代码，产品所属的机构代码
     */
    private String orgCode;

    /**
     * 是否分级产品 01 是/02 否
     */
    private String isConstracte;

    /**
     * 转让类型：01 收益权转让/99 其他
     */
    private String transferType;

    /**
     * 销售区域
     */
    private String buyPlace;

    /**
     * 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    private String operateMode;

    /**
     * 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    private String ordIdentified;

    /**
     * 募集方式： 01公募/02私募
     */
    private String raisemode;

    /**
     * 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 管理方式：01 集合/02 单一
     */
    private String manageType;

    /**
     * 受托职责：01 主动管理/02 被动管理
     */
    private String fiduciaryDuty;

    /**
     * 存续状态 01存续/02终止/03未起息
     */
    private String durationflag;

    /**
     * 货币市场基金：01是/02否
     */
    private String isMarketFund;

    /**
     * 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    private Date vdate;

    /**
     * null
     */
    private Date mdate;

    /**
     * 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    private String paystatus;

    /**
     * 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    private String term;

    /**
     * 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    private String ccy;

    /**
     * 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    private String customerType2;

    /**
     * 募集余额，截止月末的余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endamtsumOth;

    /**
     * 当期募集金额，本月内的募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyamtsum;

    /**
     * 当期兑付金额，本月内兑付的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redamt;

    /**
     * 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays;

    /**
     * 期末份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endqtysum;

    /**
     * 当期募集份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyqty;

    /**
     * 当期兑付份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redqty;

    /**
     * 当期到期本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redcost;

    /**
     * 剩余成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endcost;

    /**
     * 是否现金管理类
     */
    private String cashManaCate;

    /**
     * 是否新产品
     */
    private String isNewPrd;

    /**
     * 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    private String termtype;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal termdays;

    /**
     * 开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他
     */
    private String openType;

    /**
     * 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    private String profitType;

    /**
     * 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    private String saleTarget;

    /**
     * 是否母行划转产品
     */
    private String isTransfer;

    /**
     * 是否合规新产品
     */
    private String isFhxgcp;

    /**
     * 是否养老理财产品
     */
    private String isYllccp;

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 设置 主键uuid
     * @param posuuid 主键uuid
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 机构代码，产品所属的机构代码
     * @return 机构代码，产品所属的机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码，产品所属的机构代码
     * @param orgCode 机构代码，产品所属的机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 是否分级产品 01 是/02 否
     * @return 是否分级产品 01 是/02 否
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 是否分级产品 01 是/02 否
     * @param isConstracte 是否分级产品 01 是/02 否
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 取得 转让类型：01 收益权转让/99 其他
     * @return 转让类型：01 收益权转让/99 其他
     */
    public String getTransferType() {
        return transferType;
    }

    /**
     * 设置 转让类型：01 收益权转让/99 其他
     * @param transferType 转让类型：01 收益权转让/99 其他
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    /**
     * 取得 销售区域
     * @return 销售区域
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 设置 销售区域
     * @param buyPlace 销售区域
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace == null ? null : buyPlace.trim();
    }

    /**
     * 取得 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     * @return 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     * @param operateMode 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     * @return 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    public String getOrdIdentified() {
        return ordIdentified;
    }

    /**
     * 设置 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     * @param ordIdentified 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    public void setOrdIdentified(String ordIdentified) {
        this.ordIdentified = ordIdentified == null ? null : ordIdentified.trim();
    }

    /**
     * 取得 募集方式： 01公募/02私募
     * @return 募集方式： 01公募/02私募
     */
    public String getRaisemode() {
        return raisemode;
    }

    /**
     * 设置 募集方式： 01公募/02私募
     * @param raisemode 募集方式： 01公募/02私募
     */
    public void setRaisemode(String raisemode) {
        this.raisemode = raisemode == null ? null : raisemode.trim();
    }

    /**
     * 取得 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 设置 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 取得 管理方式：01 集合/02 单一
     * @return 管理方式：01 集合/02 单一
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式：01 集合/02 单一
     * @param manageType 管理方式：01 集合/02 单一
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 受托职责：01 主动管理/02 被动管理
     * @return 受托职责：01 主动管理/02 被动管理
     */
    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    /**
     * 设置 受托职责：01 主动管理/02 被动管理
     * @param fiduciaryDuty 受托职责：01 主动管理/02 被动管理
     */
    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty == null ? null : fiduciaryDuty.trim();
    }

    /**
     * 取得 存续状态 01存续/02终止/03未起息
     * @return 存续状态 01存续/02终止/03未起息
     */
    public String getDurationflag() {
        return durationflag;
    }

    /**
     * 设置 存续状态 01存续/02终止/03未起息
     * @param durationflag 存续状态 01存续/02终止/03未起息
     */
    public void setDurationflag(String durationflag) {
        this.durationflag = durationflag == null ? null : durationflag.trim();
    }

    /**
     * 取得 货币市场基金：01是/02否
     * @return 货币市场基金：01是/02否
     */
    public String getIsMarketFund() {
        return isMarketFund;
    }

    /**
     * 设置 货币市场基金：01是/02否
     * @param isMarketFund 货币市场基金：01是/02否
     */
    public void setIsMarketFund(String isMarketFund) {
        this.isMarketFund = isMarketFund == null ? null : isMarketFund.trim();
    }

    /**
     * 取得 起息日，结合存续状态获得新发产品（起息日在本期=03）
     * @return 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日，结合存续状态获得新发产品（起息日在本期=03）
     * @param vdate 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 null
     * @param mdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     * @return 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    public String getPaystatus() {
        return paystatus;
    }

    /**
     * 设置 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     * @param paystatus 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    /**
     * 取得 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     * @return 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     * @param term 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     * @return 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     * @param ccy 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 客户二级类型，枚举值参见CUSTOMER_TYPE2
     * @return 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    public String getCustomerType2() {
        return customerType2;
    }

    /**
     * 设置 客户二级类型，枚举值参见CUSTOMER_TYPE2
     * @param customerType2 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    public void setCustomerType2(String customerType2) {
        this.customerType2 = customerType2 == null ? null : customerType2.trim();
    }

    /**
     * 取得 募集余额，截止月末的余额
     * @return 募集余额，截止月末的余额
     */
    public BigDecimal getEndamtsumOth() {
        return endamtsumOth;
    }

    /**
     * 设置 募集余额，截止月末的余额
     * @param endamtsumOth 募集余额，截止月末的余额
     */
    public void setEndamtsumOth(BigDecimal endamtsumOth) {
        this.endamtsumOth = endamtsumOth;
    }

    /**
     * 取得 当期募集金额，本月内的募集金额
     * @return 当期募集金额，本月内的募集金额
     */
    public BigDecimal getDirtyamtsum() {
        return dirtyamtsum;
    }

    /**
     * 设置 当期募集金额，本月内的募集金额
     * @param dirtyamtsum 当期募集金额，本月内的募集金额
     */
    public void setDirtyamtsum(BigDecimal dirtyamtsum) {
        this.dirtyamtsum = dirtyamtsum;
    }

    /**
     * 取得 当期兑付金额，本月内兑付的金额
     * @return 当期兑付金额，本月内兑付的金额
     */
    public BigDecimal getRedamt() {
        return redamt;
    }

    /**
     * 设置 当期兑付金额，本月内兑付的金额
     * @param redamt 当期兑付金额，本月内兑付的金额
     */
    public void setRedamt(BigDecimal redamt) {
        this.redamt = redamt;
    }

    /**
     * 取得 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     * @return 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    public BigDecimal getEndDays() {
        return endDays;
    }

    /**
     * 设置 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     * @param endDays 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    public void setEndDays(BigDecimal endDays) {
        this.endDays = endDays;
    }

    /**
     * 取得 期末份额
     * @return 期末份额
     */
    public BigDecimal getEndqtysum() {
        return endqtysum;
    }

    /**
     * 设置 期末份额
     * @param endqtysum 期末份额
     */
    public void setEndqtysum(BigDecimal endqtysum) {
        this.endqtysum = endqtysum;
    }

    /**
     * 取得 当期募集份额
     * @return 当期募集份额
     */
    public BigDecimal getDirtyqty() {
        return dirtyqty;
    }

    /**
     * 设置 当期募集份额
     * @param dirtyqty 当期募集份额
     */
    public void setDirtyqty(BigDecimal dirtyqty) {
        this.dirtyqty = dirtyqty;
    }

    /**
     * 取得 当期兑付份额
     * @return 当期兑付份额
     */
    public BigDecimal getRedqty() {
        return redqty;
    }

    /**
     * 设置 当期兑付份额
     * @param redqty 当期兑付份额
     */
    public void setRedqty(BigDecimal redqty) {
        this.redqty = redqty;
    }

    /**
     * 取得 当期到期本金
     * @return 当期到期本金
     */
    public BigDecimal getRedcost() {
        return redcost;
    }

    /**
     * 设置 当期到期本金
     * @param redcost 当期到期本金
     */
    public void setRedcost(BigDecimal redcost) {
        this.redcost = redcost;
    }

    /**
     * 取得 剩余成本
     * @return 剩余成本
     */
    public BigDecimal getEndcost() {
        return endcost;
    }

    /**
     * 设置 剩余成本
     * @param endcost 剩余成本
     */
    public void setEndcost(BigDecimal endcost) {
        this.endcost = endcost;
    }

    /**
     * 取得 是否现金管理类
     * @return 是否现金管理类
     */
    public String getCashManaCate() {
        return cashManaCate;
    }

    /**
     * 设置 是否现金管理类
     * @param cashManaCate 是否现金管理类
     */
    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate == null ? null : cashManaCate.trim();
    }

    /**
     * 取得 是否新产品
     * @return 是否新产品
     */
    public String getIsNewPrd() {
        return isNewPrd;
    }

    /**
     * 设置 是否新产品
     * @param isNewPrd 是否新产品
     */
    public void setIsNewPrd(String isNewPrd) {
        this.isNewPrd = isNewPrd == null ? null : isNewPrd.trim();
    }

    /**
     * 取得 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     * @return 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 设置 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     * @param termtype 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getTermdays() {
        return termdays;
    }

    /**
     * 设置 null
     * @param termdays null
     */
    public void setTermdays(BigDecimal termdays) {
        this.termdays = termdays;
    }

    /**
     * 取得 开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他
     * @return 开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他
     * @param openType 开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他
     */
    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    /**
     * 取得 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @return 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @param profitType 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @return 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public String getSaleTarget() {
        return saleTarget;
    }

    /**
     * 设置 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @param saleTarget 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public void setSaleTarget(String saleTarget) {
        this.saleTarget = saleTarget == null ? null : saleTarget.trim();
    }

    /**
     * 取得 是否母行划转产品
     * @return 是否母行划转产品
     */
    public String getIsTransfer() {
        return isTransfer;
    }

    /**
     * 设置 是否母行划转产品
     * @param isTransfer 是否母行划转产品
     */
    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer == null ? null : isTransfer.trim();
    }

    /**
     * 取得 是否合规新产品
     * @return 是否合规新产品
     */
    public String getIsFhxgcp() {
        return isFhxgcp;
    }

    /**
     * 设置 是否合规新产品
     * @param isFhxgcp 是否合规新产品
     */
    public void setIsFhxgcp(String isFhxgcp) {
        this.isFhxgcp = isFhxgcp == null ? null : isFhxgcp.trim();
    }

    /**
     * 取得 是否养老理财产品
     * @return 是否养老理财产品
     */
    public String getIsYllccp() {
        return isYllccp;
    }

    /**
     * 设置 是否养老理财产品
     * @param isYllccp 是否养老理财产品
     */
    public void setIsYllccp(String isYllccp) {
        this.isYllccp = isYllccp == null ? null : isYllccp.trim();
    }
}