/*
 * 文件名：MstBondQuotationInfoController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangzehui
 * 修改时间：2018年7月17日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstbondquotationinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.service.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoImportDataService;
import com.joyintech.service.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoService;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see MstBondQuotationInfoController
 * @since 1.0
 */
@RestController("MstBondQuotationInfoCtr_dtx")
@RequestMapping("/MstBondQuotationInfo")
public class MstBondQuotationInfoController {

    /**
     * 
     */
    @Autowired
    private MstBondQuotationInfoService mstBondQuotationInfoService;
    
    /**hsgexinghua add by zhangxing1 20200228
     * service注入
     */
    @Autowired
    private MstBondQuotationInfoImportDataService mstBondQuotationInfoImportDataService;

    /**
     * 债券代码下拉选择框
     * 
     * @return list结果集
     * @throws Exception 异常
     */
    @GetMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String secId)
        throws Exception {
        return mstBondQuotationInfoService.getSecIdOption(secId);
    }

    /**
     * 新建页面查询资产代码
     * 
     * @return list
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecIdToNew")
    public List<SuggestDto> getSecIdToNew(String secId)
        throws Exception {
        return mstBondQuotationInfoService.getSecIdToNew(secId);
    }

    /**
     * 债券简称的下拉框
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("getFinProdAbbr")
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr)
        throws Exception {
        return mstBondQuotationInfoService.getFinProdAbbr(finprodAbbr);
    }

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<MstBondQuotationInfoDto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return mstBondQuotationInfoService.selectForPage(page, length, param);
    }

    /**
     * 获取金融产品简称
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getFinprodAbbrOption")
    public List<SelectOptionDto> getFinprodAbbrOption(@RequestBody Map<String, Object> map)
        throws Exception {
        return mstBondQuotationInfoService.getFinprodAbbrOption(map);
    }

    /**
     * 删除
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return mstBondQuotationInfoService.delete(dto);
    }

    /**
     * 插入
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return mstBondQuotationInfoService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return mstBondQuotationInfoService.update(dto);
    }

    /**
     * 根据债券代码查询
     * 
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("getBySecId")
    public MstBondValuationInfo getBySecId(String secId)
        throws Exception {
        return mstBondQuotationInfoService.getBySecId(secId);
    }

    /**
     * 根据债券代码查询
     * 
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("selectByKey")
    public MstBondValuationInfo selectByKey(String secId, String valueDate, String valueSource)
        throws Exception {
        return mstBondQuotationInfoService.selectByKey(secId, valueDate, valueSource);
    }
    
  //hsgexinghua add by zhangxing1 20200228 start

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return mstBondQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
    }


    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.mstBondQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    //hsgexinghua add by zhangxing1 20200228 end

}
