package com.joyintech.tams.disclosure.information.config.repProdIndex.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.service.RepProdIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class RepProdIndexValidator implements BeanValidator {
    @Autowired
    private RepProdIndexService repProdIndexService;
    @Override
    public List<ValidMessage> valid(Object o) {
        RepProdIndexDto dto = (RepProdIndexDto) o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        if(!"delete".equals(dto.getOperateType())) {
            if(StringUtil.isEmpty(dto.getProdId())){
                errorList.add(new ValidMessage("", "子产品代码不能为空!"));
            }
            if(dto.getValueDate()== null){
                errorList.add(new ValidMessage("", "日期不能为空!"));
            }
            if(StringUtil.isEmpty(dto.getpProdId())){
                errorList.add(new ValidMessage("", "产品主代码不能为空!"));
            }
            if(dto.getAssetsValue()== null){
                errorList.add(new ValidMessage("", "子产品资产净值不能为空!"));
            }else if(dto.getAssetsValue().compareTo(new BigDecimal(0))<=0){
                errorList.add(new ValidMessage("", "子产品资产净值需要大于0!"));
            }
            if(dto.getCapital()== null){
                errorList.add(new ValidMessage("", "子产品资产净值不能为空!"));
            }
            if(dto.getUnitValue()== null){
                errorList.add(new ValidMessage("", "子产品单位净值不能为空!"));
            }
            if(dto.getUnitValueT()== null){
                errorList.add(new ValidMessage("", "子产品单位累计净值不能为空!"));
            }
            if(dto.getProdType().equals("M")){
                if(dto.getwYield()== null){
                    errorList.add(new ValidMessage("", "子产品万份收益不能为空!"));
                }
                if(dto.getYield7()== null){
                    errorList.add(new ValidMessage("", "子产品七日年化收益率不能为空!"));
                }
                if(dto.getpWYield()== null){
                    errorList.add(new ValidMessage("", "母产品万份收益不能为空!"));
                }
                if(dto.getpYield7()== null){
                    errorList.add(new ValidMessage("", "母产品七日年化收益率不能为空!"));
                }
            }
            if(dto.getpAssets()== null){
                errorList.add(new ValidMessage("", "母产品总资产不能为空!"));
            }else if(dto.getpAssets().compareTo(new BigDecimal(0))<=0){
                errorList.add(new ValidMessage("", "母产品总资产需要大于0!"));
            }
            if(dto.getpAssetsValue()== null){
                errorList.add(new ValidMessage("", "母产品资产净值不能为空!"));
            }else if(dto.getpAssetsValue().compareTo(new BigDecimal(0))<=0){
                errorList.add(new ValidMessage("", "母产品资产净值需要大于0!"));
            }
            if(dto.getpCapital()== null){
                errorList.add(new ValidMessage("", "母产品实收资本不能为空!"));
            }
            if(dto.getpUnitValue()== null){
                errorList.add(new ValidMessage("", "母产品单位净值不能为空!"));
            }
            if(dto.getpUnitValueT()== null){
                errorList.add(new ValidMessage("", "母产品单位累计净值不能为空!"));
            }
            if(!StringUtil.isEmpty(dto.getProdId()) && !(dto.getValueDate()== null)){
                RepProdIndexDto repProdIndexDto = repProdIndexService.selectByProdIDAndValueDate(dto);
                if(repProdIndexDto != null && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    errorList.add(new ValidMessage("", "“子产品代码+日期”不能同时与已有数据重复!"));
                }
            }
            if(dto.getIsGrade().equals("01")){
                RepProdIndexDto repProdIndexDto2 = repProdIndexService.selectPProdInfo(dto);
                RepProdIndexDto repProdIndexDto3 = repProdIndexService.selectPProd(dto);
                if(repProdIndexDto2 == null && repProdIndexDto3 != null && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    errorList.add(new ValidMessage("", "对于分级产品，每个子产品数据同一天的“母产品总资产“母产品资产净值”“母产品实收资本”“母产品单位净值”“母产品单位累计净值”“母产品万份收益”“母产品七日年化收益率”需要分别相等!"));
                }
            }
        }

        return errorList;
    }
}
