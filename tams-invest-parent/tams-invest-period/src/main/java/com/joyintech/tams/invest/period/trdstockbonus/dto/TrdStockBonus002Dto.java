/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.DividDetailInfo;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.TrdStockBonusDto;
import com.joyintech.tams.invest.period.trdstockbonus.validator.TrdStockBonus002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

/**股票分红交易dto
 * @author panbin
 * @version 2019年6月18日
 * @see TrdStockBonus002Dto
 */
@PageId("TrdStockBonus002")
@ValidLogic(targetClass = TrdStockBonus002DtoValidator.class,groups={ValidateGroupDelete.class,ValidateGroupEdit.class})
public class TrdStockBonus002Dto extends TrdStockBonusDto implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 红股上市日
	 */
//	private Date redStockDate;

	/**
	 * 分红类型字符串
	 */
	private String psText;
	
//	public Date getRedStockDate() {
//		return redStockDate;
//	}
//
//	public void setRedStockDate(Date redStockDate) {
//		this.redStockDate = redStockDate;
//	}

	
	/**
     * 工作流代码
     */
    private String workflowId;
	
	/**
     * 页面元素
     */
    private String elementId;
    
    /**
     * 模式（辅助标记功能）
     */
    private String mode;
    
    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;
    
	/**
	 * 分红明细dto
	 */
	private List<FinAssetIntInfo> dividDetailInfoList;
	
    
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;


	/**
	 * 获取 PSText
	 * @return
	 */
	public String getPsText() {
		String result = SysDataUtil.getNameFromDataDict(DictCode.股票分红类型,
				getPs());
		return result == null ? getPs() : result;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<FinAssetIntInfo> getDividDetailInfoList() {
		return dividDetailInfoList;
	}

	public void setDividDetailInfoList(List<FinAssetIntInfo> dividDetailInfoList) {
		this.dividDetailInfoList = dividDetailInfoList;
	}

	@Override
	@PrimaryKey(PrimaryKey.Field.PK_2)
	@NotEmpty(fieldName = "资产代码", groups = {ValidateGroupEdit.class})
	public String getFinprodMarketId() {
		return super.getFinprodMarketId();
	}


	@Override
	@PrimaryKey(PrimaryKey.Field.PK_3)
	public String getBusiType() {
		return super.getBusiType();
	}

	@Override
	@NotEmpty(fieldName = "分红类型", groups = {ValidateGroupEdit.class})
	public String getPs() {
		return super.getPs();
	}
	
	
	@Override
	@NotEmpty(fieldName = "权益登记日", groups = {ValidateGroupEdit.class})
	public Date getRegDate() {
		return super.getRegDate();
	}
	
	@Override
	@NotEmpty(fieldName = "除权除息日", groups = {ValidateGroupEdit.class})
	public Date getTradeDate() {
		return super.getTradeDate();
	}
	
	
	@Override
	@NotEmpty(fieldName = "派息日", groups = {ValidateGroupEdit.class})
	public Date getSettleDate() {
		return super.getSettleDate();
	}
	

	
	
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getBusiCode() {
		return super.getBusiCode();
	}

	@Override
	@PrimaryKey(PrimaryKey.Field.PK_4)
	public String getTradeDateStr() {
		return super.getTradeDateStr();
	}

	public Boolean getNeedSkipInsertWfl() {
		return needSkipInsertWfl;
	}

	public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
		this.needSkipInsertWfl = needSkipInsertWfl;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	/*@Override
	@NotEmpty(fieldName = "TrdStockBonus001.secManageAcctId", groups = {ValidateGroupEdit.class})
	public String getSecManageAcctId() {
		return super.getSecManageAcctId();
	}*/
	
	
}
