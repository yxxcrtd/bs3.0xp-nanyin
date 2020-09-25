/*
 * 文件名：ValBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.assets.bond.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.assets.bond.dao.ValBondTargetAssetMapper;
import com.joyintech.service.valuation.assets.bond.dto.ValBondTargetAssetDto;
import com.joyintech.service.valuation.component.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.service.valuation.component.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.service.valuation.component.common.dto.ValMstCustomerNatureDto;
//import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
//import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
//import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
//import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
//import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetService
 * @since 1.0
 */
@Service
public class ValBondTargetAssetService extends BaseService<ValBondTargetAssetDto>{
    /**
     * 基础--标的类金融产品
     */
//    @Autowired(required = false)
//    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    /**
     * 基础 --附件
     */
//    @Autowired(required = false)
//    private MstFileCommonService mstFileCommonService;
    
    @Autowired
    private ValBondTargetAssetMapper finBondTargetAssetMapper;
    
    @Autowired(required=false)
    private PaFinProductMapper paFinProductMapper;

    @Autowired(required = false)
    private FinProductConvertibleMapper finProductConvertibleMapper;
    /* add by zhangpuyu 2019/10/24 start */
    /**
     * 投后信息
     */
    @Autowired
    private FinPostInvestInfoMapper finPostInvestInfoMapper;
    /* add by zhangpuyu 2019/10/24 start */

    @Autowired
    private FinProductMapper finProductMapper;
    
//    /**
//     * 分页
//     * @param page
//     * @param length
//     * @param pageParam
//     * @return
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    public Page<FinBondTargetAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
//        throws Exception {
//        pageParam.put("MAIN_GRADE_TYPE_01", DictCodeList.评级类型_主体评级.getValue());
//        pageParam.put("MAIN_GRADE_TYPE_02", DictCodeList.评级类型_债项评级.getValue());
//        Page<FinBondTargetAssetDto> pageDto = super.queryForPage(page, length, () -> finBondTargetAssetMapper.queryForPage(pageParam));
//        List<FinBondTargetAssetDto> pageList = pageDto.getRoot();
//        pageDto.setRoot(pageList);
//        return pageDto;
//    }
    
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(ValBondTargetAssetDto entity) {
        //标的金融产品信息
    	//根据投管保存处理（由于涉及共通组件太多，待处理）
//        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        //add by zhangnian 20191014 end
      //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct,entity);
        //将数据插入到标的类金融产品扩展表
        paFinProductMapper.insertSelective(paFinProduct);
        //转债信息
        if(StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            finProductConvertibleMapper.insert(entity.getFinProductConvertible());
        }
        /* add by zhangpuyu 2019/10/24 start */
        //投后信息
        if(null!=entity.getFinprodId()){
            FinPostInvestInfo finPostInvestInfo=new FinPostInvestInfo();
        	finPostInvestInfo.setFinprodId(entity.getFinprodId());
        	finPostInvestInfo.setPostOrg(entity.getPostOrg());
            finPostInvestInfoMapper.insert(finPostInvestInfo);
        }
        /* add by zhangpuyu 2019/10/24 start */
    }
    
    /**
     * 修改
     * @param entity
     * @see
     * @since 1.0
     */
    public void update(ValBondTargetAssetDto entity){
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //根据投管保存处理（由于涉及共通组件太多，待处理）
//        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //add by zhangnian 20191014 end
      //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct,entity);
        //修改标的类金融产品扩展表
        paFinProductMapper.updateByPrimaryKey(paFinProduct);
        //转债信息
        if(StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            finProductConvertibleMapper.deleteByPrimaryKey(entity.getFinprodId());
            finProductConvertibleMapper.insert(entity.getFinProductConvertible());
        }
        /* add by zhangpuyu 2019/10/24 start */
        //投后信息
        if(null!=entity.getFinprodId()){
        	finPostInvestInfoMapper.deleteByPrimaryKey(entity.getFinprodId());
        	FinPostInvestInfo finPostInvestInfo=new FinPostInvestInfo();
        	finPostInvestInfo.setFinprodId(entity.getFinprodId());
        	finPostInvestInfo.setPostOrg(entity.getPostOrg());
        	finPostInvestInfoMapper.insert(finPostInvestInfo);
        }
        /* add by zhangpuyu 2019/10/24 start */
    }
    
    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(ValBondTargetAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        //根据投管保存处理（由于涉及共通组件太多，待处理）
//        baseFinProductBaseInfoService.delete(finprodIdList);
        // added by wangzhiang BUG修复，债券删除时漏删一张表 2019-08-24 start
        paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
        // added by wangzhiang BUG修复，债券删除时漏删一张表 2019-08-24 end
        //附件信息
//        mstFileCommonService.updateBusinessId(entity.getMstFileInputParameter());
        //转债信息
        if(StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            finProductConvertibleMapper.deleteByPrimaryKey(entity.getFinprodId());
        }
        /* add by zhangpuyu 2019/10/24 start */
        //投后信息
        if(null!=entity.getFinprodId()){
            FinPostInvestInfo finPostInvestInfo=new FinPostInvestInfo();
            finPostInvestInfoMapper.deleteByPrimaryKey(entity.getFinprodId());
        }
        /* add by zhangpuyu 2019/10/24 start */
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto getBasicInfo(String finprodId) {
        ValBondTargetAssetDto finBondTargetAssetDto = new ValBondTargetAssetDto();
        finBondTargetAssetDto = finBondTargetAssetMapper.getBasicInfo(finprodId, DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        //转债信息
//        if(null!=finBondTargetAssetDto){
//            FinProductConvertible finProductConvertible = finProductConvertibleMapper.selectByPrimaryKey(finBondTargetAssetDto.getFinprodId());
//            if (null != finProductConvertible) {
//                FinProduct finProduct = finProductMapper.selectByPrimaryKey(finProductConvertible.getConFinprodId());
//                if(null!=finProduct){
//                    finBondTargetAssetDto.setConFinprodMarketId(finProduct.getFinprodMarketId());
//                }
//                finBondTargetAssetDto.setFinProductConvertible(finProductConvertible);
//            }
//        }
        return finBondTargetAssetDto;
    } 

    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * 查询各个组件业务表数据
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public void searchData(ValBondTargetAssetDto entity){
        //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = paFinProductMapper.selectByPrimaryKey(entity.getFinprodId());
        BaseFinProductBaseInfoDto baseDto = new BaseFinProductBaseInfoDto();
        baseDto.setPaFinProduct(paFinProduct);
        List<BaseFinProductBaseInfoDto> baseList = new ArrayList<>();
        baseList.add(baseDto);
        entity.setBaseFinProductBaseInfoList(baseList);
    }
    // 债券新建新增快捷录入功能 add by renping 20191010 end

    /**
     * 市场代码是否重复
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodMarketId(String finprodMarketId){
        return finBondTargetAssetMapper.selectCountByfinprodMarketId(finprodMarketId);
    }
    
    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     * 
     * @author wangguiqi
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length,Map<String, Object> param) {
        //param.put("assestType", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
    	//zhangquan
    	param.put("assestType", "F32");
        return this.queryForPageForSelectReturn(page, length,()->finBondTargetAssetMapper.getfinprodIdForAdd(param));
    }

	public int isHqTrade(String finprodId) {
		return finBondTargetAssetMapper.isHqTrade(finprodId);
	}
	
	
	
	
	/**
     * 获取wind债券信息 <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(String secid) {
        return finBondTargetAssetMapper.selectWindBondAssetBySecId(secid);
    }
	
    /**
     * 获取wind债券行权计划 <一句话功能简述> <功能详细描述>
     * 
     * @param secid 债券id
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecoption> selectWindBondExercise(String secid) {
        return finBondTargetAssetMapper.selectWindBondExercise(secid);
    }
	
    /**
     * 获取wind债券付息计划 <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecschedule> selectWindBondSchedule(String secid) {
        return finBondTargetAssetMapper.selectWindBondSchedule(secid);
    }
    
    /**
     * 获取WIND_浮息债报价表(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String secid) {
        return finBondTargetAssetMapper.selectInterfFloatingcouponsrate(secid);
    }
    /**
     * 获取中国债券基本资料(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfCbonddescription selectInterfCbonddescription(String secid) {
        return finBondTargetAssetMapper.selectInterfCbonddescription(secid);
    }
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MstCalendarInfo> getMstCalendarInfo(){
        return finBondTargetAssetMapper.getMstCalendarInfo();
    }
    
    public String getIssuer(String linkId1, String type) {
        // 入库
        String customerId = finBondTargetAssetMapper.getIssuer(linkId1);
        // 更新属性
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("customerId", customerId);
            ValMstCustomerNatureDto dto = finBondTargetAssetMapper.selectByPrimaryKey(param);
            if (StringUtil.isNotEmpty(dto)) {
                if (StringUtil.equals("IS_ISSUER", type)) {
                    // IS_ISSUER 发行人
                    dto.setIsIssuer(DictCodeList.是否_是.getValue());
                }
                else if (StringUtil.equals("IS_TRUSTER", type)) {
                    // IS_TRUSTER 托管人
                    dto.setIsTruster(DictCodeList.是否_是.getValue());
                }
                else if (StringUtil.equals("IS_ASSTE_MANAGER", type)) {
                    // IS_ASSTE_MANAGER 管理人
                    dto.setIsAssteManager(DictCodeList.是否_是.getValue());
                }
                finBondTargetAssetMapper.updateByPrimaryKey(dto);
            }
        }
        return customerId;
    }
    
    /**
     * 查询资讯_债券评级中主体评级结果
     * 
     * @param secid
     * @param date
     * @return
     */
    public List<InterfPayhsrcsecrating> selectRating(String secid, String date) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("secid", secid);
        map.put("date", date);
        return finBondTargetAssetMapper.selectRating(map);
    }
    /**
     * 查询客户信息附属表list-债券中债
     * 
     * @param customerid
     * @return
     */
    public List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(String customerid) {
        return finBondTargetAssetMapper.selectMstCustomerInfoAdd(customerid);
    }
    /**
     * 查询资讯_债券评级信息表
     * 
     * @param secid
     * @return
     */
    public List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(String secid) {
        return finBondTargetAssetMapper.selectInterfPayhsrcsecrating(secid);
    }
    /**
     * 资产五级分类联动检索
     *
     * @param param 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public FinProductTypeConfig getAssetThreeType(FinProductTypeConfig param) {
        //优先查询wind分类
        param.setConfigType(DictCodeListExt2.配置类型_WIND分类.getValue());
        List<FinProductTypeConfig> list = finBondTargetAssetMapper.getAssetThreeType(param);
        if (StringUtil.isNotEmpty(list) && list.size() == 1){
            return list.get(0);
        }else{
            param.setConfigType(DictCodeListExt2.配置类型_投管分类.getValue());
            list = finBondTargetAssetMapper.getAssetThreeType(param);
            if (StringUtil.isEmptyList(list) || list.size() > 1 || list.get(0) == null) {
                return new FinProductTypeConfig();
            }
            return list.get(0);
        }         
    }
    /**
     * 债券下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBondId(Map<String, Object> params) {        
        List<ValBondTargetAssetDto> resultList = finBondTargetAssetMapper.getBondId(params);
        List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
        for (ValBondTargetAssetDto single : resultList) { // 组装select节点对象
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(single.getFinprodId());
            selectOptionDto.setText(single.getFinprodAbbr());
            selectOptionDtoList.add(selectOptionDto);
        }
        return selectOptionDtoList;
    }
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValBondTargetAssetDto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        String bond = (String)params.get("bond");
        String couponType = (String)params.get("couponType");
        String bondType1 = (String)params.get("bondType1");
        String bondType2 = (String) params.get("bondType2");
        
        String[] bondArr = null;
        if(StringUtil.isNotEmpty(bond)){
            bondArr = bond.split(",");
        }
        String[] couponTypeArr = null;
        if(StringUtil.isNotEmpty(couponType)){
            couponTypeArr = couponType.split(",");
        }
        String[] bondTypeArr1 = null;
        if(StringUtil.isNotEmpty(bondType1)){
            bondTypeArr1 = bondType1.split(",");
        }
        String[] bondTypeArr2 = null;
        if(StringUtil.isNotEmpty(bondType2)) {
        	bondTypeArr2 = bondType2.split(",");
        }
        
        params.put("bondArr",bondArr);
        params.put("couponTypeArr", couponTypeArr);
        params.put("bondTypeArr1", bondTypeArr1);
        params.put("bondTypeArr2", bondTypeArr2);
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        params.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return super.queryForPage(page, length, () -> finBondTargetAssetMapper.queryPageList(params));
    }
    
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getCustomer(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> finBondTargetAssetMapper.getCustomer(param));
    }
    /**
     * 交易日历联想下拉框
     *
     * @param param 参数map
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public Page<SelectOptionDto> queryCalendarInfoList(Map<String, Object> param,int page,int length) {
        return this.queryForPageForSelectReturn(page, length, ()-> finBondTargetAssetMapper.queryCalendarInfoList(param));
    }
}
