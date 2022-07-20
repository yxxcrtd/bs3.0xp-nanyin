package com.joyintech.tams.disclosure.information.config.prodpartner.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner001Service;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProdPartnerInfoValidator implements BeanValidator {


    @Autowired
    private ProdPartner002Service prodPartner002Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        ProdPartnerDto dto = (ProdPartnerDto)value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            //删除check

        }else{
                //保存check
                /*if (prodPartner002Service.checkPartnerExistByProdId(dto.getProdId()) > 0) {
                    resultList.add(new ValidMessage("该产品已经维护了合作机构！"));
                }*/
                if(DateUtil.compareDate(platDate,dto.getEffDate())>0){
                    resultList.add(new ValidMessage("生效日要大于等于平台日期！"));
                }
                if(DateUtil.compareDate(platDate,dto.getEffEndDate())>0){
                    resultList.add(new ValidMessage("生效日截止日期要大于等于平台日期！"));
                }
                //生效日截止日期要大于等于生效日
                if(DateUtil.compareDate(dto.getEffDate(),dto.getEffEndDate())>0){
                    resultList.add(new ValidMessage("生效日截止日期要大于等于生效日！"));
                }
                //校验同一个产品不能有两条数据的生效区间范围有重叠
                //查询产品所有的合作机构数据
                List<ProdPartnerDto> prodPartnerDtoList=prodPartner002Service.checkPartnerExistByProdId(dto.getProdId());
                if(prodPartnerDtoList.size()>0){
                    for (ProdPartnerDto partnerDto : prodPartnerDtoList) {
                        if(!((DateUtil.compareDate(dto.getEffDate(),partnerDto.getEffDate())<0 && DateUtil.compareDate(dto.getEffEndDate(),partnerDto.getEffDate())<0)
                        || (DateUtil.compareDate(dto.getEffDate(),partnerDto.getEffEndDate())>0 && DateUtil.compareDate(dto.getEffEndDate(),partnerDto.getEffEndDate())>0))){
                            resultList.add(new ValidMessage("生效区间范围有重叠！"+"{"+DateUtil.format(partnerDto.getEffDate(),"yyyyMMdd")
                                    +"-"+DateUtil.format(partnerDto.getEffEndDate(),"yyyyMMdd")+"}"));
                            break;
                        }
                    }
                }
            //编辑check
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if (DateUtil.compareDate(dto.getEffDate(),platDate)<=0) {
                    resultList.add(new ValidMessage("生效日小于等于平台日！"));
                }
                if (DateUtil.compareDate(dto.getEffEndDate(),platDate)<=0) {
                    resultList.add(new ValidMessage("生效截止日小于等于平台日！"));
                }
            }
        }
        return resultList;
    }
}
