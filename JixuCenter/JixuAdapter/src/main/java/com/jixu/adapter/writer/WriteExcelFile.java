package com.jixu.adapter.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/**
 * @ClassName WriteCSVFile
 * @author Pan Wei
 * @Date 2017年5月3日 上午10:19:58
 * @Description []
 */

@Component("adptWriteExcelFile")
public class WriteExcelFile extends WriteFileBasic {
	@Override
	public int getFileType() {
		return InterfaceConfig.excel;
	}

	@Override
	public boolean writeFile(InterfaceSource interfaceSource,
			DataReadResult dataSet) throws Exception {
		boolean writeSuccessFlag = false;
		Workbook workBook = null;
		File file = getWriteFile(interfaceSource, dataSet);
		if (file.getName().toLowerCase().endsWith("xls")) { // Excel 2003
			workBook = new HSSFWorkbook();
		} else if (file.getName().toLowerCase().endsWith("xlsx")) { // Excel 2007/2010
			workBook = new XSSFWorkbook();
		} else {
			workBook = new XSSFWorkbook();
		}
		
		OutputStream out = new FileOutputStream(file);
		try {
			List<List<Object>> fieldsDataSet = dataSet.getDataResult();

			if (fieldsDataSet != null && !fieldsDataSet.isEmpty()) {
				Sheet sheet=null;
				if(interfaceSource.getTablename()==null || interfaceSource.getTablename().equals(""))
					sheet= workBook.createSheet();
				else
					sheet=workBook.createSheet(interfaceSource.getTablename());

				int rownum = 0;
				if (interfaceSource.isOutFileTitleFlag()) {
					//写入文件头header
					Row row = sheet.createRow(rownum);
					// CellStyle cellStyle = createHeadCellStyle(workBook);
					List<String> headerList = dataSet.getDataHeader();
					for (int i = 0; i < headerList.size(); i++) {
						Cell cell = row.createCell(i);
						cell.setCellValue(headerList.get(i));
					}
					rownum++;
				}

				for (List<Object> fieldsList : fieldsDataSet) {
					Row row = sheet.createRow(rownum);
					for (int i = 0; i < fieldsList.size(); i++) {
						Cell cell = row.createCell(i);
						if(fieldsList.get(i)!=null)
							cell.setCellValue(fieldsList.get(i).toString());
						else
							cell.setCellValue("");
					}
					rownum++;
				}
			}
			workBook.write(out);
			writeSuccessFlag = true;
		} catch (IOException e) {
			writeSuccessFlag = false;
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if(workBook!=null){
					workBook.close();
				}
				writeSuccessFlag=true;
			} catch (IOException e) {
				writeSuccessFlag = false;
			}
		}

		return writeSuccessFlag;
	}

	/**
	 * 创建头部样式
	 * 
	 * @param wb
	 * @return
	 */
	/*
	 * private static CellStyle createHeadCellStyle(Workbook wb) { CellStyle
	 * cellStyle = wb.createCellStyle(); addAlignStyle(cellStyle,
	 * CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
	 * addBorderStyle(cellStyle, CellStyle.BORDER_MEDIUM,
	 * IndexedColors.BLACK.getIndex()); addColor(cellStyle,
	 * IndexedColors.GREY_25_PERCENT.getIndex(), CellStyle.SOLID_FOREGROUND);
	 * return cellStyle; }
	 */

}
