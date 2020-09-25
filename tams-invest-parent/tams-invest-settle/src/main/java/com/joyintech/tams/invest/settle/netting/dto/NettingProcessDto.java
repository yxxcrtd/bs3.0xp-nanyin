package com.joyintech.tams.invest.settle.netting.dto;

import java.text.DecimalFormat;
import java.util.List;

//import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.component.business.util.CommonBusinessUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingOrdersDto;
import com.joyintech.tams.invest.settle.netting.validator.NettingManagement001DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
//import org.apache.shiro.util.StringUtils;

/**
 * @author 姚轶文
 * @create 2019- 11-07 17:38
 */
@PageId("NettingManagement002")
@ValidLogic(targetClass = NettingManagement001DtoValidator.class)
public class NettingProcessDto  extends NettingOrdersDto implements ProcessEntity  {
    private static final long serialVersionUID = 1865187271162617848L;

    //private static final long serialVersionUID = 1000000L;

    /**
     * 生成指令时的业务编号
     */
    private List<NettingManagement001Dto> tradeDatas;

    private String elementId;

    private String operateType;



    private String pay; //PAY_TYPE; //划款类型
    private String payString; //前端列表显示的划款类型

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setPayString(String payString) {
        this.payString = SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPayType());
    }

    public String getPayString() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPayType());
        // return payString;
    }


    public String getIsNeedSend() {
        return StringUtil.isNotEmpty(super.getIsNeedSend())?super.getIsNeedSend():DictCodeList.是否_是.getValue();
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
     * 是否发送
     *
     * @return
     */
    public String getIsNeedSendString() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsNeedSend());
    }

    /**
     * 币种
     *
     * @return
     */
    public String getCcyString() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getSettleDateString() {
        return DateUtil.format(super.getSettleDate());
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getCcy() {
        return super.getCcy();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getCounterAcctNo() {
        return super.getCounterAcctNo();
    }

    //add by libaisong 2019-10-14 start
    @PrimaryKey(PrimaryKey.Field.PK_5)
    public String getOrderId() {
        return super.getActOrderId();
    }
    //add by libaisong 2019-10-14 end

    public String getSettleAmtString() {
        return CommonBusinessUtil.getUpperMoneyResult(getSettleAmt());
    }

    public String getSettleAmtLowerString() {
        if(super.getSettleAmt()!=null){
            DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
            return df2.format(super.getSettleAmt());
        }
        return null;
    }

    public String getOperatorUserName() {
        if (StringUtil.isEmpty(getOperatorUser())) {
            return StringUtil.EMPTY;
        }
        return CacheUtil.getAppParamCache(SystemGlobalConstant.SYS_USER_KEY, getOperatorUser());
    }

    public String getCheckUserName() {
        if (StringUtil.isEmpty(getCheckUser())) {
            return StringUtil.EMPTY;
        }
        return CacheUtil.getAppParamCache(SystemGlobalConstant.SYS_USER_KEY, getCheckUser());
    }

    /**
     * 获取 tradeDatas
     *
     * @return tradeDatas
     */
    public List<NettingManagement001Dto> getTradeDatas() {
        return tradeDatas;
    }

    /**
     * 设置 tradeDatas
     *
     * @param tradeDatas tradeDatas
     */
    public void setTradeDatas(List<NettingManagement001Dto> tradeDatas) {
        this.tradeDatas = tradeDatas;
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
