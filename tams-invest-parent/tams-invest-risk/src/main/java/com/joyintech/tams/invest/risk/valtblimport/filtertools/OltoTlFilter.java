package com.joyintech.tams.invest.risk.valtblimport.filtertools;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;



/**Title:OltoTlFilter
 * @author 裴家俊
 *备注:一行转两行
 */
public class OltoTlFilter extends AbstractFilter {

	private static final int COL_CODE = XlsUtil.convertColumnCodeToSeq("A")-1; //科目代码列
	private static final int COL_COST = XlsUtil.convertColumnCodeToSeq("E")-1; //成本
	private static final int COL_SMV = XlsUtil.convertColumnCodeToSeq("H")-1;  //市值
	private static final String  COL_UNIT_R = "今日单位净值:";
	private static final String  PROJ_JJ_R = "基金单位净值:";
	
	@Override
	public Workbook doFilter(Workbook workbook,int sheetIndex) throws Exception {
		String cost = null; String smv=null;
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);
	    for (org.apache.poi.ss.usermodel.Row row : sheet) {
	      org.apache.poi.ss.usermodel.Cell cellProjCode = row.getCell(COL_CODE);
	      if (cellProjCode != null) {
	        String projCode = cellProjCode.getStringCellValue();
	        if (projCode != null) {
	          projCode = projCode.trim();
	          if (projCode.trim().equals(COL_UNIT_R))
	          {
	        	org.apache.poi.ss.usermodel.Cell cellCost = row.getCell(COL_COST);
	            if (cellCost != null) {
	            	cost = cellCost.getStringCellValue();
	              if (cost != null) cost = cost.trim();
	            }
	            if (cellCost != null)cellCost.setCellValue("");
	            org.apache.poi.ss.usermodel.Cell cellSmv = row.getCell(COL_SMV);
	            if (cellSmv != null) {
	              smv = cellSmv.getStringCellValue();
	              if (smv != null) {
	            	  smv = smv.trim();
	              }
	            }
	            if (cellSmv != null)cellSmv.setCellValue("");
	            break;
	          }
	        }
	      }
	    }
	    Row new_row = sheet.createRow(sheet.getLastRowNum()+1);
	    new_row.createCell(COL_COST).setCellValue(cost);
	    new_row.createCell(COL_SMV).setCellValue(smv);
	    new_row.createCell(COL_CODE).setCellValue(PROJ_JJ_R);
	    return workbook;
	}
}
