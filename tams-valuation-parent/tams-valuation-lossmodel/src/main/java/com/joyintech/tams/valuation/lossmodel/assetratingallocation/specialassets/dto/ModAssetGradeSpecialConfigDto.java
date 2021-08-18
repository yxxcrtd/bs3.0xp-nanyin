package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.validator.Specialassets002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.tams.constant.DictCode;

/**
 * 描述:MOD_ASSET_GRADE_SPECIAL_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-06-24
 */
@PageId("Specialassets002")
@ValidLogic(targetClass = Specialassets002Validator.class)
public class ModAssetGradeSpecialConfigDto extends BaseEntity implements ITableRow, ProcessEntity{
    /**
	 * s
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
     * 操作类型
     */
    private String operateType;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 选中的数据
     */
    private transient List<ModAssetGradeSpecialConfigDto> selectedItem;

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
    @PrimaryKey(Field.PK_1)
    @NotEmpty(fieldName = "Specialassets.MESG001", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "Specialassets.MESG002", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "Specialassets.MESG003", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "Specialassets.MESG004", groups = {ValidateGroupEdit.class})
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
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<ModAssetGradeSpecialConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ModAssetGradeSpecialConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getGradeOrgIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构, this.getGradeOrgId());
    }
	public String getoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级类型_减值, this.getoType());
    }
}