/*
 * 文件名：FinProductEstablishBranchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.establish.dto;

import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;


/**
 * 分行募集信息
 * 
 * @author zhangliang
 * @version 1.0 2018年12月25日
 * @see FinProductEstablishBranchDto
 * @since 1.0
 */
public class FinProductEstablishBranchDto extends PaBranchSaleDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7737986376465387776L;

    /**
     * 分行名称
     */
    private String branchName;

    
    /**
     * 获取 branchName
     * @return branchName.
     */
    public String getBranchName() {
        return branchName;
    }

    
    /**
     * 设置 branchName
     * @param branchName branchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    
}
