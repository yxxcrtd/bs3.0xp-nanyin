package com.joyintech.tams.invest.entrust.etrprojectnet.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.service.EtrProjectNetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 权益类资产
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see EtrProjectNetValidator
 * @since 1.0
 */
@Component
public class EtrProjectNetValidator implements EtrCommonValidator {
    /**
     * 画面层service
     */
    @Autowired
    private EtrProjectNetService etrProjectNetService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrProjectNetDto dto = (EtrProjectNetDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrProjectNetDto> listDto = dto.getSelectedItem();
            for (EtrProjectNetDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrProjectNetService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrProjectNetDto> listDto = dto.getSelectedItem();
            for (EtrProjectNetDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrProjectNetService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrProjectNetDto> listDto = dto.getSelectedItem();
            for (EtrProjectNetDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrProjectNetService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                    || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                        // “新建”、“已撤回”、“已退回”
                        this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
            // 校验人员信息start  daizhiwei
            if(dto.getEtrInvestPersonInfo() !=null) {
                if (StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getInvManagerId())) {
                    errorList.add(new ValidMessage("请输入投资经理"));
                }
                if (StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getRoom())) {
                    errorList.add(new ValidMessage("请输入投资部门"));
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                }
            }
            // 校验人员信息end  daizhiwei
        }
        else {
            BigDecimal amt = dto.getEntrustAmt();
            BigDecimal num = dto.getEntrustNum();
            BigDecimal proAvailableCash = dto.getProAvailableCash();
            BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
            BigDecimal zero = new BigDecimal(0);
            if((StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue())||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_买入.getValue()))
            &&amt == null){
                errorList.add(new ValidMessage(this.getMessage("请输入指令金额。")));
            }
            else if((StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue()))
                    &&num == null){
                errorList.add(new ValidMessage(this.getMessage("请输入指令金额。")));
            }
            /*if (amt == null && num == null) {
                errorList.add(new ValidMessage(this.getMessage("ETR_PLANN_001")));
            }*/
            if (amt != null) {
                if (!(amt.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrPlanAssetN001.entrustAmt"))));
                }
            }
            if (num != null) {
                if (!(num.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_008",this.getMessage("EtrPlanAssetN002.entrustNum"))));
                }
                /*可用现金与持仓Check  add by zhuyongkang 2019/1/20 start*/
                // 可用现金校验
                if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                        DictCodeListExt1.是或否_否.getValue())
                        && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                        ||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue()))
                        && dto.getProAvailableCash() != null
                        && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_018",this.getMessage("EtrPlanAssetN002.entrustAmt"))));
                }
                // 可用持仓校验
                else if (StringUtil.equals(
                        CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                        DictCodeListExt1.是或否_否.getValue())
                        && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()))
                        && dto.getAssetAvailablePosition() != null
                        && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_011",this.getMessage("EtrPlanAssetN002.entrustNum"))));
                }
            }
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
                if(finvdate != null && vdate.before(finvdate)){
                    errorList.add(new ValidMessage("vdate",this.getMessage("ETR_X_006")+ DateUtil.format(finvdate)));
                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+DateUtil.format(finmdate)));
                }
            }
            // 校验人员信息start  daizhiwei 计划类净值型指令新建画面和多对多指令导入时，修改交易对手为选填，申购赎回时非必填，买入卖出时必填
            if (StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getInvManagerId())){
                errorList.add(new ValidMessage("请输入投资经理"));
            }
            if (StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getRoom())){
                errorList.add(new ValidMessage("请输入投资部门"));
            }
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
            ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                if(StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getCounterId())){
                    errorList.add(new ValidMessage("请输入交易对手"));
                }
            }
            // 校验人员信息end  daizhiwei
            errorList.addAll(etrProjectNetService.check(dto));
        }
        return errorList;
    }
}
