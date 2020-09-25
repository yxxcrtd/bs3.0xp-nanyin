/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productinfoquery.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.service.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValProductInfoQuery001Contoller
 * @since 1.0
 */
@RestController(value="serviceValProductInfoQuery001Contoller")
@RequestMapping("/ValProductInfoQuery001")
public class ValProductInfoQuery001Contoller extends BaseController {
    
    /**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ValProductInfoQuery001Service valProductInfoQuery001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        //this.setSelectOptionToModel(model, DictCode.收益类型,DictCode.是否);
        String[] busiType={"04"};
        //参数过滤
        DictCode.checkDictCode(model,DictCode.收益类型,busiType,false);
        this.setSelectOptionToModel(model, DictCode.募集方式,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.发行方式,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.投资性质,DictCode.是否);
        model.addAttribute("raiseType", DictCodeListExt1.募集方式_公募.getValue());
        model.addAttribute("issueType", DictCodeList.开放类型_封闭式.getValue());
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
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValProductInfoQuery001Dto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valProductInfoQuery001Service.findPage(page,length,params);
    }

    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
//        Map<String,Object> map= RequestUtil.getPageParam();
//        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valProductInfoQuery001Service.getFinprodId(page,length,map);
    }
    
    /**
     * 查找产品的资金账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodAcct")
    public List<ValProductInfoAcctDto> getFinprodAcct(@RequestBody Map<String, Object> map) {
//        Map<String,Object> map=RequestUtil.getPageParam();
//        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valProductInfoQuery001Service.getFinprodAcct(map);
    }
    
    /**
     * 查找产品的证券账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getSecAccount")
    public List<ValProductInfoAcctDto> getSecAccount(@RequestBody Map<String, Object> map) {
       // Map<String,Object> map=RequestUtil.getPageParam();
        return valProductInfoQuery001Service.getSecAccount(map);
    }
    
    @RequestMapping("/findBankdesc")
    public List<ValProductInfoAcctDto> findBankdesc(@RequestBody Map<String, Object> map) {
       // Map<String,Object> map=RequestUtil.getPageParam();
        return valProductInfoQuery001Service.findBankdesc(map);
    }
    /**
     * 
     * 费用查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFeeList")
    public List<ValProductInfoFeeDto> queryFeeList(@RequestBody Map<String, Object> map) throws Exception{
        return valProductInfoQuery001Service.queryFeeList(map);
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
    @PostMapping("/findAll")
    public List<ValProductInfoQuery001Dto> findAll(Map<String, Object> params){
            return valProductInfoQuery001Service.findAll(params);
        
    }
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList")
    public List<ValProductInfoFeeDto> queryfloatFeeList(@RequestBody Map<String, Object> map) throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList(map);
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList1")
    public List<ValProductInfoFeeDto> queryfloatFeeList1(@RequestBody Map<String, Object> map) throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList1(map);
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList2")
    public List<ValProductInfoFeeDto> queryfloatFeeList2(@RequestBody Map<String, Object> map) throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList2(map);
    }
    
}
