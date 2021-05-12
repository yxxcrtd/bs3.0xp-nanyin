/*
 * 文件名：MstCoinFundQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dao.ComCoinFundQuotationInfoMapper;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 基金类行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月3日
 * @see ComCoinFundQuotationInfoService
 * @since 1.0
 */
@Service("serviceComCoinFundQuotationInfoService")
public class ComCoinFundQuotationInfoService extends BaseService<ComCoinFundQuotationInfo001Dto>{
    /**
     * 金融产品mapper
     */
    @Autowired
    private ComCoinFundQuotationInfoMapper comCoinFundQuotationInfoMapper;

    /**
     * 金融产品mapper（系统自动生成）
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;
    /**
     * 标的金融产品Mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 分页查询
     * @param length 长度
     * @param page 页数
     * 
     * @param pageParam 查询参数
     * @return page对象
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    public Page<ComCoinFundQuotationInfo001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_基金.getValue());
        pageParam.put("yes", DictCodeList.是否_是.getValue());
        return super.queryForPage(page,length,() -> comCoinFundQuotationInfoMapper.queryForPage(pageParam));
    }
    /**
     * 获取基金行情信息
     * @param pageParam 查询参数主键
     * @return 基金类行情信息列表
     * @see
     * @since 1.0
     */
    public List<ComCoinFundQuotationInfo001Dto> selectPageInfo(Map<String, Object> pageParam){
        return comCoinFundQuotationInfoMapper.selectPageInfo(pageParam);
    }
    /**
     * 联想文本框service层实现
     * 
     * @param map 检索参数
     * @return SuggestDto
     */
    public List<SelectOptionDto> selectForCode(Map<String, Object> map) {
        map.put("finprodType2", DictCodeListExt1.金融产品类型_基金.getValue());
        return comCoinFundQuotationInfoMapper.queryCode(map);
        // return super.queryForSuggest(mstCoinFundQuotationInfo001Mapper::queryCode, param, null);
    }

    /**
     * data-url 方式的下拉框
     * 
     * @param map 检索参数
     * @return SuggestDto
     */
    public List<SelectOptionDto> getManagement(Map<String,Object> map) {
        return comCoinFundQuotationInfoMapper.queryManagement(map);
    }

    /**
     * 联想下拉框基金代码
     * @param map 查询参数
     * @return List<FinprodSeleOptionDto>金融产品实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryFinprod(Map<String, Object> map) {
        return comCoinFundQuotationInfoMapper.queryFinprod(map);
    }

    /**
     * 以基金代码获取金融产品信息集合
     * 
     * @param finprodId
     * @return List<MstCoinFundQuotationInfo001Dto>金融产品实体类集合
     * @see
     * @since 1.0
     *//*
       * public List<MstCoinFundQuotationInfo001Dto> getByFunCode(String finprodId){ return
       * mstCoinFundQuotationInfo001Mapper.getByfinprodId(finprodId); }
       */
    /**
     * 以基金代码获取基金类型和基金管理人名称
     * 
     * @param finprodId 金融产品代码
     * @return MstCoinFundQuotationInfo001Dto金融产品实体类
     * @see
     * @since 1.0
     */
    public ComCoinFundQuotationInfo001Dto getManageAndType(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodType2", DictCodeListExt1.金融产品类型_基金.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("finprodId", finprodId);
        String finprodType = comCoinFundQuotationInfoMapper.selectByfinprodId(map);
        ComCoinFundQuotationInfo001Dto dto = comCoinFundQuotationInfoMapper.selectManagement(
            map);
        if (dto != null) {
            dto.setFinprodType2(finprodType);
        }
        else {
            dto = new ComCoinFundQuotationInfo001Dto();
            dto.setFinprodType2(finprodType);
        }
        return dto;
    }

    /**
     * 实现docomplement方法
     */
    public void doComplete(ComCoinFundQuotationInfo001Dto entity) {
        String type = entity.getType();
        if (StringUtil.equals(type, "DELETE")) {
            // 调用父类的主键删除方法
        	entity.setFinprodId(entity.getFinprodMarketId());
        	entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
            finProductValuationMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(type, "NEW")) {
            entity.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
            entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
         // 一体化对应etliu start
//            entity.setInputType(DictCodeListExt1.录入方式_手工维护.getValue());
            entity.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
         // 一体化对应etliu start

            //七日年化收益率入库需除100 add by gejiaao 2019/8/20 start
            if (null != entity.getDay7YearYield()){
                entity.setDay7YearYield(entity.getDay7YearYield().divide(BigDecimal.valueOf(100)));
            }
            //七日年化收益率入库需除100 add by gejiaao 2019/8/20 end
            finProductValuationMapper.insert(entity);
        }
        else {
            entity.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
            entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
            //七日年化收益率入库需除100 add by gejiaao 2019/8/20 start
            if (null != entity.getDay7YearYield()){
                entity.setDay7YearYield(entity.getDay7YearYield().divide(BigDecimal.valueOf(100)));
            }
            //七日年化收益率入库需除100 add by gejiaao 2019/8/20 end
            finProductValuationMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * 通过主键进行查找
     * <功能详细描述>
     * @param map 参数
     * @return stirng
     * @see
     * @since 1.0
     */
    public String selectByIdAndValDate(Map<String, Object> map) {
        /** 改为用估值类型进行数据过滤 modify by yanya on 20210209 **/
    	//map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_基金.getValue());
        return comCoinFundQuotationInfoMapper.selectByIdAndValDate(map);
    }

    public String getIplatDate(){
//        Date date = iPlatformDate != null ? iPlatformDate.getSysDate():DateUtil.now();
        Date date = DateUtil.now();
        String dateStr = "";
        if(date != null){
            dateStr = DateUtil.format(date);
        }
        return dateStr;
    }
}
