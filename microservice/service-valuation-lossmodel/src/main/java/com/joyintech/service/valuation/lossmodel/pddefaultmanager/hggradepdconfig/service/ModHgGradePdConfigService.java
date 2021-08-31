package com.joyintech.service.valuation.lossmodel.pddefaultmanager.hggradepdconfig.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.hggradepdconfig.dao.ModHgGradePdConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * @Description:回购评级PD维护Service
 * @auther: yuzj
 * @date: 2021/8/13
 * @param:
 * @return:
 */
@Service(value="serviceModHgGradePdConfigService")
public class ModHgGradePdConfigService extends BaseService<ModGradePdConfigDto> {

    /**
     * 估值表查询mapper
     */
    @Autowired
    private ModHgGradePdConfigMapper modHgGradePdConfigMapper;



    public Page<ModGradePdConfigDto> selectForPage(Map<String, Object> pageParam,int page,int length) throws Exception {
        Page<ModGradePdConfigDto> pageList =super.queryForPage(page, length, () -> modHgGradePdConfigMapper.selectAll(pageParam));
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
        List<ModGradePdConfigDto> entityList = modHgGradePdConfigMapper.selectExportList(dto);
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
        List<ModGradePdConfigDto> entityList = modHgGradePdConfigMapper.selectByMap(params);
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
        ModGradePdConfigDto entity = modHgGradePdConfigMapper.selectByPrimaryKey(dto);
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
        //处理前瞻前年度边际PD%字段   /100
        entity.setForwardYearPd(ArithmeticUtil.divide(entity.getForwardYearPd(), new BigDecimal("100"),6));
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {//删除操作
            modHgGradePdConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {//新建操作
            entity.setUpdateTime(new Date());
            modHgGradePdConfigMapper.insert(entity);
        }
        else {//修改
            modHgGradePdConfigMapper.updateByPrimaryKey(entity);
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
        return modHgGradePdConfigMapper.getRepeatCheckList(entity);
    }

    /**
     * 根据主键获取信息
     * @auther: yuzj
     * @date: 2021/6/27 1:52
     * @param:
     * @return:
     */
    public ModGradePdConfigDto selectByPrimaryKey(ModGradePdConfigDto entity){
        ModGradePdConfigDto dto=modHgGradePdConfigMapper.selectByPrimaryKey(entity);
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
            List<ModGradePdConfigDto> repetList = modHgGradePdConfigMapper.selectRepetList(dto);
            //处理前瞻前年度边际PD%字段   *100
            dto.setForwardYearPd(ArithmeticUtil.divide(dto.getForwardYearPd(), new BigDecimal("100"),6));
            if(repetList.size()>0){
                if("1".equals(type)){//覆盖数据
                    modHgGradePdConfigMapper.updateRepetDto(dto);
                }
            }else{
                dto.setSeqNo(IDGenerator.generate());
                modHgGradePdConfigMapper.insert(dto);
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
        return modHgGradePdConfigMapper.insert(dto);
    }
    /**
     * 更新数据
     * @param dto
     * @return
     */
    public int update(ModGradePdConfigDto dto) {
        if(dto.getForwardYearPd() !=null){
            //处理前瞻前年度边际PD%字段   /100
            dto.setForwardYearPd(ArithmeticUtil.divide(dto.getForwardYearPd(), new BigDecimal("100"),6));
        }
        return modHgGradePdConfigMapper.updateByPrimaryKey(dto);
    }
    /**
     * 删除数据
     * @param dto
     * @return
     */
    public int delete(ModGradePdConfigDto dto) {
        return modHgGradePdConfigMapper.deleteByPrimaryKey(dto.getSeqNo());
    }

    /**
     * 根据map参数校验是否被使用过
     * @param map
     * @return
     */
    public int checkBySeqNoAndType(Map<String,Object> map){
        return modHgGradePdConfigMapper.checkBySeqNoAndType(map);
    }

}
