package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo002Mapper;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.common.base.dao.MstValFxMapper;
import com.joyintech.tams.common.base.entity.MstValFx;
import com.joyintech.tams.common.base.entity.MstValFxKey;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Service("serviceValFxValuztionInfo002Service")
public class ValFxValuztionInfo002Service extends CommonExportService<ValFxValuztionInfo002Dto> implements BaseProcessService<ValFxValuztionInfo002Dto> {

    @Autowired
    private ValFxValuztionInfo002Mapper valFxValuztionInfo002Mapper;
    
    @Autowired
    private MstValFxMapper mstValFxMapper;
    
    /**
     * 交易编号获取
     * @param params 
     *
     * @return 返回的数据
     */
    
    public List<SuggestDto> getFinprodId2(Map<String, Object> params) throws Exception{
    	 return valFxValuztionInfo002Mapper.getFinprodId2(params);
	
    }

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ValFxValuztionInfo002Dto entity, OperationType type) {

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
    public ValFxValuztionInfo002Dto findByPrimaryKey(Map<String, Object> map) {
        return valFxValuztionInfo002Mapper.selectByPrimaryKey(map);
    }

    /**
     * 新建 利率互换估值信息
     *
     * @param dto
     */
    public void insertSelective(ValFxValuztionInfo002Dto dto) {
        MstValFx mstValIrs = new MstValFx();

        BeanUtils.copyProperties(dto, mstValIrs);
        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));

        mstValFxMapper.insertSelective(mstValIrs);
    }

    /**
     * 修改 利率互换估值信息
     *
     * @param dto
     */
    public void updateByPrimaryKeySelective(ValFxValuztionInfo002Dto dto) {
        MstValFx mstValIrs = new MstValFx();

        BeanUtils.copyProperties(dto, mstValIrs);
        mstValIrs.setValDate(DateUtil.parse(dto.getValDate()));

        mstValFxMapper.updateByPrimaryKeySelective(mstValIrs);
    }

    /**
     * 删除 利率互换估值信息
     *
     * @param dto
     */
    public void deleteByPrimaryKey(ValFxValuztionInfo002Dto dto) {
        MstValFxKey key = new MstValFxKey();

        BeanUtils.copyProperties(dto, key);
        key.setValDate(DateUtil.parse(dto.getValDate()));

        mstValFxMapper.deleteByPrimaryKey(key);
    }

}
