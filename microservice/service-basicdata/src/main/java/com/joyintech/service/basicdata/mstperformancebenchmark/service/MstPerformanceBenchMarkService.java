/*
 * 文件名：MstCaptialAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstperformancebenchmark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstIndexComponentMapper;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstperformancebenchmark.dao.MstPerformanceBenchMarkMapper;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
/**
 * 
 * 基准信息Serivice
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月20日
 * @see MstPerformanceBenchMarkService
 * @since 1.0
 */
@Service("backMstPerformanceBenchMarkService")
public class MstPerformanceBenchMarkService extends BaseService<MstPerformanceBenchMarkDto>{

    /**
     * 手写基准信息mapper
     */
    @Autowired
    private MstPerformanceBenchMarkMapper mstPerformanceBenchMark001Mapper;

    /**
     * 基准信息管理mapper(自动生成)
     */
    @Autowired
    private MstIndexInfoMapper mstIndexInfoMapper;

    /**
     * 基准成分管理mapper(自动生成)
     */
    @Autowired
    private MstIndexComponentMapper mstIndexComponentMapper;

    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<MstPerformanceBenchMarkDto> selectForPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> mstPerformanceBenchMark001Mapper.select(pageParam));
    }
        /**
         * 查询基准成分
         * @param pageParam Map
         * @return Page类
         * @throws Exception 异常信息
         */
        public List<MstIndexComponent> selectComponentInfoPage(Map<String, Object> pageParam)
            throws Exception {
            return  mstPerformanceBenchMark001Mapper.selectComponentInfoPage(pageParam);
        }
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getIndexName(Map<String,String> map) {
        return mstPerformanceBenchMark001Mapper.getIndexName(map);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param indexId String
     * @return MstPerformanceBenchMarkDto
     * @see
     * @since 1.0
     */
    public MstPerformanceBenchMarkDto selectDetail(String indexId) {
        return mstPerformanceBenchMark001Mapper.selectDetail(indexId);
    }

    /**
     * 增删改查
     * 
     * @param entity MstPerformanceBenchMarkDto
     * @param type OperationType
     * @see
     * @since 1.0
     */
    public void doComplete(MstPerformanceBenchMarkDto entity) {
        String type = entity.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            mstPerformanceBenchMark001Mapper.deleteInfoByPrimaryKey(entity.getIndexId());
            mstPerformanceBenchMark001Mapper.deleteByPrimaryKey(entity.getIndexId());
        }
        else if (StringUtil.equals(type, "NEW")) {
            mstIndexInfoMapper.insert(entity);
            List<ComponentInfoDto> componentInfoList =new ArrayList<ComponentInfoDto>();
            componentInfoList=entity.getComponentInfo();
           // mstPerformanceBenchMark001Mapper.deleteByPrimaryKey(entity.getIndexId());
            for(MstIndexComponent mstIndexComponent:componentInfoList){
                mstIndexComponent.setIndexId(entity.getIndexId());
                mstIndexComponentMapper.insert(mstIndexComponent);
            }
        }
        else {
            //MstIndexComponent mstIndexComponent =new MstIndexComponent();
            List<ComponentInfoDto> componentInfoList = new ArrayList<ComponentInfoDto>();
            componentInfoList = entity.getComponentInfo();
            //mstIndexComponent.setComponentId(entity.getComponentInfo());
            //在这里的修改，主要是表格中可能会出现多行数据，也就是一个mstIndexInfo表对应多个mstIndexComponent，这样的话，我们选择直接修改mstIndexInfo表，删除mstIndexComponent，然后循环添加
            mstPerformanceBenchMark001Mapper.deleteByPrimaryKey(entity.getIndexId());
            for(MstIndexComponent mstIndexComponent:componentInfoList){
                mstIndexComponent.setIndexId(entity.getIndexId());
                mstIndexComponent.setWorkflowId(entity.getWorkflowId());
                mstIndexInfoMapper.updateByPrimaryKeySelective(entity);
                mstIndexComponentMapper.insert(mstIndexComponent);
            }
            
        }

    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstPerformanceBenchMarkDto dto) {
        return mstPerformanceBenchMark001Mapper.checkCodeIsUse(dto);
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkAllCodeIsUse(List<MstPerformanceBenchMarkDto> item) {
        return mstPerformanceBenchMark001Mapper.checkAllCodeIsUse(item);
    }

    /**
     * 新增
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see 
     */
    public int insert(MstPerformanceBenchMarkDto dto) {
        return mstPerformanceBenchMark001Mapper.insert(dto);
    }

    /**
     * 通过indexName查询
     * <功能详细描述>
     * @param dto 数据条件
     * @return 结果
     * @see
     * @since 1.0
     */
    public int selectByIndexName(MstPerformanceBenchMarkDto dto) {
        return mstPerformanceBenchMark001Mapper.selectByIndexName(dto);
    }

    /**
     * 通过主键统计数据
     * <功能详细描述>
     * @param mstIndexComponent 主键
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int selectByPrimaryKey(MstIndexComponent mstIndexComponent) {
        return mstPerformanceBenchMark001Mapper.selectByPrimaryKey(mstIndexComponent);
    }

}
