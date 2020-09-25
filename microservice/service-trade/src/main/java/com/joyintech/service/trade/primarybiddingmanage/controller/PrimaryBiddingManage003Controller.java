

package com.joyintech.service.trade.primarybiddingmanage.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.service.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.service.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;

/**
 * 债券投标交易微服务
 *
 * @author 裴家俊
 * @version 1.0 2019年5月10日
 * @see PrimaryBiddingManage003Controller
 * @since 1.0
 */
@RestController("PrimaryBiddingManage003Ctr_dtx")
@RequestMapping("/PrimaryBiddingManage003")
public class PrimaryBiddingManage003Controller {
    /**
     * 注入service类
     */
    @Autowired
    private PrimaryBiddingManage003Service primaryBiddingManage003Service;

    /**
     * 获取委托代码下拉框集合
     * 
     * @return 委托代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("findBidPrice")
    public List<BidOrderBidPriceDto> findBidPrice(@RequestBody Map<String, Object> param)
        throws Exception {
        return primaryBiddingManage003Service.findBidPrice(param);
    }

    /**
     * 获取委托代码下拉框集合
     *
     * @return 委托代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getBidDetailByBidId")
    public List<BidOrderBidPriceDto> getBidDetailByBidId(@RequestBody Map<String, Object> param)
        throws Exception {
        return primaryBiddingManage003Service.getBidDetailByBidId(param.get("bidId").toString());
    }

    /**
     * 查询证券托管户：联想框文本框
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     */
    @RequestMapping("getBussinessEntityByBidId")
    public TrdBiddingManage003Dto getBussinessEntityByBidId(@RequestBody Map<String, Object> param) {
        return primaryBiddingManage003Service.getBussinessEntityByBidId(param);
    }

    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody TrdBiddingManage003Dto dto) throws Exception {
    	primaryBiddingManage003Service.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody TrdBiddingManage003Dto dto) throws Exception {
    	primaryBiddingManage003Service.update(dto);
    }

    /**
     * 根据流程ID查询流程状态
     * @param param
     * @return
     */
    @PostMapping("findProcessStatusByWorkflowId")
    public String findProcessStatusByWorkflowId(@RequestBody Map<String, Object> param){
        return primaryBiddingManage003Service.findProcessStatusByWorkflowId(param.get("workflowId").toString());
    }

    /**
     * 查询是否有交易
     * @param param
     * @return
     */
    @PostMapping("findTradeIdByBidId")
    public Boolean findTradeIdByBidId(@RequestBody Map<String, Object> param){
        return primaryBiddingManage003Service.findTradeIdByBidId(param.get("bidId").toString());
    }
    /**
     * 查询委托信息
     * @param param
     * @return
     */
    @PostMapping("selectInfoByEntrustId")
    public EtrEntrust selectInfoByEntrustId(@RequestBody Map<String, Object> param){
        return primaryBiddingManage003Service.selectInfoByEntrustId(param);
    }
}
