/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto;

/**
 * ValManualVoucherTemplete001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月8日
 * @see ValManualVoucherTemplete001SearchDto
 * @since 1.0
 */
public class ValManualVoucherTemplete001SearchDto {

    /**
     * 账套代码
     */
    private String tmpCode;

    /**
     * 账套代码
     */
    private String tmpName;

    /**
     * 账套代码
     */
    private String busiType;

    /**
     * 是否显示分录
     */
    private String isShowfl;

    public String getTmpCode() {
        return tmpCode;
    }

    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    public String getTmpName() {
        return tmpName;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getIsShowfl() {
        return isShowfl;
    }

    public void setIsShowfl(String isShowfl) {
        this.isShowfl = isShowfl;
    }
}
