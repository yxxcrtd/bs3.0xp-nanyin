package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbMstCounterPartyErrordata extends BaseEntity {
    /**
     * uuid主键
     */
    private String datauuid;

    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * 交易对手名称
     */
    private String counterName;

    /**
     * 交易对手一级类型
     */
    private String counterType1;

    /**
     * 交易对手二级类型
     */
    private String counterType2;

    /**
     * 取得 uuid主键
     * @return uuid主键
     */
    public String getDatauuid() {
        return datauuid;
    }

    /**
     * 设置 uuid主键
     * @param datauuid uuid主键
     */
    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid == null ? null : datauuid.trim();
    }

    /**
     * 取得 交易对手代码
     * @return 交易对手代码
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手代码
     * @param counterId 交易对手代码
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 交易对手名称
     * @return 交易对手名称
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * 设置 交易对手名称
     * @param counterName 交易对手名称
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName == null ? null : counterName.trim();
    }

    /**
     * 取得 交易对手一级类型
     * @return 交易对手一级类型
     */
    public String getCounterType1() {
        return counterType1;
    }

    /**
     * 设置 交易对手一级类型
     * @param counterType1 交易对手一级类型
     */
    public void setCounterType1(String counterType1) {
        this.counterType1 = counterType1 == null ? null : counterType1.trim();
    }

    /**
     * 取得 交易对手二级类型
     * @return 交易对手二级类型
     */
    public String getCounterType2() {
        return counterType2;
    }

    /**
     * 设置 交易对手二级类型
     * @param counterType2 交易对手二级类型
     */
    public void setCounterType2(String counterType2) {
        this.counterType2 = counterType2 == null ? null : counterType2.trim();
    }
}