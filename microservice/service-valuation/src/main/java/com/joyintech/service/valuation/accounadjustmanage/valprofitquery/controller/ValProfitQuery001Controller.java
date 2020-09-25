/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery001Service;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 利润表
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceValProfitQuery001Controller")
@RequestMapping("ValProfitQuery001")
public class ValProfitQuery001Controller extends BaseController {

    @Autowired
    private ValProfitQuery001Service valProfitQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;


    @PostMapping("findPage")
    public List<ValProfitQuery001Dto> selectBalanceStatement(@RequestBody Map<String, Object> paraMap)
        throws Exception {
    	// 报表维度为单账套
    	if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(String.valueOf(paraMap.get("reportDimen")))) {
    		return valProfitQuery001Service.findPage(paraMap);
    	}
    	// 报表维度为全账套
    	paraMap.put("accounts", valAssetBook001Service.getBooksetId2((String)paraMap.get("param")));
        return valProfitQuery001Service.findPageAll(paraMap);
    }

    /**
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectListAll")
    public List<ValProfitQuery001Dto> selectListAll(@RequestBody Map<String, Object> pageParam)
        throws ParseException {
        return valProfitQuery001Service.selectListAll(pageParam);
    }
    
    /**
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectList")
    public List<ValProfitQuery001Dto> selectList(@RequestBody Map<String, Object> pageParam)
        throws ParseException {
        return valProfitQuery001Service.selectList(pageParam); 
    }
    
    
    /**
     * 通用联动查询
     * 
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @PostMapping("/getLinkSelectOption")
    public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
        return valProfitQuery001Service.getLinkSelectOption(param);
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
     * @return ValProfitQuery001Dto
     * @throws Exception
     */
    @PostMapping("getDateInfo")
    public ValProfitQuery001Dto getDateInfo(@RequestBody Map<String, Object> pageParam) {
        return valProfitQuery001Service.getDateInfo(pageParam);
    }
    
}
