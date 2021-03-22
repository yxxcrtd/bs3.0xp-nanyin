package com.joyintech.service.trade.transfer.assetstransfer.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.trade.transfer.assetstransfer.dto.AssetsTransferDto;
import com.joyintech.service.trade.transfer.assetstransfer.service.AssetsTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资产转仓一览画面
 * @author liuwei
 * @version 1.0 2019年12月11日
 * @see AssetsTransferController
 * @since 1.0
 */
@RestController("AssetsTransferCtr_dtx")
@RequestMapping("/AssetsTransfer")
public class AssetsTransferController {

    /**
     * 注入资产转仓一览Service
     */
    @Autowired
    private AssetsTransferService assetsTransferService;

    /**
     * 获取组合代码联想文本框
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return assetsTransferService.getPortfolioId(map);
    }

    /**
     * 获取组合代码联想文本框
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioIdTree")
    public List<TreeNode> getPortfolioIdTree(@RequestBody Map<String,Object> map){
        return assetsTransferService.getPortfolioIdTree(map);
    }

    /**
     * 待转仓交易查询
     * @return
     * @throws Exception
     */
    @PostMapping("/findUnTransPage/{page}/{length}")
    public Page<AssetsTransferDto> findUnTransPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return assetsTransferService.findUnTransPage(page, length, param);
    }

    /**
     * 已转仓交易查询
     * @return
     * @throws Exception
     */
    @PostMapping("/findTransPage/{page}/{length}")
    public Page<AssetsTransferDto> findTransPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return assetsTransferService.findTransPage(page, length, param);
    }

    /**
     * 获取资产代码联想下拉框
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> map){
        return assetsTransferService.getFinprodId(map);
    }
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return assetsTransferService.getFinprodMarketId(map, page, length);
    }
}
