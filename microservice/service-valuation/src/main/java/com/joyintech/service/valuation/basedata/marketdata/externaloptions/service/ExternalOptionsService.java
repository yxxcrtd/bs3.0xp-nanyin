package com.joyintech.service.valuation.basedata.marketdata.externaloptions.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.tams.common.base.dao.MstValOpMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-31 10:39
 */
@Service
public class ExternalOptionsService extends CommonExportService<ExternalOptions002Dto> implements BaseProcessService<ExternalOptions002Dto> {

    @Autowired
    ExternalOptionsDao externalOptionsDao;

    @Autowired
    MstValOpMapper mstValOpMapper;

    public List<SuggestDto> findFinRateInfoByBenchmarkId(Map<String, Object> params) {
        return externalOptionsDao.findFinRateInfoByBenchmarkId(params);
    }

    public List<SuggestDto> findTargetId(Map<String, Object> params) {
        return externalOptionsDao.findTargetId(params);
    }



    public List<SuggestDto> findFinprodId(Map<String, Object> params) {
        return externalOptionsDao.findFinprodId(params);
    }

    public ExternalOptions002Dto findByPrimaryKey(Map<String, Object> map) {
        return externalOptionsDao.selectByPrimaryKey(map);
    }

    public String findByBenchmarkId(String finprodId2) {
        return externalOptionsDao.findByBenchmarkId(finprodId2);
    }

    @Override
    public void doComplete(ExternalOptions002Dto entity, OperationType type) {

        if (OperationType.NEW.equals(type)) {
            this.insertSelective(entity);
        } else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity);
        } else if (OperationType.DELETE.equals(type)) {
            this.deleteByPrimaryKey(entity);
        }
    }

    /**
     * 新建
     *
     * @param dto
     */
    public void insertSelective(ExternalOptions002Dto dto) {
//        MstValFx mstValIrs = new MstValFx();
//
//        BeanUtils.copyProperties(dto, mstValIrs);
//        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));
//
//        mstValFxMapper.insertSelective(mstValIrs);
        MstValOp mstValOp = new MstValOp();
        BeanUtils.copyProperties(dto, mstValOp);
        mstValOp.setValDate(DateUtil.parse(dto.getValDate()));
        mstValOp.setPublishDate(DateUtil.parse(dto.getPublishDate()));
        mstValOpMapper.insert(mstValOp);
    }

    /**
     * 修改
     *
     * @param dto
     */
    public void updateByPrimaryKeySelective(ExternalOptions002Dto dto) {
//        MstValFx mstValIrs = new MstValFx();
//
//        BeanUtils.copyProperties(dto, mstValIrs);
//        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));
//
//        mstValFxMapper.updateByPrimaryKeySelective(mstValIrs);
        MstValOp mstValOp = new MstValOp();
        BeanUtils.copyProperties(dto, mstValOp);
        mstValOp.setValDate(DateUtil.parse(dto.getValDate()));
        mstValOp.setPublishDate(DateUtil.parse(dto.getPublishDate()));
        mstValOpMapper.updateByPrimaryKeySelective(mstValOp);
    }

    /**
     * 删除
     *
     * @param dto
     */
    public void deleteByPrimaryKey(ExternalOptions002Dto dto) {
//        MstValFxKey key = new MstValFxKey();
//        BeanUtils.copyProperties(dto, key);
//        key.setValDate(DateUtil.parse(dto.getValDate()));
//        mstValFxMapper.deleteByPrimaryKey(key);
        MstValOpKey key = new MstValOpKey();
        BeanUtils.copyProperties(dto, key);
        key.setValDate(DateUtil.parse(dto.getValDate()));
        mstValOpMapper.deleteByPrimaryKey(key);
    }

    public List<ExternalOptions001Dto> selectList(Map<String, Object> map) {
        return externalOptionsDao.selectList(map);
    }

    /**
     * 列表查询
     * @param params
     * @return
     */
    public List<ExternalOptions001Dto> queryAllList(Map<String, Object> params) {
        return externalOptionsDao.selectList(params);
    }
    
    /**
     * 估值日期验证
     * @param finprodId2
     * @return
     */
    public ExternalOptions002Dto dateCheck(Map<String, Object> params) {
        return externalOptionsDao.dateCheck(params);
    }

}
