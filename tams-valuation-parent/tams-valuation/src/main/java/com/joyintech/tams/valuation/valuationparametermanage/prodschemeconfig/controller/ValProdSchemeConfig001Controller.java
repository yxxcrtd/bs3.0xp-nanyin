/*
 * 文件名：ValProdSchemeConfig001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品对账方案
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("ValProdSchemeConfig001")
@RequestMapping("/valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig001")
public class ValProdSchemeConfig001Controller extends BaseController{
    
    @Autowired
    private ValProdSchemeConfig001Service valProdSchemeConfig001Service;
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    
    /**
     * 对账方案管理 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型);
        // 获取方案名称
        List<String> schemeTypeList = new ArrayList<>();
        schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
        //schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
        Map<String, Object> params = new HashMap<>();
        params.put("schemeTypeList", schemeTypeList);
        // 只查询账套模板对应的方案
        params.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        List<SelectOptionDto> options = valProdSchemeConfig001Service.getSchemeInfoList(params);
        model.addAttribute("SCHEME_NAME", JsonUtils.objectArray2Json(options.toArray()));
        // 获取方案类型---更改方案名称时，赋值使用
        options = new ArrayList<SelectOptionDto>();
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.配置方案类型, null);
        for (SelectOptionDto dto : selectDto.getOptions()) {
            if (DictCodeListExt1.配置方案类型_估值表对账方案.getValue().equals(dto.getId())
                //|| DictCodeListExt1.配置方案类型_余额表对账方案.getValue().equals(dto.getId())
                ) {
                options.add(dto);
            }
        }
        model.addAttribute("SCHEME_TYPE", JsonUtils.objectArray2Json(options.toArray()));
        return super.init(model);
    }
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId()
        throws Exception {
//        String param = super.getRequest().getParameter("param");
        return valAssetBook001Service.getBooksetId(RequestUtil.getPageParam());
    }
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId2")
    public Page<SelectOptionDto> getBooksetId2()
        throws Exception {
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
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
    public Page<ValProdSchemeConfig001Dto> findPage() throws Exception {
        return valProdSchemeConfig001Service.findPage(RequestUtil.getPageParam());

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
        Map<String, Object> params= RequestUtil.getPageParam();
        List<ValProdSchemeConfig001Dto> listdto;
        if (datas == null) {
            listdto = valProdSchemeConfig001Service.selectAll(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValProdSchemeConfig001Dto.class);
        }
        File outFile = valProdSchemeConfig001Service.makeFile(
                "/com/joyintech/tams/valuation/valuationparametermanage/prodschemeconfig/conf/export_prodschemeconfig.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
}
