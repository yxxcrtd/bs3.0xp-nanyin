package com.joyintech.tams.svreport.chb.common.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.base.TableRow;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

/**
 * 功能:描述:导出删除模板的实体类
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月31日
 * @修改记录：
 */
public class ChbDeleteExcelExportDetailDto{
    
    /**
     * 删除数据的技术主键
     */
    private String seqno;
    
    /**
     * 产品登记编码
     */
    private String col1;
    
    /**
     * 业务结束日
     */
    private String col2;
    
    /**
     * 行内资产/负债编码
     */
    private String col3;
    
    /**
     * 产品登记编码
     */
    private String col4;
    
    /**
     * 行内交易编码
     */
    private String col5;
    
    /**
     * 资金流动类型
     */
    private String col6;
    
    /**
     * 行内资产/负债编码
     */
    private String col7;
    
    /**
     * 行内资产/负债编码
     */
    private String col8;
    
    /**
     * 估值日期
     */
    private String col9;
    
    /**
     * 对应资管及委外资产行内资产/负债编码
     */
    private String col10;
    
    /**
     * 底层资产行内资产/负债编码
     */
    private String col11;
    
    /**
     * 持仓日期
     */
    private String col12;
    
    /**
     * 删除原因
     */
    private String col13;

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getCol8() {
        return col8;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public String getCol9() {
        return col9;
    }

    public void setCol9(String col9) {
        this.col9 = col9;
    }

    public String getCol10() {
        return col10;
    }

    public void setCol10(String col10) {
        this.col10 = col10;
    }

    public String getCol11() {
        return col11;
    }

    public void setCol11(String col11) {
        this.col11 = col11;
    }

    public String getCol12() {
        return col12;
    }

    public void setCol12(String col12) {
        this.col12 = col12;
    }

    public String getCol13() {
        return col13;
    }

    public void setCol13(String col13) {
        this.col13 = col13;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    
}