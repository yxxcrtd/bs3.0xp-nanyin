package com.joyintech.tams.invest.entrust.etribrepo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.EtrSecStock;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 */
@PageId("EtrIbRepoRk")
public class EtrIbRepoRk005Dto extends EtrSecStockConfig implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6774775512798679356L;
	
	/**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getConfigId() {
        return super.getConfigId();
    }
    
    /**
     * 不触发预占切面
     */
    private String otherFlg;
    
    public String getOtherFlg() {
        return DictCodeListExt1.是或否_是.getValue();
    }

    public void setOtherFlg(String otherFlg) {
        this.otherFlg = otherFlg;
    }
	
}
