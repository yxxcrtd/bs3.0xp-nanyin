/*
 * 文件名：FinProductDurDeal001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.durdeal.dto.FinProductDurDeal001Dto;
import com.joyintech.tams.invest.product.durdeal.service.FinProductDurDeal001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品存续期交易查询
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDeal001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductDurDeal001")
@RequestMapping("/invest/product/durdeal/FinProductDurDeal001")
public class FinProductDurDeal001Controller extends BaseController {

    /**
     * FinProductDurDeal001Service
     */
    @Autowired
    private FinProductDurDeal001Service finProductDurDeal001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //ZGBUG-307 产品处理模式下拉框有“分档周期型” 但实际上暂无该类型产品 guyunfei 20200310 start
    	//setSelectOptionToModel(model, DictCode.处理模式, DictCode.交易方向, DictCode.有效状态,DictCode.币种);
        setSelectOptionToModel(model, DictCode.交易方向, DictCode.有效状态,DictCode.币种);
    	List<SelectOptionDto> list = new ArrayList<>();
    	SelectOptionDto dto = new SelectOptionDto();
    	dto.setId(DictCodeList.处理模式_普通.getValue());
    	dto.setText(DictCodeList.处理模式_普通.getCodeName());
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.处理模式_周期型.getValue());
        dto1.setText(DictCodeList.处理模式_周期型.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.处理模式_分档.getValue());
        dto2.setText(DictCodeList.处理模式_分档.getCodeName());
        list.add(dto);
        list.add(dto1);
        list.add(dto2);
        model.addAttribute("DISPOSE_PATTERN",JsonUtils.objectArray2Json(list.toArray()));
        //ZGBUG-307 产品处理模式下拉框有“分档周期型” 但实际上暂无该类型产品 guyunfei 20200310 end
        return super.init(model);
    }

    /**
     * 产品代码下拉框
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    @RequestMapping("queryProdIdInfo")
    @ResponseBody
    public List<SelectOptionDto> queryProdIdInfo(@RequestParam Map<String, Object> param)
            throws Exception {
        return finProductDurDeal001Service.queryProdIdInfo(param);
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductDurDeal001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<FinProductDurDeal001Dto> findPage() throws Exception {
        return finProductDurDeal001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductDurDeal001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 同步
     *
     * @return 处理结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("synchroData")
    @ResponseBody
    public AjaxResult synchroData() {
        return finProductDurDeal001Service.synchroData();
    }
    
    /**
     * 查询交易明细数据
     * @return
     * @throws Exception
     */
    @RequestMapping("findDetailPage")
    @ResponseBody
    public Page<FinProductDurDeal001Dto> findDetailPage() throws Exception {
    	return finProductDurDeal001Service.findDetailPage(RequestUtil.getPageParam());
    }
}
