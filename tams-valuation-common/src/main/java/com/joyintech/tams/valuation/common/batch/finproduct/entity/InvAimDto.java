/*
 * 文件名：InvAimDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：lihuadong
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.batch.finproduct.entity;


/**
 * 投资目的 DTO
 * @author lihuadong
 * @version 1.0 2018年10月24日
 * @see InvAimDto
 * @since 1.0
 */
public class InvAimDto {
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 估值方案条数
     */
    private Integer schemeCnt;
    /**
     * 获取 invAim
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }
    /**
     * 设置 invAim
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }
    /**
     * 获取 schemeCnt
     * @return schemeCnt.
     */
    public Integer getSchemeCnt() {
        return schemeCnt;
    }
    /**
     * 设置 schemeCnt
     * @param schemeCnt schemeCnt
     */
    public void setSchemeCnt(Integer schemeCnt) {
        this.schemeCnt = schemeCnt;
    }

}
