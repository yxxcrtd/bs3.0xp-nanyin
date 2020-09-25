/*
 * 文件名：FinProductEstablishShowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.establish.dto;

/**
 * 收益型产品认申购双击查看
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年3月1日
 * @see FinProductEstablishShowDto
 * @since 1.0
 */
public class FinProductEstablishShowDto extends FinProductEstablishDto{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4347379455184853459L;
    /**
     * 是否续投 continue_Flag
     */
    private String continueFlag;
    /**
     * 处理模式
     */
    private String dealMode;
    /**
     * 获取 continueFlag
     * @return continueFlag.
     */
    public String getContinueFlag() {
        return continueFlag;
    }
    /**
     * 设置 continueFlag
     * @param continueFlag continueFlag
     */
    public void setContinueFlag(String continueFlag) {
        this.continueFlag = continueFlag;
    }
    /**
     * 获取 dealMode
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }
    /**
     * 设置 dealMode
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }
    
}
