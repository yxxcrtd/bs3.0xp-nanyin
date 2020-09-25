/*
 * 文件名：TrdDepoDuration001dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trddepoduration.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trddepoduration.TrdDepoDurationCommonDto;
import com.joyintech.tams.invest.period.trddepoduration.validator.TrdDepoDuration001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * dto
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdDepoDuration001Dto
 * @since 1.0
 */
@PageId("TrdDepoDuration002")
@ValidLogic(targetClass = TrdDepoDuration001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdDepoDuration001Dto extends TrdDepoDurationCommonDto implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -716587016375490940L;
    /**
     * 选择的数据
     */
    private List<TrdDepoDuration001Dto> selectedItem;

    /**
     * 防止出现空业务编号 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBusiCodeTemp() {
        return StringUtil.isNotEmpty(getBusiCode()) ? getBusiCode() : IDGenerator.generate();
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId.
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodMarketId() {
        return super.getFinprodMarketId();
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId.
     */
    @Override
//    @NotEmpty(fieldName = "TrdDepoDuration001.portfolioId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @PrimaryKey(PrimaryKey.Field.PK_4)
    String getMdateProdStr() {
        return DateUtil.format(getMdateProd());
    }

	public String getBusinessCode() {
        return DictCodeList.共通业务类型_存款类资产存续期.getValue();
    }

    public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, super.getAssetType());
    }

    public String getStatusValidStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, super.getStatusValid());
    }
    /**
     * 获取 isClean
     *
     * @return isClean
     */
    @Override
    public String getIsClean() {
        return super.getIsClean();
    }
    public String getPingAnCodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPingAnCodeSin());
    }

    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<TrdDepoDuration001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdDepoDuration001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 用于APP显示 类型
     */
    private String appPingAnCodeSin;

    public String getAppPingAnCodeSin() {
        return appPingAnCodeSin;
    }

    public void setAppPingAnCodeSin(String appPingAnCodeSin) {
        this.appPingAnCodeSin = appPingAnCodeSin;
    }

}
