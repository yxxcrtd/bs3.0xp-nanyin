/*
 * 文件名：FinProductFeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.prod.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstFeeinfo;
/**
 * 费用信息
 * 
 * @author 张亮
 * @version 1.0 2018年9月29日
 * @see FinProductFeeInfoDto
 * @since 1.0
 */
public class FinProductFeeInfoDto extends MstFeeinfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1247450102095317057L;
    /**
     * 选择数据
     */
    private List<FinProductFeeInfoDto> selectedItem;

    public List<FinProductFeeInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FinProductFeeInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

}
