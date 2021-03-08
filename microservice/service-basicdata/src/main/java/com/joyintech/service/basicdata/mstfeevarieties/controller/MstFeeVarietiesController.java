/*
 * 文件名：NetWorthMarketInfoController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstfeevarieties.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.service.basicdata.mstfeevarieties.service.MstFeeVarietiesService;

/**
 * 费用品种信息管理 提供净值型资产行情信息的管理
 * 
 * @author huwei
 * @version 1.0 2018年7月24日
 * @see MstFeeVarietiesController
 * @since 1.0
 */
@RestController("MstFeeVarietiesCtr_dtx")
@RequestMapping("/MstFeeVarieties")
public class MstFeeVarietiesController  {
    /**
     * service对象
     */
    @Autowired
    private MstFeeVarietiesService mstFeeVarietiesService;


    /**
     * 查询费用名称
     * @param param 参数
     * @return list 结果集
     * @throws Exception Exception
     */
    @RequestMapping("/getFeeNameOption")
    public List<SuggestDto> getFeeNameOption(@RequestBody Map<String, Object> param)
        throws Exception {
        List<SuggestDto> list = mstFeeVarietiesService.getFeeNameOption(param);
        return list;// 返回费用名称
    }

    /**
     * 畫面查詢方法
     * @param map 参数
     * @return 所有數據
     * @throws Exception 異常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstFeeVarietiesDto> selectForPage(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstFeeVarietiesService.selectForPage(page,length,map);
    }
    /**
     * 根据费用代码查询数据
     * 
     * @param map 费用代码
     * @return 查询到的实体类说
     */
    @PostMapping("/selectByFeeId")
    public MstFeeVarietiesDto selectByFeeId(@RequestBody Map<String, Object> map) {
        return mstFeeVarietiesService.selectByFeeId(map);
    }
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody MstFeeVarietiesDto dto) {
         mstFeeVarietiesService.doComplete(dto);
    }
    
    
    /**
     * 判断费用品种代码是否已使用
     * 
     * @param map 费用代码
     * @return 查询到的实体类说
     */
    @PostMapping("/isUseByFeeId")
    public int isUseByFeeId(@RequestBody String feeId) {
        return mstFeeVarietiesService.isUseByFeeId(feeId);
    }
    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName feeName
     * @return dto
     * @see
     * @since 1.0
     */
    @GetMapping("/getDtoByFeeName")
    public List<MstFeeVarietiesDto> getDtoByFeeName(String feeName){
        return mstFeeVarietiesService.getDtoByFeeName(feeName);
    }
    
    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName feeName
     * @return dto
     * @see
     * @since 1.0
     */
    @PostMapping("/getDtoByFeeNameInstitution")
    public List<MstFeeVarietiesDto> getDtoByFeeNameInstitution(@RequestBody Map<String,Object> para){
        return mstFeeVarietiesService.getDtoByFeeNameInstitution(para);
    }
}
