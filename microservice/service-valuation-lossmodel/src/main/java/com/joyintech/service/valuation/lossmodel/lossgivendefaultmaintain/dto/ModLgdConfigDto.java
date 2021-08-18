package com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:MOD_LGD_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-06-28
 */
public class ModLgdConfigDto extends BaseEntity{
    /**
     * 序列号
     */
    private String seqno;

    /**
     * 资产类型(数据字典 证券品种_模型)
     */
    private String assetType;

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     */
    private String assetDetailType;

    /**
     * 是否含有抵质押物
     */
    private String isContainPledge;

    /**
     * LGD(%)
     */
    private BigDecimal lgd;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 序列号
     * @return SEQNO 序列号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 序列号
     * @param seqno 序列号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 资产类型(数据字典 证券品种_模型)
     * @return ASSET_TYPE 资产类型(数据字典 证券品种_模型)
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 资产类型(数据字典 证券品种_模型)
     * @param assetType 资产类型(数据字典 证券品种_模型)
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     * @return ASSET_DETAIL_TYPE 资产细分类(数据字典 证券细分类_模型)
     */
    public String getAssetDetailType() {
        return assetDetailType;
    }

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     * @param assetDetailType 资产细分类(数据字典 证券细分类_模型)
     */
    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType == null ? null : assetDetailType.trim();
    }

    /**
     * 是否含有抵质押物
     * @return IS_CONTAIN_PLEDGE 是否含有抵质押物
     */
    public String getIsContainPledge() {
        return isContainPledge;
    }

    /**
     * 是否含有抵质押物
     * @param isContainPledge 是否含有抵质押物
     */
    public void setIsContainPledge(String isContainPledge) {
        this.isContainPledge = isContainPledge == null ? null : isContainPledge.trim();
    }

    /**
     * LGD(%)
     * @return LGD LGD(%)
     */
    public BigDecimal getLgd() {
        return lgd;
    }

    /**
     * LGD(%)
     * @param lgd LGD(%)
     */
    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    @Override
	public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    @Override
	public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    @Override
	public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    @Override
	public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    @Override
	public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    @Override
	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    @Override
	public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    @Override
	public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    @Override
	public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    @Override
	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}