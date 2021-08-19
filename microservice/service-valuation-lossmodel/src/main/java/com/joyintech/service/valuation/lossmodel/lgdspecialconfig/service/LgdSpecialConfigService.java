
package com.joyintech.service.valuation.lossmodel.lgdspecialconfig.service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.valuation.lossmodel.lgdspecialconfig.dao.LgdSpecialConfigMapper;
import com.joyintech.service.valuation.lossmodel.lgdspecialconfig.dto.LgdSpecialConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * 特殊资产LGD维护Service
 * 
 * @author yuzj
 * @version 1.0 2021年7月1日
 * @see LgdSpecialConfigService
 * @since 1.0
 */
@Service(value="serviceLgdSpecialConfigService")
public class LgdSpecialConfigService extends BaseService<LgdSpecialConfigDto> {

    /**
     * 特殊资产LGD维护mapper
     */
    @Autowired
    private LgdSpecialConfigMapper lgdSpecialConfigMapper;



    public Page<LgdSpecialConfigDto> selectForPage(Map<String, Object> pageParam, int page, int length) throws Exception {
        return super.queryForPage(page, length, () -> lgdSpecialConfigMapper.selectAll(pageParam));
    }


    /**
     * @Description:根据条件查询需要导出的数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return: 
     */
    public List<LgdSpecialConfigDto> selectExportList(LgdSpecialConfigDto dto) throws IllegalAccessException, InvocationTargetException {
        List<LgdSpecialConfigDto> entityList = lgdSpecialConfigMapper.selectExportList(dto);
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
    public List<LgdSpecialConfigDto> selectByMap(Map<String,Object> params) throws IllegalAccessException, InvocationTargetException {
        List<LgdSpecialConfigDto> entityList = lgdSpecialConfigMapper.selectByMap(params);
        return entityList;
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
    public LgdSpecialConfigDto selectByKey(LgdSpecialConfigDto dto) throws IllegalAccessException, InvocationTargetException {
        LgdSpecialConfigDto entity = lgdSpecialConfigMapper.selectByPrimaryKey(dto);
        if(entity != null){
            LgdSpecialConfigDto d = new LgdSpecialConfigDto();
            BeanUtil.copyProperties(d,entity);
            return d;
        }
        return null;
    }



    /**
     * @Description:工作流，实现增删改的方法
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:entity 特殊资产LGD维护实体类  type 操作类型
     * @return:
     */
    public void doComplete(LgdSpecialConfigDto entity, String type) {
        //删除操作
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            lgdSpecialConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
        //新建操作
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setUpdateTime(new Date());
            lgdSpecialConfigMapper.insert(entity);
        }
        //修改
        else {
            lgdSpecialConfigMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * @Description:校验年份、评级机构、评级类型、评级结果是否重复
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    public int getRepeatCheckList(LgdSpecialConfigDto entity){
        return lgdSpecialConfigMapper.getRepeatCheckList(entity);
    }

    /**
     * 根据主键获取实体信息
     * @auther: yuzj
     * @date: 2021/6/27 1:52
     * @param:
     * @return:
     */
    public LgdSpecialConfigDto selectByPrimaryKey(LgdSpecialConfigDto entity){
        return lgdSpecialConfigMapper.selectByPrimaryKey(entity);
    }


    /**
     * @Description:保存导入数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:type 重复数据是否覆盖   0不覆盖  1覆盖
     * @return:
     */
    public void saveModGradePdConfigDtoList(List<LgdSpecialConfigDto> dtoList,String type){
        for(LgdSpecialConfigDto dto:dtoList){
            //判断是否有重复数据，重复更新
            List<LgdSpecialConfigDto> repetList = lgdSpecialConfigMapper.selectRepetList(dto);
            if(repetList.size()>0){
                if("1".equals(type)){//覆盖数据
                    lgdSpecialConfigMapper.updateRepetDto(dto);
                }
            }else{
                lgdSpecialConfigMapper.insert(dto);
            }
        }
    }


    /**
     * 插入数据
     * @param dto
     * @return
     */
    public int insert(LgdSpecialConfigDto dto) {
        return lgdSpecialConfigMapper.insert(dto);
    }
    /**
     * 更新数据
     * @param dto
     * @return
     */
    public int update(LgdSpecialConfigDto dto) {
        return lgdSpecialConfigMapper.updateByPrimaryKey(dto);
    }
    /**
     * 删除数据
     * @param dto
     * @return
     */
    public int delete(LgdSpecialConfigDto dto) {
        return lgdSpecialConfigMapper.deleteByPrimaryKey(dto.getSeqNo());
    }



    /**
     * 查询资产名称根据系统减值参数lossType=01
     * @auther: yuzj
     * @date: 2021-8-14
     * @param:
     * @return:
     */
    public List<SelectOptionDto> getFinprodIdByLossTypeOne(Map<String, Object> params){
        return  lgdSpecialConfigMapper.getFinprodIdByLossTypeOne(params);
    }

    /**
     * 查询资产名称根据系统减值参数lossType=02
     * @auther: yuzj
     * @date: 2021-8-14
     * @param:
     * @return:
     */
    public List<SelectOptionDto> getFinprodIdByLossTypeTwo(Map<String, Object> params){
        return  lgdSpecialConfigMapper.getFinprodIdByLossTypeTwo(params);
    }

}
