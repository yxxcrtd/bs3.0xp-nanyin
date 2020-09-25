package com.joyintech.tams.invest.risk.valtblimport.filtertools;

import org.apache.poi.ss.usermodel.Workbook;

import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;


public class PacificValuationFilter extends AbstractFilter {

	private static final int SUBJ_CODE_POS = XlsUtil.convertColumnCodeToSeq("A")-1;
	private static final int SUBJ_NAME_POS = XlsUtil.convertColumnCodeToSeq("B")-1;
	private static final int PROJ_CODE_POS = XlsUtil.convertColumnCodeToSeq("E")-1;
	private static final int PROJ_NAME_POS = XlsUtil.convertColumnCodeToSeq("F")-1;
	
	@Override
	public Workbook doFilter(Workbook workbook,int sheetIndex) throws Exception {
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);
	    for (org.apache.poi.ss.usermodel.Row row : sheet) {
	    	org.apache.poi.ss.usermodel.Cell cellProjCode = row.getCell(PROJ_CODE_POS);
	      if (cellProjCode != null) {
	        String projCode = cellProjCode.getStringCellValue();
	        //projCode = projCode.trim().replaceAll("SH|SZ", "");
	        if (projCode != null) {
	          //替换最后两位SH/SZ
	          if(projCode.indexOf("SZ")==projCode.length()-2||projCode.indexOf("SH")==projCode.length()-2){
	        	  projCode = projCode.substring(0,projCode.length()-2);
	          }
	          if (projCode.length() != 0)
	          {
	        	  org.apache.poi.ss.usermodel.Cell cellProjName = row.getCell(PROJ_NAME_POS);
	            String projName = null;
	            if (cellProjName != null) {
	              projName = cellProjName.getStringCellValue();
	              if (projName != null) projName = projName.trim();
	            }
	            if ((projName == null) || (projName.length() == 0)) {
	              projName = "N/A";
	            }

	            org.apache.poi.ss.usermodel.Cell cellSubjCode = row.getCell(SUBJ_CODE_POS);
	            if (cellSubjCode != null) {
	              String subjCode = cellSubjCode.getStringCellValue();
	              if (subjCode != null) {
	                subjCode = subjCode.trim();
	                if (subjCode.length() != 0)
	                {
	                  subjCode = subjCode + projCode;
	                  cellSubjCode.setCellValue(subjCode);

	                  org.apache.poi.ss.usermodel.Cell cellSubjName = row.getCell(SUBJ_NAME_POS);
	                  if (cellSubjName != null)
	                    cellSubjName.setCellValue(projName);
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	    return workbook;
	}
}
