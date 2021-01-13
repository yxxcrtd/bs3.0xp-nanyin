/*
 * 文件名：BussRepFinProductInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.prod.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.invest.common.calculator.RepCheckUtil;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 申报参数验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussRepFinProductInfoValidator
 * @since 1.0
 */
@Component
public class BussRepFinProductInfoValidator implements BeanValidator {
    // 中债报送验证改成配置文件读取 WangLili 2019-02-12
    @Value("${prod.bussRep}")
    String ischeckMust;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepFinProductInfoDto dto = (BussRepFinProductInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        // 产品期次
        if (StringUtil.isNotEmpty(dto.getRepFinprodStage())) {
            if (RepCheckUtil.checkByteLength(dto.getRepFinprodStage().toString(), 6)) {
                resultList.add(new ValidMessage("repFinprodStage", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repFinprodStage"))));
            }
        }
        
        
        if (!StringUtil.isNotEmpty(dto.getRepManageMode())) {
        	resultList.add(new ValidMessage("repManageMode", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("BussRepFinProductInfo.repManageMode"))));
        }
        // 发行机构代码
        if (StringUtil.isNotEmpty(dto.getRepIssuePartyId())) {
            if (RepCheckUtil.checkByteLength(dto.getRepIssuePartyId(), 6)) {
                resultList.add(new ValidMessage("repIssuePartyId", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repIssuePartyId"))));
            }
        }
        // 产品审批人身份证号
        if (StringUtil.isNotEmpty(dto.getRepApproverId())) {
            if (RepCheckUtil.checkByteLength(dto.getRepApproverId(), 30)) {
                resultList.add(new ValidMessage("repApproverId", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repApproverId"))));
            }
        }
        // 产品审批人姓名
        if (StringUtil.isNotEmpty(dto.getRepApproverName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepApproverName(), 200)) {
                resultList.add(new ValidMessage("repApproverName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repApproverName"))));
            }
        }
        else{
        	 resultList.add(new ValidMessage("repApproverName", this.getMessage("BUSS_COM_E_0038",
                     this.getMessage("BussRepFinProductInfo.repApproverName"))));
        }
        // 产品设计人身份证号
        if (StringUtil.isNotEmpty(dto.getRepDesignerId())) {
            if (RepCheckUtil.checkByteLength(dto.getRepDesignerId(), 30)) {
                resultList.add(new ValidMessage("repDesignerId", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repDesignerId"))));
            }
        }
        // 产品设计人姓名
        if (StringUtil.isNotEmpty(dto.getRepDesignerName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepDesignerName(), 200)) {
                resultList.add(new ValidMessage("repDesignerName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repDesignerName"))));
            }
        }
        else{
        	resultList.add(new ValidMessage("repDesignerName", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("BussRepFinProductInfo.repDesignerName"))));
        }
        // 投资经理身份证号
        if (StringUtil.isNotEmpty(dto.getRepManagerId())) {
            if (RepCheckUtil.checkByteLength(dto.getRepManagerId(), 30)) {
                resultList.add(new ValidMessage("repManagerId", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repManagerId"))));
            }
        }
        // 投资经理姓名
        if (StringUtil.isNotEmpty(dto.getRepManagerName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepManagerName(), 200)) {
                resultList.add(new ValidMessage("repManagerName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repManagerName"))));
            }
        }
        else{
        	resultList.add(new ValidMessage("repManagerName", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("BussRepFinProductInfo.repManagerName"))));
        }
        // 业务联络人姓名
        if (StringUtil.isNotEmpty(dto.getRepLiaisonName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepLiaisonName(), 32)) {
                resultList.add(new ValidMessage("repLiaisonName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repLiaisonName"))));
            }
        }
        // 业务联络人座机
        if (StringUtil.isNotEmpty(dto.getRepLiaisonTel())) {
            if (RepCheckUtil.checkByteLength(dto.getRepLiaisonTel(), 30)) {
                resultList.add(new ValidMessage("repLiaisonTel", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repLiaisonTel"))));
            }
        }
        // 业务联络人手机
        if (StringUtil.isNotEmpty(dto.getRepLiaisonMobile())) {
        	if(!RepCheckUtil.checkNum11(dto.getRepLiaisonMobile())){
        		resultList.add(new ValidMessage("repLiaisonMobile", this.getMessage("FW_E_0002",
                        this.getMessage("BussRepFinProductInfo.repLiaisonMobile"))));
        	}
        	else if (RepCheckUtil.checkByteLength(dto.getRepLiaisonMobile(), 11)) {
                resultList.add(new ValidMessage("repLiaisonMobile", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repLiaisonMobile"))));
            }
        }
        // 业务联络人邮箱
        if (StringUtil.isNotEmpty(dto.getRepLiaisonEmail())) {
            if (RepCheckUtil.checkByteLength(dto.getRepLiaisonMobile(), 50)) {
                resultList.add(new ValidMessage("repLiaisonEmail", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repLiaisonEmail"))));
            }
        }
        // 境外托管机构名称
        if (StringUtil.isNotEmpty(dto.getRepOutDepName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepOutDepName(), 200)) {
                resultList.add(new ValidMessage("repOutDepName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repOutDepName"))));
            }
        }
        // 实际管理人名称
        if (StringUtil.isNotEmpty(dto.getRepMname())) {
            if (RepCheckUtil.checkByteLength(dto.getRepMname(), 120)) {
                resultList.add(new ValidMessage("repMname", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repMname"))));
            }
        }
        // 收益率分档情况说明
        if (StringUtil.isNotEmpty(dto.getRepYieldComment())) {
            if (RepCheckUtil.checkByteLength(dto.getRepYieldComment(), 400)) {
                resultList.add(new ValidMessage("repYieldComment", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repYieldComment"))));
            }
        }
        // 境内托管机构名称
        if (StringUtil.isNotEmpty(dto.getRepInDepName())
            && RepCheckUtil.checkByteLength(dto.getRepInDepName(), 120)) {
            resultList.add(new ValidMessage("repInDepName", this.getMessage("FW_E_0002",
                this.getMessage("BussRepFinProductInfo.repInDepName"))));
        }
        if (StringUtil.isNotEmpty(dto.getRepCoopName())) {
            if (RepCheckUtil.checkByteLength(dto.getRepCoopName(), 120)) {
                resultList.add(new ValidMessage("repCoopName", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repCoopName"))));
            }
        }
        // 投资资产种类及比例
        if (StringUtil.isNotEmpty(dto.getRepInvTypeRat())) {
            if (RepCheckUtil.checkByteLength(dto.getRepInvTypeRat(), 300)) {
                resultList.add(new ValidMessage("repInvTypeRat", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repInvTypeRat"))));
            }else if(dto.getRepInvTypeRat().contains("（") || dto.getRepInvTypeRat().contains("）") ||
            		dto.getRepInvTypeRat().contains("：") || dto.getRepInvTypeRat().contains("；")) {
            	resultList.add(new ValidMessage("repInvTypeRat", this.getMessage("FW_E_0002",
                        this.getMessage("BussRepFinProductInfo.repInvTypeRat"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepRuleComment())) {
            if (RepCheckUtil.checkByteLength(dto.getRepRuleComment(), 256)) {
                resultList.add(new ValidMessage("repRuleComment", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repRuleComment"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepOpenBussRemark())) {
            if (RepCheckUtil.checkByteLength(dto.getRepOpenBussRemark(), 256)) {
                resultList.add(new ValidMessage("repOpenBussRemark", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repOpenBussRemark"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepRemark())) {
            if (RepCheckUtil.checkByteLength(dto.getRepRemark(), 256)) {
                resultList.add(new ValidMessage("repRemark", this.getMessage("FW_E_0002",
                    this.getMessage("BussRepFinProductInfo.repRemark"))));
            }
        }
        // 境内托管机构代码
        if (StringUtil.isNotEmpty(dto.getRepInDepCode())) {
            // 规则： CXXXXX（XXXXX 为数字）
            Pattern pattern = Pattern.compile("^[C]{1}[0-9]{5}$");
            Matcher matcher = pattern.matcher(dto.getRepInDepCode());
            if (!matcher.matches()) {
                resultList.add(new ValidMessage("repInDepCode", getMessage("FW_E_0002",
                    getMessage("BussRepFinProductInfo.repInDepCode"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepAvgOpenCount())) {
            if (dto.getRepAvgOpenCount().compareTo(new BigDecimal(366)) > 0) {
                resultList.add(
                    new ValidMessage("repAvgOpenCount", getMessage("REP_E_0008")));
            }
        }
        
        // modify by zhangliang 20190226 start
        // 资金投向地区
        if (StringUtil.isNotEmpty(dto.getRepInvestArea())) {
            if (StringUtil.equals(dto.getRepInvestArea(),
                DictCodeListExt2.资金投向地区_境外.getValue())
                || StringUtil.equals(dto.getRepInvestArea(),
                    DictCodeListExt2.资金投向地区_境内和境外.getValue())) {
                // 产品投资国家或地区
                if (StringUtil.isEmpty(dto.getRepInvestCountry())) {
                    resultList.add(
                        new ValidMessage("repInvestCountry", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repInvestCountry"))));
                }
                else {
                    if (StringUtil.equals(dto.getRepInvestArea(),
                            DictCodeListExt2.资金投向地区_境外.getValue())
                    		&& StringUtil.indexOf(dto.getRepInvestCountry(),
                        DictCodeListExt2.所属国家或地区_中国.getValue()) > -1) {
                        resultList.add(
                            new ValidMessage("repInvestCountry", getMessage("REP_E_0006")));
                    }
                }
                // 境外托管机构国别
                if (StringUtil.isEmpty(dto.getRepOutDepCtry())) {
                    resultList.add(
                        new ValidMessage("repOutDepCtry", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repOutDepCtry"))));
                }
                else {
                    if (StringUtil.indexOf(dto.getRepOutDepCtry(),
                        DictCodeListExt2.所属国家或地区_中国.getValue()) > -1) {
                        resultList.add(
                            new ValidMessage("repOutDepCtry", getMessage("REP_E_0007")));
                    }
                }
                // 境外托管机构名称
                if (StringUtil.isEmpty(dto.getRepOutDepName())) {
                    resultList.add(
                        new ValidMessage("repOutDepName", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repOutDepName"))));
                }
            }
            if (StringUtil.equals(dto.getRepInvestArea(),
                DictCodeListExt2.资金投向地区_境内.getValue())
                || StringUtil.equals(dto.getRepInvestArea(),
                    DictCodeListExt2.资金投向地区_境内和境外.getValue())) {
                // 理财业务服务模式
                if (StringUtil.isEmpty(dto.getRepFinanSerModel())) {
                    resultList.add(
                        new ValidMessage("repFinanSerModel", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repFinanSerModel"))));
                }
                if (StringUtil.isEmpty(dto.getRepInDepName())) {
                    resultList.add(new ValidMessage("repInDepName", getMessage(
                        "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repInDepName"))));
                }
            }
        }
        // 产品管理模式
        if (StringUtil.isNotEmpty(dto.getRepPmmode())) {
            // 实际管理人名称
            if (StringUtil.equals(dto.getRepPmmode(),
                DictCodeListExt1.产品管理模式_资产管理公司为实际管理人.getValue())
                || StringUtil.equals(dto.getRepPmmode(),
                    DictCodeListExt1.产品管理模式_其他机构为实际管理人.getValue())) {
                if (StringUtil.isEmpty(dto.getRepMname())) {
                    resultList.add(new ValidMessage("repMname", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repMname"))));
                }
            }
        }
//        // 是否有预期收益率
//        if (StringUtil.isNotEmpty(dto.getRepIsYield()) && StringUtil.equals(dto.getRepIsYield(),
//            DictCodeList.是否_是.getValue())) {
            if (StringUtil.isEmpty(dto.getRepIsYieldBasis())) {
                resultList.add(
                    new ValidMessage("repIsYieldBasis", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repIsYieldBasis"))));
            }
            // 预计客户最高年收益率%
            if (!StringUtil.isNotEmpty(dto.getRepHighYield())) {
//                resultList.add(new ValidMessage("repHighYield", getMessage(
//                    "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repHighYield"))));
            }
            else {
                if (dto.getRepHighYield().compareTo(BigDecimal.ZERO) < 0) {
                    resultList.add(
                        new ValidMessage("repHighYield", getMessage("BUSS_COM_E_0004",
                            getMessage("BussRepFinProductInfo.repHighYield"), "0")));

                }
            }
            // 平均预计客户年收益率
//            if (!StringUtil.isNotEmpty(dto.getRepExpAvgYield())) {
//                resultList.add(
//                    new ValidMessage("repExpAvgYield", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repExpAvgYield"))));
//            }
            // 预计客户最低年收益率%
//            if (!StringUtil.isNotEmpty(dto.getRepLowYield())) {
//                resultList.add(new ValidMessage("repLowYield", getMessage(
//                    "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repLowYield"))));
//            }

            if (StringUtil.isNotEmpty(dto.getRepHighYield())
                && StringUtil.isNotEmpty(dto.getRepLowYield())) {
                if (dto.getRepLowYield().compareTo(dto.getRepHighYield()) > 0) {
                    resultList.add(new ValidMessage("repLowYield",
                        getMessage("BUSS_COM_E_0003",
                            getMessage("BussRepFinProductInfo.repLowYield"),
                            getMessage("BussRepFinProductInfo.repHighYield"))));
                }
            }

//            if (StringUtil.isNotEmpty(dto.getRepExpAvgYield())
//                && StringUtil.isNotEmpty(dto.getRepLowYield())) {
//                if (dto.getRepLowYield().compareTo(dto.getRepExpAvgYield()) > 0) {
//                    resultList.add(new ValidMessage("repLowYield",
//                        getMessage("BUSS_COM_E_0003",
//                            getMessage("BussRepFinProductInfo.repLowYield"),
//                            getMessage("BussRepFinProductInfo.repExpAvgYield"))));
//                }
//            }

//            if (StringUtil.isNotEmpty(dto.getRepExpAvgYield())
//                && StringUtil.isNotEmpty(dto.getRepHighYield())) {
//                if (dto.getRepExpAvgYield().compareTo(dto.getRepHighYield()) > 0) {
//                    resultList.add(new ValidMessage("repExpAvgYield",
//                        getMessage("BUSS_COM_E_0003",
//                            getMessage("BussRepFinProductInfo.repExpAvgYield"),
//                            getMessage("BussRepFinProductInfo.repHighYield"))));
//                }
//            }
            // 收益率分档情况说明
//            if (StringUtil.isEmpty(dto.getRepYieldComment())) {
//                resultList.add(
//                    new ValidMessage("repYieldComment", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repYieldComment"))));
//
//            }
//        }
        // 起点销售金额
        if (StringUtil.isNotEmpty(dto.getRepSaleAmtStr())) {
            if (dto.getRepSaleAmtStr().compareTo(BigDecimal.ZERO) < 0) {
                resultList.add(new ValidMessage("repSaleAmtStr", getMessage("BUSS_COM_E_0004",
                    getMessage("BussRepFinProductInfo.repSaleAmtStr"), "0")));
            }
        }
        // 计划募集金额 在销售参数中 校验
//        if (StringUtil.isNotEmpty(dto.getRepRaiseAmtPlan())) {
//            if (dto.getRepRaiseAmtPlan().compareTo(BigDecimal.ZERO) < 0) {
//                resultList.add(
//                    new ValidMessage("repRaiseAmtPlan", getMessage("BUSS_COM_E_0004",
//                        getMessage("BussRepFinProductInfo.repRaiseAmtPlan"), "0")));
//            }
//        }
        // 合作模式
        if (StringUtil.isNotEmpty(dto.getRepContMode())) {
            // 合作机构名称
            if (!StringUtil.equals(dto.getRepContMode(),
                DictCodeListExt2.合作模式_独立运作.getValue())) {
                if (StringUtil.isEmpty(dto.getRepCoopName())) {
                    resultList.add(new ValidMessage("repCoopName", getMessage(
                        "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repCoopName"))));
                }
            }
        }
        // 产品增信标识
        if (StringUtil.isNotEmpty(dto.getRepCreditFlag())) {
            if (StringUtil.equals(dto.getRepCreditFlag(),
                DictCodeList.是否_是.getValue())) {
                if (StringUtil.isEmpty(dto.getRepCreditType())) {
                    resultList.add(
                        new ValidMessage("repCreditType", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repCreditType"))));
                }

                if (!StringUtil.isNotEmpty(dto.getRepCreditMode())) {
                    resultList.add(
                        new ValidMessage("repCreditMode", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repCreditMode"))));
                }
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepOperateMode())
            && (StringUtil.equals(dto.getRepOperateMode(),
                DictCodeListExt2.产品运作模式_开放式净值型.getValue()) || StringUtil.equals(
                    dto.getRepOperateMode(),
                    DictCodeListExt2.产品运作模式_开放式非净值型.getValue()))) {
        	//界面中将首次开放周期起始日已删除，故先将此校验去除  delete by wm 2019-05-09 begin
//            if (!StringUtil.isNotEmpty(dto.getRepFirstOpenDay())) {
//                resultList.add(new ValidMessage("repFirstOpenDay", getMessage(
//                    "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repFirstOpenDay"))));
//            }
          //界面中将首次开放周期起始日已删除，故先将此校验去除  delete by wm 2019-05-09 end
            if (!StringUtil.isNotEmpty(dto.getRepAvgOpenCount())) {
                resultList.add(new ValidMessage("repAvgOpenCount", getMessage(
                    "BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repAvgOpenCount"))));
            }
            if (!StringUtil.isNotEmpty(dto.getRepOpenBuss())) {
                resultList.add(new ValidMessage("repOpenBuss", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.repOpenBuss"))));
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepOpenCircle())) {
            if (StringUtil.equals(dto.getRepOpenCircle(),
                DictCodeListExt2.规律开放周期_其他规律开放周期.getValue())) {
                if (!StringUtil.isNotEmpty(dto.getRepOpenCircleDays())) {
                    resultList.add(
                        new ValidMessage("repOpenCircleDays", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repOpenCircleDays"))));
                }
            }
        }
        if (StringUtil.isNotEmpty(dto.getRepOpenBuss())) {
            if (StringUtil.equals(dto.getRepOpenBuss(),
                    DictCodeListExt2.开放期业务_其他.getValue())) {
                if (StringUtil.isEmpty(dto.getRepOpenBussRemark())) {
                    resultList.add(
                        new ValidMessage("repOpenBussRemark", getMessage("BUSS_COM_E_0038",
                            getMessage("BussRepFinProductInfo.repOpenBussRemark"))));
                }
            }
        }
        // modify by zhangliang 20190226 end

        if (StringUtil.equals(dto.getPageId(), "FinProductBuild002")
            || StringUtil.equals(dto.getPageId(), "FinProductScheduling002")
            || StringUtil.equals(dto.getPageId(), "FinNetValueProductBuild002")) {
            // 产品期次
            if (!StringUtil.isNotEmpty(dto.getRepFinprodStage())) {
                resultList.add(new ValidMessage("repFinprodStage", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.repFinprodStage"))));
            }
            if("1".equals(ischeckMust)){
                //delete by wm 因界面去掉此字段，故去掉校验 start 2019-05-10
                // 发行机构代码
    //            if (StringUtil.isEmpty(dto.getRepIssuePartyId())) {
    //                resultList.add(new ValidMessage("repIssuePartyId", getMessage("BUSS_COM_E_0038",
    //                    getMessage("BussRepFinProductInfo.repIssuePartyId"))));
    //            }
                //delete by wm 因界面去掉此字段，故去掉校验 end 2019-05-10
                // 产品审批人身份证号
                if (StringUtil.isEmpty(dto.getRepApproverId())) {
                    resultList.add(new ValidMessage("repApproverId", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repApproverId"))));
                }
                // 产品设计人身份证号
                if (StringUtil.isEmpty(dto.getRepDesignerId())) {
                    resultList.add(new ValidMessage("repDesignerId", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repDesignerId"))));
                }
                // 投资经理身份证号
                if (StringUtil.isEmpty(dto.getRepManagerId())) {
                    resultList.add(new ValidMessage("repManagerId", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repManagerId"))));
                }
                // 业务联络人姓名
                if (StringUtil.isEmpty(dto.getRepLiaisonName())) {
                    resultList.add(new ValidMessage("repLiaisonName", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repLiaisonName"))));
                }
                // 业务联络人座机
                if (StringUtil.isEmpty(dto.getRepLiaisonTel())) {
                    resultList.add(new ValidMessage("repLiaisonTel", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repLiaisonTel"))));
                }
                // 业务联络人手机
                if (StringUtil.isEmpty(dto.getRepLiaisonMobile())) {
                    resultList.add(new ValidMessage("repLiaisonMobile", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repLiaisonMobile"))));
                }
                // 业务联络人邮箱
                if (StringUtil.isEmpty(dto.getRepLiaisonEmail())) {
                    resultList.add(new ValidMessage("repLiaisonEmail", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repLiaisonEmail"))));
                }
                // 资金投向地区
                if (StringUtil.isEmpty(dto.getRepInvestArea())) {
                    resultList.add(new ValidMessage("repInvestArea", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repInvestArea"))));
                }
//                // 目标客户类型
//                if (StringUtil.isEmpty(dto.getRepInvestorType())) {
//                    resultList.add(new ValidMessage("repInvestorType", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repInvestorType"))));
//                }
                // 产品运作模式
                if (StringUtil.isEmpty(dto.getRepOperateMode())) {
                    resultList.add(new ValidMessage("repOperateMode", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repOperateMode"))));
                }
                // 会计核算方式
                //close by chenxiaoqiu 20191206 因界面去掉此字段，故去掉校验
//                if (StringUtil.isEmpty(dto.getRepBookMode())) {
//                    resultList.add(new ValidMessage("repBookMode", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repBookMode"))));
//                }
                // 产品资产配置方式
                if (StringUtil.isEmpty(dto.getRepCmmode())) {
                    resultList.add(new ValidMessage("repCmmode",
                        getMessage("BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repCmmode"))));
                }
                // 产品管理模式
                if (StringUtil.isEmpty(dto.getRepPmmode())) {
                    resultList.add(new ValidMessage("repPmmode",
                        getMessage("BUSS_COM_E_0038", getMessage("BussRepFinProductInfo.repPmmode"))));
                }
                // 产品定价方式
                if (StringUtil.isEmpty(dto.getRepPricedMode())) {
                    resultList.add(new ValidMessage("repPricedMode", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repPricedMode"))));
                }
                // 投资资产类型
                //close by chenxiaoqiu  20191206 因界面去掉此字段，故去掉校验
//                if (StringUtil.isEmpty(dto.getRepInvAssetType())) {
//                    resultList.add(new ValidMessage("repInvAssetType", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repInvAssetType"))));
//                }
                // 是否有预期收益率
                if (StringUtil.isEmpty(dto.getRepIsYield())) {
                    resultList.add(new ValidMessage("repIsYield", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repIsYield"))));
                }
                //delete by wm 因界面去掉此字段，故去掉校验 start 2019-05-10
                // 起点销售金额
    //            if (!StringUtil.isNotEmpty(dto.getRepSaleAmtStr())) {
    //                resultList.add(new ValidMessage("repSaleAmtStr", getMessage("BUSS_COM_E_0038",
    //                    getMessage("BussRepFinProductInfo.repSaleAmtStr"))));
    //            }
                //delete by wm 因界面去掉此字段，故去掉校验 end 2019-05-10
                // 兑付本金币种
                if (!StringUtil.isNotEmpty(dto.getRepCashPrinCcy())) {
                    resultList.add(new ValidMessage("repCashPrinCcy", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repCashPrinCcy"))));
                }
                // 兑付收益币种
                if (!StringUtil.isNotEmpty(dto.getRepCashProfCcy())) {
                    resultList.add(new ValidMessage("repCashProfCcy", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repCashProfCcy"))));
                }
                // 计划募集金额 销售参数校验
//                if (!StringUtil.isNotEmpty(dto.getRepRaiseAmtPlan())) {
//                    resultList.add(new ValidMessage("repRaiseAmtPlan", getMessage("BUSS_COM_E_0038",
//                        getMessage("BussRepFinProductInfo.repRaiseAmtPlan"))));
//                }
                // 投资者风险偏好
                if (!StringUtil.isNotEmpty(dto.getRepRiskType())) {
                    resultList.add(new ValidMessage("repRiskType", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repRiskType"))));
                }
                // 产品风险等级
                if (!StringUtil.isNotEmpty(dto.getRepRiskLevel())) {
                    resultList.add(new ValidMessage("repRiskLevel", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repRiskLevel"))));
                }
                // 发行机构提前终止权标识
                if (!StringUtil.isNotEmpty(dto.getRepTermRightFlag())) {
                    resultList.add(new ValidMessage("repTermRightFlag", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repTermRightFlag"))));
                }
                // 客户赎回权标识
                if (!StringUtil.isNotEmpty(dto.getRepCusRedeemFlag())) {
                    resultList.add(new ValidMessage("repCusRedeemFlag", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repCusRedeemFlag"))));
                }
                // 合作模式
                if (StringUtil.isEmpty(dto.getRepContMode())) {
                    resultList.add(new ValidMessage("repContMode", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repContMode"))));
                }
                // 投资本金到账日
                if (!StringUtil.isNotEmpty(dto.getRepInvestPrinDate())) {
                    resultList.add(new ValidMessage("repInvestPrinDate", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repInvestPrinDate"))));
                }
                // 投资收益到账日
                if (!StringUtil.isNotEmpty(dto.getRepInvestProfDate())) {
                    resultList.add(new ValidMessage("repInvestProfDate", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repInvestProfDate"))));
                }
                // 产品增信标识
                if (StringUtil.isEmpty(dto.getRepCreditFlag())) {
                    resultList.add(new ValidMessage("repCreditFlag", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repCreditFlag"))));
                }
                // 投资资产种类及比例
                if (StringUtil.isEmpty(dto.getRepInvTypeRat())) {
                    resultList.add(new ValidMessage("repInvTypeRat", getMessage("BUSS_COM_E_0038",
                        getMessage("BussRepFinProductInfo.repInvTypeRat"))));
                }

                //产品增信标识”选择“01 有” 时 add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepCreditFlag()) 
                		&& StringUtil.equals(dto.getRepCreditFlag(), DictCodeListExt1.增信标识_有.getValue())
                		) {
                	//产类型品增信机构 (若“产品增信标识”选择“01 有”，该要素必填)
                	if(!StringUtil.isNotEmpty(dto.getRepCreditType())) {
                		resultList.add(new ValidMessage("repCreditType", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repCreditType"))));
                	}
                	//产品增信形式 (若“产品增信标识”选择“01 有”，该要素必填)
                	if(!StringUtil.isNotEmpty(dto.getRepCreditMode())) {
                		resultList.add(new ValidMessage("repCreditMode", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repCreditMode"))));
                	}
                }
                //产品增信标识”选择“01 有” 时 add by yanya 20190827 end 
                
                //是否为结构化（分级）产品”选择“是”时必填；是当“是否为结构化（分级）产品”选择“否”时，层级类别必须为空  add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepIsConstracte()) 
                		&& StringUtil.equals(dto.getRepIsConstracte(), DictCodeList.是否_中债_是.getValue())
                		&& StringUtil.isEmpty(dto.getRepGradeType())) {
                		resultList.add(new ValidMessage("repGradeType", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repGradeType"))));
                }
                if (StringUtil.isNotEmpty(dto.getRepIsConstracte()) 
                		&& StringUtil.equals(dto.getRepIsConstracte(), DictCodeList.是否_中债_否.getValue())
                		&& StringUtil.isNotEmpty(dto.getRepGradeType())) {
                		resultList.add(new ValidMessage("repGradeType", getMessage("层级类型只用于结构化产品必输")));
                }
                //是否为结构化（分级）产品”选择“是”时必填；是当“是否为结构化（分级）产品”选择“否”时，层级类别必须为空  add by yanya 20190827 end
                
                //开放模式:产品运作模式选择“开放式净值型”和“开放式非净值型” 的产品，该要素必填,产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品，此要素必须为空  add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepOperateMode()) 
                		&& (StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                				|| StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue()))
                		&& StringUtil.isEmpty(dto.getRepOpenMode())) {
                		resultList.add(new ValidMessage("repOpenMode", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repOpenMode"))));
                } 
                if (StringUtil.isNotEmpty(dto.getRepOperateMode()) 
                		&& (StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_封闭式净值型.getValue())
                				|| StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_封闭式非净值型.getValue()))
                		&& StringUtil.isNotEmpty(dto.getRepOpenMode())) {
                		resultList.add(new ValidMessage("repOpenMode", getMessage("MANUAL_VOUCHER_E_001",
                                getMessage("BussRepFinProductInfo.repOpenMode"))));
                }
                //开放模式:产品运作模式选择“开放式净值型”和“开放式非净值型” 的产品，该要素必填 ,产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品，此要素必须为空 add by yanya 20190827 end 
                
                //节假日是否开放:产品运作模式选择“开放式净值型”和“开放式非净值型” 的产品，该要素必填,产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品，此要素必须为空  add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepOperateMode()) 
                        && (StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                                || StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue()))
                        && StringUtil.isEmpty(dto.getRepHolidayOnFlag())) {
                        resultList.add(new ValidMessage("repHolidayOnFlag", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repHolidayOnFlag"))));
                } 
                if (StringUtil.isNotEmpty(dto.getRepOperateMode()) 
                        && (StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_封闭式净值型.getValue())
                                || StringUtil.equals(dto.getRepOperateMode(), DictCodeListExt2.产品运作模式_封闭式非净值型.getValue()))
                        && StringUtil.isNotEmpty(dto.getRepHolidayOnFlag())) {
                        resultList.add(new ValidMessage("repHolidayOnFlag", getMessage("MANUAL_VOUCHER_E_001",
                                getMessage("BussRepFinProductInfo.repHolidayOnFlag"))));
                }
                //节假日是否开放:产品运作模式选择“开放式净值型”和“开放式非净值型” 的产品，该要素必填 ,产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品，此要素必须为空 add by yanya 20190827 end 
                
                
                //规律开放周期:当“开放模式”选择“01 有规律开放”时 add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepOpenMode()) 
                		&& StringUtil.equals(dto.getRepOpenMode(), DictCodeListExt2.开放模式_中债_有规律开放.getValue())) {
                	//规律开放周期必须填写
                	if(StringUtil.isEmpty(dto.getRepOpenCircle())) {
                		resultList.add(new ValidMessage("repOpenCircle", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repOpenCircle"))));
                	}
                	//无规律开放说明必为空
                	if(StringUtil.isNotEmpty(dto.getRepRuleComment())) {
                		resultList.add(new ValidMessage("repRuleComment", getMessage("MANUAL_VOUCHER_E_001",
                                getMessage("BussRepFinProductInfo.repRuleComment"))));
                	}
                }
                //规律开放周期:当“开放模式”选择“01 有规律开放”时  add by yanya 20190827 end 
                
                //当“开放模式”选择“02 无规律开放”时 add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepOpenMode()) 
                		&& StringUtil.equals(dto.getRepOpenMode(), DictCodeListExt2.开放模式_中债_无规律开放.getValue())) {
                	//规律开放周期必须为空
                	if(StringUtil.isNotEmpty(dto.getRepOpenCircle())) {
                		resultList.add(new ValidMessage("repOpenCircle", getMessage("MANUAL_VOUCHER_E_001",
                                getMessage("BussRepFinProductInfo.repOpenCircle"))));
                	}
                	//无规律开放说明必填写
                	if(StringUtil.isEmpty(dto.getRepRuleComment())) {
                		resultList.add(new ValidMessage("repRuleComment", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repRuleComment"))));
                	}
                }
                //当“开放模式”选择“02 无规律开放”时 add by yanya 20190827 end
                
                //其他规律开放周期（天）：对“规律开放周期”选择“99 其他规律开放周期”的开放式产品必填；“规律开放周期”选择“其他规律开放周期”以外的要素时，此要素必须为空   add by yanya 20190827 start
                if (StringUtil.isNotEmpty(dto.getRepOpenCircle()) 
                		&& StringUtil.equals(dto.getRepOpenCircle(), DictCodeListExt2.规律开放周期_其他规律开放周期.getValue())
                		&& !StringUtil.isNotEmpty(dto.getRepOpenCircleDays())) {
                		resultList.add(new ValidMessage("repOpenCircleDays", getMessage("BUSS_COM_E_0038",
                                getMessage("BussRepFinProductInfo.repOpenCircleDays"))));
                	
                }
                if (!StringUtil.equals(dto.getRepOpenCircle(), DictCodeListExt2.规律开放周期_其他规律开放周期.getValue())
                		&& StringUtil.isNotEmpty(dto.getRepOpenCircleDays())) {
                		resultList.add(new ValidMessage("repOpenCircleDays", getMessage("MANUAL_VOUCHER_E_001",
                                getMessage("BussRepFinProductInfo.repOpenCircleDays"))));
                	
                }
                //其他规律开放周期（天）：对“规律开放周期”选择“99 其他规律开放周期”的开放式产品必填；“规律开放周期”选择“其他规律开放周期”以外的要素时，此要素必须为空   add by yanya 20190827 end
                
            }
        }
        // 是否收益权转让
        if (StringUtil.isEmpty(dto.getRepFiduciaryDuty())) {
            resultList.add(new ValidMessage("repFiduciaryDuty","请输入是否收益权转让"));
        }
        // 是否金融同业专属
        if (StringUtil.isEmpty(dto.getExcFinSector())) {
            resultList.add(new ValidMessage("excFinSector", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.excFinSector"))));
        }
        // 人行报送客户类型必输 
        if (StringUtil.isEmpty(dto.getCustomerType())) {
            resultList.add(new ValidMessage("customerType", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.customerType"))));
        }  
        if (dto.getRepRaisePstrdate() == null) {
            resultList.add(new ValidMessage("repRaisePstrdate", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.repRaisePstrdate"))));
        }  
        if (dto.getRepRaisePenddate()==null) {
            resultList.add(new ValidMessage("repRaisePenddate", getMessage("BUSS_COM_E_0038",
                    getMessage("BussRepFinProductInfo.repRaisePenddate"))));
        }  
        //add by wm 增加业绩比较基准与投资管理费非空校验
        //update by yzg 业绩基准改为选填
//        if (StringUtil.isEmpty(dto.getRepYield())) {
//            resultList.add(new ValidMessage("repYield", getMessage("BUSS_COM_E_0038",
//                    getMessage("ProductReportRegister001.repJizhun"))));
//        } 
        if (StringUtil.isEmpty(dto.getRepInvestFeeRate())) {
            resultList.add(new ValidMessage("repInvestFeeRate", getMessage("BUSS_COM_E_0038",
                    getMessage("ProductReportRegister001.repInvestFeeRate"))));
        }

        // 是否为结构化(分级)产品
        if(StringUtil.isEmpty(dto.getRepIsConstracte())){
            resultList.add(new ValidMessage("repIsConstracte","请输入是否为结构化(分级)产品"));
        }

        if (StringUtil.isNotEmpty(dto.getRepYieldComment())){
            Pattern p = Pattern.compile("[\uFF01]|[\uFF0C-\uFF0E]|[\uFF1A-\uFF1B]|[\uFF1F]|[\uFF08-\uFF09]|[\u3001-\u3002]|[\u3010-\u3011]|[\u201C-\u201D]|[\u2013-\u2014]|[\u2018-\u2019]|[\u2026]|[\u3008-\u300F]|[\u3014-\u3015]");
            Matcher m = p.matcher(String.valueOf(dto.getRepYieldComment()));
            if(m.find()){
                resultList.add(new ValidMessage("repYieldComment","业绩比较基准说明不得含有中文标点"));
            }
        }
        return resultList;
    }
}
