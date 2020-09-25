package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RskAuthorizeMng extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 角色岗位
     */
    private String rolePos;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 资产类型

     */
    private String assetType;

    /**
     * 资产二级分类

     */
    private String assetSecType;

    /**
     * 交易场所

     */
    private String tradeMarket;

    /**
     * 交易方向

     */
    private String tradeDirection;

    /**
     * 授权类型
     */
    private String authorizeType;

    /**
     * 授权额度

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal authorizeLimitAmt;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 角色岗位
     * @return 角色岗位
     */
    public String getRolePos() {
        return rolePos;
    }

    /**
     * 设置 角色岗位
     * @param rolePos 角色岗位
     */
    public void setRolePos(String rolePos) {
        this.rolePos = rolePos == null ? null : rolePos.trim();
    }

    /**
     * 取得 用户名
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 取得 资产类型

     * @return 资产类型

     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型

     * @param assetType 资产类型

     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产二级分类

     * @return 资产二级分类

     */
    public String getAssetSecType() {
        return assetSecType;
    }

    /**
     * 设置 资产二级分类

     * @param assetSecType 资产二级分类

     */
    public void setAssetSecType(String assetSecType) {
        this.assetSecType = assetSecType == null ? null : assetSecType.trim();
    }

    /**
     * 取得 交易场所

     * @return 交易场所

     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所

     * @param tradeMarket 交易场所

     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易方向

     * @return 交易方向

     */
    public String getTradeDirection() {
        return tradeDirection;
    }

    /**
     * 设置 交易方向

     * @param tradeDirection 交易方向

     */
    public void setTradeDirection(String tradeDirection) {
        this.tradeDirection = tradeDirection == null ? null : tradeDirection.trim();
    }

    /**
     * 取得 授权类型
     * @return 授权类型
     */
    public String getAuthorizeType() {
        return authorizeType;
    }

    /**
     * 设置 授权类型
     * @param authorizeType 授权类型
     */
    public void setAuthorizeType(String authorizeType) {
        this.authorizeType = authorizeType == null ? null : authorizeType.trim();
    }

    /**
     * 取得 授权额度

     * @return 授权额度

     */
    public BigDecimal getAuthorizeLimitAmt() {
        return authorizeLimitAmt;
    }

    /**
     * 设置 授权额度

     * @param authorizeLimitAmt 授权额度

     */
    public void setAuthorizeLimitAmt(BigDecimal authorizeLimitAmt) {
        this.authorizeLimitAmt = authorizeLimitAmt;
    }
}