package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 资产重分类
 * 
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see ValAssetReclassification001Dto
 * @since 1.0
 */
public class ValAssetReclassification001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 原会计分类
     */
    private String invAim;

    /**
     * 新会计分类
     */
    private String newInvAim;

    /**
     * 重分类日期
     */
    private Date booksetDate;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 币种
     */
    private String cyy;

    /**
     * 交易类型
     */
    private String busiType;

    /**
     * 金融产品类型
     */
    private String finprodType;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getNewInvAim() {
        return newInvAim;
    }

    public void setNewInvAim(String newInvAim) {
        this.newInvAim = newInvAim;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getCyy() {
        return cyy;
    }

    public void setCyy(String cyy) {
        this.cyy = cyy;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

}
