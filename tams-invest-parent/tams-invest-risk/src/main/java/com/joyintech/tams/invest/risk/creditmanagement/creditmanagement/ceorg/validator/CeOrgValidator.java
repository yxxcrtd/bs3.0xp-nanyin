package com.joyintech.tams.invest.risk.creditmanagement.ceorg.validator;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 机构授信验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see CeOrgValidator
 * @since 1.0
 */
@Component
public class CeOrgValidator implements BeanValidator{
    @Autowired
	CeOrg002Service ceOrg002Service;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
    	CreditLinesOrgDto dto = (CreditLinesOrgDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if ("edit".equals(dto.getOperateType())) {

        } else if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if (CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
                dto.getSelectedItem().forEach(item -> {
                    if (ceOrg002Service.checkBusinessDataInFlow(item)) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getPflsh())));
                    }
                    if (!StringUtil.isNotEmpty(ceOrg002Service.getBussinessEntityByIndId(item.getSeqno()))) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                    }
                    if (StringUtil.isNotEmpty(item.getHapCreditAmt()) && item.getHapCreditAmt().compareTo(BigDecimal.ZERO) > 0) {
                        resultList.add(
                                new ValidMessage(this.getMessage("RSK_W_0049", getMessage("CeOrg001"))));
                    }
                });

                return resultList;
            }
        	/*if(StringUtil.isNotEmpty(dto.getHapCreditAmt())&&dto.getHapCreditAmt().compareTo(BigDecimal.ZERO)>0){
        		 resultList.add(
 	                    new ValidMessage(this.getMessage("RSK_W_0040",getMessage("CeOrg001"))));
        	}*/
        } else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if (StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())) {
                    if (DateUtil.compareDate(dto.getVdate(), dto.getMdate()) == 1) {
                        resultList.add(new ValidMessage("",
                                getMessage("BUSS_COM_E_0003", getMessage("CeOrg002.vdate"), getMessage("CeOrg002.mdate"))));
                    }
                }
                if (StringUtil.isNotEmpty(dto.getCustomerId()) && StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())) {
//                    int vcount = ceOrg002Service.searchRepeatByVmDate(dto.getSeqno(), dto.getCustomerId(), dto.getVdate(), dto.getCreditMstId());
//                    if (vcount > 0) {
//                        resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0076", getMessage("CeOrg002.vdate"))));
//                    }

                    //去除以上原有逻辑  ；当前业务逻辑： 修改时如果当前数据额度已被占用则不允许操作 20191024
                    boolean ispass = ceOrg002Service.searchIsUse(dto.getSeqno(), dto.getCustomerId(), dto.getVdate(), dto.getMdate(), dto.getPflsh());
                    if (ispass) {
                        resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0049", getMessage("CeOrg002.vdate"))));
                    }

                }
                // 校验批复号和机构代码是否重复（批复号 + 机构 唯一）
                if (StringUtil.isNotEmpty(dto.getPflsh())) {
                    // delete by yushilong 批复号可重复 20200629 start
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("customerId", dto.getCustomerId());
                    map.put("pflsh", dto.getPflsh());
                    map.put("seqno", dto.getSeqno());
                    map.put("creditType", DictCodeListRsk.授信占用方式_机构授信.getValue());
                    map.put("creditMstId", dto.getCreditMstId());
                    int count= ceOrg002Service.searchRepeat(map);
                    if(count>0){
                        resultList.add(new ValidMessage("pflsh", getMessage("BUSS_COM_E_0006",getMessage("CeOrg002.pflsh")+ "、",getMessage("CeOrg002.institutionName"))));
                    }
                    // delete by yushilong 批复号可重复 20200629 end
                    //判断流程中是否存在相同的数据
                    WflValidInputParameter param = new WflValidInputParameter();
                    if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                        param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
                    }
                    param.setBusinessDataPk2(dto.getPflsh());
                    param.setBusinessDataPk3(dto.getCustomerId());
                    param.setPageId("CeOrg002");
                    if (null != mstWorkflowCommonService &&
                            mstWorkflowCommonService.isInWflProcess(param)) {
                        resultList.add(
                                new ValidMessage(this.getMessage("COMMON_E_0012", dto.getPflsh())));
                    }
                }

                if (CollectionUtils.isNotEmpty(dto.getCreditLabelItem())) {
                    // 分项额度之和 不能大于机构总额度
                    Map<String, BigDecimal> amtMap = new HashMap<>();
                    amtMap.put("amount", BigDecimal.ZERO);
                    dto.getCreditLabelItem().forEach(item -> {
                        BigDecimal bm = item.getCreditAmt();
                        amtMap.put("amount", amtMap.get("amount").add(bm));
                    });
                    if (amtMap.get("amount") != null && amtMap.get("amount").compareTo(dto.getCreditAmt()) > 0) {
                        resultList.add(
                                new ValidMessage(this.getMessage("RSK_W_0072", getMessage("CeOrg002.creditAmt"))));
                    }
                    // add by yushilong 分项重复校验 20200629 start
                    HashSet<String> creditConfInfoSet = new HashSet<>();
                    for (CreditConfInfoDto creditConfInfoDto : dto.getCreditLabelItem()) {
                        if (!creditConfInfoSet.add(creditConfInfoDto.getCreditTagId())) {
                            resultList.add(new ValidMessage(this.getMessage("COMMON_E_0011", creditConfInfoDto.getCreditTagId())));
                        }
                    }
                    // add by yushilong 查询是否重复校验 20200629 end
                }
                if (StringUtil.isNotEmpty(dto.getSingerSecRatio())) {
                    if (dto.getSingerSecRatio().compareTo(BigDecimal.ZERO) < 0 || dto.getSingerSecRatio().compareTo(new BigDecimal(100)) > 0) {
                        resultList.add(new ValidMessage(this.getMessage("RSK_W_0074", getMessage("CeOrg002.singerSecRatio"))));
                    }
                }
            }
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if (CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
                    dto.getSelectedItem().forEach(item -> {
                        if (ceOrg002Service.checkBusinessDataInFlow(item)) {
                            resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getPflsh())));
                        }
                        if (!StringUtil.isNotEmpty(ceOrg002Service.getBussinessEntityByIndId(item.getSeqno()))) {
                            resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                        }
                        if (StringUtil.isNotEmpty(item.getHapCreditAmt()) && item.getHapCreditAmt().compareTo(BigDecimal.ZERO) > 0) {
                            resultList.add(
                                    new ValidMessage(this.getMessage("RSK_W_0049", getMessage("CeOrg001"))));
                        }
                    });

                    return resultList;
                }
            }

        }
        return resultList;
    }
}
