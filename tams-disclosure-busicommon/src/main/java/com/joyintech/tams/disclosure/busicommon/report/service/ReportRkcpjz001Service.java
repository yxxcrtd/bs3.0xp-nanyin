
package com.joyintech.tams.disclosure.busicommon.report.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.report.dao.ReportRkcpjz001Mapper;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportRkcpjz001Dto;
import com.joyintech.tams.disclosure.busicommon.report.dto.ReportValueDto;
import com.joyintech.tams.disclosure.busicommon.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.IReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.utils.echarts.EchartsUtils;

/**
 * 日开产品净值公告（公募净值型产品）、日开产品净值公告（公募现管类产品）
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportRkcpjz001Service
 * @since 1.0
 */
@Service(value = "ReportRkcpjz001Service")
public class ReportRkcpjz001Service implements IReportDataService {

    @Autowired
    private ReportRkcpjz001Mapper reportRkcpjz001Mapper;

    @Autowired
    private HolidayService holidayService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {
        logger.info("日开产品净值公告（公募净值型产品）【{}】开始生成", JsonUtils.object2Json(paraMap));
        String echartsCmdPath = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_PIC_EXPORT_PATH);
        String tmpImagePath = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_TMPFILE_PATH);
        //产品代码：子产品代码为空的话，取父产品代码
        String prodId =  (paraMap.get("prodid") == null ? (String)paraMap.get("pprodid") : (String)paraMap.get("prodid"));
        //净值日
        String valueDate = (String)paraMap.get("enddate");
        //判断是 日开产品净值公告（公募净值型产品） 还是 日开产品净值公告（公募现管类产品） 的模板
        String rptType = (String)paraMap.get("uri");

        List<Map<String, Object>> resultMap = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<>();
        ReportRkcpjz001Dto reportRkcpjz001Dto = new ReportRkcpjz001Dto();
        //近一个月（工作日）份额净值走势图或近一个月(自然日)万份收益走势图全路径
        String imagePath1 = tmpImagePath + "image1" + "_" + prodId + "_" + valueDate;
        //近一个月（工作日）份额累计净值走势图或近一个月份(自然日)七日年化收益率走势图全路径
        String imagePath2 = tmpImagePath + "image2" + "_" + prodId + "_" + valueDate;
        try {
            if(StringUtil.isNotEmpty(prodId)&&StringUtil.isNotEmpty(valueDate)){
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId", prodId);

                //判断是普通产品还是子产品
                RepProdInfo info=reportRkcpjz001Mapper.getPProdInfo(searchparam);
                if(StringUtil.isNotEmpty(info)){
                    searchparam.put("prodId",info.getProdId());
                }

                searchparam.put("valueDate", valueDate);
                //查询产品信息
                reportRkcpjz001Dto = reportRkcpjz001Mapper.getProdInfo(searchparam);
                //净值日
                reportRkcpjz001Dto.setValueDate(valueDate);
                //查询公告日 公告日=净值日期+1工作日
                searchparam.put("busi_date", DateUtil.parse(valueDate));
                searchparam.put("is_holiday", "0");
                searchparam.put("market_code", "04");
                Date announceDate = holidayService.getNextWorkDay(searchparam);
                if (announceDate == null) {
                    announceDate = DateUtil.addDay(DateUtil.parse(valueDate), 1);
                }
                reportRkcpjz001Dto.setAnnounceT(DateUtil.format(announceDate, DateUtil.DEFAULT_DATE_FORMAT_CN));


                //查询净值数据:现管型产品需要展示多条净值数据： 根据上交所日历判断，对于每周第一个工作日生成的净值公告，此部分需要披露自上一工作日至当前第一个工作日-1自然日的净值信息
                List<ReportValueDto> list = new ArrayList<>();
                if (StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)) {
                    //查询公告日上一工作日
                    searchparam.put("busi_date", announceDate);
                    searchparam.put("is_holiday", "0");
                    searchparam.put("market_code", "04");
                    Date vdate = holidayService.getLastWorkDay(searchparam);
                    if (vdate == null) {
                        vdate = DateUtil.addDay(announceDate, -1);
                    }
                    //前第一个工作日-1自然日
                    Date edate = DateUtil.addDay(announceDate, -1);
                    searchparam.put("prodId", prodId);
                    searchparam.put("vdate", vdate);
                    searchparam.put("edate", edate);
                    list = reportRkcpjz001Mapper.getXgxValueList(searchparam);
                } else {//净值型产品：展示一条：今日份额净值及申购赎回价格
                    list = reportRkcpjz001Mapper.getJzxValueList(searchparam);
                }
                reportRkcpjz001Dto.setList(list);


                searchparam.put("rptType", rptType);
                //曲线图数据对象
                List<List<String>> dataList = new ArrayList<>();
                //查询小于等于当前最新净值日期的过去30个自然日的万分收益
                if (StringUtil.equals(rptType, DisReportType.RKCPJZ_GMJZXCP)) {
                    //关联节假日表，查询小于等于当前净值日其的过去30个工作日
                    searchparam.put("is_holiday", "0");
                }else{
                    searchparam.put("is_holiday", "");
                }
                searchparam.put("busi_date", valueDate);
                searchparam.put("market_code", "04");
                //如果是现管类产品，需要查询小于等于当前净值日的过去30个自然日数据
                List<Date> queryDateList =reportRkcpjz001Mapper.getValueDayList(searchparam);
                searchparam.put("dateList",queryDateList);
                List<ReportGrahDto> grahDtoList = reportRkcpjz001Mapper.getValues(searchparam);
                //排序
                grahDtoList = grahDtoList.stream().sorted(Comparator.comparing(ReportGrahDto::getcDate)).collect(Collectors.toList());
                //图例赋值
                List<String> single = new ArrayList<>();
                single.add("日期");
                single.add("");
                dataList.add(single);

                //近一个月（工作日）份额净值走势图或近一个月(自然日)万份收益走势图
                for (int i = 0; i < grahDtoList.size(); i++) {
                    ReportGrahDto grahDto = grahDtoList.get(i);
                    List<String> row = new ArrayList<>();
                    if(i == 0){
                        row.add("");
                    }else{
                        row.add(grahDto.getcDate());
                    }
                    row.add(grahDto.getValue());
                    dataList.add(row);
                }
                //由于纵坐标要求居中显示，需要设置 "boundaryGap"属性
//                double max = grahDtoList.stream().mapToDouble(value -> Double.valueOf(value.getValue())).max().orElse(1);
//                double min = grahDtoList.stream().mapToDouble(value -> Double.valueOf(value.getValue())).min().orElse(0);
//                BigDecimal endTag = new BigDecimal(min).divide(new BigDecimal(max),4,BigDecimal.ROUND_HALF_UP);
                //折线图配置信息
                Map<String, Object> configMap = getEchartsConfigMap(rptType);
                configMap.put("yAxis.boundaryGap",new double[]{0.5,0.5});
                EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, imagePath1, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
                //清空list，重新绘制份额累计净值（元）走势图
                dataList.clear();
                //重新添加标题
                if (StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)) {
                    configMap.put("title.text", "七日年化收益率（%）走势图");
                    configMap.put("yAxis.axisLabel.formatter", "{value}%");
                } else {
                    configMap.put("title.text", "份额累计净值（元）走势图");
                }
                //添加横纵坐标说明
                dataList.add(single);
                //份额累计净值曲线数据
                for (int i = 0; i < grahDtoList.size(); i++) {
                    ReportGrahDto grahDto = grahDtoList.get(i);
                    List<String> row = new ArrayList<>();
                    if(i == 0){
                        row.add("");
                    }else{
                        row.add(grahDto.getcDate());
                    }
                    row.add(grahDto.getTotalValue());
                    dataList.add(row);
                }
//                 max = grahDtoList.stream().mapToDouble(value -> Double.valueOf(value.getTotalValue())).max().orElse(1);
//                 min = grahDtoList.stream().mapToDouble(value -> Double.valueOf(value.getTotalValue())).min().orElse(0);
//                 endTag = new BigDecimal(min).divide(new BigDecimal(max),4,BigDecimal.ROUND_HALF_UP);
                //折线图配置信息
                configMap.put("yAxis.boundaryGap",new double[]{0.5,0.5});
                EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, imagePath2, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
            }
            logger.info("日开产品净值公告（公募净值型产品）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("日开产品净值公告（公募净值型产品）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        //跑批日
        reportRkcpjz001Dto.setEnddate(valueDate);
        dataMap = ReportCollectionUtils.bean2Map(reportRkcpjz001Dto);
        //近一个月（工作日）份额净值走势图或近一个月(自然日)万份收益走势图
        Map<String, Object> prodValueChart = getImageMap(imagePath1);
        dataMap.put("${@image:image1}", prodValueChart);
        //近一个月（工作日）份额累计净值走势图或近一个月份(自然日)七日年化收益率走势图
        Map<String, Object> prodTotalValueChart = getImageMap(imagePath2);
        dataMap.put("${@image:image2}", prodTotalValueChart);

        resultMap.add(dataMap);
        return resultMap;
    }

    private Map<String, Object> getImageMap(String imagePath) {
        Map<String, Object> prodValueChart = new HashMap<String, Object>();
        prodValueChart.put("width", 450);
        prodValueChart.put("height", 250);
        prodValueChart.put("type", "png");
        prodValueChart.put("content", imagePath + ".png");
        return prodValueChart;
    }

    private Map<String, Object> getEchartsConfigMap(String rptType) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("series.type", "line");
        if (StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)) {
            configMap.put("title.text", "万份收益（元）走势图");
        } else {
            configMap.put("title.text", "份额净值（元）走势图");
        }
        configMap.put("series.symbol","rect");
        configMap.put("title.left", "center");
        configMap.put("title.top", "");
        configMap.put("title.textStyle.fontSize", "20");
        configMap.put("title.textStyle.fontWeight", "bold");
        configMap.put("title.textStyle.color", "#595959");
        configMap.put("legend.left", "center");
        configMap.put("legend.top", "auto");
        configMap.put("legend.bottom", "1%");
        configMap.put("grid.bottom","15%");

        configMap.put("xAxis.type", "category");
        configMap.put("xAxis.boundaryGap", "");
        configMap.put("xAxis.axisLabel.fontSize", "20");
        configMap.put("xAxis.axisLabel.fontWeight", "bold");
        configMap.put("xAxis.axisLabel.rotate", "30");//设置x轴转的角度
        configMap.put("xAxis.axisLabel.interval", 1);//设置x轴数据隔天显示

        configMap.put("yAxis.type", "value");
        configMap.put("yAxis.position", "left");
        configMap.put("yAxis.axisLabel.fontSize", "20");
        configMap.put("yAxis.axisLabel.fontWeight", "bold");
        configMap.put("yAxis.boundaryGap", "");

        configMap.put("yAxis.scale", true);
        return configMap;
    }

    public RepReveal getRepReveal(Map<String, Object> paraMap) {
        RepReveal repReveal = new RepReveal();
        String prodId = (paraMap.get("prodid") == null ? (String) paraMap.get("pprodid") : (String) paraMap.get("prodid"));
        repReveal.setProdid(prodId);

        Map<String, Object> searchparam = new HashMap<>();
        searchparam.put("prodId", prodId);
        RepProdInfo info=reportRkcpjz001Mapper.getProdId(searchparam);
        if(StringUtil.isNotEmpty(info)){
            repReveal.setProdid(info.getpProdId());
        }

        repReveal.setRepId((String) paraMap.get("repId"));
        repReveal.setEndDate( (Date)(paraMap.get("reportDate")));
        return repReveal;
    }
}
