/*
 * 文件名：ValBatchLog002Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：nihuai
 * 修改时间：2018年2月23日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.valuation.system.log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.system.log.dao.ValBatchLog002Mapper;
import com.joyintech.tams.valuation.system.log.dto.BokBatchMarketwarningInfoDto;

/**
 * 批处理日志service
 * 
 * @author ww1
 * @version 1.0 2018年2月23日
 * @see ValBatchLog002Service
 * @since 1.0
 */
@Service
public class ValBatchLog002Service extends CommonExportService<BokBatchMarketwarningInfoDto> {

    @Autowired
    private ValBatchLog002Mapper valBatchLog002Mapper;
 
    
	public Page<BokBatchMarketwarningInfoDto> findWarnLogDetail(Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(ValBatchLog002Mapper.class, "findWarnLogDetail", pageParam);
	}
	
	   /**
     * 导出报表一览
     * 
     * @param pageParam 参数
     * @return list
     */
    public List<BokBatchMarketwarningInfoDto> selectForExport(Map<String, Object> pageParam) {
         return valBatchLog002Mapper.findWarnLogDetail(pageParam);
    }
}
