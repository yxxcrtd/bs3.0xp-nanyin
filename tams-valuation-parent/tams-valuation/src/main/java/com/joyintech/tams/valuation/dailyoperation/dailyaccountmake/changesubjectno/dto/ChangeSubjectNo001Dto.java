package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto;
/**
 * @author yanya
 * @create 2020-12-12 14:50
 */

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.validator.ValChangeSubjectNo001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @ClassName ChangeSubjectNo001Dto
 * @Description: 科目变更dto
 * @Author yanya
 * @Date 2020/12/28
 * @Version V1.0
 **/
@ValidLogic(targetClass = ValChangeSubjectNo001Validator.class)
public class ChangeSubjectNo001Dto extends BaseEntity implements ProcessEntity {


    private static final long serialVersionUID = 3715730290535061007L;

    /**
     * 科目号变更处理类型
     */
    private String changeSubjectType;

    /**
     * 账套代码
     */
    private String booksetIds;

    /**
     * 原科目号
     */
    private String oriSubjectNo;

    /**
     * 变更后的科目号
     */
    private String changeSubjectNo;

    /**
     * 变更后的科目名称
     */
    private String changeSubjectName;

    /**
     * 批量是的文件名称
     */
    private String fileName;


    // add by zwq 2021-01-05 start
    // 金融产品类型
    private String finprodType;
    // 核算分类
    private String type2;
    // 交易场所
    private String tradeMarket;
    // 是否其他债权类资产
    private String isQtzq;

    public String getIsQtzq() {
        return isQtzq;
    }

    public void setIsQtzq(String isQtzq) {
        this.isQtzq = isQtzq;
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
// add by zwq 2021-01-05 end

    public String getChangeSubjectType() {
        return changeSubjectType;
    }

    public void setChangeSubjectType(String changeSubjectType) {
        this.changeSubjectType = changeSubjectType;
    }

    public String getBooksetIds() {
        return booksetIds;
    }

    public void setBooksetIds(String booksetIds) {
        this.booksetIds = booksetIds;
    }

    public String getOriSubjectNo() {
        return oriSubjectNo;
    }

    public void setOriSubjectNo(String oriSubjectNo) {
        this.oriSubjectNo = oriSubjectNo;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
