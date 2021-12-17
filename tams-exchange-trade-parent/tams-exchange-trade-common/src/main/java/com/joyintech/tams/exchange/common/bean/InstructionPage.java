package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author swwan
 * @create 2020-03-20 11:45
 */
@ApiModel(value="指令查询返回报文",description="指令查询返回报文")
public class InstructionPage<T, V> implements Serializable {
    private static final long serialVersionUID = 3325317592745725894L;
    @ApiModelProperty(name="AMOUNT",value="数量")
    private int AMOUNT;
    @ApiModelProperty(name="pageCount",value="页数")
    private int pageCount;
    @ApiModelProperty(name="etr_entrust_list",value="指令列表")
    private List<T> etr_entrust_list;
    @ApiModelProperty(name="etr_pledge_list",value="质押券列表")
    private List<V> etr_pledge_list;

    public InstructionPage() {
        this.AMOUNT = 0;
        this.pageCount = 1;
    }

    public InstructionPage(List<T> etr_entrust_list, List<V> etr_pledge_list) {
        this.etr_entrust_list = etr_entrust_list;
        this.etr_pledge_list = etr_pledge_list;
    }

    public int getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(int AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getEtr_entrust_list() {
        return etr_entrust_list;
    }

    public void setEtr_entrust_list(List<T> etr_entrust_list) {
        this.etr_entrust_list = etr_entrust_list;
    }

    public List<V> getEtr_pledge_list() {
        return etr_pledge_list;
    }

    public void setEtr_pledge_list(List<V> etr_pledge_list) {
        this.etr_pledge_list = etr_pledge_list;
    }
}
