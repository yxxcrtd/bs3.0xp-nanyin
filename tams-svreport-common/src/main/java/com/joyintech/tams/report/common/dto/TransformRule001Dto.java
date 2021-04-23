package com.joyintech.tams.report.common.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 变形转换规则 dto
 *
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRule001Dto
 * @since 1.0
 */
public class TransformRule001Dto extends BaseEntity {
    /**
     * 删除list
     */
    private List<TransformRule001Dto> deleteList;
    /**
     * 操作类型
     */
    private String operateType;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4407775860808560346L;
	

	/**
	 * 数据状态
	 */
	private String seqno;
	

	/**
	 * 数据状态
	 */
	private String dataStatus;

	/**
	 * 数据状态Str
	 */
	private String statusName;

	/**
	 * 类型
	 */
	private String transType;

	/**
	 * 类型Str
	 */
	private String transTypeStr;

	/**
	 * 原始值
	 */
	private String orgField;

	/**
	 * 拼音
	 */
	private String pinyin;

	/**
	 * unicode码
	 */
	private String fieldUnicode;

	/**
	 * 转化后值
	 */
	private String targetField;

	/**
	 * 是否可维护Str
	 */
	private String isModifyStr;

	/**
	 * updateUser
	 */
	private String updateUser;
	
	/**
	 * 是否可维护
	 */
	private String isModify;
	
	/**
	 * createUser
	 */
	private String createUser;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 备注
	 */
	private String createDept;
	
	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getIsModify() {
		return isModify;
	}

	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}

	public String getIsModifyStr() {
		return isModifyStr;
	}

	public void setIsModifyStr(String isModifyStr) {
		this.isModifyStr = isModifyStr;
	}

	public String getFieldUnicode() {
		return fieldUnicode;
	}

	public void setFieldUnicode(String fieldUnicode) {
		this.fieldUnicode = fieldUnicode;
	}


	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getOrgField() {
		return orgField;
	}

	public void setOrgField(String orgField) {
		this.orgField = orgField;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransTypeStr() {
		return transTypeStr;
	}

	public void setTransTypeStr(String transTypeStr) {
		this.transTypeStr = transTypeStr;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<TransformRule001Dto> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<TransformRule001Dto> deleteList) {
		this.deleteList = deleteList;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

}
