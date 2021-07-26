/*
 * 文件名：RskRedisParamConfigDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.redismgr.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskBusinessRedisConfig;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.redismgr.validate.RskRedisParamConfigValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 
 * redis参数配置
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskRedisParamConfigDto
 * @since 1.0
 */
@PageId("RskRedisParamConfig002")
@ValidLogic(targetClass = RskRedisParamConfigValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class RskRedisParamConfigDto extends RskBusinessRedisConfig implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    /**
     * 画面Id - 工作流参数
     */
    private String pageId;

	/**
	 * 画面Id - 参数名称
	 */
    private String paraName;
    
    /**
     * 选择数据
     */
    private List<RskRedisParamConfigDto> selectedItem;

	@Override
	@PrimaryKey(value=Field.PK_1)
	@NotEmpty(fieldName = "RskRedisParamConfig001.commonBusinessType",groups={ValidateGroupEdit.class})
	public String getCommonBusinessType() {
		return StringUtil.isNotEmpty(super.getCommonBusinessType())?super.getCommonBusinessType():StringUtil.EMPTY;
	}

    @Override
    @PrimaryKey(value=Field.PK_2)
	@NotEmpty(fieldName = "RskRedisParamConfig001.paraCode",groups={ValidateGroupEdit.class})
    public String getParaCode() {
        return StringUtil.isNotEmpty(super.getParaCode())?super.getParaCode():StringUtil.EMPTY;
    }

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public List<RskRedisParamConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RskRedisParamConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getCommonBusinessTypeName() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.共通业务类型, this.getCommonBusinessType());
	}
	 public String getRedisModelName(){
    	 return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.REDIS模块, this.getRedisModel());
    }

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
}
