/*
 * 文件名：ValOtcOpt001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valotcopt.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valotcopt.service.ValOtcOpt001Service;
/**
 * 
 * 场外期权交易
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOtcOpt001Controller
 * @since 1.0
 */
@Controller
@PageId("ValOtcOpt001")
@RequestMapping("/valuation/basedata/marketdata/valotcopt/ValOtcOpt001")
public class ValOtcOpt001Controller extends BaseController {
    
    /**
     * ValOtcOpt001Service
     */
    @Autowired
    private ValOtcOpt001Service valOtcOpt001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.交易方向);
        super.setSelectOptionToModel(model, DictCode.期权费支付类型);
        super.setSelectOptionToModel(model, DictCode.交易流程状态);
        return super.init(model);
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValOtcOpt001Dto> findPage() throws Exception{
        return valOtcOpt001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 账套下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valOtcOpt001Service.getBooksetId(RequestUtil.getPageParam());
    }
    
    /**
     * 交易编号下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getTotleTradeId")
    @ResponseBody
    public List<SuggestDto> getTotleTradeId() {
        return valOtcOpt001Service.getTotleTradeId(RequestUtil.getPageParam());
    }
    
    /**
     * 合约标的下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBenchmarkId")
    @ResponseBody
    public List<SuggestDto> getBenchmarkId() {
        return valOtcOpt001Service.getBenchmarkId(RequestUtil.getPageParam());
    }
    
//	/**
//	 * 撤销验证
//	 * @param dto
//	 * @param result
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("check")
//	public AjaxResult check(@RequestBody ValOtcOpt001Dto dto, BindingResult result) throws Exception {
//		
//        return commonCheck(result, dto.getOperateType());
//	}
//	
//    /**
//     * 启动撤销流程
//     * 
//     * @param dto 业务实体
//     * @return 画面地址
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = "revoke", method = RequestMethod.POST)
//    public AjaxResult revoke(@RequestBody ValOtcOpt001Dto dto, BindingResult br) {
//      String msg = StringUtils.EMPTY;
//        if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//        
//        ProcessParameter<ValOtcOpt001Dto> processParameter = new ProcessParameter<>();
//        WorkFlowParameter<ValOtcOpt001Dto> workFlowParameter = new WorkFlowParameter<>();
//        
//        try {
//            workFlowParameter.setPageId("ValOptInfo002"); // 页面id
//            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
//            workFlowParameter.setBusinessCode(item -> item.getTotleTradeId());// 业务代码编号
//            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_期权交易.getValue());// 业务类型
//            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
//                    entity.getTotleTradeId(), "场外期权交易"));// 当前工作流备注
//            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
//                
//            valOtcOpt001Service.start(processParameter, dto);
//            msg = getMessage("COMMON_I_0001"); // 处理正常结束
//        } catch (Exception e) {
//            this.getLogger().error("启动场外期权交易信息撤销流程失败：", e);
//            msg = e.getMessage();
//        }
//        return new AjaxResult(msg);
//    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> para= RequestUtil.getPageParam();
        List<ValOtcOpt001Dto> dtoList = new ArrayList<>();
        if (datas == null) {
            dtoList = valOtcOpt001Service.findAll(para);
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValOtcOpt001Dto.class);
        }
        
        File outFile = valOtcOpt001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valotcopt/config/export_valotcopt.xml",
                dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

}
