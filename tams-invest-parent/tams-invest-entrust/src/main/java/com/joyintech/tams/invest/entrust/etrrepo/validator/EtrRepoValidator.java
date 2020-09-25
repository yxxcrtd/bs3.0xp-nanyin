/*
 * 文件名：EtrRepoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrrepo.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.tams.invest.entrust.etrrepo.service.EtrRepo001Service;
import com.joyintech.tams.invest.entrust.etrrepo.service.EtrRepo002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * 回购投资委托校验
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月24日
 * @see EtrRepoValidator
 * @since 1.0
 */
@Component
public class EtrRepoValidator implements EtrCommonValidator {

    @Autowired
    private EtrRepo001Service etrRepo001Service;

    @Autowired
    private EtrRepo002Service etrRepo002Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrRepoDto dto = (EtrRepoDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrRepoDto> listDto = dto.getSelectedItem();
            for (EtrRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrRepo001Service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrRepoDto> listDto = dto.getSelectedItem();
            for (EtrRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrRepo001Service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrRepoDto> listDto = dto.getSelectedItem();
            for (EtrRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrRepo001Service.check(entity));
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
        }
        else {
            // delete by renping 改为共通处理 2019-08-22 start
//            BigDecimal entrustAmt = dto.getEntrustAmt();
//            BigDecimal proAvailableCash = dto.getProAvailableCash();
//            if(entrustAmt != null){
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrRepo001Service.selectLimitAmt(dto);
//                    if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_009",this.getMessage("EtrRepo002.entrustAmt"))+limitAmt));
//                    }
//                }
//            }
            // delete by renping 改为共通处理 2019-08-22 end

        	  if (DictCodeList.回购类型_质押式协议回购.getValue().equals(dto.getAssetType1())
                      && dto.getBussRepoSec() != null) {
                  if (StringUtil.isEmptyList(dto.getBussRepoSec().getPledgeBondList())&&!StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())) {
                      errorList.add(new ValidMessage("","请输入质押券信息！"));
                  }
              }

            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
            if (dto.getMdateAdd() !=null && dto.getBussRepoSec()!=null && dto.getBussRepoSec().getPledgeBondList()!=null && dto.getBussRepoSec().getPledgeBondList().size()>0){
                List<String> bondCodeList = new ArrayList<>();
                for (PledgeBondInfoDto bond: dto.getBussRepoSec().getPledgeBondList()){
                    bondCodeList.add(bond.getBondCode());
                }
                Map<String,Object> param = new HashMap<>();
                param.put("bondCodeList",bondCodeList);
                Date finmdate = etrRepo002Service.selectRepoMdate(param);
                if (finmdate!=null && finmdate.before(dto.getMdateAdd())){
                    errorList.add(new ValidMessage("mdateAdd", this.getMessage("ETR_X_014",DateUtil.format(finmdate), DateUtil.format(dto.getMdateAdd()))));
                }
            }
            if (dto.getEntrustAmt() != null) {
                if (dto.getEntrustAmt().compareTo(BigDecimal.ZERO) == 0
                    || dto.getEntrustAmt().compareTo(BigDecimal.ZERO) == -1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrRepo002.entrustAmt"))));
                }
//                投资组合系统-投资端-回购投资委托-新建-提交，正回购委托金额大于质押券总额，系统没有报错
                if(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue()) && !StringUtil.equals(dto.getAssetType1(), DictCodeList.回购类型_质押式竞价回购.getValue())) {
                    // 银行间正回购委托，当没有添加押券时做提交，请去掉“委托金额不能大于质押券面额。”，但是添加了押券，就要有此判断。
                    if (DictCodeListExt1.交易场所_银行间.getValue().equals(dto.getTradeMarket()) && dto.getBussRepoSec() != null && dto.getBussRepoSec().getPledgeBondList() != null &&
                            !dto.getBussRepoSec().getPledgeBondList().isEmpty() && dto.getBussRepoSec().getTotalPrinAmt() != null
                        && dto.getEntrustAmt().compareTo(dto.getBussRepoSec().getTotalPrinAmt()) > 0) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0003", getMessage("EtrRepo001.entrustAmt"),
                            getMessage("EtrRepo002.pledgeAmt"))));
                    }
                }
            }
            errorList.addAll(etrRepo001Service.check(dto));
            // 质押式竞价回购、正回购的情况下，证券托管户必输check
            if (StringUtil.equals(dto.getAssetType1(), DictCodeList.回购类型_质押式竞价回购.getValue()) &&
                StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue()) &&
                !StringUtil.isNotEmpty(dto.getSecuritiesCust())) {
                // 交易所竞价式正回购委托，当没有添加押券时做提交，去掉“请输入证券托管户”的判断。
                if (DictCodeListExt1.交易场所_银行间.getValue().equals(dto.getTradeMarket()) || (!DictCodeListExt1.交易场所_银行间.getValue().equals(dto.getTradeMarket())
                && dto.getBussRepoSec() != null && dto.getBussRepoSec().getPledgeBondList() != null && !dto.getBussRepoSec().getPledgeBondList().isEmpty()))
                errorList.add(new ValidMessage("securitiesCust", this.getMessage("ETR_X_012",this.getMessage("EtrRepo002.securitiesCust"))));
            }
            // 当价格模式不为市价指令时，才check回购利率
            if (!StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue()) &&
                    dto.getPrice() == null) {
            	errorList.add(new ValidMessage("price", this.getMessage("ETR_X_012",this.getMessage("EtrRepo002.price"))));
            }

            // 可用现金校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_否.getValue())
                && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())
                && dto.getProAvailableCash() != null && dto.getEntrustAmt() != null
                && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                errorList.add(new ValidMessage("entrustAmt",
                    this.getMessage("BUSS_COM_E_0003", this.getMessage("EtrRepo002.entrustAmt"),
                        this.getMessage("EtrRepo002.proAvailableCash"))));
            }
        }
        return errorList;
    }

}
