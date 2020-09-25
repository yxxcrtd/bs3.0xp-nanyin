/*
 * 文件名：RepFinProductPerIssRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperissregist.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.util.DateUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月20日
 * @see RepFinProductPerIssRegistDto
 * @since 1.0
 */
public class RepFinProductPerIssRegistDto extends RepProdIssue{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 数据日期
     */
    private Date dataDate;
    /**
     * 发行机构
     */
    private String issuerCode;
    
    /**
     * 理财产品代码
     */
    private String repFinprodInsId;
    /**
     * 产品名称
     */
    private String repFinprodName;
    public Date getDataDate() {
        return dataDate;
    }
    
    public String getDataDateStr() {
        return DateUtil.format(dataDate);
    }
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
    
    public String getIssuerCode() {
        return issuerCode;
    }
    
    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }
    
    public String getRepFinprodName() {
        return repFinprodName;
    }

    
    public void setRepFinprodName(String repFinprodName) {
        this.repFinprodName = repFinprodName;
    }

    
    public String getRepFinprodInsId() {
        return repFinprodInsId;
    }

    
    public void setRepFinprodInsId(String repFinprodInsId) {
        this.repFinprodInsId = repFinprodInsId;
    }
    
}
