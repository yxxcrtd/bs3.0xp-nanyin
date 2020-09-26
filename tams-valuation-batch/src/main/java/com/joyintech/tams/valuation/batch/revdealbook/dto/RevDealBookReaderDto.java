/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 撤销查询明细
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see RevDealBookReaderDto
 * @since 1.0
 */
public class RevDealBookReaderDto extends NeedOperateEntity {

    // 撤销查询明细数据
    private List<RevDealBookDetailDto> listRevDealBookDetailDto;

    
    /**
     * 获取 listRevDealBookDetailDto
     * @return listRevDealBookDetailDto.
     */
    public List<RevDealBookDetailDto> getListRevDealBookDetailDto() {
        return listRevDealBookDetailDto;
    }

    
    /**
     * 设置 listRevDealBookDetailDto
     * @param listRevDealBookDetailDto listRevDealBookDetailDto
     */
    public void setListRevDealBookDetailDto(List<RevDealBookDetailDto> listRevDealBookDetailDto) {
        this.listRevDealBookDetailDto = listRevDealBookDetailDto;
    }

 
}
