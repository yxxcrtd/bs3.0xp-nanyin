/*
 * 文件名：ValueAddedTax001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TaxDeclareSumInfo;
import com.joyintech.tams.constant.DictCode;

public class ValueAddedTax001Dto extends TaxDeclareSumInfo implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2016667813772620305L;
    
    /**
     * 产品代码
     */
    private String finprodId;
    
    /**
     * 产品名称
     */
    private String finprodName;
    
    /**
     * 核算主体类别/
     * @return
     */
   private String acctSubjectType;
   
   /**
    * 账套代码
    */
   private String[] booksetIds;
   
    public String getFinprodId() {
        return finprodId;
    }
    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    public String getFinprodName() {
        return finprodName;
    }
    
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getAcctSubjectType() {
        return acctSubjectType;
    }
    
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }
    
    public String[] getBooksetIds() {
        return booksetIds;
    }
    
    public void setBooksetIds(String[] booksetIds) {
        this.booksetIds = booksetIds;
    }

}
