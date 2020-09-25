package com.joyintech.tams.invest.portfolio.trdpbrelation.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdPbRelation;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.trdpbrelation.validator.TrdPbRelation001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * PB编号管理dto
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation001Dto
 * @since 1.0
 *
 */
@ValidLogic(targetClass = TrdPbRelation001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdPbRelation001Dto extends TrdPbRelation implements ITableRow,ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 选中的数据
     */
    private transient List<TrdPbRelation001Dto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 是否新建
     */
    private String isNew;


	public List<TrdPbRelation001Dto> getSelectedItem() {
		return selectedItem==null?new ArrayList<TrdPbRelation001Dto>():selectedItem;
	}


	public void setSelectedItem(List<TrdPbRelation001Dto> selectedItem) {
		this.selectedItem = selectedItem;
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


	public String getElementId() {
		return elementId;
	}


	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	
	
	public String getIsNew() {
		return isNew;
	}


	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}


//	/**
//     * 设置更新日为字符
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @return 返回格式化后的值
//     * @see
//     * @since 1.0
//     */
//    public String getUpdateTimeStr() {
//        return DateUtil.format(super.getUpdateTime());
//    }
	/**
	 * 设置要素类型 <一句话功能简述> <功能详细描述>
	 * 
	 * @return 返回格式化后的值
	 * @see
	 * @since 1.0
	 */
	public String getFactorTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.PB要素类型, super.getFactorType());
	}
}
