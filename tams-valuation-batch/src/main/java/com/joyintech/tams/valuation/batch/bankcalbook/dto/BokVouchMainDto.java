/*
 * 文件名：BokVouchMainDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 11, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.joyintech.tams.common.base.entity.BokVouchMain;

/**
 * 记账凭证主表 DTO
 * @author zhangwei
 * @version 1.0 Apr 11, 2019
 * @see BokVouchMainDto
 * @since 1.0
 */
public class BokVouchMainDto extends BokVouchMain {

    List<BokVouchDetailDto> listBokVouchDetailDto = Lists.newArrayList();

    public List<BokVouchDetailDto> getListBokVouchDetailDto() {
        return listBokVouchDetailDto;
    }

    public void setListBokVouchDetailDto(List<BokVouchDetailDto> listBokVouchDetailDto) {
        this.listBokVouchDetailDto = listBokVouchDetailDto;
    }

}
