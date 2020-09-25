/*
 * 文件名：FinFloatMgtExpQuotation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.controller;
/**
 * 浮动管理费报价管理-查询controller
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotation001Dto
 * @since 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.service.FinFloatMgtExpQuotation001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月30日
 * @see FinFloatMgtExpQuotation001Controller
 * @since 1.0
 */
@Controller
@PageId("FinFloatMgtExpQuotation001")
@RequestMapping("/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001")
public class FinFloatMgtExpQuotation001Controller extends BaseController {
    /**
     * 浮动管理费报价管理service
     */
    @Autowired
    private FinFloatMgtExpQuotation001Service finFloatMgtExpQuotation001Service;
    /**
     * 业务编号组件
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
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
     * 浮动管理费报价查询
     * <功能详细描述>
     * @return Page<FinFloatMgtExpQuotation001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinFloatMgtExpQuotation001Dto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finFloatMgtExpQuotation001Service.selectForPage(param);
    }
    /**
     * 产品代码联想框
     * <功能详细描述>
     * @param search String
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
	public List<SelectOptionDto> getFinprodid(){
        return finFloatMgtExpQuotation001Service.getFinprodIds(RequestUtil.getPageParam());
    }
    /**
     * 导出信息
     * @return ResponseEntity<byte[]>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
//        return finFloatMgtExpQuotation001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
        
    	File outFile = null;
        List<FinFloatMgtExpQuotation001Dto> dtoList = this.finFloatMgtExpQuotation001Service.selectForList(
            RequestUtil.getPageParam());
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("FinProductBuild001");
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
         String value1 = isEleShow.get("FinProductBuild001_sendStatus");
         String value2 = isEleShow.get("send_remark_table");
        if(StringUtil.equals(value1, DictCodeList.是否_是.getValue())&&StringUtil.equals(value2, DictCodeList.是否_是.getValue())) {
        	outFile = finFloatMgtExpQuotation001Service.makeFile(
    	            "/com/joyintech/tams/invest/product/messageadjust/finfloatmgtexpquotation/conf/export_conf.xml",
    	            dtoList);
        }else if(StringUtil.equals(value1, DictCodeList.是否_否.getValue())&&StringUtil.equals(value2, DictCodeList.是否_否.getValue()) ) {
        	outFile = finFloatMgtExpQuotation001Service.makeFile(
    	            "/com/joyintech/tams/invest/product/messageadjust/finfloatmgtexpquotation/conf/export_conf_N.xml",
    	            dtoList);
        }
        return ExportUtil.export(this.getMessage(this.getPageId()) + GlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    /**
     * 删除，修改时的check
     * <功能详细描述>
     * @param dto FinFloatMgtExpQuotation001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinFloatMgtExpQuotation001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 启动工作流执行删除
     * <功能详细描述>
     * @param dto FinFloatMgtExpQuotation001Dto
     * @return
     * @throws Exception Exception
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody FinFloatMgtExpQuotation001Dto dto) throws Exception{
        ProcessParameter<FinFloatMgtExpQuotation001Dto> processParameter=new ProcessParameter<FinFloatMgtExpQuotation001Dto>();
        WorkFlowParameter<FinFloatMgtExpQuotation001Dto> params=new WorkFlowParameter<FinFloatMgtExpQuotation001Dto>();
        BussinessCodeParameter parameter=new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_浮动管理费报价调整);
        String code=baseGetBusinessId.buildBussinessCode(parameter);
        params.setPageId("FinFloatMgtExpQuotation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(code);
        params.setBusinessType(DictCodeList.共通业务类型_浮动管理费报价调整.getValue());
        processParameter.setOperationType(OperationType.DELETE);
        //业务说明："产品代码" + "(" + 产品代码 + ")" + "-"  + "浮动管理费报价生效日期" + "(" + 画面.生效日 + ")"
        params.setComment(entity -> this.getMessage("FinFloatMgtExpQuotation001.finprodId") + "("
                + entity.getFinprodMarketId() +")" +this.getMessage("FinFloatMgtExpQuotation002.effDate") + "("
                + DateUtil.format(entity.getEffDate()) +")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFloatMgtExpQuotation001Service.start(processParameter,
                dto.getSelectedItem1().toArray(new FinFloatMgtExpQuotation001Dto[dto.getSelectedItem1().size()]));
        return new AjaxResult(msg);
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
		if(StringUtil.isEmpty(finprodMarketId)){
			finprodMarketId = (String) map.get("finprodId");
		}
		if (StringUtil.isNotEmpty(finprodMarketId)) {
			map.put("finprodId", StringUtil.splitToList(GlobalConstant.COMMA, finprodMarketId));
		} else {
			map.put("finprodId", null);
		}
		map.put("finprodMarketId", null);
		map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		map.put("FINPROD_TYPE_TG_F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
		map.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
//		map.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());//只取净值型，不取货币型
		map.put("PROFIT_TYPE_03", "");
		List<SelectOptionDto> result = finFloatMgtExpQuotation001Service.queryLayerProdStyleList(map);
		Collections.sort(result, Comparator.comparing(SelectOptionDto::getText));
		return result;
  }
	
    /**
     * 发送销售接口状态
     *
     * @param FinMdateAdjustment001Dto
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sendFloatMgtExpQuotationSale")
    public AjaxResult sendFloatMgtExpQuotationSale(@RequestBody FinFloatMgtExpQuotation001Dto finFloatMgtExpQuotation001Dto) {
        //或许选择推送的数据，循环调用推送销售系统方法
        List<FinFloatMgtExpQuotation001Dto> dtoList = finFloatMgtExpQuotation001Dto.getSelectedItem1() ;
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;// 默认状态为推送销售系统
            for (FinFloatMgtExpQuotation001Dto fin001Dto : dtoList) {
                // 查询是否已经推送销售系统
                if(DictCodeList.发送状态_发送成功.equals(fin001Dto==null?null:fin001Dto.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
            if (sendFlag) {
                List<String> finProdIds = new ArrayList<>();
                for (FinFloatMgtExpQuotation001Dto model : dtoList) {
                    finProdIds.add(model.getFinprodId());
                }
                // 推送产品分红至销售系统
                Map<String, String> map = finFloatMgtExpQuotation001Service.sendFloatMgtExpQuotationSale(finProdIds);
                errorList.add(new ValidMessage(map.get("msg")));
            }
        }
        return ar;
    }
	
	
}
