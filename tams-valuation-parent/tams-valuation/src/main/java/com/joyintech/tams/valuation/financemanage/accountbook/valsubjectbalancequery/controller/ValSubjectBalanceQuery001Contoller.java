/*
 * 文件名：ValSubjectBalanceQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
/**
 * 
 * 科目余额查询
 * @author wanggengyun
 * @version 1.0 2018年5月16日
 * @see ValSubjectBalanceQuery001Contoller
 * @since 1.0
 */
@Controller
@PageId("ValSubjectBalanceQuery001")
@RequestMapping("/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery001")
public class ValSubjectBalanceQuery001Contoller extends BaseController {
    
    /**
     * ValSubjectBalanceQuery001Service
     */
    @Autowired
    private ValSubjectBalanceQuery001Service valSubjectBalanceQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Value("${application.valuation.maxReportIOnceTime:100000}")
    private Integer maxReportIOnceTime;
    
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.科目级别, DictCode.是否);
        model.addAttribute("yearOrNo", DictCodeList.是否_是);
//        model.addAttribute("FINPRODID_LIST",JsonUtils.objectArray2Json(valAssetBook001Service.getBooksetIdForSelect().toArray()));
//        String sysDate = DateUtil.format(baseCommonService.getSysDate(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        
        model.addAttribute("initDateRange", sysDate + " - " + sysDate);
        model.addAttribute("initCCY", DictCodeList.币种_人民币_CNY.getValue());
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
    public Page<ValSubjectBalanceQuery001Dto> findPage() throws Exception{
        return valSubjectBalanceQuery001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public List<SelectOptionDto> getBooksetId() {
        return valSubjectBalanceQuery001Service.getBooksetId();
    }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId(){
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }
    
    /**
     * 所属科目下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getSubjectNo")
    @ResponseBody
    public Page<SelectOptionDto> getSubjectNo(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return valSubjectBalanceQuery001Service.queryValBokSubject(param);
    }
    
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
    public ResponseEntity<byte[]> export()throws Exception {
            String datas = this.getParam("datas");
            Map<String, Object> paraMap = RequestUtil.getPageParam();
            List<ValSubjectBalanceQuery001Dto> listdto;
            if (datas == null) {
                listdto = valSubjectBalanceQuery001Service.getAllList(paraMap);
            }
            else {
                listdto = JsonUtils.json2List(datas, ValSubjectBalanceQuery001Dto.class);
            }
            File outFile = valSubjectBalanceQuery001Service.makeFile(
                "/com/joyintech/tams/valuation/financemanage/accountbook/valsubjectbalancequery/config/export_ValSubjectBalanceQuery001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        }
    
    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck(@RequestBody Map<String,Object> paraMap) {
    	paraMap.put("maxReportIOnceTime",maxReportIOnceTime);
    	return valSubjectBalanceQuery001Service.reportCheck(paraMap);
    }
}
