package com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfig;

import java.math.BigDecimal;

/**
 * @author jinmaodong
 * @date 2021/3/20
 * @since 1.0.0
 *  原始配置信息不够，需要添加些信息
 **/
public class FeeForwadDrawConfigDto extends TrdAccountmfConfig {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 金融产品代码-费用所属产品的金融产品代码
     */
    private String finprodiId;
    /**
     * 金融产品类型-费用所属产品的金融产品类型
     */
    private String finprodType;
    /**
     * 分层代码
     */
    private String layeringId;
    /**
     *  级别序号
     */
    private String lev;

    /**
     * 分支序号
     */
    private BigDecimal branch;
    /**
     * 账务明细代码
     */
    private String bokDetailId;

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public BigDecimal getBranch() {
        return branch;
    }

    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    public String getFinprodiId() {
        return finprodiId;
    }

    public void setFinprodiId(String finprodiId) {
        this.finprodiId = finprodiId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getLayeringId() {
        return layeringId;
    }

    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }
}
