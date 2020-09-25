package com.joyintech.tams.invest.risk.valtblimport.filtertools;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.PoiCellUtil;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;

/**
 * @author 裴家俊
 * @date  2019/04/27
 * @remark 去掉“-”转换为4,6,8,资产科目体系 同时产品单位净值生成今日单位净值
 */
public class ProductFilter extends AbstractFilter {

	private static final Integer COL_CODE = XlsUtil.convertColumnCodeToSeq("B")-1; //科目代码列
	private static final Integer COL_COST = XlsUtil.convertColumnCodeToSeq("F")-1; //成本
	private static final Integer COL_SMV = XlsUtil.convertColumnCodeToSeq("I")-1;  //市值
	private static final String  COL_UNIT_P = "产品单位净值：";
	private static final String  COL_UNIT_T = "今日单位净值：";
	
	@Override
	public Workbook doFilter(Workbook workbook, int sheetIndex) throws Exception {
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);		
		Integer rowNum_src = sheet.getLastRowNum();
		String cost = null; String smv=null;
		for(int i=startRow-1;i<=rowNum_src;i++){
			org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
			org.apache.poi.ss.usermodel.Cell cell = null;	
			cell = row.getCell(COL_CODE);
            if(null!=cell){
            	String subjectCode = PoiCellUtil.getCellValue(cell).replace(" ", "");	
    			if(subjectCode!=null&&!"".equals(subjectCode)){
    				if(subjectCode.contains("-")&&subjectCode.length()==13){
    					String subCode=subjectCode.substring(0,6)+subjectCode.substring(subjectCode.length()-2);
    					row.createCell(COL_CODE).setCellValue(subCode);
    				}
    				if(subjectCode.contains("-")&&subjectCode.length()>13){
    					String code=subjectCode.substring(0,13);
    					String subCode=code.substring(0,6)+code.substring(code.length()-2)+subjectCode.substring(13);
    					row.createCell(COL_CODE).setCellValue(subCode);
    				}
		            if (subjectCode.trim().equals(COL_UNIT_P)||subjectCode.trim().equals("产品单位净值:")){
			        	org.apache.poi.ss.usermodel.Cell cellCost = row.getCell(COL_COST);
			             if (cellCost != null) {
			            	 if("NUMERIC".equals(cellCost.getCellTypeEnum().toString())){
					             cost = String.valueOf(cellCost.getNumericCellValue()); 
			            	 }else if("STRING".equals(cellCost.getCellTypeEnum())){
			            		 cost = cellCost.getStringCellValue();
			            	 }
				               if (cost != null) cost = cost.trim();
				         } 
			            org.apache.poi.ss.usermodel.Cell cellSmv = row.getCell(COL_SMV);
			            if (cellSmv != null) {
			            	 if("NUMERIC".equals(cellSmv.getCellTypeEnum().toString())){
			            		 smv = String.valueOf(cellSmv.getNumericCellValue()); 
			            	 }else if("STRING".equals(cellSmv.getCellTypeEnum())){
			            		 smv = cellSmv.getStringCellValue();
			            	 }
				               if (smv != null) smv = smv.trim();
				         } 
			            Row new_row = sheet.createRow(sheet.getLastRowNum()+1);
			    	    new_row.createCell(COL_COST).setCellValue(cost);
			    	    new_row.createCell(COL_SMV).setCellValue(smv);
			    	    new_row.createCell(COL_CODE).setCellValue(COL_UNIT_T);
			        }        
    			}
            }		
		}
		return workbook;
	}
}
