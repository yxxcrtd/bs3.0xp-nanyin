/*
 * 文件名：MstTradeOpponent001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.basicdata.msttradeopponent.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoMapper;
import com.joyintech.tams.common.base.entity.MstCounterParty;
import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.FinCashDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.StlCapacctDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.service.MstTradeOpponent001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 交易对手管理检查
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see MstTradeOpponent001Validator
 * @since 1.0
 */
@Transactional
@Component
public class MstTradeOpponent001Validator implements BeanValidator {

    /**
     * 注入service
     */
    @Autowired
    private MstTradeOpponent001Service mstTradeOpponent001Service;

    /**
     * 注入业务主键生产类
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 传入参数检查 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        MstTradeOpponentDto dto = (MstTradeOpponentDto)value;
        if (WorkFlowUtil.isNotFromToDoList()) {
            // 判断操作类型
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 判断金融产品是否处于交易中
                for (MstTradeOpponentDto dt : dto.getSelectedItem()) {
                    if (mstTradeOpponent001Service.selectCounter(dt.getCounterId()) > 0) {
                        errorList.add(new ValidMessage(getMessage("COMMON_E_0030",
                            getMessage("MstTradeOpponent001.counterId"),
                            getMessage("MstTradeOpponent001.selectCounterId"))));
                    }

                    // 当交易对手类别为内部交易对手时，不能删除！  add by zhangyf 20190826 start
                    if(StringUtil.equals(dt.getCounterType(), DictCodeListExt1.交易对手类型_内部交易对手.getValue())) {
                    	errorList.add(new ValidMessage(getMessage("MST_TRADE_OPPONENT_E_0002",
                                getMessage("MstTradeOpponent001.counterType"),
                                getMessage(DictCodeListExt1.交易对手类型_内部交易对手.getCodeName()))));
                    }
                    // 当交易对手类别为内部交易对手时，不能删除！  add by zhangyf 20190826 end
                }
            }
            else if (StringUtil.isNotEmpty(dto.getOperateType())
                     && !StringUtil.equals(dto.getOperateType(), "delete")) {
                //如果交易对手一级类型不为空，则二级必填 yjm start 20190808
                if (StringUtil.isNotEmpty(dto.getpTypeOne()) && StringUtil.isEmpty(dto.getpTypeTwo())){
                    errorList.add(new ValidMessage("pTypeTwo",
                            getMessage("BUSS_COM_E_0038", getMessage("MstTradeOpponent002.pTypeTwo"))));
                }
                //如果交易对手一级类型不为空，则二级必填 yjm end 20190808
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if (mstTradeOpponent001Service.checkCounterId(dto.getCounterId()) > 0) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                            getMessage("MstTradeOpponent001.counterId"))));
                    }
                    if (dto.getCustomerId() != null
                        && mstTradeOpponent001Service.checkCounterId(dto.getCustomerId()) > 0) {
                        errorList.add(new ValidMessage("customerId", getMessage("BUSS_COM_E_0006",
                            getMessage("MstTradeOpponent001.customerId"), "【"+dto.getCounterName()+"】")));
                    }
                    //add by songyawei 20200712 验证交易对手名称不能重复 start
                    if(mstTradeOpponent001Service.checkCounterName(dto.getCounterName()) > 0) {
                    	errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006", "交易对手名称重复","【"+dto.getCounterName()+"】")));
                    }
                    //add by songyawei 20200712 验证交易对手名称不能重复 end
                    //add by yangwenjun 20200904 验证备注字段长度 start
                    if(StringUtil.isNotEmpty(dto.getRemark())) {
                    	StringBuffer sb = new StringBuffer("");
                    	String str=dto.getRemark();
                    	String chinese = "[\u4e00-\u9fa5]";
                        for (int i = 0; i < str.length(); i++) {
                            String tempStr = str.substring(i, i + 1);
                            sb.append(tempStr);
                            if (tempStr.matches(chinese)) {
                                // 遇到中文加空格
                                sb.append(" ");
                            }
                        }
                        if(sb.length()>1000) {
                        	errorList.add(new ValidMessage("remark","备注过长"));
                        }
                    }
                    //add by yangwenjun 20200904 验证备注字段长度 end
                }
                else if (StringUtil.equals(dto.getPageModel(),
                    GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
                         && (StringUtil.equals(dto.getOperateType(), "save")
                             || StringUtil.equals(dto.getOperateType(), "submit"))) {
                    if (StringUtil.isNotEmpty(dto.getCounterId())
                        && bussCommonMapperDelegateService != null) {
                        MstCounterParty mstCounterParty = bussCommonMapperDelegateService.selectBySinglePrimaryKey(
                            MstCounterPartyMapper.class, dto.getCounterId(),
                            MstCounterParty.class);
                        if (mstCounterParty != null) {
                            if (StringUtil.equals(dto.getCounterId(),
                                mstCounterParty.getCounterId())
                                && !StringUtil.equals(mstCounterParty.getCounterName(),
                                    dto.getCounterName())) {
                                if (dto.getCounterName() != null
                                    && mstTradeOpponent001Service.checkCounterName(
                                        dto.getCounterName()) > 0) {
                                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                                        getMessage("MstTradeOpponent001.counterName"), "【"+dto.getCounterName()+"】")));
                                }
                            }
                        }
                    }
                    	
//                    //add by songyawei 20200712 验证交易对手名称不能重复 start
//                    if(mstTradeOpponent001Service.checkcounterName(dto.getCounterId(),dto.getCounterName()) > 0) {
//                    	errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006", "交易对手名称重复")));
//                    }
//                    //add by songyawei 20200712 验证交易对手名称不能重复 end
                    
                }
                if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(dto.getCounterId())
                    && dto.getCounterName() == null) {
                    dto.setCounterName(dto.getCustomerId());
                }
                List<FinCashDto> finDto = dto.getSelectFinCash();
                Set<String> set = null;
                Set<String> typeSet = null;
                if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(dto.getCounterType())
                    && finDto != null && !finDto.isEmpty()) {
                    set = new HashSet<String>();
                    typeSet = new HashSet<String>();
                    for (int index = 0; index < finDto.size(); index++ ) {
                        set.add(finDto.get(index).getFeeId());
                        typeSet.add(finDto.get(index).getFeeType());
                    }
                    // 费用类型不能重复的id不能为空
                    if (typeSet.size() < finDto.size()) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                            getMessage("MstTradeOpponent002.feeType"))));
                    }
                    // 判断资金账户的id不能重复
                    if (set.size() < finDto.size()) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                            getMessage("MstTradeOpponent002.costName"))));
                    }
                }
                else if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(
                    dto.getCounterType())) {
                    // errorList.add(new ValidMessage(
                    // getMessage("BUSS_COM_E_0006",
                    // getMessage("MstTradeOpponent002.accountInfo"))));
                }
                List<StlCapacctDto> cashDto = dto.getSelectStlCapacct();
                if (cashDto != null && !cashDto.isEmpty()) {
                    set = new HashSet<String>();
                    int openNum = 0;
                    Set<String> setOpenNum = new HashSet<String>();
                    for (int index = 0; index < cashDto.size(); index++ ) {
                        if (StringUtil.isNotEmpty(cashDto.get(index).getAccountType())
                            && StringUtil.isNotEmpty(cashDto.get(index).getIsOpen())
                            && StringUtil.equals(cashDto.get(index).getIsOpen(),
                                DictCodeList.开放状态_开放.getValue())
                            && StringUtil.equals(cashDto.get(index).getAccountType(),
                                DictCodeListExt1.资金账户类型_产品托管户.getValue())) {
                            openNum++ ;
                        	if(!setOpenNum.contains(cashDto.get(index).getAccountType()+cashDto.get(index).getCcy())){
                        		setOpenNum.add(cashDto.get(index).getAccountType()+cashDto.get(index).getCcy());
                        	}
                        }
                        set.add(cashDto.get(index).getAcctNo());
                    }
                    // 开放状态的托管户仅能有一个
//                    if (openNum > 1) {
                    if(openNum > 1&&openNum!=setOpenNum.size()){
                        errorList.add(new ValidMessage(getMessage("MstTradeOpponent002.openNum")));
                    }
                    // 账号不能重复
                    if (set.size() < cashDto.size()) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                            getMessage("MstTradeOpponent002.accountInfo"))));
                    }
                }
                errorList.addAll(myValid(dto));
                if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(dto.getCounterType())
                    && DictCodeList.计划类资产一级分类_信托产品.getValue().equals(dto.getType1())
                    && DictCodeList.计划二级分类_信托_资金信托.getValue().equals(dto.getType2())
                    && !StringUtil.isNotEmpty(dto.getBankTrustCode())) {
                    errorList.add(new ValidMessage(
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent001.bankTrustCode"))));
                }
            }
            else {
                // 判断交易类型为通道时，金融产品是否存在
                if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(dto.getCounterType())
                    && mstTradeOpponent001Service.selectFinProduct(dto) > 0
                    && (StringUtil.equals(dto.getOperateType(), "save")
                        || StringUtil.equals(dto.getOperateType(), "submit"))) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                        getMessage("invest.channelId"+"MstTradeOpponent002.channlType"))));
                }
                if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(dto.getCounterType())
                    && DictCodeList.计划类资产一级分类_信托产品.getValue().equals(dto.getType1())
                    && DictCodeList.计划二级分类_信托_资金信托.getValue().equals(dto.getType2())
                    && !StringUtil.isNotEmpty(dto.getBankTrustCode())) {
                    errorList.add(new ValidMessage(
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent001.bankTrustCode"))));
                }
                // errorList.addAll(myValid(dto));
            }
        }
        return errorList;
    }

    /**
     * dto判断是否为空
     * 
     * @param mDto 实体类
     * @return 错误集合
     * @see
     * @since 1.0
     */
    public List<ValidMessage> myValid(MstTradeOpponentDto mDto) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        if (DictCodeListExt1.交易对手类型_市场机构.getValue().equals(mDto.getCounterType())) {
            if (mDto.getCustomerId() == null || mDto.getCustomerId().isEmpty()) {
                errorList.add(new ValidMessage("customerId",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent002.customerId"))));
            }
            else {
                // 验证交易对手名称是否重复
                if (bussCommonMapperDelegateService != null) {
                    MstCustomerInfo mstCustomerInfo = bussCommonMapperDelegateService.selectBySinglePrimaryKey(
                        MstCustomerInfoMapper.class, mDto.getCustomerId(), MstCustomerInfo.class);
                    if (StringUtil.equals(mDto.getOperateType(),
                        GlobalConstant.DETAIL_PAGE_MODEL_NEW) && mstCustomerInfo != null
                        && StringUtil.isNotEmpty(mstCustomerInfo.getCustomerName())) {
                        if (mstTradeOpponent001Service.checkCounterName(
                            mstCustomerInfo.getCustomerName()) > 0) {
                            errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",
                                getMessage("MstTradeOpponent001.counterName"))));
                        }
                    }
                }
            }
         /*   交易对手性质必填校验 update by zhuyongkang 2019/09/19 start*/
            if (mDto.getpTypeOne() == null || mDto.getpTypeOne().isEmpty()) {
                errorList.add(new ValidMessage("pTypeOne",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeOne"))));
            }
            if (mDto.getpTypeTwo() == null || mDto.getpTypeTwo().isEmpty()) {
                errorList.add(new ValidMessage("pTypeTwo",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeTwo"))));
            }
            /*   交易对手性质必填校验 update by zhuyongkang 2019/09/19 end*/
        }
        else if (DictCodeListExt1.交易对手类型_资管产品.getValue().equals(mDto.getCounterType())) {
            // 判断交易对手名称是否为空
            if (mDto.getCounterName() == null || mDto.getCounterName().isEmpty()) {
                errorList.add(new ValidMessage("counterName",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.counterName"))));
            }
            // 判断管理人是否为空
            if (mDto.getManagerId().isEmpty()) {
                errorList.add(new ValidMessage("managerId",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.managerId"))));
            }
            
            // START  交易对手性质必填校验 update by wj 20200413
            if (mDto.getpTypeOne() == null || mDto.getpTypeOne().isEmpty()) {
                errorList.add(new ValidMessage("pTypeOne",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeOne"))));
            }
            if (mDto.getpTypeTwo() == null || mDto.getpTypeTwo().isEmpty()) {
                errorList.add(new ValidMessage("pTypeTwo",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeTwo"))));
            }
            // END  交易对手性质必填校验 update by wj 20200413
            
        }
        else if (DictCodeListExt1.交易对手类型_分行.getValue().equals(mDto.getCounterType())) {
            // 判断交易对手名称是否为空
            if (mDto.getCounterName() == null || mDto.getCounterName().isEmpty()) {
                errorList.add(new ValidMessage("counterName",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.counterName"))));
            }
            if (mDto.getHeadBank() == null || mDto.getHeadBank().isEmpty()) {
                // 判断所属总行是否为空
                errorList.add(new ValidMessage("headOffice",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.headOffice"))));
            }

            // START  交易对手性质必填校验 update by wj 20200717
            if (mDto.getpTypeOne() == null || mDto.getpTypeOne().isEmpty()) {
                errorList.add(new ValidMessage("pTypeOne",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeOne"))));
            }
            if (mDto.getpTypeTwo() == null || mDto.getpTypeTwo().isEmpty()) {
                errorList.add(new ValidMessage("pTypeTwo",
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent002.pTypeTwo"))));
            }
            // END  交易对手性质必填校验 update by wj 20200717
        }
        else if (DictCodeListExt1.交易对手类型_内部交易对手.getValue().equals(mDto.getCounterType())) {
            // 判断交易对手名称是否为空
            if (mDto.getCounterName() == null || mDto.getCounterName().isEmpty()) {
                errorList.add(new ValidMessage("counterName",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.counterName"))));
            }
            // 判断交易对手代码是否为空
            if (mDto.getCounterId() == null || mDto.getCounterId().isEmpty()) {
                errorList.add(new ValidMessage("counterId",
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.counterId"))));
            }
        }
        else {
            // 判断费用信息是否为空
            List<FinCashDto> finDto = mDto.getSelectFinCash();
            if (finDto == null || finDto.isEmpty()) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("MstTradeOpponent001.FeeInfo"))));
            }

            Map<String, Object> map = BeanUtil.object2Map(mDto);
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            // 通道类型的交易对手待检测的字段（由于公用一个实体类，字段不能再实体类中进行检测，这里进行逐字段检查）
            String[] checkItem = {"counterType", "counterName", "managerId", "trusteeId", "type1",
                "repCapiInvest", "repCapUseWay", "repCapUseIndu", "repPlanProp", "repYieldFlag",
                "repMaxRate", "repMinRate", "repBuySruct", "repManageType", "repIsCtrlBttom",
                "repAmt", "repPartyCode", "repRegistCode", "repSetDate", "repEndDate",
                "repTradeFeeRate", "repAgencyFeeRate", "repOtherFeeRate"};// 通道需要进行检测的必填项
            List<String> checkItemList = Arrays.asList(checkItem);
            while (iterator.hasNext()) {
                String next = iterator.next();
                // 判断是否是需要进行判断的字段
                if (checkItemList.indexOf(next) < 0) {
                    continue;
                }
                // else if((next.equals("trusteeId") || next.equals("managerId"))
                // && map.get(next) == null){
                // errorList.add(new
                // ValidMessage(next,getMessage("FW_E_0006",getMessage("MstTradeOpponent001."+next))));
                // }
                // 是否有预期收益，如果有则最低收益和最高收益必填
                else if (next.equals("repMinRate") || next.equals("repMaxRate")) {
                    if (map.get("repYieldFlag") != null && map.get("repYieldFlag").equals("Y")
                        && map.get(next) == null) {
                        errorList.add(new ValidMessage(next,
                            getMessage("FW_E_0006", getMessage("MstTradeOpponent001." + next))));
                    }
                }
                // 拼接错误信息
                else if (map.get(next) == null || map.get(next).toString().isEmpty()) {
                    errorList.add(new ValidMessage(next,
                        getMessage("FW_E_0006", getMessage("MstTradeOpponent001." + next))));
                }
            }
        }
        return errorList;
    }
}
