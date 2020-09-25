/*
 * 文件名：RskValAssetCode004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.util.ExcelUtilForRisk;
import com.joyintech.tams.invest.risk.valtblimport.filtertools.filter.Filter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.TreeNode;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.PriviewParamDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode003Service;

/**
 * 估值表资产科目映射明细维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月12日
 * @see RskValAssetCode004Controller
 * @since 1.0
 */
@Controller
@PageId("RskValAssetCode003")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode003")
public class RskValAssetCode003Controller extends BaseController{
    
    @Autowired
    private RskValAssetCode003Service rskValAssetCode003Service;
    
    @Autowired
    private ProgressBarService progressBarService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, new BussinessDictCode[] {BussinessDictCode.科目代码级别类型,BussinessDictCode.估值过滤器});
        String id = this.getParam("id");
        String name = this.getParam("name");
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return super.init(model);
    }
    
    /***
     * 解析源科目树
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAllTreeSourceNode")
    public List<TreeNode> getAllTreeSourceNode(@RequestBody PriviewParamDto dto)
        throws Exception {
        List<TreeNode> list = new ArrayList<>();
        //起始行、科目代码列 字母转数字
        dto.setAssetCodeColIndex(ExcelUtilForRisk.columnToIndex(dto.getAssetCodeColIndexLetter()));
        if (StringUtil.isNotEmpty(dto.getFileName())) {
        	//---modify by 裴家俊 2019-0514---start---添加过滤器------
        	List<List<String>> dataList =new ArrayList<List<String>>();
        	Workbook wb = ExcelUtil.loadWorkbook(dto.getFileName(), null, null);
        	Filter filter = null;
        	 if (StringUtil.isNotEmpty(dto.getFilterId())) {
        		 if(dto.getFilterId().startsWith(BussinessConstant.VALUATION_FILTER_MERGECOL)) {
        			 filter = (Filter) Class.forName(BussinessConstant.VALUATION_FILTER_URL+BussinessConstant.VALUATION_FILTER_MERGECOL).newInstance();
        		 }else {
        			 filter = (Filter) Class.forName(BussinessConstant.VALUATION_FILTER_URL+dto.getFilterId()).newInstance();
        		 }
        	 }
    		if(filter != null) {
    			if(dto.getFilterId().startsWith(BussinessConstant.VALUATION_FILTER_MERGECOL)) {
    				wb = filter.doFilter(wb,0,dto.getFilterId().substring(BussinessConstant.VALUATION_FILTER_MERGECOL.length() + 1));
    			}else {
    				wb = filter.doFilter(wb,0);
    			}
    			dataList=readToList(0, dto.getStartRow()-1, dto.getStartRow()-1,wb);
    		}
    		else{
             dataList = readToList(0, dto.getStartRow()-1, dto.getStartRow()-1,wb);
    		}
            if (null != dataList) {
                list=  rskValAssetCode003Service.getAllTreeSourceNode(dataList,dto);
            }
          //---modify by 裴家俊 2019-0514---start---------
        }
        return list;
    }
    
    /**
     * 获取目标科目映射树
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAllTreeTargetNode")
    public List<TreeNode> getAllTreeTargetNode(){
        List<TreeNode> list = new ArrayList<TreeNode>();
        list=  rskValAssetCode003Service.getAllTreeTargetNode();
        return list;
    }
    
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile() {
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
    
    /**
     * 解析excel
     * <功能详细描述>
     * @param sheetIndex
     * @param skipRows
     * @param titleRow
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private List<List<String>> readToList(int sheetIndex, int skipRows, int titleRow, Workbook wb)
        throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(titleRow).getPhysicalNumberOfCells();
            //这步check多余   双表头无法读出数据
            /*Row tRow = sh.getRow(titleRow);*/
            // 列标题为空的话结束
            /*for (int c = 0; c < cells; c++ ) {
                if (tRow.getCell(c) == null
                    || StringUtil.isEmpty(getStringCellValue(tRow.getCell(c)))) {
                    cells = c;
                    break;
                }
            }*/

            for (int i = skipRows; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    break;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空继续
                if (dataFlag) {
                    continue;
                }
                ls.add(r);
            }
        }
        return ls;
    }
    
    private static String getStringCellValue(Cell cell) {
        String value = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue(),
                        DateUtil.DEFAULT_ANOTHER_FORMAT);
                    return value;
                }
                value = ExcelUtil.point.format(cell.getNumericCellValue());
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case FORMULA: // 公式
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
                break;
            case BLANK: // 空值
                break;
            case BOOLEAN: // Boolean
                break;
            case ERROR: // 故障
                break;
            default:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue());
                }
                break;
        }

        return value;
    }
}
