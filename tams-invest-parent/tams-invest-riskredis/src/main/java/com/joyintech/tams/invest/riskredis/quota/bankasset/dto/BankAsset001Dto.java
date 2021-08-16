package com.joyintech.tams.invest.riskredis.quota.bankasset.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.MstBankNetvalue;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2020年12月29日
 * @see BankAsset001Dto
 * @since 1.0
 */
@PageId("PltMedianManagement001")
public class BankAsset001Dto extends MstBankNetvalue implements ITableRow,ProcessEntity {

	/**
	 * 市场名称
	 */
	private String customerName;
	
	/**
	 * 有效状态
	 */
	private String statusStr;
	
	/**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 选中的数据列表
     */
    private transient List<BankAsset001Dto> selectedItem;
    
    /**
     * 界面类型
     */
    private String pageModel;
    
    /**
     * 画面元素
     */
    private String elementId;
    
    /**
     * 
     * 组织机构代码
     */
    private String insitituteCode;
    
    /**
     * 更新时间格式化
     * 
     */
    private String effDateStr;
    
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getValStatusStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.商业银行净资产维护_有效状态,getValStatus());
	}
	
	public String getStatus() {
		if(StringUtil.equals(BussinessDictCodeListRds.商业银行净资产维护_有效状态_失效.getValue(), super.getValStatus())){
			return "D";
		}else{
			return "E";
		}
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<BankAsset001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<BankAsset001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	
	@PrimaryKey(Field.PK_3)
    public String getCustomerId() {
        return super.getCustomerId();
    }
	
    /**
     * 取得 更新时间字符串格式
     * 
     * @return 更新时间
     */
    @PrimaryKey(Field.PK_2)
    public String getEffDateStr() {
        return DateUtil.format(super.getEffDate());
    }

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	@PrimaryKey(Field.PK_1)
	public String getInsitituteCode() {
		return insitituteCode;
	}

	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
	
	
}
