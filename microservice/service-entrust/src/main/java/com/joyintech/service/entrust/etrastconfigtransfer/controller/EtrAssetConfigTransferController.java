package com.joyintech.service.entrust.etrastconfigtransfer.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etrastconfigtransfer.dto.EtrAssetConfigDto;
import com.joyintech.service.entrust.etrastconfigtransfer.service.EtrAssetConfigTransferService;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 资产配置快速转仓指令
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransferController
 * @since 1.0
 */

@RestController("EtrAssetConfigTransferCtr_dtx")
@RequestMapping("EtrAssetConfigTransfer")
public class EtrAssetConfigTransferController {

    /**
     * 股票委托微服务service
     */
    @Autowired
    private EtrAssetConfigTransferService etrAssetConfigTransferService;


    @PostMapping("findPage/{page}/{length}")
    public Page<PtlPortfolio> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrAssetConfigTransferService.selectForPage(page,length,param);
    }

    @PostMapping("getAstPosition")
    public List<EtrAssetConfigDto> getAstPosition(@RequestBody Map<String, Object> param) throws Exception{
        return etrAssetConfigTransferService.getAstPosition(param);
    }

}
