package com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dao.InsideOutsideGrade001Mapper;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 内外部评级映射管理微服务类
 * 
 * @author 刘华明
 * @version 1.0 2021年7月6日
 * @see InsideOutsideGrade001Service
 * @since 1.0
 */
@Service(value="serviceInsideOutsideGrade001Service")
@Transactional(rollbackFor = Exception.class)
public class InsideOutsideGrade001Service extends BaseService<InsideOutsideGrade001Dto> {

    /**
     * InsideOutsideGrade001Mapper
     */
    @Autowired
    private InsideOutsideGrade001Mapper mapper;

    /**
     * 删除
     * @param dto
     * @return
     */
    public int delete(InsideOutsideGrade001Dto dto) {
        return mapper.deleteByPrimaryKey(dto.getGradeInst(),dto.getoType());
    }

    /**
     * 插入
     * @param dto
     */
    public void insert(InsideOutsideGrade001Dto dto){
        // 保存
        mapper.insert(dto);
    }

    /**
     * 导出
     * @param map
     * @return
     */
    public List<InsideOutsideGrade001Dto> selectForExport(Map<String, Object> map) {
        return mapper.getQueryData(map);
    }

    /**
     * 查询
     *
     * @param map
     * @return List<InsideOutsideGrade001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<InsideOutsideGrade001Dto> getQueryDataList(int page, int length, Map<String, Object> map) throws Exception {
        return super.queryForPage(page,length,()-> mapper.getQueryData(map));
    }

    /**
     * 查询
     *
     * @param map
     * @return List<InsideOutsideGrade001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<InsideOutsideGrade001Dto> getBySeqNo(Map<String, Object> map) throws Exception {
        return mapper.getBySeqNo(map);
    }

    /**
     * 增加时重复校验
     * @param dto
     * @return
     */
    public boolean haveData(InsideOutsideGrade001Dto dto){
        int count=mapper.checkData(dto);
        if(count>0){
            return true;
        }
        return false;
    }


}
