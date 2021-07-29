package com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dao.RskSpecificAssetTypeMapper;
import com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dto.RskSpecificAssetTypeDto;

@Service("backRskSpecificAssetTypeService")
public class RskSpecificAssetTypeService extends BaseService {

    @Autowired
    private com.joyintech.tams.common.base.dao.RskSpecificAssetTypeMapper rskSpecificAssetTypeMapper;


    @Resource(name = "rskPageSpecificAssetTypeMapper")
    private RskSpecificAssetTypeMapper rskSpecificAssetTypeMapper2;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskSpecificAssetTypeDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSpecificAssetTypeMapper2.selectForPage(pageParam));
    }


    /**
     * 插入风险事件信息
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskSpecificAssetTypeDto entity) {
        rskSpecificAssetTypeMapper.insert(entity);
    }

    /**
     * 更新
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(RskSpecificAssetTypeDto entity) {
        rskSpecificAssetTypeMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 移除
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(RskSpecificAssetTypeDto entity) {
        rskSpecificAssetTypeMapper.deleteByPrimaryKey(entity.getSpecificId());
    }

    public RskSpecificAssetTypeDto getBasicInfo(String specificId){
        return rskSpecificAssetTypeMapper2.getBasicInfo(specificId);
    }

    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getAssetIdOption(int page, int length, @RequestBody Map<String, Object> param) {
        return this.queryForPage(page, length,() ->rskSpecificAssetTypeMapper2.getAssetIdOption(param));
    }

    public int getRepeatCount(Map<String,Object> param){
        return rskSpecificAssetTypeMapper2.getRepeatCount(param);
    }
}
