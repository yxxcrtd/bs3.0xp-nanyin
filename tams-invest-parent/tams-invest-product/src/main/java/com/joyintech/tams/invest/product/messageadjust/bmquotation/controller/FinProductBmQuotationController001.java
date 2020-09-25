/*
 * 文件名：FinProductBmQuotationController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.bmquotation.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.service.FinProductBmQuotationService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品业绩基准信息
 * 支持产品业绩基准调整的查询
 * @author 王泽慧
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationController001
 * @since 1.0
 */
@Controller
@PageId("FinProductBmQuotation001")
@RequestMapping("invest/product/messageadjust/bmquotation/FinProductBmQuotation001")
public class FinProductBmQuotationController001 extends BaseController {
    /**
     * finProductBmQuotationService
     */
    @Autowired
    private FinProductBmQuotationService finProductBmQuotationService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 开放计划组件
     */
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, new DictCode[] {DictCode.调整类型, DictCode.发送状态});
        
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }
    
    /**
     * 产品代码联想框 
     * @return List List
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList()
        throws Exception {
        return finProductBmQuotationService.getFinprodMarketIdList(getParam("search"));
    }
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductBmQuotationDto> selectAll()
        throws Exception {
        return finProductBmQuotationService.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinProductBmQuotationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinProductBmQuotationDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto =  dto.getSelectedItem().get(0);
        //判断流程中是否存在,如果流程不存在则说明是生成的首条业绩基准，组装撤销dto yjm start 20191015
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("FinProductBmQuotation002");
        parameter.setBusinessDataPk1(dto.getFinprodId());
        if (!mstWorkflowCommonService.isProcessData(parameter)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodId", dto.getFinprodId());
            map.put("effDate", dto.getEffDateStr());
            map.put("baseRuleId", dto.getBaseRuleId());
            map.put("baseRuleType", DictCodeListExt2.业绩基准类型_披露业绩基准.getValue());
            if (StringUtil.isNotEmpty(dto.getFinprodId()) && null != bussBenchMarketInfoService) {
                BussBenchMarkInfoDto bussBenchMarkInfoDto = bussBenchMarketInfoService.getByFinprodIdAndEffDate(map);
                dto.setBussBenchMarkInfoDto(bussBenchMarkInfoDto);
            }
            dto.setOpType(ACTION_DELETE);
            //add by menghongwei 支持分档产品 20190626 start
            if(StringUtil.isNotEmpty(dto.getpFinprodId())) {
                dto.setProdStyle(dto.getFinprodId());
                dto.setFinprodId(dto.getpFinprodId());
            }
        }
        //判断流程中是否存在,如果流程不存在则说明是生成的首条业绩基准，组装撤销dto yjm end 20191015
        ProcessParameter<FinProductBmQuotationDto> processParameter = new ProcessParameter<FinProductBmQuotationDto>();
        WorkFlowParameter<FinProductBmQuotationDto> params = new WorkFlowParameter<FinProductBmQuotationDto>();
        params.setPageId("FinProductBmQuotation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_产品业绩基准调整.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明："产品代码" + "(" + 选中记录.市场代码 + ")" + "-"  + "业绩基准报价生效日期" + "(" + 选中记录.生效日 + ")" 
        params.setComment(entity -> "产品代码" + "(" + entity.getFinprodMarketId() + ")" + "-" + "业绩基准报价生效日期" + "("
                                    + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductBmQuotationService.start(processParameter,dto);
        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {

        List<FinProductBmQuotationDto> dtoList = this.finProductBmQuotationService.selectForList(
            RequestUtil.getPageParam());
        File outFile= null;
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
         String value1 = isEleShow.get("FinProductBmQuotation001_sendStatus");
         String value2 = isEleShow.get("send_remark_table");
        if(StringUtil.equals(value1, DictCodeList.是否_是.getValue())&&StringUtil.equals(value2, DictCodeList.是否_是.getValue())) {
        	outFile = finProductBmQuotationService.makeFile(
                    "/com/joyintech/tams/invest/product/messageadjust/bmquotation/conf/export_conf.xml",
                    dtoList);
        }else if(StringUtil.equals(value1, DictCodeList.是否_否.getValue())&&StringUtil.equals(value2, DictCodeList.是否_否.getValue()) ) {
        	outFile = finProductBmQuotationService.makeFile(
                    "/com/joyintech/tams/invest/product/messageadjust/bmquotation/conf/export_conf_N.xml",
                    dtoList);
        } 
        return ExportUtil.export(this.getMessage(this.getPageId()) + GlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
    /**
     * 获取分层产品产品款型
	* @param finprodId
	* @return
	*/
	@ResponseBody
	@RequestMapping("queryLayerProdStyleList")
	public List<SelectOptionDto> queryLayerProdStyleList() {
		Map<String, Object> map = RequestUtil.getPageParam();
		String finprodMarketId = (String) map.get("finprodMarketId");
		if (StringUtil.isNotEmpty(finprodMarketId)) {
			map.put("finprodMarketId", StringUtil.splitToList(GlobalConstant.COMMA, finprodMarketId));
		} else {
			map.put("finprodMarketId", null);
		}
		map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		map.put("FINPROD_TYPE_TG_F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
		map.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
		map.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
		List<SelectOptionDto> result = finProductBmQuotationService.queryLayerProdStyleList(map);
		Collections.sort(result, Comparator.comparing(SelectOptionDto::getText));
		return result;
  }
	
	
 	    @ResponseBody
	    @RequestMapping("sendProductBmQuotation")
	    public AjaxResult sendProductBmQuotation(@RequestBody FinProductBmQuotationDto finProductBmQuotationDto) {
	        //或许选择推送的数据，循环调用推送系统方法
	        List<FinProductBmQuotationDto> dtoList = finProductBmQuotationDto.getSelectedItem() ;
	        AjaxResult ar = new AjaxResult() ;
	        if(dtoList.size() > 0){
	            List<ValidMessage> errorList = ar.getErrorList();
	            boolean sendFlag = true ;// 默认状态为销售系统
	            for (FinProductBmQuotationDto fin001Dto : dtoList) {
	                // 查询是否已经推送系统
	                if(DictCodeList.发送状态_发送成功.getValue().equals(fin001Dto.getSendStatus())){
	                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
	                    sendFlag = false ;
	                    return ar ;
	                }
	            }
	            if (sendFlag) {
	            	List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
	                for (FinProductBmQuotationDto model : dtoList) {
	                	Map<String,String> mapInfo =new HashMap<>();
						mapInfo.put("baseRuleId", model.getBaseRuleId());
						mapInfo.put("effDate", model.getEffDateStr());
						listMap.add(mapInfo);
	                }
	                // 推送产品分红至销售系统
	                Map<String, String> map = finProductBmQuotationService.sendProductBmQuotation(listMap);
	                errorList.add(new ValidMessage(map.get("msg")));
	            }
	        }
	        return ar;
	    }
}
