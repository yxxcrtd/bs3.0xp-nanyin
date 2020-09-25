package com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.dao.ReportDealMapper;

@Service("disclosureReportDealService")
public class ReportDealService {
	
	@Autowired
	ReportDealMapper reportDealMapper;
	
	/**
     * 更新相同报表数据数据状态为D(删除)
     * @param RepReveal repReveal
     */
    public int updateDataStatus(RepReveal repReveal){
    	return reportDealMapper.updateDataStatus(repReveal); 
    }
    
    /**
     * 查询相同报表数据数据是否为已确定
     * @param RepReveal repReveal
     */
    public boolean selectConfirmStatus(RepReveal repReveal){
    	boolean confirmFlag = true;
    	int size = reportDealMapper.selectConfirmStatus(repReveal);
    	if(size > 0) {
    		// 存在相同且已确认的数据,返回false,不能更新
    		confirmFlag = false;
    		return confirmFlag; 
    	}
    	return confirmFlag;
    }
    

    /**
     * 查询数据
     * @param List<String> idList
     * @see
     * @since 1.0
     */
    public String selectDate(Map<String, Object> paraMap) {
        String value = "";
    	String dateParam = paraMap.get("data").toString();
		String methodName = paraMap.get("Method").toString();
		switch (methodName) {
            case "method1":
                value = getMathed1(paraMap);
                break;

            default:
                break;
        }
    	return value;
    }
    
    private String getMathed1(Map<String, Object> paraMap) {
//        String josnString = "{totalCount:2,data:[{prodId:A001,fileType:01,revealStatus:01},{prodId:A002,fileType:01,revealStatus:01}]}";
        String josnString = "";
        // sql查询结果
        paraMap.put("revealStatus", DictCodeListExt2.披露状态_未披露.getValue());
        List<RepReveal> infoList = reportDealMapper.selectDataByRevEndDate(paraMap);
        // 方法返回结果list
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = null;
        RepReveal repReveal = new RepReveal();
        if(StringUtil.isNotEmpty(infoList)) {
        	if(infoList.size() > 20) {
        		for (int i = 0; i < 20; i++) {
                	repReveal = infoList.get(i);
                	resultMap = new HashMap<String, Object>();
                	resultMap.put("prodId", repReveal.getProdid());
                	resultMap.put("fileType", repReveal.getReportCategory());
                	resultMap.put("revealStatus", "01");
                	resultList.add(resultMap);
        		}
        	}else {
        		for (int i = 0; i < infoList.size(); i++) {
                	repReveal = infoList.get(i);
                	resultMap = new HashMap<String, Object>();
                	resultMap.put("prodId", repReveal.getProdid());
                	resultMap.put("fileType", repReveal.getReportCategory());
                	resultMap.put("revealStatus", "01");
                	resultList.add(resultMap);
        		}
        	}
        }
        
        if(StringUtil.isNotEmpty(resultList)) {
        	josnString = JsonUtils.objectArray2Json(resultList.toArray());
        	josnString = "{\"totalCount\":" + infoList.size() + ",\"datas\":" + josnString + "}";
        }
//        for(RepReveal dto : infoList) {
//            if(StringUtil.isNotEmpty(dto.getSysFilename())) {
//                ReportDealDto resultDto = new ReportDealDto();
//                String fileType = dto.getSysFilename().substring(dto.getSysFilename().lastIndexOf(".") + 1);
//                resultDto.setFileType(fileType);
//                resultDto.setProdid(dto.getProdid());
//                resultDto.setRevealEndDate(DateUtil.format(dto.getRevealEndDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//                resultDto.setRevealStatus(dto.getRevealStatus());
//                resultList.add(resultDto);
//            }
//            else {
//                ReportDealDto resultDto = new ReportDealDto();
//                String fileType = dto.getFilename().substring(dto.getFilename().lastIndexOf(".") + 1);
//                resultDto.setFileType(fileType);
//                resultDto.setProdid(dto.getProdid());
//                resultDto.setRevealEndDate(DateUtil.format(dto.getRevealEndDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//                resultDto.setRevealStatus(dto.getRevealStatus());
//                resultList.add(resultDto);
//            }
//        }
//        if(StringUtil.isNotEmpty(resultList)) {
//            josnString = JsonUtils.objectArray2Json(resultList.toArray());
//            josnString = "totalCount:"+100+",data:" + josnString;
//        }
        return josnString;
    }
}
