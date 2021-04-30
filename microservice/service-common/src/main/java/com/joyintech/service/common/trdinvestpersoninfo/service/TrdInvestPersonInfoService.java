/*
 * 文件名：TrdInvestPersonInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.trdinvestpersoninfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.common.trdinvestpersoninfo.dao.TrdInvestPersonInfoMapper;

@Service
public class TrdInvestPersonInfoService extends BaseService<ITableRow> {
    
    @Autowired
    private TrdInvestPersonInfoMapper trdInvestPersonInfoMapper;
    public Page<SelectOptionDto> getCounterId(int page,int length,Map<String, Object> param) {
        param.put("COUNTER_TYPE", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
        param.put("YES_NO_Y", DictCodeList.是否_是.getValue());
        param.put("YES_NO_N", DictCodeList.是否_否.getValue());
        return this.queryForPageForSelectReturn(page, length,() ->getAllCounterId(param));
    }
    
    //选择交易方向是获取交易对手
    private List<SelectOptionDto> getAllCounterId(Map<String, Object> param) {
    	List<SelectOptionDto> list = trdInvestPersonInfoMapper.getCounterId(param);
    	if(list!=null && list.size()>0) {
    		for(SelectOptionDto dto : list) {
    			if(dto.getId()!=null && dto.getId().contains("F16_")) {
    				dto.setId(dto.getId().replace("F16_", ""));
    			}
    		}
    	}
    	return list;
    }
    
    public Page<SelectOptionDto> getCounterIdDepat(int page,int length,Map<String, Object> param) {
        param.put("COUNTER_TYPE", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
        param.put("YES_NO_Y", DictCodeList.是否_是.getValue());
        param.put("YES_NO_N", DictCodeList.是否_否.getValue());
        return this.queryForPageForSelectReturn(page, length,() ->trdInvestPersonInfoMapper.getCounterIdDepat(param));
    }
    
    /**
     * 查用户表的本方交易员
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto>  getTrader(int page,int length,Map<String, Object> param){
    	return this.queryForPageForSelectReturn(page, length, ()->trdInvestPersonInfoMapper.getTrader(param));
    }
    
    /**
     * 查交易员表的交易员
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto>  getCfetsTrader(int page,int length,Map<String, Object> param){
    	return this.queryForPageForSelectReturn(page, length, ()->trdInvestPersonInfoMapper.getCfetsTrader(param));
    }
}
