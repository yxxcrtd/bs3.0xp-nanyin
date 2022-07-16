
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.information.report.dao.ReportZjxsjg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportZjxsjg001Dto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加销售机构公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportZjxsjg001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportZjxsjg001Service extends BaseService<ReportZjxsjg001Dto> {

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private ReportZjxsjg001Mapper reportZjxsjg001Mapper;
    
   /**
    * 根据产品代码查询公告信息
    * <一句话功能简述>
    * <功能详细描述>
    * @param params
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    public Map<String, Object> findZjxsjg(Map<String, Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if (params.get("portnum") == null) {
            return dataMap;
        }
        String prodId = params.get("portnum").toString();
        Map<String,Object> searchparam = new HashMap<String,Object>();
        searchparam.put("prodId", prodId);
        ReportZjxsjg001Dto zjxsjg = reportZjxsjg001Mapper.queryZjxsjg(searchparam);
        if (zjxsjg == null) {
            return dataMap;
        }
        dataMap.put("${prodName}", zjxsjg.getProdName());
        dataMap.put("${regCode}", zjxsjg.getRegCode());
        Date puDate = commonPlatformDateService.getSysDate();
        if (null != params.get("endDate")) {
            puDate = (java.sql.Date)params.get("endDate");
        }
        dataMap.put("${pubDate}",DateUtil.format(puDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        return dataMap;
    }
}
