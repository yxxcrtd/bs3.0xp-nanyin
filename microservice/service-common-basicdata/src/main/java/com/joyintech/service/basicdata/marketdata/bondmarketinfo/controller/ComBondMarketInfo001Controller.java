/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * 
 * 每日债券行情
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@RestController(value="serviceComBondMarketInfo001Controller")
@RequestMapping("/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {

    /**
     *
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;


    /**
     * 页面查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ComBondMarketInfo001Dto> queryPageList(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
//        Map<String, Object> params= this.getRequestMap();
//        ValBondMarketInfo001SearchDto dto = (ValBondMarketInfo001SearchDto)JsonUtils.map2entity(params, ValBondMarketInfo001SearchDto.class);
        return comBondMarketInfo001Service.queryPageList(page,length,params);
    }

    /**
     *
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     *
     * @return paraMap
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String valueDateBegin = this.getParam("valueDateBegin");
        String valueDateEnd = this.getParam("valueDateEnd");
        if(StringUtil.isNotEmpty(valueDateBegin)){
            paraMap.put("valueDateBegin", DateUtil.parseDate(valueDateBegin, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateBegin");
        }
        if(StringUtil.isNotEmpty(valueDateEnd)){
            paraMap.put("valueDateEnd", DateUtil.parseDate(valueDateEnd, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateEnd");
        }
        return paraMap;
    }

    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @PostMapping("/queryAllList")
    public List<ComBondMarketInfo001Dto> queryAllList(@RequestBody Map<String, Object> params) throws Exception{
            return comBondMarketInfo001Service.queryAllList(params);
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
    @RequestMapping("delete")
    public int delete(@RequestBody ComBondMarketInfo001Dto dto)
            throws Exception {
        return comBondMarketInfo001Service.delete(dto);
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
    public int insert(@RequestBody ComBondMarketInfo001Dto dto)
            throws Exception {
        String finprodId = dto.getFinprodId();
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 start**/
        //String secId = finprodId.substring(4);
        String secId = comBondMarketInfo001Service.queryFinprodMarketIdByFinprodId(finprodId);
        /** modify by yanya on 20210224 查找市场代码通过finprod_type包进行查询 end**/
//        dto.setCreateUser(LoginUserUtil.getUserName());
//        dto.setUpdateUser(LoginUserUtil.getUserName());
        dto.setSecId(secId);
        return comBondMarketInfo001Service.insert(dto);
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
    public int update(@RequestBody ComBondMarketInfo001Dto dto)
            throws Exception {
//        dto.setUpdateUser(LoginUserUtil.getUserName());
        return comBondMarketInfo001Service.update(dto);
    }

    /**
     * 查询资产代码
     */
    @ResponseBody
    @RequestMapping("getFinProductList/{page}/{length}")
    public Page<SelectOptionDto> getFinProductList(@RequestBody Map<String, Object> map, @PathVariable("page") int page,@PathVariable("length") int length)
            throws Exception {
        return comBondMarketInfo001Service.getFinProductList(map,page,length);
    }

    /**
     * 查询单个数据
     */
    @ResponseBody
    @RequestMapping("queryDtoByKey")
    public List<ComBondMarketInfo001Dto> queryDtoByKey(@RequestBody Map<String, Object> map){
        return comBondMarketInfo001Service.queryDtoByKey(map);
    }


    /**
     *
     * 插入数据
     * @param equityList 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/mergeInfoList")
    public void mergeInfoList(@RequestBody List<ComBondMarketInfo001Dto> equityList){
    	comBondMarketInfo001Service.mergeInfoList(equityList);
    }
}

