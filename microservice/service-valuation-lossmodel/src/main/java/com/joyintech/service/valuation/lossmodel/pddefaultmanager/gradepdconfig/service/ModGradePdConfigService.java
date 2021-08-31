/*
 * 文件名：ModGradePdConfigService.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */

package com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dao.ModGradePdConfigMapper;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * 估值余额表查询Service
 * 
 * @author 杨智
 * @version 1.0 2018年7月25日
 * @see ModGradePdConfigService
 * @since 1.0
 */
@Service(value="serviceModGradePdConfigService")
public class ModGradePdConfigService extends BaseService<ModGradePdConfigDto> {

    /**
     * 估值表查询mapper
     */
    @Autowired
    private ModGradePdConfigMapper modGradePdConfigMapper;

    public Page<ModGradePdConfigDto> selectForPage(Map<String, Object> pageParam,int page,int length) throws Exception {
        Page<ModGradePdConfigDto> pageList = super.queryForPage(page, length, () -> modGradePdConfigMapper.selectAll(pageParam));
        for(ModGradePdConfigDto dto:pageList.getRoot()){
            //处理前瞻前年度边际PD%字段   *100
            dto.setForwardYearPd(ArithmeticUtil.multiply(dto.getForwardYearPd(), new BigDecimal("100")));
        }
        return pageList;
    }


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public List<ModGradePdConfigDto> selectExportList(ModGradePdConfigDto dto) throws IllegalAccessException, InvocationTargetException {
        List<ModGradePdConfigDto> entityList = modGradePdConfigMapper.selectExportList(dto);
        for(ModGradePdConfigDto pdDto:entityList){
            //处理前瞻前年度边际PD%字段   *100
            pdDto.setForwardYearPd(ArithmeticUtil.multiply(pdDto.getForwardYearPd(), new BigDecimal("100")));
        }
        return entityList;
    }


    /**
     * 根据map参数获取对象列表
     * <功能详细描述>
     * @param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public List<ModGradePdConfigDto> selectByMap(Map<String,Object> params) throws IllegalAccessException, InvocationTargetException {
        List<ModGradePdConfigDto> entityList = modGradePdConfigMapper.selectByMap(params);
        for(ModGradePdConfigDto dto:entityList){
            //处理前瞻前年度边际PD%字段   *100
            dto.setForwardYearPd(ArithmeticUtil.multiply(dto.getForwardYearPd(), new BigDecimal("100")));
        }
        return entityList;
    }

    /**
     * @Description:根据实体参数获取实体信息
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    public ModGradePdConfigDto selectByKey(ModGradePdConfigDto dto) throws IllegalAccessException, InvocationTargetException {
        ModGradePdConfigDto entity = modGradePdConfigMapper.selectByPrimaryKey(dto);
        if(entity != null){
            ModGradePdConfigDto d = new ModGradePdConfigDto();
            BeanUtil.copyProperties(d,entity);
            //处理前瞻前年度边际PD%字段   *100
            d.setForwardYearPd(ArithmeticUtil.multiply(d.getForwardYearPd(), new BigDecimal("100")));
            return d;
        }
        return null;
    }


    /**
     * @Description:工作流，实现增删改的方法
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    public void doComplete(ModGradePdConfigDto entity, String type) {
        //处理前瞻前年度边际PD%字段   *100
        entity.setForwardYearPd(ArithmeticUtil.divide(entity.getForwardYearPd(), new BigDecimal("100"),6));
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {//删除操作
            modGradePdConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {//新建操作
            entity.setUpdateTime(new Date());
            modGradePdConfigMapper.insert(entity);
        }
        else {//修改
            modGradePdConfigMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * 校验年份、评级类型、评级结果是否重复
     * @auther: yuzj
     * @date: 2021/6/27 1:52
     * @param:
     * @return:
     */
    public int getRepeatCheckList(ModGradePdConfigDto entity){
        return modGradePdConfigMapper.getRepeatCheckList(entity);
    }

    /**
     * 根据主键获取信息
     * @auther: yuzj
     * @date: 2021/6/27 1:52
     * @param:
     * @return:
     */
    public ModGradePdConfigDto selectByPrimaryKey(ModGradePdConfigDto entity){
        ModGradePdConfigDto dto =modGradePdConfigMapper.selectByPrimaryKey(entity);
        //处理前瞻前年度边际PD%字段   *100
        dto.setForwardYearPd(ArithmeticUtil.multiply(dto.getForwardYearPd(), new BigDecimal("100")));
        return dto;
    }



    /**
     * @Description:保存导入数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    public void saveModGradePdConfigDtoList(List<ModGradePdConfigDto> dtoList,String type){
        for(ModGradePdConfigDto dto:dtoList){
            //判断是否有重复数据，重复更新
            List<ModGradePdConfigDto> repetList = modGradePdConfigMapper.selectRepetList(dto);
            //处理前瞻前年度边际PD%字段   *100
            dto.setForwardYearPd(ArithmeticUtil.divide(dto.getForwardYearPd(), new BigDecimal("100"),6));
            if(repetList.size()>0){
                if("1".equals(type)){//覆盖数据
                    modGradePdConfigMapper.updateRepetDto(dto);
                }
            }else{
                dto.setSeqNo(IDGenerator.generate());
                modGradePdConfigMapper.insert(dto);
            }
        }
    }


    /**
     * 插入数据
     * @param dto
     * @return
     */
    public int insert(ModGradePdConfigDto dto) {
        //处理前瞻前年度边际PD%字段   *100
        dto.setForwardYearPd(ArithmeticUtil.divide(dto.getForwardYearPd(), new BigDecimal("100"),6));
        return modGradePdConfigMapper.insert(dto);
    }
    /**
     * 更新数据
     * @param dto
     * @return
     */
    public int update(ModGradePdConfigDto dto) {
        if(dto.getForwardYearPd() !=null){
            //处理前瞻前年度边际PD%字段   *100
            dto.setForwardYearPd(ArithmeticUtil.divide(dto.getForwardYearPd(), new BigDecimal("100"),6));
        }
        return modGradePdConfigMapper.updateByPrimaryKey(dto);
    }
    /**
     * 删除数据
     * @param dto
     * @return
     */
    public int delete(ModGradePdConfigDto dto) {
        return modGradePdConfigMapper.deleteByPrimaryKey(dto.getSeqNo());
    }

    /**
     * 根据map参数校验是否被使用过
     * @param map
     * @return
     */
    public int checkBySeqNoAndType(Map<String,Object> map){
        return modGradePdConfigMapper.checkBySeqNoAndType(map);
    }

}
