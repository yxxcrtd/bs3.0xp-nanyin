package com.joyintech.tams.invest.period.trdbondduration.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;
import com.joyintech.tams.invest.period.trdbondduration.validator.TrdBondDuration002DtoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.dto
 * @Description: 页面查询参数
 * @date Date : 2018年09月25日 9:42
 */
@PageId("TrdBondDuration002")
@ValidLogic(targetClass = TrdBondDuration002DtoValidator.class, groups = {ValidateGroupDelete.class, ValidateGroupEdit.class})
public class TrdBondDuration002ProcessDto extends TrdBondDurationDto implements ProcessEntity {

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * code Name
     */
    private String statusValidString;

    /**
     * 平安业务类型
     */
    private String paBussTypeString;
    
    /***update zhangweiqi 2019-10-14 存续期添加通道费 begin**/
    /**
     * 通道费
     */
    private BigDecimal channelFee;

    /**
     * 通道性质
     */
    private String payType;
    
    /**
     * 通道收取方式
     */
    private String collectType;
    
    /**
     * 实收本息总额
     */
     
    private BigDecimal tradeAmtReal;
    /***update zhangweiqi 2019-10-14 存续期添加通道费 end**/

    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, getStatusValid());
        return result == null ? getStatusValid() : result;
    }

    /**
     * 设置 statusValidString
     *
     * @param statusValidString statusValidString
     */
    public void setStatusValidString(String statusValidString) {
        this.statusValidString = statusValidString;
    }

    /**
     * 获取 PaBussTypeString
     *
     * @return PaBussTypeString
     */
    public String getPaBussTypeString() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_债券中间还本.getCodeName();
            }
            else if (getCashType() != null && (getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())
                     || getCashType().contains(DictCodeList.现金流类型_到期收息.getValue()))) {
                return DictCodeListExt1.平安业务类型_债券到期.getCodeName();
            }
            else {
                return DictCodeListExt1.平安业务类型_债券中间收息.getCodeName();
            }
        }
        else {
            return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBussType());
        }
    }

    @Override
    public String getPaBussType() {
        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(getStatusValid())) {
            if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
                return DictCodeListExt1.平安业务类型_债券中间还本.getValue();
            }
            else if (getCashType() != null && (getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())
                    || getCashType().contains(DictCodeList.现金流类型_到期收息.getValue()))) {
                return DictCodeListExt1.平安业务类型_债券到期.getValue();
            }
            else {
                return DictCodeListExt1.平安业务类型_债券中间收息.getValue();
            }
        }
        else {
            return super.getPaBussType();
        }
    }

    /**
     * 交易编号
     * 
     * @return
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_债券类资产存续期.getValue();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getSecManageAcctId() {
        return super.getSecManageAcctId();
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode.
     */
    @Override
    @PrimaryKey(Field.PK_5)
    public String getBusiCode() {
        return super.getBusiCode();
    }
    
    @Override
    @NotEmpty(fieldName = "实收利息总额", groups = {ValidateGroupEdit.class})
    public BigDecimal getIntAmt() {
        return super.getIntAmt();
    }

    @Override
    @NotEmpty(fieldName = "到账金额", groups = {ValidateGroupEdit.class})
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }

    @Override
    @NotEmpty(fieldName = "资金到账日", groups = {ValidateGroupEdit.class})
    public String getTradeDate() {
        return super.getTradeDate();
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    @NotEmpty(fieldName = "划款指令", groups = {ValidateGroupEdit.class})
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        if (super.getBussTrdTransferOrder() == null || super.getBussTrdTransferOrder().isEmpty()) {
            return null;
        }
        return super.getBussTrdTransferOrder();
    }
    /**
     * 获取 isClean
     *
     * @return isClean
     */
    @Override
    @NotEmpty(fieldName = "TrdSecOverdueDuration002.isClean", groups = {ValidateGroupEdit.class})
    public String getIsClean() {
        return super.getIsClean();
    }
    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 设置 paBussTypeString
     *
     * @param paBussTypeString paBussTypeString
     */
    public void setPaBussTypeString(String paBussTypeString) {
        this.paBussTypeString = paBussTypeString;
    }

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}
	@Override
    @NotEmpty(fieldName = "实收本息总额", groups = {ValidateGroupEdit.class})
	public BigDecimal getTradeAmtReal() {
		return tradeAmtReal;
	}

	public void setTradeAmtReal(BigDecimal tradeAmtReal) {
		this.tradeAmtReal = tradeAmtReal;
	}
    
    
}
