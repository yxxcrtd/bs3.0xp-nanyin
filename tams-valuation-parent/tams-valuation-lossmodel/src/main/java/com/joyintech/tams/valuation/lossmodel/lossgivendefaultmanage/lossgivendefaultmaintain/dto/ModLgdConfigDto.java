package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.validator.LossGivenDefaultMaintain002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 描述:MOD_LGD_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-06-28
 */
@PageId("LossGivenDefaultMaintain002")
@ValidLogic(targetClass = LossGivenDefaultMaintain002Validator.class)
public class ModLgdConfigDto extends BaseEntity implements ITableRow, ProcessEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 序列号
     */
    private String seqno;

    /**
     * 资产类型(数据字典 证券品种_模型)
     */
    private String assetType;

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     */
    private String assetDetailType;

    /**
     * 是否含有抵质押物
     */
    private String isContainPledge;

    /**
     * LGD(%)
     */
    private BigDecimal lgd;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

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
    private transient List<ModLgdConfigDto> selectedItem;

    /**
     * 序列号
     * @return SEQNO 序列号
     */
    @PrimaryKey(Field.PK_1)
    public String getSeqno() {
        return seqno;
    }

    /**
     * 序列号
     * @param seqno 序列号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 资产类型(数据字典 证券品种_模型)
     * @return ASSET_TYPE 资产类型(数据字典 证券品种_模型)
     */
    @NotEmpty(fieldName = "LossGivenDefaultMaintain001.assetType", groups = {ValidateGroupEdit.class})
    public String getAssetType() {
        return assetType;
    }

    /**
     * 资产类型(数据字典 证券品种_模型)
     * @param assetType 资产类型(数据字典 证券品种_模型)
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     * @return ASSET_DETAIL_TYPE 资产细分类(数据字典 证券细分类_模型)
     */
    public String getAssetDetailType() {
        return assetDetailType;
    }

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     * @param assetDetailType 资产细分类(数据字典 证券细分类_模型)
     */
    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType == null ? null : assetDetailType.trim();
    }

    /**
     * 是否含有抵质押物
     * @return IS_CONTAIN_PLEDGE 是否含有抵质押物
     */
    public String getIsContainPledge() {
        return isContainPledge;
    }

    /**
     * 是否含有抵质押物
     * @param isContainPledge 是否含有抵质押物
     */
    public void setIsContainPledge(String isContainPledge) {
        this.isContainPledge = isContainPledge == null ? null : isContainPledge.trim();
    }

    /**
     * LGD(%)
     * @return LGD LGD(%)
     */
    @Amount(fieldName = "LossGivenDefaultMaintain001.lgd", maxIntBit = 3, maxDecBit = 2, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "LossGivenDefaultMaintain001.lgd", groups = {ValidateGroupEdit.class})
    public BigDecimal getLgd() {
        return lgd;
    }

    /**
     * LGD(%)
     * @param lgd LGD(%)
     */
    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
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

	public List<ModLgdConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ModLgdConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产类型, this.getAssetType());
    }
	public String getAssetDetailTypeStr() {
		if("-".equals(this.getAssetDetailType())){
			return this.getAssetDetailType();
		}
        return SysDataUtil.getNameFromDataDict(DictCode.资产分类, this.getAssetDetailType());
    }
	public String getIsContainPledgeStr() {
		if("-".equals(this.getIsContainPledge())){
			return this.getIsContainPledge();
		}
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsContainPledge());
    }
}