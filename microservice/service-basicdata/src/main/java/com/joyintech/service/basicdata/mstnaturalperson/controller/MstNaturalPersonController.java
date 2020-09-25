/*
 * 文件名：MstNaturalPerson001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstnaturalperson.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.service.basicdata.mstnaturalperson.service.MstNaturalPersonService;

/**
 * 自然人信息管理
 * 
 * @author xiexinting
 * @version 1.0 2018年8月10日
 * @see MstNaturalPersonController
 * @since 1.0
 */
@RestController("MstNaturalPersonCtr_dtx")
@RequestMapping("/MstNaturalPerson")
public class MstNaturalPersonController  {

    /**
     * 自然人信息service
     */
    @Autowired
    private MstNaturalPersonService mstNaturalPerson001Service;
    /**
     * 查询 <功能详细描述>
     * 
     * @param pageParam 查询信息
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstNaturalPerson001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstNaturalPerson001Service.selectForPage(page,length,pageParam);
    }
    /**
     * 
     * 客户名称联想文本框service层实现
     * <功能详细描述>
     * @param map 联想文本框查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("/getcName")
    public List<SuggestDto> getcName(@RequestBody Map<String, String> map){
        return mstNaturalPerson001Service.getcName(map);
    }
    /**
     * 客户身份证联想文本框service层实现
     *
     * @param param 检索参数
     * @return SuggestDto
     */
    @GetMapping("/getcID")
    public List<SuggestDto> getcID(String param){
        return mstNaturalPerson001Service.getcID(param);
    }
    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("/checkCodeIsUse")
    public int checkCodeIsUse(@RequestBody MstNaturalPerson001Dto dto){
        return mstNaturalPerson001Service.checkCodeIsUse(dto);
    }
    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("/checkAllCodeIsUse")
    public int checkAllCodeIsUse(@RequestBody List<MstNaturalPerson001Dto> item){
        return mstNaturalPerson001Service.checkAllCodeIsUse(item);
    }
    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param customerId 查询条件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @GetMapping("/selectDetail")
    public MstNaturalPerson001Dto selectDetail(String customerId){
        return mstNaturalPerson001Service.selectDetail(customerId);
    }
    /**
     * 
     * 工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody MstNaturalPerson001Dto entity){
        
        mstNaturalPerson001Service.doComplete(entity,entity.getOperateType());
    }
    
    /**
     * 
     * 新建验证
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectByPersonId")
    public int selectByPersonId(@RequestBody MstNaturalPerson001Dto dto){
        return mstNaturalPerson001Service.selectByPersonId(dto);
    }
    /**
     * 
     * 修改验证
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectByPidandCid")
    public int selectByPidandCid(@RequestBody MstNaturalPerson001Dto dto){
        return mstNaturalPerson001Service.selectByPidandCid(dto);
    }
    
    /**
     * 
     * 删除自然人
     * <功能详细描述>
     * @param customerIdList
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public boolean delete(@RequestBody List<String> customerIdList){
        return mstNaturalPerson001Service.delete(customerIdList);
    }
}
