/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.service.OwnerEquityChangeNew001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery001Service;

/**
 * 所有者权益变动Controller
 * 
 * @author lby
 * @version 1.0 2020年11月24日
 * @see OwnerEquityChangeNew001Controller
 * @since 1.0
 */
@Controller
@PageId("OwnerEquityChangeNew001")
@RequestMapping("/valuation/accounadjustmanage/ownerequitychangenew/OwnerEquityChangeNew001")
public class OwnerEquityChangeNew001Controller  extends BaseController {

    @Autowired
    private OwnerEquityChangeNew001Service ownerEquityChangeNew001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    @Autowired
    private ValProfitQuery001Service valProfitQuery001Service;
    @Autowired
    private BooksetCommonService booksetCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表期限类型);
        this.setSelectOptionToModel(model, DictCode.上期维度);
        // model.addAttribute("lastDimenVal", DictCodeList.上期维度_环比.getValue());
        model.addAttribute("reportDimen", DictCodeListExt2.批处理账套类型_账套组.getValue());
        super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
        return super.init(model);
    }
    /**
     * 查询条件检查
     * 
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid OwnerEquityChangeNew001Dto dto, BindingResult result) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        if (StringUtils.isEmpty(dto.getReportDimen())) {
            return new AjaxResult("报表维度不能为空！", MessageType.错误消息);
        } else {
            if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(dto.getReportDimen())) {
                // 账套代码不能为空
                if (StringUtil.isEmpty(dto.getBooksetId())) {
                    return new AjaxResult("请输入账套代码。", MessageType.错误消息);
                }
                // 账套代码必须存在
                String[] booksetIds = new String[1];
                booksetIds[0] = dto.getBooksetId();
                List<Product> booksetList = booksetCommonService.findProductByIds(booksetIds);
                if (booksetList == null || booksetList.isEmpty()) {
                    return new AjaxResult("账套代码不存在。", MessageType.错误消息);
                }
            } else {
                // 账套组不能为空
                if (StringUtil.isEmpty(dto.getBooksetGroup())) {
                    return new AjaxResult("请输入账套组。", MessageType.错误消息);
                }
            }
        }
        // 处理开始和结束日
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("reportTermType", dto.getReportTermType());
        pageParam.put("valDate", dto.getValDate());
        pageParam.put("reportTerm", dto.getReportTerm());
        pageParam.put("lastDimen", dto.getLastDimen());
        ownerEquityChangeNew001Service.changeMapData(pageParam);
        String valueDateStart = DateUtil.format(
                DateUtil.addDay(DateUtil.parse(String.valueOf(pageParam.get("thisTermVdate"))), 1),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        String valueDateEnd = DateUtil.format(DateUtil.parse(String.valueOf(pageParam.get("thisTermMdate"))),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        dto.setValueDateStart(valueDateStart); // 本期日期
        dto.setValueDateEnd(valueDateEnd); // 期初日期
        ajaxResult.setData(dto);
        return ajaxResult;
    }
    
    /**
	 * 获得 表头信息
	 * 
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("getHeadInfo")
	public Map<String, String> getHeadInfo(@RequestBody Map<String, Object> pageParam)
			throws ParseException {
		Map<String, String> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date reportDate = ownerEquityChangeNew001Service.analyseDate(pageParam);
		
		map.put("statementDate", sdf.format(reportDate));
		map.put("statementType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, (String)pageParam.get("reportTermType")));
		map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
		return map;
	}
    
    
    /**
     * 
     * 分頁查詢
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<OwnerEquityChangeNew001Dto> selectBalanceStatement()
        throws Exception {
        List<OwnerEquityChangeNew001Dto> dtos = ownerEquityChangeNew001Service.selectList(RequestUtil.getParameters(getRequest()));
        dtos = ownerEquityChangeNew001Service.doDealStyle(dtos,"HTML");
        return dtos;
    }

    /**
     * 检索账套代码(只取理财账套)
     *
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, Object> params = new HashMap<>();
        params.put("param",param);
        return valAssetBook001Service.getBooksetId2(params);
    }
    
    /**
     * 导出信息
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> exportExcel()
        throws Exception {
    	Map<String, Object> params = RequestUtil.getParameters(getRequest());
    	return ownerEquityChangeNew001Service.exportExcel(params);
    }
    
    /**
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getTermLink")
    public List<SelectOptionDto> getTradeMarket() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        String reportTermType = (String) param.get("reportTermType");

        param.put("dict_code_parent", DictCode.报表期限类型.getValue());
        param.put("item_code_parent", param.get("reportTermType"));
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.半年.getValue());
            return valProfitQuery001Service.getLinkSelectOption(param);
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.季度.getValue());
            return valProfitQuery001Service.getLinkSelectOption(param);
        }
        return null;
    }
    
    /**
     * 获取默认日期信息
     * 
     * @return OwnerEquityChange001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getDateInfo")
    public OwnerEquityChangeNew001Dto getDateInfo()
        throws Exception {
        return ownerEquityChangeNew001Service.getDateInfo(RequestUtil.getPageParam());
    }
    
}
