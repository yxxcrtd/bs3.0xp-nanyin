/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.yieldcurveinfo.service;

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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.yieldcurveinfo.dao.ComYieldCurveInfo001Mapper;
import com.joyintech.service.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComYieldCurveInfoService
 * @since 1.0
 */
@Service("serviceComYieldCurveInfoService")
@Transactional(rollbackFor = Exception.class)
public class ComYieldCurveInfoService extends BaseService<ComYieldCurveInfoDto>{

   /**
    * 
    */
    @Autowired
    private ComYieldCurveInfo001Mapper comYieldCurveInfo001Mapper;
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
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ComYieldCurveInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
    	pageParam.put("curveIntType", DictCode.曲线利率类型.getValue());
    	return super.queryForPage(page,length,()-> comYieldCurveInfo001Mapper.select(pageParam));
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    public List<ComYieldCurveInfoDto> selectAllList(Map<String, Object> pageParam) {
        return comYieldCurveInfo001Mapper.select(pageParam);
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
    public void doComplete(ComYieldCurveInfoDto entity) {
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
        return super.queryForPageForSelectReturn(page,length,()-> comYieldCurveInfo001Mapper.getCurveName(map));
    }

    /**
     * 根据曲线名称查询n、k
     * @param param
     * @return
     */
    public ComYieldCurveInfoDto selectNK(ComYieldCurveInfoDto param) {
        List<ComYieldCurveInfoDto> list = comYieldCurveInfo001Mapper.selectNK(param);
        if (StringUtil.isEmptyList(list) || list.size() > 1 || list.get(0) == null) {
            return new ComYieldCurveInfoDto();
        }
        return list.get(0);
    }
    //add by lishuping 20191008 end


    /**
     * 保存导入数据
     *
     * @param list 需保存的数据
     * @see
     * @since 1.0
     */
    public void mergeInfoList(List<ComYieldCurveInfoDto> list){
        int batchCount = 50;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();) {
            if (batchLastIndex > list.size() - 1) {
                batchLastIndex = list.size();
                comYieldCurveInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                break;// 数据插入完毕,退出循环
            }
            else {
            	comYieldCurveInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + batchCount;
            }
        }
    }
}
