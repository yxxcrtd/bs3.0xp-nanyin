/*
 * 文件名：RskValAssetCodeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.risklevel.risklevelimport.service;

import java.util.*;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.risk.risklevel.risklevelimport.dao.RiskLevelImportMapper;
import com.joyintech.service.risk.risklevel.risklevelimport.dto.RiskLevelImportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapDtlMapper;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtlKey;
import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dao.RskValAssetCodeMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RiskLevelImportService
 * @since 1.0
 */
@Service
public class RiskLevelImportService extends BaseService<RiskLevelImportDto>{

    @Autowired
    private RiskLevelImportMapper riskLevelImportMapper;
    


    
    /**
     * 初始化映射明细
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public Page<RiskLevelImportDto> selectForPage(int page, int length,
                                                Map<String, Object> pageParam) throws Exception {

        String[]  finprodName = null;
        if(pageParam.get("finprodName") != null && !(pageParam.get("finprodName").toString().isEmpty())){
            String finprodNameTemp = (String)pageParam.get("finprodName");
            finprodName = finprodNameTemp.split(",");
            pageParam.replace("finprodName", Arrays.asList(finprodName));
        }else{
            pageParam.replace("finprodName", finprodName);
        }

        String[]  bondRiskType = null;
        if(pageParam.get("bondRiskType") != null && !(pageParam.get("bondRiskType").toString().isEmpty())){
            String bondRiskTypeTemp = (String)pageParam.get("bondRiskType");
            bondRiskType = bondRiskTypeTemp.split(",");
            pageParam.replace("bondRiskType", Arrays.asList(bondRiskType));
        }else{
            pageParam.replace("bondRiskType", bondRiskType);
        }

        String[]  assetsType = null;
        if(pageParam.get("assetsType") != null && !(pageParam.get("assetsType").toString().isEmpty())){
                String portfolioIdTemp = (String)pageParam.get("assetsType");
                assetsType = portfolioIdTemp.split(",");
                pageParam.replace("assetsType", Arrays.asList(assetsType));
        }else{
            pageParam.replace("assetsType", assetsType);
        }
             return super.queryForPage(page, length, () -> {
                 riskLevelImportMapper.selectForPage(pageParam);
             });
         }
    

    
    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void delete(List<RiskLevelImportDto> equityList){
        Map<String,Object> paramMap = new HashMap<>();
        for(RiskLevelImportDto riskLevelImportDto:equityList){
            paramMap.put("finprodMarketId",riskLevelImportDto.getFinprodMarketId());
            paramMap.put("updateTime", DateUtil.format(riskLevelImportDto.getUpdateTime()));
            riskLevelImportMapper.deleteById(paramMap);
        }
    }
    


    public void save(List<RiskLevelImportDto> equityList) {
        List<RiskLevelImportDto> deleteList = new ArrayList<RiskLevelImportDto>();
        for(RiskLevelImportDto riskLevelImportDto:equityList){
            Map<String,Object> param = new HashMap<>();
            param.put("finprodMarketId",riskLevelImportDto.getFinprodMarketId());
            param.put("updateTime",DateUtil.format(riskLevelImportDto.getUpdateTime()));

            String sinal = riskLevelImportMapper.queryIsExit(param);
            if(StringUtil.isNotEmpty(sinal)){
                deleteList.add(riskLevelImportDto) ;
            }
        }
        //重复数据先删后插
        delete(deleteList);
        for(RiskLevelImportDto riskLevelImportDto:equityList){
            riskLevelImportMapper.insert(riskLevelImportDto);
        }

    }

 

    public Boolean IsInFinproduct(Map<String, Object> pageParam) {
        return !StringUtil.isNotEmpty(riskLevelImportMapper.IsInFinproduct(pageParam));
    }
}
