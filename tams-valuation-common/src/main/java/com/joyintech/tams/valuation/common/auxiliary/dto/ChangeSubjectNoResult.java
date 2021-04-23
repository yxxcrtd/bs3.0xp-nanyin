package com.joyintech.tams.valuation.common.auxiliary.dto;
/**
 * @author yanya
 * @create 2020-12-12 19:06
 */

import java.io.Serializable;

/**
 * @ClassName ChangeSubjectNoResult
 * @Description: 科目号变更结果
 * @Author yanya
 * @Date 2020/12/29
 * @Version V1.0
 **/
public class ChangeSubjectNoResult implements Serializable {

    private static final long serialVersionUID = 5500598452326660432L;

    /**
     * 构造函数
     * @param rowIndexNo
     * @param resultCode
     * @param resultDesc
     */
    public ChangeSubjectNoResult(int rowIndexNo, String resultCode, String resultDesc) {
        this.rowIndexNo = rowIndexNo;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }
    /**
     * 行号
     */
    private int rowIndexNo;

    /**
     * 结果代码
     */
    private String resultCode;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 执行SQL
     */
    private String execSql;

    public ChangeSubjectNoResult() {

    }

    public int getRowIndexNo() {
        return rowIndexNo;
    }

    public void setRowIndexNo(int rowIndexNo) {
        this.rowIndexNo = rowIndexNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getExecSql() {
        return execSql;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }
}
