
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.information.report.dao.ReportKfqgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportKfqgg001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 产品开放期公告/报告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportKfqgg001Service
 * @since 1.0
 */
@Service(value="ReportKfqgg001Service")
@Transactional(rollbackFor = Exception.class)
public class ReportKfqgg001Service extends BaseService<ReportKfqgg001Dto> {
    
    @Autowired
    private ReportKfqgg001Mapper reportKfqgg001Mapper;
    
    
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
    public Map<String, Object> findKfqgg(Map<String, Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if (params.get("portnum") == null) {
            return dataMap;
        }
        String portnum = params.get("portnum").toString();
        String endDate = DateUtil.format((Date)params.get("endDate"));
        Map<String,Object> searchparam = new HashMap<String,Object>();
        searchparam.put("prodId", portnum);
        searchparam.put("endDate", endDate);
        ReportKfqgg001Dto kfgg = reportKfqgg001Mapper.queryKfqgg(searchparam);
        if (kfgg == null) {
            return dataMap;
        }
        String[] endDates = endDate.split("-");
        dataMap.put("${prodName}", kfgg.getProdName());
        dataMap.put("${prodId}", kfgg.getProdId());
        dataMap.put("${announceYear}", endDates[0]);
        dataMap.put("${announceMonth}", endDates[1]);
        dataMap.put("${announceDay}", endDates[2]);
        //根据pProdid查询排期表
        searchparam.put("startDate",endDates[0] + "-01-01");
        searchparam.put("endDate",endDates[0] + "-12-31");
        //如果是分级产品，排期表只存母产品代码的排期数据，所以需要使用父编码（普通产品、子产品该字段有值）
        searchparam.put("prodId", kfgg.getpProdId());
        List<Map<String,String>> list = reportKfqgg001Mapper.queryKfqjh(searchparam);
        if(list == null){
            list = new ArrayList<>();
        }
        dataMap.put("openList", list);
        return dataMap;
    }
}
