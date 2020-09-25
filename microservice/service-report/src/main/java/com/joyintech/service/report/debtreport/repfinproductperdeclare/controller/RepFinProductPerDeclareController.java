/*
 * 文件名：RepFinProductPerDeclareController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperdeclare.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductperdeclare.dto.RepFinProductPerDeclareDto;
import com.joyintech.service.report.debtreport.repfinproductperdeclare.service.RepFinProductPerDeclareService;
import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 个人产品申报报送信息
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月21日
 * @see RepFinProductPerDeclareController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductPerDeclare")
public class RepFinProductPerDeclareController {
    
    /**
     * 文件共通组件
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductPerDeclareService repFinProductPerDeclareService;
    
       /**
    * 
    * 产品名称下拉框
    * <功能详细描述>
    * @param param Map<String, Object>
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
    @PostMapping("/getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param){
        return repFinProductPerDeclareService.getFinprodMarketId(param);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam  Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RepFinProductPerDeclareDto> selectAll(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductPerDeclareService.selectForPage(page, length, pageParam);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam  Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForExport/{page}/{length}")
    public Page<RepFinProductPerDeclareDto> selectForExport(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductPerDeclareService.selectForExport(page, length, pageParam);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param bussinessId String
     * @return List<SysUploadFileInfo>
     * @see
     * @since 1.0
     */
    @GetMapping("/getSysUploadFileInfo")
    public List<SysUploadFileInfo> getSysUploadFileInfo(String bussinessId){
        return mstFileCommonService.getFileInfoByBusiId(bussinessId).getUploadFileList();
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param schemeId String
     * @return PrdDeclareSchemeDoc
     * @see
     * @since 1.0
     */
    @GetMapping("/getSeqno")
    public List<PrdDeclareSchemeDoc> getSeqno(String schemeId){
        return repFinProductPerDeclareService.getSeqno(schemeId);
    }
}
