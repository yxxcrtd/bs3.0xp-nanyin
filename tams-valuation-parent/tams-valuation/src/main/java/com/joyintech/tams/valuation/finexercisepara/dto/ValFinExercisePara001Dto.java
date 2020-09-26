/*
 * 文件名：ValFinExercisePara001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.finexercisepara.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 含权债摊销参数设置
 * 
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara001Dto
 * @since 1.0
 */
public class ValFinExercisePara001Dto extends BaseEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 含权债摊销日
     */
    private String exeDateType;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getExeDateType() {
        return SysDataUtil.getNameFromDataDict(DictCode.含权债摊销日,
            exeDateType) == null ? exeDateType : SysDataUtil.getNameFromDataDict(DictCode.含权债摊销日, exeDateType);
    }

    public void setExeDateType(String exeDateType) {
        this.exeDateType = exeDateType;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

}
