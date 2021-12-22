package com.joyintech.tams.exchange.common.bean.bond;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="标准券可用总数量",description="标准券可用总数量")
public class StandardBondBean implements Serializable {
    private static final long serialVersionUID = 5125496055165648793L;
    @ApiModelProperty(name = "szStandardBondTotal", value = "深证标准券可用总数量")
    private int szStandardBondTotal;//深证标准券可用总数量
    
    @ApiModelProperty(name = "shStandardBondTotal", value = "上海标准券可用总数量")
    private int shStandardBondTotal;//上海标准券可用总数量

    public int getSzStandardBondTotal() {
        return szStandardBondTotal;
    }

    public void setSzStandardBondTotal(int szStandardBondTotal) {
        this.szStandardBondTotal = szStandardBondTotal;
    }

    public int getShStandardBondTotal() {
        return shStandardBondTotal;
    }

    public void setShStandardBondTotal(int shStandardBondTotal) {
        this.shStandardBondTotal = shStandardBondTotal;
    }
}
