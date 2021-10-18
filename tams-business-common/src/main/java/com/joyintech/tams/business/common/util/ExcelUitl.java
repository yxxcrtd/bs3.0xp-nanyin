package com.joyintech.tams.business.common.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class ExcelUitl {

	public static void createExcelTemplate(File excelTemplateFile,String sheetName,List<ExcelColumn> columns) {
		List<List<String>> list = new ArrayList<List<String>>(2);
		List<String> head = new ArrayList<String>(columns.size());
		List<String> data = new ArrayList<String>(columns.size());
		for (ExcelColumn column : columns) {
			head.add(column.getName());
			data.add("{."+column.getCode()+"}");
		}
		list.add(head);
		list.add(data);
		EasyExcel.write(excelTemplateFile).inMemory(true).sheet(sheetName).doWrite(list);
	}
	
	public static <T> void exportAllData(File excelFile,String sheetName,List<ExcelColumn> columns,List<T> data) {
		File excelTemplateFile = new File(excelFile.getParentFile(),Thread.currentThread().getName()+excelFile.getName());
		createExcelTemplate(excelTemplateFile, sheetName, columns);
		EasyExcel.write(excelFile).withTemplate(excelTemplateFile).sheet(sheetName).doFill(data);
		excelTemplateFile.deleteOnExit();
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
//	
//	public static class Data{
//		private long id;
//		private String name;
//		private BigDecimal number;
//		private Date createTime;
//		
//		public Data(long id, String name, BigDecimal number, Date createTime) {
//			this.id = id;
//			this.name = name;
//			this.number = number;
//			this.createTime = createTime;
//		}
//		public long getId() {
//			return id;
//		}
//		public void setId(long id) {
//			this.id = id;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public Date getCreateTime() {
//			return createTime;
//		}
//		public void setCreateTime(Date createTime) {
//			this.createTime = createTime;
//		}
//		public BigDecimal getNumber() {
//			return number;
//		}
//		public void setNumber(BigDecimal number) {
//			this.number = number;
//		}
//	}
}
