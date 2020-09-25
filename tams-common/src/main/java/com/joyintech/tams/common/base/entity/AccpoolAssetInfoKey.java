package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class AccpoolAssetInfoKey extends BaseEntity {
    /**
     * 准入池代码
     */
    private String accpoolId;

    /**
     * 出入项类型包括资产、机构
     */
    private String inoutItem;

    /**
     * 机构代码
     */
    private String compNo;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 生效日
     */
    private Date vdate;

    /**
     * 出入类型包括入池、出池
     */
    private String inoutType;

    /**
     * 取得 准入池代码
     * @return 准入池代码
     */
    public String getAccpoolId() {
        return accpoolId;
    }

    /**
     * 设置 准入池代码
     * @param accpoolId 准入池代码
     */
    public void setAccpoolId(String accpoolId) {
        this.accpoolId = accpoolId == null ? null : accpoolId.trim();
    }

    /**
     * 取得 出入项类型包括资产、机构
     * @return 出入项类型包括资产、机构
     */
    public String getInoutItem() {
        return inoutItem;
    }

    /**
     * 设置 出入项类型包括资产、机构
     * @param inoutItem 出入项类型包括资产、机构
     */
    public void setInoutItem(String inoutItem) {
        this.inoutItem = inoutItem == null ? null : inoutItem.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getCompNo() {
        return compNo;
    }

    /**
     * 设置 机构代码
     * @param compNo 机构代码
     */
    public void setCompNo(String compNo) {
        this.compNo = compNo == null ? null : compNo.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 生效日
     * @param vdate 生效日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 出入类型包括入池、出池
     * @return 出入类型包括入池、出池
     */
    public String getInoutType() {
        return inoutType;
    }

    /**
     * 设置 出入类型包括入池、出池
     * @param inoutType 出入类型包括入池、出池
     */
    public void setInoutType(String inoutType) {
        this.inoutType = inoutType == null ? null : inoutType.trim();
    }
}