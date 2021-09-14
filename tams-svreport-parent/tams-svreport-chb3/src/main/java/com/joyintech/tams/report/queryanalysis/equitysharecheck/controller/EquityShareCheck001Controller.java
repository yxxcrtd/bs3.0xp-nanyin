package com.joyintech.tams.report.queryanalysis.equitysharecheck.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dto.EquityShareCheck001Dto;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.service.EquityShareCheck001Service;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.service.EquityShareCheckMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 投资者份额检查Controller
 * @author zhengxiaolian
 * @version 1.0 2019年11月12日
 * @see EquityShareCheck001Controller
 * @since 1.0
 */
@PageId("SvrEquityShareCheck001")
@RequestMapping("/svreport/queryanalysis/equitysharecheck/SvrEquityShareCheck001")
@Controller
public class EquityShareCheck001Controller extends BaseController{
	
    /**
     * 投资者份额检查servive
     */
    @Autowired
    private EquityShareCheckMService equityShareCheckMService;
    @Autowired
    private EquityShareCheck001Service equityShareCheck001Service;
    
	
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model,DictCode.投资者份额检查结果);
    	model.addAttribute("EQUDET_RESULT_NG", DictCodeListExt2.投资者份额检查结果_有偏差.getValue());
        model.addAttribute("defaultDate",DateUtil.addDay(DateUtil.getCurrentDate(), -1) );
        return super.init(model);
    }
    
    /**
    *
    * 业务画面查询事件
    *
    * @return 结果列表
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("searchPage")
   public Page<EquityShareCheck001Dto> selectAllData() {
       Page<EquityShareCheck001Dto> page = equityShareCheckMService.searchPage(RequestUtil.getPageParam());
       return page;
   }
   
   /**
    * 产品下拉框
    * @return 产品代码和名称列表
    */
   @RequestMapping( "getProd")
   @ResponseBody
   public List<SelectOptionDto> getProd() {
       return equityShareCheckMService.queryPrdNameList(RequestUtil.getPageParam());
   } 
   
   /**
    * 识别标识下拉框
    * @return 识别标识代码和名称列表
    */
   @RequestMapping( "getIndentifyMark")
   @ResponseBody
   public List<SelectOptionDto> getIndentifyMark(){
       return equityShareCheckMService.queryIndentifyMarkList(RequestUtil.getPageParam());
   } 
    
  
  /**
  *
  * 业务画面导出事件
  * @return 结果
  * @see
  * @since 1.0
  */
 @ResponseBody
 @RequestMapping("export")
 public ResponseEntity<byte[]> export() {
     List<EquityShareCheck001Dto> dtoList = equityShareCheck001Service.setDataList(RequestUtil.getParameters(getRequest()));
     File outFile = equityShareCheck001Service.makeFile(
         "/com/joyintech/tams/report/queryanalysis/equitysharecheck/conf/export_conf.xml", dtoList);
     return ExportUtil.export(
             "投资者份额检查" + GlobalConstant.OPEN_PARENTHESIS
                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
             outFile, true);
 }
   
}
