/*
 * 文件名：MstNetAssetQuotationInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.service.ComNetAssetQuotationInfo001Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
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

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月13日
 * @see ComNetAssetQuotationInfoController
 * @since 1.0
 */
@RestController("serviceComNetAssetQuotationInfoController")
@RequestMapping("/ComNetAssetQuotationInfo")
public class ComNetAssetQuotationInfoController {

    /**
     * 
     */
    @Autowired
    private ComNetAssetQuotationInfo001Service comNetAssetQuotationInfo001Service;
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
    public Page<ComNetAssetQuotationInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return comNetAssetQuotationInfo001Service.selectForPage(page,length,pageParam);
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
        return comNetAssetQuotationInfo001Service.getAssetName(map);
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
        return comNetAssetQuotationInfo001Service.getAssetCode(map);
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
        return comNetAssetQuotationInfo001Service.getAssetCodeList(map);
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
    public ComNetAssetQuotationInfo001Dto selectMstNetAsset(@RequestBody Map<String, Object> param) {
        return comNetAssetQuotationInfo001Service.selectMstNetAsset(param);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param dto
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ComNetAssetQuotationInfo001Dto dto) throws Exception {
        return comNetAssetQuotationInfo001Service.delete(dto);
    }

    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ComNetAssetQuotationInfo001Dto dto) throws Exception {
        return comNetAssetQuotationInfo001Service.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody ComNetAssetQuotationInfo001Dto dto) throws Exception {
        return comNetAssetQuotationInfo001Service.update(dto);
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
    public ComNetAssetQuotationInfo001Dto selectNameAndFundManager(@RequestBody Map<String, Object> map) {
        return comNetAssetQuotationInfo001Service.selectNameAndFundManager(map);
    }

    /**
     * 检验validate
     * @param map
     * @return
     */
    @PostMapping("selectByIdAndDate")
    public ComNetAssetQuotationInfo001Dto selectByIdAndDate(@RequestBody Map<String,Object> map) {
        return comNetAssetQuotationInfo001Service.selectByIdAndDate(map);
    }

    /**
     * 根据条件查询数据
     * @param pageParam
     * @return
     * @throws Exception
     */
    @PostMapping("selectAllList")
    public List<ComNetAssetQuotationInfo001Dto> selectAllList(@RequestBody Map<String, Object> pageParam) throws Exception {
        return comNetAssetQuotationInfo001Service.selectAllList(pageParam);
    }

}
