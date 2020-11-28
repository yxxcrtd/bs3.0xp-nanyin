package com.joyintech.service.valuation.accounadjustmanage.assetbook.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dao.ValAssetBook006Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetBook006Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="serviceValAssetBook006Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetBook006Service extends CommonExportService<ValAssetBook006Dto> {

    @Autowired
    private ValAssetBook006Mapper mapper;

    public List<SuggestDto> getParamCode(Map<String, String> param) {
        return mapper.getParamCode(param);
    }

    public Page<ValAssetBook006Dto> findPage(int page, int length, Map<String, Object> map) {
        Page<ValAssetBook006Dto> objPage = super.queryForPage(page, length, () -> mapper.findPage(map));
        for(int i=0;;i++){
            if(map.get("valAssetBook006DtoList["+i+"][paramCode]")!=null){
                for(int j=objPage.getRoot().size();j>0;j--){
                    if(map.get("valAssetBook006DtoList["+i+"][paramCode]").equals(objPage.getRoot().get(j-1).getParamCode())){
                        objPage.getRoot().get(j-1).setObjParamValue((String)map.get("valAssetBook006DtoList["+i+"][objParamValue]"));
                    }
                }
            }else{
                break;
            }
        }
        return objPage;
    }

    public void doComplete(ValAssetBook006Dto entity) {
        List<ValAssetBook006Dto> valAssetBook006DtoList=  entity.getValAssetBook006DtoList();
        for(int i=0;i<valAssetBook006DtoList.size();i++){
            ValAssetBook006Dto dto=valAssetBook006DtoList.get(i);
            if(dto.getObjParamValue()!=null&&dto.getObjParamValue()!=""&&!dto.getObjParamValue().equals(dto.getParamValue())){
                mapper.updateParamValue(dto);
            }
        }
    }
}
