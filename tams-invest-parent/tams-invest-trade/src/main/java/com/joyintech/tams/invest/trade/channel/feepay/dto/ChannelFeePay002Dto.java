/*
 * 文件名：ChannelAssetQueryDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeePayDto;
import com.joyintech.tams.invest.trade.channel.feepay.validate.ChannelFeePay002Validator;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

/**
 * 通道费用支付新建
 * 
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelFeePay002Dto
 * @since 1.0
 */
@PageId("ChannelFeePay002")
@ValidLogic(targetClass = ChannelFeePay002Validator.class)
public class ChannelFeePay002Dto extends ChannelFeePayDto implements ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    @Override
    @NotEmpty(fieldName = "通道")
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getChlId() {
        return super.getChlId();
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getMdateString() {
        return DateUtil.format(super.getMdate());
    }

    @NotEmpty(fieldName = "计提结束日")
    public Date getMdate() {
        return super.getMdate();
    }

    @Override
    @NotEmpty(fieldName = "组合代码")
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @PrimaryKey(PrimaryKey.Field.PK_4)
    @Override
    public String getTradeId() {
        return super.getTradeId();
    }

    @Override
    @NotEmpty(fieldName = "支付日")
    public Date getSettleDate() {
        return super.getSettleDate();
    }

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 获取 uploadFileList
     *
     * @return uploadFileList
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    /**
     * 设置 uploadFileList
     *
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
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
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
     * 获取 fileArray
     *
     * @return fileArray
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     *
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }
}
