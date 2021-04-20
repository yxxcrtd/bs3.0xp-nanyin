package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCounterPartyInfo extends BaseEntity {
    /**
     * 主键
     */
    private String seqno;

    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * 交易对手席位
     */
    private String counterSeatId;

    /**
     * 对手方交易员
     */
    private String counterTraderId;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
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
     * 取得 交易对手席位
     * @return 交易对手席位
     */
    public String getCounterSeatId() {
        return counterSeatId;
    }

    /**
     * 设置 交易对手席位
     * @param counterSeatId 交易对手席位
     */
    public void setCounterSeatId(String counterSeatId) {
        this.counterSeatId = counterSeatId == null ? null : counterSeatId.trim();
    }

    /**
     * 取得 对手方交易员
     * @return 对手方交易员
     */
    public String getCounterTraderId() {
        return counterTraderId;
    }

    /**
     * 设置 对手方交易员
     * @param counterTraderId 对手方交易员
     */
    public void setCounterTraderId(String counterTraderId) {
        this.counterTraderId = counterTraderId == null ? null : counterTraderId.trim();
    }
}