package com.joyintech.tams.invest.portfolio.actualtimeasset.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.portfolio.actualtimeasset.dto.ActualTimeAssetDto;
import com.joyintech.tams.invest.portfolio.actualtimeasset.service.ActualTimeAsset001Service;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAsset001Controller
 * @author: yanya
 * @create: 2019-10-15
 **/
@Controller
@PageId("ActualTimeAsset001")
@RequestMapping("/invest/portfolio/actualtimeasset/ActualTimeAsset001")
public class ActualTimeAsset001Controller extends BaseController {
    /**
     * 资产头寸查询service
     */
    @Autowired
    private ActualTimeAsset001Service actualTimeAsset001Service;

    /**
     * 资产头寸查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        //this.setSelectOptionToModel(model, DictCode.WIND一级分类_含债券一级分类等, DictCode.WIND二级分类_含债券二级分类等);
        this.setSelectOptionToModel(model, DictCode.平安资产类型);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        return super.init(model);
    }

    /**
     * 组合代码的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecAcct")
    public List<SelectOptionDto> getSecAcct(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = null;
//        if(StringUtil.isNotEmpty(portfolioId)) {
        temp = actualTimeAsset001Service.getSecAcct(map);
//        }
//        else {
//            temp = new ArrayList<SelectOptionDto>();
//        }
        return temp;
    }

    /**
     * 资产代码的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetsInfo")
    public List<SelectOptionDto> getAssetsInfo(){
        Map<String,Object> map = RequestUtil.getPageParam();
        //资产类型
        Object type8 = map.get("type8");
        List<SelectOptionDto> temp = null;
        //资产类型为质押式回购 、买断式回购、拆借、利率互换,资产代码不需要值
        if(StringUtil.isNotEmpty(type8) && Arrays.asList(DictCodeListExt2.平安资产类型_外汇即期.getValue(), DictCodeListExt2.平安资产类型_外汇掉期.getValue(), 
            DictCodeListExt2.平安资产类型_债券借贷.getValue(), DictCodeListExt2.平安资产类型_外汇远期.getValue(), 
            DictCodeList.平安资产类型_质押式回购.getValue(), DictCodeList.平安资产类型_买断式回购.getValue(), 
            DictCodeList.平安资产类型_拆借.getValue(), DictCodeListExt2.平安资产类型_利率互换.getValue()).contains(type8)) {
            temp = new ArrayList<SelectOptionDto>();
        }
        else {
            temp = actualTimeAsset001Service.getAssetsInfo(map);
        }
        return temp;
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ActualTimeAssetDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        return actualTimeAsset001Service.selectForPage(map);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck()
            throws Exception {
        Page<ActualTimeAssetDto> result = actualTimeAsset001Service.selectForPage(RequestUtil.getPageParam());
        if (result.getRoot().isEmpty()) {
            return new AjaxResult(getMessage("PTL_COM_E_0012"), MessageType.错误消息);
        }
        return new AjaxResult();
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return actualTimeAsset001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
