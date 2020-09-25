/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.psbc.bankvoucherquery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.psbc.bankvoucherquery.dto.BankValVoucherQuery001Dto;
import com.joyintech.tams.psbc.bankvoucherquery.dto.BankValVoucherQuery001SearchDto;
import com.joyintech.tams.psbc.bankvoucherquery.service.BankValVoucherQuery001Service;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.controller.ValVoucherQuery001Controller;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 模板示例<br>
 * 凭证查询
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Controller
 * @since 2.0
 */
@Controller
@PageId("BankValVoucherQuery001")
@RequestMapping("/psbc/bankvoucherquery/BankValVoucherQuery001")
public class BankValVoucherQuery001Controller extends BaseController {
    
    /**
     * ValVoucherQuery001Service
     */
    @Autowired
    private BankValVoucherQuery001Service valVoucherQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.会计场景代码);
        this.setSelectOptionToModel(model, DictCode.凭证类型);
        // 默认系统平台日
        String sysDate = DateUtil.format(baseCommonService.getSysDate(),"yyyy-MM-dd");
        if(StringUtil.isNotEmpty(sysDate)){
            model.addAttribute("sysDate", sysDate+" - "+ sysDate);
        }
        else{
            model.addAttribute("sysDate", "");
        }
        List<SelectOptionDto> booksetIds = valVoucherQuery001Service.getBooksetId();
        if (!StringUtil.isEmptyList(booksetIds)) {
            model.addAttribute("booksetIds", JsonUtils.object2Json(booksetIds));
            model.addAttribute("booksetId", booksetIds.get(0).getId());
        } 
        return super.init(model);
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
        return valVoucherQuery001Service.getBooksetId();
    }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getTableId")
    @ResponseBody
    public List<SelectOptionDto> getTableId() {
        return valVoucherQuery001Service.getTableId();
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
    	Map<String, Object> param = RequestUtil.getPageParam();
    	param.put("noBooksetType", DictCodeList.账套类别_账套模板.getValue());
        return valAssetBook001Service.findBooksetIdByPage(param);
    }
    
    /**
     * 业务类别下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("getBusiClass")
    @ResponseBody
    public List<SelectOptionDto> getBusiClass() {
        return valVoucherQuery001Service.getBusiClass();
    }

    /**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkQuery")
    public AjaxResult checkQuery(@Valid @RequestBody BankValVoucherQuery001SearchDto dto,
            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BankValVoucherQuery001Dto> findPage(BankValVoucherQuery001SearchDto dto) throws Exception {
        return valVoucherQuery001Service.findPage(dto);

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
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params= this.getRequestMap();
        BankValVoucherQuery001SearchDto dto = (BankValVoucherQuery001SearchDto)JsonUtils.map2entity(params, BankValVoucherQuery001SearchDto.class);
        List<BankValVoucherQuery001Dto> listdto;
        if (datas == null) {
            listdto = valVoucherQuery001Service.selectAll(dto);
        }
        else {
            listdto = JsonUtils.json2List(datas, BankValVoucherQuery001Dto.class);
        }
        File outFile = valVoucherQuery001Service.makeFile(
                "/com/joyintech/tams/psbc/bankvoucherquery/conf/export_voucherquery.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 入账日期区间
        String bookDateBegin = this.getParam("bookDateBegin");
        String bookDateEnd = this.getParam("bookDateEnd");
        if(StringUtil.isNotEmpty(bookDateBegin)){
            paraMap.put("bookDateBegin", DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
        }
        if(StringUtil.isNotEmpty(bookDateEnd)){
            paraMap.put("bookDateEnd", DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
        }
        // 发生日期区间
        String happenDateBegin = this.getParam("happenDateBegin");
        if(StringUtil.isNotEmpty(happenDateBegin)){
            paraMap.put("happenDateBegin", DateUtil.parseDate(happenDateBegin, "yyyy-MM-dd"));
        }
        String happenDateEnd = this.getParam("happenDateEnd");
        if(StringUtil.isNotEmpty(happenDateEnd)){
            paraMap.put("happenDateEnd", DateUtil.parseDate(happenDateEnd, "yyyy-MM-dd"));
        }
        return paraMap;
    }

}
