/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productinfoquery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.controller.ValManualVoucher003Controller;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.tams.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
import com.joyintech.tams.valuation.common.constants.ValDictCode;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * 新建-手工凭证
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher003Controller
 * @since 2.0
 */
@Controller
@PageId("ValProductInfoQuery003")
@RequestMapping("/valuation/basedata/productinfoquery/ValProductInfoQuery003")
public class ValProductInfoQuery003Contoller extends BaseController {

    @Autowired
    private ValProductInfoQuery001Service valProductInfoQuery001Service;

    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");
        // model.addAttribute("busiType", JsonUtils.objectArray2Json(options.toArray()));
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.发行方式, DictCode.是否);
        model.addAttribute("issueType", DictCodeList.开放类型_封闭式.getValue());
        this.setSelectOptionToModel(model, DictCode.收益标识, DictCode.是否);
        model.addAttribute("prdIncomeType", DictCodeListExt1.收益标识_非保本浮动收益型.getValue());
        this.setSelectOptionToModel(model, DictCode.收益类型, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.是否, DictCode.是否);
        model.addAttribute("yesOrNo", false);
        this.setSelectOptionToModel(model, DictCode.利率类型, DictCode.是否);
        model.addAttribute("intType", DictCodeList.利率类型_固定利率.getValue());
        this.setSelectOptionToModel(model, DictCode.计息基础, DictCode.是否);
        model.addAttribute("basis", DictCodeList.计息基础_A_360.getValue());
        this.setSelectOptionToModel(model, ValDictCode.计提规则, DictCode.是否);
        model.addAttribute("calculationType", ValDictCodeList.计提规则_区间累计.getValue());
        this.setSelectOptionToModel(model, DictCode.估值舍入规则, DictCode.是否);
        //当份额尾数处理查询结果为NULL时，默认为舍位,else取四舍五入
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("paramCode", ValDictCodeList.账套配置表参数项_份额小数位尾数处理.getValue());
        List<BokBooksetExt> bokBookSetExtList1 = valProductInfoQuery001Service.queryBooksetExtByKey(map);
        if(StringUtil.isEmptyList(bokBookSetExtList1)){
        	model.addAttribute("valuationRoundRule", DictCodeList.估值舍入规则_舍位.getValue());
        }else{
        	model.addAttribute("valuationRoundRule", bokBookSetExtList1.get(0).getParamValue());
        }
        //当单位净值尾数处理查询结果为NULL时，默认四舍五入 
        map.put("paramCode", ValDictCodeList.账套配置表参数项_估值小数位处理方式.getValue());
        List<BokBooksetExt> bokBookSetExtList2 = valProductInfoQuery001Service.queryBooksetExtByKey(map);
        if(StringUtil.isEmptyList(bokBookSetExtList2)){
        	model.addAttribute("valuationRoundRule2", DictCodeList.估值舍入规则_四舍五入.getValue());
        }else{
        	model.addAttribute("valuationRoundRule2", bokBookSetExtList2.get(0).getParamValue());
        }
        //当七日年华收益率尾数处理为NULL时间，默认四舍五入
        map.put("paramCode", ValDictCodeList.账套配置表参数项_万份收益尾数处理.getValue());
        List<BokBooksetExt> bokBookSetExtList3 = valProductInfoQuery001Service.queryBooksetExtByKey(map);
        if(StringUtil.isEmptyList(bokBookSetExtList3)){
        	model.addAttribute("valuationRoundRule3", DictCodeList.估值舍入规则_四舍五入.getValue());
        }else{
        	model.addAttribute("valuationRoundRule3", bokBookSetExtList3.get(0).getParamValue());
        }
        this.setSelectOptionToModel(model, DictCode.生效规则, DictCode.是否);
        model.addAttribute("actRule", DictCodeList.生效规则_期初.getValue());
        this.setSelectOptionToModel(model, DictCode.频率, DictCode.是否);
        model.addAttribute("frequencyUnit", DictCodeList.频率_月.getValue());
        this.setSelectOptionToModel(model, DictCode.营业日准则, DictCode.是否);
        // model.addAttribute("tDayRule", DictCodeList.营业日准则_不调整.getValue());
        model.addAttribute("endTdayRule", DictCodeList.营业日准则_不调整.getValue());
        model.addAttribute("payTdayRule", DictCodeList.营业日准则_不调整.getValue());
        this.setSelectOptionToModel(model, DictCode.业绩报酬计算规则,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.现金流二级类型,DictCode.是否);//modify by dongjun on 20181226
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("finprodId", finprodId);
        ValProductInfoQuery001Dto dto = valProductInfoQuery001Service.queryProductByfinprodId(map);
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        List<ValProductInfoFeeDto> list = valProductInfoQuery001Service.queryfloatFeeList(map);
        if (list.size() > 0) {
            list.get(0).setFeeName(DictCodeListExt1.现金流二级类型_浮动管理费.getValue());//modify by dongjun on 20181226
            dto.setValProductInfoFeeDto(list.get(0));
        }
        // 查询账套配置表，获取 份额小数位，万份收益小数位，年化收益率小数位
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        List<BokBooksetExt> listBokBooksetExt = valProductInfoQuery001Service.queryBooksetExt(map);
        for (BokBooksetExt item : listBokBooksetExt) {
            if (ValDictCodeList.账套配置表参数项_份额小数位位数.getValue().equals(item.getParamCode())) {
                dto.setShareDecDigits(item.getParamValue());
            }
            else if (ValDictCodeList.账套配置表参数项_万份收益小数位位数.getValue().equals(item.getParamCode())) {
                dto.setPyeildDecDigits(item.getParamValue());
            }
            else if (ValDictCodeList.账套配置表参数项_收益率小数位位数.getValue().equals(item.getParamCode())) {
                dto.setYeildDecDigits(item.getParamValue());
            }
            else if (ValDictCodeList.账套配置表参数项_估值小数位位数.getValue().equals(item.getParamCode())) {
                dto.setValDecDigits(item.getParamValue());
            }
        }
        
        super.setSelectOptionToModel(model, DictCode.基数类型);
        model.addAttribute("entity", dto);
        return super.init(model);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryFeeList()
        throws Exception {
        return valProductInfoQuery001Service.queryFeeList(RequestUtil.getPageParam());
    }
}
