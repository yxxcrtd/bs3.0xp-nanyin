/*
 * 文件名：FinDepositTarget001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.stock.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.stock.dto.FinStockInfoDto;
import com.joyintech.tams.invest.asset.stock.service.FinStockInfoService;

import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票标的
 * @author yegang
 * @version 1.0 2019年6月13日
 * @see FinStockInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("FinStockInfo001")
@RequestMapping("/invest/asset/stock/FinStockInfo001")
public class FinStockInfo001Controller extends BaseController {
    @Autowired
    private FinStockInfoService finStockInfoService;
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 初始化数据字典下拉框
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.是否沪港通或深港通);
        this.setSelectOptionToModel(model, DictCode.上市板);
        this.setSelectOptionToModel(model, DictCode.是否);
        model.addAttribute("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinStockInfoDto> selectAll()
        throws Exception {
       Page<FinStockInfoDto> page = finStockInfoService.selectForPage(
                 RequestUtil.getParameters(getRequest()));
               return page;
    }
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String param)
        throws Exception{
        return finStockInfoService.getFinprodMarketIdList(param);
    }

    /**
     * 删除check
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinStockInfoDto dto, BindingResult result) {
    	AjaxResult ajax =commonCheck(result, dto.getOperateType());
		WflValidInputParameter parameter = new WflValidInputParameter();
		if(null==dto.getFinprodMarketId()){
			for(FinStockInfoDto entity:dto.getSelectedItem()){
				if(null!=entity.getFinprodMarketId()){
					entity.setFinprodMarketId(entity.getFinprodMarketId().substring(0,entity.getFinprodMarketId().indexOf(".")));
				    parameter.setPageId("FinStockInfo002");
				    parameter.setBusinessDataPk1(entity.getFinprodIdStr());
				    boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
			        if(isInWfl){
			        	Map<String, Object> param= new HashMap<String, Object>();
			        	param.put("pageId", "FinStockInfo002");
			        	param.put("pk1", entity.getFinprodIdStr());
			        	String username =finStockInfoService.getIsInWorkFlowReturnUser(param);
			        	ajax.appendMessage(new ValidMessage(getMessage("FIN_ASSET_STOCK_2", username)));
			        }
				}
			}
		}else{
			dto.setFinprodMarketId(dto.getFinprodMarketId().substring(0,dto.getFinprodMarketId().indexOf(".")));
		    parameter.setPageId("FinStockInfo002");
		    parameter.setBusinessDataPk1(dto.getFinprodIdStr());
		    boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
	        if(isInWfl){
	        	Map<String, Object> param= new HashMap<String, Object>();
	        	param.put("pageId", "FinStockInfo002");
	        	param.put("pk1", dto.getFinprodIdStr());
	        	String username =finStockInfoService.getIsInWorkFlowReturnUser(param);
	        	ajax.appendMessage(new ValidMessage(getMessage("FIN_ASSET_STOCK_2", username)));
	        }
		}
		
    	return ajax;
    }

    /**
     * 编辑按钮check
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(@RequestBody @Validated FinStockInfoDto dto, BindingResult result) {
    	AjaxResult ajax =commonCheck(result, dto.getOperateType());
    	return ajax;
    }


    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinStockInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinStockInfoDto> processParameter = new ProcessParameter<FinStockInfoDto>();
        WorkFlowParameter<FinStockInfoDto> params = new WorkFlowParameter<FinStockInfoDto>();
        params.setPageId("FinStockInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票信息.getValue());
      //  params.setBusinessType(DictCodeList.共通业务类型_项目类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinStockInfo001.finprodMarketId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("FinStockInfo001.finprodAbbr")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodAbbr()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finStockInfoService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinStockInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finStockInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
//}
}