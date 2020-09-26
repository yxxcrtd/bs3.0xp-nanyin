/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productinfoquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.tams.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
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
@Controller
@PageId("ValProductInfoQuery001")
@RequestMapping("/valuation/basedata/productinfoquery/ValProductInfoQuery001")
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
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValProductInfoQuery001Dto> findPage() throws Exception{
        return valProductInfoQuery001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valProductInfoQuery001Service.getFinprodId(map);
    }
    
    /**
     * 查找产品的资金账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodAcct")
    @ResponseBody
    public List<ValProductInfoAcctDto> getFinprodAcct() {
        Map<String,Object> map=RequestUtil.getPageParam();
        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
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
    @ResponseBody
    public List<ValProductInfoAcctDto> getSecAccount() {
        Map<String,Object> map=RequestUtil.getPageParam();
        return valProductInfoQuery001Service.getSecAccount(map);
    }
    /**
     * 
     * 费用查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryFeeList() throws Exception{
        return valProductInfoQuery001Service.queryFeeList(RequestUtil.getPageParam());
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
        Map<String, Object> para= RequestUtil.getPageParam();
        List<ValProductInfoQuery001Dto> dtoList;
        if (datas == null) {
            para.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
            dtoList = valProductInfoQuery001Service.findAll(para);
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValProductInfoQuery001Dto.class);
        }
        
        File outFile = valProductInfoQuery001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/productinfoquery/config/export_ValProductInfoQuery001.xml",
                dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList() throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList1")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList1() throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList1(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryfloatFeeList2")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryfloatFeeList2() throws Exception{
        return valProductInfoQuery001Service.queryfloatFeeList2(RequestUtil.getPageParam());
    }
    
}
