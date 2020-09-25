package com.joyintech.tams.invest.period.trdseclendduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdseclendduration.TrdSecLendDurationDto;

import java.util.List;

/**
 *
 */
@PageId("TrdSecLendDuration001")
public class TrdSecLendDuration001Dto extends TrdSecLendDurationDto {

    private String elementId;

    /**
     * code Name
     */
    private String statusValidString;


    /**
     * 交易方向 Name
     */
    private String psText;

    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 获取 psText
     *
     * @return psText
     */
    public String getPsText() {
        psText = SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, this.getPs());
        return psText;
    }



    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态,
            getStatusValid());
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
}
