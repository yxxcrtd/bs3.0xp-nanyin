/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workrule.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.manage.workrule.dao.ReportRule001Mapper;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule001Dto;
import com.joyintech.tams.common.base.dao.RepRuleMapper;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceReportRule001Service")
public class ReportRule001Service extends CommonExportService<ReportRule001Dto> {
	
    /**
     */
    @Autowired
    private ReportRule001Mapper reportRule001Mapper;
    @Autowired
    private RepRuleMapper repRuleMappe;

    
    /**
     * 日志
     */
     private static Logger log = LoggerFactory.getLogger(ReportRule001Service.class);
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ReportRule001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {

        return super.queryForPageForSelectReturn(page, length, ()-> reportRule001Mapper.queryPageList(params));
    }



    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportRule001Dto> queryPageList(Map<String, Object> params) {
      
        return reportRule001Mapper.queryPageList(params);
    }






	 /***
	  * 删除
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param jobId
	  * @return
	  */
	public int deleteByPrimaryKey(String jobId) {
		// TODO Auto-generated method stub
		return repRuleMappe.deleteByPrimaryKey(jobId);
	}

}
