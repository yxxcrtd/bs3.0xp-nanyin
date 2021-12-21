package com.joyintech.service.basicdata.stlsecreconpara.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.stlsecreconpara.dao.StlSecReconParaPageMapper;
import com.joyintech.service.basicdata.stlsecreconpara.dto.StlSecReconParaDto;
import com.joyintech.tams.common.base.dao.StlSecReconParaMapper;
import com.joyintech.tams.common.base.entity.StlSecReconPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账参数设置-service
 */
@Service("backStlSecReconParaService")
public class StlSecReconParaService extends BaseService<StlSecReconParaDto> {

    // 画面自定义的mapper
    @Autowired
    private StlSecReconParaPageMapper stlSecReconParaPageMapper;

    // 对账参数设置-mapper
    @Autowired
    private StlSecReconParaMapper stlSecReconParaMapper;

    /**
     * 分页查询
     * @param pageParam 画面参数
     * @param page 页数
     * @param length 页大小
     * @return 分页数据
     */
    public Page<StlSecReconParaDto> selectForPage(Map<String, Object> pageParam, int page, int length) {
        // 使用框架调用分页接口
        return super.queryForPage(page,length,() -> stlSecReconParaPageMapper.selectForPage(pageParam));
    }

    /**
     * 查询所有券商ID的下拉列表
     * @param paramMap
     * @return
     */
    public List<SuggestDto> findAllBrokerIds(Map<String, Object> paramMap) {
        return stlSecReconParaPageMapper.findAllBrokerIds(paramMap);
    }

    /**
     * 查询所有券商ID的下拉列表-Select
     * @param paramMap
     * @return
     */
    public List<SelectOptionDto> findAllBrokerIdsSelect(Map<String, Object> paramMap) {
        return stlSecReconParaPageMapper.findAllBrokerIdsSelect(paramMap);
    }


    /**
     * 通过主键查出实体类
     * @param seqNo
     * @return
     */
    public StlSecReconParaDto findEntityByPrimarykey(String seqNo) {
        // 主键查找
        StlSecReconPara stlSecReconPara = stlSecReconParaMapper.selectByPrimaryKey(seqNo);
        StlSecReconParaDto entity = new StlSecReconParaDto();
        // 拷贝属性
        BeanUtil.copyPropertiesIgnoreNull(entity, stlSecReconPara);
        return entity;
    }

    /**
     * 插入一条记录
     * @param entity
     * @return
     */
    public Integer insert(StlSecReconParaDto entity) {
        StlSecReconPara stlSecReconPara = new StlSecReconPara();
        BeanUtil.copyPropertiesIgnoreNull(stlSecReconPara, entity);
        return stlSecReconParaMapper.insert(stlSecReconPara);
    }

    /**
     * 更新一条记录
     * @param entity
     * @return
     */
    public Integer update(StlSecReconParaDto entity) {
        StlSecReconPara stlSecReconPara = new StlSecReconPara();
        BeanUtil.copyPropertiesIgnoreNull(stlSecReconPara, entity);
        return stlSecReconParaMapper.updateByPrimaryKeySelective(stlSecReconPara);
    }

    /**
     * 删除一条记录
     * @param entity
     * @return
     */
    public Integer delete(StlSecReconParaDto entity) {
        String seqNo = entity.getSeqNo();
        return stlSecReconParaMapper.deleteByPrimaryKey(seqNo);
    }

    /**
     * 查询所有记录
     * @param params
     * @return
     */
    public List<StlSecReconParaDto> selectAllList(Map<String, Object> params) {
        return stlSecReconParaPageMapper.selectForPage(params);
    }

    /**
     * 检查dto 是否已存在
     * @return
     */
    public Boolean checkParaIsExist(StlSecReconParaDto dto) {
        Map<String, Object> para = new HashMap<>();
        if (StringUtil.isBlank(dto.getCustomerId())) {
            para.put("paraCode", dto.getParaCode());
            para.put("paraValue", dto.getParaValue());
        }
        else {
            para.put("customerId", dto.getCustomerId());
            para.put("paraCode", dto.getParaCode());
            para.put("paraValue", dto.getParaValue());
        }
        return stlSecReconParaPageMapper.checkParaIsExist(para) > 0;
    }
}
