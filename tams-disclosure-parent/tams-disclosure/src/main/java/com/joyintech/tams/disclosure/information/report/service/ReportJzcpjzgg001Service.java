
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportJzcpjzgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import com.joyintech.tams.utils.echarts.EchartsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 日开产品净值公告（公募净值型产品）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportJzcpjzgg001Service
 * @since 1.0
 */
@Service(value = "ReportJzcpjzgg001Service")
public class ReportJzcpjzgg001Service implements IReportDataService {

    @Autowired
    private ReportJzcpjzgg001Mapper reportJzcpjzgg001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 日开产品净值公告（公募净值型产品）
     * 1.适用于“募集方式”为“公募”、“开放频率”为“日开”且“产品类型”为“净值型产品”的产品；
     * 2.公告生成日期：根据上交易所日历，在最新净值日期+1工作日上午8：00之前生成；
     * 3.生成规则：
     * 根据上交易所日历，需要生成小于等于最新净值日期的过去30个工作日的数据;
     * 生成公告时，若取不到所需的净值数据，需要在【报表数据校验】处给出提示，例：2022年7月19日对2022年7月18日的净值信息进行披露时，若7月18日的净值信息还未接入进系统，需要在【报表数据校验】处给出提示；
     * 若产品是分级产品，则需要以子产品维度生成数据，公告中的净值相关信息展示子产品的净值；
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("日开产品净值公告（公募净值型产品）【{}】开始生成", JsonUtils.object2Json(paraMap));

        List<Map<String, Object>> resultMap = new ArrayList<>();

        Map<String, Object> dataMap = new HashMap<>();

        //设置报表初始值
        dataMap.put("TITLENAME", "");
        dataMap.put("VALUEYEAR", "");
        dataMap.put("VALUEMONTH", "");
        dataMap.put("VALUEDAY", "");
        dataMap.put("PRODNAME", "");
        dataMap.put("REGCODE", "");
        dataMap.put("PRODID", "");
        dataMap.put("ANNOUNCEYEAR", "");
        dataMap.put("ANNOUNCEMONTH", "");
        dataMap.put("ANNOUNCEDAY", "");
        dataMap.put("valueList", new ArrayList<Map<String, String>>());
        dataMap.put("${@image:prod_yield_line}", "");
        dataMap.put("${@image:prod_sevenYield_line}", "");

        resultMap.add(dataMap);

        if (paraMap.get("prodid") == null || paraMap.get("enddate") == null) {
            return resultMap;
        }

        try {
            //TODO　后续更改成系统参数获取  创建图片
            String echartsCmdPath = "D:\\soft\\phantomjs-2.1.1-windows\\bin\\";  //从系统参数里面获取
            String prodValueLinePathName = "e:\\poitest\\temp\\prodValueLine";
            String prodTotalvalueLinePathName = "e:\\poitest\\temp\\prodTotalvalueLine";

            String prodid = (String) paraMap.get("prodid");
            //净值日期
            String valueDateStr = (String) paraMap.get("enddate");
            Date valueDate = DateUtil.parse(valueDateStr);

            /**
             * 查询产品表相关数据
             * 返回信息：PRODID，TITLENAME，PRODNAME，REGCODE
             */
            Map<String, Object> prodMap = reportJzcpjzgg001Mapper.getProdInfo(prodid);

            if (prodMap != null) {
                dataMap.putAll(prodMap);
            }
            //设置净值日期
            String[] valueDateArray = valueDateStr.split("-");
            dataMap.put("VALUEYEAR", valueDateArray[0]);
            dataMap.put("VALUEMONTH", valueDateArray[1]);
            dataMap.put("VALUEDAY", valueDateArray[2]);

            //查询公告日 公告日=净值日期+1工作日
            Map<String, Object> holidaysearchparam = new HashMap<String, Object>();
            holidaysearchparam.put("busi_date", valueDate);
            holidaysearchparam.put("is_holiday", "0");
            holidaysearchparam.put("market_code", "04");
            Date announceDate = holidayService.getNextWorkDay(holidaysearchparam);
            if (announceDate == null) {
                announceDate = DateUtil.addDay(valueDate, 1);
            }
            String announceDateStr = DateUtil.format(announceDate);
            String[] announceDateArray = announceDateStr.split("-");
            dataMap.put("ANNOUNCEYEAR", announceDateArray[0]);
            dataMap.put("ANNOUNCEMONTH", announceDateArray[1]);
            dataMap.put("ANNOUNCEDAY", announceDateArray[2]);

            //根据净值日期查询最新的日期的净值数据
            Map<String, Object> searchParam = new HashMap<>();
            searchParam.put("prodId", prodid);
            searchParam.put("valueDate", valueDate);
            //TODO 赎回价格未弄，先出成跟申购价格一样，后续产品基本信息表有 固定持有天数字段  再处理
            //查询净值日的净值数据
            Map<String, Object> valueMap = reportJzcpjzgg001Mapper.getLastValue(searchParam);

            if (valueMap != null) {
                dataMap.putAll(valueMap);
            }

            //生成统计图
            Map<String, Object> configMap = new HashMap<>();

            configMap.put("series.type", "bar");
            // 标题
            configMap.put("title.text", "份额净值（元）走势图");
            configMap.put("title.left", "center");
            configMap.put("title.top", "");
            configMap.put("title.textStyle.fontSize", "10");
            configMap.put("title.textStyle.fontWeight", "normal");
            configMap.put("title.textStyle.color", "#595959");
            configMap.put("legend.left", "center");
            configMap.put("legend.top", "auto");
            configMap.put("legend.bottom", "1%");
//        configMap.put("xAxis.name", "日期");
            configMap.put("xAxis.type", "category");
            configMap.put("xAxis.boundaryGap", "");
            //设置x轴转的角度
            configMap.put("xAxis.axisLabel.rotate", 40);
            //设置x轴数据全部显示
            configMap.put("xAxis.axisLabel.interval", 0);
//        configMap.put("yAxis.name", "值");
            configMap.put("yAxis.type", "value");
            configMap.put("yAxis.position", "left");
            //设置y轴最多显示个数，不一定会显示
            configMap.put("yAxis.splitNumber", "30");

            //曲线图数据对象
            List<List<String>> dataList = new ArrayList<>();
            //查询小于等于当前最新净值日期的过去30个工作日的单位净值折线图   份额净值
            List<ReportGrahDto> grahDtoList = reportJzcpjzgg001Mapper.getValues(searchParam);
            //排序
            grahDtoList = grahDtoList.stream().sorted(Comparator.comparing(ReportGrahDto::getcDate)).collect(Collectors.toList());

            //图例赋值
            List<String> single = new ArrayList<>();
            single.add("日期");
            single.add("");
            dataList.add(single);

            //份额净值曲线数据
            for (ReportGrahDto grahDto : grahDtoList) {
                List<String> row = new ArrayList<>();
                row.add(grahDto.getcDate());
                row.add(grahDto.getValue());
                dataList.add(row);
            }

            EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, prodValueLinePathName, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);


            Map<String, Object> prodValueChart = new HashMap<String, Object>();
            prodValueChart.put("width", 500);
            prodValueChart.put("height", 200);
            prodValueChart.put("type", "png");
            prodValueChart.put("content", prodValueLinePathName + ".png");
            dataMap.put("${@image:prod_value_line}", prodValueChart);

            //清空list，重新份额累计净值（元）走势图
            dataList.clear();
            //重新添加标题
            configMap.put("title.text", "份额累计净值（元）走势图");
            //添加横纵坐标说明
            dataList.add(single);
            //份额累计净值曲线数据
            for (ReportGrahDto grahDto : grahDtoList) {
                List<String> row = new ArrayList<>();
                row.add(grahDto.getcDate());
                row.add(grahDto.getTotalValue());
                dataList.add(row);
            }

            EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, prodTotalvalueLinePathName, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);


            Map<String, Object> prodTotalValueChart = new HashMap<String, Object>();
            prodTotalValueChart.put("width", 500);
            prodTotalValueChart.put("height", 200);
            prodTotalValueChart.put("type", "png");
            prodTotalValueChart.put("content", prodTotalvalueLinePathName + ".png");
            dataMap.put("${@image:prod_totalvalue_line}", prodTotalValueChart);
            logger.info("日开产品净值公告（公募净值型产品）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("日开产品净值公告（公募净值型产品）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
