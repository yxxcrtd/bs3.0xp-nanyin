/*
 * 文件名：MstNetAssetQuotationInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstnetassetquotationinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.joyintech.service.basicdata.mstnetassetquotationinfo.dto.MstNetAssetQuotationInfo001Dto;
import com.joyintech.service.basicdata.mstnetassetquotationinfo.service.MstNetAssetQuotationInfo001Service;
import com.joyintech.service.basicdata.mstnetassetquotationinfo.service.MstNetAssetQuotationInfoImportDataService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月13日
 * @see MstNetAssetQuotationInfoController
 * @since 1.0
 */
@RestController("MstNetAssetQuotationInfoCtr_dtx")
@RequestMapping("/MstNetAssetQuotationInfo")
public class MstNetAssetQuotationInfoController{

    /**
     * 
     */
    @Autowired
    private MstNetAssetQuotationInfo001Service mstNetAssetQuotationInfo001Service;
    /**hsgexinghua add by zhangxing1 20200228
     * service注入
     */
    @Autowired
    private MstNetAssetQuotationInfoImportDataService mstNetAssetQuotationInfoImportDataService;
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<MstNetAssetQuotationInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return mstNetAssetQuotationInfo001Service.selectForPage(page,length,pageParam);
    }
    /**
     * 资产名称联想文本框 可以通过输入的资产名称模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetName")
    public List<SuggestDto> getAssetName(@RequestBody Map<String, Object> map) {
        return mstNetAssetQuotationInfo001Service.getAssetName(map);
    }
    /**
     * 查询界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetCode")
    public List<SuggestDto> getAssetCode(@RequestBody Map<String, Object> map) {
        return mstNetAssetQuotationInfo001Service.getAssetCode(map);
    }
    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList(@RequestBody Map<String, Object> map) {
        return mstNetAssetQuotationInfo001Service.getAssetCodeList(map);
    }
    /**
     * 通过资产代码查询数据 完成新建、编辑界面和双击查看界面初始化
     * 
     * @param param 资产代码
     * @return dto
     * @see
     * @since 1.0
     */
    @PostMapping("selectMstNetAsset")
    public MstNetAssetQuotationInfo001Dto selectMstNetAsset(@RequestBody Map<String, Object> param) {
        return mstNetAssetQuotationInfo001Service.selectMstNetAsset(param);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstNetAssetQuotationInfo001Dto dto) throws Exception {
        return mstNetAssetQuotationInfo001Service.delete(dto);
    }

    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstNetAssetQuotationInfo001Dto dto) throws Exception {
        return mstNetAssetQuotationInfo001Service.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstNetAssetQuotationInfo001Dto dto) throws Exception {
        return mstNetAssetQuotationInfo001Service.update(dto);
    }
    /**
     * 通过资产代码查询资产名称和管理人 完成资产代码画面联动
     * 
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    @PostMapping("selectNameAndFundManager")
    public MstNetAssetQuotationInfo001Dto selectNameAndFundManager(@RequestBody Map<String, Object> map) {
        return mstNetAssetQuotationInfo001Service.selectNameAndFundManager(map);
    }
    /**
     * 
     * 检验validate
     * <功能详细描述>
     * @param finprodId
     * @param valDate
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectByIdAndDate")
    public MstNetAssetQuotationInfo001Dto selectByIdAndDate(@RequestBody Map<String,Object> map) {
        return mstNetAssetQuotationInfo001Service.selectByIdAndDate(map);
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
        return mstNetAssetQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
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
        return this.mstNetAssetQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    //hsgexinghua add by zhangxing1 20200228 end
}
