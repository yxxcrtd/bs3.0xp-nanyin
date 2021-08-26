package com.joyintech.tams.invest.risk.creditmanagement.ceasset.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListRsk;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset001Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset002Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产授信验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see CeAssetValidator
 * @since 1.0
 */
@Component
public class CeAssetValidator implements BeanValidator{
    @Autowired
    CeAsset001Service ceAsset001Service;
    
    @Autowired
    CeAsset002Service ceAsset002Service;
    
    @Autowired
   	CeOrg002Service ceOrg002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
    	CreditLinesAstDto dto = (CreditLinesAstDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getOperateType(), "edit")){

        }else if (StringUtil.equals(dto.getOperateType(), "delete")){

        }else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if(!StringUtil.isNotEmpty(dto.getCustomerId())){
                    resultList.add(new ValidMessage("customerId",
                            getMessage("BUSS_COM_E_0038", getMessage("CeAsset002.assetName"))));
                }

                if(StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
                    if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())==1){
                        resultList.add(new ValidMessage("",
                                getMessage("BUSS_COM_E_0003", getMessage("CeAsset002.vdate"),getMessage("CeAsset002.mdate"))));
                    }
                }
                // 校验批复号和机构代码是否重复（批复号 + 机构 唯一）
                if (StringUtil.isNotEmpty(dto.getPflsh())) {
                    // delete by yushilong 批复号可重复 20200629 start
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("customerId", dto.getCustomerId());
                    map.put("pflsh", dto.getPflsh());
                    map.put("seqno", dto.getSeqno());
                    map.put("creditType", DictCodeListRsk.授信占用方式_资产授信.getValue());
                    map.put("creditMstId", BussinessDictCodeList.授信配置代码_资产授信.getValue());
                    int count= ceOrg002Service.searchRepeat(map);
                    if(count>0){
                        resultList.add(new ValidMessage("pflsh", getMessage("BUSS_COM_E_0006",getMessage("CeAsset002.pflsh")+ "、",getMessage("CeAsset002.assetName"))));
                    }
                    // delete by yushilong 批复号可重复 20200629 end
                    //判断流程中是否存在相同的数据
                    WflValidInputParameter param = new WflValidInputParameter();
                    if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                        param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
                    }
                    param.setBusinessDataPk2(dto.getPflsh());
                    param.setBusinessDataPk3(dto.getCustomerId());
                    param.setPageId("CeAsset002");
                    if (null != mstWorkflowCommonService &&
                            mstWorkflowCommonService.isInWflProcess(param)) {
                        resultList.add(
                                new ValidMessage(this.getMessage("COMMON_E_0012", dto.getPflsh()+ "、" + dto.getCustomerId())));
                    }
                }
                //modify by gaopeng 20191018 start错误提示信息修改
                if(StringUtil.isNotEmpty(dto.getCustomerId())){
                    List<CreditLinesAstDto> creditList=ceAsset002Service.searchSameAssetGroup(dto.getCustomerId());
                    if(creditList.size()>0){
                        resultList.add(new ValidMessage("customerId", getMessage("RSK_C_0049",creditList.get(0).getCustomerName())));
                    }
                }

                // 资产授信新建新增字段校验 add by yufei 20200630 start
                // 【授信总份额（亿份）】、【授信总额度（亿）】二填一
                // 判断当前应输入额度为总额度或者总份额
                if (StringUtil.isNotEmpty(dto.getCreditObj())) {
                    if (dto.getCreditObj().equals(BussinessDictCodeList.授信对象_份额.getValue())) {
                        if (dto.getCreditShareAmt() == null
                                || dto.getCreditShareAmt().compareTo(new BigDecimal(0)) == 0) {
                            resultList.add(new ValidMessage("creditShareAmt",
                                    getMessage("BUSS_COM_E_0038", getMessage("CeAsset002.creditShareAmt"))));
                        }
                    }
                    else {
                        if (dto.getCreditAmt() == null
                                || dto.getCreditAmt().compareTo(new BigDecimal(0)) == 0) {
                            resultList.add(new ValidMessage("creditAmt",
                                    getMessage("BUSS_COM_E_0038", getMessage("CeAsset002.creditTotalAmt"))));
                        }
                    }
                }
                // 审批日期区间校验
                if(StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getApprVdate())){
                    // 审批开始日≥生效日
                    if(DateUtil.compareDate(dto.getVdate(), dto.getApprVdate()) == 1){
                        resultList.add(new ValidMessage("apprVdate",
                                getMessage("BUSS_COM_E_0004", getMessage("CeAsset002.approveVdate"),getMessage("CeAsset002.vdate"))));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getMdate()) && StringUtil.isNotEmpty(dto.getApprMdate())){
                    // 审批结束日≤到期日
                    if(DateUtil.compareDate(dto.getMdate(), dto.getApprMdate()) == -1){
                        resultList.add(new ValidMessage("apprMdate",
                                getMessage("BUSS_COM_E_0003", getMessage("CeAsset002.approveMdate"),getMessage("CeAsset002.mdate"))));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getApprVdate()) && StringUtil.isNotEmpty(dto.getApprMdate())){
                    // 审批开始日<=审批结束日
                    if(DateUtil.compareDate(dto.getApprVdate(), dto.getApprMdate()) == 1){
                        resultList.add(new ValidMessage("apprMdate",
                                getMessage("BUSS_COM_E_0004", getMessage("CeAsset002.approveMdate"),getMessage("CeAsset002.approveVdate"))));
                    }
                }
//                if(StringUtil.isNotEmpty(dto.getCustomerId()) && needCheckFirstLendingInterval(dto.getCustomerId())){
//                    // 首次开贷区间校验
//                    if(!StringUtil.isNotEmpty(dto.getCreditVdate())){
//                        resultList.add(new ValidMessage("firstLendingInterval",
//                                getMessage("BUSS_COM_E_0038", getMessage("CeAsset002.firstLendingInterval"))));
//                    }
//                    if(StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getCreditVdate())){
//                        // 区间开始日≥生效日
//                        if(DateUtil.compareDate(dto.getVdate(), dto.getCreditVdate()) == 1){
//                            resultList.add(new ValidMessage("firstLendingInterval",
//                                    getMessage("BUSS_COM_E_0004", getMessage("CeAsset002.firstLendingIntervalVdate"),getMessage("CeAsset002.vdate"))));
//                        }
//                    }
//                    if(StringUtil.isNotEmpty(dto.getMdate()) && StringUtil.isNotEmpty(dto.getCreditMdate())){
//                        // 区间结束日≤到期日
//                        if(DateUtil.compareDate(dto.getMdate(), dto.getCreditMdate()) == -1){
//                            resultList.add(new ValidMessage("firstLendingInterval",
//                                    getMessage("BUSS_COM_E_0003", getMessage("CeAsset002.firstLendingIntervalMdate"),getMessage("CeAsset002.mdate"))));
//                        }
//                    }
//                }

            }
        }

        // 修改时验证
        if(WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getOperateType(), "edit")) {
            if (!StringUtil.isNotEmpty(ceAsset002Service.getBussinessEntityByIndId(dto.getSeqno()))) {
            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
            }
            return resultList;
        }
        // 删除时校验
        if(StringUtil.equals(dto.getOperateType(), "delete")) {
            if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
                dto.getSelectedItem().forEach(item -> {
                    if (ceAsset002Service.checkBusinessDataInFlow(item)) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getPflsh())));
                    }
                    if (!StringUtil.isNotEmpty(ceAsset002Service.getBussinessEntityByIndId(item.getSeqno()) )) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                    }
                });
                return resultList;
            }

        }
        return resultList;
    }

    /**
     * 判断是否需要校验首次开贷区间 false 不需要；true 需要
     * @param customerId 资产代码
     * @return boolean 
     * @see
     * @since 1.0
     */
    private boolean needCheckFirstLendingInterval(String customerId) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("customerId", customerId);
        map.put("page",1);
        map.put("length",20);
        Page<SelectOptionDto> selectOptionDtoPage = ceAsset001Service.getFinprodMarketIds(map);
        List<SelectOptionDto> finprodMarketIds = selectOptionDtoPage.getRoot();
        if(!StringUtil.isEmptyList(finprodMarketIds)){
            Map<String, Object> mapObj = JsonUtils.object2Map(finprodMarketIds.get(0).getObject());
            if(mapObj.containsKey("finprodType2") && mapObj.containsKey("type9")){
                String finprodType2 = (String)mapObj.get("finprodType2");
                /*非标债权*/
                if(StringUtil.equals(BussinessDictCodeList.金融产品类型_投管用_项目类资产.getValue(),finprodType2)){
                    return true;
                }
                // 权益类资产.私募股权基金
                if (StringUtil.equals(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue(),finprodType2)){
                    if(mapObj.containsKey("type9")){
                        String type9 = (String)mapObj.get("type9");
                        if(StringUtil.equals(BussinessDictCodeList.行内资产三级分类_私募股权基金.getValue(),type9)){
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    
}
