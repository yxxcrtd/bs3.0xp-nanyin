package com.joyintech.tams.disclosure.information.config.saleagency.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.service.SaleAgency002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SaleAgencyValidator implements BeanValidator {

    @Autowired
    private SaleAgency002Service saleAgency002Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        SaleAgencyDto dto = (SaleAgencyDto)value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            //删除check

        }else{
            if(!StringUtil.isNotEmpty(dto.getMaintenanceType())){
                resultList.add(new ValidMessage("maintenanceType","请先选择维护类型！"));
                return resultList;
            }
            if(DictCodeListExt5.维护类型_代销机构.getValue().equals(dto.getMaintenanceType())){
                //非空校验
                if(!StringUtil.isNotEmpty(dto.getAgencyName())){
                    resultList.add(new ValidMessage("agencyName","代销机构必填！"));
                }
                if(!StringUtil.isNotEmpty(dto.getAgreementVdate())){
                    resultList.add(new ValidMessage("agreementVdate","协议起始时间必填！"));
                }
                if(!StringUtil.isNotEmpty(dto.getAgreementMdate())){
                    resultList.add(new ValidMessage("agreementMdate","协议终止时间必填！"));
                }
                //协议终止时间要大于等于协议起始时间
                if(StringUtil.isNotEmpty(dto.getAgreementVdate()) && StringUtil.isNotEmpty(dto.getAgreementMdate())){
                    if(DateUtil.compareDate(dto.getAgreementVdate(),dto.getAgreementMdate())>0){
                        resultList.add(new ValidMessage("agreementMdate","协议终止时间要大于等于协议起始时间！"));
                    }
                }
                if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    //代销机构不能重复
                    if(saleAgency002Service.checkNameIsRepeat(dto.getAgencyName())){
                        resultList.add(new ValidMessage("代销机构已存在，请确认！"));
                    }
                }
            }else{
                if(!StringUtil.isNotEmpty(dto.getProdName())){
                    resultList.add(new ValidMessage("prodName","产品名称必填！"));
                }
                if(!StringUtil.isNotEmpty(dto.getAgencyIds())){
                    resultList.add(new ValidMessage("agencyIds","代销机构必填！"));
                }
                //需要校验同一产品，同一代销机构的数据不能再提交了
                if(StringUtil.isNotEmpty(dto.getProdId()) && StringUtil.isNotEmpty(dto.getAgencyIds())){
                    List<String> agencyIds = StringUtil.splitToList(null, dto.getAgencyIds());
                    //根据产品代码查询已经关联的代销机构
                    List<ProdSaleAgencyDto> list=saleAgency002Service.selectAgencyByProdId(dto.getProdId());
                    if(list!=null && list.size()>0){
                        for (ProdSaleAgencyDto prodSaleAgencyDto : list) {
                            for (String agencyId : agencyIds) {
                                if(agencyId.equals(prodSaleAgencyDto.getAgencyId())){
                                    resultList.add(new ValidMessage("该产品已经关联了代销机构"+"\""+prodSaleAgencyDto.getAgencyName()+"\""+",请确认！"));
                                }
                            }

                        }
                    }
                }
            }

        }
        return resultList;
    }
}
