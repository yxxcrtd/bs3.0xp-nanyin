package com.joyintech.tams.svreport.chb.investtradereg.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.component.svrreport.chb.dto.ChbInvestTradeSubDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.svreport.chbsd.assetdealsub.service.SdAssetDealRegBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.chb.investtradereg.service.InvestTradeRegist001Service;
import com.joyintech.tams.svreport.chb.investtradereg.service.InvestTradeRegistMService;

@Controller
@PageId("SvrInvestTradeRegist001")
@RequestMapping("/chb/investtraderegist/InvestTradeRegist001")
public class InvestTradeRegist001Controller extends BaseController{

	@Autowired
	private InvestTradeRegistMService investTradeRegistMService;
	@Autowired
	private InvestTradeRegist001Service investTradeRegist001Service;
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SdAssetDealRegBatchService sdAssetDealRegBatchService;
	/**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
    	Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
    	super.setSelectOptionToModel(model, DictCode.报送数据状态,
                DictCode.报送状态,DictCode.报送反馈状态,
                DictCode.是否_中债);
//        model.addAttribute("searchDataStart", DateUtil.addDay(DateUtil.getSysDate(),-1));
//        model.addAttribute("searchDataEnd", DateUtil.getSysDate());
//        model.addAttribute("statusT", DictCodeListExt2.报送数据状态_新建.getValue());
//        model.addAttribute("sendStatusT", DictCodeListExt2.报送状态_手工处理.getValue());
        return super.init(model);
    }
    
    /**
     * @Description 交易登记分页查询
     * @return Page<RaiseTotalRegist001Dto>
     * @version 3.0 2019年1月25日
     */
    @ResponseBody
    @RequestMapping(SVReportGlobalConstant.SEARCH_PAGE)
    public Page<InvestTradeRegist001Dto> selectAllData() {
        Page<InvestTradeRegist001Dto> page = investTradeRegistMService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
    	Map<String, Object> pageParam = RequestUtil.getPageParam();
    	if(pageParam.containsKey("keyList")){
          List<InvestTradeRegist001Dto> dtoList = investTradeRegistMService.selectForExport(RequestUtil.getParameters(getRequest()));
          File outFile = investTradeRegist001Service.makeFile(
              "/com/joyintech/tams/svreport/chb/investtradereg/conf/export_conf.xml", dtoList);
          return ExportUtil.export(
              this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                   + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                   + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
              outFile, true);
    	}
    	else{
          return investTradeRegist001Service.export(this.getPageId(), RequestUtil.getPageParam());
    	}
    }
    
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @ResponseBody
    @RequestMapping(value = "/manual" ,method = RequestMethod.POST)
    public AjaxResult doUpdate(@RequestBody Map<String,Object> map) {
    	investTradeRegistMService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月25日
     */
     @ResponseBody
 	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
 	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
    	boolean isDuplicate = investTradeRegistMService.cancelMannul(map);
        // 是否有重复数据(同时有已报送和未报送数据)
        if (isDuplicate){
            AjaxResult ajaxResult = new AjaxResult(this.getMessage("REPORT_COMMON_I_0030"),MessageType.错误消息);
            // 防止提示信息后卡死
            ajaxResult.setStatuCode(HttpStatus.OK.value());
            return ajaxResult;
        } else {
            return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
        }
 	}
     
     /**
      * 行内交易编码下拉框
      * @return 产品名称列表
      */
     @RequestMapping("/getTradeNo")
     @ResponseBody
     public List<SelectOptionDto> getRegCode() {
         return investTradeRegistMService.queryTradeNoCodeList(RequestUtil.getPageParam());
     } 
     
     /**
      * 功能:删除指定字符
      * @since 1.6.0
      * @version 1.6.0
      * @author：weiyuqiang 2019年2月15日
      * @param sourceString
      * @param chElemData
      * @return 
      * String
      */
     @SuppressWarnings("unused")
     private String deleteCharString(String sourceString, char chElemData) {
         String deleteString = "";
         for (int i = 0; i < sourceString.length(); i++) {
             if (sourceString.charAt(i) != chElemData) {
                 deleteString += sourceString.charAt(i);
             }
         }
         return deleteString;
     }
     
     @ResponseBody
     @RequestMapping("reportCheck")
     public Map<String,Object> reportCheck() {
         return investTradeRegistMService.reportCheck(RequestUtil.getPageParam());
     }

    /**start  中债一二期直连代码  add  by  wangzehui  20201012*/

    /**
     * 中债直连报送
     *
     * @param map
     *            传入需要报送的主键
     * @return 反馈信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/submitChb", method = RequestMethod.POST)
    public AjaxResult doSubmit(@RequestBody Map<String, Object> param) throws Exception{

        List<InvestTradeRegist001Dto> list=new ArrayList<>();
        // 选择行数据时
        if (param.containsKey("keyList")) {
            String keyList = param.get("keyList").toString();
            try {
                list = JsonUtils.json2List(keyList,
                        InvestTradeRegist001Dto.class);
            }
            catch (IOException e) {
                // e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }else{
            Page<InvestTradeRegist001Dto> page = investTradeRegistMService.selectForPage(RequestUtil.getPageParam());
            if(page!=null&&page.getRoot()!=null&&page.getRoot().size()>0){
                list = page.getRoot();
            }
            //查询数据
        }

        //报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
        List<ChbInvestTradeSubDto> dataList = new ArrayList<ChbInvestTradeSubDto>();
        for(InvestTradeRegist001Dto sdDto:list){
            ChbInvestTradeSubDto target = new ChbInvestTradeSubDto();
            BeanUtil.copyPropertiesIgnoreNull(target, sdDto);
            dataList.add(target);
        }

        sdAssetDealRegBatchService.sendBatchCommon(dataList);

        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0018"));
    }

    /**end  中债一二期直连代码  add  by  wangzehui  2020100910*/
}
