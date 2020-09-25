/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.TrdStockBonusDto;
import com.joyintech.tams.invest.period.trdstockbonus.validator.TrdStockBonus002DtoValidator;

/**
 * @author panbin
 * @version 2019年6月18日
 * @see TrdStockBonus001Dto
 */
@ValidLogic(targetClass = TrdStockBonus002DtoValidator.class,groups={ValidateGroupDelete.class,ValidateGroupEdit.class})
public class TrdStockBonus001Dto extends TrdStockBonusDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String statusValidString;

	/**
	 * 分红类型字符串
	 */
	private String psText;
	/**
     * 页面元素
     */
    private String elementId;
	
	
	public void setStatusValidString(String statusValidString) {
		this.statusValidString = statusValidString;
	}


	/**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态,
            getStatusValid());
        return result == null ? getStatusValid() : result;
    }

	/**
	 * 获取 PSText
	 * @return
	 */
	public String getPsText() {
		String result = SysDataUtil.getNameFromDataDict(DictCode.股票分红类型,
				getPs());
		return result == null ? getPs() : result;
	}

    public String getElementId() {
		return elementId;
	}


	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@Override
    public String getBusiCode() {
    	return super.getBusiCode();
    }
}
