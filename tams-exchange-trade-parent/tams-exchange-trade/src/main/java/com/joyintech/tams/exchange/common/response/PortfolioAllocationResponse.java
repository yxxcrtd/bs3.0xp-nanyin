package com.joyintech.tams.exchange.common.response;

import com.joyintech.tams.exchange.common.db.domain.Instruction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "公平分配", description = "公平分配")
public class PortfolioAllocationResponse {

    @ApiModelProperty(value = "指令列表")
    private List<Instruction> list;

    @ApiModelProperty(value = "调整后的委托价格")
    private BigDecimal price;

    @ApiModelProperty(value = "试算委托总数量")
    private Long totalEntrustVolume;

    @ApiModelProperty(value = "剩余委托总数量")
    private Long totalLeftVolume;

    @ApiModelProperty(value = "警告信息")
    private String info;

    public List<Instruction> getList() {
        return list;
    }

    public void setList(List<Instruction> list) {
        this.list = list;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getTotalEntrustVolume() {
        return totalEntrustVolume;
    }

    public void setTotalEntrustVolume(Long totalEntrustVolume) {
        this.totalEntrustVolume = totalEntrustVolume;
    }

    public Long getTotalLeftVolume() {
        return totalLeftVolume;
    }

    public void setTotalLeftVolume(Long totalLeftVolume) {
        this.totalLeftVolume = totalLeftVolume;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "PortfolioAllocationResponse{" +
                "list=" + list +
                ", price=" + price +
                ", totalEntrustVolume=" + totalEntrustVolume +
                ", totalLeftVolume=" + totalLeftVolume +
                ", info='" + info + '\'' +
                '}';
    }
}



