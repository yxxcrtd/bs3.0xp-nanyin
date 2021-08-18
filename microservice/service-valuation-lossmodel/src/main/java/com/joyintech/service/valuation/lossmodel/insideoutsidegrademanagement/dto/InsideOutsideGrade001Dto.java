package com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class InsideOutsideGrade001Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 评级机构
     */
    private String gradeInst;

    /**
     * 对象类型
     */
    private String oType;

    /**
     * 外部评级
     */
    private String gradeResult;

    /**
     * 内部评级
     */
    private String gradeStandardResult;

    private List<InsideOutsideGradeDetail001Dto> selectInsideOutsideDetail;

    public List<InsideOutsideGradeDetail001Dto> getSelectInsideOutsideDetail() {
        return selectInsideOutsideDetail;
    }

    public void setSelectInsideOutsideDetail(List<InsideOutsideGradeDetail001Dto> selectInsideOutsideDetail) {
        this.selectInsideOutsideDetail = selectInsideOutsideDetail;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getGradeInst() {
        return gradeInst;
    }

    public void setGradeInst(String gradeInst) {
        this.gradeInst = gradeInst;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public String getGradeStandardResult() {
        return gradeStandardResult;
    }

    public void setGradeStandardResult(String gradeStandardResult) {
        this.gradeStandardResult = gradeStandardResult;
    }
}
