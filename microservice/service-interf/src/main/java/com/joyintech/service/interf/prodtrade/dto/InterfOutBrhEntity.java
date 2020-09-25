/*
 * 文件名：InterfOutBrhEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodtrade.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfBranch;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口输出分行信息
 * 
 * @author yjm
 * @version 1.0 2018年11月7日
 * @see InterfOutBrhEntity
 * @since 1.0
 */
public class InterfOutBrhEntity extends InterfBranch {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 906840450347625869L;

    @EsbName(value = "BranchId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBranchid(String branchid) {
        super.setBranchid(branchid);
    }

    @EsbName(value = "BranchName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBankname(String bankname) {
        super.setBankname(bankname);
    }

    @EsbName(value = "BranchBuyAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setBranchbuyamount(BigDecimal branchbuyamount) {
        super.setBranchbuyamount(branchbuyamount);
    }

    @EsbName(value = "BranchBuyNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @Override
    public void setBranchbuynum(Short branchbuynum) {
        super.setBranchbuynum(branchbuynum);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BranchAccountAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setBranchaccountamount(BigDecimal branchaccountamount) {
        super.setBranchaccountamount(branchaccountamount);
    }

    /**
     * 449183接口.本金、449283接口.本金
     * 
     * @param branchaccountamount 本金
     * @see
     * @since 1.0
     */
    @EsbName(value = "BranchCurrentAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setBranchCurrentAmount(BigDecimal branchaccountamount) {
        super.setBranchaccountamount(branchaccountamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BranchDrawAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setBranchdrawamount(BigDecimal branchdrawamount) {
        super.setBranchdrawamount(branchdrawamount);
    }

    /**
     * 设置 分行号
     * 
     * @param branchId 分行号
     */
    @EsbName(value = "BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBranchId2(String branchId) {
        super.setBranchid(branchId);
    }

    /**
     * 设置 分行名称
     * 
     * @param branchName 分行名称
     */
    @EsbName(value = "BRANCH_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBranchName(String branchName) {
        super.setBankname(branchName);
    }

    /**
     * 设置 本金
     * 
     * @param corpus 本金
     */
    @EsbName(value = "CORPUS")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setCorpus(BigDecimal corpus) {
        super.setBranchaccountamount(corpus);
    }

    /**
     * 设置 募集
     * 
     * @param collect 募集
     */
    @EsbName(value = "COLLECT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setCollect(BigDecimal collect) {
        super.setBranchbuyamount(collect);
    }

    /**
     * 设置 赎回
     * 
     * @param redeem 赎回
     */
    @EsbName(value = "REDEEM")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setRedeem(BigDecimal redeem) {
        super.setBranchdrawamount(redeem);
    }
}
