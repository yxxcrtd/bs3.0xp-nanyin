/*
 * 文件名：RepAssetDeptRegistService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repassetdeptregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.debtreport.repassetdeptregist.dao.RepAssetDeptRegistMapper;
import com.joyintech.service.report.debtreport.repassetdeptregist.dto.RepAssetDeptRegistDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产要素登记
 * @author fanshuanghu
 * @version 1.0 2019年1月19日
 * @see RepAssetDeptRegistService
 * @since 1.0
 */
@Service
public class RepAssetDeptRegistService extends BaseService<RepAssetDeptRegistDto>{
    @Autowired
    private RepAssetDeptRegistMapper repAssetDeptRegistMapper;
    /**
     * 分页查询
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<RepAssetDeptRegistDto> selectForPage(int page, int length,
                                                            Map<String, Object> pageParam) throws Exception {
        Page<RepAssetDeptRegistDto> resultPage = null;
        String tableNum = "";
        String assetType = (String)pageParam.get("assetType");
        if(StringUtil.isNotEmpty(assetType)){
            pageParam.put("assetType", StringUtil.splitToList(GlobalConstant.COMMA,assetType));
        }else{
            pageParam.put("assetType",null);
        }
        String assetCode = (String)pageParam.get("assetCode");
        if(StringUtil.isNotEmpty(assetCode)){
            pageParam.put("assetCode", StringUtil.splitToList(GlobalConstant.COMMA,assetCode));
        }else{
            pageParam.put("assetCode",null);
        }
        if(pageParam.get("tableNum") != null){
            tableNum = pageParam.get("tableNum").toString();
        }
        
        if(pageParam.get("dataDateStart") != null && pageParam.get("dataDateStart").toString() != ""){
            pageParam.replace("dataDateStart", DateUtil.parse(pageParam.get("dataDateStart").toString()));
            pageParam.replace("dataDateEnd", DateUtil.parse(pageParam.get("dataDateEnd").toString()));
        }else{
            pageParam.replace("dataDateStart", null);
            pageParam.replace("dataDateEnd", null);
        }
        if("01".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage1(pageParam));
        }
        else if("02".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage2(pageParam));
        }
        else if("03".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage3(pageParam));
        }
        else if("04".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage4(pageParam));
        }
        else if("05".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage5(pageParam));
        }
        else if("06".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage6(pageParam));
        }
        else if("07".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage7(pageParam));
        }
        else if("08".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage8(pageParam));
        }
        else if("09".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage9(pageParam));
        }
        else if("010".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage10(pageParam));
        }
        else if("011".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage11(pageParam));
        }
        else if("012".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage12(pageParam));
        }
        else if("013".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage13(pageParam));
        }
        else if("014".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage14(pageParam));
        }
        else if("015".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage15(pageParam));
        }
        else if("016".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage16(pageParam));
        }
        else if("017".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage17(pageParam));
        }
        else if("018".equals(tableNum)){
            resultPage = super.queryForPage(page, length, () -> repAssetDeptRegistMapper.selectForPage18(pageParam));
        }
        return resultPage;
    }
    /**
     * 获取资产代码集合
     * @param map 请求参数
     * @return 资产代码集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepAssetCode(Map<String,Object> map){
        return repAssetDeptRegistMapper.getRepAssetCode(map);
    }
}
