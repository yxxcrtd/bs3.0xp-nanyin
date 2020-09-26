/*
 * 文件名：ValSharePriceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valshareprice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.validator.ValSharePriceValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 股票行情dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ValSharePriceDto
 * @since 1.0
 */
@PageId("ValSharePrice002")
@ValidLogic(targetClass = ValSharePriceValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValSharePriceDto extends MstSharePrice implements ITableRow,ProcessEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;
    
    /**
     * 选中的数据
     */
    private transient List<ValSharePriceDto> selectedItem;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<ValSharePriceDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ValSharePriceDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


	@Override
	@NotEmpty(fieldName = "ValSharePrice001.sharesId", groups = {ValidateGroupEdit.class})
	@PrimaryKey(value=Field.PK_1)
	public String getSharesId() {
		return super.getSharesId();
	}



	@Override
	@NotEmpty(fieldName = "ValSharePrice001.dealDate", groups = {ValidateGroupEdit.class})
	public Date getDealDate() {
		return super.getDealDate();
	}
    
	@PrimaryKey(value=Field.PK_2)
	public String getDealDateStr() {
		return DateUtil.format(super.getDealDate());
	}
	
	public String getTradeStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.股票交易状态, super.getTradeStatus());
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.preClosePrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.preClosePrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getPreClosePrice() {
		return super.getPreClosePrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.openPrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.openPrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getOpenPrice() {
		return super.getOpenPrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.highPrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.highPrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getHighPrice() {
		return super.getHighPrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.lowPrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.lowPrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getLowPrice() {
		return super.getLowPrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.closePrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.closePrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getClosePrice() {
		return super.getClosePrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.sShares", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.sShares", maxIntBit=26, maxDecBit=4, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getsShares() {
		return super.getsShares();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.cShares", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.cShares", maxIntBit=26, maxDecBit=4, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getcShares() {
		return super.getcShares();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.change", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.change", maxDecBit=14,canMinus=true, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getChange() {
		return super.getChange();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.changePercent", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.changePercent",canMinus=true, maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getChangePercent() {
		return super.getChangePercent();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.dealValume", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.dealValume", maxIntBit=26, maxDecBit=4, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getDealValume() {
		return super.getDealValume();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.dealAmount", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.dealAmount", maxIntBit=28,  groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getDealAmount() {
		return super.getDealAmount();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.avgPrice", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.avgPrice", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getAvgPrice() {
		return super.getAvgPrice();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.preClosePriceAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.preClosePriceAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getPreClosePriceAdj() {
		return super.getPreClosePriceAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.openPriceAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.openPriceAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getOpenPriceAdj() {
		return super.getOpenPriceAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.highPriceAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.highPriceAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getHighPriceAdj() {
		return super.getHighPriceAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.lowPriceAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.lowPriceAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getLowPriceAdj() {
		return super.getLowPriceAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.closePriceAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.closePriceAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getClosePriceAdj() {
		return super.getClosePriceAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.factorAdj", groups = {ValidateGroupEdit.class})
	@Amount(fieldName = "ValSharePrice001.factorAdj", maxDecBit=14, groups = {
	        ValidateGroupEdit.class})
	public BigDecimal getFactorAdj() {
		return super.getFactorAdj();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.tradeStatus", groups = {ValidateGroupEdit.class})
	public String getTradeStatus() {
		return super.getTradeStatus();
	}

	@Override
	@NotEmpty(fieldName = "ValSharePrice001.finprodAbbr", groups = {ValidateGroupEdit.class})
	public String getFinprodAbbr() {
		return super.getFinprodAbbr();
	}
   

}
