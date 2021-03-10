package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.service.SvrBaseCommonService;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheck001Mapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 产品余额、份额核查共同处理
 * @author wdb
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsQualityCheckCPYEComService extends BaseService<InvestorsQualityCheck001Dto>{
	/**
     * 共通Service
     */
    @Autowired
    private SvrBaseCommonService svrBaseCommonService;
    
	@Autowired
	private InvestorsQualityCheck001Mapper investorsQualityCheck001Mapper;
	
	/**
     * 读取系统中的对于颜色的配置参数
     * @return 保存了配置参数的map
     */
    public Map<String, String> readConfig() {
        Map<String, String> resultMap =new HashMap<String, String>();

        resultMap.put("mid", CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_COLOR_CONFIG_MID));
        resultMap.put("top", CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_COLOR_CONFIG_TOP));
        return resultMap;
    }

    
	
}
