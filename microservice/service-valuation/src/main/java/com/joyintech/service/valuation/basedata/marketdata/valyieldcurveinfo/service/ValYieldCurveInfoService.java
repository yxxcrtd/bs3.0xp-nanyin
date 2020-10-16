/*
 * 文件名：ValYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstYieldCurveInfoMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.dao.ValYieldCurveInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ValYieldCurveInfoService
 * @since 1.0
 */
@Service("backValYieldCurveInfoService")
@Transactional(rollbackFor = Exception.class)
public class ValYieldCurveInfoService extends BaseService<ValYieldCurveInfoDto>{

   /**
    * 
    */
    @Autowired
    private ValYieldCurveInfo001Mapper valYieldCurveInfo001Mapper;
   /**
    * 
    */
    @Autowired
    private  MstYieldCurveInfoMapper mstYieldCurveInfoMapper;
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<ValYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValYieldCurveInfoDto> selectForPage(int page,int length,Map<String, Object> pageParam) throws Exception {
    	pageParam.put("curveIntType", DictCode.曲线利率类型.getValue());
    	return super.queryForPage(page,length,()-> valYieldCurveInfo001Mapper.select(pageParam));
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<ValYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    public List<ValYieldCurveInfoDto> selectAllList(Map<String, Object> pageParam) {
        return valYieldCurveInfo001Mapper.select(pageParam);
    }
    /**
     * 
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @param type 类型
     * @see
     * @since 1.0
     */
    public void doComplete(ValYieldCurveInfoDto entity) {
        //新建或删除时，收益率除100
        if(StringUtil.isNotEmpty(entity.getYield())){
            entity.setYield(entity.getYield().divide(new BigDecimal(100+"")));
        }
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstYieldCurveInfoMapper.deleteByPrimaryKey(entity);
        }
        else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            mstYieldCurveInfoMapper.insert(entity);
        }
        else{
            mstYieldCurveInfoMapper.updateByPrimaryKeySelective(entity);
        }
    }
    //add by lishuping 20191008 start
    /**
     *曲线名称的联想下拉框事件
     * @param
     * @return
     */
    public Page<SelectOptionDto> getCurveName(int page,int length,Map<String, Object> map) {
        map.put("CURVE_CLASS", DictCodeListExt2.曲线类别_收益率曲线.getValue());
        return super.queryForPageForSelectReturn(page,length,()->valYieldCurveInfo001Mapper.getCurveName(map));
    }

    /**
     * 根据曲线名称查询n、k
     * @param param
     * @return
     */
    public ValYieldCurveInfoDto selectNK(ValYieldCurveInfoDto param) {
        List<ValYieldCurveInfoDto> list = valYieldCurveInfo001Mapper.selectNK(param);
        if (StringUtil.isEmptyList(list) || list.size() > 1 || list.get(0) == null) {
            return new ValYieldCurveInfoDto();
        }
        return list.get(0);
    }
    //add by lishuping 20191008 end
}
