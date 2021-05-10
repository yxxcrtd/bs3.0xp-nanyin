/*
 * 文件名：EtrInvestBondInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dao.TrdAssetLoss001Mapper;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产减值管理微服务端service 〈功能详细描述〉
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLossService
 * @since 1.0
 */
@Service("backTrdAssetLossService")
@Transactional(rollbackFor = Exception.class)
public class TrdAssetLossService extends BaseService<TrdAssetLoss001Dto> {

    @Autowired
    private TrdAssetLoss001Mapper trdAssetLoss001Mapper;
    /**
     * 资产减值管理分页查询 <功能详细描述>
     *
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<TrdAssetLoss001Dto> selectForPage(int page, int length,
                                                  Map<String, Object> param)
        throws Exception {
        param.put("status_init_01",DictCodeListExt1.交易状态_初始.getValue());
        param.put("lossTestType_01", DictCodeListExt3.减值测算方式_外部评估.getValue());

        Page<TrdAssetLoss001Dto> pageDto = queryForPage(page, length,
            () -> trdAssetLoss001Mapper.selectForPage(param));
        return pageDto;
    }

    /**
     * 资产名称联想下拉框框 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length,Map<String, Object> param) {
        param.put("PROFITTYPE_VALUE_02", DictCodeListExt2.收益类型_预期收益.getValue());
        param.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("TYPE_TOUGUAN_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return this.queryForPageForSelectReturn(page, length,()-> trdAssetLoss001Mapper.getfinprodIdForAdd(param));
    }

    public List<TrdAssetLoss001Dto> getAssetInfo(Map<String, Object> param) {
        param.put("EXEC_DATE_04", DictCodeList.含权债摊销日_到期日.getValue());
        param.put("PROFITTYPE_VALUE_02", DictCodeListExt2.收益类型_预期收益.getValue());
        param.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("TYPE_TOUGUAN_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        List<TrdAssetLoss001Dto> list = trdAssetLoss001Mapper.getAssetInfo(param);
        return list;
    }

    /**
     * 工作流完成方法 <功能详细描述>
     *
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(TrdAssetLoss001Dto dto, String operationType)
            throws IllegalAccessException, InvocationTargetException {

        TrdAssetLoss001Dto entity = new TrdAssetLoss001Dto();
        if (StringUtil.isNotEmpty(dto.getPdValue())){
            dto.setPdValue(dto.getPdValue().divide(new BigDecimal(100)));
        }
        if (StringUtil.isNotEmpty(dto.getLgdValue())){
            dto.setLgdValue(dto.getLgdValue().divide(new BigDecimal(100)));
        }
        if (StringUtil.isNotEmpty(dto.getLossRatio())){
            dto.setLossRatio(dto.getLossRatio().divide(new BigDecimal(100)));
        }
        if (StringUtil.isNotEmpty(dto.getEclVaule())){
            dto.setEclVaule(dto.getEclVaule().divide(new BigDecimal(100)));
        }
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setStatus(DictCodeListExt1.交易状态_初始.getValue());
            if (!StringUtil.isNotEmpty(dto.getEclVaule())){
                dto.setEclVaule(dto.getLossRatio());
            }
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            trdAssetLoss001Mapper.insertSelective(entity);
        }
        if (StringUtil.equals(operationType, "REVOKE")) {
            if (StringUtil.isNotEmpty(dto.getTradeId())){
                dto.setTradeId(dto.getTradeId().replace("R",""));
            }
            dto.setStatus(DictCodeListExt1.交易状态_撤销.getValue());
            BeanUtil.copyProperties(entity, dto);
            trdAssetLoss001Mapper.updateByTradeIdSelective(entity);
        }
    }

    public List<TrdAssetLoss001Dto> selectByKey(Map<String, Object> param) {
        List<TrdAssetLoss001Dto> list = trdAssetLoss001Mapper.selectByKey(param);
        return list;
    }

    public Page<TrdAssetLoss001Dto> selectForPagePreView(int page, int length, Map<String, Object> param) {
        Page<TrdAssetLoss001Dto> pageDto = queryForPage(page, length,
                () -> trdAssetLoss001Mapper.selectForPagePreView(param));
        return pageDto;
    }

    public List<TrdAssetLoss001Dto> selectByFinprodId(Map<String, Object> param) {
        List<TrdAssetLoss001Dto> list = trdAssetLoss001Mapper.selectByFinprodId(param);
        return list;
    }

    public List<TrdAssetLoss001Dto> selectForExport(Map<String, Object> param) {
        param.put("status_init_01",DictCodeListExt1.交易状态_初始.getValue());
        param.put("lossTestType_01", DictCodeListExt3.减值测算方式_外部评估.getValue());
        return trdAssetLoss001Mapper.selectForPage(param);
    }

    public List<TrdAssetLoss001Dto> selectByFinprodIdAndValDate(Map<String, Object> param) {
        param.put("status_init_01",DictCodeListExt1.交易状态_初始.getValue());
        return trdAssetLoss001Mapper.selectByFinprodIdAndValDate(param);
    }
    public List<TrdAssetLoss001Dto> selectPreByFinprodIdAndLossDate(Map<String, Object> param) {
        return trdAssetLoss001Mapper.selectPreByFinprodIdAndLossDate(param);
    }
    public List<TrdAssetLoss001Dto> selectNextByFinprodIdAndLossDate(Map<String, Object> param) {
        return trdAssetLoss001Mapper.selectNextByFinprodIdAndLossDate(param);
    }

    public List<TrdAssetLoss001Dto> getAssetExerciseDate(Map<String, Object> param) {
        Map<String, Object> map = new HashMap<>();
        map.put("valDate", DateUtil.format(DateUtil.parse(param.get("valDate").toString())));
        map.put("finprodId", param.get("finprodId"));
        map.put("PROFITTYPE_VALUE_02", DictCodeListExt2.收益类型_预期收益.getValue());
        map.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        map.put("TYPE_TOUGUAN_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        map.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        map.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        TrdAssetLoss001Dto dto = trdAssetLoss001Mapper.selectAssetExerciseDate(map);
        return Arrays.asList(dto);
    }

    public List<TrdAssetLoss001Dto> getBondAssetInfo(Map<String, Object> param) {
        param.put("EXEC_DATE_04", DictCodeList.含权债摊销日_到期日.getValue());
        param.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        List<TrdAssetLoss001Dto> list = trdAssetLoss001Mapper.getBondAssetInfo(param);
        return list;
    }

    public List<TrdAssetLoss001Dto> selectRepeatByFinprodIdAndValDate(Map<String, Object> param) {
        param.put("status_init_01",DictCodeListExt1.交易状态_初始.getValue());
        return trdAssetLoss001Mapper.selectRepeatByFinprodIdAndValDate(param);
    }
}
