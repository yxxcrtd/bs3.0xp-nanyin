/*
 * 文件名：ValProfitQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.dto;

import com.joyintech.tams.common.base.entity.ValTableIns;

/**
 * 利润表数据
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitDto
 * @since 1.0
 */
public class ValProfitDto extends ValTableIns {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5129540391864256053L;

    /**
     * 排序号
     */
    private int sortNum;

    /**
     * 获取 排序号
     * 
     * @return 排序号
     */
    public int getSortNum() {
        return sortNum;
    }

    /**
     * 设置 排序号
     * 
     * @param sortNum 排序号
     */
    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

}
