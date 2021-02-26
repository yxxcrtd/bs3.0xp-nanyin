/*
 * 文件名：FinFloatMgtExpQuotationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
//import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
//import com.joyintech.tams.constant.DictCodeListExt2;
//import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.service.FinFloatMgtExpQuotation001Service;

/**
 * 浮动管理费报价管理验证类
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月22日
 * @see FinFloatMgtExpQuotationValidator
 * @since 1.0
 */
@Component
public class FinFloatMgtExpQuotationValidator implements BeanValidator{
    /**
     * 浮动管理费报价管理service
     */
    @Autowired
    private FinFloatMgtExpQuotation001Service finFloatMgtExpQuotation001Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 判断交易日历共通
     */
//    @Autowired(required= false )
//    private DateCalService dateCalService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        FinFloatMgtExpQuotation001Dto finFloatMgtExpQuotation001Dto=(FinFloatMgtExpQuotation001Dto)value;
        //if (WorkFlowUtil.isNotFromToDoList()) {//编辑
        //行编辑按钮(是否可以修改校验)
        if(StringUtil.equals(finFloatMgtExpQuotation001Dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
            && StringUtil.equals(finFloatMgtExpQuotation001Dto.getOperateType(), "BEFORE_EDIT")){
            //修改记录.生效日 <= 平台日
            if(finFloatMgtExpQuotation001Dto.getEffDate().compareTo(
                    DateUtil.platDateFromCache())<0){
                errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0018",
                    getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(finFloatMgtExpQuotation001Dto.getEffDate()),
                    "平台日："+DateUtil.getSysDate())));
            }
            FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.queryProductByProdId(finFloatMgtExpQuotation001Dto.getFinprodId());
            if(!StringUtil.isNotEmpty(dto)){
            	errorList.add(new ValidMessage("","只能对存续未到期的净值型/货币型产品进行修改"));
            }
            //add by yujiaping 20210226 ZGBSS-643 浮动管理费调整报价初始化记录加限制不允许修改 start
            String isInitial = finFloatMgtExpQuotation001Service.getisInitial(finFloatMgtExpQuotation001Dto.getFinprodId(),DateUtil.format(finFloatMgtExpQuotation001Dto.getEffDate()));
            if(isInitial != null && StringUtil.equals(isInitial,DictCodeList.是否_是.getValue())) {
            	errorList.add(new ValidMessage("","初始化生成的产品浮动管理费信息不允许操作。"+
            			"产品代码："+finFloatMgtExpQuotation001Dto.getFinprodMarketId()));
            }
            //add by yujiaping 20210226 ZGBSS-643 浮动管理费调整报价初始化记录加限制不允许修改 end
        }
        //修改校验（保存）
        if(StringUtil.equals(finFloatMgtExpQuotation001Dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
            && !StringUtil.equals(finFloatMgtExpQuotation001Dto.getOperateType(), "BEFORE_EDIT")){
            //记录是否已存在(同一产品、同一生效日只能有一条记录)
            if(StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getFinprodId()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getCashId()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDateStr())
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDateOld())
                && finFloatMgtExpQuotation001Dto.getEffDate().compareTo(finFloatMgtExpQuotation001Dto.getEffDateOld())!=0){//生效日变动了，则需要重新校验是否存有记录
                FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.selectDetail(finFloatMgtExpQuotation001Dto.getFinprodId(),
                    finFloatMgtExpQuotation001Dto.getEffDateStr(),finFloatMgtExpQuotation001Dto.getCashId());
                if(dto != null){
                    errorList.add(new ValidMessage("", "生效日："+finFloatMgtExpQuotation001Dto.getEffDateStr()+"下的产品浮动管理费已存在"));
                    return errorList;
                }
            }
            //非空校验
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDate())){
                errorList.add(new ValidMessage("effDate", getMessage("BUSS_COM_E_0038",getMessage("FinProductQuotation002.effDate"))));
            }
            List<FinFloatMgtExpQuotation001Dto> selectedItem=finFloatMgtExpQuotation001Dto.getSelectedItem1();
            if(selectedItem.size()>0){
                for(int i=0 ; i<selectedItem.size() ; i++){
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getLev())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.lev"))));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getSpreadRate())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.spreadRate"))));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getCoefficient())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.coefficient"))));
                    } else if(selectedItem.get(i).getCoefficient().compareTo(BigDecimal.ZERO)<0){
                   	 	errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0004",getMessage("FinFloatMgtExpQuotation002.coefficient"),"0")));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getChargeRatio())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.chargeRatio"))));
                    } else if(selectedItem.get(i).getChargeRatio().compareTo(BigDecimal.ZERO)<0){
                    	errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0004",getMessage("FinFloatMgtExpQuotation002.chargeRatio"),"0")));
                    }
                }
            }
            else{
                errorList.add(new ValidMessage("", "超额业绩报酬不能为空！"));
            }
            String finprodId=finFloatMgtExpQuotation001Dto.getFinprodId();
            Date effDate=finFloatMgtExpQuotation001Dto.getEffDate();
            String effDateStr=finFloatMgtExpQuotation001Dto.getEffDateStr();
            if(StringUtil.isNotEmpty(finprodId) && StringUtil.isNotEmpty(effDateStr)){
                //画面.生效日 < 标的类金融产品.起息日 时，提交报错
                FinFloatMgtExpQuotation001Dto temp = finFloatMgtExpQuotation001Service.getVdateAndMdate(finprodId);
                if(DateUtil.compareDate(effDate,temp.getVdate()) < 0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                        getMessage("FinProductQuotation002.effDate")+DateUtil.format(effDate),
                        getMessage("FinProductQuotation002.vdate")+DateUtil.format(temp.getVdate()))));
                }
                //画面.生效日 > 标的类金融产品.到期日 时，提交报错
                if(DateUtil.compareDate(effDate,temp.getMdate()) >= 0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0045",
                        getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(effDate),
                        getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                }
                //画面.生效日 < 平台日 ,提交时报错
                if(effDate.compareTo(DateUtil.platDateFromCache())<0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                        getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(effDate),
                        "平台日："+DateUtil.getSysDate())));
                }
                //当生效日后发生了浮动管理费计提、支付交易，不予保存
                int count=finFloatMgtExpQuotation001Service.getFloatFeeCount(finprodId, effDateStr);
                if(count > 0){
                    errorList.add(new ValidMessage("", getMessage("FIN_PROD_W_0014")));
                }
                if(finFloatMgtExpQuotation001Dto.isCheckFlag() != false ){
                	//首次计提日check
                    Date fAccrDate=finFloatMgtExpQuotation001Dto.getfAccrDate();//起息日
                    if(StringUtil.isNotEmpty(fAccrDate)){
                        //首次计提日<产品.起息日,提交时报错
                        if(DateUtil.compareDate(fAccrDate,temp.getVdate()) < 0){
                            errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0015",
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate),
                                getMessage("FinFloatMgtExpQuotation001.vdate")+DateUtil.format(temp.getVdate()))));
                        }
                        //首次计提日>产品.到期日,提交时报错
                        if(DateUtil.compareDate(fAccrDate,temp.getMdate()) > 0){
                            errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0017",
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate),
                                getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                        }
                        //add by yujiaping 20210224 ZGBSS-617 封闭式净值产品设置浮动管理费，提示首次计提日必须为到期日或者到期日-1 start
                        if(StringUtil.equals(temp.getOperationType(), "01")){                        	
                        	if(DateUtil.compareDate(fAccrDate,temp.getMdate()) != 0 && DateUtil.compareDate(fAccrDate,DateUtil.addDay(temp.getMdate(),-1)) != 0){
                        	errorList.add(new ValidMessage("",getMessage("封闭式产品 浮动管理费  首次计提日 只能是产品到期日 或 产品到期日-1")));
                        }
                        }
                        //add by yujiaping 20210224 ZGBSS-617 封闭式净值产品设置浮动管理费，提示首次计提日必须为到期日或者到期日-1 end                        
                      //新建不联动首次计提日大于等于平台日
//                      if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getCashId())){
                      if(DateUtil.compareDate(fAccrDate,DateUtil.platDateFromCache()) < 0){
                          errorList.add(new ValidMessage("",getMessage(
                              getMessage("FinFloatMgtExpQuotation002.fAccrDate")+":"+DateUtil.format(fAccrDate)+
                              "必须大于等于平台日："+DateUtil.getSysDate())));
                      }
//                      }
                    }
                }
                
            }
        }//删除check
        else if(StringUtil.equals(finFloatMgtExpQuotation001Dto.getOperateType(), "delete")){
            if( StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getSelectedItem1()) ){
                for(FinFloatMgtExpQuotation001Dto dto:finFloatMgtExpQuotation001Dto.getSelectedItem1()){
                    Date effDate=dto.getEffDate();
                    if(StringUtil.isNotEmpty(effDate)){
                        if(effDate.compareTo(DateUtil.platDateFromCache())<0){
                            errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0015",
                                getMessage("FinFloatMgtExpQuotation001.effDate")+":"+DateUtil.format(effDate),
                                "平台日:"+DateUtil.getSysDate())));
                        }

                        WflValidInputParameter inputParameter = new WflValidInputParameter();
                        inputParameter.setBusinessDataPk1(dto.getFinprodId());
                        inputParameter.setBusinessDataPk2(DateUtil.format(effDate));
                        inputParameter.setPageId("FinFloatMgtExpQuotation002");
                        if (null != mstWorkflowCommonService && mstWorkflowCommonService.isInWflProcess(inputParameter)) {
                            errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getFinprodId())));
                        }

                    }
                }
            }
            else{
                Date effDate=finFloatMgtExpQuotation001Dto.getEffDate();
                if(StringUtil.isNotEmpty(effDate)){
                    if(finFloatMgtExpQuotation001Dto.getEffDate().compareTo(DateUtil.platDateFromCache())<0){
                        errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                            getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(effDate),
                            "平台日："+DateUtil.getSysDate())));
                    }
                }
            }
            
        }//新建check
        if(StringUtil.equals(finFloatMgtExpQuotation001Dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            //重复性check
            if(StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getFinprodId()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getCashId()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDateStr())){
                FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.selectDetail(finFloatMgtExpQuotation001Dto.getFinprodId(),
                    finFloatMgtExpQuotation001Dto.getEffDateStr(),finFloatMgtExpQuotation001Dto.getCashId());
                if(dto != null){
                    errorList.add(new ValidMessage("", "生效日："+finFloatMgtExpQuotation001Dto.getEffDateStr()+"下的产品浮动管理费已存在"));
                    return errorList;
                }
            }
            //重复性check
            if(StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getFinprodId()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getInvestmentCycle()) 
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDateStr())
                && StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getInvestmentCycleText())){
                FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.selectManaFeeDetail(finFloatMgtExpQuotation001Dto.getFinprodId(),
                    finFloatMgtExpQuotation001Dto.getEffDateStr(),finFloatMgtExpQuotation001Dto.getInvestmentCycleText());
                if(dto != null){
                    errorList.add(new ValidMessage("", "生效日："+finFloatMgtExpQuotation001Dto.getEffDateStr()+"下的产品浮动管理费已存在"));
                    return errorList;
                }
            }
            //非空校验
            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getpFinprodId()) && StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getFinprodId())){
                errorList.add(new ValidMessage("finprodId", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation001.finprodId"))));
            }
            //支持分层产品 ： 分层产品，但是款型为空，给出提示
            if(StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getpFinprodId()) && StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getProdStyle())
            		&&StringUtil.equals(DictCodeList.处理模式_分档.getValue(), finFloatMgtExpQuotation001Dto.getDealMode())){
            	errorList.add(new ValidMessage("prodStyle", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.prodStyle"))));
            }
            else if(StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getpFinprodId()) && StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getInvestmentCycle())
            		&&StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), finFloatMgtExpQuotation001Dto.getDealMode())){
            	errorList.add(new ValidMessage("prodStyle", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.investmentCycle"))));
            }
            
            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getFeeId())){
                errorList.add(new ValidMessage("feeName", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.feeName"))));
            }
            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getBasicBill())){
                errorList.add(new ValidMessage("basicBill", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.basicBill"))));
            }
            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getBasis())){
                errorList.add(new ValidMessage("basis", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.basic"))));
            }
//            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getAstVdateType())){
//                errorList.add(new ValidMessage("astVdateType", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.astVdateType"))));
//            }
            if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getBaseRule())){
                errorList.add(new ValidMessage("baseRule", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.baseRule"))));
            }
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getfAccrDate())){
                errorList.add(new ValidMessage("fAccrDate", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.fAccrDate"))));
            }
         // 一体化对应etliu start
//            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getRelaeseDate())){
//                errorList.add(new ValidMessage("relaeseDate", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation001.relaeseDate"))));
//            }
         // 一体化对应etliu end
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEffDate())){
                errorList.add(new ValidMessage("effDate", getMessage("BUSS_COM_E_0038",getMessage("FinProductQuotation002.effDate"))));
            }
            
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getFinprodAbbr())){
                errorList.add(new ValidMessage("finprodAbbr",getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation001.finprodAbbr"))));
            }
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.geteAccrDate())){
                errorList.add(new ValidMessage("eAccrDate", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.eAccrDate"))));
            }
            
            //计提方式：“每日”；“区间规则”“首个计提区间结束日”置灰
//            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getAccrType())){
//                errorList.add(new ValidMessage("accrType", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.accrType"))));
//            }else{
//            	if(StringUtil.equals(finFloatMgtExpQuotation001Dto.getAccrType(), DictCodeList.计提方式_区间倒减.getValue())){
//            		if(null == finFloatMgtExpQuotation001Dto.getIntervalRule()){
//            			errorList.add(new ValidMessage("intervalRule",
//            					getMessage("BUSS_COM_E_0008",getMessage("FinFloatMgtExpQuotation002.accrType"),DictCodeList.计提方式_区间倒减.getCodeName(), getMessage("FinFloatMgtExpQuotation002.intervalRule"))));
//            		}
//            		if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getfAccrEndDate())){
//                        errorList.add(new ValidMessage("fAccrEndDate", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.fAccrEndDate"))));
//                    }
//            		//首个计提区间结束日
//            		Date fAccrEndDate = finFloatMgtExpQuotation001Dto.getfAccrEndDate();
//            		//首次计提日
//            		Date fAccrDate = finFloatMgtExpQuotation001Dto.getfAccrDate();
//            		//计提结束日
//            		Date eAccrDate = finFloatMgtExpQuotation001Dto.geteAccrDate();
//            		//首次计提日<=首个计提区间结束日<=计提结束日
//            		if(StringUtil.isNotEmpty(fAccrEndDate)&&StringUtil.isNotEmpty(fAccrDate)&&StringUtil.isNotEmpty(eAccrDate)){
//            			if(DateUtil.compareDate(fAccrEndDate,fAccrDate) < 0){
//            				 errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
//                                     getMessage("FinFloatMgtExpQuotation002.fAccrEndDate")+DateUtil.format(fAccrEndDate),
//                                     getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate))));
//            			}
//            			if(DateUtil.compareDate(fAccrEndDate,eAccrDate) > 0){
//            				 errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0003",
//                                     getMessage("FinFloatMgtExpQuotation002.fAccrEndDate")+DateUtil.format(fAccrEndDate),
//                                     getMessage("FinFloatMgtExpQuotation002.eAccrDate")+DateUtil.format(eAccrDate))));
//            			}
//            		}
//            	}
//            }
            
//            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getAccrTdayRule())){
//                errorList.add(new ValidMessage("accrTdayRule", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.accrTdayRule"))));
//            }
            if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getPayCycle())){
                errorList.add(new ValidMessage("payCycle", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.payCycle"))));
            }
            //modify by wm 2019-11-12 付费规则为不规则时，无需校验这些字段必填 start
            if(!StringUtil.equals(finFloatMgtExpQuotation001Dto.getPayCycle(),DictCodeList.频率_不规则.getValue())){
            	if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getFirstPayDate())){
                    errorList.add(new ValidMessage("firstPayDate",getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.firstPayDate"))));
                }
                if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getEndTdayRule())){
                    errorList.add(new ValidMessage("endTdayRule", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.endTdayRule"))));
                }
                if(!StringUtil.isNotEmpty(finFloatMgtExpQuotation001Dto.getPayTdayRule())){
                    errorList.add(new ValidMessage("payTdayRule", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.payTdayRule"))));
                }
            }
            //modify by wm 2019-11-12 付费规则为不规则时，无需校验这些字段必填 end
            List<FinFloatMgtExpQuotation001Dto> selectedItem=finFloatMgtExpQuotation001Dto.getSelectedItem1();
            if(selectedItem.size()>0){
                for(int i=0 ; i<selectedItem.size() ; i++){
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getLev())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.lev"))));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getSpreadRate())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.spreadRate"))));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getCoefficient())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.coefficient"))));
                    } else if(selectedItem.get(i).getCoefficient().compareTo(BigDecimal.ZERO)<0){
                   	 	errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0004",getMessage("FinFloatMgtExpQuotation002.coefficient"),"0")));
                    }
                    if(!StringUtil.isNotEmpty(selectedItem.get(i).getChargeRatio())){
                        errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0038",getMessage("FinFloatMgtExpQuotation002.chargeRatio"))));
                    } else if(selectedItem.get(i).getChargeRatio().compareTo(BigDecimal.ZERO)<0){
                   	 	errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0004",getMessage("FinFloatMgtExpQuotation002.chargeRatio"),"0")));
                   }
                }
                /*for(int m=0; m<selectedItem.size(); m++){
                    for(int n=m+1; n<selectedItem.size(); n++){
                        if(StringUtil.isNotEmpty(selectedItem.get(n).getCoefficient())
                            && StringUtil.isNotEmpty(selectedItem.get(n).getSpreadRate())
                            && StringUtil.isNotEmpty(selectedItem.get(n).getChargeRatio())
                            && StringUtil.isNotEmpty(selectedItem.get(m).getCoefficient())
                            && StringUtil.isNotEmpty(selectedItem.get(m).getSpreadRate())
                            && StringUtil.isNotEmpty(selectedItem.get(m).getChargeRatio())
                        ){
                            if(selectedItem.get(n).getSpreadRate() == selectedItem.get(m).getSpreadRate()
                                && selectedItem.get(n).getCoefficient() == selectedItem.get(m).getCoefficient()
                                && selectedItem.get(n).getChargeRatio() == selectedItem.get(m).getChargeRatio()){
                                errorList.add(new ValidMessage("", "业绩基准表格数据不能完全相同！"));
                            } 
                        }
                    }
                }*/
            }
            else{
                errorList.add(new ValidMessage("", "超额业绩报酬不能为空！"));
            }
            String finprodId=finFloatMgtExpQuotation001Dto.getFinprodId();
            Date effDate=finFloatMgtExpQuotation001Dto.getEffDate();
            String effDateStr=finFloatMgtExpQuotation001Dto.getEffDateStr();
            if(StringUtil.isNotEmpty(finprodId) && StringUtil.isNotEmpty(effDateStr)){
                //画面.生效日 < 标的类金融产品.起息日 时，提交报错
                FinFloatMgtExpQuotation001Dto temp = finFloatMgtExpQuotation001Service.getVdateAndMdate(finprodId);
                if(DateUtil.compareDate(effDate,temp.getVdate()) < 0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                        getMessage("FinProductQuotation002.effDate")+DateUtil.format(effDate),
                        getMessage("FinProductQuotation002.vdate")+DateUtil.format(temp.getVdate()))));
                }
                //画面.生效日 > 标的类金融产品.到期日 时，提交报错
                if(DateUtil.compareDate(effDate,temp.getMdate()) >= 0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0045",
                        getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(effDate),
                        getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                }
                //画面.生效日 < 平台日 ,提交时报错
                if(effDate.compareTo(DateUtil.platDateFromCache())<0){
                    errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                        getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(effDate),
                        "平台日："+DateUtil.getSysDate())));
                }
                //当生效日后发生了浮动管理费计提、支付交易，不予保存
                int count=finFloatMgtExpQuotation001Service.getFloatFeeCount(finprodId, effDateStr);
                if(count > 0){
                    errorList.add(new ValidMessage("", getMessage("报价生效日后有浮动管理费报价。")));
                }
                //校验是否存在业绩基准代码
//                if(StringUtil.isNotEmpty(finprodId) && StringUtil.isNotEmpty(effDate)){
//                    Map<String,Object> param = new HashMap<String, Object>();
//                    param.put("finprodId", finprodId);
//                    param.put("effDate", effDateStr);
//                    if(finFloatMgtExpQuotation001Service.getIntRateById(param).size() == 0){
//                        errorList.add(new ValidMessage("", "设置浮动管理费时必须先设置业绩基准！"));
//                    }
//                }
                //首次计提日check
                Date fAccrDate=finFloatMgtExpQuotation001Dto.getfAccrDate();//起息日
                if(StringUtil.isNotEmpty(fAccrDate)){
                	if(finFloatMgtExpQuotation001Dto.isCheckFlag() != false ){
                		//首次计提日<产品.起息日,提交时报错
                        if(DateUtil.compareDate(fAccrDate,temp.getVdate()) < 0){
                            errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0015",
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate),
                                getMessage("FinFloatMgtExpQuotation001.vdate")+DateUtil.format(temp.getVdate()))));
                        }
                        //首次计提日>产品.到期日,提交时报错
                        if(DateUtil.compareDate(fAccrDate,temp.getMdate()) > 0){
                            errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0017",
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate),
                                getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                        }
                        //add by yujiaping 20210224 ZGBSS-617 封闭式净值产品设置浮动管理费，提示首次计提日必须为到期日或者到期日-1 start
                        if(StringUtil.equals(temp.getOperationType(), "01")){                        	
                        	if(DateUtil.compareDate(fAccrDate,temp.getMdate()) != 0 && DateUtil.compareDate(fAccrDate,DateUtil.addDay(temp.getMdate(),-1)) != 0){
                        	errorList.add(new ValidMessage("",getMessage("封闭式产品 浮动管理费  首次计提日 只能是产品到期日 或 产品到期日-1")));
                        }
                        }
                        //add by yujiaping 20210224 ZGBSS-617 封闭式净值产品设置浮动管理费，提示首次计提日必须为到期日或者到期日-1 end                        
                        //新建不联动首次计提日大于等于平台日
//                        if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getCashId())){
                        if(DateUtil.compareDate(fAccrDate,DateUtil.platDateFromCache()) < 0){
                            errorList.add(new ValidMessage("",getMessage(
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+":"+DateUtil.format(fAccrDate)+
                                "必须大于等于平台日："+DateUtil.getSysDate())));
                        }
//                        }
                        //首次计提日大于等于生效日的校验
                        if(DateUtil.compareDate(fAccrDate,finFloatMgtExpQuotation001Dto.getEffDate()) < 0){
                            errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0129",
                                    getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate),
                                    getMessage("FinFloatMgtExpQuotation002.effDate")+DateUtil.format(finFloatMgtExpQuotation001Dto.getEffDate()))));
                        }
                	}
                    //首次计提日为节假日报错
                    /*if(dateCalService.isDealCalendarHoliday(DateUtil.format(fAccrDate),temp.getCalendarId()) ){
                        errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0039",
                                getMessage("FinFloatMgtExpQuotation001.fAccrDate")+DateUtil.format(fAccrDate))) );
                    }*/
                    //新建不联动首次计提日大于等于平台日
                    /*if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getCashId())){
                        if(DateUtil.compareDate(fAccrDate,DateUtil.parse((String)SecurityUtil.getFromSession(GlobalConstant.SESSION_SYS_DATE))) < 0){
                            errorList.add(new ValidMessage("",getMessage(
                                getMessage("FinFloatMgtExpQuotation002.fAccrDate")+":"+DateUtil.format(fAccrDate)+
                                "必须大于等于平台日："+DateUtil.format(DateUtil.parse((String)SecurityUtil.getFromSession(GlobalConstant.SESSION_SYS_DATE))))));
                        }
                    }*/
                    
                }
                //计提结束日check
                Date eAccrDate=finFloatMgtExpQuotation001Dto.geteAccrDate();
                if(StringUtil.isNotEmpty(eAccrDate)){
                	//计提结束日<首个计提日,提交时报错
                    if(DateUtil.compareDate(eAccrDate,fAccrDate) < 0){
                        errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                            getMessage("FinFloatMgtExpQuotation002.eAccrDate")+DateUtil.format(eAccrDate),
                            getMessage("FinFloatMgtExpQuotation002.fAccrDate")+DateUtil.format(fAccrDate))));
                    }
                    //计提结束日>产品.到期日,提交时报错
                    if(DateUtil.compareDate(eAccrDate,temp.getMdate()) > 0){
                        errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0017",
                            getMessage("FinFloatMgtExpQuotation002.eAccrDate")+DateUtil.format(eAccrDate),
                            getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                    }
                }
                //首次付费日check
                Date firstPayDate = finFloatMgtExpQuotation001Dto.getFirstPayDate();
                if(StringUtil.isNotEmpty(firstPayDate)){
                  //lijun modify SHBS-521 20190301 start
                	//首次付费日 < 平台日 ,提交时报错
//                    if(firstPayDate.compareTo(DateUtil.platDateFromCache())<0){
//                        errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
//                            getMessage("FinFloatMgtExpQuotation002.firstPayDate")+DateUtil.format(firstPayDate),
//                            "平台日："+DateUtil.platDateFromCache())));
//                    }
                  //lijun modify SHBS-521 20190301 end
                    //首次付费日>产品.到期日,提交时报错
                    if(DateUtil.compareDate(firstPayDate,temp.getMdate()) > 0){
                        errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0017",
                            getMessage("FinFloatMgtExpQuotation002.firstPayDate")+DateUtil.format(firstPayDate),
                            getMessage("FinFloatMgtExpQuotation001.mdate")+DateUtil.format(temp.getMdate()))));
                    }
                    //首次付费日不能小于生效日                  
                    if(StringUtil.isEmpty(finFloatMgtExpQuotation001Dto.getCashId())){ //非首次报价，去掉生效日和首次付费日的判断
                    if(finFloatMgtExpQuotation001Dto.getEffDate()!=null&&
                    		DateUtil.compareDate(firstPayDate,finFloatMgtExpQuotation001Dto.getEffDate()) < 0){
                        errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0127",
                        		getMessage("FinFloatMgtExpQuotation002.firstPayDate")+DateUtil.format(firstPayDate),
                        		getMessage("FinFloatMgtExpQuotation002.effDate")+DateUtil.format(finFloatMgtExpQuotation001Dto.getEffDate())
                            )));
                    }
                }
                }
            }
            
        }
        //}    
        return errorList;
    }

}
