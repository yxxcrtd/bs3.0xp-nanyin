package com.joyintech.tams.report.queryanalysis.prosharecheck.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;
import com.joyintech.tams.report.queryanalysis.prosharecheck.service.ProShareCheckMService;

/**
 * 产品份额检查Controller
 * @author zhengxiaolian
 * @version 1.0 2019年12月17日
 * @see ProShareCheck001Controller
 * @since 1.0
 */
@PageId("SvrProShareCheck001")
@RequestMapping("/svreport/queryanalysis/prosharecheck/SvrProShareCheck001")
@Controller
public class ProShareCheck001Controller extends BaseController{
	
    /**
     * 产品份额检查servive
     */
    @Autowired
    private ProShareCheckMService proShareCheckMService;
	
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
   public Page<ProShareCheck001Dto> selectAllData() {
       Page<ProShareCheck001Dto> page = proShareCheckMService.searchPage(RequestUtil.getPageParam());
       return page;
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
      List<ProShareCheck001Dto> dtoList = proShareCheckMService.setDataList(RequestUtil.getParameters(getRequest()));
      File outFile = proShareCheckMService.makeFile(
          "/com/joyintech/tams/report/queryanalysis/prosharecheck/conf/export_conf.xml", dtoList);
      return ExportUtil.export(
              this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                   + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                   + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
              outFile, true);
  }
   
}
