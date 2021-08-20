package com.joyintech.service.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.dao.ModCkGradePdConfigMapper;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * @Description:存款评级PD维护
 * @auther: yuzj
 * @date: 2021/8/13
 * @param: serviceModGradePdConfigService
 * @return:
 */
@Service(value="serviceCkModGradePdConfigService")
public class ModCkGradePdConfigService extends BaseService<ModGradePdConfigDto> {

    /**
     * 估值表查询mapper
     */
    @Autowired
    private ModCkGradePdConfigMapper modCkGradePdConfigMapper;



    public Page<ModGradePdConfigDto> selectForPage(Map<String, Object> pageParam,int page,int length) throws Exception {
//        return super.queryForPageForSelectReturn(page, length, () -> modGradePdConfigMapper.selectAll(pageParam));
        return super.queryForPage(page, length, () -> modCkGradePdConfigMapper.selectAll(pageParam));
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
        List<ModGradePdConfigDto> entityList = modCkGradePdConfigMapper.selectExportList(dto);
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
        List<ModGradePdConfigDto> entityList = modCkGradePdConfigMapper.selectByMap(params);
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
        ModGradePdConfigDto entity = modCkGradePdConfigMapper.selectByPrimaryKey(dto);
        if(entity != null){
            ModGradePdConfigDto d = new ModGradePdConfigDto();
            BeanUtil.copyProperties(d,entity);
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

        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {//删除操作
            modCkGradePdConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {//新建操作
            entity.setUpdateTime(new Date());
            modCkGradePdConfigMapper.insert(entity);
        }
        else {//修改
            modCkGradePdConfigMapper.updateByPrimaryKey(entity);
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
        return modCkGradePdConfigMapper.getRepeatCheckList(entity);
    }

    /**
     * 根据主键获取信息
     * @auther: yuzj
     * @date: 2021/6/27 1:52
     * @param:
     * @return:
     */
    public ModGradePdConfigDto selectByPrimaryKey(ModGradePdConfigDto entity){
        return modCkGradePdConfigMapper.selectByPrimaryKey(entity);
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
            List<ModGradePdConfigDto> repetList = modCkGradePdConfigMapper.selectRepetList(dto);
            if(repetList.size()>0){
                if("1".equals(type)){//覆盖数据
                    modCkGradePdConfigMapper.updateRepetDto(dto);
                }
            }else{
                dto.setSeqNo(IDGenerator.generate());
                modCkGradePdConfigMapper.insert(dto);
            }
        }
    }


    /**
     * 插入数据
     * @param dto
     * @return
     */
    public int insert(ModGradePdConfigDto dto) {
        return modCkGradePdConfigMapper.insert(dto);
    }
    /**
     * 更新数据
     * @param dto
     * @return
     */
    public int update(ModGradePdConfigDto dto) {
        return modCkGradePdConfigMapper.updateByPrimaryKey(dto);
    }
    /**
     * 删除数据
     * @param dto
     * @return
     */
    public int delete(ModGradePdConfigDto dto) {
        return modCkGradePdConfigMapper.deleteByPrimaryKey(dto.getSeqNo());
    }

    /**
     * 根据map参数校验是否被使用过
     * @param map
     * @return
     */
    public int checkBySeqNoAndType(Map<String,Object> map){
        return modCkGradePdConfigMapper.checkBySeqNoAndType(map);
    }

}
