/*
 * 文件名：MstNetAssetQuotationInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.mstrateassetquotationinfo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.mstrateassetquotationinfo.dto.MstRateAssetQuotationInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.mstrateassetquotationinfo.service.MstRateAssetQuotationInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lby
 * @version 1.0 2020年10月20日
 * @see MstRateAssetQuotationInfoController
 * @since 1.0
 */
@RestController(value="serviceMstRateAssetQuotationInfoController")
@RequestMapping("/MstRateAssetQuotationInfo")
public class MstRateAssetQuotationInfoController {

    /**
     *
     */
    @Autowired
    private MstRateAssetQuotationInfo001Service mstRateAssetQuotationInfo001Service;
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
    @PostMapping("selectForPage/{page}/{length}")
    public Page<MstRateAssetQuotationInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return mstRateAssetQuotationInfo001Service.selectForPage(page,length,pageParam);
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
        return mstRateAssetQuotationInfo001Service.getAssetCode(map);
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
        return mstRateAssetQuotationInfo001Service.getAssetName(map);
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
        return mstRateAssetQuotationInfo001Service.getAssetCodeList(map);
    }

    /**
     * 通过资产代码查询资产名称 完成资产代码画面联动
     * @param map
     * @return
     */
    @PostMapping("getFinProdNameAndFinprodId")
    public MstRateAssetQuotationInfo001Dto selectNameAndFundManager(@RequestBody Map<String, Object> map) {
        return mstRateAssetQuotationInfo001Service.getFinProdNameAndFinprodId(map);
    }

    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstRateAssetQuotationInfo001Dto dto) throws Exception {
        return mstRateAssetQuotationInfo001Service.insert(dto);
    }

    /**
     *
     * @param map
     * @return
     */
    @PostMapping("selectByIdAndDate")
    public List<MstRateAssetQuotationInfo001Dto> selectByIdAndDate(@RequestBody Map<String, Object> map) {
        return mstRateAssetQuotationInfo001Service.selectByIdAndDate(map);
    }

    /**
     * 通过资产代码查询数据 完成新建、编辑界面和双击查看界面初始化
     *
     * @param param 资产代码
     * @return dto
     * @see
     * @since 1.0
     */
    @PostMapping("selectMstRateAsset")
    public MstRateAssetQuotationInfo001Dto selectMstRateAsset(@RequestBody Map<String, Object> param) {
        return mstRateAssetQuotationInfo001Service.selectMstRateAsset(param);
    }

    /**
     *
     * 更新
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstRateAssetQuotationInfo001Dto dto) throws Exception {
        return mstRateAssetQuotationInfo001Service.update(dto);
    }

    /**
     *
     * 删除
     *
     * 页面选中的数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstRateAssetQuotationInfo001Dto dto) throws Exception {
        return mstRateAssetQuotationInfo001Service.delete(dto);
    }

    /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<MstRateAssetQuotationInfo001Dto> selectForExport(@RequestBody Map<String, Object> map) {
        return mstRateAssetQuotationInfo001Service.selectForExport(map);
    }
}
