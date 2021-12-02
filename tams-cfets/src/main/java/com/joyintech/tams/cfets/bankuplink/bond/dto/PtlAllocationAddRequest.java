package com.joyintech.tams.cfets.bankuplink.bond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zznn
 * @date 2021/11/12
 */
@ApiModel(value="组合分配添加请求参数",description="组合分配添加请求参数")
public class PtlAllocationAddRequest {

    @ApiModelProperty(required = true,name="finprodId",value="资产代码")
    private String finprodId;

    @ApiModelProperty(required = true,name="ps",value="交易方向")
    private String ps;

    @ApiModelProperty(required = true,name="settleSpreed",value="清算速度")
    private String settleSpreed;

    @ApiModelProperty(required = true,name="entrustIdList",value="排除已经选择的指令")
    private List<String> entrustIdList;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSettleSpreed() {
        return settleSpreed;
    }

    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    public List<String> getEntrustIdList() {
        return entrustIdList;
    }

    public void setEntrustIdList(List<String> entrustIdList) {
        this.entrustIdList = entrustIdList;
    }
}

