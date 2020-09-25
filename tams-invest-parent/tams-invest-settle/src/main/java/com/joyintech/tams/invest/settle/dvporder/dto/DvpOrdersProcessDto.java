package com.joyintech.tams.invest.settle.dvporder.dto;

import java.text.DecimalFormat;
import java.util.List;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.component.business.util.CommonBusinessUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.settle.dvporder.validator.DvpOrdersManagement001DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.apache.shiro.util.StringUtils;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-13 15:44
 **/
@PageId("DvpOrdersManagement002")
@ValidLogic(targetClass = DvpOrdersManagement001DtoValidator.class)
public class DvpOrdersProcessDto extends DvpOrdersDto implements ProcessEntity {

    /**
     * 生成指令时的业务编号
     */
    private List<DvpOrdersManagement001Dto> tradeDatas;

    private String elementId;

    private String operateType;


    /**
     * 获取 inOutBank
     *
     * @return inOutBank
     */
    public String getInOutBankString() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产来源, getInOutBank());
    }

    public String getPayTypeString() {
        // 一体化对应etliu start
//        return SysDataUtil.getNameFromDataDict(DictCode.划款类型, getPayType());
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPayType());
        // 一体化对应etliu end
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
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getIsNeedSend());
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
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_5)
    public String getOrderId() {
        return super.getOrderId();
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
            return StringUtils.EMPTY_STRING;
        }
        return CacheUtil.getAppParamCache(SystemGlobalConstant.SYS_USER_KEY, getOperatorUser());
    }

    public String getCheckUserName() {
        if (StringUtil.isEmpty(getCheckUser())) {
            return StringUtils.EMPTY_STRING;
        }
        return CacheUtil.getAppParamCache(SystemGlobalConstant.SYS_USER_KEY, getCheckUser());
    }

    /**
     * 获取 tradeDatas
     *
     * @return tradeDatas
     */
    public List<DvpOrdersManagement001Dto> getTradeDatas() {
        return tradeDatas;
    }

    /**
     * 设置 tradeDatas
     *
     * @param tradeDatas tradeDatas
     */
    public void setTradeDatas(List<DvpOrdersManagement001Dto> tradeDatas) {
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
