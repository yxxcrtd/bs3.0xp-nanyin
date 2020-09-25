/*
 * 文件名：EtrBondFirstLBidDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrbondfirstlbidding.validator;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.service.EtrBondFirstBiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年5月6日
 * @see EtrBondFirstLBiddingDtoValidator
 * @since 1.0
 */
@Component
public class EtrBondFirstLBiddingDtoValidator implements BeanValidator{

    @Autowired
    private EtrBondFirstBiddingService service;
    List<ValidMessage> resultList = new ArrayList<ValidMessage>();

    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {

        List<ValidMessage> errorList = new ArrayList<>();
        EtrBondFirstLBiddingDto dto = (EtrBondFirstLBiddingDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrBondFirstLBiddingDto> listDto = dto.getSelectedItem();
            for (EtrBondFirstLBiddingDto entity : listDto) {
                entity.setOperateType(BussinessDictCodeList.委托操作类型_删除.getValue());
                errorList.addAll(service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrBondFirstLBiddingDto> listDto = dto.getSelectedItem();
            for (EtrBondFirstLBiddingDto entity : listDto) {
                entity.setOperateType(BussinessDictCodeList.委托操作类型_关闭.getValue());
                errorList.addAll(service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrBondFirstLBiddingDto> listDto = dto.getSelectedItem();
            for (EtrBondFirstLBiddingDto entity : listDto) {
                entity.setOperateType(BussinessDictCodeList.委托操作类型_撤回.getValue());
                if (StringUtil.isNotEmpty(entity.getWinPriceStr())) {
                    errorList.add(new ValidMessage("", entity.getWinPriceStr() + "中标结果已存在不能关闭！"));
                    return errorList;
                }
                errorList.addAll(service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(BussinessDictCodeList.委托操作类型_修改.getValue());
            if (StringUtil.equals(dto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue())
                && !StringUtil.isNotEmpty(dto.getPortfolioId())) {
                errorList.add(new ValidMessage("finprodId", this.getMessage("ETR_BOND_001")));
            }
            if (StringUtil.isNotEmpty(dto.getWinPriceStr())) {
                errorList.add(new ValidMessage("", dto.getWinPriceStr() + "中标结果已存在不能修改！"));
                return errorList;
            }
//            存在系统变量是否有截标 为01 是时 增加截标check
            String isCloseBid = CacheUtil.getSysParam(BussinessConstant.IS_CLOSE_BID);
            if (StringUtil.equals(isCloseBid, DictCodeList.是否_是.getValue())&&service.checkIsBidClose(dto) > 0){
                errorList.add(new ValidMessage(getMessage("ETR_BOND_007")));
            }
            errorList.addAll(service.check(dto));
        }
        else {
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            Date entrustDate = dto.getEntrustDate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
//                if(finvdate != null && vdate.before(finvdate)){
//                    errorList.add(new ValidMessage("vdate",this.getMessage("ETR_X_006")+DateUtil.format(finvdate)));
//                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+DateUtil.format(finmdate)));
                }
                if (null != entrustDate &&(entrustDate.before(vdate) || entrustDate.after(mdate))) {
                    errorList.add(new ValidMessage("entrustDate",this.getMessage("投标日要在委托有效期内，在委托开始日和委托结束日之间!")
                        + DateUtil.format(vdate) + GlobalConstant.COMMA + DateUtil.format(mdate)));
                }
            }
            // delete by renping 改为共通处理 2019-08-22 start
//            BigDecimal entrustNum = dto.getEntrustNum();
//            if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH),"01")){
//                BigDecimal limitAmt = service.selectLimitAmt(dto);
//                if(limitAmt != null && entrustNum.compareTo(limitAmt) > 0){
//                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_BOND_005")+limitAmt));
//                }
//            }
            // delete by renping 改为共通处理 2019-08-22 end
            BussEtrInvestPersonInfoDto personDto =  dto.getEtrInvestPersonInfo();
            if (StringUtil.isNotEmpty(personDto) && !StringUtil.isNotEmpty(personDto.getCounterId()) ) {
                errorList.add(new ValidMessage("etrInvestPersonInfo.counterId",  "请输入交易对手！"));
            }
//            if (service.checkIsBidClose(dto) > 0){
//                errorList.add(new ValidMessage("", this.getMessage("ETR_BOND_007")));
//            }
            //            存在系统变量是否有截标 为01 是时 增加截标check
            String isCloseBid = CacheUtil.getSysParam(BussinessConstant.IS_CLOSE_BID);
            if (StringUtil.equals(isCloseBid, DictCodeList.是否_是.getValue())&&service.checkIsBidClose(dto) > 0){
                errorList.add(new ValidMessage(getMessage("ETR_BOND_007")));
            }
            errorList.addAll(service.check(dto));
        }
        //是否展示资产四五级分类
        if(StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"),"01")) {
        	if(StringUtil.isEmpty(dto.getAssetType11())) {
        		resultList.add(new ValidMessage("type11",
                        getMessage("COMMON_E_0057", getMessage("EtrBondFirstLBidding002.type11"))));
        	}
        	 
        }
        return errorList;
    
    }

}
