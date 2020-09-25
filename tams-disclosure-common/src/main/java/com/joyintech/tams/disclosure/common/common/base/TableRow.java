/*
 * 文件名：TableRow.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.disclosure.common.common.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 〈一句话功能简述〉
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月14日
 * @see TableRow
 * @since 1.0
 */
public class TableRow implements ITableRow {

    /**
     * TR节点ID属性
     */
    @JsonProperty("ID")
    private String rowId;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

	@Override
	public String getCreateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateUser(String createUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreateDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDept(String createDept) {
		// TODO Auto-generated method stub
		
	}

}
