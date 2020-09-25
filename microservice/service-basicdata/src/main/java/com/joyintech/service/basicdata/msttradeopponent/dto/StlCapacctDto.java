/*
 * 文件名：StlCapacctDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.msttradeopponent.dto;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;

/**
 * 清算账户实体类
 * @author fanshuanghu
 * @version 1.0 2018年8月23日
 * @see StlCapacctDto
 * @since 1.0
 */
public class StlCapacctDto extends StlCapacctInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2308580382718194005L;
    /**
     * 交易对手代码
     */
    private String counterId;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 账号类型
     */
    private String accountType;
    /**
     * 是否启用
     */
    private String isOpen;
    
    /**
     * 交易对手对应的code
     */
    private String isOpenVal;
    /**
     * 账号对应的code
     */
    private String accountTypeVal;
    
    
    /**
     * 获取 isOPenVal
     * @return isOPenVal.
     */
    public String getIsOpenVal() {
        return isOpenVal;
    }



    
    /**
     * 设置 isOPenVal
     * @param isOPenVal isOPenVal
     */
    public void setIsOpenVal(String isOpenVal) {
        this.isOpenVal = isOpenVal;
    }



    
    /**
     * 获取 accountTypeVal
     * @return accountTypeVal.
     */
    public String getAccountTypeVal() {
        return accountTypeVal;
    }



    
    /**
     * 设置 accountTypeVal
     * @param accountTypeVal accountTypeVal
     */
    public void setAccountTypeVal(String accountTypeVal) {
        this.accountTypeVal = accountTypeVal;
    }



    /**
     * 获取 accountName
     * @return accountName.
     */
    public String getAccountName() {
        return accountName;
    }


    
    /**
     * 设置 accountName
     * @param accountName accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    
    /**
     * 获取 accountType
     * @return accountType.
     */
    public String getAccountType() {
        return accountType;
    }


    
    /**
     * 设置 accountType
     * @param accountType accountType
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    /**
     * 获取 isOpen
     * @return isOpen.
     */
    public String getIsOpen() {
        return isOpen;
    }

    
    /**
     * 设置 isOpen
     * @param isOpen isOpen
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取 counterId
     * @return counterId.
     */
    public String getCounterId() {
        return counterId;
    }
    
    /**
     * 设置 counterId
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }
    @Override
    public String getAcctNo(){
        return super.getAcctNo();
    }
    @Override
    public String getCcy(){
        return super.getCcy();
    }
}
