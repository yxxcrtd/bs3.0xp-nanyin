/*
 * 文件名：BokVouchDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 11, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.dto;

import com.joyintech.tams.common.base.entity.BokVouchDetail;

/**
 * 记账凭证明细
 * 
 * @author zhangwei
 * @version 1.0 Apr 11, 2019
 * @see BokVouchDetailDto
 * @since 1.0
 */
public class BokVouchDetailDto extends BokVouchDetail {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

}
