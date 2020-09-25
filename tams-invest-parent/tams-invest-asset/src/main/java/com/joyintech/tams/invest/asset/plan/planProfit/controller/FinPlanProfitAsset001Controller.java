/*
 * 文件名：FinPlanProfitAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.plan.planProfit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.asset.plan.planProfit.dto.FinPlanProfitAssetDto;
import com.joyintech.tams.invest.asset.plan.planProfit.service.FinPlanProfitAssetService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 计划类资产（利率型）查询和删除controller
 * 
 * @author liuwei
 * @version 1.0 2018年9月14日
 * @see FinPlanProfitAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("FinPlanProfitAsset001")
@RequestMapping("/invest/asset/plan/planProfit/FinPlanProfitAsset001")
public class FinPlanProfitAsset001Controller extends BaseController{
    /**
     *平台日
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    
    /**
     * 计划类资产（利率型）service
     */
    @Autowired
    private FinPlanProfitAssetService finPlanProfitAssetService;
    
    /**
     * 计划类资产（利率型）查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model,
        		DictCode.计划类资产一级分类,
        		DictCode.计划类资产二级分类,
        		/* 添加数据字典、zhuyongkang、2019/7/19、start*/
        		DictCode.行内资产二级分类,
        		DictCode.行内资产三级分类,
        		DictCode.行内资产四级分类,
        		DictCode.行内资产五级分类,
        		/* 添加数据字典、zhuyongkang、2019/7/19、end*/
            DictCode.清算速度,DictCode.币种,DictCode.是否);
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    /**
     * 资产代码的联想文本框事件
     * 
     * @param param 输入的参数
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinProdMarketId")
    public List<SuggestDto> getFinProdMarketId(String param)
        throws Exception {
        List<SuggestDto> temp = finPlanProfitAssetService.getFinProdMarketId(param);
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinPlanProfitAssetDto> selectAll()
        throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<FinPlanProfitAssetDto> page = finPlanProfitAssetService.selectForPage(map);
        return page;
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
    public AjaxResult check(@RequestBody  @Validated({
            ValidateGroupDelete.class}) FinPlanProfitAssetDto dto, BindingResult result) {
    	AjaxResult checkResult = commonCheck(result, dto.getOperateType());
//    	// 当成立日期与终止日期查询条件同时选择时，终止日期开始日必须大于等于成立日期结束日
//    	if (StringUtil.isNotEmpty(dto.getEndVDate()) && StringUtil.isNotEmpty(dto.getStartMDate())) {
//    		if (DateUtil.compareDate(dto.getEndVDate(), dto.getStartMDate()) > 0) {
//    			checkResult.getErrorList().add(new ValidMessage("vdate", getMessage("FW_E_0009", this.getMessage("FinPlanProfitAsset001.endVDate"), this.getMessage("FinPlanProfitAsset001.startMDate"))));
//    			checkResult.setMessageType(MessageType.错误消息);
//    		}
//    	}
    	//如果平台日不为空,校验平台日和终止日期
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) 
                && StringUtil.isNotEmpty(platformDate)){
            if (DateUtil.compareDate(dto.getMdate(), platformDate.getSysDate()) < 0){
                ArrayList<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage("终止日期不能小于平台日！"));
                checkResult.setErrorList(errorList);
            }
        }
    	return checkResult;
    }
    /**
     * 删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  @Validated({
            ValidateGroupDelete.class}) FinPlanProfitAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinPlanProfitAssetDto> processParameter = new ProcessParameter<FinPlanProfitAssetDto>();
        WorkFlowParameter<FinPlanProfitAssetDto> params = new WorkFlowParameter<FinPlanProfitAssetDto>();
        params.setPageId("FinPlanProfitAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_利率型_信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：资产代码 + "(" + 市场代码+ ")" + 资产名称 + "(" + 资产名称+ ")"
        params.setComment(entity -> getMessage("FinPlanProfitAsset001.finProdMarketId") + "(" + entity.getFinprodMarketId() + ")"
                +getMessage("FinPlanProfitAsset001.finProdName") + "(" + entity.getFinprodName()+ ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finPlanProfitAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinPlanProfitAssetDto[dto.getSelectedItem().size()]));
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
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
        // 修改导出错误 zhuyongkang
//		param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finPlanProfitAssetService.export(this.getPageId(),param);

    }

}
