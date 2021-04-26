package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProdMdateUpdate001Dto
 * @Description: 核算到期日调整Dto
 * @Author yanya
 * @Date 2021/3/1 0001 
 * @Version V1.0
 **/
public class ProdMdateUpdate001Dto implements Serializable {

    private static final long serialVersionUID = -5425672617503822863L;

    /**产品代码**/
    @ValInterfApiField
    private String prodId;
    /**产品类型**/
    @ValInterfApiField(dictCode = "FINPROD_TYPE")
    private String  prodType;
    /**分支序号**/
    @ValInterfApiField
    private Long branch;
    /**到期日**/
    @ValInterfApiField
    private Date mdate;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    @Override
    public String toString() {
        return "ProdMdateUpdate001Dto{" +
                "prodId='" + prodId + '\'' +
                ", prodType='" + prodType + '\'' +
                ", branch=" + branch +
                ", mdate=" + mdate +
                '}';
    }
}
