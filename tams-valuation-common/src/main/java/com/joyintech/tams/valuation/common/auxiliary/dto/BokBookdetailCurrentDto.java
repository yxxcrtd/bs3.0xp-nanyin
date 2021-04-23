package com.joyintech.tams.valuation.common.auxiliary.dto;
/**
 * @author yanya
 * @create 2020-12-12 14:09
 */

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;

import java.util.Date;

/**
 * @ClassName BokBookdetailCurrentDto
 * @Description: 账务明细（当前表）查询返回结果dto
 * @Author yanya
 * @Date 2020/12/28
 * @Version V1.0
 **/
public class BokBookdetailCurrentDto extends BokBookdetailCurrent {

    private static final long serialVersionUID = 4670873884994017862L;
    /**
     * 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    private String busiDetailType;

    /**
     * 四级科目尾号，不含三级科目
     */
    private String detailSubjectNoTail;

    /**
     * 四级科目尾号名称
     */
    private String detailSubnameTail;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 替换科目号
     */
    private String changeSubjectNo;

    /**
     * 替换后的四级科目号
     */
    private String changeDetailSubjectNo;

    public String getBusiDetailType() {
        return busiDetailType;
    }

    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }

    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail;
    }

    public String getDetailSubnameTail() {
        return detailSubnameTail;
    }

    public void setDetailSubnameTail(String detailSubnameTail) {
        this.detailSubnameTail = detailSubnameTail;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getChangeSubjectNo() {
        return changeSubjectNo;
    }

    public void setChangeSubjectNo(String changeSubjectNo) {
        this.changeSubjectNo = changeSubjectNo;
    }

    public String getChangeDetailSubjectNo() {
        return changeDetailSubjectNo;
    }

    public void setChangeDetailSubjectNo(String changeDetailSubjectNo) {
        this.changeDetailSubjectNo = changeDetailSubjectNo;
    }
}
