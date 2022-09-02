package com.joyintech.tams.disclosure.information.config.repRelDeal.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.service.RepRelDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepRelDealValidator implements BeanValidator {

    @Autowired
    private RepRelDealService repRelDealService;

    @Override
    public List<ValidMessage> valid(Object o) {
        RepRelDealDto dto = (RepRelDealDto) o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        if(!"delete".equals(dto.getOperateType())) {
            if(StringUtil.isEmpty(dto.getProdId())){
                errorList.add(new ValidMessage("", "产品主代码 不能为空!"));
            }
            if(StringUtil.isEmpty(dto.getProdName())){
                errorList.add(new ValidMessage("", "产品名称 不能为空!"));
            }
            if( dto.getCreditorAmt()==null ){
                errorList.add(new ValidMessage("", "投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额 不能为空!"));
            }
            if(dto.getBondAmt()== null){
                errorList.add(new ValidMessage("", "投资于关联方承销的证券交易金额 不能为空!"));
            }            
            if(dto.getInvestAmt()== null){
                errorList.add(new ValidMessage("", "与关联方作为交易对手开展的投融资业务交易金额 不能为空!"));
            }
            if(dto.getAssetAmt()== null){
                errorList.add(new ValidMessage("", "投资关联方作为管理人的资产管理产品交易金额 不能为空!"));
            }
            if(dto.getTrustFee()== null){
                errorList.add(new ValidMessage("", "支付关联方托管费 不能为空!"));
            }
            if(dto.getSellFee()== null){
                errorList.add(new ValidMessage("", "支付关联方代销费 不能为空!"));
            }
            if(dto.getOtherFee()== null){
                errorList.add(new ValidMessage("", "发生其他关联交易金额 不能为空!"));
            }            
            if(dto.getEffdate()== null){
                errorList.add(new ValidMessage("", "生效日 不能为空!"));
            }
            if(dto.getEffdelidate()== null){
                errorList.add(new ValidMessage("", "生效截止日期 不能为空!"));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("uuid",dto.getUuid());
            RepRelDealDto repRelDealDto =repRelDealService.selectData(map);
            Date platDate = DateUtil.platDateFromCache();
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                if(StringUtil.isNotEmpty(dto.getEffdate())) {
                    if (DateUtil.compareDate(dto.getEffdate(), platDate) < 0) {
                        errorList.add(new ValidMessage("", "生效日不能小于平台日"));
                    }
                }

                if(StringUtil.isNotEmpty(dto.getEffdelidate())) {
                    if(DateUtil.compareDate(dto.getEffdelidate(), platDate) < 0){
                        errorList.add(new ValidMessage("", "生效截止日期不能小于平台日"));
                    }
                    if(StringUtil.isNotEmpty(dto.getEffdate()) && DateUtil.compareDate(dto.getEffdelidate(), dto.getEffdate()) < 0){
                        errorList.add(new ValidMessage("", "生效截止日期不能小于生效日"));
                    }
                }


            }else{

                if(repRelDealDto!=null && StringUtil.isNotEmpty(repRelDealDto.getEffdate())){
                    if(StringUtil.isNotEmpty(dto.getEffdate())) {
                        if (DateUtil.compareDate(repRelDealDto.getEffdate(), platDate) > 0) {
                            if (DateUtil.compareDate(dto.getEffdate(), platDate) < 0) {
                                errorList.add(new ValidMessage("", "生效日不能小于平台日"));
                            }
                        }
                    }
                }

                if(repRelDealDto!=null && StringUtil.isNotEmpty(repRelDealDto.getEffdelidate())){
                    if(StringUtil.isNotEmpty(dto.getEffdelidate())) {
                        if (DateUtil.compareDate(repRelDealDto.getEffdelidate(), platDate) > 0) {
                            if (DateUtil.compareDate(dto.getEffdelidate(), platDate) < 0) {
                                errorList.add(new ValidMessage("", "生效截止日期不能小于平台日"));
                            }
                            if(StringUtil.isNotEmpty(dto.getEffdate()) && DateUtil.compareDate(dto.getEffdelidate(), dto.getEffdate()) < 0){
                                errorList.add(new ValidMessage("", "生效截止日期不能小于生效日"));
                            }
                        }
                    }
                }
            }


            if(StringUtil.isNotEmpty(dto.getProdId()) && StringUtil.isNotEmpty(dto.getEffdate()) && StringUtil.isNotEmpty(dto.getEffdelidate())){
                List<RepRelDealDto> list = repRelDealService.selectExist(dto);
//                if(list.size() > 0){
//                    errorList.add(new ValidMessage("", "生效区间范围与已有数据有重叠"));
//                }
                if(list.size() > 0){
                    RepRelDealDto entity = list.get(0);
                    errorList.add(new ValidMessage("生效区间范围有重叠！"+"{"+DateUtil.format(entity.getEffdate(),"yyyy-MM-dd")
                            +"-"+DateUtil.format(entity.getEffdelidate(),"yyyy-MM-dd")+"}"));
                }
            }
        }

        return errorList;
    }
}
