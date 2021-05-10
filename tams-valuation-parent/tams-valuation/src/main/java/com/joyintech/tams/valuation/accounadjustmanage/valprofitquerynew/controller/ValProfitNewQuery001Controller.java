/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.util.StringUtil;
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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.service.ValProfitNewQuery001Service;

/**
 * 利润表
 * 
 * @author 黄浩
 * @version 1.0 2020年5月31日
 * @see ValProfitNewQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValProfitNewQuery001")
@RequestMapping("/valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001")
public class ValProfitNewQuery001Controller extends BaseController {

    @Autowired
    private ValProfitNewQuery001Service valProfitNewQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

	@Autowired
	private BooksetCommonService booksetCommonService;
    @Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.报表期限类型);
		this.setSelectOptionToModel(model, DictCode.上期维度);
		model.addAttribute("reportDimen", DictCodeListExt2.批处理账套类型_账套组.getValue());
		super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
		return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValProfitNewQuery001Dto dto, BindingResult result) {
		if (StringUtils.isEmpty(dto.getReportDimen())) {
			return new AjaxResult("报表维度不能为空！", MessageType.错误消息);
		}else {
			// 当选择单账套时
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
			}else {
				// 账套组不能为空
				if (StringUtil.isEmpty(dto.getBooksetGroup())) {
					return new AjaxResult("请输入账套组。", MessageType.错误消息);
				}
			}
		}
		return commonCheck(result, "");
    }

    @ResponseBody
    @RequestMapping("findPage")
    public List<ValProfitNewQuery001Dto> selectBalanceStatement()
        throws Exception {
    	Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
    	// 报表维度为单账套
		if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(String.valueOf(paraMap.get("reportDimen")))) {
    		return valProfitNewQuery001Service.findPage(paraMap);
    	}
    	// 报表维度为全账套
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		paraMap.put("booksetIds", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
		return valProfitNewQuery001Service.findPageAll(paraMap);
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
		Date reportDate = valProfitNewQuery001Service.analyseDate(pageParam);
		
		map.put("statementDate", sdf.format(reportDate));
		map.put("statementType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, (String)pageParam.get("reportTermType")));
		map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
		return map;
	}
    
	/**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
    	Map<String, Object> paraMap = RequestUtil.getPageParam();
		if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(String.valueOf(paraMap.get("reportDimen")))) {
			List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
			paraMap.put("booksetIds", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
		}

        return valProfitNewQuery001Service.exportExcel(paraMap);
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
			return valProfitNewQuery001Service.getLinkSelectOption(param);
		}
		if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
			param.put("dict_code_child", DictCode.季度.getValue());
			return valProfitNewQuery001Service.getLinkSelectOption(param);
		}
		return null;
	}
	
    /**
     * 获取默认日期信息
     * 
     * @return ValProfitQuery001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getDateInfo")
    public ValProfitNewQuery001Dto getDateInfo()
        throws Exception {
        return valProfitNewQuery001Service.getDateInfo(RequestUtil.getPageParam());
    }
    
}
