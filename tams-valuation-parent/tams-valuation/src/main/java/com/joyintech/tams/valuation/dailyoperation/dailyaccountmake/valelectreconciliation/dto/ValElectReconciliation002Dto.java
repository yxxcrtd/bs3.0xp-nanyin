package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ValElectReconciliation002Dto extends BaseEntity implements ITableRow {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5064115751455891903L;

    /**
     * 业务流水号
     */
    private String serialNo;

    /**
     * 对账对象
     */
    private String interType;

    /**
     * 本方科目号
     */
    private String fxmdmb;

    /**
     * 对方科目号
     */
    private String fxmdmd;

    /**
     * 本方科目名称
     */
    private String bsubjectName;

    /**
     * 对方科目名称
     */
    private String dsubjectName;

    /**
     * 0：余额表/1：估值表
     */
    private String checkFlag;

    /**
     * 本方 余额表：期末余额/估值表：市值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bje;

    /**
     * 对方 余额表：期末余额/估值表：市值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal dje;

    /**
     * 差额 余额表：期末余额/估值表：市值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cje;

    /**
     * 本方 余额表：期末数量余额/估值表：数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bsl;

    /**
     * 对方 余额表：期末数量余额/估值表：数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal dsl;

    /**
     * 差额 余额表：期末数量余额/估值表：数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal csl;

    /**
     * 本方 余额表：借方发生额/估值表：成本
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bjeo;

    /**
     * 对方 余额表：借方发生额/估值表：成本
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal djeo;

    /**
     * 差额 余额表：借方发生额/估值表：成本
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cjeo;

    /**
     * 本方 余额表：贷方发生额/估值表：估值增值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bjet;

    /**
     * 对方 余额表：贷方发生额/估值表：估值增值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal djet;

    /**
     * 对方 余额表：贷方发生额/估值表：估值增值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cjet;

    /**
     * 获取本方科目号
     * 
     * @return
     */
    public String getFxmdmb() {
        return fxmdmb;
    }

    /**
     * 设置本方科目号
     * 
     * @param fxmdmb
     */
    public void setFxmdmb(String fxmdmb) {
        this.fxmdmb = fxmdmb == null ? null : fxmdmb.trim();
    }

    /**
     * 获取对方科目号
     * 
     * @return
     */
    public String getFxmdmd() {
        return fxmdmd;
    }

    /**
     * 设置对方科目号
     * 
     * @param fxmdmd
     */
    public void setFxmdmd(String fxmdmd) {
        this.fxmdmd = fxmdmd == null ? null : fxmdmd.trim();
    }

    /**
     * 获取本方科目名称
     * 
     * @return
     */
    public String getBsubjectName() {
        return bsubjectName;
    }

    /**
     * 设置本方科目名称
     * 
     * @param fundId
     */
    public void setBsubjectName(String bsubjectName) {
        this.bsubjectName = bsubjectName == null ? null : bsubjectName.trim();
    }

    /**
     * 获取对方科目名称
     * 
     * @return
     */
    public String getDsubjectName() {
        return dsubjectName;
    }

    /**
     * 设置对方科目名称
     * 
     * @param dsubjectName
     */
    public void setDsubjectName(String dsubjectName) {
        this.dsubjectName = dsubjectName == null ? null : dsubjectName.trim();
    }

    /**
     * 获取checkFlag
     * 
     * @return
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * 设置checkFlag
     * 
     * @param fundId
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    /**
     * 获取本方 余额表：期末余额/估值表：市值
     * 
     * @return
     */
    public BigDecimal getBje() {
        if (bje != null) {
            return bje;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置本方 余额表：期末余额/估值表：市值
     * 
     * @param bje
     */
    public void setBje(BigDecimal bje) {
        this.bje = bje;
    }

    /**
     * 获取对方 余额表：期末余额/估值表：市值
     * 
     * @return
     */
    public BigDecimal getDje() {
        if (dje != null) {
            return dje;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置对方 余额表：期末余额/估值表：市值
     * 
     * @param dje
     */
    public void setDje(BigDecimal dje) {
        this.dje = dje;
    }

    /**
     * 获取差额 余额表：期末余额/估值表：市值
     * 
     * @return
     */
    public BigDecimal getCje() {
        if (cje != null) {
            return cje;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置差额 余额表：期末余额/估值表：市值
     * 
     * @param cje
     */
    public void setCje(BigDecimal cje) {
        this.cje = cje;
    }

    /**
     * 获取本方 余额表：期末数量余额/估值表：数量
     * 
     * @return
     */
    public BigDecimal getBsl() {
        if (bsl != null) {
            return bsl;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置本方 余额表：期末数量余额/估值表：数量
     * 
     * @param bsl
     */
    public void setBsl(BigDecimal bsl) {
        this.bsl = bsl;
    }

    /**
     * 获取对方 余额表：期末数量余额/估值表：数量
     * 
     * @return
     */
    public BigDecimal getDsl() {
        if (dsl != null) {
            return dsl;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置对方 余额表：期末数量余额/估值表：数量
     * 
     * @param dsl
     */
    public void setDsl(BigDecimal dsl) {
        this.dsl = dsl;
    }

    /**
     * 获取差额 余额表：期末数量余额/估值表：数量
     * 
     * @return
     */
    public BigDecimal getCsl() {
        if (csl != null) {
            return csl;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置差额 余额表：期末数量余额/估值表：数量
     * 
     * @param csl
     */
    public void setCsl(BigDecimal csl) {
        this.csl = csl;
    }

    /**
     * 获取本方 余额表：借方发生额/估值表：成本
     * 
     * @return
     */
    public BigDecimal getBjeo() {
        if (bjeo != null) {
            return bjeo;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置本方 余额表：借方发生额/估值表：成本
     * 
     * @param bjeo
     */
    public void setBjeo(BigDecimal bjeo) {
        this.bjeo = bjeo;
    }

    /**
     * 获取对方 余额表：借方发生额/估值表：成本
     * 
     * @return
     */
    public BigDecimal getDjeo() {
        if (djeo != null) {
            return djeo;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置对方 余额表：借方发生额/估值表：成本
     * 
     * @param djeo
     */
    public void setDjeo(BigDecimal djeo) {
        this.djeo = djeo;
    }

    /**
     * 获取差额 余额表：借方发生额/估值表：成本
     * 
     * @return
     */
    public BigDecimal getCjeo() {
        if (cjeo != null) {
            return cjeo;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置差额 余额表：借方发生额/估值表：成本
     * 
     * @param cjeo
     */
    public void setCjeo(BigDecimal cjeo) {
        this.cjeo = cjeo;
    }

    /**
     * 获取本方 余额表：贷方发生额/估值表：估值增值
     * 
     * @return
     */
    public BigDecimal getBjet() {
        if (bjet != null) {
            return bjet;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置本方 余额表：贷方发生额/估值表：估值增值
     * 
     * @param bje
     */
    public void setBjet(BigDecimal bjet) {
        this.bjet = bjet;
    }

    /**
     * 获取对方 余额表：贷方发生额/估值表：估值增值
     * 
     * @return
     */
    public BigDecimal getDjet() {
        if (djet != null) {
            return djet;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置对方 余额表：贷方发生额/估值表：估值增值
     * 
     * @param bjet
     */
    public void setDjet(BigDecimal djet) {
        this.djet = djet;
    }

    /**
     * 获取差额 余额表：贷方发生额/估值表：估值增值
     * 
     * @return
     */
    public BigDecimal getCjet() {
        if (cjet != null) {
            return cjet;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 设置差额 余额表：贷方发生额/估值表：估值增值
     * 
     * @param cjet
     */
    public void setCjet(BigDecimal cjet) {
        this.cjet = cjet;
    }

    /**
     * 获取业务流水号
     * 
     * @return
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置业务流水号
     * 
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    /**
     * 获取对账对象
     * 
     * @return
     */
    public String getInterType() {
        return interType;
    }

    /**
     * 设置对账对象
     * 
     * @param interType
     */
    public void setInterType(String interType) {
        this.interType = interType == null ? null : interType.trim();
    }

}
