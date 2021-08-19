/*
 * 文件名：LgdSpecialConfigController.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */
 
package com.joyintech.service.valuation.lossmodel.lgdspecialconfig.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.lossmodel.lgdspecialconfig.dto.LgdSpecialConfigDto;
import com.joyintech.service.valuation.lossmodel.lgdspecialconfig.service.LgdSpecialConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.joyintech.framework.grid.Page;
/**
 * 特殊资产LGD维护Controller
 * 
 * @author yuzj
 * @version 1.0 2021年7月1日
 * @see LgdSpecialConfigController
 * @since 1.0
 */
@RestController(value="serviceLgdSpecialConfigController")
@RequestMapping("LgdSpecialConfig")
public class LgdSpecialConfigController{

    @Autowired
    private LgdSpecialConfigService lgdSpecialConfigService;

    /**
     * 特殊资产LGD分页查询
     * @param params
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<LgdSpecialConfigDto> selectForPage(@RequestBody Map<String,Object> params,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<LgdSpecialConfigDto> pages = lgdSpecialConfigService.selectForPage(params,page,length);
        return pages;
    }

    /**
     * 根据主键查询映射明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByKey")
    public LgdSpecialConfigDto selectByKey(@RequestBody LgdSpecialConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return lgdSpecialConfigService.selectByKey(dto);
    }


    /**
     * 根据条件查询需要导出的数据
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectExportList")
    public List<LgdSpecialConfigDto> selectExportList(@RequestBody LgdSpecialConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return lgdSpecialConfigService.selectExportList(dto);
    }

    /**
     * 根据条件查询需要数据
     * @param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByMap")
    public List<LgdSpecialConfigDto> selectByMap(@RequestBody Map<String,Object> params) throws IllegalAccessException, InvocationTargetException{
        return lgdSpecialConfigService.selectByMap(params);
    }

    /**
     * 根据主键查询映射明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public LgdSpecialConfigDto selectByPrimaryKey(@RequestBody LgdSpecialConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return lgdSpecialConfigService.selectByPrimaryKey(dto);
    }




    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody LgdSpecialConfigDto dto) throws Exception {
        return lgdSpecialConfigService.insert(dto);
    }

    /**
     * 更新数据
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody LgdSpecialConfigDto dto) throws Exception {
        return lgdSpecialConfigService.update(dto);
    }

    /**
     *
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody LgdSpecialConfigDto dto) throws Exception {
        return lgdSpecialConfigService.delete(dto);
    }

    /**
     * 根据流水号+（年份、评级机构、评级类型、评级结果）重复校验
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("getRepeatCheck")
    public Boolean getRepeatCheck(@RequestBody LgdSpecialConfigDto entity){
        Boolean flag =false;
        int num = lgdSpecialConfigService.getRepeatCheckList(entity);
        if(num>0){
            flag =true;
        }
        return flag;
    }


    @RequestMapping("getFinprodIdByLossType")
    @ResponseBody
    public List<SelectOptionDto> getFinprodIdByLossType(@RequestBody Map<String, Object> params) {
        //lossType   01 毕马威；02 普华永道
        List<SelectOptionDto> suggestDtoList = new ArrayList<>();
        String lossType = (String)params.get("lossType");
        if(StringUtil.isNotEmpty(lossType) && "01".equals(lossType)){
            return lgdSpecialConfigService.getFinprodIdByLossTypeOne(params);
        }else if (StringUtil.isNotEmpty(lossType) && "02".equals(lossType)){
            return lgdSpecialConfigService.getFinprodIdByLossTypeTwo(params);
        }
        return  suggestDtoList;
    }








}
