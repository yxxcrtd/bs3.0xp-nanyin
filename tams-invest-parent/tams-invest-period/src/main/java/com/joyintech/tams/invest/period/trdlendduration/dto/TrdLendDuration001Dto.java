/*
 * 文件名：TrdLendDuration001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdlendduration.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.trdlendduration.TrdLendDurationCommonDto;
import com.joyintech.tams.invest.period.trdlendduration.validator.TrdLendDurationValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年9月14日
 * @see TrdLendDuration001Dto
 * @since 1.0
 */
@PageId("TrdLendDuration002")
@ValidLogic(targetClass = TrdLendDurationValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class TrdLendDuration001Dto extends TrdLendDurationCommonDto implements ProcessEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2471957708779806411L;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 选中数据
     */
    private List<TrdLendDuration001Dto> selectedItem;

    /**
     * 获取 busiCode
     *
     * @return busiCode.
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getBusiCode() {
        return super.getBusiCode();
    }

    /**
     * PK_2
     */
    @PrimaryKey(Field.PK_2)
    public String getLoanMaturityTransaction() {
        return DictCodeList.共通业务类型_拆借存续期交易.getValue();
    }

    /**
     * 平安业务类型
     */
    public String getPingAnCodeSin() {
        if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_拆入.getValue(),
            this.getFinprodType2())||StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue(),
                this.getFinprodType2())) {
            return DictCodeList.回购拆借方向_拆入.getValue();
        }
        else {
            return DictCodeList.回购拆借方向_拆出.getValue();
        }
    }

    /**
     * 平安业务类型Str
     */
    public String getPingAnCodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, getPingAnCodeSin());
    }


    public String getStatusValidStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, getStatusValid());
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<TrdLendDuration001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdLendDuration001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    /**
     * 获取 uploadFileList
     * @return uploadFileList.
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    
    /**
     * 设置 uploadFileList
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 金融产品代码
     */
    private String finprodId;

    @Override
    @PrimaryKey(Field.PK_3)
    public String getFinprodId() {
        return finprodId;
    }

    @Override
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
}

