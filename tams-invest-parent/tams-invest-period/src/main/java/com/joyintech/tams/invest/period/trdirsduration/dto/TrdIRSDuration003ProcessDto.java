package com.joyintech.tams.invest.period.trdirsduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.tams.invest.period.trdirsduration.validator.TrdIRSDuration003DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 利率互换提前终止交易Entity
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.dto
 * @Description: 页面查询参数
 * @date Date : 2019年08月10日
 */
@PageId("TrdIRSDuration003")
@ValidLogic(targetClass = TrdIRSDuration003DtoValidator.class, groups = {ValidateGroupDelete.class,
    ValidateGroupEdit.class})
public class TrdIRSDuration003ProcessDto extends TrdIRSDurationDto implements ProcessEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9087131115779376712L;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

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
     * 交易编号
     * 
     * @return 交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "TrdIRSDuration003.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    @NotEmpty(fieldName = "划款指令", groups = {ValidateGroupEdit.class})
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        if (bussTrdTransferOrder == null || bussTrdTransferOrder.isEmpty()) {
            return null;
        }
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
     * 获取 beforeEndDate
     * 
     * @return beforeEndDate.
     */
    @NotEmpty(fieldName = "TrdIRSDuration003.beforeEndDate", groups = {ValidateGroupEdit.class})
    public Date getBeforeEndDate() {
        return super.getBeforeEndDate();
    }

    /**
     * 获取 beforeEndPayDate
     * 
     * @return beforeEndPayDate.
     */
    @NotEmpty(fieldName = "TrdIRSDuration003.beforeEndPayDate", groups = {ValidateGroupEdit.class})
    public Date getBeforeEndPayDate() {
        return super.getBeforeEndPayDate();
    }

    /**
     * 获取 beforeEndPrin
     * 
     * @return beforeEndPrin.
     */
    @NotEmpty(fieldName = "TrdIRSDuration003.beforeEndPrin", groups = {ValidateGroupEdit.class})
    public BigDecimal getBeforeEndPrin() {
        return super.getBeforeEndPrin();
    }

    /**
     * 获取 beforeEndPs
     * 
     * @return beforeEndPs.
     */
    @NotEmpty(fieldName = "TrdIRSDuration003.beforeEndPs", groups = {ValidateGroupEdit.class})
    public String getBeforeEndPs() {
        return super.getBeforeEndPs();
    }

    /**
     * 获取 beforeEndAmt
     * 
     * @return beforeEndAmt.
     */
    @NotEmpty(fieldName = "TrdIRSDuration003.beforeEndAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getBeforeEndAmt() {
        return super.getBeforeEndAmt();
    }

    /**
     * 获取 trdInvestPersonInfo
     * 
     * @return trdInvestPersonInfo.
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    /**
     * 设置 trdInvestPersonInfo
     * 
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

}
