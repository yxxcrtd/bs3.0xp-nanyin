/*
 * 文件名：OperationManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.operationmanage.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.operationmanage.dao.OperationManageMapper;
import com.joyintech.service.basicdata.operationmanage.dto.OperationManageDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperationManageService extends BaseService<OperationManageDto>{
	
	@Autowired
	private OperationManageMapper operationManageMapper;
	
	public Page<OperationManageDto> selectForPage(int page, int length, Map<String, Object> pageParam)
	        throws Exception {
			if(pageParam.get("index").equals("0")){
				if (StringUtil.isNotEmpty((String)pageParam.get("startCdate"))) {
					pageParam.put("startCdate", DateUtil.parse((String)pageParam.get("startCdate")));
				}
				else {
					pageParam.put("startCdate", null);
				}
				if (StringUtil.isNotEmpty((String)pageParam.get("endCdate"))) {
					pageParam.put("endCdate", DateUtil.parse((String)pageParam.get("endCdate")));
				}
				else {
					pageParam.put("endCdate", null);
				}
			}else if(pageParam.get("index").equals("1")){
				if (StringUtil.isNotEmpty((String)pageParam.get("startCdate1"))) {
					pageParam.put("startCdate1", DateUtil.parse((String)pageParam.get("startCdate1")));
				}
				else {
					pageParam.put("startCdate1", null);
				}
				if (StringUtil.isNotEmpty((String)pageParam.get("endCdate1"))) {
					pageParam.put("endCdate1", DateUtil.parse((String)pageParam.get("endCdate1")));
				}
				else {
					pageParam.put("endCdate1", null);
				}
			}else{
				if (StringUtil.isNotEmpty((String)pageParam.get("startSettleDate"))) {
					pageParam.put("startSettleDate", DateUtil.parse((String)pageParam.get("startSettleDate")));
				}
				else {
					pageParam.put("startSettleDate", null);
				}
				if (StringUtil.isNotEmpty((String)pageParam.get("endSettleDate"))) {
					pageParam.put("endSettleDate", DateUtil.parse((String)pageParam.get("endSettleDate")));
				}
				else {
					pageParam.put("endSettleDate", null);
				}
			}
			pageParam.put("yes", DictCodeList.是否_是.getValue());
			pageParam.put("no",DictCodeList.是否_否.getValue());
	        return super.queryForPage(page,length,() -> operationManageMapper.select(pageParam));
	    }
	
	public List<SuggestDto> getPortfolioId(Map<String, Object> pageParam){
	        return operationManageMapper.getPortfolioId(pageParam);
    }
	
	public List<SuggestDto> getPortfolioId1(Map<String, Object> pageParam){
        return operationManageMapper.getPortfolioId1(pageParam);
    }
}
