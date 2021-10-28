package com.joyintech.tams.business.common.util;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;

import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.service.DoQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class ExcelUitl {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelUitl.class);
	
	public static void createExcelTemplate(File excelTemplateFile,String sheetName,List<ExcelColumn> columns, Map<String, BaseMap> styleMap) {
		List<List<String>> list = new ArrayList<List<String>>(2);
		List<String> head = new ArrayList<String>(columns.size());
		List<String> data = new ArrayList<String>(columns.size());
		for (ExcelColumn column : columns) {
			head.add(column.getName());
			data.add("{."+column.getCode()+"}");
		}
		list.add(head);
		list.add(data);
		
		EasyExcel.write(excelTemplateFile).inMemory(true).sheet(sheetName).registerWriteHandler(new CellWriteHandler() {
			@Override
			public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, WriteCellData<?> cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
				String key = cellData.getStringValue();
				/**
				 * CFG_DATATABLE_SHOW列表字段配置信息
				 */
				BaseMap cfg = styleMap.get(key);
				if (cfg == null){
					// 表头信息
					Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
					CellStyle cellStyle = workbook.createCellStyle();
					Font cellFont = workbook.createFont();
					cellFont.setBold(true);
					cellStyle.setFont(cellFont);
					cell.setCellStyle(cellStyle);
				}else{
					Integer width = cfg.getInteger("width"); // 单元格宽度
					String dataType = cfg.getString("dataType"); // 设置数据类型
					String cloAlign = cfg.getString("colAlign"); // 水平对齐方式
					
					if (width != null){
						// 设置宽度
						writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), width);
					}
					
					if (StringUtil.isNotEmpty(dataType) || StringUtil.isNotEmpty(cloAlign)){
						// 自定义设置单元格格式
						// 数据类型设置 rate、amount、date
						// 水平对齐方式设置
						setCellStyleSelf(writeSheetHolder, cfg, cell, dataType, cloAlign);
					}
				}
				CellWriteHandler.super.afterCellDataConverted(writeSheetHolder, writeTableHolder, cellData, cell, head, relativeRowIndex, isHead);
			}
		}).doWrite(list);
	}

	/**
	 * 自定义设置单元格格式
	 * dataType "rate|amount|date", // 数据类型, rate-利率型，amount - 金额，date-日期
	 * format: "yyyy-MM-dd|yyyy-MM-dd HH:mm:ss|...", // 日期格式
	 * unit: "1|2|3", //金额类型：1 - 元, 2 - 万元, 3 - 亿元
	 * precision: 2|3|4... // 利率型保留小数位，整数
	 * @param writeSheetHolder
	 * @param cfg
	 */
	private static void setCellStyleSelf(WriteSheetHolder writeSheetHolder, BaseMap cfg, Cell cell, String dataType, String cloAlign) {
		try {
			Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
			CellStyle cellStyle = workbook.createCellStyle();
			DataFormat df = workbook.createDataFormat();
			
			if (StringUtil.isNotEmpty(dataType)){
				if (StringUtil.equals("rate", dataType)){
					// 利率型
					if (StringUtil.equals("2", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("0.00"));
					}else if (StringUtil.equals("3", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("0.000"));
					}else if (StringUtil.equals("4", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("0.0000"));
					}else{ // 默认保留两位
						cellStyle.setDataFormat(df.getFormat("0.00"));
					}
				} else if (StringUtil.equals("amount", dataType)){
					// 金额
					if (StringUtil.equals("2", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("#,##0.00"));
					}else if (StringUtil.equals("3", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("#,##0.000"));
					}else if (StringUtil.equals("4", cfg.getString("precision"))){
						cellStyle.setDataFormat(df.getFormat("#,##0.0000"));
					}else{ // 默认保留两位
						cellStyle.setDataFormat(df.getFormat("#,##0.00"));
					}
				}else if (StringUtil.equals("date", dataType)){
					// 日期
					cellStyle.setDataFormat(df.getFormat(cfg.getString("format")));
				}
			}
			
			// 设置对齐方式
			if (StringUtil.isNotEmpty(cloAlign)) {
				if (StringUtil.equals("left", cloAlign)){
					HorizontalAlignment align = HorizontalAlignment.LEFT;
					cellStyle.setAlignment(align);
				}else if (StringUtil.equals("right", cloAlign)){
					HorizontalAlignment align = HorizontalAlignment.RIGHT;
					cellStyle.setAlignment(align);
				}else if (StringUtil.equals("center", cloAlign)){
					HorizontalAlignment align = HorizontalAlignment.CENTER;
					cellStyle.setAlignment(align);
				}
			}
			
			cell.setCellStyle(cellStyle);
		}catch (Exception e){
			logger.error(e.getMessage());
		}
	}

	public static <T> void exportAllData(File excelFile,String sheetName,List<ExcelColumn> columns,List<T> data, Map<String, BaseMap> styleMap) {
		File excelTemplateFile = new File(excelFile.getParentFile(),Thread.currentThread().getName()+excelFile.getName());
		createExcelTemplate(excelTemplateFile, sheetName, columns, styleMap);
		EasyExcel.write(excelFile).withTemplate(excelTemplateFile).sheet(sheetName).doFill(data);
		excelTemplateFile.delete();
	}
	
	public static class DataAppendUtil<T> {
		
		private File excelTemplateFile;
		private File excelFile;
		private String sheetName;
		private ExcelWriter excelWriter;
		private WriteSheet writeSheet;
		
		public DataAppendUtil(File excelTemplateFile, File excelFile, String sheetName) {
			this.excelTemplateFile = excelTemplateFile;
			this.excelFile = excelFile;
			this.sheetName = sheetName;
			this.excelWriter = EasyExcel.write(excelFile).withTemplate(excelTemplateFile).build();
			this.writeSheet = EasyExcel.writerSheet(sheetName).build();
		}
		
		public void append(List<T> data) {
			excelWriter.fill(data, writeSheet);
		}
		
		public void close() {
			if (null!=excelWriter) {
				excelWriter.finish();
			}
		}

		public File getExcelTemplateFile() {
			return excelTemplateFile;
		}

		public void setExcelTemplateFile(File excelTemplateFile) {
			this.excelTemplateFile = excelTemplateFile;
		}

		public File getExcelFile() {
			return excelFile;
		}

		public void setExcelFile(File excelFile) {
			this.excelFile = excelFile;
		}

		public String getSheetName() {
			return sheetName;
		}

		public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}

		public ExcelWriter getExcelWriter() {
			return excelWriter;
		}

		public void setExcelWriter(ExcelWriter excelWriter) {
			this.excelWriter = excelWriter;
		}

		public WriteSheet getWriteSheet() {
			return writeSheet;
		}

		public void setWriteSheet(WriteSheet writeSheet) {
			this.writeSheet = writeSheet;
		}
		
	}
	
	@ApiModel(value="Excel导出指定的列",description="Excel导出指定的列")
	public static class ExcelColumn{
		
		@ApiModelProperty(required = false, name="code",value="列的字段名",example="portfolioName")
		private String code;
		
		@ApiModelProperty(required = false, name="name",value="列的中文名",example="组合名称")
		private String name;

		public ExcelColumn() {
			super();
		}

		public ExcelColumn(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static void doExport(CfetsExportRequestDto requestDto, HttpServletResponse response, String excelAndSheetName, DoQuery doQuery) {
		List<BaseMap> header = requestDto.getHeader();
		List dataList = requestDto.getData();
		if (dataList == null || dataList.size() == 0){
			dataList = doQuery.query();
		}
		doExport(excelAndSheetName, header, dataList, response);
	}
	
	private static void doExport(String fileName, List<BaseMap> heads, List dataList, HttpServletResponse response) {
		OutputStream os = null;
		File excelFile = null;
		String dir = UUID.randomUUID().toString();
		try {
			os = response.getOutputStream();
			response.setContentType("text/vnd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30");
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));

			/**
			 * 设置格式配置信息，后面监听并加入到excel中
			 */
			Map<String, BaseMap> styleMap = new HashMap<>();
			Iterator<BaseMap> it = heads.iterator(); 
			while (it.hasNext()){
				BaseMap one = it.next();
				String extendCfg = one.getString("extendCfg");
				styleMap.put("{." + one.getString("colCode") + "}", BaseMap.newMap(JsonUtils.json2Object(extendCfg, Map.class)));
			}
			
			StringBuffer buf = new StringBuffer();
			List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
			for (Map lineMap : heads) {
				columns.add(new ExcelColumn(lineMap.get("colCode")+"", lineMap.get("colName")+""));
			}

			/**
			 * 需要使用当前目录，防止生产时目录找不到导出报错
			 */
			File path = new File(dir);
			if (!path.exists()){
				path.mkdir();
			}
			excelFile = new File(dir + File.separator + fileName +".xlsx");
			
			long s = System.currentTimeMillis();
			exportAllData(excelFile, fileName , columns, dataList, styleMap);
			
			// 写出响应
			os.write(File2byte(excelFile));
			os.flush();
		} catch (Exception e) {
			logger.error("doExport错误...", e);
			throw new RuntimeException(e.getMessage());
		} finally {
			try {os.close();} catch (IOException e) {e.printStackTrace();}
			/**
			 * 删除文件夹包含文件
			 */
			delFolder(dir);
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除指定文件夹下所有文件
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);//再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	public static byte[] File2byte(File tradeFile){
		byte[] buffer = null;
		try
		{
			FileInputStream fis = new FileInputStream(tradeFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return buffer;
	}
//	public static void main(String[] args) {
//		//导出所有数据
//		List<Data> data = new ArrayList<Data>();
//		data.add(new Data(1L, "名称1", new BigDecimal(3.11), new Date()));
//		data.add(new Data(2L, "名称2", new BigDecimal(3.12), new Date()));
//		data.add(new Data(3L, "名称3", new BigDecimal(3.13), new Date()));
//		data.add(new Data(4L, "名称4", new BigDecimal(3.14), new Date()));
//		data.add(new Data(5L, "名称5", new BigDecimal(3.15), new Date()));
//		data.add(new Data(6L, "名称6", new BigDecimal(3.16), new Date()));
//		
//		List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
//		columns.add(new ExcelColumn("id", "ID"));
//		columns.add(new ExcelColumn("name", "名称"));
//		columns.add(new ExcelColumn("number", "数字"));
//		columns.add(new ExcelColumn("createTime", "创建日期"));
//		
//		File excelFile = new File("D://1.xlsx");
//		String sheetName = "债券交易-持仓信息";
//		long s = System.currentTimeMillis();
//		exportAllData(excelFile, sheetName , columns, data);
//		System.out.println(System.currentTimeMillis()-s);
//		
//		//分页追加
//		excelFile = new File("D://2.xlsx");
//		File excelTemplateFile = new File(excelFile.getParentFile(),Thread.currentThread().getName()+excelFile.getName());
//		createExcelTemplate(excelTemplateFile, sheetName, columns);
//		DataAppendUtil<Data> u = new DataAppendUtil<>(excelTemplateFile, excelFile, sheetName);
//		u.append(data);
//		u.append(data);
//		u.append(data);
//		u.close();
//	}
	
	public static class Data{
		private long id;
		private String name;
		private BigDecimal number;
		private Date createTime;
		
		public Data(long id, String name, BigDecimal number, Date createTime) {
			this.id = id;
			this.name = name;
			this.number = number;
			this.createTime = createTime;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public BigDecimal getNumber() {
			return number;
		}
		public void setNumber(BigDecimal number) {
			this.number = number;
		}
	}
}
