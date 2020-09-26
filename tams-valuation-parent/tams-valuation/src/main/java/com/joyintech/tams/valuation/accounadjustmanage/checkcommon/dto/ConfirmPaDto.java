package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto;

/**
 * 对账相关实体类
 * 
 * @author yangjian
 * @version 1.0 2018年11月15日
 * @see ConfirmPaDto
 * @since 1.0
 */
public class ConfirmPaDto {

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 估值日期
     */
    private String valDateString;

    /**
     * 估值表余额表标志
     */
    private String valBalFlag;

    /**
     * 本方文件类型
     */
    private String myFileType;

    /**
     * 对方文件类型
     */
    private String otherFileType;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 valDateString
     * 
     * @return valDateString.
     */
    public String getValDateString() {
        return valDateString;
    }

    /**
     * 设置 valDateString
     * 
     * @param valDateString valDateString
     */
    public void setValDateString(String valDateString) {
        this.valDateString = valDateString;
    }

    /**
     * 获取 valBalFlag
     * 
     * @return valBalFlag.
     */
    public String getValBalFlag() {
        return valBalFlag;
    }

    /**
     * 设置 valBalFlag
     * 
     * @param valBalFlag valBalFlag
     */
    public void setValBalFlag(String valBalFlag) {
        this.valBalFlag = valBalFlag;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 myFileType
     * 
     * @return myFileType.
     */
    public String getMyFileType() {
        return myFileType;
    }

    /**
     * 设置 myFileType
     * 
     * @param myFileType myFileType
     */
    public void setMyFileType(String myFileType) {
        this.myFileType = myFileType;
    }

    /**
     * 获取 otherFileType
     * 
     * @return otherFileType.
     */
    public String getOtherFileType() {
        return otherFileType;
    }

    /**
     * 设置 otherFileType
     * 
     * @param otherFileType otherFileType
     */
    public void setOtherFileType(String otherFileType) {
        this.otherFileType = otherFileType;
    }

}
