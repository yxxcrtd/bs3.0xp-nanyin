/*
 * 文件名：FinProductScheduling001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.scheduling.dto;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 理财产品排期管理
 * 
 * @author zhangliang
 * @version 1.0 2018年11月14日
 * @see FinProductScheduling001Dto
 * @since 1.0
 */
@PageId("FinProductScheduling002")
public class FinProductScheduling001Dto extends PaPrdIssueSchedule implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6116954489838798886L;

    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    @PrimaryKey(Field.PK_2)
    public String getFinprodMarketId() {
        return super.getFinprodMarketId();
    }
    
//    @PrimaryKey(Field.PK_3)
//    public String getFinprodNameMd5() {
//        return EncryptUtil.getInstance().md5(super.getFinprodName());
//    }
//
//    @PrimaryKey(Field.PK_4)
//    public String getFinprodAbbrMd5() {
//        return EncryptUtil.getInstance().md5(super.getFinprodAbbr());
//    }
    
    /**
     * 操作类型
     */
    private String operateType;

    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    
}
