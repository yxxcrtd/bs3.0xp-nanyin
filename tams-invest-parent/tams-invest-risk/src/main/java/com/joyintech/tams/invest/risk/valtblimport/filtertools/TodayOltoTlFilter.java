package com.joyintech.tams.invest.risk.valtblimport.filtertools;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.PoiCellUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;



/**Title:OltoTlFilter
 * @author 裴家俊
 *备注:一行转两行 ，今日转基金，且科目代码消除点号与横杠
 */
public class TodayOltoTlFilter extends AbstractFilter {

	private static final int COL_CODE = XlsUtil.convertColumnCodeToSeq("A")-1; //科目代码列
	private static final int COL_NAME = XlsUtil.convertColumnCodeToSeq("B")-1; //科目名称列
	private static final int COL_COST = XlsUtil.convertColumnCodeToSeq("E")-1; //成本
	private static final int COL_SMV = XlsUtil.convertColumnCodeToSeq("H")-1;  //市值
	private static final String  COL_UNIT_R_NOT = "今日单位净值";
	private static final String  PROJ_JJ_R_NOT = "基金单位净值";
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
	        	//---add by 裴家俊 20190420----
	        	if(projCode.contains(".")){
	        		projCode= projCode.replace(".", "").replace(" ", "");
	        		row.createCell(COL_CODE).setCellValue(projCode);
				}
	        	if(projCode.contains("-")){
	        		projCode= projCode.replace("-", "").replace(" ", "");
	        		row.createCell(COL_CODE).setCellValue(projCode);
				}
	        	//---add by 裴家俊 20190420----
	          projCode = projCode.trim();
	          if (projCode.trim().equals(COL_UNIT_R)||projCode.trim().equals("今日单位净值："))
	          {
	        	org.apache.poi.ss.usermodel.Cell cellCost = row.getCell(COL_COST);
	            if (cellCost != null) {
	            	cost = PoiCellUtil.getCellValue(cellCost);
	              if (cost != null) cost = cost.trim();
	            }
	            //cellCost.setCellValue("");---modify by 裴家俊  20190420--------
	            org.apache.poi.ss.usermodel.Cell cellSmv = row.getCell(COL_SMV);
	            if (cellSmv != null) {
	              smv = PoiCellUtil.getCellValue(cellSmv);
	              if (smv != null) {
	            	  smv = smv.trim();
	              }
	            }
	           // cellSmv.setCellValue("");---modify by 裴家俊  20190420--------
	            //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
	            if((null==cost||"".equals(cost))&&(null==smv||"".equals(smv))){
	            	org.apache.poi.ss.usermodel.Cell cellName = row.getCell(COL_NAME);
		            if (cellName != null) {
		            	cost = PoiCellUtil.getCellValue(cellName);
		            	if (cost != null){ 
		            	  cost = cost.trim();
		            	  smv = cost;
		            	}
		            }
	            }
	            //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
	            Row new_row = sheet.createRow(sheet.getLastRowNum()+1);
	    	    new_row.createCell(COL_COST).setCellValue(cost);
	    	    new_row.createCell(COL_SMV).setCellValue(smv);
	    	    new_row.createCell(COL_CODE).setCellValue(PROJ_JJ_R);
	            break;
	          }else if(projCode.trim().equals(COL_UNIT_R_NOT)){
	        	  org.apache.poi.ss.usermodel.Cell cellCost = row.getCell(COL_COST);
		            if (cellCost != null) {
						cost = PoiCellUtil.getCellValue(cellCost);
		              if (cost != null) cost = cost.trim();
		            }
		           // cellCost.setCellValue("");//---modify by 裴家俊  20190420--------
		            org.apache.poi.ss.usermodel.Cell cellSmv = row.getCell(COL_SMV);
		            if (cellSmv != null) {
						smv = PoiCellUtil.getCellValue(cellSmv);
		              if (smv != null) {
		            	  smv = smv.trim();
		              }
		            }
		           // cellSmv.setCellValue("");
		            //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
		            if((null==cost||"".equals(cost))&&(null==smv||"".equals(smv))){
		            	org.apache.poi.ss.usermodel.Cell cellName = row.getCell(COL_NAME);
			            if (cellName != null) {
							cost = PoiCellUtil.getCellValue(cellName);
			            	if (cost != null){ 
			            	  cost = cost.trim();
			            	  smv = cost;
			            	}
			            }
		            }
		            //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
		            Row new_row = sheet.createRow(sheet.getLastRowNum()+1);
		    	    new_row.createCell(COL_COST).setCellValue(cost);
		    	    new_row.createCell(COL_SMV).setCellValue(smv);
		    	    new_row.createCell(COL_CODE).setCellValue(PROJ_JJ_R_NOT);
		            break;
	          }
	        }
	      }
	    }
	    return workbook;
	}
}
