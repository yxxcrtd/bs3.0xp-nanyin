/*
 * 文件名：ValDespositAssetQuery002CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.posit.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.assets.posit.dao.ValDespositAssetQuery002Mapper;
import com.joyintech.tams.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.valuation.assets.posit.service.ValDespositAssetQuery002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 新建修改验证
 * 
 * @author HuangJian
 * @version 1.0 2018年10月20日
 * @see ValDespositAssetQuery002CheckValidator
 * @since 1.0
 */
@Component
public class ValDespositAssetQuery002CheckValidator implements BeanValidator {

    /**
     * ValDespositAssetQuery002Mapper
     */
    @Autowired
    private ValDespositAssetQuery002Mapper valDespositAssetQuery002Mapper;

//    /**
//     * ValDespositAssetQuery001Mapper
//     */
//    @Autowired
//    private ValDespositAssetQuery001Mapper valDespositAssetQuery001Mapper;

    /**
     * valDespositAssetQuery002Service
     */
    @Autowired
    private ValDespositAssetQuery002Service valDespositAssetQuery002Service;

    /**
     * {@inheritDoc}
     */
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();

        ValDespositAssetQuery002Dto dto = (ValDespositAssetQuery002Dto)value;

        // 起息日
        Date vdate = dto.getVdate();
        // 到期日
        Date mdate = dto.getMdate();

        List<DateTableDto> table1 = (List<DateTableDto>)dto.getTable1();

        List<DateTableDto> table2 = (List<DateTableDto>)dto.getTable2();

        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", dto.getFinprodId());
        map.put("finprodType", dto.getFinprodType());
        
        if (valDespositAssetQuery002Service.checkBusinessDataInFlow(dto)) {
            String msg = this.getMessage("ValDespositAsset.finprodId") + dto.getFinprodId()
                         + this.getMessage("ValDespositAsset.finprodType");
            validMessageList.add(
                new ValidMessage(getMessage("COMMON_E_0021", msg, dto.getFinprodType())));
            return validMessageList;

        }
        
        
        
        
        // 新建
        if (DictCodeList.是否_是.getValue().equals(dto.getIsEdit())) {
            //资产代码长度
            if (valDespositAssetQuery002Service.length(dto.getFinprodId()) > 32) {
                
                String msg = this.getMessage("DESSPOSIT_ASSET_E_018");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            if (valDespositAssetQuery002Mapper.checkFinprodId(map)) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_005");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }

        }
        // 修改
        // if (DictCodeList.是否_否.getValue().equals(dto.getIsEdit())) {
        // if (valDespositAssetQuery001Mapper.checkFinprodId(dto.getFinprodId())) {
        // String msg = this.getMessage("DESSPOSIT_ASSET_E_006");
        // validMessageList.add(new ValidMessage(msg));
        // return validMessageList;
        //
        // }
        // }
        //资产名称长度
        if (valDespositAssetQuery002Service.length(dto.getFinprodName()) > 200) {

            String msg = this.getMessage("DESSPOSIT_ASSET_E_019");
            validMessageList.add(new ValidMessage(msg));
            return validMessageList;
        }

        // 计息开始日不能大于计息结束日
        if (StringUtil.isNotEmpty(vdate) && StringUtil.isNotEmpty(mdate)) {
            if (!(DateUtil.compareDate(mdate, vdate) == 1)) {
                String msg = this.getMessage("VAL_COMMON_E_0001",
                    this.getMessage("ValDespositAsset.vdate"),
                    this.getMessage("ValDespositAsset.mdate"));
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
        }

        // 利率调整表不为空
        if (table1.size() > 0) {
            int count = 0;
            int counteffDate = 0;
            int countyield = 0;
            Map<String, Object> tablemap = new HashMap<>();
            for (DateTableDto item : table1) {
                if (tablemap.containsKey(item.getEffDate())) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_011");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                else {
                    tablemap.put(item.getEffDate(), "");
                }
                if (DateUtil.parse(item.getEffDate()).equals(dto.getVdate())) {
                    count++ ;
                }
                if (StringUtils.isEmpty(DateUtil.parse(item.getEffDate()))) {
                    counteffDate++ ;
                }
                if (StringUtils.isEmpty(item.getYield())) {
                    countyield++ ;
                }

            }
            // 利率调整信息中有且只有一条与起息日相同的数据
            if (count != 1) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_001");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            // 存在某条生效日为空
            if (counteffDate != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_002");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            // 存在某条数据利率值为空
            if (countyield != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_003");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }

        }
        // 利率调整表为空
        else {
            String msg = this.getMessage("VAL_COMMON_E_0002",
                this.getMessage("ValDespositAsset.table1"));
            validMessageList.add(new ValidMessage(msg));
            return validMessageList;
        }
        // 付息计划表不为空
        if (table2.size() > 0) {
            int countVdate = 0;
            int countMdate = 0;
            int countPayDate = 0;
            int countIntAmt = 0;
            for (DateTableDto item : table2) {
                if (StringUtils.isEmpty(DateUtil.parse(item.getVdate()))) {
                    countVdate++ ;
                }
                if (StringUtils.isEmpty(DateUtil.parse(item.getMdate()))) {
                    countMdate++ ;
                }
                if (StringUtils.isEmpty(DateUtil.parse(item.getPayDate()))) {
                    countPayDate++ ;
                }
                if (StringUtils.isEmpty(item.getIntAmt())) {
                    countIntAmt++ ;
                }

            }
            //
            if (countVdate != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_012");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            //
            if (countMdate != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_013");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            //
            if (countPayDate != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_014");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            //
            if (countIntAmt != 0) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_015");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }

            // 如果只存在一条数据 判断起息日 到期日
            if (table2.size() == 1) {
                // 起息日
                if (!dto.getVdate().equals(DateUtil.parse(table2.get(0).getVdate()))) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_007");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                // 到期日
                if (!dto.getMdate().equals(DateUtil.parse(table2.get(0).getMdate()))) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_008");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
                // 同一条计息结束 不能大于计划支付日
                if ((DateUtil.compareDate(table2.get(0).getMdate(),
                    table2.get(0).getPayDate()) == 1)) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_016");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                // 推算方式为首次付息日时,首次付息日必填
                if (DictCodeList.推算方式_首次支付日.getValue().equals(dto.getCalcRule())) {
                    if (StringUtils.isEmpty(dto.getFirstPayDate())) {
                        String msg = this.getMessage("VAL_COMMON_E_0002",
                            this.getMessage("ValDespositAsset.firstPayDate"));
                        validMessageList.add(new ValidMessage(msg));
                        return validMessageList;
                    }
                    else {

                        if (!dto.getFirstPayDate().equals(
                            DateUtil.parse(table2.get(0).getMdate()))) {
                            String msg = this.getMessage("DESSPOSIT_ASSET_E_017");
                            validMessageList.add(new ValidMessage(msg));
                            return validMessageList;
                        }
                    }

                }

            }
            // 多条数据
            else {
                // 进行升序排列
                Collections.sort(table2, new Comparator<DateTableDto>() {

                    public int compare(DateTableDto o1, DateTableDto o2) {
                        return o1.getMdate().compareTo(o2.getMdate());
                    }
                });
                // 进行升序排列
                Collections.sort(table2, new Comparator<DateTableDto>() {

                    public int compare(DateTableDto o1, DateTableDto o2) {
                        return o1.getVdate().compareTo(o2.getVdate());
                    }
                });
                // 推算方式为首次付息日时,首次付息日必填
                if (DictCodeList.推算方式_首次支付日.getValue().equals(dto.getCalcRule())) {
                    if (StringUtils.isEmpty(dto.getFirstPayDate())) {
                        String msg = this.getMessage("VAL_COMMON_E_0002",
                            this.getMessage("ValDespositAsset.firstPayDate"));
                        validMessageList.add(new ValidMessage(msg));
                        return validMessageList;
                    }
                    else {
                        
                        if (!dto.getFirstPayDate().equals(
                            DateUtil.parse(table2.get(0).getVdate()))) {
                            String msg = this.getMessage("DESSPOSIT_ASSET_E_017");
                            validMessageList.add(new ValidMessage(msg));
                            return validMessageList;
                        }
                    }

                }

                // 最小的计息开始日 应该等于起息日
                if (!DateUtil.parse(table2.get(0).getVdate()).equals(dto.getVdate())) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_007");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                // 最大的计息结束日 应该等于 到期日
                if (!DateUtil.parse(table2.get(table2.size() - 1).getMdate()).equals(
                    dto.getMdate())) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_008");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
                // 循环判断 计息结束日+1 等于下一条计息开始日（0-----size-2）
                for (int i = 0; i < table2.size() - 1; i++ ) {
                    // 同一条计息开始日 不能大于计息结束日
                    if (!(DateUtil.compareDate(table2.get(i).getMdate(),
                        table2.get(i).getVdate()) == 1)) {
                        String msg = this.getMessage("DESSPOSIT_ASSET_E_010");
                        validMessageList.add(new ValidMessage(msg));
                        return validMessageList;

                    }
                    // 计息结束日+1 等于下一条计息开始日
                    if (!DateUtil.parse(table2.get(i).getMdate()).equals(
                        DateUtil.parse(table2.get(i + 1).getVdate()))) {
                        String msg = this.getMessage("DESSPOSIT_ASSET_E_009");
                        validMessageList.add(new ValidMessage(msg));
                        return validMessageList;

                    }

                    // 同一条计息结束 不能大于计划支付日
                    if ((DateUtil.compareDate(table2.get(i).getMdate(),
                        table2.get(i).getPayDate()) == 1)) {
                        String msg = this.getMessage("DESSPOSIT_ASSET_E_016");
                        validMessageList.add(new ValidMessage(msg));
                        return validMessageList;

                    }

                }
                // size-1
                if (!DateUtil.parse(table2.get(table2.size() - 2).getMdate()).equals(
                    DateUtil.parse(table2.get(table2.size() - 1).getVdate()))) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_009");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                if (!(DateUtil.compareDate(table2.get(table2.size() - 1).getMdate(),
                    table2.get(table2.size() - 1).getVdate()) == 1)

                ) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_010");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }
                // 同一条计息结束 不能大于计划支付日
                if ((DateUtil.compareDate(table2.get(table2.size() - 1).getMdate(),
                    table2.get(table2.size() - 1).getPayDate()) == 1)) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_016");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;

                }

            }

        }
        // 付息计划表为空
        else {
            String msg = this.getMessage("VAL_COMMON_E_0002",
                this.getMessage("ValDespositAsset.table2"));
            validMessageList.add(new ValidMessage(msg));
            return validMessageList;
        }
        //备注长度
        if (valDespositAssetQuery002Service.length(dto.getRemark()) > 600) {

            String msg = this.getMessage("DESSPOSIT_ASSET_E_020");
            validMessageList.add(new ValidMessage(msg));
            return validMessageList;
        }
        // 活期类资产没有还本计划表
        if(DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getType1())){
            
        }else{
        /** modify by dongjun on 20181203 start**/
        List<DateTableDto> table3 = (List<DateTableDto>)dto.getTable3();
        // 按照日期从小到大排序
        Collections.sort(table3, new Comparator<DateTableDto>() {

            public int compare(DateTableDto o1, DateTableDto o2) {
                return o1.getRepayDate().compareTo(o2.getRepayDate());
            }
        });
        if(StringUtil.isNotEmpty(table3) && table3.size() > 0 ){
            BigDecimal prinAmt = BigDecimal.ZERO;
            for(int i = 0; i < table3.size(); i++){
                // 还本计划还本日不能为空
                if (StringUtil.isEmpty(table3.get(i).getRepayDate())) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_021");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
                // 还本计划支付日不能为空
                if (StringUtil.isEmpty(table3.get(i).getPayDate())) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_022");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }         
                // 还本计划支付日小于还本日
                if (DateUtil.compareDate(DateUtil.parse(table3.get(i).getRepayDate()), DateUtil.parse(table3.get(i).getPayDate())) > 0 ) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_023");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
                // 还本计划还本金额等于零
                if (ArithmeticUtil.equals(table3.get(i).getPrinAmt(), BigDecimal.ZERO)) {
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_024");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }else {
                    prinAmt = ArithmeticUtil.doAdd(prinAmt,table3.get(i).getPrinAmt());
                }
            }
            
            //还本金额不等于存款本金
            if (!ArithmeticUtil.equals(prinAmt, dto.getIssueAmt())) {
                String msg = this.getMessage("DESSPOSIT_ASSET_E_025");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }
            
            //最大还本日不等于到期日
            if(table3.size() == 1){
                if(DateUtil.compareDate(dto.getMdate(), DateUtil.parse(table3.get(0).getRepayDate())) != 0){
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_026");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
            }else {
                if(DateUtil.compareDate(dto.getMdate(), DateUtil.parse(table3.get(table3.size()-1).getRepayDate())) != 0){
                    String msg = this.getMessage("DESSPOSIT_ASSET_E_026");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList; 
                }
            }
            
        }else {
            String msg = this.getMessage("VAL_COMMON_E_0002",
                this.getMessage("ValDespositAsset.table3"));
            validMessageList.add(new ValidMessage(msg));
            return validMessageList;
        }
        /** modify by dongjun on 20181203 end**/
        }
        return validMessageList;

    }

}
