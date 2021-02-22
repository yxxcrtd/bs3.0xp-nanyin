package com.joyintech.tams.common.base.entity;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 产品客户数信息
 * @author wang
 * @create 2021-02-22 10:33
 */
public class ChbPrdCustNumIfo {

    /**
     * 唯一标识
     */
    private String prdCode;
    /**
     * 检查结果
     */
    private String InspectionResults;
    /**
     * 警告信息项
     */
    private String WarningInformation;
    /**
     * 数据日期
     */
    private Date DataOfDate;
    /**
     * 持有客户数
     */
    private BigDecimal CustHeldNum;
    /**
     * 三期客户数
     */
    private BigDecimal ThrCustNum;


}
