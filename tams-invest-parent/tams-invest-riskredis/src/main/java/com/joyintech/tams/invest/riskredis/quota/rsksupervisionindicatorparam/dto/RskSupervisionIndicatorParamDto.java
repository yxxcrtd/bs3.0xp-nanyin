package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.validator.RskSupervisionIndicatorParamValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParamDto
 * @since 1.0
 */
@PageId("RskSupervisionIndicatorParam002")
@ValidLogic(targetClass = RskSupervisionIndicatorParamValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class RskSupervisionIndicatorParamDto extends RskIsupIndicatorParaDtl implements ITableRow,ProcessEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 操作类型
     */
    private String operateType;
    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 参数名称
     */
    private String paraName;
    /**
     * 有效日期
     */
    private Date validDate;
    /**
     * 主动提醒日期
     */
    private Date notifyDate;
    
    private String pageId;
    
    private List<RskIsupIndicatorParaDtl> rskIsupIndicatorParaDtlList;
    /**
     * 买入预占
     */
    private String mrContent;
    /**
     * 卖出预占
     */
    private String mcContent;
    /**
     * 事后(日间)
     */
    private String rjContent;
    /**
     * 事后(日终)
     */
    private String rzContent;
    /**
     * 禁可(投)可选项
     */
    private String forbidEnum;
    /**
     * 是否禁(可)投类
     */
    private String isForbid;
    private String paraType;

	/**
	 * 是否更新redis
	 */
	private String isUpRedis;
	/**
	 * 参数数据类型
	 */
	private String paramDataType;
    /**
     * 取得 参数代码
     * @return 参数代码
     */
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "RskSupervisionIndicatorParam002.paraCode",groups = {ValidateGroupEdit.class})
    public String getParaCode() {
        return super.getParaCode();
    }
    
    /**
     * 取得 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     * @return 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     */
    @NotEmpty(fieldName = "RskSupervisionIndicatorParam002.paraDisplayType",groups = {ValidateGroupEdit.class})
    public String getParaDisplayType() {
        return super.getParaDisplayType();
    }
    @NotEmpty(fieldName = "RskSupervisionIndicatorParam002.content",groups = {ValidateGroupEdit.class})
    public String getContent(){
    	return super.getContent();
    }
    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     *
     * @return pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 paraName
     *
     * @return paraName
     */
    @NotEmpty(fieldName = "RskSupervisionIndicatorParam002.paraName",groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
    public String getParaName() {
        return paraName;
    }

    /**
     * 设置 paraName
     *
     * @param paraName paraName
     */
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public Date getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public List<RskIsupIndicatorParaDtl> getRskIsupIndicatorParaDtlList() {
		return rskIsupIndicatorParaDtlList;
	}

	public void setRskIsupIndicatorParaDtlList(List<RskIsupIndicatorParaDtl> rskIsupIndicatorParaDtlList) {
		this.rskIsupIndicatorParaDtlList = rskIsupIndicatorParaDtlList;
	}

	public String getMrContent() {
		return mrContent;
	}

	public void setMrContent(String mrContent) {
		this.mrContent = mrContent;
	}

	public String getMcContent() {
		return mcContent;
	}

	public void setMcContent(String mcContent) {
		this.mcContent = mcContent;
	}
	@NotEmpty(fieldName = "RskSupervisionIndicatorParam002.rjContent",groups = {ValidateGroupEdit.class})
	public String getRjContent() {
		return rjContent;
	}
	
	public void setRjContent(String rjContent) {
		this.rjContent = rjContent;
	}
	@NotEmpty(fieldName = "RskSupervisionIndicatorParam002.rzContent",groups = {ValidateGroupEdit.class})
	public String getRzContent() {
		return rzContent;
	}

	public void setRzContent(String rzContent) {
		this.rzContent = rzContent;
	}

	public String getForbidEnum() {
		return forbidEnum;
	}

	public void setForbidEnum(String forbidEnum) {
		this.forbidEnum = forbidEnum;
	}

	public String getIsForbid() {
		return isForbid;
	}

	public void setIsForbid(String isForbid) {
		this.isForbid = isForbid;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	
	public String getParaTypeStr(){
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标参数实现类型, this.getParaType());
	}
	public String getIsUpRedis() {
		return isUpRedis;
	}

	public void setIsUpRedis(String isUpRedis) {
		this.isUpRedis = isUpRedis;
	}
	public String getParamDataType() {
		return paramDataType;
	}

	public void setParamDataType(String paramDataType) {
		this.paramDataType = paramDataType;
	}
    
}
