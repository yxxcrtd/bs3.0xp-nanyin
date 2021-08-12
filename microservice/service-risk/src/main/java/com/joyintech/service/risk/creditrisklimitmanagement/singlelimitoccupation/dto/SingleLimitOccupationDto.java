/*
 * 文件名：CreditLinesDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dto;

import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 单笔资产额度校验规则
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupationDto
 * @since 1.0
 */
public class SingleLimitOccupationDto  extends CreditRuleInfo implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 额度标签名称
     */
    private String creditTagName;
    
    
    /**
     * 资产名称
     */
    private String finprodName;
    
    /**
     * 资产名称
     */
    private int seqno;
    

    
    public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

    public String getCreditTagName() {
		return creditTagName;
	}

	public void setCreditTagName(String creditTagName) {
		this.creditTagName = creditTagName;
	}

	public String getAstType1Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, super.getAstType1());
    }

    public String getAstType2Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产二级分类, super.getAstType2());
    }

    public String getAstType3Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产三级分类, super.getAstType3());
    }

    public String getAstType4Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产四级分类, super.getAstType4());
    }

    public String getAstType5Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产五级分类, super.getAstType5());
    }
	
    public String getDeductObjTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.扣减对象, super.getDeductObjType());
    }

    /**
     * 额度标签
     */
    private String creditTag;

    /**
     * 画面处理模式
     */
    private String pageModel;

   /**
	* 选中列表
	*/
	 private List<SingleLimitOccupationDto> selectedItem;
	
	 public List<SingleLimitOccupationDto> getSelectedItem() {
		return selectedItem;
	 }

	 public void setSelectedItem(List<SingleLimitOccupationDto> selectedItem) {
		this.selectedItem = selectedItem;
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

	/**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    @PrimaryKey(value=Field.PK_1)
    public String getCreditRuleId() {
        return super.getCreditRuleId();
    }

    
    /**
     * 取得 资产代码
     * @return 资产代码
     */
    @NotEmpty(fieldName = "SingleLimitOccupation001.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodId() {
        return super.getFinprodId();
    }

	/**
     * 取得额度标签
     * @return 额度标签
     */
    @NotEmpty(fieldName = "SingleLimitOccupation001.creditTag", groups = {ValidateGroupEdit.class})
    public String getCreditTag() {
        return creditTag;
    }

    /**
     * 设置 额度标签
     * @param creditTag 额度标签
     */
    public void setCreditTag(String creditTag) {
        this.creditTag = creditTag == null ? null : creditTag.trim();
    }
 
}
