package com.joyintech.tams.invest.period.trdbondduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondExerciseProcessDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondPtlSecExerciseInfo;
import com.joyintech.tams.invest.period.trdbondduration.validator.TrdBondDuration003DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.dto
 * @Description: 页面查询参数
 * @date Date : 2018年09月25日 9:42
 */
@PageId("TrdBondDuration003")
@ValidLogic(targetClass = TrdBondDuration003DtoValidator.class, groups = {ValidateGroupDelete.class, ValidateGroupEdit.class})
public class TrdBondDuration003ProcessDto extends TrdBondExerciseProcessDto implements ProcessEntity {

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    @Override
    @PrimaryKey(Field.PK_1)
    @NotEmpty(fieldName = "资产简称", groups = {ValidateGroupEdit.class})
    public String getFinprodId() {
        return super.getFinprodId();
    }

    @Override
    @PrimaryKey(Field.PK_2)
    public String getPs() {
        return super.getPs();
    }

    /**
     * 获取 BizCode
     *
     * @return BizCode.
     */
    @Override
    @PrimaryKey(Field.PK_3)
    public String getBizCode() {
        return super.getBizCode();
    }

    /**
     * 获取 chlId
     *
     * @return chlId.
     */
    @Override
    @PrimaryKey(Field.PK_4)
    public String getChlId() {
        return super.getChlId();
    }

    /**
     * 获取 payDate
     *
     * @return payDate.
     */
    @PrimaryKey(Field.PK_5)
    public String getPayDateString() {
        return DateUtil.format(super.getPayDate());
    }

    @Override
    @NotEmpty(fieldName = "行权净价", groups = {ValidateGroupEdit.class})
    public BigDecimal getNetPrice() {
        return super.getNetPrice();
    }

    @Override
    @NotEmpty(fieldName = "到账金额", groups = {ValidateGroupEdit.class})
    public BigDecimal getfPriceAmt() {
        return super.getfPriceAmt();
    }

    @Override
    @NotEmpty(fieldName = "资金到账日", groups = {ValidateGroupEdit.class})
    public Date getSettleDate() {
        return super.getSettleDate();
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    @Override
    @NotEmpty(fieldName = "划款指令", groups = {ValidateGroupEdit.class})
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return super.getBussTrdTransferOrder();
    }

    @Override
    @NotEmpty(fieldName = "金额明细信息", groups = {ValidateGroupEdit.class})
    public List<TrdBondPtlSecExerciseInfo> getTrdBondPtlSecExerciseInfos() {
        return super.getTrdBondPtlSecExerciseInfos();
    }

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
}
