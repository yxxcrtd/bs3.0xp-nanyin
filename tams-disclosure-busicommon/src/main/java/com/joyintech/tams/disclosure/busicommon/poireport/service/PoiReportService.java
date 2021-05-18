package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.Configure.ConfigureBuilder;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.poireport.dao.PoiReportMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiQueryDto;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiSubDto;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisPoiTabDto;
import com.joyintech.tams.disclosure.busicommon.poireport.echarts.service.EchartsPictureService;
import com.joyintech.tams.disclosure.busicommon.poireport.util.TableRenderPolicy;

/**
 * poi生成报表service
 * 
 * @author zhangyf
 */
@Service
public class PoiReportService {

	private Logger logger = LoggerFactory.getLogger(PoiReportService.class);
			
	@Autowired
	private PoiReportMapper poiReportMapper;
	
	@Autowired
	private EchartsPictureService echartsPictureService;
	
	/**
	 * 生成报表-统一入口
	 * 
	 * @param tabNum 报表id
	 * @param params 报表参数
	 * @return 生成的文件
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public File createReport(String tabNum, Map<String, Object> params) throws IOException{
		// 报表业务数据集合
		Map<String, Object> reportDataMap = new HashMap<>();

		// 1.获取主、子表信息
		// 主表信息
		DisPoiTabDto disPoiTab = poiReportMapper.selectTabInfoByTabNum(tabNum);
		if (null == disPoiTab) {
			return null;
		}
		// 主表拥有的子表集合
		List<DisPoiSubDto> subList = poiReportMapper.selectSubListByTabNum(tabNum);

		// 2.获取子表业务数据 - 图表部分优先处理
		// eCharts子表id及其配置
		Map<String,Map<String, Object>> subIdAndConfig = new HashMap<>();
		for (DisPoiSubDto sub : subList) {
			// word文档
			if (StringUtil.equals(DictCodeListExt3.poi模板类型_WORD.getValue(), sub.getSubType())) {
				// 图表
				if (StringUtil.equals(DictCodeListExt3.poi表格类型_图表.getValue(), sub.getSubFormType())) {
					// 图表业务参数
					List<List<String>> dataList = buildTableDataForColumns(sub, params);
					if (CollectionUtils.isEmpty(dataList)) {
						logger.debug("未查询到子表数据，主表id=" + tabNum + ",子表id=" + sub.getId());
						continue;
					}
					// 图表配置参数
					Map<String, Object> configMap = new HashMap<>();
					if (StringUtil.isNotEmpty(sub.getSubChartConf())) {
						try {
							configMap = JsonUtils.json2Object(sub.getSubChartConf(), Map.class);
						} catch (Exception e) {
							logger.error("图表配置参数错误，不符合json格式，将按照默认参数配置。主表id=" + tabNum + ",子表id=" + sub.getId());
						}
					}
					// poi导出报表Echarts临时文件存放路径
					String echartsTmpfilePath = CacheUtil.getSysParam(ReportGlobalConstant.POI_ECHARTS_TMPFILE_PATH);
					if (!StringUtil.isNotEmpty(echartsTmpfilePath)) {
						logger.error("poi导出报表Echarts临时文件存放路径未配置：" + ReportGlobalConstant.POI_ECHARTS_TMPFILE_PATH);
						break;
					}
					// 图表文件名(不带后缀名)
					String picPathName = echartsTmpfilePath + "pictures" + File.separator + DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1);
					// [图表渲染].统一入口
					echartsPictureService.buildEChartsPicture4ChartType(configMap, dataList, picPathName, sub.getSubChartType());
					configMap.put("picPathName", picPathName + ".png");
					subIdAndConfig.put(sub.getId(), configMap);
				}
			}
		}

		// 2.获取子表业务数据 - 非图表部分的处理
		for (DisPoiSubDto sub : subList) {
			// word文档
			if (StringUtil.equals(DictCodeListExt3.poi模板类型_WORD.getValue(), sub.getSubType())) {
				// 单列表
				if (StringUtil.equals(DictCodeListExt3.poi表格类型_单表.getValue(), sub.getSubFormType())) {
					// [表格渲染].单列表入口
					MiniTableRenderData mini = buildTableDataForColumn(sub, params);
					if (null == mini) {
						// 默认文本
						// reportDataMap.put(sub.getId(),sub.getSubDefualt());
					} else {
						reportDataMap.put(sub.getId(), mini);
					}
				}
				// 多列表
				else if (StringUtil.equals(DictCodeListExt3.poi表格类型_多列表.getValue(), sub.getSubFormType())) {
					// [表格渲染].多列表入口
					List<List<String>> dataList = buildTableDataForColumns(sub, params);
					if (CollectionUtils.isEmpty(dataList)) {
						// 默认文本
						// reportDataMap.put(sub.getId(),sub.getSubDefualt());
					} else {
						reportDataMap.put(sub.getId(), dataList);
					}
				}
			}
		}
				
		// 3.获取主表业务数据
		Map<String, String> tabDataMap = this.queryBusiDataMapWithSql(tabNum, params,
				DictCodeListExt3.poi关联类型_主表.getValue());
		if (null != tabDataMap) {
			reportDataMap.putAll(tabDataMap);
		}
		
		// 4.检查图片是否生成完成，完成后存入数据集合
		if (subIdAndConfig != null && !subIdAndConfig.isEmpty()) {
			for (String subid : subIdAndConfig.keySet()) {
				// 图表配置参数
				Map<String, Object> configMap = subIdAndConfig.get(subid);
				if (StringUtils.isEmpty(configMap.get("picPathName"))) {
					continue;
				}
				File echartsPicture = new File(configMap.get("picPathName").toString());
				int waitTimes = 10;
				while (echartsPicture.exists() && echartsPicture.length() == 0 && 0 < waitTimes) {
					try {
						logger.debug("等待生成echarts图片1000毫秒");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					waitTimes--;
				}
				if (echartsPicture.exists()) {
					// 图片设置宽、高默认为700、300
					int height = configMap.get("picture.height") == null ? 300 : (int) configMap.get("picture.height");
					int width = configMap.get("picture.width") == null ? 700 : (int) configMap.get("picture.width");
					// 保存图片字节到数据集合
					reportDataMap.put(subid, new PictureRenderData(width, height, ".png", BytePictureUtils.getLocalByteArray(echartsPicture)));
					// 删除图片文件
					echartsPicture.delete();
				}else {
					logger.error("poi报表Echarts图片不存在，主表id=" + tabNum + "，子表id=" + subid + "，图片=" + echartsPicture.getPath());
				}
			}
		}
		
		// 5.获取模板并进行渲染
		// 绑定模板
		ConfigureBuilder builder = Configure.newBuilder();
		builder.addPlugin('!', new TableRenderPolicy());
		XWPFTemplate template = XWPFTemplate.compile(disPoiTab.getTempPath() + disPoiTab.getTempName(),
				builder.build());
		// 模板绑定数据
		template.render(reportDataMap);

		// 6.获取生成的文件并反馈文件
		// 文件后缀名
		String tempSuffix = disPoiTab.getTempName().substring(disPoiTab.getTempName().lastIndexOf("."));
		// 文件全路径名
		String path = FileNameUtil.createUploadFilePath("");
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		String filename = path + disPoiTab.getTabName()
				+ GlobalConstant.UNDER_LINE + DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1) + tempSuffix;
		// 生成文件
		template.writeToFile(filename);
		File report = new File(filename);
		return report;
	}
	
	/**
	 * 获取业务数据map
	 * 
	 * @param tabNum 报表id
	 * @param params 报表参数
	 * @return 业务数据map
	 */
	public Map<String, String> queryBusiDataMapWithSql(String tabNum,Map<String, Object> params,String poiType) {
		Map<String, String> dataMap = new HashMap<>();
		// 查询sql集合
		List<DisPoiQueryDto> sqlList = poiReportMapper.selectSqlList(tabNum, poiType);
		if (CollectionUtils.isEmpty(sqlList)) {
			return dataMap;
		}

		// 组装业务sql
		String sql = sqlList.get(0).getPoiSql();

		// 替换sql参数
		if(StringUtil.isNotEmpty(sql)) {
			sql = replaceSqlParams(sql, params);
		}
		
		//执行业务sql，获取数据
		dataMap = poiReportMapper.selectMap(sql);
		return dataMap;
	}
	
	/**
	 * sql查询子表业务数据
	 * 
	 * @param subId 子表id
	 * @param params 报表参数
	 * @return 子表业务数据list
	 */
	public List<List<String>> querySubBusiDataListWithSql(String subId, Map<String, Object> params) {
		List<List<String>> rows = new ArrayList<>();
		// 查询sql集合
		List<DisPoiQueryDto> sqlList = poiReportMapper.selectSqlList(subId, DictCodeListExt3.poi关联类型_子表.getValue());
		if (CollectionUtils.isEmpty(sqlList)) {
			return rows;
		}

		// 组装业务sql
		String sql = "";
		for (int i = 0; i < sqlList.size(); i++) {
			if(StringUtil.isNotEmpty(sql)) {
				sql += " union all ";
			}
			sql += sqlList.get(i).getPoiSql();
		}

		// 替换sql参数
		if(StringUtil.isNotEmpty(sql)) {
			sql = replaceSqlParams(sql, params);
		}
		rows = getQuery(sql);
		return rows;
	}
	
	/**
	 * sql查询结果
	 * 
	 * @param sql
	 * @return
	 */
	public List<List<String>> getQuery(String sql) {
		// 表格数据
		List<List<String>> rows = new ArrayList<>();
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			if(ps != null){
				rs = ps.executeQuery();
				// 子表的一行数据
				List<String> row = new ArrayList<>();
				while (rs.next()) {
					row = new ArrayList<>();
					// 遍历获取每列数据
					for (int i = 1; i < 50; i++) {
						try {
							// 遍历超越列数则异常，跳出for循环
							row.add(rs.getString(i));
						} catch (Exception e) {
							break;
						}
					}
					rows.add(row);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if(null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rows;
	}
	/**
	 * 替换sql中参数
	 * 
	 * @param sql
	 * @param params 报表参数
	 * @return 替换参数后可执行的sql
	 */
	private String replaceSqlParams(String sql, Map<String, Object> params) {
		for (String key : params.keySet()) {
			sql = sql.replaceAll(Pattern.quote("@" + key.trim()), "'" + params.get(key) + "'");
		}
		return sql;
	}
	
	/**
	 * [表格渲染].多列表入口
	 * 
	 * @param subId 子表id
	 * @param params 报表参数
	 * @return
	 */
	private List<List<String>> buildTableDataForColumns(DisPoiSubDto sub, Map<String, Object> params) {
		return this.querySubBusiDataListWithSql(sub.getId(),params);
	}
	
	/**
	 * [表格渲染].单列表入口
	 * 
	 * @param subId 子表id
	 * @param params 报表参数
	 * @return
	 */
	private MiniTableRenderData buildTableDataForColumn(DisPoiSubDto sub, Map<String, Object> params) {
		Map<String, String> dataMap = this.queryBusiDataMapWithSql(sub.getId(), params, DictCodeListExt3.poi关联类型_子表.getValue());
		if (null == dataMap || dataMap.isEmpty()) {
			return null;
		}
		MiniTableRenderData mini = new MiniTableRenderData(null, this.getTableWidth(sub.getSubWidth()));
		List<RowRenderData> singData = new ArrayList<>();
		for (Map.Entry<String, String> m : dataMap.entrySet()) {
			singData.add(RowRenderData.build(m.getKey(), m.getValue()));
		}
		mini.setDatas(singData);
		return mini;
	}
	
	/**
	 * 获取子表宽度
	 * 
	 * @param widthFlag poipoi表格宽度(N-窄边距,M-适中边距,E-宽边距)
	 * @return
	 */
	private float getTableWidth(String widthFlag) {
		float width;
		if (StringUtil.equals(DictCodeListExt3.poi表格宽度_窄边距.getValue(), widthFlag))
			width = MiniTableRenderData.WIDTH_A4_NARROW_FULL;
		else if (StringUtil.equals(DictCodeListExt3.poi表格宽度_适中边距.getValue(), widthFlag))
			width = MiniTableRenderData.WIDTH_A4_MEDIUM_FULL;
		else if (StringUtil.equals(DictCodeListExt3.poi表格宽度_宽边距.getValue(), widthFlag))
			width = MiniTableRenderData.WIDTH_A4_EXTEND_FULL;
		else
			width = MiniTableRenderData.WIDTH_A4_FULL;
		return width;
	}
}
