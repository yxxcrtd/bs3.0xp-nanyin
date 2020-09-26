/*
 * 文件名：HqParCloseFundInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqIntRateInfo;

/**
 * 行情信息-利率行情
 * 
 * @author WUJIE
 * @version 1.0 2018年9月06日
 * @see HqIntRateInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqIntRateInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        
        List<HqIntRateInfo> hqIntRateInfoList = new ArrayList<HqIntRateInfo>();

        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        
        // 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        hqIntRateInfoList = returnResult.getT(HqIntRateInfo.class);

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> map = handleListToVaLInfo(hqIntRateInfoList,acctEntity.getResovleDate());
        // 判断返回结果不为空
        if(map!=null){
        	if(map.get("MstIntRateInfo")!=null){
        		List<MstIntRateInfo> mstIntRateInfoList = (List<MstIntRateInfo>)map.get("MstIntRateInfo");
        		result.setMstIntRateInfoList(mstIntRateInfoList);
        	}
//        	if(map.get("MstRateInfo")!=null){
//        		List<MstRateInfo> mstRateInfoList = (List<MstRateInfo>)map.get("MstRateInfo");
//        		result.setMstRateInfoList(mstRateInfoList);
//        	}
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    private Map<String, Object>  handleListToVaLInfo(List<HqIntRateInfo> hqIntRateInfoList, Date date) {
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MstIntRateInfo> mstIntRateInfoList = new ArrayList<MstIntRateInfo>();
//    	List<MstRateInfo> mstRateInfoList = new ArrayList<MstRateInfo>();
//    	
//    	Map<String,MstRateInfo> mstRateInfoMap = new HashMap<String,MstRateInfo>();
    	for(HqIntRateInfo dto :hqIntRateInfoList){
    		dto.setRelaeseDate(date);
    		dto.setCreateTime(date);
    		//利率行情
    		MstIntRateInfo mstIntRateInfo =  getMstIntRateInfo(dto);
    		mstIntRateInfoList.add(mstIntRateInfo);
    		
    		//行情信息表
//    		MstRateInfo mstRateInfo = new MstRateInfo();
//    		mstRateInfo.setRateId(dto.getIntRateId());
//    		mstRateInfo.setRateName(dto.getIntRateId());
//    		mstRateInfo.setRateType(DictCodeList.行情类型_利率行情.getValue());
//    		mstRateInfoMap.put(mstRateInfo.getRateId(),mstRateInfo);
    	}
//    	for(String key :mstRateInfoMap.keySet()){
//    		mstRateInfoList.add(mstRateInfoMap.get(key));
//    	}
    	resultMap.put("MstIntRateInfo", mstIntRateInfoList);
//    	resultMap.put("MstRateInfo", mstRateInfoList);
    	
    	return resultMap;
    }
    
    private MstIntRateInfo getMstIntRateInfo(HqIntRateInfo dto){
    	MstIntRateInfo info = new MstIntRateInfo();
    	//行情代码 = A.行情代码
    	info.setIntRateId(dto.getIntRateId());
    	//生效日 = A.生效日
    	info.setEffDate(dto.getEffDate());
    	//发布日 = A.发布日
    	info.setRelaeseDate(dto.getRelaeseDate());
    	//利率 = A.利率
    	info.setIntRate(dto.getIntRate());
    	//创建时间
    	info.setCreateTime(dto.getCreateTime());
    	return info;
    }
}
