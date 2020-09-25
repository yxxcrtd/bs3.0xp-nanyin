/*
 * 文件名：CommonSelectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.channel.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.channel.dto.ChannelAssetDto;
import com.joyintech.service.asset.channel.service.ChannelAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 通道资产查询
 * 
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see FinChannelAssetQueryController
 * @since 1.0
 */
@RestController("FinChannelAsset_dtx")
@RequestMapping("/FinChannelAsset")
public class FinChannelAssetController {
    
    /**
     * 通道资产查询
     */
    @Autowired
    private ChannelAssetService channelAssetService;
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChannelAssetDto> selectAll(@RequestBody Map<String, Object> param, 
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return channelAssetService.selectForPage(page,length,param);
    }
    
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody ChannelAssetDto dto) throws Exception {
        channelAssetService.insert(dto);
    }
    
    /**
     * 修改
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody ChannelAssetDto dto) throws Exception{
        channelAssetService.update(dto);
    }
    
    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody ChannelAssetDto dto) throws Exception{
        channelAssetService.delete(dto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getBasicInfo")
    public ChannelAssetDto getBasicInfo(@RequestBody Map<String,Object> parm) throws Exception {
        return channelAssetService.getBasicInfo(parm);
    }
    
    /**
     * 
     * 获取通道代码
     * <功能详细描述>
     * @param search 查询条件
     * @return 市场代码 金融产品代码
     * @see
     * @since 1.0
     */
    @PostMapping("/getSecAcctId")
     public List<SelectOptionDto> getSecAcctId(@RequestBody Map<String,Object> parm){
        return channelAssetService.getSecAcctId(parm);
        
    }
    
    /**
     * 
     * 获取通道名称
     * <功能详细描述>
     * @param search 查询条件
     * @return 金融产品名称  金融产品代码
     * @see
     * @since 1.0
     */
    @PostMapping("/getSecAcctName")
     public List<SelectOptionDto> getSecAcctName(@RequestBody Map<String,Object> parm){
        return channelAssetService.getSecAcctName(parm);
        
    }
    /**
     * 
     * 判断通道是否存在交易
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/isTransInAsset")
    public int isTransInAsset(String finprodId){
        return channelAssetService.isTransInAsset(finprodId);
    }

    /**
     * 资金账户已经有通道挂的话，不允许再挂
     * @param map
     * @return
     */
    @PostMapping("/isReBusiAcctNo")
    public int isReBusiAcctNo(@RequestBody Map<String, Object> map){
        return channelAssetService.isReBusiAcctNo(map);
    }

}
