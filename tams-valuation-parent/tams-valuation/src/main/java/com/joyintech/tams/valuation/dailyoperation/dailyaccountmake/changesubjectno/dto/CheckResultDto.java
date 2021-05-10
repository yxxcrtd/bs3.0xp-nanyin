package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto;
/**
 * @author yanya
 * @create 2020-12-12 20:32
 */

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

/**
 * @ClassName CheckResultDto
 * @Description: 校验结果
 * @Author yanya
 * @Date 2020/12/29 0029 
 * @Version V1.0
 **/
public class CheckResultDto extends BaseEntity {


    private static final long serialVersionUID = -5958728660042792059L;

    /**
     * 校验结果
     */
    private String resultCode;

    /**
     * 校验描述
     */
    private String resultDesc;
    /**
     * 执行SQL
     */
    private String execSql;

    /**
     * 文件校验结果
     */
    private List<ImportCheckResultDto> importCheckList;

    /**
     * 解析后的原始结果list
     */
    private List<List<String>> dataList;

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

    public List<ImportCheckResultDto> getImportCheckList() {
        return importCheckList;
    }

    public void setImportCheckList(List<ImportCheckResultDto> importCheckList) {
        this.importCheckList = importCheckList;
    }

    public List<List<String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<String>> dataList) {
        this.dataList = dataList;
    }

    public String getExecSql() {
        return execSql;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }
}
