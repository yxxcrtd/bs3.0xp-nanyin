
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.disclosure.information.report.dao.ReportKfqgg001Mapper;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 产品开放期公告/报告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportKfqgg001Service
 * @since 1.0
 */
@Service(value = "ReportKfqgg001Service")
public class ReportKfqgg001Service implements IReportDataService {

    @Autowired
    private ReportKfqgg001Mapper reportKfqgg001Mapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 产品开放期公告/报告
     * 1.若“募集方式”为“公募”，则采用产品开放期公告模板，若“募集方式”为“私募”，则采用产品开放期报告模板；
     * 2.公告生成日期：手工触发生成；
     * 3.生成规则：若产品是分级产品，则需要以子产品维度生成多个公告/报告；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("产品开放期公告/报告【{}】开始生成", JsonUtils.object2Json(paraMap));
        List<Map<String, Object>> resultMap = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //设置初始值
        dataMap.put("TITLENAME", "");
        dataMap.put("YEAR", "");
        dataMap.put("PLATEYEAR", "");
        dataMap.put("PLATEMONTH", "");
        dataMap.put("PLATEDAY", "");
        dataMap.put("openList", new ArrayList<Map<String, String>>());

        resultMap.add(dataMap);
        //默认普通产品跟分级产品都要传入prodid做为查询跳进啊
        if (paraMap.get("prodid") == null || paraMap.get("year") == null) {
            return resultMap;
        }

        try {
            String portnum = (String)paraMap.get("prodid");
            String year = (String)paraMap.get("year");
            dataMap.put("YEAR", year);
            //获取平台日做公告日
            String platfromDateStr = DateUtil.format(DateUtil.platDateFromCache());
            String[] platfromDateArray = platfromDateStr.split("-");
            dataMap.put("PLATEYEAR", platfromDateArray[0]);
            dataMap.put("PLATEMONTH", platfromDateArray[1]);
            dataMap.put("PLATEDAY", platfromDateArray[2]);

            Map<String, Object> searchparam = new HashMap<>();
            searchparam.put("prodId", portnum);

            Map<String, Object> prodMap = reportKfqgg001Mapper.getProdInfo(searchparam);

            if (prodMap != null) {
                dataMap.putAll(prodMap);
            }
            //根据pProdid查询排期表
            searchparam.put("startDate", year + "-01-01");
            searchparam.put("endDate", year + "-12-31");
            //如果是分级产品，排期表只存母产品代码的排期数据，所以需要使用父编码（普通产品、子产品该字段有值）
            searchparam.put("prodId", prodMap.get("PPRODID"));

            List<Map<String, String>> list = reportKfqgg001Mapper.queryKfqjh(searchparam);
            if (list != null) {
                dataMap.put("openList", list);
            }
            logger.info("产品开放期公告/报告【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("产品开放期公告/报告【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
