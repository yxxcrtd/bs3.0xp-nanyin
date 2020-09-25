package com.joyintech.tams.invest.risk.valtblimport.filtertools;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.AbstractFilter;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.PoiCellUtil;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.util.XlsUtil;

/**
 * MergeColsFilter
 * 
 * @author 孟宏伟 备注:合并科目号：科目号+资产代码 ;科目号替换为底层资产名称
 */
public class MergeColsFilter extends AbstractFilter {
	@Override
	public Workbook doFilter(Workbook workbook, int sheetIndex, String param) throws Exception {

		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		// 格式化参数
		formatParam(param, paramMap);
		// 先合并和替换
		MergeOrReplace(workbook, sheetIndex, paramMap);
		return workbook;
	}

	private void MergeOrReplace(Workbook workbook, int sheetIndex, Map<String, String> param) {

		Sheet sheet = workbook.getSheetAt(sheetIndex);

		if (param != null && !param.isEmpty()) {

			Iterator<String> iter = param.keySet().iterator();

			while (iter.hasNext()) {
				// 源列
				String source = (String) iter.next();
				// 目标列
				String dest = param.get(source);

				//true表示合并，false表示替换
				boolean isMerge = source.endsWith("#");
				
				source = source.substring(0,source.length()-1);
				
				int sourceCOL_ = XlsUtil.convertColumnCodeToSeq(source) - 1;
				int destCOL_ = XlsUtil.convertColumnCodeToSeq(dest) - 1;

				for (org.apache.poi.ss.usermodel.Row row : sheet) {
					// 源列值
					String sourceStr = "";
					// 合并后的结果
					String destStr = "";

					Cell sourceCell = row.getCell(sourceCOL_);
					Cell destCell = row.getCell(destCOL_);

					if (sourceCell != null) {
						sourceStr = PoiCellUtil.getCellValue(sourceCell);
					}
					if (destCell != null) {
						destStr = PoiCellUtil.getCellValue(destCell);
						if (!StringUtil.isBlank(sourceStr)) {
							destStr = destStr + "_" + sourceStr;
						}
					}

					//表示合并
					if(isMerge) {
						row.createCell(destCOL_).setCellValue(destStr);
					}
					//表示替换
					else {
						row.createCell(destCOL_).setCellValue(sourceStr);
					}
				}
				
			}
		}
	}

	/**
	 * #:表示合并的列，@：表示替换的列
	 * 支持的param:A#E_..#.._@.._..#.._F@B_xx@yy_..@...@..,把A列合并到E列,F替换掉B列的值
	 * 
	 * @param param
	 */
	private void formatParam(String param, Map<String, String> paramMap) {
		if (Pattern.matches("^([A-Za-z]+(#|@)[A-Za-z]+)(_[A-Za-z]+(#|@)[A-Za-z]+)*$", param)) {
//			// 合并map
//			Map<String, String> _map_join = new LinkedHashMap<String, String>();
//			// 替换map
//			Map<String, String> _map_repl = new LinkedHashMap<String, String>();

			String[] arr = param.split("_");

			for (String tmp1 : arr) {
				String[] arr2 = tmp1.split("#");
				if (arr2.length < 2) {//
					arr2 = tmp1.split("@");
					if (arr2.length == 2) {
						// 替换
						paramMap.put(arr2[0]+"@", arr2[1]);
					}
				} else {// 合并
					paramMap.put(arr2[0]+"#", arr2[1]);
				}
			}
//			result[0] = _map_join;
//			result[1] = _map_repl;
		}
	}
	
}
