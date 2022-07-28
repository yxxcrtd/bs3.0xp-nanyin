
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.common.DisReportType;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportRkcpjz001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportRkcpjz001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportValueDto;
import com.joyintech.tams.disclosure.information.report.util.ReportCollectionUtils;
import com.joyintech.tams.disclosure.rptdata.service.IReportDataService;
import com.joyintech.tams.utils.echarts.EchartsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        //phantomjs、生成图片临时路径:  TODO　后续更改成系统参数获取
//        String echartsCmdPath = "D:\\soft\\phantomjs-2.1.1-windows\\bin\\";
        String echartsCmdPath = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_PIC_EXPORT_PATH);
        String tmpImagePath = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_TMPFILE_PATH);
//        String tmpImagePath = "e:\\poitest\\temp\\";

        List<Map<String, Object>> resultMap = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            //产品代码：子产品代码为空的话，取父产品代码
            String prodId =  (paraMap.get("prodid") == null ? (String)paraMap.get("pprodid") : (String)paraMap.get("prodid"));
            //净值日
            String valueDate = (String)paraMap.get("enddate");
            //判断是 日开产品净值公告（公募净值型产品） 还是 日开产品净值公告（公募现管类产品） 的模板
            String rptType = (String)paraMap.get("uri");

            if(StringUtil.isNotEmpty(prodId)&&StringUtil.isNotEmpty(valueDate)){
                Map<String, Object> searchparam = new HashMap<>();
                searchparam.put("prodId",prodId);
                searchparam.put("valueDate",valueDate);
                //查询产品信息
                ReportRkcpjz001Dto reportRkcpjz001Dto = reportRkcpjz001Mapper.getProdInfo(searchparam);
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
                reportRkcpjz001Dto.setAnnounceT(DateUtil.format(announceDate,DateUtil.ABB_DATE_FORMAT_CN));

                //查询净值数据:现管型产品需要展示多条净值数据： 根据上交所日历判断，对于每周第一个工作日生成的净值公告，此部分需要披露自上一工作日至当前第一个工作日-1自然日的净值信息
                List<ReportValueDto> list = new ArrayList<>();
                if(StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)){
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
                }else{//净值型产品：展示一条：今日份额净值及申购赎回价格
                    list = reportRkcpjz001Mapper.getJzxValueList(searchparam);
                }
                reportRkcpjz001Dto.setList(list);

                dataMap = ReportCollectionUtils.bean2Map(reportRkcpjz001Dto);

                //近一个月（工作日）份额净值走势图或近一个月(自然日)万份收益走势图
                String imagePath1 = tmpImagePath+"image1"+"_"+prodId+"_"+valueDate;
                //近一个月（工作日）份额累计净值走势图或近一个月份(自然日)七日年化收益率走势图
                String imagePath2 = tmpImagePath+"image2"+"_"+prodId+"_"+valueDate;
                //折线图配置信息
                Map<String, Object> configMap = new HashMap<>();
                configMap.put("series.type", "bar");
                if(StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)){
                    configMap.put("title.text", "万份收益（元）走势图");
                }else{
                    configMap.put("title.text", "份额净值（元）走势图");
                }
                configMap.put("title.left", "center");
                configMap.put("title.top", "");
                configMap.put("title.textStyle.fontSize", "10");
                configMap.put("title.textStyle.fontWeight", "normal");
                configMap.put("title.textStyle.color", "#595959");
                configMap.put("legend.left", "center");
                configMap.put("legend.top", "auto");
                configMap.put("legend.bottom", "1%");
                configMap.put("xAxis.type", "category");
                configMap.put("xAxis.boundaryGap", "");
                configMap.put("xAxis.axisLabel.rotate", 40);//设置x轴转的角度
                configMap.put("xAxis.axisLabel.interval", 0);//设置x轴数据全部显示
                configMap.put("yAxis.type", "value");
                configMap.put("yAxis.position", "left");
                configMap.put("yAxis.splitNumber", "30");//设置y轴最多显示个数，不一定会显示


                searchparam.put("rptType",rptType);
                //曲线图数据对象
                List<List<String>> dataList = new ArrayList<>();
                //查询小于等于当前最新净值日期的过去30个工作日的单位净值折线图   份额净值
                List<ReportGrahDto> grahDtoList = reportRkcpjz001Mapper.getValues(searchparam);
                //排序
                grahDtoList = grahDtoList.stream().sorted(Comparator.comparing(ReportGrahDto::getcDate)).collect(Collectors.toList());
                //图例赋值
                List<String> single = new ArrayList<>();
                single.add("日期");
                single.add("");
                dataList.add(single);

                //近一个月（工作日）份额净值走势图或近一个月(自然日)万份收益走势图
                for (ReportGrahDto grahDto : grahDtoList) {
                    List<String> row = new ArrayList<>();
                    row.add(grahDto.getcDate());
                    row.add(grahDto.getValue());
                    dataList.add(row);
                }
                EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, imagePath1, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);

                Map<String, Object> prodValueChart = new HashMap<String, Object>();
                prodValueChart.put("width", 500);
                prodValueChart.put("height", 200);
                prodValueChart.put("type", "png");
                prodValueChart.put("content", imagePath1 + ".png");
                dataMap.put("${@image:image1}", prodValueChart);

                //清空list，重新绘制份额累计净值（元）走势图
                dataList.clear();
                //重新添加标题
                if(StringUtil.equals(rptType, DisReportType.RKCPJZ_GMXGL)){
                    configMap.put("title.text", "七日年化收益率（%）走势图");
                }else{
                    configMap.put("title.text", "份额累计净值（元）走势图");
                }
                //添加横纵坐标说明
                dataList.add(single);
                //份额累计净值曲线数据
                for (ReportGrahDto grahDto : grahDtoList) {
                    List<String> row = new ArrayList<>();
                    row.add(grahDto.getcDate());
                    row.add(grahDto.getTotalValue());
                    dataList.add(row);
                }

                EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, imagePath2, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);

                Map<String, Object> prodTotalValueChart = new HashMap<String, Object>();
                prodTotalValueChart.put("width", 500);
                prodTotalValueChart.put("height", 200);
                prodTotalValueChart.put("type", "png");
                prodTotalValueChart.put("content", imagePath2 + ".png");
                dataMap.put("${@image:image2}", prodTotalValueChart);
            }
            resultMap.add(dataMap);
            logger.info("日开产品净值公告（公募净值型产品）【{}】生成成功", JsonUtils.object2Json(paraMap));
        } catch (Exception e) {
            logger.error("日开产品净值公告（公募净值型产品）【{}】生成异常", JsonUtils.object2Json(paraMap), e);
        }
        return resultMap;
    }
}
