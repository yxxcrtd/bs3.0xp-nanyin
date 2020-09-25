/*
 * 文件名：EtrInvestInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bond.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.EtrPledge;

/**
 * 投资委托信息dto 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author huwei
 * @version 1.0 2018年8月30日
 * @see BaseEtrInvestInfoDto
 * @since 1.0
 */
public class BaseEtrInvestInfoDto {

    /**
     * 委托指令dto
     */
    private EtrEntrust etrEntrust;

    /**
     * 委托指令附属表dto
     */
    private EtrEntrustAdd etrEntrustAdd;

    /**
     * 委托指令回购质押品list
     */
    private List<EtrPledge> etrPledgeList;

    /**
     * 委托指令分发list
     */
    private List<EtrDistribute> etrDistributeList;

    /**
     * 委托完成状况表list
     */
    private List<EtrFinishStatus> etrFinishStatusList;

    /**
     * 清算速度
     */
    private String settleSpeedStr;

    /**
     * 金融产品名称
     */
    private String finprodName;
    

    /**
     * 通道代码
     */
    private String chlId;
    /**
     * 可委托金额
     */
    private BigDecimal doEntrustMoney;

    public BigDecimal getDoEntrustMoney() {
        return doEntrustMoney;
    }

    public void setDoEntrustMoney(BigDecimal doEntrustMoney) {
        this.doEntrustMoney = doEntrustMoney;
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 获取 etrEntrust
     *
     * @return etrEntrust.
     */
    public EtrEntrust getEtrEntrust() {
        return etrEntrust;
    }

    /**
     * 设置 etrEntrust
     *
     * @param etrEntrust etrEntrust
     */
    public void setEtrEntrust(EtrEntrust etrEntrust) {
        this.etrEntrust = etrEntrust;
    }

    /**
     * 获取 etrEntrustAdd
     *
     * @return etrEntrustAdd.
     */
    public EtrEntrustAdd getEtrEntrustAdd() {
        return etrEntrustAdd;
    }

    /**
     * 设置 etrEntrustAdd
     *
     * @param etrEntrustAdd etrEntrustAdd
     */
    public void setEtrEntrustAdd(EtrEntrustAdd etrEntrustAdd) {
        this.etrEntrustAdd = etrEntrustAdd;
    }

    /**
     * 获取 etrPledgeList
     *
     * @return etrPledgeList.
     */
    public List<EtrPledge> getEtrPledgeList() {
        return etrPledgeList;
    }

    /**
     * 设置 etrPledgeList
     *
     * @param etrPledgeList etrPledgeList
     */
    public void setEtrPledgeList(List<EtrPledge> etrPledgeList) {
        this.etrPledgeList = etrPledgeList;
    }

    /**
     * 获取 etrDistributeList
     *
     * @return etrDistributeList.
     */
    public List<EtrDistribute> getEtrDistributeList() {
        return etrDistributeList;
    }

    /**
     * 设置 etrDistributeList
     *
     * @param etrDistributeList etrDistributeList
     */
    public void setEtrDistributeList(List<EtrDistribute> etrDistributeList) {
        this.etrDistributeList = etrDistributeList;
    }

    /**
     * 获取 etrFinishStatusList
     *
     * @return etrFinishStatusList.
     */
    public List<EtrFinishStatus> getEtrFinishStatusList() {
        return etrFinishStatusList;
    }

    /**
     * 设置 etrFinishStatusList
     *
     * @param etrFinishStatusList etrFinishStatusList
     */
    public void setEtrFinishStatusList(List<EtrFinishStatus> etrFinishStatusList) {
        this.etrFinishStatusList = etrFinishStatusList;
    }

    /**
     * 获取 settleSpeedStr
     *
     * @return settleSpeedStr.
     */
    public String getSettleSpeedStr() {
        return settleSpeedStr;
    }

    /**
     * 设置 settleSpeedStr
     *
     * @param settleSpeedStr settleSpeedStr
     */
    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    /**
     * 获取 finprodName
     *
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

}
