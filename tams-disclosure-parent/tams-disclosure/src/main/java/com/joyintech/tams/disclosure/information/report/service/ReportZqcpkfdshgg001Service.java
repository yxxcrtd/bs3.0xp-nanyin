
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportZqcpkfdshgg001Mapper;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 周期型产品开放公告（公募产品-单赎回）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportZqcpkfdshgg001Service
 * @since 1.0
 */
@Service(value = "ReportZqcpkfdshgg001Service")
public class ReportZqcpkfdshgg001Service implements IReportDataService {

    @Autowired
    private ReportZqcpkfdshgg001Mapper reportZqcpkfdshgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     1.适用于“募集方式”为“公募”且“是否是客户周期型产品”为“是”的产品；
     2.公告生成时点：自动赎回日上午8：00之前；
     3.生成规则：
     客户周期型产品的自动赎回日大多是后续某一开放周期的运作起始日，根据产品排期表进行判断，若某一自动赎回日不是该产品未来某开放周期的运作起始日，则需要为该条赎回信息生成此公告；
     若产品是分级产品，需要按照子产品维度生成报表，报表中历史开放信息中的净值相关字段按照子产品维度来取值；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("周期型产品开放公告（公募产品-单赎回）【{}】开始生成", JsonUtils.object2Json(paraMap));

        List<Map<String, Object>> resultMap = new ArrayList<>();

        Map<String, Object> dataMap = new HashMap<String, Object>();

        //设置报表初始值
        dataMap.put("TITLENAME", "");
        dataMap.put("PRODNAME", "");
        dataMap.put("REGCODE", "");
        dataMap.put("PRODID", "");
        dataMap.put("ANNOUNCEYEAR", "");
        dataMap.put("ANNOUNCEMONTH", "");
        dataMap.put("ANNOUNCEDAY", "");
        dataMap.put("SETUPYEAR", "");
        dataMap.put("SETUPMONTH", "");
        dataMap.put("SETUPDAY", "");
        dataMap.put("openList", new ArrayList<Map<String, String>>());
        dataMap.put("openHisList", new ArrayList<Map<String, String>>());

        resultMap.add(dataMap);

        if (paraMap.get("prodid") == null || paraMap.get("startdate") == null) {
            return resultMap;
        }

        try {
            String portnum = (String) paraMap.get("prodid");
            //本期运作起始日
            String endDateStr = (String) paraMap.get("startdate");
            Date endDate = DateUtil.parse(endDateStr);

            String[] endDateArray = endDateStr.split("-");
            dataMap.put("ANNOUNCEYEAR", endDateArray[0]);
            dataMap.put("ANNOUNCEMONTH", endDateArray[1]);
            dataMap.put("ANNOUNCEDAY", endDateArray[2]);

            /**
             * 查询产品表相关数据
             * 返回信息：PRODID，TITLENAME，PRODNAME，REGCODE,SETDATE
             */
            Map<String, Object> prodMap = reportZqcpkfdshgg001Mapper.getProdInfo(portnum);
            if (prodMap != null) {
                dataMap.putAll(prodMap);
            }

            if (StringUtil.isNotEmpty(dataMap.get("SETDATE"))) {
                Date setDate = (Date) dataMap.get("SETDATE");
                String setDateStr = DateUtil.format(setDate);
                String[] setDateArray = setDateStr.split("-");
                dataMap.put("SETUPYEAR", setDateArray[0]);
                dataMap.put("SETUPMONTH", setDateArray[1]);
                dataMap.put("SETUPDAY", setDateArray[2]);
            }

            //根据净值日期查询最新的日期的净值数据
            Map<String, Object> searchParam = new HashMap<>();
            //排期表需要按母产品id来查
            searchParam.put("prodId", dataMap.get("PRODID"));
            searchParam.put("pprodId", dataMap.get("PPRODID"));
            searchParam.put("endDate", endDate);
            //平台日
            Date plateDate = DateUtil.platDateFromCache();
            searchParam.put("platDate", plateDate);

            List<Map<String, String>> list = reportZqcpkfdshgg001Mapper.queryOpenList(searchParam);
            //如果查出来未空，需要对应内容置空，业务老师手工维护
            if (list == null || list.size() == 0) {
                list = new ArrayList<>();
                Map<String, String> emptyMap = new HashMap<>();
                emptyMap.put("OPENVDATE","XXXX/XX/XX-XXXX/XX/XX");
                emptyMap.put("VDATE","XXXX-XX-XX");
                emptyMap.put("RDATE","XXXX-XX-XX");
                emptyMap.put("HOLDDAYS","XX");
                list.add(emptyMap);
            }
            dataMap.put("openList", list);
            //查询历史开放信息
            List<Map<String, Object>> openHisList = reportZqcpkfdshgg001Mapper.queryOpenHisList(searchParam);
            if (list != null) {
                //根据每个区间去查各区间的净值数据
                for(Map<String,Object> histMap :openHisList){
                    //周期运作起始日
                    Date vDate = DateUtil.parse((String)histMap.get("VDATE"));
                    //根据周期运作起始日去查上一工作日的净值数据
                    searchParam.put("busi_date", vDate);
                    searchParam.put("is_holiday", "0");
                    searchParam.put("market_code", "04");
                    Date lastWorkDay = holidayService.getLastWorkDay(searchParam);
                    if (lastWorkDay == null) {
                        lastWorkDay = DateUtil.addDay(vDate, -1);
                    }
                    searchParam.put("lastWorkDay",lastWorkDay);
                    Map<String, Object> valueMap =  reportZqcpkfdshgg001Mapper.queryValueMap(searchParam);
                    //单位净值
                    BigDecimal value = null;
                    //累计单位净值
                    BigDecimal totalValue = null;
                    //赎回确认净值
                    BigDecimal shValue = null;

                    if(valueMap != null){
                        value = (BigDecimal) valueMap.get("VALUE");
                        totalValue = (BigDecimal) valueMap.get("TOTALVALUE");
                        /**
                         * 当自动赎回日小于本周期“运作起始日”时，会有赎回确认单位净值，需要结合超额业绩报酬表进行判断，
                         * 若超额业绩报酬为0，则赎回确认单位净值=申购确认单位净值；若超额业绩报酬不为0，则用确认金额/确认份额的值按照截位法保留4位小数；
                         * 若自动赎回日大于当前平台日，赎回确认单位净值（元）/赎回价格（元/份）置空；
                         */
                        //自动赎回日
                        Date rDate = DateUtil.parse((String)valueMap.get("RDATE"));
                        //超额业绩报酬
                        BigDecimal aboveAmt = (BigDecimal) valueMap.get("ABOVE_AMT");
                        //确认金额
                        BigDecimal AFFIRM_AMT =(BigDecimal) histMap.get("AFFIRM_AMT");
                        //确认份额
                        BigDecimal AFFIRM_NUM =(BigDecimal) histMap.get("AFFIRM_NUM");
                        //自动赎回日小于平台日
                        if(rDate.compareTo(plateDate) < 0){
                            //超额业绩报酬为0  赎回确认单位净值=申购确认单位净值
                            if(aboveAmt != null && aboveAmt.compareTo(BigDecimal.ZERO)  == 1){
                                shValue = value;
                            }else{
                                if(AFFIRM_AMT !=null && AFFIRM_NUM != null){
                                    shValue = AFFIRM_AMT.divide(AFFIRM_NUM).setScale(4, RoundingMode.HALF_UP);
                                }
                            }

                        }else {
                            shValue = null;
                        }
                    }
                    histMap.put("SHVALUE",shValue);
                    histMap.put("VALUE",value);
                    histMap.put("TOTALVALUE",totalValue);
                }
                dataMap.put("openHisList", openHisList);
            }
            logger.info("周期型产品开放公告（公募产品-单赎回）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("周期型产品开放公告（公募产品-单赎回）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
