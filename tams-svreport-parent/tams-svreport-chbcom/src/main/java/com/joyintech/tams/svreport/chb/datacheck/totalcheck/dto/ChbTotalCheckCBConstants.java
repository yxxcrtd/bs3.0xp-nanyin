package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto;

/**
 * 功能:中债总体核查批处理常量类
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月19日
 * @修改记录：
 */
public class ChbTotalCheckCBConstants {
	/**
	 * 产品份额
	 */
    public static final String PRDQTY = "prdqty";
    
    /**
     * 产品净值
     */
    public static final String PRDVALUE = "prdvalue";
    
    /**
     * 募集币种
     */
	public static final String PRDRAISECCY = "raiseccy";
    
    /**
     * 产品id
     */
    public static final String PRDID = "prodid";
    
    /**
     * 币种及申购兑付金额
     */
    public static final String CCYCASH = "ccycash";
	/**
	 * 公募理财产品
	 */
    public static final String GMLCCP="GMLCCP"; 
    /**
     * 私募理财产品
     */ 
    public static final String SMLCCP="SMLCCP";  
    /** 
     * 保本理财产品（不含结构性存款）
     */
    public final static String BBLCCP="BBLCCP";  
    /**
     * 人民币
     */
    public static final String RMB="RMB";
    /**
     * 封闭
     */
    public static final String PROD_TYPE_C="02";
    /**
     * 1.存续余额与月度报表对比
     */
    public static final String CHECKTYPE01 = "01";
    /**
     * 2.产品存续金额和资产持仓偏差情况
     */
    public static final String CHECKTYPE02 = "02";
    /**
     * 3.募集总量登记明细
     */
    public static final String CHECKTYPE03 = "03";
    
    /**
     * 4.月末底层持仓报送情况
     */
    public static final String CHECKTYPE04 = "04";
    
    /**
     * 5.资产持仓为负的产品
     */
    public static final String CHECKTYPE05 = "05";
    
    /**
     * 6.现金持仓为负的产品
     */
    public static final String CHECKTYPE06 = "06";
    
    /**
     * 7.交易价格偏差过大的资产
     */
    public static final String CHECKTYPE07 = "07";
    
    /**
     * 8.资管计划底层金额偏差过大
     */
    public static final String CHECKTYPE08 = "08";
    /**
     * 1.存续余额与月度报表对比
     */
    public static final String CHECKTYPE01_TIPS = "计算规则：100%-绝对值（存续余额-月度统计表存续余额）/月度统计表存续余额*100%";
    /**
     * 2.产品存续金额和资产持仓偏差情况
     */
    public static final String CHECKTYPE02_TIPS = "计算规则：100%-偏差过大封闭式产品数/存续封闭式产品数*100%";
    /**
     * 3.募集总量登记明细
     */
    public static final String CHECKTYPE03_TIPS = "计算规则：已报送数量/应报送数量*100%";
    
    /**
     * 4.月末底层持仓报送情况
     */
    public static final String CHECKTYPE04_TIPS = "计算规则：实报底层持仓资产数/应报底层持仓资产数*100%";
    
    /**
     * 5.资产持仓为负的产品
     */
    public static final String CHECKTYPE05_TIPS = "计算规则：100%-存在持仓为负的理财产品只数/存续产品数*100%";
    
    /**
     * 6.现金持仓为负的产品
     */
    public static final String CHECKTYPE06_TIPS = "计算规则：100%-存在现金持仓为负的理财产品只数/存续产品数*100%";
    
    /**
     * 7.交易价格偏差过大的资产
     */
    public static final String CHECKTYPE07_TIPS = "计算规则：100%-交易价格偏差过大资产对应的产品数/登记系统存续产品数*100%";
    
    /**
     * 8.资管计划底层金额偏差过大
     */
    public static final String CHECKTYPE08_TIPS = "计算规则：100%-计划发行金额与资产市值偏差过大数量/报送底层的计划数*100%";
}
