/*
 * 文件名：FinProductBmQuotationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.bmquotation.validate;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.service.FinProductBmQuotationService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationValidator
 * @since 1.0
 */
@Component
public class FinProductBmQuotationValidator implements BeanValidator {

    /**
     * finProductBmQuotationService
     */
    @Autowired
    private FinProductBmQuotationService finProductBmQuotationService;

    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductBmQuotationDto dto = (FinProductBmQuotationDto)value;
        dto.setBaseRuleType(DictCodeListExt1.业绩基准类型_产品披露.getValue());
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<FinProductBmQuotationDto> list = dto.getSelectedItem();
            if (list.size() > 0 && list != null) {
                for (FinProductBmQuotationDto mst : list) {
                    // 判断估值日期是否大于平台日期
                    if (mst.getEffDate().compareTo(DateUtil.platDateFromCache()) < 0) {
                        resultList.add(new ValidMessage(this.getMessage(
                            "BUSS_COM_E_0015", getMessage("FinProductBmQuotation001.effDate")
                                               + DateUtil.format(mst.getEffDate()),
                            "平台日" + DateUtil.getSysDate())));
                    }
                    mst.setBaseRuleType(DictCodeListExt1.业绩基准类型_产品披露.getValue());
                    List<FinProductBmQuotationDto> eList = finProductBmQuotationService.selectByFinprodIdAndEffDate(
                        mst);
                    if (eList == null || eList.size() == 0) {
                        resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
                    }
                }
            }
            else {
                resultList.add(new ValidMessage(this.getMessage("COMMON_E_0002")));
            }

        }
        else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                // 判断生效日期是否大于平台日期
                if (dto.getEffDate() != null) {
                    if (dto.getEffDate().compareTo(DateUtil.platDateFromCache()) < 0) {
                        resultList.add(new ValidMessage(this.getMessage(
                            "BUSS_COM_E_0018", getMessage("FinProductBmQuotation001.effDate")
                                               + DateUtil.format(dto.getEffDate()),
                            "平台日" + DateUtil.getSysDate())));
                    }
                }
                if ("new".equals(dto.getOpType())) {
                    // 判断是否为有效报价记录
                    List<FinProductBmQuotationDto> list = finProductBmQuotationService.selectByFinprodIdAndEffDate(
                        dto);
                    if (list != null && list.size() > 0) {
                        resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0009",
                            DateUtil.format(dto.getEffDate()), "有效报价记录")));
                    }
                }
            }
            else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                // 判断生效日期是否大于平台日期
                if (dto.getEffDate() != null) {
                    if (dto.getEffDate().compareTo(DateUtil.platDateFromCache()) < 0) {
                        resultList.add(new ValidMessage(this.getMessage(
                            "BUSS_COM_E_0004", getMessage("FinProductBmQuotation001.effDate")
                                               + DateUtil.format(dto.getEffDate()),
                            "平台日" + DateUtil.getSysDate())));
                    }
                }
                // 判断是否为有效报价记录
                List<FinProductBmQuotationDto> list = finProductBmQuotationService.selectByFinprodIdAndEffDate(
                    dto);
                if (list != null && list.size() > 0) {
                    resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0009",
                        DateUtil.format(dto.getEffDate()), "有效报价记录")));
                }
            }

            if (dto.getBussBenchMarkInfoDto() != null) {
//                // 当利率类型=浮动利率时
//                if (StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(),
//                    DictCodeList.利率类型_浮动利率.getValue())) {
//                    if (dto.getBussBenchMarkInfoDto().getIndexId() == null
//                        || StringUtil.equals(dto.getBussBenchMarkInfoDto().getIndexId(), "")) {
//                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
//                            getMessage("FinProductBmQuotation001.indexId"))));
//                    }
//                }
//                // 当利率类型=固定利率时
//                else if (StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(),
//                    DictCodeList.利率类型_固定利率.getValue())) {
//                    if (dto.getBussBenchMarkInfoDto().getRate() == null) {
//                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
//                            getMessage("FinProductBmQuotation001.rate"))));
//                    }
//                }
                // 当生效日后发生了浮动管理费计提、支付交易，保存时系统给出提醒不予保存 业绩基准不判断这个
//                int count = finProductBmQuotationService.getCount(dto);
//                if (count > 0) {
//                    resultList.add(new ValidMessage(this.getMessage("FIN_PROD_W_0014")));
//                }
                
                //当利率为固定利率时，校验业绩基准重置规则是否勾选
                if (StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(),DictCodeList.利率类型_固定利率.getValue())){
              //  	if (!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getResetType())) {
                //		resultList.add(new ValidMessage("bussBenchMarkInfoDto.resetType",
                  //          getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                    //}
                	//当利率为固定利率时，利率不能为空！ add by yanya 20190828
                	if (!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getRate())) {
                		resultList.add(new ValidMessage("bussBenchMarkInfoDto.rate",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                    }
                }
                else if (StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(),DictCodeList.利率类型_浮动利率.getValue())){
                	if (!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getIndexId())) {
                		resultList.add(new ValidMessage("bussBenchMarkInfoDto.indexId",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.indexId"))));
                    }
                	if (!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getSpreadRate())) {
                		resultList.add(new ValidMessage("bussBenchMarkInfoDto.spreadRate",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.spreadRate"))));
                    }
                }
                
                //add by wm 增加是否披露区间的校验 start 2019-09-25
                if(StringUtil.equals(dto.getBussBenchMarkInfoDto().getPublishType(),DictCodeListExt1.披露类型_区间披露.getValue())){
                	if(!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getRateLower())){
                		resultList.add(new ValidMessage("bussBenchMarkInfoDto.rateLower",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateLower"))));
                	}
                	if(!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getRateLimit())){
                		resultList.add(new ValidMessage("bussBenchMarkInfoDto.rateLimit",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateLimit"))));
                	}
                }
                else if(StringUtil.equals(dto.getBussBenchMarkInfoDto().getPublishType(),DictCodeListExt1.披露类型_单值披露.getValue())){
                	//delete by wm 去除单值披露时对业绩基准重置规则校验 start 2019-10-16
                     if (StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(),DictCodeList.利率类型_浮动利率.getValue()) && !StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getResetType())) {
                    	 resultList.add(new ValidMessage("bussBenchMarkInfoDto.resetType",
                    			 getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                      }
                	//delete by wm 去除单值披露时对业绩基准重置规则校验 end 2019-10-16
                     if (!StringUtil.isNotEmpty(dto.getBussBenchMarkInfoDto().getRateType())) {
                    	 resultList.add(new ValidMessage("bussBenchMarkInfoDto.rateType",
                    			 getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateType"))));
                      }
                }
            }
            //add by wm 增加是否披露区间的校验 end 2019-09-25
            // 判断生效日与标的类金融产品表.到期日,到期日,平台日的关系
            FinProductBmQuotationDto entity = finProductBmQuotationService.getMdate(
                dto.getFinprodId());
            if (dto.getEffDate() != null) {
                if (entity.getVdate() != null) {
                    if (dto.getEffDate().compareTo(entity.getVdate()) < 0) {
                        resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                            getMessage("FinProductBmQuotation001.effDate") + "(" + DateUtil.format(
                                dto.getEffDate()) + ")",
                            getMessage("FinProductBmQuotation001.vdate") + "(" + DateUtil.format(
                                entity.getVdate()) + ")")));
                    }
                }
                if (entity.getMdate() != null) {
                    if (dto.getEffDate().compareTo(entity.getMdate()) >= 0) {
                        resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0035",
                            getMessage("FinProductBmQuotation001.effDate") + "(" + DateUtil.format(
                                dto.getEffDate()) + ")",
                            getMessage("FinProductBmQuotation001.mdate") + "(" + DateUtil.format(
                                entity.getMdate()) + ")")));
                    }
                }

            }
            if (!WorkFlowUtil.isNotFromToDoList()) {
                if (dto != null && dto.getWorkflowId() != null) {
                    // 判断流程中是否存在相同的数据
                    WflValidInputParameter param = new WflValidInputParameter();
                    param.setWorkflowId(dto.getWorkflowId());
                    param.setBusinessDataPk1(dto.getFinprodId());
                    param.setBusinessDataPk2(dto.getEffDateStr());
                    param.setPageId("FinProductBmQuotation002");
                    if (null != mstWorkflowCommonService
                        && mstWorkflowCommonService.isInWflProcess(param)) {
                        resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0080",
                            dto.getFinprodId(), dto.getEffDateStr())));
                    }
                }

            }
        }
        return resultList;
    }

}
