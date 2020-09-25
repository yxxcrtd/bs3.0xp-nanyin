 /* 
  * 文件名：FinProductBuild002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild001Service;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品新建管理验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月18日
 * @see FinProductBuild002Validator
 * @since 1.0
 */
@Component
public class FinProductBuild002Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductScheduling002Service finProductScheduling002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild002Service finProductBuild002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild001Service finProductBuild001Service;

    /**
     * {@linkplain}
     */

    /**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
//    /**
//     * {@linkplain}
//     */
//    @Autowired
//    private PrdSaleParamMapper prdSaleParamMapper;
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductBuild002Dto entity = (FinProductBuild002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.isNotEmpty(entity.getFinprodMarketId())) {
            if (entity.getFinprodMarketId().getBytes().length > 20) {
                validList.add(new ValidMessage("finprodMarketId", "产品代码过长（可能有汉字）"));
            }
        }
        if (!DictCodeList.是否_是.getValue().equals(entity.getIsSus()) && entity.getTermDays() == null) {
            validList.add(new ValidMessage("termDays", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductBuild002.termDays"))));
        }
        if (!StringUtil.isNotEmpty(entity.getIssueType())) {
            validList.add(new ValidMessage("issueType", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductBuild002.issueType"))));
        }
        
        if (!StringUtil.isNotEmpty(entity.getLiquidationDate())) {
            validList.add(new ValidMessage("liquidationDate", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductBuild002.liquidationDate"))));
        }
        //是否现金管理类产品 必填 开放式产品必输
        if (!StringUtil.isNotEmpty(entity.getIsCashManage()) && StringUtil.equals(entity.getOperationType(), DictCodeList.开放类型_开放式.getValue())) {
            validList.add(new ValidMessage("isCashManage", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductBuild002.isCashManage"))));
        }
        // 信息调整流程中的check
        if (StringUtil.isNotEmpty(entity.getFinprodId())){
            finProductBuild001Service.wflInFlowCheck(entity.getWorkflowId(), entity.getFinprodId(), validList);
        }
        
        if (StringUtil.isNotEmpty(entity.getFinprodMarketId())) {
            // 规则：代码(1~3位，第1位为大写字母，第2~3位为数字或大写字母)+销售渠道代码(1位大写字母)+品牌代码(1位大写字母)+年份(2位数字)+产品序号(4位数）
//            Pattern pattern = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{0,2}[A-Za-z]{2}[0-9]{6}$");
//            Matcher matcher = pattern.matcher(entity.getFinprodMarketId());
//            if (!matcher.matches()) {
//                validList.add(new ValidMessage("finprodMarketId", getMessage("BUSS_COM_E_0044",
//                    getMessage("FinProductBuild002.finprodMarketId"))));
//            }
            // 产品代码不能重复
            if (finProductScheduling002Service.checkProdInfoExist(entity.getFinprodMarketId(),	
                null, null, entity.getFinprodId()) > 0) {
                validList.add(new ValidMessage("finprodMarketId", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductBuild002.finprodMarketId"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            //pageIdList.add("FinProductScheduling002");//产品新建时若代码和排期重复，以新建为准，查询不生成待处理排期数据
            pageIdList.add("FinProductBuild002");
            pageIdList.add("FinProductBuild003");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk2(entity.getFinprodMarketId());
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                validList.add(new ValidMessage("finprodMarketId",
                    getMessage("BUSS_COM_I_0017", getMessage("FinProductScheduling002.prodId"))));
            }
        }
        // 产品名称不能重复
        if (StringUtil.isNotEmpty(entity.getFinprodName())) {
            if (finProductScheduling002Service.checkProdInfoExist(null, null,
                entity.getFinprodName(), entity.getFinprodId()) > 0) {
                validList.add(new ValidMessage("finprodName",
                    getMessage("BUSS_COM_I_0017", getMessage("FinProductBuild002.finprodName"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            pageIdList.add("FinProductScheduling002");
            pageIdList.add("FinProductBuild002");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk3(
                EncryptUtil.getInstance().md5(entity.getFinprodName()));
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                validList.add(new ValidMessage("finprodName", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodName"))));
            }
        }
        // 产品简称不能重复
        if (StringUtil.isNotEmpty(entity.getFinprodAbbr())) {
            if (finProductScheduling002Service.checkProdInfoExist(null, entity.getFinprodAbbr(),
                null, entity.getFinprodId()) > 0) {
                validList.add(new ValidMessage("finprodAbbr",
                    getMessage("BUSS_COM_I_0017", getMessage("FinProductBuild002.finprodAbbr"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            pageIdList.add("FinProductScheduling002");
            pageIdList.add("FinProductBuild002");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk4(
                EncryptUtil.getInstance().md5(entity.getFinprodAbbr()));
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                validList.add(new ValidMessage("finprodAbbr", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodAbbr"))));
            }
        }
        //delete by wm 去除此条限制 2019-11-21 start
        /*if ((StringUtil.isNotEmpty(entity.getFirstSaleVdate())
             || StringUtil.isNotEmpty(entity.getFirstSaleMdate()))
            && StringUtil.isNotEmpty(entity.getFinprodId())) {
            PrdSaleParam prdSaleParam = prdSaleParamMapper.selectByPrimaryKey(entity.getFinprodId());
            if (prdSaleParam != null) {
            	//update by wangcheng 201900408 SHBS-1800
//              if (DateUtil.compareDate(entity.getFirstSaleVdate(),
//                  prdSaleParam.getFirstSaleVdate()) < 0) {
//                  validList.add(new ValidMessage("firstSaleVdate", getMessage("FIN_PROD_E_0074",
//                      getMessage("FinProductBuild002.firstSaleVdate"))));
//              }
                if (DateUtil.compareDate(entity.getFirstSaleMdate(),
                    prdSaleParam.getFirstSaleMdate()) > 0) {
                    validList.add(new ValidMessage("firstSaleMdate", getMessage("FIN_PROD_E_0075",
                        getMessage("FinProductBuild002.firstSaleMdate"))));
                }
            }
        }*/
        //delete by wm 去除此条限制 2019-11-21 end
        // 画面.募集开始日>画面.募集结束日
        if (StringUtil.isNotEmpty(entity.getFirstSaleVdate())
            && StringUtil.isNotEmpty(entity.getFirstSaleMdate())) {
            if (DateUtil.compareDate(entity.getFirstSaleVdate(), entity.getFirstSaleMdate()) > 0) {
                validList.add(new ValidMessage("firstSaleVdate",
                    getMessage("BUSS_COM_E_0003", getMessage("FinProductBuild002.firstSaleVdate"),
                        getMessage("FinProductBuild002.firstSaleMdate"))));
            }
        }
        // 画面.募集结束日>画面.产品起息日
        if (StringUtil.isNotEmpty(entity.getFirstSaleMdate())
            && StringUtil.isNotEmpty(entity.getVdate())) {
            if (DateUtil.compareDate(entity.getFirstSaleMdate(), entity.getVdate()) > 0) {
                validList.add(new ValidMessage("firstSaleMdate",
                    getMessage("BUSS_COM_E_0003", getMessage("FinProductBuild002.firstSaleMdate"),
                        getMessage("FinProductBuild002.vdate"))));
            }
        }
        // 画面.产品起息日>=画面.产品到期日
        if (StringUtil.isNotEmpty(entity.getVdate()) && StringUtil.isNotEmpty(entity.getMdate())) {
            if (DateUtil.compareDate(entity.getVdate(), entity.getMdate()) >= 0) {
                validList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0045", getMessage("FinProductBuild002.vdate"),
                        getMessage("FinProductBuild002.mdate"))));
            }
        }

        // "当画面.组合代码输入时根据画面.组合代码查询组合成立日和到期日 画面.产品起息日<组合开始日时 画面.产品到期日>组合结束日时"
        if (StringUtil.isNotEmpty(entity.getPtlId())) {
            PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(entity.getPtlId());
            if (ptlPortfolio != null) {
                if (StringUtil.isNotEmpty(ptlPortfolio.getVdate())
                    && StringUtil.isNotEmpty(entity.getVdate())) {
                    if (DateUtil.compareDate(entity.getVdate(), ptlPortfolio.getVdate()) < 0) {
                        validList.add(new ValidMessage("vdate", this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductBuild002.vdate"),
                            this.getMessage("FinProductBuild002.portfolioVdate") + DateUtil.format(
                                ptlPortfolio.getVdate()))));
                    }
                }
              //update zhangweiqi SHBS-1457  begin
                /*
                if (StringUtil.isNotEmpty(ptlPortfolio.getMdate())
                    && StringUtil.isNotEmpty(entity.getMdate())) {
                    if (DateUtil.compareDate(entity.getMdate(), ptlPortfolio.getMdate()) > 0) {
                        validList.add(new ValidMessage("mdate", this.getMessage("BUSS_COM_E_0003",
                            this.getMessage("FinProductBuild002.mdate"),
                            this.getMessage("FinProductBuild002.portfolioMdate") + DateUtil.format(
                                ptlPortfolio.getMdate()))));
                    }
                }*/
              //update zhangweiqi SHBS-1457  end
            }
        }

        // 当画面.收益类型 = 数据字典缓存.收益类型.枚举值.预期收益， 画面.利率类型未填
        if (StringUtil.isNotEmpty(entity.getProfitType()) && StringUtil.equals(
            entity.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
            if (!StringUtil.isNotEmpty(entity.getCalcType())) {
                validList.add(new ValidMessage("calcType",
                    this.getMessage("BUSS_COM_E_0008",
                        this.getMessage("FinProductBuild002.profitType"),
                        this.getMessage("FinProductBuild002.msg.profitTypeE"),
                        this.getMessage("FinProductBuild002.calcType"))));
            }
        }
        // 当画面.是否分层 = 数据字典缓存.是否.枚举值.是， 画面.分层类型未填
//        if (StringUtil.isNotEmpty(entity.getIsLay())
//            && StringUtil.equals(entity.getIsLay(), DictCodeList.是否_是.getValue())) {
//            if (!StringUtil.isNotEmpty(entity.getLayType())) {
//                validList.add(new ValidMessage("layType",
//                    this.getMessage("BUSS_COM_E_0008", this.getMessage("FinProductBuild002.isLay"),
//                        DictCodeList.是否_是.getCodeName(),
//                        this.getMessage("FinProductBuild002.layType"))));
//            }
//        }
		//update zhangweiqi 开放式校验
        if (StringUtil.isNotEmpty(entity.getBussFinProductOpenPlanDto()) &&
        		!"02".equals(entity.getBussFinProductOpenPlanDto().getOpenMode())&&//add by zlp 20190314 高频开放不校验确认天数
        		StringUtil.isNotEmpty(entity.getOperationType()) && StringUtil.equals(
                entity.getOperationType(), DictCodeList.开放类型_开放式.getValue())) {
        	if (!StringUtil.isNotEmpty(entity.getPurSpeed())) {
                validList.add(new ValidMessage("purSpeed",
                    this.getMessage("BUSS_COM_E_0008", this.getMessage("FinProductBuild002.purSpeed"),
                        DictCodeList.是否_是.getCodeName(),
                        this.getMessage("FinProductBuild002.purSpeed"))));
            }
        }
        // 当画面.发行方式 = 数据字典缓存.发行方式.枚举值.单一 ， 画面.分行未填
        /*
        if (StringUtil.isNotEmpty(entity.getEntrustType()) && StringUtil.equals(
            entity.getEntrustType(), DictCodeList.排期发行方式_单一.getValue())) {
            if (!StringUtil.isNotEmpty(entity.getEntruster())) {
                validList.add(new ValidMessage("entruster",
                    this.getMessage("BUSS_COM_E_0008",
                        this.getMessage("FinProductBuild002.entrustType"),
                        DictCodeList.排期发行方式_单一.getCodeName(),
                        this.getMessage("FinProductBuild002.entruster"))));
            }
        }*/
        // 当画面.收益类型 = 数据字典缓存.收益类型.枚举值.收益型 且 画面.是否分层 = 数据字典缓存.是否.枚举值.否 时 ， 画面.预期收益率未填
        if (StringUtil.isNotEmpty(entity.getProfitType())
            && StringUtil.equals(entity.getProfitType(),
                DictCodeList.收益类型_收益型.getValue())
            && StringUtil.isNotEmpty(entity.getIsLay())
            && StringUtil.equals(entity.getIsLay(), DictCodeList.是否_否.getValue())) {
            if (!StringUtil.isNotEmpty(entity.getYield())) {
                validList.add(new ValidMessage("yield", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("FinProductBuild002.yield"))));
            }
        }
        // 当画面.收益类型 = 数据字典缓存.收益类型.枚举值.净值型 OR 画面.收益类型 = 数据字典缓存.收益类型.枚举值.货币型 且 画面.是否周期型 =
        // 数据字典缓存.是否.枚举值.是 时，封闭周期（天）未填
        /*
        if (StringUtil.isNotEmpty(entity.getProfitType())
            && (StringUtil.equals(entity.getProfitType(), DictCodeList.收益类型_净值型.getValue())
                || StringUtil.equals(entity.getProfitType(),
                    DictCodeList.收益类型_货币型.getValue()))) {
            if (StringUtil.isNotEmpty(entity.getIsCycle())
                && StringUtil.equals(entity.getIsCycle(), DictCodeList.是否_是.getValue())) {
                if (!StringUtil.isNotEmpty(entity.getCloseDays())) {
                    validList.add(
                        new ValidMessage("closeDays", this.getMessage("FIN_PROD_E_0051")));
                }
            }
        }
*/
        // 当处理模式为普通N天或者滚动N天时封闭期限必填
        if (StringUtil.isNotEmpty(entity.getDealMode())
            && (StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_周期型.getValue())
                || StringUtil.equals(entity.getDealMode(),
                    DictCodeList.处理模式_分档周期型.getValue()))) {
            if (!StringUtil.isNotEmpty(entity.getInvestmentCycle())) {
                validList.add(new ValidMessage("investmentCycle", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("FinProductBuild002.investmentCycle"))));
            }
        }

        // 付费计划业务组件 
        //delete by wm 根据业务需求删除费用必须输入 start 2019-07-17
//        if ((entity.getBussFinProductFeeInfoDto() != null && StringUtil.isEmptyList(
//            entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList()))
//            || !StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto())) {
//            validList.add(new ValidMessage("bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
//                this.getMessage("BUSS_COM_E_0038",
//                    this.getMessage("BussFinProductFeeInfo.feeInfoList"))));
//        }
//        else 
         //delete by wm 根据业务需求删除费用必须输入 end 2019-07-17
        if(entity.getBussFinProductFeeInfoDto() != null && !StringUtil.isEmptyList(
                entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList())){
            for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList()) {
                //不规则费用非必填
                if(StringUtil.equals(DictCodeList.收费类型_不规则费用.getValue(), bussFinProductFeeInfoDto.getChargeType())){
                    continue;
                }
                if (StringUtil.isNotEmpty(bussFinProductFeeInfoDto.getProvisionFirstDay())) {
                    if (StringUtil.isNotEmpty(entity.getVdate())
                        && DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(),
                            entity.getVdate()) < 0) {
                        validList.add(new ValidMessage(
                            "bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
                            this.getMessage("BUSS_COM_E_0004",
                                this.getMessage("BussFinProductFeeInfo.provisionFirstDay"),
                                this.getMessage("FinProductBuild002.vdate"))));
                    }
                    if (StringUtil.isNotEmpty(entity.getMdate())
                        && DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(),
                            entity.getMdate()) > 0) {
                        validList.add(new ValidMessage(
                            "bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
                            this.getMessage("FIN_PROD_E_0017",
                                this.getMessage("BussFinProductFeeInfo.provisionFirstDay"),
                                this.getMessage("FinProductBuild002.mdate"))));
                    }
                } else {
                    validList.add(new ValidMessage(
                        "bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
                        this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("BussFinProductFeeInfo.provisionFirstDay"))));
                }
                 //计提结束日
                if(!StringUtil.isNotEmpty(bussFinProductFeeInfoDto.geteAccrDate())){
                    //ZGBUG-175计提结束日未输入时提示信息建议加上产品费用模块 guyunfei  20200310 start
                    /*validList.add(new ValidMessage(
                        "bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
                        this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("BussFinProductFeeInfo.eAccrDate"))));*/
                    validList.add(new ValidMessage(
                            "bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList",
                            "请输入产品费用模块的计提结束日"));
                    //ZGBUG-175计提结束日未输入时提示信息建议加上产品费用模块 guyunfei  20200310 end
                } else {
                    if(DateUtil.compareDate(entity.getVdate(), bussFinProductFeeInfoDto.geteAccrDate()) >= 0){
                        //计提结束日应该大于产品起息日
                        validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0106")));
                    }
                    if(DateUtil.compareDate(entity.getLiquidationDate(), bussFinProductFeeInfoDto.geteAccrDate()) <= 0){
                        //计提结束日应该小于产品清盘日
                        validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0107")));
                    }
                    if(DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(), bussFinProductFeeInfoDto.geteAccrDate()) >= 0){
                        //计提结束日应该大于计提开始日（首次计提结束日）
                        validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0108")));
                    }
                }
                //首次计费结束日
                if(StringUtil.isNotEmpty(bussFinProductFeeInfoDto.getFirstCalEndDate())){
                    if(DateUtil.compareDate(entity.getVdate(), bussFinProductFeeInfoDto.getFirstCalEndDate()) >= 0){
                        //首次计费结束日应该大于产品起息日
                        validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0109")));
                    }
                    if(DateUtil.compareDate(entity.getLiquidationDate(), bussFinProductFeeInfoDto.getFirstCalEndDate()) < 0){
                        //首次计费结束日应该小于产品清盘日
                        validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0110")));
    
                    }
                }

                //丁俊峰  当计费营业日准则选择“向后调整”，付费营业日准则仅允许选择“向后调整”
                if(StringUtil.isNotEmpty(bussFinProductFeeInfoDto.getCalcFeeTradeRule()) && StringUtil.isNotEmpty(bussFinProductFeeInfoDto.getPayFeeTradeRule())){
                    if(DictCodeList.营业日准则_向后调整.getValue().equals(bussFinProductFeeInfoDto.getCalcFeeTradeRule())){
                        if(!DictCodeList.营业日准则_向后调整.getValue().equals(bussFinProductFeeInfoDto.getPayFeeTradeRule())){
                            validList.add(new ValidMessage(null,getMessage("calcFeeTradeRule_0001")));
                            break;
                        }
                        
                    }
                }
            }
        }

        // 浮动管理费业务组件
        if (entity.getBussFinProductFloatFeeDto() != null) {
        	if(StringUtil.isNotEmpty(entity.getBussFinProductFloatFeeDto().getFeeId())
                    && (StringUtil.equals(DictCodeList.处理模式_普通.getValue(), entity.getDealMode())||
                    StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), entity.getDealMode())) ){
        	//首次计提日
            if (StringUtil.isNotEmpty(
                entity.getBussFinProductFloatFeeDto().getProvisionFirstDay())) {
                if (StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                    entity.getBussFinProductFloatFeeDto().getProvisionFirstDay(),
                    entity.getVdate()) < 0) {
                    validList.add(new ValidMessage("bussFinProductFloatFeeDto.provisionFirstDay",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("BussFinProductFloatFee.provisionFirstDay"),
                            this.getMessage("FinProductBuild002.vdate"))));
                }
                if (StringUtil.isNotEmpty(entity.getMdate()) && DateUtil.compareDate(
                    entity.getBussFinProductFloatFeeDto().getProvisionFirstDay(),
                    entity.getMdate()) > 0) {
                    validList.add(new ValidMessage("bussFinProductFloatFeeDto.provisionFirstDay",
                        this.getMessage("FIN_PROD_E_0017",
                            this.getMessage("BussFinProductFloatFee.provisionFirstDay"),
                            this.getMessage("FinProductBuild002.mdate"))));
                }
            }
            
            //浮动管理费首次付费日验证 yzg 20191220
            if (StringUtil.isNotEmpty(
                    entity.getBussFinProductFloatFeeDto().getFirstPayDate())) {
                    if (StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                        entity.getBussFinProductFloatFeeDto().getFirstPayDate(),
                        entity.getVdate()) < 0) {
                        validList.add(new ValidMessage("bussFinProductFloatFeeDto.firstPayDate",
                            this.getMessage("BUSS_COM_E_0004",
                                this.getMessage("BussFinProductFloatFee.firstPayDate"),
                                this.getMessage("FinProductBuild002.vdate"))));
                    }
                    //首次付费日小于等于清盘日 yzg 20191220
                    if(StringUtil.isNotEmpty(entity.getLiquidationDate()) && DateUtil.compareDate(entity.getLiquidationDate(), entity.getBussFinProductFloatFeeDto().getFirstPayDate()) < 0){
                    	validList.add(new ValidMessage("bussFinProductFloatFeeDto.firstPayDate",
                                this.getMessage("FIN_PROD_E_0017",
                                    this.getMessage("BussFinProductFloatFee.firstPayDate"),
                                    this.getMessage("FinProductBuild002.liquidationDate"))));
                    }
                }
            
            //浮动管理费 计提结束日判断 yzg 20191220
            if (StringUtil.isNotEmpty(
                    entity.getBussFinProductFloatFeeDto().geteAccrDate())) {
                    if (StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                        entity.getBussFinProductFloatFeeDto().geteAccrDate(),
                        entity.getVdate()) < 0) {
                        validList.add(new ValidMessage("bussFinProductFloatFeeDto.eAccrDate",
                            this.getMessage("BUSS_COM_E_0004",
                                this.getMessage("BussFinProductFloatFee.eAccrDate"),
                                this.getMessage("FinProductBuild002.vdate"))));
                    }
                    if (StringUtil.isNotEmpty(entity.getMdate()) && DateUtil.compareDate(
                        entity.getBussFinProductFloatFeeDto().geteAccrDate(),
                        entity.getMdate()) > 0) {
                        validList.add(new ValidMessage("bussFinProductFloatFeeDto.eAccrDate",
                            this.getMessage("FIN_PROD_E_0017",
                                this.getMessage("BussFinProductFloatFee.eAccrDate"),
                                this.getMessage("FinProductBuild002.mdate"))));
                    }
                    //计提结束日要小于清盘日 yzg 
                    if(StringUtil.isNotEmpty(entity.getLiquidationDate()) && DateUtil.compareDate(entity.getLiquidationDate(), entity.getBussFinProductFloatFeeDto().geteAccrDate()) <= 0){
                        //计提结束日应该小于产品清盘日
                        validList.add(new ValidMessage("bussFinProductFloatFeeDto.eAccrDate", getMessage("FIN_PROD_E_0107")));
                    }
            	}
            	if(StringUtil.isNotEmpty(
                    entity.getBussFinProductFloatFeeDto().getProvisionFirstDay())
                    &&StringUtil.isNotEmpty(
                        entity.getBussFinProductFloatFeeDto().geteAccrDate())
                    &&DateUtil.compareDate(
                            entity.getBussFinProductFloatFeeDto().getProvisionFirstDay(),
                            entity.getBussFinProductFloatFeeDto().geteAccrDate()) > 0){
                    validList.add(new ValidMessage("bussFinProductFloatFeeDto.eAccrDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("BussFinProductFloatFee.eAccrDate"),
                            this.getMessage("BussFinProductFloatFee.provisionFirstDay"))));
                    
                }
             }
        }

        // 分红计划业务组件
        if (entity.getBussFinProductBonusPlanDto() != null) {
            if (StringUtil.isNotEmpty(entity.getBussFinProductBonusPlanDto().getFirstPayDate())) {
                if (StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                    entity.getBussFinProductBonusPlanDto().getFirstPayDate(),
                    entity.getVdate()) <= 0) {
                    validList.add(new ValidMessage("bussFinProductBonusPlanDto.firstPayDate",
                        this.getMessage("FIN_PROD_E_0015",
                            this.getMessage("BussFinProductBonusPlan.firstPayDate"),
                            this.getMessage("FinProductBuild002.vdate"))));
                }
                if (StringUtil.isNotEmpty(entity.getMdate()) && DateUtil.compareDate(
                    entity.getBussFinProductBonusPlanDto().getFirstPayDate(),
                    entity.getMdate()) > 0) {
                    validList.add(new ValidMessage("bussFinProductBonusPlanDto.firstPayDate",
                        this.getMessage("FIN_PROD_E_0017",
                            this.getMessage("BussFinProductBonusPlan.firstPayDate"),
                            this.getMessage("FinProductBuild002.mdate"))));
                }
            }
        }

        // 开放计划业务组件
        if (entity.getBussFinProductOpenPlanDto() != null) {
            if (StringUtil.isNotEmpty(entity.getBussFinProductOpenPlanDto().getFirstOpenDate())) {
                if (StringUtil.equals(DictCodeList.推算方法_开放确认日.getValue(), entity.getBussFinProductOpenPlanDto().getCalcRule()) 
                		&& StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                    entity.getBussFinProductOpenPlanDto().getFirstOpenDate(),
                    entity.getVdate()) <= 0) {
                    validList.add(new ValidMessage("bussFinProductOpenPlanDto.firstOpenDate",
                        this.getMessage("FIN_PROD_E_0015",
                            this.getMessage("FinProductOpenRule.firstOpenDate"),
                            this.getMessage("FinProductBuild002.vdate"))));
                }
                if (StringUtil.isNotEmpty(entity.getMdate()) && DateUtil.compareDate(
                    entity.getBussFinProductOpenPlanDto().getFirstOpenDate(),
                    entity.getMdate()) > 0) {
                    validList.add(new ValidMessage("bussFinProductOpenPlanDto.firstOpenDate",
                        this.getMessage("FIN_PROD_E_0017",
                            this.getMessage("FinProductOpenRule.firstOpenDate"),
                            this.getMessage("FinProductBuild002.mdate"))));
                }
            }
            List<FinOpenPlan> finOpenPlanList = entity.getBussFinProductOpenPlanDto().getFinOpenPlanList();
            if (!StringUtil.isEmptyList(finOpenPlanList)
                && StringUtil.isNotEmpty(entity.getVdate())&&StringUtil.equals(
                        entity.getOperationType(), DictCodeList.开放类型_开放式.getValue())) {
                for (FinOpenPlan finOpenPlan : finOpenPlanList) {
                    if (finOpenPlan.getOpenDateStr().compareTo(entity.getVdate()) < 0
                        || finOpenPlan.getOpenDateEnd().compareTo(entity.getVdate()) < 0) {
                        validList.add(new ValidMessage("bussFinProductOpenPlanDto.finOpenPlanList",
                            this.getMessage("FIN_PROD_E_0073")));
                        break;
                    }
                }
            }
          //当申赎不同频并且不是周期型产品时，对赎回计划进行校验
            if (!StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), entity.getDealMode())
            		&&!StringUtil.equals(entity.getBussFinProductOpenPlanRedDto().getOpenMode(), DictCodeList.开放模式_高频开放.getValue())
            		&&!StringUtil.equals(entity.getBussFinProductOpenPlanDto().getOpenType(),DictCodeList.申赎开放类型_申赎开放.getValue())
            		&&entity.getBussFinProductOpenPlanRedDto() != null) {
            	if(StringUtil.isEmptyList(entity.getBussFinProductOpenPlanRedDto().getFinOpenPlanList())){
            	    validList.add(new ValidMessage("bussFinProductOpenPlanRedDto.finOpenPlanList",
                            "申赎不同频时，请生成赎回计划"));
            	}
            	if (StringUtil.isNotEmpty(entity.getBussFinProductOpenPlanRedDto().getFirstOpenDate())) {
                    if (StringUtil.equals(DictCodeList.推算方法_开放确认日.getValue(), entity.getBussFinProductOpenPlanRedDto().getCalcRule()) 
                    		&& StringUtil.isNotEmpty(entity.getVdate()) && DateUtil.compareDate(
                        entity.getBussFinProductOpenPlanRedDto().getFirstOpenDate(),
                        entity.getVdate()) <= 0) {
                        validList.add(new ValidMessage("bussFinProductOpenPlanRedDto.firstOpenDate",
                            this.getMessage("FIN_PROD_E_0015",
                                this.getMessage("FinProductOpenRule.firstOpenDate"),
                                this.getMessage("FinProductBuild002.vdate"))));
                    }
                    if (StringUtil.isNotEmpty(entity.getMdate()) && DateUtil.compareDate(
                        entity.getBussFinProductOpenPlanRedDto().getFirstOpenDate(),
                        entity.getMdate()) > 0) {
                        validList.add(new ValidMessage("bussFinProductOpenPlanRedDto.firstOpenDate",
                            this.getMessage("FIN_PROD_E_0017",
                                this.getMessage("FinProductOpenRule.firstOpenDate"),
                                this.getMessage("FinProductBuild002.mdate"))));
                    }
                }
                List<FinOpenPlan> finOpenRedPlanList = entity.getBussFinProductOpenPlanRedDto().getFinOpenPlanList();
                if (!StringUtil.isEmptyList(finOpenRedPlanList)
                    && StringUtil.isNotEmpty(entity.getVdate())) {
                    for (FinOpenPlan finOpenPlan : finOpenRedPlanList) {
                        if (finOpenPlan.getOpenDateStr().compareTo(entity.getVdate()) < 0
                            || finOpenPlan.getOpenDateEnd().compareTo(entity.getVdate()) < 0) {
                            validList.add(new ValidMessage("bussFinProductOpenPlanRedDto.finOpenPlanList",
                                this.getMessage("FIN_PROD_E_0073")));
                            break;
                        }
                    }
                }
            }
        }

        if (StringUtil.equals(entity.getConfirmFlag(), DictCodeList.是否_是.getValue())) {
            // 根据排期金融产品代码查询排期信息
            FinProductBuild002Dto finProductSchedulingDto = finProductBuild002Service.queryProdScheduleByFinprodId(
                entity.getFinprodId());
            if (finProductSchedulingDto != null) {
                // 修改时需校验本次修改的募集起始日>上次募集起始日，本次修改的募集到期日<上次募集到期日；
                if (StringUtil.isNotEmpty(entity.getFirstSaleVdate())
                    && DateUtil.compareDate(entity.getFirstSaleVdate(),
                        finProductSchedulingDto.getFirstSaleVdate()) < 0) {
                    validList.add(
                        new ValidMessage("firstSaleVdate", getMessage("FIN_PROD_E_0074")));
                }
                if (StringUtil.isNotEmpty(entity.getFirstSaleMdate())
                    && DateUtil.compareDate(entity.getFirstSaleMdate(),
                        finProductSchedulingDto.getFirstSaleMdate()) > 0) {
                    validList.add(
                        new ValidMessage("firstSaleMdate", getMessage("FIN_PROD_E_0075")));
                }
                // 修改成立日时，需判断成立日>=平台日
                if (StringUtil.isNotEmpty(entity.getVdate())
                    && DateUtil.compareDate(entity.getVdate(), DateUtil.platDateFromCache()) < 0) {
                    validList.add(new ValidMessage("vdate",
                        getMessage("BUSS_COM_E_0004", this.getMessage("FinProductBuild002.vdate"),
                            this.getMessage("FinProductQuotation001.currentDate"))));
                }
            }
        }

        // 浮动管理费的费用名称如果不为空时需判断业绩基准是否填写
        if (entity.getBussFinProductFloatFeeDto() != null) {
            BussFinProductFloatFeeDto bussFinProductFloatFeeDto = entity.getBussFinProductFloatFeeDto();
            if (StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFeeId())) {
                BussBenchMarkInfoDto bussBenchMarkInfoDto = entity.getBussBenchMarkInfoDto();
                if ((bussBenchMarkInfoDto != null
                     && !StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType()))
                    || bussBenchMarkInfoDto == null) {
                    validList.add(new ValidMessage("bussBenchMarkInfoDto.rateType", getMessage(
                        "BUSS_COM_E_0038", getMessage("BussBenchMarkInfo")
                                           + getMessage("BussBenchMarkInfo.rateType"))));
                }
            }
        }
        
        // 预期收益型产品的分层
        if(StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档.getValue())) {
            if (!StringUtil.isEmptyList(entity.getBussFinProductLayeredInfoDto())) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < entity.getBussFinProductLayeredInfoDto().size(); i++) {
                    //产品款型 必填
                    if(!StringUtil.isNotEmpty(entity.getBussFinProductLayeredInfoDto().get(i).getProdStyle())){
                        validList.add(new ValidMessage("", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("bussFinProductLayeredInfoDto.finProductLayeredType"))));
                    } else {
                        BussFinProductLayeredInfoDto dto = (BussFinProductLayeredInfoDto) map.get(entity.getBussFinProductLayeredInfoDto().get(i).getProdStyle());
                        if(dto == null) {
                            map.put(entity.getBussFinProductLayeredInfoDto().get(i).getProdStyle(), entity.getBussFinProductLayeredInfoDto().get(i));
                        } else {
                            validList.add(new ValidMessage("", this.getMessage("FIN_PROD_E_0125",
                                    this.getMessage(entity.getBussFinProductLayeredInfoDto().get(i).getProdStyle()))));
                        }
                    }
                    //预期收益率（%）
                    if(!StringUtil.isNotEmpty(entity.getBussFinProductLayeredInfoDto().get(i).getYield())){
                        validList.add(new ValidMessage("", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("bussFinProductLayeredInfoDto.finProductLayeredield"))));
                    }
                    //销售渠道 必填
                    if(!StringUtil.isNotEmpty(entity.getBussFinProductLayeredInfoDto().get(i).getSaleChannel())){
                        validList.add(new ValidMessage("", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("bussFinProductLayeredInfoDto.finProductLayeredSaleChannel"))));
                    }
                    
                    //目标客户类型 必填
                    if(!StringUtil.isNotEmpty(entity.getBussFinProductLayeredInfoDto().get(i).getTarCustomerType())){
                        validList.add(new ValidMessage("", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("bussFinProductLayeredInfoDto.finProductLayeredRepInvestorType"))));
                    }
                    
                }   
            } else {
                validList.add(new ValidMessage("", this.getMessage("BUSS_COM_E_0038",
                    "产品分层信息")));
            }
        }
 
        
        //add by yejiejie 2019-2-14 start
        //净值化产品的募集要素中， 产品的募集币种在兑付本金及兑付收益币种的包含之中
        if(StringUtil.isNotEmpty(entity.getCcy())){

            //BSTOT-455 募集要素DIV里的募集币种、兑付本金币种、对付收益币种应该不能多选 guyunfei 20200111 start
            if(entity.getCcy().equals(entity.getBussRepFinProductInfoDto().getRepCashProfCcy())){
                if(StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepCashPrinCcy())
                        && StringUtil.equals(entity.getBussRepFinProductInfoDto().getRepCashPrinCcy(),
                        entity.getBussRepFinProductInfoDto().getRepCashProfCcy())){
                    if(entity.getCcy().equals(entity.getBussRepFinProductInfoDto().getRepCashPrinCcy())){

                    }else{
                        validList.add(new ValidMessage("ccy", getMessage("产品的募集币种、兑付本金及兑付收益币种必须相同")));
                    }
                }else{
                    validList.add(new ValidMessage("ccy", getMessage("产品的募集币种、兑付本金及兑付收益币种必须相同")));
                }
            }else{
                validList.add(new ValidMessage("ccy", getMessage("产品的募集币种、兑付本金及兑付收益币种必须相同")));
            }

        	/*//兑付本金币种
        	String repCashPrinCcyFlag = "0";
        	List<String> repCashPrinCcyList = StringUtil.splitToList(GlobalConstant.COMMA,
                    entity.getBussRepFinProductInfoDto().getRepCashPrinCcy()); 
        	for (int i = 0; i < repCashPrinCcyList.size(); i++) {
				if(StringUtil.equals(repCashPrinCcyList.get(i),entity.getCcy())){
					repCashPrinCcyFlag = "1";
				}
			}
        	if(!StringUtil.equals("1",repCashPrinCcyFlag)){
        		//兑付本金币种不包含募集币种
        		validList.add(new ValidMessage("ccy", getMessage("FIN_PROD_E_0093")));
        	}	
        	//兑付收益币种
        	String repCashProfCcyFlag = "0";
        	List<String> repCashProfCcyList = StringUtil.splitToList(GlobalConstant.COMMA,
                    entity.getBussRepFinProductInfoDto().getRepCashProfCcy()); 
        	for (int i = 0; i < repCashProfCcyList.size(); i++) {
				if(StringUtil.equals(repCashProfCcyList.get(i),entity.getCcy())){
					repCashProfCcyFlag = "1";
				}
			}
        	if(!StringUtil.equals("1",repCashProfCcyFlag)){
        		//兑付收益币种不包含募集币种
        		validList.add(new ValidMessage("ccy", getMessage("FIN_PROD_E_0094")));
        	}*/

            //BSTOT-455 募集要素DIV里的募集币种、兑付本金币种、对付收益币种应该不能多选 guyunfei 20200111 start
        }
        //add by yejiejie 2019-2-14 end
        //update zhangweiqi 资金账户不能为空，且至少有个托管户 begin
        //update yzg 资金账户可以为空 不为空校验不变 20191115 start
		if (!StringUtil.isEmptyList(entity.getBussFinProductFundAccountInfoDto())) {
			/*validList.add(new ValidMessage("bussFinProductFundAccountInfoDto",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("BussFinProductFundAccountInfo"))));
		} else {*/
			//update yzg 资金账户可以为空 不为空校验不变 20191115 end	
			boolean flag = false;
			for (int k = 0; k < entity.getBussFinProductFundAccountInfoDto().size(); k++) {
				if (StringUtil.equals(DictCodeListExt1.资金账户类型_产品托管户.getValue(),
						entity.getBussFinProductFundAccountInfoDto().get(k).getAcctType())) {
					flag = true;
				}
			}
			if (!flag) {
				validList.add(new ValidMessage("bussFinProductFundAccountInfoDto", this.getMessage("bussFinProductFundAccountInfoDto",this.getMessage("BussFinProductFundAccountInfo_A01"))));
			}
		}
		//update zhangweiqi 资金账户不能为空，且至少有个托管户 end
        //add by yejiejie 2019-2-18 start
        //资金账户中的费用名称不能重复
        if(StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto())){
        	List<String> fundAccountFeetypeList = new ArrayList<String>();
        	for(int k=0;k<entity.getBussFinProductFundAccountInfoDto().size();k++){
        		
        		List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,
                        entity.getBussFinProductFundAccountInfoDto().get(k).getFeeType());
        		for(String str:list){
        			if(StringUtil.isNotEmpty(str))
        		    fundAccountFeetypeList.add(str);
        		}
        	}
        	
        	if(fundAccountFeetypeList != null && fundAccountFeetypeList.size()!=0){
	        	for(int i=0;i<fundAccountFeetypeList.size();i++){
	        		for(int j=i+1;j<fundAccountFeetypeList.size();j++){
	        			if(StringUtil.equals(fundAccountFeetypeList.get(i),fundAccountFeetypeList.get(j))){
	        				//如果有费用相等，则给出提示信息
	        				//资金账户信息表中费用名称不能重复
	                		validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0100")));
	        			}
	        		}        		
	        	}
        	}
        }
        //资金账户信息表中同一个币种不能有相同的资金账户
        if(StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto())){
        	for(int i=0;i<entity.getBussFinProductFundAccountInfoDto().size();i++){
        	    //对资金账户类型_产品托管户、资金账户类型_结算备付金、资金账户类型_交易保证金 账号重复check lijun modify
        	    String acctType1 = entity.getBussFinProductFundAccountInfoDto().get(i).getAcctType();
        	    if(StringUtil.equals(DictCodeListExt1.资金账户类型_产品托管户.getValue(), acctType1) 
                    || StringUtil.equals(DictCodeListExt1.资金账户类型_结算备付金账户.getValue(), acctType1)
                    || StringUtil.equals(DictCodeListExt1.资金账户类型_交易保证金账户.getValue(), acctType1)){
            		for(int j=0;j<entity.getBussFinProductFundAccountInfoDto().size();j++){
            		  //去除自己本身比较
            		    if(i == j){
            		        continue;
            		    }
            		    String acctType2 = entity.getBussFinProductFundAccountInfoDto().get(j).getAcctType();
            			if(StringUtil.equals(acctType1, acctType2)
            					&&
        					StringUtil.equals(
                					entity.getBussFinProductFundAccountInfoDto().get(i).getCcy(),
                					entity.getBussFinProductFundAccountInfoDto().get(j).getCcy())){
            				//如果有同一个币种有相同的资金账户类型，则给出提示信息
            				//资金账户信息表中同一个币种不能有相同的资金账户类型
                    		validList.add(new ValidMessage("bussFinProductFundAccountInfoDto", getMessage("FIN_PROD_E_0101")));
            			}
            			if(StringUtil.equals(
            					entity.getBussFinProductFundAccountInfoDto().get(i).getAcctNo(),
            					entity.getBussFinProductFundAccountInfoDto().get(j).getAcctNo())
            					&&
        					StringUtil.equals(
                					entity.getBussFinProductFundAccountInfoDto().get(i).getCcy(),
                					entity.getBussFinProductFundAccountInfoDto().get(j).getCcy())){
            				//如果有同一个币种有相同的资金账户，则给出提示信息
            				//资金账户信息表中同一个币种不能有相同的资金账户
                    		validList.add(new ValidMessage("bussFinProductFundAccountInfoDto", getMessage("FIN_PROD_E_0111")));
            			}
            		}
            	}
        	}
        }
        /*
        //资金账户信息列表中的费用名称必须是产品费用里面或者是浮动管理费中存在的
        if(StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto())){
        	List<String> feeList = new ArrayList<String>();
        	for(int i=0;i<entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().size();i++){
        		feeList.add(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getFeeId());
        	}
        	feeList.add(entity.getBussFinProductFloatFeeDto().getFeeId());
        	List<String> fundAccountList = new ArrayList<String>();
        	for(int j=0;j<entity.getBussFinProductFundAccountInfoDto().size();j++){
        		fundAccountList.add(entity.getBussFinProductFundAccountInfoDto().get(j).getFeeType());
        	}
        	for(int a=0;a<fundAccountList.size();a++){
        		if(!feeList.contains(fundAccountList.get(a))){
        			//产品费用加上浮动管理费的费用名称不包括资金账户信息列表中的费用名称
        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0102")));
        		}
        	}
        }
        */
        //add by yejiejie 2019-2-18 end
        //资金账户信息表中资金账户或者资金账户类型不能为空
        if(StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto())){
        	for(int i=0;i<entity.getBussFinProductFundAccountInfoDto().size();i++){
        		if(!StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto().get(i).getAcctType())
        				|| !StringUtil.isNotEmpty(entity.getBussFinProductFundAccountInfoDto().get(i).getAcctNo())){
        			//资金账户信息表中资金账户或者资金账户类型不能为空
        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0103")));
        		}
        	}
        }
        //add by yejiejie 2019-2-22 end
        //add by yejiejie 2019-2-22 start
        //产品起息日≤计提开始日<=产品到期日
//        if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto())&&!StringUtil.isEmptyList(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList())){
//        	for(int i=0;i<entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().size();i++){
//        		if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getProvisionFirstDay())){
//	        		if(DateUtil.compareDate(entity.getVdate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getProvisionFirstDay()) > 0){
//	        			//计提开始日应该大于等于产品起息日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0104")));
//	        		}
//	        		if(DateUtil.compareDate(entity.getMdate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getProvisionFirstDay()) < 0){
//	        			//计提开始日应该小于等于产品到期日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0105")));
//	        		}
//        		}
//        	}
//        }
        //产品起息日<计提结束日<产品清盘日
//        if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto())&&!StringUtil.isEmptyList(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList())){
//        	for(int i=0;i<entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().size();i++){
//        		if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).geteAccrDate())){
//	        		if(DateUtil.compareDate(entity.getVdate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).geteAccrDate()) >= 0){
//	        			//计提结束日应该大于产品起息日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0106")));
//	        		}
//	        		if(DateUtil.compareDate(entity.getLiquidationDate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).geteAccrDate()) <= 0){
//	        			//计提结束日应该小于产品清盘日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0107")));
//	        		}
//	        		if(DateUtil.compareDate(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getProvisionFirstDay(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).geteAccrDate()) >= 0){
//	        			//计提结束日应该大于计提开始日（首次计提结束日）
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0108")));
//	        		}
//        		} 	
//        	}
//        }
        //产品起息日<首次计费结束日<产品清盘日
//        if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto())&&!StringUtil.isEmptyList(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList())){
//        	for(int i=0;i<entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().size();i++){
//        		if(StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getFirstCalEndDate())){
//	        		if(DateUtil.compareDate(entity.getVdate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getFirstCalEndDate()) >= 0){
//	        			//首次计费结束日应该大于等于产品起息日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0109")));
//	        		}
//	        		if(DateUtil.compareDate(entity.getLiquidationDate(), entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().get(i).getFirstCalEndDate()) <= 0){
//	        			//首次计费结束日应该小于等于产品清盘日
//	        			validList.add(new ValidMessage("", getMessage("FIN_PROD_E_0110")));
//	
//	        		}
//        		}
//        	}
//        }
        //add by yejiejie 2019-2-22 end
        //add by yejiejie 2019-3-11 start
        //产品的起息日，到期日不能是节假日
//        if (dateCalService.isDealCalendarHoliday(entity.getVdate(), entity.getCalendarId())) {
//        	validList.add(new ValidMessage("vdate", getMessage("FIN_PROD_VDATE_0001")));
//        }
//        if (dateCalService.isDealCalendarHoliday(entity.getMdate(), entity.getCalendarId())) {
//        	validList.add(new ValidMessage("mdate", getMessage("FIN_PROD_MDATE_0002")));
//        }
        //add by yejiejie 2019-3-11 end
        
        // add by dingjunfeng 2019-3-13
//        if (entity.getBussFinProductFeeInfoDto() != null) {
//            List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList = entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList();
//            if (!StringUtil.isEmptyList(bussFinProductFeeInfoDtoList)) {
//                for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : bussFinProductFeeInfoDtoList) {
//                    System.out.println(bussFinProductFeeInfoDto.getCalcFeeTradeRule());
//                    System.out.println(bussFinProductFeeInfoDto.getPayFeeTradeRule());
//                  //丁俊峰  当计费营业日准则选择“向后调整”，付费营业日准则仅允许选择“向后调整”
//                    if(bussFinProductFeeInfoDto.getCalcFeeTradeRule() != null && !"".equals(bussFinProductFeeInfoDto.getCalcFeeTradeRule())&&bussFinProductFeeInfoDto.getPayFeeTradeRule() != null && !"".equals(bussFinProductFeeInfoDto.getPayFeeTradeRule())){
//                    	if("02".equals(bussFinProductFeeInfoDto.getCalcFeeTradeRule())){
//                    		if(!"02".equals(bussFinProductFeeInfoDto.getPayFeeTradeRule())){
//                    			validList.add(new ValidMessage(null,getMessage("calcFeeTradeRule_0001")));
//                    			break;
//                    		}
//                    		
//                    	}
//                    }
//                }
//            }
//        }
     // 产品挂了费用时，没挂相应的资金账户给出提示信息
//        if (StringUtil.isNotEmpty(entity.getBussFinProductFeeInfoDto())) {
//        	boolean flag = false;
//        	BussFinProductFeeInfoDto dto = entity.getBussFinProductFeeInfoDto();
//        	List<BussFinProductFundAccountInfoDto> fundAccountList = entity.getBussFinProductFundAccountInfoDto();
//        	List<BussFinProductFeeInfoDto> feeInfoList = dto.getBussFinProductFeeInfoDtoList();
//        	for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : feeInfoList) {
//				if (StringUtil.equals("托管费", bussFinProductFeeInfoDto.getFeeType())) {
//					for (BussFinProductFundAccountInfoDto bussFinProductFundAccountInfoDto : fundAccountList) {
//						if (bussFinProductFundAccountInfoDto.getAcctType().equals("A03")) {
//							flag = true;
//							break;
//						}
//					}
//					if (!flag) {
//						validList.add(new ValidMessage(null,this.getMessage("请添加托管费账户")));
//					}
//				}else if (StringUtil.equals("销售手续费", bussFinProductFeeInfoDto.getFeeType())) {
//					flag = false;
//					for (BussFinProductFundAccountInfoDto bussFinProductFundAccountInfoDto : fundAccountList) {
//						if (bussFinProductFundAccountInfoDto.getAcctType().equals("A02")) {
//							flag = true;
//							break;
//						}
//					}
//					if (!flag) {
//						validList.add(new ValidMessage(null,this.getMessage("请添加销售费账户")));
//					}
//				}else if (StringUtil.equals("固定管理费", bussFinProductFeeInfoDto.getFeeType()) || StringUtil.equals("浮动管理费", bussFinProductFeeInfoDto.getFeeType())) {
//					flag = false;
//					for (BussFinProductFundAccountInfoDto bussFinProductFundAccountInfoDto : fundAccountList) {
//						if (bussFinProductFundAccountInfoDto.getAcctType().equals("A04")) {
//							flag = true;
//							break;
//						}
//					}
//					if (!flag) {
//						validList.add(new ValidMessage(null,this.getMessage("请添加管理费账户")));
//					}
//				}else if (StringUtil.equals("其他费用", bussFinProductFeeInfoDto.getFeeType())) {
//					flag = false;
//					for (BussFinProductFundAccountInfoDto bussFinProductFundAccountInfoDto : fundAccountList) {
//						if (bussFinProductFundAccountInfoDto.getAcctType().equals("A13")) {
//							flag = true;
//							break;
//						}
//					}
//					if (!flag) {
//						validList.add(new ValidMessage(null,this.getMessage("请添加其他费用账户")));
//					}
//				}
//			}
//        }
        
        if(entity.getBussFinProductSaleParamDto()!=null){
        	if(StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_普通.getValue()) || 
        			StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_周期型.getValue())){
        		//销售渠道必填
                if (!StringUtil.isNotEmpty(entity.getBussFinProductSaleParamDto().getSaleChannel())) {
                	validList.add(new ValidMessage("bussFinProductSaleParamDto.saleChannel", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("BussFinProductSaleParam.saleChannel"))));
                }
                
                //目标客户类型
                if (!StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepInvestorType())) {
                	validList.add(new ValidMessage("bussFinProductSaleParamDto.investorType", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("BussRepFinProductInfo.repInvestorType"))));
                }
        	}
        	
        }
        
        if (StringUtil.isNotEmpty(entity.getFirstSaleVdate())) {
    		if(DateUtil.compareDate(entity.getFirstSaleVdate(),entity.getBussRepFinProductInfoDto().getRepRaisePstrdate()) < 0
    				||DateUtil.compareDate(entity.getBussRepFinProductInfoDto().getRepRaisePenddate(),entity.getFirstSaleVdate())<0){
                validList.add(new ValidMessage("firstSaleVdate",this.getMessage("FIN_PROD_E_0226")));
    		}
        }    
        //到期日与清盘日判断  清盘日必须大于到期日 add by 20190828
        if(StringUtil.isNotEmpty(entity.getLiquidationDate())&&StringUtil.isNotEmpty(entity.getMdate())){
        	if(entity.getLiquidationDate().compareTo(entity.getMdate())<0){
        		validList.add(new ValidMessage("liquidationDate", this.getMessage("FIN_PROD_E_0127",
        				this.getMessage("FinProductBuild002.liquidationDate"),this.getMessage("FinProductBuild002.mdate"))));
        	}
        }
    	//产品经理不能为空校验
    	if(StringUtil.isEmpty(entity.getProdManager())){
			validList.add(new ValidMessage("prodManager", getMessage("BUSS_COM_E_0038",
                    getMessage("FinProductBuild002.prodManager"))));
		}
    	//行内标识码在产品申报要素配置为分开的时候必填
    	if(StringUtil.isEmpty(entity.getRepFinprodInsId())
    			&&StringUtil.equals(DictCodeList.是否_否.getValue(), CacheUtil.getSysParam(BussinessConstant.PRODUCTT_IS_MERGER))){
    		validList.add(new ValidMessage("repFinprodInsId", getMessage("BUSS_COM_E_0038",
                    getMessage("ProductReportRegister001.repFinprodInsId"))));
    	}
    	//产品设计系列如果展示不为空 yzg 20191211 start
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("FinProductBuild002");
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        if(isEleShow != null && StringUtil.isNotEmpty(isEleShow.get("FinProductBuild002.prdDesignId")) && StringUtil.equals(isEleShow.get("FinProductBuild002.prdDesignId"), "Y")) {
	    	if(StringUtil.isEmpty(entity.getPrdDesignId())) {
	    		validList.add(new ValidMessage("prdDesignId", getMessage("BUSS_COM_E_0038",
	                    getMessage("FinProductBuild002.prdDesignId"))));
	    	}
        }
        //产品设计系列如果展示不为空 yzg 20191211 end
    	//认购起点 addby cxq 20200109 解决 OPEX-763
        if(!StringUtil.isNotEmpty(entity.getBussFinProductSaleParamDto().getToffStart())) {
        	validList.add(new ValidMessage("bussFinProductSaleParamDto.toffStart", getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductSaleParam.toffStart"))));
        }
        //收益型产品运作模式不可选净值型 add by yzg 20200309
        if(StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto()) && StringUtil.isNotEmpty(entity.getBussRepFinProductInfoDto().getRepOperateMode())) {
        	if(StringUtil.equals(entity.getBussRepFinProductInfoDto().getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue()) || 
        			StringUtil.equals(entity.getBussRepFinProductInfoDto().getRepOperateMode(), DictCodeListExt2.产品运作模式_封闭式净值型.getValue())) {
        		validList.add(new ValidMessage("bussRepFinProductInfoDto.repOperateMode", getMessage("FinProductBuild002.repOperateMode.error")));
        	}
        }
        /**20200319 taoran add 同业机构报错后输入框描红*/
    	if (StringUtil.isNotEmpty(entity.getBussFinProductSaleParamDto().getInvestorType()) &&
    			entity.getBussFinProductSaleParamDto().getInvestorType().indexOf( DictCodeList.目标客户类型_金融同业专属.getValue())!=-1
         		&& !StringUtil.isNotEmpty(entity.getBussFinProductSaleParamDto().getSameOrg())) {
    		validList.add(new ValidMessage("sameOrg", this.getMessage("BUSS_COM_E_0038",
                 this.getMessage("BussFinProductSaleParam.sameOrg"))));
    	}
    	/**20200319 taoran add 同业机构报错后输入框描红*/
        return validList;
    }

}
