package com.joyintech.tams.invest.common.trade.dto;

import java.math.BigDecimal;

/**
 *  下单信息dto
 * @author fuyongsheng
 * @version 1.0 2022年3月3日
 * @see PortfolioCalculateDto
 * @since 1.0
 */
public class OrderInfoDto {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 指令编号
     */
    private String instructionId;

    /**
     * 下单券面总额
     */
    private BigDecimal entrustVolume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public BigDecimal getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(BigDecimal entrustVolume) {
        this.entrustVolume = entrustVolume;
    }
}
