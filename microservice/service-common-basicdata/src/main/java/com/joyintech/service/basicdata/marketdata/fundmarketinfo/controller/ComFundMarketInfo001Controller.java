/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.fundmarketinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.service.ComFundMarketInfo001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2018年7月17日
 * @see ComFundMarketInfo001Controller
 * @since 1.0
 */
@RestController("serviceComFundMarketInfo001Controller")
@RequestMapping("/ComFundMarketInfo001")
public class ComFundMarketInfo001Controller extends BaseController {

    /**
     * comFundMarketInfo001Service
     */
    @Autowired
    private ComFundMarketInfo001Service comFundMarketInfo001Service;

    /**
     * 重写init方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }

    /**
     * 加载表格
     * <功能详细描述>
     * 
     * @return List<ComFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @PostMapping("/tableLoad")
    public List<ComFundMarketInfo001dto> tableLoad(@RequestBody Map<String, Object> params)
        throws Exception {
//        Map<String, Object> params = RequestUtil.getPageParam();
        return comFundMarketInfo001Service.tableLoad(params);
    }
    @PostMapping("/findPage/{page}/{length}")
    public Page<ComFundMarketInfo001dto> findPage(@RequestBody Map<String, Object> params,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return comFundMarketInfo001Service.findPage(page,length,params);
    }
    
//    /**
//     * 导出数据 <一句话功能简述> <功能详细描述>
//     * 
//     * @return List<ComFundMarketInfo001dto>
//     * @throws Exception Exception
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//
//        String datas = this.getParam("datas");
//        Map<String, Object> params = RequestUtil.getPageParam();
//        List<ComFundMarketInfo001dto> listdto;
//
//        if (datas == null) {
//            listdto = comFundMarketInfo001Service.tableLoad(params);
//        }
//        else {
//            listdto = JsonUtils.json2List(datas, ComFundMarketInfo001dto.class);
//        }
//
//        File outFile = comFundMarketInfo001Service.makeFile(
//            "/com/joyintech/tams/valuation/basedata/marketdata/fundmarketinfo/config/export_valfundmarketinfo001.xml",
//            listdto);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date())
//                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
//                                 + SystemGlobalConstant.POINT
//                                 + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型 <一句话功能简述> <功能详细描述>
     * 
     * @return 日期区间
     * @throws ParseException ParseException
     */
    public Map<String, Object> getRequestMap()
        throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String startDate = this.getParam("startDate");
        String endDate = this.getParam("endDate");
        if (StringUtil.isNotEmpty(startDate)) {
            paraMap.put("startDate", DateUtil.parseDate(startDate, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            paraMap.put("endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
        }

        return paraMap;
    }

    /**
     * 删除数据
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("delete")
    public int deleteByPrimaryKey(@RequestBody ComFundMarketInfo001dto dto){
        String finprodId = dto.getFinprodId();
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 start**/
        //String fundCode = finprodId.substring(4);
        String fundCode = comFundMarketInfo001Service.queryFinprodMarketIdByFinprodId(finprodId);
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 end**/
        dto.setFundCode(fundCode);
        return comFundMarketInfo001Service.deleteByPrimaryKey(dto);
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
    @RequestMapping("insert")
    public int insert(@RequestBody ComFundMarketInfo001dto dto)
            throws Exception {
        String finprodId = dto.getFinprodId();
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 start**/
        //String fundCode = finprodId.substring(4);
        String fundCode = comFundMarketInfo001Service.queryFinprodMarketIdByFinprodId(finprodId);
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 end**/
        dto.setFundCode(fundCode);
        return comFundMarketInfo001Service.saveNewDto(dto);
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
    @RequestMapping("update")
    public int update(@RequestBody ComFundMarketInfo001dto dto)
            throws Exception {
        String finprodId = dto.getFinprodId();
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 start**/
        //String fundCode = finprodId.substring(4);
        String fundCode = comFundMarketInfo001Service.queryFinprodMarketIdByFinprodId(finprodId);
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 end**/
        dto.setFundCode(fundCode);
        return comFundMarketInfo001Service.updateByPrimaryKey(dto);
    }

    /**
     * 查询资产代码
     */
    @ResponseBody
    @RequestMapping("getFinProductList/{page}/{length}")
    public Page<SelectOptionDto> getFinProductList(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return comFundMarketInfo001Service.getFinProductList(map,page,length);
    }
    /**
     * 查询市场代码
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> map) {
        return comFundMarketInfo001Service.getFinprodMarketId(map);
    }

    /**
     * 查询符合条件的数据
     */
    @ResponseBody
    @RequestMapping("queryDtoByKey")
    public List<ComFundMarketInfo001dto> queryDtoByKey(@RequestBody Map<String, Object> map){
        return comFundMarketInfo001Service.queryDtoByKey(map);
    }
    /**
     *
     * 资产信息检索查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getAssetInfo")
    public List<ComFundMarketInfo001dto>  getAssetInfo(@RequestBody Map<String, Object> param) {
        return comFundMarketInfo001Service.getAssetInfo(param);
    }
}
