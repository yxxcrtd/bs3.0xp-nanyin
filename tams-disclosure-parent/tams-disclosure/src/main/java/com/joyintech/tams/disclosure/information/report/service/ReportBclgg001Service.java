
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.information.report.dao.ReportBclgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportBclgg001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportFxgg001Dto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 不成立公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportBclgg001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportBclgg001Service extends BaseService<ReportFxgg001Dto> {

    @Autowired
    private ReportBclgg001Mapper reportBclgg001Mapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

   /**
    * 根据产品代码查询不成立公告信息
    * <一句话功能简述>
    * <功能详细描述>
    * @param params
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    public Map<String, Object> findBclgg(Map<String, Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if (params.get("portnum") == null) {
            return dataMap;
        }
        String prodId = params.get("portnum").toString();
        Map<String,Object> searchparam = new HashMap<String,Object>();
        searchparam.put("prodId", prodId);
        ReportBclgg001Dto bclgg = reportBclgg001Mapper.queryBclgg(searchparam);
        if (bclgg == null) {
            return dataMap;
        }
        dataMap.put("${prodName}", bclgg.getProdName());
        dataMap.put("${regCode}", bclgg.getRegCode());
        dataMap.put("${nocreateReson}", bclgg.getNocreateReason());
        String raiseType = bclgg.getRaiseType();
        if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_公募.getValue())) {
            dataMap.put("${pubType}", "公告");
            dataMap.put("${pubString}", "特此公告。");
        } else if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_私募.getValue())) {
            dataMap.put("${pubType}", "报告");
        }
        if (StringUtil.equals(bclgg.getIsSalesAgency(), DictCodeListExt2.是否_是.getValue())) {
            dataMap.put("${saleString1}", "本理财产品代销机构或");
            dataMap.put("${saleString2}", "代销机构及");
        }
        Date puDate = commonPlatformDateService.getSysDate();
        if (null != params.get("endDate")) {
            puDate = (java.sql.Date)params.get("endDate");
        }
        dataMap.put("${pubDate}",DateUtil.format(puDate, DateUtil.DEFAULT_DATE_FORMAT_CN));
        return dataMap;
    }
}
