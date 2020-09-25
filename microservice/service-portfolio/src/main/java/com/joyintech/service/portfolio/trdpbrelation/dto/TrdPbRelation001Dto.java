/*
 * 文件名：TrdPbRelation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：caoxue
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.portfolio.trdpbrelation.dto;


import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.TrdPbRelation;
/**
 * PB编号管理dto
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月25日
 * @see TrdPbRelation001Dto
 * @since 1.0
 *
 */
public class TrdPbRelation001Dto extends TrdPbRelation implements ITableRow {
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2785845093600767995L;
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
		return selectedItem;
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
}
