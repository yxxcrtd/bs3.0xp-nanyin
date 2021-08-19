package com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:MOD_ASSET_GRADE_SPECIAL_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-06-24
 */
public class ModAssetGradeSpecialConfigDto extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 流水号
     */
    private String seqno;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;
    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型）
     */
    private String oType;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 金融产品代码
     * @return FINPROD_ID 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 金融产品名称
     * @return FINPROD_NAME 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 评级机构（数据字典 评级机构）
     * @return GRADE_ORG_ID 评级机构（数据字典 评级机构）
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 评级机构（数据字典 评级机构）
     * @param gradeOrgId 评级机构（数据字典 评级机构）
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 评级类型（数据字典 评级类型）
     * @return O_TYPE 评级类型（数据字典 评级类型）
     */
    public String getoType() {
        return oType;
    }

    /**
     * 评级类型（数据字典 评级类型）
     * @param oType 评级类型（数据字典 评级类型）
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }



    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    @Override
	public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    @Override
	public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    @Override
	public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    @Override
	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    @Override
	public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    @Override
	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }
}