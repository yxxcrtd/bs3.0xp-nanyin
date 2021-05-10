package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto;
/**
 * @author yanya
 * @create 2020-12-12 20:32
 */

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.auxiliary.dto.ResultCode;

/**
 * @ClassName ImportCheckDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2020/12/29 0029 
 * @Version V1.0
 **/
public class ImportCheckResultDto extends BaseEntity {

    private static final long serialVersionUID = 6399029452696059015L;
    /**
     * 行号
     */
    private Integer rowIndexNo;

    /**
     * 行号
     */
    private String rowIndexNoName;

    /**
     * 结果代码
     */
    private String resultCode;

    /**
     * 结果代码说明
     */
    private String validKey;

    /**
     * 结果描述
     */
    private String resultDesc;

    public Integer getRowIndexNo() {
        return rowIndexNo;
    }

    public void setRowIndexNo(Integer rowIndexNo) {
        this.rowIndexNo = rowIndexNo;
    }

    public String getRowIndexNoName() {
        if(StringUtil.isNotEmpty(this.getRowIndexNo())) {
            return "第" + this.getRowIndexNo() + "行";
        }
        return rowIndexNoName;
    }

    public void setRowIndexNoName(String rowIndexNoName) {
        this.rowIndexNoName = rowIndexNoName;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getValidKey() {
        if(StringUtil.isNotEmpty(this.getResultCode())) {
            if(StringUtil.equals(this.getResultCode(), ResultCode.SUCCESS.getCode())) {
                return ResultCode.SUCCESS.getName();
            }
            if(StringUtil.equals(this.getResultCode(), ResultCode.FAILURE.getCode())) {
                return ResultCode.FAILURE.getName();
            }
            if(StringUtil.equals(this.getResultCode(), ResultCode.REMIND.getCode())) {
                return ResultCode.REMIND.getName();
            }
        }
        return validKey;
    }

    public void setValidKey(String validKey) {
        this.validKey = validKey;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
