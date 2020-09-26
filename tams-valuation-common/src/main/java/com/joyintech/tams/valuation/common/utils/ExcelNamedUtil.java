/*
 * 文件名：ExcelNamedUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.utils;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 导出Excle命名规则
 * @author wanghb
 * @version 1.0 2018年9月11日
 * @see ExcelNamedUtil
 * @since 1.0
 */
public class ExcelNamedUtil extends StringUtil {
    /**
     * 文件类型
     */
    public static final String EXCEL_SIGN = ".xls";
    /**
     * 杭州银行-估值表标志
     */
    public static final String TABLE_HZGZ = "GZ";
    /**
     * 杭州银行-余额表标志
     */
    public static final String TABLE_HZYE = "YE";
    /**
     * 平安银行-估值表标志
     */
    public static final String TABLE_PAGZ = "_估值表";
    /**
     * 平安银行-余额表标志
     */
    public static final String TABLE_PAYE = "_余额表";
    /**
     * 分隔符
     */
    public static final String SEPERATE = "_";
    /**
     * 产生表名称
     * @param booksetId 账套代码
     * @param balDate 日期
     * @param finprodId 产品代码
     * @param tableSign 表标识    01-估值表   02-余额表
     * @param bankSign 银行标识  01-平安银行  02-杭州银行
     * @return 表名称
     * @see
     * @since 1.0
     */
    public static String generalExcelName(String booksetId, String balDate, String finprodId, String tableSign,String schemeCode) {
        StringBuffer excelName=new StringBuffer();
        // 理财产品模板
        if (DictCodeList.估值模板_理财产品模板.getValue().equals(schemeCode)) {
            balDate=balDate.replaceAll("_", "");
            //估值表
            if("01".equals(tableSign)){
                excelName.append(TABLE_HZGZ);
            }//余额表
            else if("02".equals(tableSign)){
                excelName.append(TABLE_HZYE);
            }
            excelName.append(booksetId==null?finprodId:booksetId).append(balDate).append(EXCEL_SIGN);
        } // 基金模板
        else{
            excelName.append(booksetId==null?finprodId:booksetId).append(SEPERATE).append(balDate).append(SEPERATE);
            // 估值表
            if("01".equals(tableSign)){
                excelName.append(TABLE_PAGZ);
            }//余额表
            else if("02".equals(tableSign)){
                excelName.append(TABLE_PAYE);
            }
            excelName.append(EXCEL_SIGN);
        }
        return excelName.toString();
    }

}
