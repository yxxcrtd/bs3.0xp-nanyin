package com.joyintech.tams.invest.period.trdrepoduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.period.trdrepoduration.validator.TrdRepoDuration001DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

/**
 * @program: tams-invest-parent
 * @description: 查询dto
 * @author: 强成西
 * @create: 2018-10-20 16:27
 **/
@PageId("TrdRepoDuration001")
@ValidLogic(targetClass =TrdRepoDuration001DtoValidator.class)
public class TrdRepoDuration001Dto extends TrdRepoDurationDto {
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    private String elementId;

    /**
     * code Name
     */
    private String statusValidString;

    /**
     * 回购类型 Name
     */
    private String type6String;

    /**
     * 交易方向 Name
     */
    private String finprodType2String;

    /**
     * 获取 finprodType2String
     *
     * @return finprodType2String
     */
    public String getFinprodType2String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,
            getFinprodType2());
        return result == null ? getFinprodType2() : result;
    }
    /**
     * 获取 getPsStr
     *
     * @return getPsStr
     */
    public String getPsStr() {
        String result = StringUtil.equals(getStatusValid(), DictCodeListExt1.画面数据状态_待处理.getValue())?null:SysDataUtil.getNameFromDataDict(DictCode.交易方向,
                getPs());
        return result;
    }
    /**
     * 设置 finprodType2String
     *
     * @param finprodType2String finprodType2String
     */
    public void setFinprodType2String(String finprodType2String) {
        this.finprodType2String = finprodType2String;
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
     * 获取 type6String
     *
     * @return type6String
     */
    public String getType6String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.回购类型, getType6());
        return result == null ? getType6() : result;
    }

    /**
     * 设置 type6String
     *
     * @param type6String type6String
     */
    public void setType6String(String type6String) {
        this.type6String = type6String;
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
