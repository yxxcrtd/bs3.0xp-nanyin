package com.joyintech.tams.invest.period.trdprojint.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdprojint.TrdProjectDurationInterestDto;

/**
 * @program: tams-invest-parent
 * @description: 买断利率查询dto
 * @author: 强成西
 * @create: 2018-10-23 14:26
 **/
public class TrdProjectDurationIntDto extends TrdProjectDurationInterestDto {

    /**
     * 付息计划
     */
    private FinPayInterestStdInfoDto finPayInterestStdInfoDto;

    /**
     * 获取 assetTypeString
     *
     * @return assetTypeString
     */
    public String getAssetTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getAssetType());
    }

    /**
     * 获取 finPayInterestStdInfoDto
     *
     * @return finPayInterestStdInfoDto
     */
    public FinPayInterestStdInfoDto getFinPayInterestStdInfoDto() {
        return finPayInterestStdInfoDto;
    }

    /**
     * 设置 finPayInterestStdInfoDto
     *
     * @param finPayInterestStdInfoDto finPayInterestStdInfoDto
     */
    public void setFinPayInterestStdInfoDto(FinPayInterestStdInfoDto finPayInterestStdInfoDto) {
        this.finPayInterestStdInfoDto = finPayInterestStdInfoDto;
    }
}
