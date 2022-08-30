package com.joyintech.tams.utils.echarts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.utils.echarts.dto.*;
import com.joyintech.tams.utils.poi.PoiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * echarts报表图片服务类
 * 
 * @author zhangyf
 */
@Service
public class EchartsUtils {
	
	public static final String ECHARTS_图表类型_曲线图 = "line";
	public static final String ECHARTS_图表类型_柱状图 = "bar";
	public static final String ECHARTS_图表类型_饼状图 = "pie";
	
	//private Logger logger = LoggerFactory.getLogger(EchartsUtils.class);
	
	/**
	 * 报表图片分类处理异步方法 （目前仅支持：曲线图、柱状/条形图、饼图）
	 * 
	 * @param configMap echarts配置
	 * @param dataList echarts业务数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @param chartType 报表类型
	 * @return 图表文件（图片）
	 */

	public static File buildEChartsPicture4ChartType(Map<String, Object> configMap, List<List<String>> dataList,
			String echartsFilePathName, String chartType,String echartsCmdPath) throws EchartsException {
		if (StringUtils.isEmpty(chartType) || CollectionUtils.isEmpty(dataList)) {
			return null;
		}
		configMap.put("series.type", chartType);
		if (StringUtil.equals(chartType, ECHARTS_图表类型_曲线图)) {
			return buildEChartsPicture4Line(configMap, dataList, echartsFilePathName,echartsCmdPath);
		} else if (StringUtil.equals(chartType, ECHARTS_图表类型_柱状图)) {
			return buildEChartsPicture4Bar(configMap, dataList, echartsFilePathName,echartsCmdPath);
		} else if (StringUtil.equals(chartType, ECHARTS_图表类型_饼状图)) {
			return buildEChartsPicture4Pie(configMap, dataList, echartsFilePathName,echartsCmdPath);
		}
		return null;
	}
	
	/**
	 * 报表图片处理-曲线图
	 * 
	 * @param configMap echarts配置
	 * @param dataList echarts业务数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @return 图表文件（图片）
	 */
	public static File buildEChartsPicture4Line(Map<String, Object> configMap, List<List<String>> dataList,String echartsFilePathName,String echartsCmdPath) throws EchartsException {
		// 图例集合
		List<String> legend_data = new ArrayList<>();
		// 图例临时map
		Map<Integer, String> legendName = new HashMap<>();
		// 图例对应的数据集合
		Map<String, List<String>> legendData = new HashMap<>();
		// 遍历业务数据，获取图例、各图例数
		List<String> row = null;
		for (int i = 0; i < dataList.size(); i++) {
			row = dataList.get(i);
			for (int j = 0; j < row.size(); j++) {
				// 第一行为图例
				if (i == 0) {
					legend_data.add(row.get(j));
					legendName.put(j, row.get(j));
					legendData.put(row.get(j), new ArrayList<String>());
				} else {
					legendData.get(legendName.get(j)).add(row.get(j));
				}
			}
		}
		// 业务数据
		Map<String, Object> dataMap = new HashMap<>();
		// 图例
		dataMap.put("legend.data", CollectionUtils.isEmpty(legend_data) ? null : legend_data.toArray());
		// 横坐标
		dataMap.put("xAxis.data", CollectionUtils.isEmpty(legendData.get(legendName.get(0))) ? null : legendData.get(legendName.get(0)).toArray());
		// 数据集合
		List<Series> seriesList = new ArrayList<Series>();
		String series_type = ECHARTS_图表类型_曲线图;
		if (!StringUtils.isEmpty(configMap.get("series.type")))
			series_type = obj2str(configMap.get("series.type"));
		String series_symbol = "none";
		if (!StringUtils.isEmpty(configMap.get("series.symbol")))
			series_symbol = obj2str(configMap.get("series.symbol"));
		for(String legend: legendData.keySet()) {
			if(StringUtil.equals(legend, legendName.get(0)))
				continue;
			seriesList.add(new Series(legend, series_type, true, series_symbol,legendData.get(legend).toArray()));
		}
		dataMap.put("series", seriesList);
		return buildEChartsPicture(configMap,dataMap,echartsFilePathName,echartsCmdPath);
	}
	
	/**
	 * 报表图片处理-柱状图
	 * 
	 * @param configMap echarts配置
	 * @param dataList echarts业务数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @return 图表文件（图片）
	 */
	public static File buildEChartsPicture4Bar(Map<String, Object> configMap, List<List<String>> dataList,String echartsFilePathName,String echartsCmdPath) throws EchartsException {
		// 逻辑同曲线图
		return buildEChartsPicture4Line(configMap, dataList, echartsFilePathName,echartsCmdPath);
	}
	
	/**
	 * 报表图片处理-饼状图
	 * 
	 * @param configMap echarts配置
	 * @param dataList echarts业务数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @return 图表文件（图片）
	 */
	public static File buildEChartsPicture4Pie(Map<String, Object> configMap, List<List<String>> dataList,String echartsFilePathName,String echartsCmdPath) throws EchartsException {
		// 图例集合
		List<String> legend_data = new ArrayList<>();
		// 数据集合
		List<NvObject> series_data = new ArrayList<>();
		List<String> row = null;
		for (int i = 0; i < dataList.size(); i++) {
			if (i != 0) {
				row = dataList.get(i);
				for (int j = 0; j < row.size(); j++) {
					// 第一列为图例
					if (j == 0) {
						legend_data.add(row.get(j));
					} else if (j == 1) {
						series_data.add(new NvObject(row.get(0), row.get(j)));
					}
				}
			}
		}
		// 业务数据组装
		Map<String, Object> dataMap = new HashMap<>();
		// 图例
		dataMap.put("legend.data", CollectionUtils.isEmpty(legend_data) ? null : legend_data.toArray());
		// 数据集合
		List<Series> seriesList = new ArrayList<Series>();
		String series_type = ECHARTS_图表类型_曲线图;
		if (!StringUtils.isEmpty(configMap.get("series.type")))
			series_type = obj2str(configMap.get("series.type"));
		seriesList.add(new Series("", series_type, true,null,CollectionUtils.isEmpty(series_data) ? null : series_data.toArray()));
		dataMap.put("series", seriesList);
		return buildEChartsPicture(configMap,dataMap,echartsFilePathName,echartsCmdPath);
	
	}
	/**
	 * 创建echarts报表图片-主入口
	 * 
	 * @param configMap echarts配置
	 * @param dataMap echarts业务数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @return 图表文件（图片）
	 */
	public static File buildEChartsPicture(Map<String, Object> configMap, Map<String, Object> dataMap,String echartsFilePathName,String echartsCmdPath) throws EchartsException {
		long start = System.currentTimeMillis();
		// 创建echarts数据
		String option = buildEChartOption(configMap,dataMap);
		// 创建图片
		File picture = buildEChartFile(option, echartsFilePathName,echartsCmdPath);
		long end = System.currentTimeMillis();
		//logger.debug("耗时（毫秒）：" + picture.getPath() + " = " + (end - start));
		return picture;
	}
	
	/**
	 * 创建echarts数据对象
	 * 
	 * @param configMap echarts配置数据
	 * @param dataMap echarts业务数据
	 * @return echarts option对象
	 */
	@SuppressWarnings("unchecked")
	private static String buildEChartOption(Map<String, Object> configMap, Map<String, Object> dataMap) {
		// 图表类型
		String series_type = obj2str(configMap.get("series.type"));
		// 标题
		String title_text = obj2str(configMap.get("title.text"));
		String title_subtext = obj2str(configMap.get("title.subtext"));
		String title_left = obj2str(configMap.get("title.left"));
		if (StringUtils.isEmpty(title_left))
			title_left = "center";// 默认居中
		String title_top = obj2str(configMap.get("title.top"));
		String title_textStyle_fontSize = obj2str(configMap.get("title.textStyle.fontSize"));
		if (StringUtils.isEmpty(title_textStyle_fontSize))
			title_textStyle_fontSize = "14";// 默认 14
		String title_textStyle_fontWeight = obj2str(configMap.get("title.textStyle.fontWeight"));
		if (StringUtils.isEmpty(title_textStyle_fontWeight))
			title_textStyle_fontWeight = "normal";// 默认不加粗
		String title_textStyle_color = obj2str(configMap.get("title.textStyle.color"));
		if (StringUtils.isEmpty(title_textStyle_color))
			title_textStyle_color = "#595959";// 默认灰色
		// 图例
		String legend_left = obj2str(configMap.get("legend.left"));
		if (StringUtils.isEmpty(legend_left))
			legend_left = "center";// 默认居中
		String legend_top = obj2str(configMap.get("legend.top"));
		if (StringUtils.isEmpty(legend_top))
			legend_top = "auto";// 默认自动
		String legend_bottom = obj2str(configMap.get("legend.bottom"));
		if (StringUtils.isEmpty(legend_bottom))
			legend_bottom = "1%";// 默认距离底部1%
		Object[] legend_data = null;
		if (StringUtil.isNotEmpty(dataMap.get("legend.data")) && (dataMap.get("legend.data") instanceof Object[]))
			legend_data = (Object[]) dataMap.get("legend.data");
		// 横坐标
		String xAxis_name = obj2str(configMap.get("xAxis.name"));
		String xAxis_type = obj2str(configMap.get("xAxis.type"));
		if (StringUtils.isEmpty(xAxis_type))
			xAxis_type = "category";// 默认类目轴
		Object xAxis_boundaryGap = configMap.get("xAxis.boundaryGap");
		String xAxis_axisLabel_rotate = obj2str(configMap.get("xAxis.axisLabel.rotate"));
		if (StringUtils.isEmpty(xAxis_axisLabel_rotate))
			xAxis_axisLabel_rotate = "";// 默认不设置
		//设置X轴的刻度
		int xAxis_axisLabel_interval = 0;
		if(configMap.get("xAxis.axisLabel.interval") != null){
			xAxis_axisLabel_interval = (Integer) configMap.get("xAxis.axisLabel.interval");
		}
		//横坐标显示刻度样式
		String xAxis_axisLabel_formatter = obj2str(configMap.get("xAxis.axisLabel.formatter"));
		if (StringUtils.isEmpty(xAxis_axisLabel_formatter))
			xAxis_axisLabel_formatter = null;// 默认为空
		//横坐标显示字体大小
		String xAxis_axisLabel_fontSize = obj2str(configMap.get("xAxis.axisLabel.fontSize"));
		if (StringUtils.isEmpty(xAxis_axisLabel_fontSize))
			xAxis_axisLabel_fontSize = "";// 默认为空
		//横坐标显示字体大小
		String xAxis_axisLabel_fontWeight = obj2str(configMap.get("xAxis.axisLabel.fontWeight"));
		if (StringUtils.isEmpty(xAxis_axisLabel_fontWeight))
			xAxis_axisLabel_fontWeight = "";// 默认为空

		Object[] xAxis_data = null;
		if (StringUtil.isNotEmpty(dataMap.get("xAxis.data")) && (dataMap.get("xAxis.data") instanceof Object[]))
			xAxis_data = (Object[]) dataMap.get("xAxis.data");

		// 纵坐标
		String yAxis_name = obj2str(configMap.get("yAxis.name"));
		String yAxis_type = obj2str(configMap.get("yAxis.type"));
		if (StringUtils.isEmpty(yAxis_type))
			yAxis_type = "value";// 默认数值轴
		String yAxis_position = obj2str(configMap.get("yAxis.position"));
		if (StringUtils.isEmpty(yAxis_position))
			yAxis_position = "left";// 默认居左
		boolean yAxis_scale = true; // 默认不包含零刻度
		if (null != configMap.get("yAxis.scale"))
			yAxis_scale = (boolean)configMap.get("yAxis.scale");
		String yAxis_axisLine_lineStyle_color = obj2str(configMap.get("yAxis.axisLine.lineStyle.color"));
		if (StringUtils.isEmpty(yAxis_axisLine_lineStyle_color))
			yAxis_axisLine_lineStyle_color = "#595959";// 默认灰色
		String yAxis_axisLine_lineStyle_type = obj2str(configMap.get("yAxis.axisLine.lineStyle.type"));
		if (StringUtils.isEmpty(yAxis_axisLine_lineStyle_type))
			yAxis_axisLine_lineStyle_type = "solid";// 默认实线
		String splitNumber = obj2str(configMap.get("yAxis.splitNumber"));
		if (StringUtils.isEmpty(splitNumber))
			splitNumber = null;
		//纵坐标显示刻度
		String yAxis_axisLabel_formatter = obj2str(configMap.get("yAxis.axisLabel.formatter"));
		if (StringUtils.isEmpty(yAxis_axisLabel_formatter))
			yAxis_axisLabel_formatter = "{value}";// 默认为空
		//纵坐标显示字体大小
		String yAxis_axisLabel_fontSize = obj2str(configMap.get("yAxis.axisLabel.fontSize"));
		if (StringUtils.isEmpty(yAxis_axisLabel_fontSize))
			yAxis_axisLabel_fontSize = "";// 默认为空
		//横坐标显示字体大小
		String yAxis_axisLabel_fontWeight = obj2str(configMap.get("yAxis.axisLabel.fontWeight"));
		if (StringUtils.isEmpty(yAxis_axisLabel_fontWeight))
			yAxis_axisLabel_fontWeight = "";// 默认为空
		Object yAxis_boundaryGap = configMap.get("yAxis.boundaryGap");

		String yAxis_min = obj2str(configMap.get("yAxis.min"));
		String yAxis_max = obj2str(configMap.get("yAxis.max"));



		// 数据集合
		List<Series> seriesList = null;
		if (StringUtil.isNotEmpty(dataMap.get("series")) && (dataMap.get("series") instanceof List))
			seriesList = (List<Series>) dataMap.get("series");

		// 组装echarts数据对象
		Option option = new Option();
		// 标题
		option.setTitle(new Title(title_text, title_subtext, title_left, title_top,
				new TextStyle(title_textStyle_fontSize, title_textStyle_fontWeight, title_textStyle_color)));
		// 图例（展示：折线/面积图、饼图）
		if (Arrays.asList(new String[] { ECHARTS_图表类型_曲线图, ECHARTS_图表类型_饼状图, ECHARTS_图表类型_柱状图 }).contains(series_type)) {
			option.setLegend(new Legend(legend_left, legend_top, legend_bottom, legend_data));
		}
		// 横坐标（展示：折线/面积图、柱状/条形图）
		if (Arrays.asList(new String[] { ECHARTS_图表类型_曲线图, ECHARTS_图表类型_柱状图 }).contains(series_type)) {
			option.setxAxis(new XAxis(xAxis_name, xAxis_type, xAxis_boundaryGap, new AxisLabel(xAxis_axisLabel_rotate,xAxis_axisLabel_interval,xAxis_axisLabel_formatter,xAxis_axisLabel_fontSize,xAxis_axisLabel_fontWeight),xAxis_data));
		}
		// 纵坐标（展示：折线/面积图、柱状/条形图）
		if (Arrays.asList(new String[] { ECHARTS_图表类型_曲线图, ECHARTS_图表类型_柱状图 }).contains(series_type)) {
			List<YAxis> yAxisList = new ArrayList<YAxis>();
			yAxisList.add(new YAxis(yAxis_name, yAxis_type, yAxis_position,yAxis_scale,splitNumber,
					new AxisLine(new LineStyle(yAxis_axisLine_lineStyle_color, yAxis_axisLine_lineStyle_type)),new AxisLabel(null,0,yAxis_axisLabel_formatter,yAxis_axisLabel_fontSize,yAxis_axisLabel_fontWeight),yAxis_boundaryGap,yAxis_min,yAxis_max));
			option.setyAxis(yAxisList);
		}
		// 数据集合
		option.setSeries(seriesList);
		//图表离容器底部的距离
		String grid_bottom = obj2str(configMap.get("grid.bottom"));
		Grid grid = new Grid();
		if(StringUtil.isNotEmpty(grid_bottom)){
			grid.setBottom(grid_bottom);
		}
		option.setGrid(grid);

		String optionStr = JsonUtils.object2Json(option);
		//如果optionStr中存在function ,需要将function前面及后面的"去除，否则echarts画图无法正常显示函数中的动作
		if(yAxis_axisLabel_formatter.contains("function")){
			//查看需要替换的function表达式
			optionStr = optionStr.replace("\""+yAxis_axisLabel_formatter+"\"",yAxis_axisLabel_formatter);
		}
		//logger.debug("echarts数据对象：" + optionStr);
		return optionStr;
	}

	/**
	 * 创建echarts图表文件（图片）
	 * 
	 * @param options 图表数据
	 * @param echartsFilePathName 图表文件全路径和名称
	 * @return 图表文件
	 */
	private static File buildEChartFile(String options, String echartsFilePathName,String echartsCmdPath) throws EchartsException {
		// 生成echarts图片工具存放路径
		//String echartsPicExportPath  = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_PIC_EXPORT_PATH);
		String echartsPicExportPath = echartsCmdPath;
		if (StringUtils.isEmpty(echartsPicExportPath)) {
			//logger.error("未配置poi导出报表之生成echarts图片工具存放路径." );
			return null;
		}
		
		String jsonData = buildEChartOptionsFile(options,echartsFilePathName);
		String echartsPicture = echartsFilePathName + ".png";
		
		File file = new File(echartsPicture); // 文件路径
		if (!file.exists()) {
			File dir = new File(file.getParent());
			dir.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new EchartsException( "buildEChartFile",e.getCause(),"" );
			}
		}
		// 创建phantomjs命令（必须用全路径）
		String phantomjsCmd = echartsPicExportPath + "phantomjs "
				+ echartsPicExportPath  + "echarts-convert.js" + " -infile " + jsonData + " -outfile " + echartsPicture;
		Process process = null;
		//logger.debug("即将执行phantomjs命令：" + phantomjsCmd);
		try {
			// 执行phantomjs命令，生成echarts图片
			process = Runtime.getRuntime().exec(phantomjsCmd);
		} catch (IOException e) {
			throw new EchartsException( "buildEChartFile",e.getCause(),"" );
//			logger.error("执行的phantomjs命令：" + phantomjsCmd);
//			logger.error("执行phantomjs命令异常，请检查phantomjs工具是否安装成功和phantomjs命令语句！");
		} finally {
			if (process != null) {
				try {
					process.waitFor();
				} catch (InterruptedException e) {
					throw new EchartsException( "buildEChartFile",e.getCause(),"" );
				}
				process.destroy();
			}
		}
		// 删除临时json文件
		File jsondata = new File(jsonData);
		if (jsondata != null && jsondata.exists())
			jsondata.delete();
		//logger.debug("已创建图表：" + file.getPath());
		return file;
	}

	/**
	 * 生成options存储json文件
	 * 
	 * @param option 图表数据
	 * @param echartsFileTmp    
	 * @return 文件
	 */
	private static String buildEChartOptionsFile(String option, String echartsFileTmp) throws EchartsException {
		// Echarts临时json文件
		String dataPath = echartsFileTmp + ".json";
		BufferedWriter out = null;
		try {
			File writename = new File(dataPath);
			if (!writename.exists()) {
				File dir = new File(writename.getParent());
				dir.mkdirs();
				writename.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(writename));
			out.write(option);

		} catch (IOException e) {
			throw new EchartsException( "buildEChartOptionsFile",e.getCause(),"" );
		}finally {
			if (out !=null){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					throw new EchartsException( "buildEChartOptionsFile",e.getCause(),"" );
				}
			}
		}
		return dataPath;
	}
	
	/**
	 * Object转String
	 * @param obj
	 * @return
	 */
	private static String obj2str(Object obj) {
		return StringUtils.isEmpty(obj) ? null : obj.toString();
	}
}