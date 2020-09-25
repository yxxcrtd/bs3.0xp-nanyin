package com.joyintech.tams.invest.risk.valtblimport.filtertools;


import org.apache.poi.ss.usermodel.Workbook;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.PoiCellUtil;

/**
 * @author 裴家俊
 * @date  2019/04/27
 * @remark 去掉“.”与空格且转换为4,6,8,资产科目体系
 */
public class PointDeleteFilter extends AbstractFilter {

	private static final Integer subjectCodeCol =1;//科目代码列
	@Override
	public Workbook doFilter(Workbook workbook, int sheetIndex) throws Exception {
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);
		Integer rowNum_src = sheet.getLastRowNum();
		
		for(int i=startRow-1;i<=rowNum_src-1;i++){
			org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
			if(null!=row){
				org.apache.poi.ss.usermodel.Cell subjectCode = row.getCell(subjectCodeCol-1);
				String code = PoiCellUtil.getCellValue(subjectCode);
				String code1="";
				if(code!=null&&code.contains(".")){
					code1= code.replace(".", "").replace(" ", "");
					row.createCell(subjectCodeCol-1).setCellValue(code1);
				}
			}
		}
		return workbook;
	}
}
