package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;

/**
 * @ClassName ProdAcctInit001Dto
 * @Description: 产品账户表初始化
 * @Author yanya
 * @Date 2021/2/8
 * @Version V1.0
 **/
public class ProdAcctInit001Dto implements Serializable {

    private static final long serialVersionUID = 5486612151174507057L;
    /**
     * 金融产品代码
     */
    @ValInterfApiField(fieldVal="F16,F22,F18")
    private String finprodId;

    /**
     * 账户账号，存网点账号
     */
    @ValInterfApiField
    private String acctNo;

    /**
     * 币种
     */
    @ValInterfApiField
    private String ccy;

    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * 接口中不需要该字段，该字段根据产品代码进行查找生成，用于后续判定
     */
    private String finprodType;

    /**
     * 账户类型
     */
    @ValInterfApiField
    private String acctType;


    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}
