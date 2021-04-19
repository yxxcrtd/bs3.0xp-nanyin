/*
 * 文件名：MstNetAssetQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dao.ComNetAssetQuotationInfo001Mapper;

/**
 * 计划类（净值型）资产估值行情信息查询service
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ComNetAssetQuotationInfo001Service
 * @since 1.0
 */

@Service("serviceComNetAssetQuotationInfo001Service")
@Transactional
public class ComNetAssetQuotationInfo001Service extends BaseService<ComNetAssetQuotationInfo001Dto>
    {
    
    /**
     * 计划类（净值型）资产估值行情信息mapper
     */
    @Autowired
    private ComNetAssetQuotationInfo001Mapper comNetAssetQuotationInfo001Mapper;
    
    /**
     * 场外金融产品估值Mapper
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;
    /**
     * 平台日查询
     */
    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

//    /**
//     * 投管接口共通组件
//     */
//    @Autowired
//    private InvestInterfCommService investInterfCommService;
    
    /**
     * 查询按钮点击事件
     * 通过条件查询数据
     * @param pageParam 页面参数
     * @return  page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ComNetAssetQuotationInfo001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        pageParam.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        pageParam.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return super.queryForPage(page,length,()-> comNetAssetQuotationInfo001Mapper.select(pageParam));
    }   
    
    /**
     * 资产名称联想文本框
     * 可以通过输入的资产名称模糊查询
     * @param map 查询参数
     * @return  资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetName(Map<String, Object> map) {
        return comNetAssetQuotationInfo001Mapper.getAssetName(map);
    }
    
    /**
     * 查询界面资产代码联想文本框
     * 可以通过输入的资产代码模糊查询
     * @param map 查询参数
     * @return  资产名称和资产代码列表
     * @see
     * @since 1.0
     */    
    public List<SuggestDto> getAssetCode(Map<String, Object> map) { 
        return comNetAssetQuotationInfo001Mapper.getAssetCode(map);
    }
    
    /**
     * 新建界面资产代码联想文本框
     * 可以通过输入的资产代码模糊查询
     * @param map 查询参数
     * @return  资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        return comNetAssetQuotationInfo001Mapper.getAssetCodeList(map);
    }
    
    /**
     * 通过资产代码查询数据
     * 完成新建、编辑界面和双击查看界面初始化
     * @param param 资产代码
     * @return  dto
     * @see
     * @since 1.0
     */
    public ComNetAssetQuotationInfo001Dto selectMstNetAsset(Map<String, Object> param) {
        return comNetAssetQuotationInfo001Mapper.selectByFinProdId(param);
    }


    /**
     * 删除操作
     */
    public int delete(ComNetAssetQuotationInfo001Dto entity) {
    	entity.setFinprodId(entity.getFinProdMarketId());
    	entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return finProductValuationMapper.deleteByPrimaryKey(entity);
    }
    
    /**
     * 插入操作
     */
    public int insert(ComNetAssetQuotationInfo001Dto entity) {
        Date sysDate= commonPlatformDateService.getSysDate();
        entity.setPublishDate(sysDate);
     // 一体化对应etliu start
//        entity.setInputType(BussinessDictCodeList.录入方式_手工维护.getValue());
        entity.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
     // 一体化对应etliu end
        entity.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        //投管接口调用
//        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
//        investInterfCommDto.setPageId("MstNetAssetQuotationInfo002");
//        investInterfCommDto.setPortfolioId(entity.getFinProdMarketId());
//        investInterfCommDto.setFinProductValuation(entity);
//        investInterfCommService.doInvestInterf(investInterfCommDto);
        return finProductValuationMapper.insertSelective(entity);
    }
    
    /**
     * 更新操作
     */
    public int update(ComNetAssetQuotationInfo001Dto entity) {
    	entity.setFinprodId(entity.getFinProdMarketId());
        return comNetAssetQuotationInfo001Mapper.update(entity);
    }
//    /**
//     * 导出
//     */
//    @Override
//    protected void buildExportParam(ExportParam exportParam) {
//        Map<String, Object> param = exportParam.getSelectParam();
//        param.put("PROFITTYPE_VALUE_01", BussinessDictCodeList.收益类型_净值.getValue());
//        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
//        exportParam.config(
//            "/com/joyintech/basicdata/mstnetassetquotationinfo/conf/export_conf.xml").select(
//                () -> mstnetassetquotationinfo001mapper.select(param)).dataSource(DataSourceType.MASERT);
//    }

    /**
     * 通过资产代码和估值日期查询数据
     * 查询插入数据的主键是否与数据库中的相同
     * @param finprodId 金融产品代码
     * @param valDate 估值日期
     * @return dto
     * @see
     * @since 1.0
     *//*
    public MstNetAssetQuotationInfo001Dto selectByIdAndDate(String finprodId, String valDate) {
        return mstnetassetquotationinfo001mapper.selectByIdAndDate(finprodId,valDate);
    }*/

    /**
     * 通过资产代码查询资产名称和管理人
     * 完成资产代码画面联动
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    public ComNetAssetQuotationInfo001Dto selectNameAndFundManager(Map<String, Object> map) {
        return comNetAssetQuotationInfo001Mapper.selectNameAndFundManager(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @param valDate
     * @return
     * @see
     * @since 1.0
     */
    /**
     *
     * @param map
     * @return
     */
    public ComNetAssetQuotationInfo001Dto selectByIdAndDate(Map<String,Object> map) {
    	map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return comNetAssetQuotationInfo001Mapper.selectByIdAndDate(map);
    }
}
