package com.joyintech.tams.svreport.chb.assettypesetting.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.chb.assettypesetting.dto.AssetTypeSetting001Dto;
import com.joyintech.tams.svreport.chb.assettypesetting.service.AssetTypeSetting001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AssetTypeSettingValidator implements BeanValidator {

    @Autowired
    private AssetTypeSetting001Service assetTypeSetting001Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        AssetTypeSetting001Dto dto = (AssetTypeSetting001Dto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(),"delete")){//删除

        }else {//新建、修改
             if ((StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) ||
                    (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))){
                Map<String ,Object> map = new HashMap<>();
                map.put("assetType1",dto.getAssetType1());
                map.put("assetType2",dto.getAssetType2());
                map.put("assetType3",dto.getAssetType3());
                map.put("assetBusiType",dto.getAssetBusiType());
                AssetTypeSetting001Dto assetTypeSetting001Dto = assetTypeSetting001Service.isConfilct(map);
                if (StringUtil.isEmpty(dto.getAssetType1())){
                    errorList.add(new ValidMessage("资产一级分类不可为空"));
                }
                if (dto.getSelectedItem1().size()==0){
                    errorList.add(new ValidMessage("资产负债类别、资产负债二级类别不可为空"));
                }
                if (StringUtil.isNotEmpty(assetTypeSetting001Dto)){
                    errorList.add(new ValidMessage("已存在相同的资产一级分类、资产二级分类、资产三级分类、业务分类，请重新编辑后再提交！"));
                }
            }
        }
        return errorList;
    }
}
