package com.jixu.adapter.reader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		ReadExcelFile
 * @author  		Pan Wei 
 * @Date 			2016年12月5日 下午3:08:08 	
 * @Description		[]  
 */

@Service("readExcelFile")
public class ReadExcelFile extends ReadFileBasic {

	public int getFileType() {
		return InterfaceConfig.excel;
	}

	public DataReadResult readFileDataResult(InterfaceSource source,DataCounter dataCounter) throws Exception {
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();
		if(result.getFilePathList().size()==0){
			result.setFilePathList(filePaths);
		}

		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		
		for (String filepath : filePaths) {
			InputStream stream = new FileInputStream(filepath);  
			Workbook workbook=null;
			try {
				//创建实例，并判断是否为xls或者xlsx格式的文件
				workbook=WorkbookFactory.create(stream);

				Sheet sheet = null;
				if (source.getTablename() != null && !source.getTablename().equals("")) {
					sheet = workbook.getSheet(source.getTablename());
				} else {
					sheet = workbook.getSheetAt(0);
				}

				for (int i = source.getTableStartLine(); i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					int celltotalnum=row.getLastCellNum();
					List<Object> fieldsList = new ArrayList<Object>();
					for (int cellnum=0; cellnum<celltotalnum;cellnum++) {
						Cell cell=row.getCell(cellnum);
						if(source.getTableEndChar()!=null && !source.getTableEndChar().equals("")){
							if(getCellContent(cell).contains(source.getTableEndChar()))
								break;
						}else{
							fieldsList.add(getCellContent(cell));
						}
					}
					fileContainer.add(fieldsList);
				}
			}finally {
				if(stream != null){
					stream.close();
				}
				if(workbook != null){
					workbook.close();
				}
			}
		}

		result.setDataResult(fileContainer);

		return result;
	}
	
	@SuppressWarnings("deprecation")
	private String getCellContent(Cell cell){
		if(cell==null)
			return "";
		switch(cell.getCellType().getCode()){
			case 0:
				 if(HSSFDateUtil.isCellDateFormatted(cell)){
				      Date preDate = (Date) cell.getDateCellValue();
				      return DateUtil.convertD2YYYY_MM_DD(preDate);
				 }else {
					 return String.valueOf(cell.getNumericCellValue());
				 }
			case 1:
				return cell.getStringCellValue();
			case 3:
				return "";
			case 4:
				return String.valueOf(cell.getBooleanCellValue());
			case 2:
				String returnV="";
				try{
					returnV=cell.getStringCellValue();
				}catch(IllegalStateException e){
					returnV=String.valueOf(cell.getNumericCellValue());
				}
				return returnV;
			default:
				return "";
		}
	}
}
