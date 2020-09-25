package com.joyintech.tams.invest.risk.valtblimport.filtertools;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.PoiCellUtil;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;




/**HaifutongFilter
 * @author jjpei
 * Date: 2019年5月13日
 * Time: 上午9:33:56
 * version 1.0
 *备注:海富通基金模板，把面额科目代码复制到总价行上，然后消除 面额与折溢价行，
 */
public class HaifutongFilter extends AbstractFilter {

	private static final int COL_CODE = XlsUtil.convertColumnCodeToSeq("A")-1; //科目代码列
	private static final int COL_NAME = XlsUtil.convertColumnCodeToSeq("B")-1; //科目名称
	private static final int COL_COST = XlsUtil.convertColumnCodeToSeq("E")-1; //成本
	private static final int COL_SMV = XlsUtil.convertColumnCodeToSeq("H")-1;  //市值
	private static final String  COL_UNIT_R_NOT = "今日单位净值";
	private static final String  PROJ_JJ_R_NOT = "基金单位净值";
	private static final String  COL_UNIT_R = "今日单位净值:";
	private static final String  PROJ_JJ_R = "基金单位净值:";
	private static final String  CELL_MIANE = "面额";
	private static final String  CELL_YIZHEJIA = "溢折价";
	private static final String  CELL_ZHEYIJIA = "折溢价";
	
	
	
	@Override
	public Workbook doFilter(Workbook workbook,int sheetIndex) throws Exception {
		String cost = null; String smv=null;
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);
		Integer rowNum_src = sheet.getLastRowNum();
		List<Integer> delRowList=new ArrayList<Integer>();
		 for (Row row : sheet) {
	      Cell cellProjCode = row.getCell(COL_CODE);
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
	        	//---add by 裴家俊 20190530----start----找出是面额的行-------
	          Cell cellHfgjName = row.getCell(COL_NAME);
	   	      if (cellHfgjName != null) {
	   	    	  String hfgName = PoiCellUtil.getCellValue(cellHfgjName);
		   	      if(hfgName.contains("(总价)")||hfgName.contains("（总价）")){
		   	    		row.createCell(COL_NAME).setCellValue(hfgName.replace("(总价)", "").replace("（总价）", ""));
		   	      }
	   	    	  if(CELL_MIANE.equals(hfgName)){
	   	    		Row preRow= sheet.getRow(row.getRowNum()-1);
	   	    		Row nextRow= sheet.getRow(row.getRowNum()+1);
	   	    		if(null==PoiCellUtil.getCellValue(preRow.getCell(COL_CODE))||"".equals(PoiCellUtil.getCellValue(preRow.getCell(COL_CODE)))){
	   	    			preRow.createCell(COL_CODE).setCellValue(projCode);
	   	    		}
	   	    		if(CELL_YIZHEJIA.equals(PoiCellUtil.getCellValue(nextRow.getCell(COL_NAME)))
					||CELL_ZHEYIJIA.equals(PoiCellUtil.getCellValue(nextRow.getCell(COL_NAME)))){
	   	    			delRowList.add(row.getRowNum()+1);
	   	    		}
	   	    		delRowList.add(row.getRowNum());;
	   	    		continue;
	   	    	  }
	   	      }
	   	      //---add by 裴家俊 20190530----end-----------
	   	      
	          //---add by 裴家俊 20190420----
	          projCode = projCode.trim();
	          if (projCode.trim().equals(PROJ_JJ_R)||projCode.trim().equals("基金单位净值："))//顿号是全角或半角
	          {
	        	  Cell cellCost = row.getCell(COL_COST);
		            if (cellCost != null) {
		            	cost = PoiCellUtil.getCellValue(cellCost);
		              if (cost != null) cost = cost.trim();
		            }
		            //cellCost.setCellValue("");---modify by 裴家俊  20190420--------
		            Cell cellSmv = row.getCell(COL_SMV);
		            if (cellSmv != null) {
		              smv = PoiCellUtil.getCellValue(cellSmv);
		              if (smv != null) {
		            	  smv = smv.trim();
		              }
		            }
		            //cellSmv.setCellValue("");---modify by 裴家俊  20190420--------
	        	  //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
		         if((null==cost||"".equals(cost))&&(null==smv||"".equals(smv))){
	            	Cell cellName = row.getCell(COL_NAME);
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
	    	    new_row.createCell(COL_CODE).setCellValue(COL_UNIT_R);
	            break;
	          }else if (projCode.trim().equals(PROJ_JJ_R_NOT))
	          {
	        	  Cell cellCost = row.getCell(COL_COST);
		            if (cellCost != null) {
						cost = PoiCellUtil.getCellValue(cellCost);
		              if (cost != null) cost = cost.trim();
		            }
		            //cellCost.setCellValue("");---modify by 裴家俊  20190420--------
		            Cell cellSmv = row.getCell(COL_SMV);
		            if (cellSmv != null) {
						smv = PoiCellUtil.getCellValue(cellSmv);
		              if (smv != null) {
		            	  smv = smv.trim();
		              }
		            }
		            //cellSmv.setCellValue("");---modify by 裴家俊  20190420--------
	        	  //----------add by 裴家俊--20190420---有的资产计划把值只放在科目名称列----
		            if((null==cost||"".equals(cost))&&(null==smv||"".equals(smv))){
	            	Cell cellName = row.getCell(COL_NAME);
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
	    	    new_row.createCell(COL_CODE).setCellValue(COL_UNIT_R_NOT);
	            break;
	          }
	        }
	      }
	    }
		for(Integer delRow:delRowList){
			sheet.removeRow(sheet.getRow(delRow));
		}
		 for(int i =1;i<rowNum_src;i++){
		        Row row = sheet.getRow(i);
		        Cell cell = null;
		        String cellValue  = "";
		        if (row!=null){
		            cell = row.getCell(0);
		            cellValue = PoiCellUtil.getCellValue(cell);
		        }
		        if (row==null||cell==null||cellValue==""){
		            sheet.shiftRows(i+1,rowNum_src,-1);
		            i--;
		            //减去一条空行，总行数减一。
		            rowNum_src--;
		        }
		    }
	    return workbook;
	}
}
