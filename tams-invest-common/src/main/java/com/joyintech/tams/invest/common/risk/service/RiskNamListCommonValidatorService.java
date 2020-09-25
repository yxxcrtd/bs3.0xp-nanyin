package com.joyintech.tams.invest.common.risk.service;


import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.risk.dao.RiskNamListCommonMapper;
import com.joyintech.tams.invest.common.risk.dto.RiskNamListCommonDto;
import com.joyintech.tams.invest.common.risk.dto.RiskNameListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  名单公共校验
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年7月23日
 * @see RiskNamListCommonValidatorService
 * @since 1.0
 */
@Service
public class RiskNamListCommonValidatorService {

    @Autowired
    private RiskNamListCommonMapper riskNamListCommonMapper;

    public Map<String,Object> 名单是否匹配(Map<String,Object> param){
        List<RiskNamListCommonDto> dtoList = new ArrayList<RiskNamListCommonDto>();
        Map<String,Object> resultMap = new HashMap<>();
        //是否验证资产
        boolean isCheckAsset = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CHECK_ASSET")); 
        //是否验证机构
        boolean isCheckOrg = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CHECK_ORG"));
        
        boolean assetFlag = false;
//        boolean orgTrusteeFlag = false;
//        boolean orgIssuerFlag = false;boolean orgManagerFlag = false;
//        boolean orgFinancierFlag = false;
        boolean assetFlagB = false;
        boolean assetFlagW = false;
        //通过资产id get相关信息
        if (!isCheckAsset && !isCheckOrg) {
            resultMap.put("isAssetMatch",true);
            resultMap.put("isOrgMatch",true);
            resultMap.put("isAssetWhiteList",false);
            resultMap.put("isOrgBlackMatch",true);
            resultMap.put("isOrgWhiteMatch",true);
            return resultMap;
        }
        if(param.containsKey("finprodId")){
            param.put("RISK_TYPE",DictCodeListExt2.配置类型_风险分类.getValue());
            dtoList.addAll(riskNamListCommonMapper.getAllInfoByFinprodId(param));
        }else{
            param.put("NO_TARGET_TYPE_MAPPING",DictCodeListExt2.配置类型_无标的投管资产类型映射行内资产分类.getValue());
            dtoList.addAll(riskNamListCommonMapper.getAllInfoByNoFinprodId(param));
        }
        Map<String,Boolean> whiteMap = new HashMap<>();
        Map<String,Boolean> blackMap = new HashMap<>();
        if(null!=dtoList&&!dtoList.isEmpty()){
            for(RiskNamListCommonDto item: dtoList) {
               if(null!=item&&StringUtil.isNotEmpty(item.getListId())){
                    Map<String, Object> nameListMap = new HashMap<>();
                    //1.判断资产是否匹配上
                    if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_资产.getValue())) {
                        if(isCheckAsset&&StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){ 
                            nameListMap.put("listId", item.getListId());
                            nameListMap.put("finprodId", item.getFinprodId());
                            nameListMap.put("finprodName", item.getFinprodName());
                            nameListMap.put("finprodAbbr", item.getFinprodAbbr());
                            /* edit by songyawei 20200701 start */
                            List<RiskNamListCommonDto> listDto = riskNamListCommonMapper.selectAssetCount(nameListMap);
                            int count = 0;
                            if(listDto!=null) {
                            	count = listDto.size();
                            }
                            /* edit by songyawei 20200701 end */
                            if (count > 0) {
                                assetFlag = true;
                                resultMap.put("listTypeAsset",item.getListType());
                            }
                        }else if(isCheckAsset&&StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                            assetFlagW = true;
                            nameListMap.put("listId", item.getListId());
                            nameListMap.put("finprodId", item.getFinprodId());
                            nameListMap.put("finprodName", item.getFinprodName());
                            nameListMap.put("finprodAbbr", item.getFinprodAbbr());
                            /* edit by songyawei 20200701 start */
                            List<RiskNamListCommonDto> listDto = riskNamListCommonMapper.selectAssetCount(nameListMap);
                            int count = 0;
                            if(listDto!=null) {
                            	count = listDto.size();
                            }
                            /* edit by songyawei 20200701 end */
                            if (count > 0) {
                                assetFlagB = true;  
                            } 
                        }
                    }else{
                        if(isCheckOrg){
                            nameListMap.put("listId", item.getListId());
                            RiskNamListCommonDto riskNamListCommonDto = new RiskNamListCommonDto();
                            //2.判断机构是否匹配上
                            if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_托管人.getValue())) {
                                if(StringUtil.isNotEmpty(item.getTrusteeId())&&StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getTrusteeId());
                                    boolean orgTrusteeFlag = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    blackMap.put(item.getListId(), orgTrusteeFlag);
                                    if(orgTrusteeFlag){  
                                            resultMap.put("listTypeOrg",item.getListType()); 
                                     } 
                                    continue;
                                }else if(StringUtil.isNotEmpty(item.getTrusteeId())&&StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getTrusteeId());
                                    boolean orgTrusteeFlagB = 机构是否匹配(riskNamListCommonDto,nameListMap); 
                                    whiteMap.put(item.getListId(), orgTrusteeFlagB);
                                    continue;
                                }
                            }else if(StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_发行人.getValue())) {
                                if(StringUtil.isNotEmpty(item.getIssuer())&& StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getIssuer());
                                    boolean orgIssuerFlag = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    blackMap.put(item.getListId(), orgIssuerFlag);
                                    if(orgIssuerFlag){
                                        resultMap.put("listTypeOrg",item.getListType());
                                    } 
                                    continue;
                                }else if(StringUtil.isNotEmpty(item.getIssuer())&& StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getIssuer());
                                    boolean orgIssuerFlagB = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    whiteMap.put(item.getListId(), orgIssuerFlagB);
                                    continue;
                                }
                            }else if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_管理人.getValue())) {
                                if(StringUtil.isNotEmpty(item.getManager()) && StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getManager());
                                    boolean orgManagerFlag = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    blackMap.put(item.getListId(), orgManagerFlag);
                                    if(orgManagerFlag){  
                                       resultMap.put("listTypeOrg",item.getListType()); 
                                    } 
                                    continue;
                                }else if(StringUtil.isNotEmpty(item.getManager()) && StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getManager());
                                    boolean orgManagerFlagB = 机构是否匹配(riskNamListCommonDto,nameListMap); 
                                    whiteMap.put(item.getListId(), orgManagerFlagB);
                                    continue;
                                }
                            }else if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_融资人.getValue())) {
                                if(StringUtil.isNotEmpty(item.getFinancier())&& StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getFinancier());
                                    boolean orgFinancierFlag = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    blackMap.put(item.getListId(), orgFinancierFlag);
                                    if(orgFinancierFlag){ 
                                        resultMap.put("listTypeOrg",item.getListType()); 
                                    } 
                                    continue;
                                }else if(StringUtil.isNotEmpty(item.getFinancier())&& StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getFinancier());
                                    boolean orgFinancierFlagB = 机构是否匹配(riskNamListCommonDto,nameListMap); 
                                    whiteMap.put(item.getListId(), orgFinancierFlagB);
                                    continue;
                                }
                            }else if(StringUtil.equals(item.getListDetailIdType(), DictCodeListExt3.名单主体明细类型_交易对手.getValue())) {
                                if(param.containsKey("counterId")&&null!=param.get("counterId") && StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_黑名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo2(param.get("counterId").toString(), DictCodeListExt1.交易对手类型_市场机构.getValue());
                                    boolean orgCounterIdFlag = 机构是否匹配(riskNamListCommonDto,nameListMap);
                                    blackMap.put(item.getListId(), orgCounterIdFlag);
                                    if(orgCounterIdFlag) {  
                                        resultMap.put("listTypeOrg", item.getListType()); 
                                    } 
                                    continue;
                                }else if(param.containsKey("counterId")&&null!=param.get("counterId") && StringUtil.equals(item.getListType(), DictCodeListRsk.名单属性_白名单.getValue())){
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo2(param.get("counterId").toString(), DictCodeListExt1.交易对手类型_市场机构.getValue());
                                    boolean orgCounterIdFlagB = 机构是否匹配(riskNamListCommonDto,nameListMap); 
                                    whiteMap.put(item.getListId(), orgCounterIdFlagB);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(null!=dtoList&&!dtoList.isEmpty()){ 
            boolean blackFlag = true;
            boolean whiteFlag = true;
            if (StringUtil.isNotEmpty(blackMap) && blackMap.size() > 0) {  
                for (String blackKey : blackMap.keySet()) {
                    if (blackMap.get(blackKey)) {
                        blackFlag = false;
                    }
                }
            }  
            if (StringUtil.isNotEmpty(whiteMap) && whiteMap.size() > 0) {  
                for (String whiteKey : whiteMap.keySet()) {
                    if (!whiteMap.get(whiteKey)) {
                        whiteFlag = false;
                    }
                }
            } 
            resultMap.put("isAssetMatch",(assetFlag||assetFlagB));
            resultMap.put("isOrgMatch",blackFlag&&whiteFlag);
            resultMap.put("isAssetWhiteList",assetFlagW);
            resultMap.put("isOrgBlackMatch",blackFlag);
            resultMap.put("isOrgWhiteMatch",whiteFlag);
        }else {
            resultMap.put("isAssetMatch",true);
            resultMap.put("isOrgMatch",true);
            resultMap.put("isAssetWhiteList",false);
            resultMap.put("isOrgBlackMatch",true);
            resultMap.put("isOrgWhiteMatch",true);
        }
        return resultMap;
    }

    private Boolean 机构是否匹配( RiskNamListCommonDto riskNamListCommonDto,Map<String, Object> nameListMap){
        if(StringUtil.isNotEmpty(riskNamListCommonDto.getCustomerId())){
            nameListMap.put("finprodId", riskNamListCommonDto.getInsitituteCode());
            nameListMap.put("finprodName", riskNamListCommonDto.getCustomerName());
            nameListMap.put("finprodAbbr", riskNamListCommonDto.getCustomerName());
            /* edit by songyawei 20200701 start */
            List<RiskNamListCommonDto> listDto = riskNamListCommonMapper.selectAssetCount(nameListMap);
            int count = 0;
            if(listDto!=null) {
            	count = listDto.size();
            }
            /* edit by songyawei 20200701 end */
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *  可投金额试算使用
     * 〈功能详细描述〉
     * 判断当前资产是否在机构名单
     * @author nixiukuan
     * @version 1.0 2019年10月11日
     * @see RiskNamListCommonValidatorService
     * @since 1.0
     */
    private Boolean orgCheckIspass( RiskNamListCommonDto riskNamListCommonDto,Map<String, Object> nameListMap){
        if(StringUtil.isNotEmpty(riskNamListCommonDto.getCustomerId())){
            nameListMap.put("finprodId", nameListMap.get("finprodId"));
            nameListMap.put("finprodName", riskNamListCommonDto.getCustomerName());
            nameListMap.put("finprodAbbr", riskNamListCommonDto.getCustomerName());
            /* edit by songyawei 20200701 start */
            List<RiskNamListCommonDto> listDto = riskNamListCommonMapper.selectAssetCount(nameListMap);
            int count = 0;
            if(listDto!=null) {
            	count = listDto.size();
            }
            /* edit by songyawei 20200701 end */
            if (count > 0) {
                return true;
            }
        }
        return false;
    }
    /**
     *  可投金额试算使用
     * 〈功能详细描述〉
     * 当前资产是否在名单中,在的话可投总金额为Max999999999999否则为0
     * @author nixiukuan
     * @version 1.0 2019年10月11日
     * @see RiskNamListCommonValidatorService
     * @since 1.0
     */
    public List<RiskNameListDto>  getNameListByFinprodId(String finprodId,String platDate) {
        List<RiskNameListDto> riskNameListDtoList=new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("RISK_TYPE",DictCodeListExt2.配置类型_风险分类.getValue());
        param.put("listDate",DateUtil.parse(platDate));
        param.put("finprodId", finprodId);
        //根据参数判断当前资产是否在名单中在的
        //是否验证资产
        boolean isCheckAsset = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CHECK_ASSET"));;
        //是否验证机构
        boolean isCheckOrg =  Boolean.parseBoolean(CacheUtil.getSysParam("IS_CHECK_ORG"));;
        boolean assetFlag = true;
        boolean orgTrusteeFlag = true;
        boolean orgIssuerFlag = true;
        boolean orgManagerFlag = true;
        boolean orgFinancierFlag = true;
        //通过资产id get相关信息
        List<RiskNamListCommonDto> dtoList = riskNamListCommonMapper.getAllInfoByFinprodId(param);
        if (null != dtoList && !dtoList.isEmpty()) {
            for (RiskNamListCommonDto item : dtoList) {
                RiskNameListDto riskNameListDto=new RiskNameListDto();
                if (StringUtil.isNotEmpty(item.getListId())) {
                    Map<String, Object> nameListMap = new HashMap<>();
                    //1.判断资产是否匹配上
                    if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_资产.getValue())) {
                        if (isCheckAsset) {
                            assetFlag = false;
                            nameListMap.put("listId", item.getListId());
                            nameListMap.put("finprodId", item.getFinprodId());
                            nameListMap.put("finprodName", item.getFinprodName());
                            nameListMap.put("finprodAbbr", item.getFinprodAbbr());
                            /* edit by songyawei 20200701 start */
                            List<RiskNamListCommonDto> listDto = riskNamListCommonMapper.selectAssetCount(nameListMap);
                            int count = 0;
                            if(listDto!=null) {
                            	count = listDto.size();
                            }
                            /* edit by songyawei 20200701 end */
                            if (count > 0) {
                                assetFlag = true;
                                riskNameListDto.setListDetailType(item.getListDetailIdType());
                                riskNameListDto.setPass(assetFlag);
                                riskNameListDtoList.add(riskNameListDto);
                                continue;
                            }
                        }
                    } else {
                        if (isCheckOrg) {
                            nameListMap.put("listId", item.getListId());
                            nameListMap.put("finprodId", item.getFinprodId());
                            RiskNamListCommonDto riskNamListCommonDto = new RiskNamListCommonDto();
                            //2.判断机构是否匹配上
                            if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_托管人.getValue())) {
                                if (StringUtil.isNotEmpty(item.getTrusteeId())) {
                                    orgTrusteeFlag = false;
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getTrusteeId());
                                    orgTrusteeFlag = orgCheckIspass(riskNamListCommonDto, nameListMap);
                                    continue;
                                }
                            } else if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_发行人.getValue())) {
                                if (StringUtil.isNotEmpty(item.getIssuer())) {
                                    orgIssuerFlag = false;
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getIssuer());
                                    orgIssuerFlag = orgCheckIspass(riskNamListCommonDto, nameListMap);
                                    if(orgIssuerFlag)
                                    {
                                        riskNameListDto.setListDetailType(item.getListDetailIdType());
                                        riskNameListDto.setPass(orgIssuerFlag);
                                        riskNameListDtoList.add(riskNameListDto);
                                    }
                                    continue;
                                }
                            } else if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_管理人.getValue())) {
                                if (StringUtil.isNotEmpty(item.getManager())) {
                                    orgManagerFlag = false;
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getManager());
                                    orgManagerFlag = orgCheckIspass(riskNamListCommonDto, nameListMap);
                                    if(orgManagerFlag)
                                    {
                                        riskNameListDto.setListDetailType(item.getListDetailIdType());
                                        riskNameListDto.setPass(orgManagerFlag);
                                        riskNameListDtoList.add(riskNameListDto);
                                    }
                                    continue;
                                }
                            } else if (StringUtil.equals(item.getListDetailIdType(), DictCodeListExt2.名单主体明细类型_融资人.getValue())) {
                                if (StringUtil.isNotEmpty(item.getFinancier())) {
                                    orgFinancierFlag = false;
                                    riskNamListCommonDto = riskNamListCommonMapper.selectCustomerInfo(item.getFinancier());
                                    orgFinancierFlag = orgCheckIspass(riskNamListCommonDto, nameListMap);
                                    if(orgFinancierFlag)
                                    {
                                        riskNameListDto.setListDetailType(item.getListDetailIdType());
                                        riskNameListDto.setPass(orgFinancierFlag);
                                        riskNameListDtoList.add(riskNameListDto);
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                }
            }

        }
        return riskNameListDtoList;
    }
}
