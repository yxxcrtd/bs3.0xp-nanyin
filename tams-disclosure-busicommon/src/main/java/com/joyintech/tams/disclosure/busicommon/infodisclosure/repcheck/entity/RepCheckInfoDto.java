/*
 * 文件名：RepCheckInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity;

import com.joyintech.tams.common.base.entity.RepCheck;

/**
 * 校验内容dto
 * 
 * @author wanghb
 * @version 1.0 2019年3月20日
 * @see RepCheckInfoDto
 * @since 1.0
 */
public class RepCheckInfoDto extends RepCheck {

    /**
     * 报表编码
     */
    private String repId;

    /**
     * 校验位置
     */
    private String checkFor;

    /**
     * 获取 repId
     * 
     * @return repId.
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 repId
     * 
     * @param repId repId
     */
    public void setRepId(String repId) {
        this.repId = repId;
    }

    /**
     * 获取 checkFor
     * 
     * @return checkFor.
     */
    public String getCheckFor() {
        return checkFor;
    }

    /**
     * 设置 checkFor
     * 
     * @param checkFor checkFor
     */
    public void setCheckFor(String checkFor) {
        this.checkFor = checkFor;
    }
}
