/*
 * 文件名：InterfLc0010GradeLvEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0010.entity;

import com.joyintech.tams.common.base.entity.InterfCmsInstitutionGrade;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 机构评级信息entity
 * 
 * @author changwenbin
 * @version 1.0 2019年1月3日
 * @see InterfLc0010GradeLvEntity
 * @since 1.0
 */
public class InterfLc0010GradeLvEntity extends InterfCmsInstitutionGrade {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3903933832691113419L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "GRADE_ORG_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setGradeOrgId(String gradeOrgId) {
        super.setGradeOrgId(gradeOrgId);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "GRADE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setGradeType(String gradeType) {
        super.setGradeType(gradeType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "GRADE_RESULT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setGradeResult(String gradeResult) {
        super.setGradeResult(gradeResult);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "GRADE_RPT_YEAR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setGradeRptYear(String gradeRptYear) {
        super.setGradeRptYear(gradeRptYear);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "GRADE_MODEL_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setGradeModelType(String gradeModelType) {
        super.setGradeModelType(gradeModelType);
    }

    /**
     * 设置评级生效日
     * 
     * @param gradeEffDate 评级生效日
     * @see
     * @since 1.0
     */
    @EsbName(value = "GRADE_EFF_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setGradeEffDateStr(String gradeEffDate) {
        super.setGradeEffDate(DateUtil.parse(gradeEffDate));
    }

    /**
     * 设置 评级到期日
     * 
     * @param gradeEndDate 评级到期日
     * @see
     * @since 1.0
     */
    @EsbName(value = "GRADE_END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setGradeEndDateStr(String gradeEndDate) {
        super.setGradeEndDate(DateUtil.parse(gradeEndDate));
    }

}
