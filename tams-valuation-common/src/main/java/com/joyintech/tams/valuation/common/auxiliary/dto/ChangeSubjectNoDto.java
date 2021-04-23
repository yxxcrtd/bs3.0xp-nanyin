package com.joyintech.tams.valuation.common.auxiliary.dto;
/**
 * @author yanya
 * @create 2020-12-12 14:50
 */

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ChangeSubjectNoDto
 * @Description: 科目变更dto
 * @Author yanya
 * @Date 2020/12/28
 * @Version V1.0
 **/
public class ChangeSubjectNoDto implements Serializable {


    private static final long serialVersionUID = -1802599348887820133L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套代码集合
     */
    List<String> booksetIdList;

    /**
     * 原科目号
     */
    private String oriSubjectNo;

    /**
     * 原科目名称
     */
    private String oriSubjectName;

    /**
     * 变更后的科目号
     */
    private String changeSubjectNo;

    /**
     * 变更后的科目名称
     */
    private String changeSubjectName;

    /**
     * 行号
     */
    private int rowIndexNo;

    /**
     * 优先级
     */
    private int rowPri;

    // 替换条件
    private String condition;

    // 金融产品类型
    private String finprodType;
    // 核算分类
    private String type2;
    // 交易场所
    private String tradeMarket;
    // 是否其他债权类资产
    private String isQtzq;
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public List<String> getBooksetIdList() {
        return booksetIdList;
    }

    public void setBooksetIdList(List<String> booksetIdList) {
        this.booksetIdList = booksetIdList;
    }

    public String getOriSubjectNo() {
        return oriSubjectNo;
    }

    public void setOriSubjectNo(String oriSubjectNo) {
        this.oriSubjectNo = oriSubjectNo;
    }

    public String getOriSubjectName() {
        return oriSubjectName;
    }

    public void setOriSubjectName(String oriSubjectName) {
        this.oriSubjectName = oriSubjectName;
    }

    public String getChangeSubjectNo() {
        return changeSubjectNo;
    }

    public void setChangeSubjectNo(String changeSubjectNo) {
        this.changeSubjectNo = changeSubjectNo;
    }

    public String getChangeSubjectName() {
        return changeSubjectName;
    }

    public void setChangeSubjectName(String changeSubjectName) {
        this.changeSubjectName = changeSubjectName;
    }

    public int getRowIndexNo() {
        return rowIndexNo;
    }

    public void setRowIndexNo(int rowIndexNo) {
        this.rowIndexNo = rowIndexNo;
    }

    public int getRowPri() {
        return rowPri;
    }

    public void setRowPri(int rowPri) {
        this.rowPri = rowPri;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getIsQtzq() {
        return isQtzq;
    }

    public void setIsQtzq(String isQtzq) {
        this.isQtzq = isQtzq;
    }
}
