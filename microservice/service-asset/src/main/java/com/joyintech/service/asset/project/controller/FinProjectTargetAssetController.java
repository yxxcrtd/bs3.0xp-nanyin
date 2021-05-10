/*
 * 文件名：FinBondTargetAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.project.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.asset.bond.controller.FinBondTargetAssetController;
import com.joyintech.service.asset.project.dto.FinProjectTargetAssetCmsDto;
import com.joyintech.service.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.service.asset.project.service.FinProjectTargetAssetService;
import com.joyintech.tams.common.base.entity.PaFinProductAssetAttach;

/**
 * 项目类资产标的信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinBondTargetAssetController
 * @since 1.0
 */
@RestController("FinProjectTargetAsset_dtx")
@RequestMapping("/FinProjectTargetAsset")
public class FinProjectTargetAssetController {

    @Autowired
    private FinProjectTargetAssetService finProjectTargetAssetService;
    /**
     *  查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinProjectTargetAssetDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProjectTargetAssetService.selectForPage(page, length, param);
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
    public void insert(@RequestBody FinProjectTargetAssetDto dto) throws Exception {
        // add zhouwenqiang 2020-12-09 start
        // 估值核算系统核算体系变更，项目类资产需要和计划类资产区分核算，对标的扩展属性增加：是否其他债权，其他资产都为空，只有项目类资产为：Y；
        for (BaseFinProductBaseInfoDto bfbd:dto.getBaseFinProductBaseInfoList()){
            bfbd.getFinProductAdd().setIsQtzq(DictCodeList.是否_是.getValue());
        }
        // add zhouwenqiang 2020-12-09 end

        finProjectTargetAssetService.insert(dto);
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
    public void update(@RequestBody FinProjectTargetAssetDto dto) throws Exception{
    	/**add by wujie on 20210130 start remark:项目类资产修改时：扩展表.是否其他债权  = Y**/
    	for (BaseFinProductBaseInfoDto bfbd:dto.getBaseFinProductBaseInfoList()){
            bfbd.getFinProductAdd().setIsQtzq(DictCodeList.是否_是.getValue());
        }
    	/**add by wujie on 20210130 END**/
        finProjectTargetAssetService.update(dto);
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
    public void delete(@RequestBody FinProjectTargetAssetDto dto) throws Exception{
        finProjectTargetAssetService.delete(dto);
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
    public FinProjectTargetAssetDto getBasicInfo(@RequestBody Map<String,Object> paramMap) throws Exception {
        return finProjectTargetAssetService.getBasicInfo(paramMap);
    }
    
    @PostMapping("getCmsInfoBycashOutNo")
    public FinProjectTargetAssetCmsDto getCmsInfoBycashOutNo(@RequestBody Map<String, Object> map){
        return finProjectTargetAssetService.getCmsInfoBycashOutNo(map);
    }

    @PostMapping("checkMarketExists")
    public String checkMarketExists(@RequestBody String finprodMarketId){
        return finProjectTargetAssetService.checkMarketExists(finprodMarketId);
    }

    @GetMapping("getBusinessIdByFinprodId")
    public List<PaFinProductAssetAttach> getBusinessIdByFinprodId(String finprodId){
        return finProjectTargetAssetService.getBusinessIdByFinprodId(finprodId);
    }
}
