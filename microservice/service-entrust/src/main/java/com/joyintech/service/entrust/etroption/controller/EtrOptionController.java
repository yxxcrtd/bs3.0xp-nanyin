/*
 * 文件名：EtrOptionController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etroption.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.service.entrust.etroption.dto.EtrOptionDto;
import com.joyintech.service.entrust.etroption.service.EtrOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 期权投资委托微服务controller
 * 〈功能详细描述〉
 * @author likai
 * @version 1.0 2019年12月23日
 * @see EtrOptionController
 * @since 1.0
 */
@RestController("EtrOptiontr_dtx")
@RequestMapping("/EtrOption")
public class EtrOptionController {
    
    /**
     * 拆借投资委托微服务service
     */
    @Autowired
    private EtrOptionService etrOptionService;
    /**
     * 
     * 分页查询
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
    public Page<EtrOptionDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
         Page<EtrOptionDto> page1 =etrOptionService.selectForPage(page, length, param);
        return page1;
    }
    
    /**
     *
     * 查询页面组合代码联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map){
        return etrOptionService.getentrustId(map);
    }

    /**
     *
     * 查询页面合约标的联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getOptionTargetId")
    public List<SuggestDto> getOptionTargetId(@RequestBody Map<String, Object> map){
        return etrOptionService.getOptionTargetId(map);
    }
    /**
     *
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return etrOptionService.getentrustIdAdd(page,length,map);
    }
    /**
     *
     * 新建页面合约标的联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getOptionTargetIdadd/{page}/{length}")
    public Page<SelectOptionDto> getOptionTargetIdadd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return etrOptionService.getOptionTargetIdadd(page,length,map);
    }

    /**
     *
     * 操作前校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrOptionDto dto){
        return etrOptionService.check(dto);
    }
    /**
     * 撤回委托
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrOptionDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrOptionService.recallDelegate(dto);
    }
    /**
     * 关闭委托
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrOptionService.closeDelegate(entrustId, closeWay);
    }
//    /**
//     *
//     * 删除投资委托
//     * <功能详细描述>
//     * @param entrustId
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @Transactional
//    @DTXTransaction
//    @GetMapping("delete")
//    public boolean deleteDelegate(String entrustId) {
//        return etrLendSpecInfoService.deleteDelegate(entrustId);
//    }
    /**
     *
     * 修改/查看信息查询
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrOptionDto selectByKey (String entrustId){
        return etrOptionService.selectByKey(entrustId);
    }
    /**
     *
     * 工作流完成方法
     * <功能详细描述>
     * @param map
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrOptionDto dto) throws IllegalAccessException, InvocationTargetException{
        etrOptionService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrOptionDto dto){
        etrOptionService.doInsert(dto, dto.getOperateType());
    }
//    /**
//     *
//     * 组合可用现金查询
//     * <功能详细描述>
//     * @param dto
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @PostMapping("getProAvailableCash")
//    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
//        return etrLendSpecInfoService.getProAvailableCash(param);
//    }
//
    /**
     *
     * 查询上传文件
     * <功能详细描述>
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    @GetMapping("/selectFileByBusinessId")
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
        return etrOptionService.selectFileByBusinessId(businessId);
    }
}
