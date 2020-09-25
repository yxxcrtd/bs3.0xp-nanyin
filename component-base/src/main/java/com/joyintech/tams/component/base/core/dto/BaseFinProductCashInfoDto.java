/*
 * 文件名：BaseFinProductCashInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashInfo;

/**
 * 金融产品现金流信息组件Dto
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月3日
 * @see BaseFinProductCashInfoDto
 * @since 1.0
 */
public class BaseFinProductCashInfoDto extends FinCashInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 现金流信息表的dto的list
     */
    private List<FinCashInfo> FinCashInfoList;
    
    /**
     * 获取 现金流信息表的dto的list
     * @return finCashInfoList.
     */
    public List<FinCashInfo> getFinCashInfoList() {
        return FinCashInfoList;
    }
    
    /**
     * 设置 现金流信息表的dto的list
     * @param finCashInfoList finCashInfoList
     */
    public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
        FinCashInfoList = finCashInfoList;
    }
    

}
