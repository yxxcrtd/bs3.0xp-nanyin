package com.joyintech.tams.valuation.lossmodel.phaseparameter.dto;

import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCode;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorConfigDto;
import org.apache.commons.lang3.StringUtils;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class ModStageParaDto extends BaseEntity implements ITableRow, ProcessEntity{
	
	private static final long serialVersionUID = 0L;
	
	/**
	 * 唯一标识
	 */
	private String seqno;
	/**
	 * 资产所属阶段(数据字典 减值阶段)
	 */
	private String stageOfAeest;
	/**
	 * 要素绝对性(数据字典 要素绝对性)
	 */
	private String  absoluteOfElements; 
	/**
	 * 要素(数据字典 减值要素)
	 */
	private String elements;
	/**
	 * 要素值
	 */
	private String elementsValue;
	/**
	 * 输入文本框值(天数，评级信息调级数)
	 */
	private String inputValue;
	/**
	 * 公司评级信息下拉框
	 */
	private String selectRateValue;
	/**
	 * 风险五级分类多选
	 */
	private String selectRskValue; 
	
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
    private transient List<PdFactorConfigDto> selectedItem;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	
	@PrimaryKey(Field.PK_1)
	@NotEmpty(fieldName = "ModStagePara001.stageOfAeest", groups = {ValidateGroupEdit.class})
	public String getStageOfAeest() {
		return stageOfAeest;
	}
	
	public String getStageOfAeestStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.减值阶段, this.getStageOfAeest());
	}
	
	public void setStageOfAeest(String stageOfAeest) {
		this.stageOfAeest = stageOfAeest;
	}
	@NotEmpty(fieldName = "ModStagePara001.absoluteOfElements", groups = {ValidateGroupEdit.class})
	public String getAbsoluteOfElements() {
		return absoluteOfElements;
	}
	
	public String getAbsoluteOfElementsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.要素绝对性, this.getAbsoluteOfElements());
	}
	
	public void setAbsoluteOfElements(String absoluteOfElements) {
		this.absoluteOfElements = absoluteOfElements;
	}
	
	@PrimaryKey(Field.PK_2)
	@NotEmpty(fieldName = "ModStagePara001.elements", groups = {ValidateGroupEdit.class})
	public String getElements() {
		return elements;
	}
	
	public String getElementsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.减值要素, this.getElements());
	}

	public void setElements(String elements) {
		this.elements = elements;
	}
	
	//@NotEmpty(fieldName = "ModStagePara001.elementsValue", groups = {ValidateGroupEdit.class})
	public String getElementsValue() {
		return elementsValue;
	}

	public String getElementsValueStr() {
		String result = this.getElementsValue();
		if(DictCodeListExt3.减值要素_公司评级信息低于.getValue().equals(this.getElements())) {
			result = SysDataUtil.getNameFromDataDict(DictCode.评级结果, this.getElementsValue());
		}else if(DictCodeListExt3.减值要素_风险五级分类.getValue().equals(this.getElements())){// 风险五级可多选
			if(StringUtils.isNoneBlank(result)) {
				String[] list = result.split(",");
				result = "";
				for (String item : list) {
					result += SysDataUtil.getNameFromDataDict(DictCode.五级分类, item)+",";
		        }
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	public void setElementsValue(String elementsValue) {
		this.elementsValue = elementsValue;
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

	public List<PdFactorConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<PdFactorConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getSelectRateValue() {
		return selectRateValue;
	}

	public void setSelectRateValue(String selectRateValue) {
		this.selectRateValue = selectRateValue;
	}

	public String getSelectRskValue() {
		return selectRskValue;
	}

	public void setSelectRskValue(String selectRskValue) {
		this.selectRskValue = selectRskValue;
	}
	
    
    
}
