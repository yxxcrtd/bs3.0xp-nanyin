package com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.service;

import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import com.joyintech.tams.common.base.dao.MstValIrsMapper;
import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.common.base.entity.MstValIrsKey;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dao.ComRateSwapValuationInfo002Mapper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Service("serviceComRateSwapValuationInfo002Service")
public class ComRateSwapValuationInfo002Service extends CommonExportService<ComRateSwapValuationInfo002Dto> implements BaseProcessService<ComRateSwapValuationInfo002Dto> {

    @Autowired
    private ComRateSwapValuationInfo002Mapper comRateSwapValuationInfo002Mapper;
    
    @Autowired
    private MstValIrsMapper mstValIrsMapper;

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ComRateSwapValuationInfo002Dto entity, OperationType type) {

        if (OperationType.NEW.equals(type)) {
            this.insertSelective(entity);
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity);
        }
        else if (OperationType.DELETE.equals(type)) {
            this.deleteByPrimaryKey(entity);
        }
    }

    /**
     * 查询 利率互换估值信息
     *
     * @param configId
     * @return
     */
    public ComRateSwapValuationInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return comRateSwapValuationInfo002Mapper.selectByPrimaryKey(map);
    }


    /**
     * 新建 利率互换估值信息
     *
     * @param dto
     */
    public void insertSelective(ComRateSwapValuationInfo002Dto dto) {
        MstValIrs mstValIrs = new MstValIrs();

        BeanUtils.copyProperties(dto, mstValIrs);
        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));
        
        mstValIrsMapper.insertSelective(mstValIrs);
    }


    /**
     * 修改 利率互换估值信息
     *
     * @param dto
     */
    public void updateByPrimaryKeySelective(ComRateSwapValuationInfo002Dto dto) {

        comRateSwapValuationInfo002Mapper.updateByPrimaryKeySelective(dto);

/*        MstValIrs mstValIrs = new MstValIrs();

        BeanUtils.copyProperties(dto, mstValIrs);
        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));

        mstValIrsMapper.updateByPrimaryKeySelective(mstValIrs);*/
    }

    /**
     * 删除 利率互换估值信息
     *
     * @param dto
     */
    public void deleteByPrimaryKey(ComRateSwapValuationInfo002Dto dto) {
        MstValIrsKey key = new MstValIrsKey();

        BeanUtils.copyProperties(dto, key);
        key.setValDate(DateUtil.parse(dto.getValDate()));

        mstValIrsMapper.deleteByPrimaryKey(key);
    }



}
