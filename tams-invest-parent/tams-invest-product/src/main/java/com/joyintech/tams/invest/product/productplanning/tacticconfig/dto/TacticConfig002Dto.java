package com.joyintech.tams.invest.product.productplanning.tacticconfig.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.validator.TacticConfig002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@PageId("TacticConfig002")
@ValidLogic(targetClass = TacticConfig002Validator.class,groups = {ValidateGroupEdit.class})
public class TacticConfig002Dto extends BaseEntity implements ProcessEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程判断
	 */
	private String pkFlag = "PRDPLAN02";
	 /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    /**
     * 画面Id
     */
    private String pageId;
    
    /**
     * elementId
     */
    private String elementId;

	/**
     * 战术配置编号
     */
    private String tacticConfigCode;

    /**
     * 开始日
     */
    @NotEmpty(fieldName="TacticConfig001.vdate",groups = {ValidateGroupEdit.class})
    private Date vdate;

    /**
     * 结束日
     */
    @NotEmpty(fieldName="TacticConfig001.edate",groups = {ValidateGroupEdit.class})
    private Date edate;

    /**
     * 投研支持观点
     */
    private String opinionRinv;
    
    private List<TacticConfigOptDetail> tacticConfigOptDetails;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 战术配置编号
     * @return 战术配置编号
     */
    @PrimaryKey(Field.PK_1)
    public String getTacticConfigCode() {
        return tacticConfigCode;
    }

    /**
     * 设置 战术配置编号
     * @param tacticConfigCode 战术配置编号
     */
    public void setTacticConfigCode(String tacticConfigCode) {
        this.tacticConfigCode = tacticConfigCode == null ? null : tacticConfigCode.trim();
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * 设置 结束日
     * @param edate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 取得 投研支持观点
     * @return 投研支持观点
     */
    public String getOpinionRinv() {
        return opinionRinv;
    }

    /**
     * 设置 投研支持观点
     * @param opinionRinv 投研支持观点
     */
    public void setOpinionRinv(String opinionRinv) {
        this.opinionRinv = opinionRinv == null ? null : opinionRinv.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public List<TacticConfigOptDetail> getTacticConfigOptDetails() {
		return tacticConfigOptDetails;
	}

	public void setTacticConfigOptDetails(List<TacticConfigOptDetail> tacticConfigOptDetails) {
		this.tacticConfigOptDetails = tacticConfigOptDetails;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@PrimaryKey(Field.PK_2)
	public String getPkFlag() {
		return pkFlag;
	}

	public void setPkFlag(String pkFlag) {
		this.pkFlag = pkFlag;
	}
    
}
