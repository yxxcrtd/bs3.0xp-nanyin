/*
 * 文件名：ValBalanceStatementQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.service.ValBalanceStatementQuery001Service;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.common.base.dao.BokBooksetExtMapper;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetExtKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 估值余额表查询Controller
 * 
 * @author 杨智
 * @version 1.0 2018年7月25日
 * @see ValBalanceStatementQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceValBalanceStatementQuery001Controller")
@RequestMapping("ValBalanceStatementQuery001")
public class ValBalanceStatementQuery001Controller extends BaseController {

    @Autowired
    private ValBalanceStatementQuery001Service valBalanceStatementQuery001Service;
    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;
//    @Autowired
//    private ValAssetBook001Service valAssetBook001Service;
    @Autowired
    private BokBooksetExtMapper bokBooksetExtMapper;
    
//    @Override
//    public String init(Model model) {
//        Map<String,Object> map=RequestUtil.getPageParam();
//        model.addAttribute("valDate", map.get("valDate"));
//        model.addAttribute("finprodId", map.get("finprodId"));
//        model.addAttribute("isInit", map.get("isInit"));
//        //估值模板选择
//        String hzSchemeSwitch = CacheUtil.getSysParam("SCHEME_MODE");
//        model.addAttribute("hzSchemeSwitch", hzSchemeSwitch);
//        return super.init(model);
//    }
//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@RequestBody @Valid ValBalanceStatementQuery001Dto dto, BindingResult result) {
//        AjaxResult ajaxResult = new AjaxResult();
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        // 获取账套配置表对应的模板
//        BokBooksetExtKey key = new BokBooksetExtKey();
//        key.setBooksetId(dto.getBooksetId());
//        key.setParamCode(DictCode.估值表模板.getValue());
//        BokBooksetExt bokBooksetExt = bokBooksetExtMapper.selectByPrimaryKey(key);
//        if(StringUtil.isNotEmpty(bokBooksetExt)){
//            ajaxResult.setData(bokBooksetExt.getParamValue());
//        }
//        
//        return ajaxResult;
//    }
    @PostMapping("/selectBokBooksetExt")
    public BokBooksetExt selectBokBooksetExt(@RequestBody BokBooksetExtKey key) {
        return bokBooksetExtMapper.selectByPrimaryKey(key);
    }

    @PostMapping("/findPage/{page}/{length}")
    public Page<ValBalanceStatementQuery001Dto> selectBalanceStatement(@RequestBody Map<String,Object> params,
                                                                       @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<ValBalanceStatementQuery001Dto> pages = valBalanceStatementQuery001Service.selectForPage(params);
        return pages;
    }
    
    @PostMapping("/selectSum/{page}/{length}")
    public Page<ValBalanceStatementQuery001Dto> selectSum(@RequestBody Map<String,Object> pageParam,
                                                          @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valBalanceStatementQuery001Service.selectSum(pageParam);
    }
//    /**
//     * 导出信息excel
//     * @return excel
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception{
//        return valBalanceStatementQuery001Service.exportExcel(RequestUtil.getParameters(getRequest()));
//        
//    }
    @PostMapping("/checkBokkSetDate")
    public String checkBokkSetDate(@RequestBody Map<String, Object> params) {
//        Map<String,Object> map = RequestUtil.getPageParam();
        String msg=valAccountHandle001Service.checkBokkSetDateForVluTab(params);//modify by dongjun on 20181207
        return msg;
    }
//    /**
//     * 检索账套代码(只取理财账套)
//     * 
//     * @return 账套代码联想框内容
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("/getBooksetId2")
//    public List<SuggestDto> getBooksetId2()
//        throws Exception {
//        String param = super.getRequest().getParameter("param");
//        return valAssetBook001Service.getBooksetId2(param);
//    }
    @PostMapping("/selectAllList")
    public List<ValBalanceStatementQuery001Dto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return valBalanceStatementQuery001Service.selectAllList(pageParam);
    }
}
