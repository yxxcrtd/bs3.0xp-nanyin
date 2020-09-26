
package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstCustomerInfoAddMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoMapper;
import com.joyintech.tams.common.base.dao.PaRskCustomerRatingMapper;
import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.common.base.entity.PaRskCustomerRating;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dao.SvrMstMarketInstitutionsMapper;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
/**
 * 
 * 市场机构管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see SvrMstMarketInstitutionsService
 * @since 1.0
 */
@Service
public class SvrMstMarketInstitutionsService extends CommonExportService<SvrMstMarketInstitutionsDto> {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
     * 市场机构mapper注入
     */
    @Autowired
    private SvrMstMarketInstitutionsMapper mstMarketInstitutionsMapper;
    /**
     * 客户信息附属表
     */
    @Autowired
    private MstCustomerInfoAddMapper mstCustomerInfoAddMapper;
    /**
     * 客户信息表
     */
    @Autowired
    private MstCustomerInfoMapper mstCustomerInfoMapper;
    /**
     * 客户评级表
     */
    @Autowired
    private PaRskCustomerRatingMapper paRskCustomerRatingMapper;
    /**
     * wind_公司简介
     */
//    @Autowired
//    private InterfCompintroductionMapper interfCompintroductionMapper;
    
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SuggestDto> getCustomerId(Map<String, Object> map) {
    	return mstMarketInstitutionsMapper.getCustomerId(map);
    }
     
     /**
      * 客户名称联想文本框service层实现
       *
       * @param map Map<String, Object> 检索参数
       * @return SuggestDto
       */
    public List<SuggestDto> getCustomerName(Map<String, Object> map) {
        return mstMarketInstitutionsMapper.getCustomerName(map);
    }
    
    /**
     * 使用给定的检索分页数据。返回类型是Service的泛型。
     *  市场机构代码
     *  市场机构名称
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<SvrMstMarketInstitutionsDto> selectForPage(Map<String, Object> pageParam){
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        	Page<SvrMstMarketInstitutionsDto> page = queryForPage(pagenum,legth,()->mstMarketInstitutionsMapper.select(pageParam));
        	return page;
        } else {
            Page<SvrMstMarketInstitutionsDto> page = queryForPage(SvrMstMarketInstitutionsMapper.class,"select",pageParam);
            return page;
        }
    }
    
    /**
     * selectPaRskCustomerPage
     * 根据客户代码查询市场机构详细信息
     * <功能详细描述>
     * @param map map
     * @return MstMarketInstitutionsDto
     * @see
     * @since 1.0
     */
    public SvrMstMarketInstitutionsDto selectDetail(Map<String, Object> map){
    	return mstMarketInstitutionsMapper.selectDetail(map);
    }
    
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto MstMarketInstitutionsDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(SvrMstMarketInstitutionsDto dto){
    	return mstMarketInstitutionsMapper.checkCodeIsUse(dto);
    }
    
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<MstMarketInstitutionsDto> 
     * @return int
     * @see
     * @since 1.0
     */
    public List<SvrMstMarketInstitutionsDto> checkAllCodeIsUse(List<SvrMstMarketInstitutionsDto> item){
    	return mstMarketInstitutionsMapper.checkAllCodeIsUse(item);
    }
    
    /**
     * 
     * 通过客户代码删除客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return int
     * @see
     * @since 1.0
     */
    public int deletePaRskCustomerRatingByPrimaryKey(String customerId){
    	return mstMarketInstitutionsMapper.deletePaRskCustomerRatingByPrimaryKey(customerId);
    }
    
    /**
     * 
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/report/submitmaintenance/mstmarketinstitutions/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SvrMstMarketInstitutionsDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 
     * 实现docomplement方法
     */
    public void doComplete(SvrMstMarketInstitutionsDto entity) {
    	//客户信息
        MstCustomerInfo mstCustomerInfo = new MstCustomerInfo();
        //客户评级
        //PaRskCustomerRating paRskCustomerRating = new PaRskCustomerRating();
        //客户信息附属表
        //MstCustomerInfoAdd mstCustomerInfoAdd = new MstCustomerInfoAdd();
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            //客户信息附属表删除数据
            mstCustomerInfoAddMapper.deleteByPrimaryKey(entity.getCustomerId());
            //客户信息表删除数据
            mstCustomerInfoMapper.deleteByPrimaryKey(entity.getCustomerId());
            //客户评级表删除数据
            mstMarketInstitutionsMapper.deletePaRskCustomerRatingByPrimaryKey(entity.getCustomerId());
            
        }
        else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            //BeanUtil.copyPropertiesIgnoreNull(mstCustomerInfo, entity);
            //客户信息附属表插入数据
        	entity.setCustomerId(IDGenerator.generate(32));
            mstCustomerInfoAddMapper.insert(entity);
            //客户信息表插入数据
            mstCustomerInfo.setCustomerId(entity.getCustomerId());//客户代码
            mstCustomerInfo.setCustomerName(entity.getCustomerName());//客户名称
            mstCustomerInfo.setCustomerType(DictCodeList.客户类型_法人.getValue());
            mstCustomerInfoMapper.insert(mstCustomerInfo);
            //客户评级表插入数据
            List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = new ArrayList<SvrPaRskCustomerRatingDto>();
            paRskCustomerRatingList = entity.getPaRskCustomer();
            for(PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList){
                paRskCustomerRating.setCustomerId(entity.getCustomerId());
                paRskCustomerRatingMapper.insert(paRskCustomerRating);
            } 
        }
        else{
            mstCustomerInfoAddMapper.updateByPrimaryKey(entity);
            //客户信息表更新数据
            mstCustomerInfo.setCustomerId(entity.getCustomerId());//客户代码
            mstCustomerInfo.setCustomerName(entity.getCustomerName());//客户名称
            mstCustomerInfo.setCustomerType(DictCodeList.客户类型_法人.getValue());
            // add by wangcheng 20190401 SHBS-1646 
            if(entity.getCustomerId()!=null){
            	MstCustomerInfo mstCustomerInfo2 = mstCustomerInfoMapper.selectByPrimaryKey(entity.getCustomerId());
            	mstCustomerInfo.setIsIssuer(mstCustomerInfo2.getIsIssuer());
            	mstCustomerInfo.setIsAssteManager(mstCustomerInfo2.getIsAssteManager());
            	mstCustomerInfo.setIsTruster(mstCustomerInfo2.getIsTruster());
            	mstCustomerInfo.setIsSaler(mstCustomerInfo2.getIsSaler());
            	mstCustomerInfo.setIsFinancier(mstCustomerInfo2.getIsFinancier());
            	mstCustomerInfo.setIsGuarantee(mstCustomerInfo2.getIsGuarantee());
            	mstCustomerInfo.setIsRatingAgencies(mstCustomerInfo2.getIsRatingAgencies());
            	mstCustomerInfo.setIsPledgor(mstCustomerInfo2.getIsPledgor());
            	mstCustomerInfo.setIsDepositBank(mstCustomerInfo2.getIsDepositBank());
            	mstCustomerInfo.setIsInvestAdviser(mstCustomerInfo2.getIsInvestAdviser());
            }
            
            mstCustomerInfoMapper.updateByPrimaryKey(mstCustomerInfo);
            //客户评级表数据更新 先删除然后插入
            //客户评级表删除数据
            mstMarketInstitutionsMapper.deletePaRskCustomerRatingByPrimaryKey(entity.getCustomerId());
            //客户评级表插入数据
            List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = entity.getPaRskCustomer();
            if(paRskCustomerRatingList!=null){
            	for(PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList){
                    paRskCustomerRating.setCustomerId(entity.getCustomerId());
                    paRskCustomerRatingMapper.insert(paRskCustomerRating);
                }
            }
        }  
    }
    /**
     * 
     * 省市县联动
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional
    public List<SelectOptionDto> getAddressSelectOption(Map<String,Object> param) {
    	return mstMarketInstitutionsMapper.getAddressSelectOption(param);
    }
    
    /**
     * 
     * 查询所有的市场机构代码、名称
     * <功能详细描述>
     * @return List<MstMarketInstitutionsDto>
     * @see
     * @since 1.0
     */
    public List<SvrMstMarketInstitutionsDto> customerNameCheck(){
    	return  mstMarketInstitutionsMapper.customerNameCheck();
    }
    
    /**
     * 
     * 根据becif查询CMS_机构信息
     * <功能详细描述>
     * @param becif String
     * @return InterfCmsInstitution
     * @see
     * @since 1.0
     */
    public InterfCmsInstitution selectInterfCmsInstitution(String becif){
    	return mstMarketInstitutionsMapper.selectInterfCmsInstitution(becif);
    }
    
    /**
     * 
     * 根据wind编码查询wind_公司简介
     * <功能详细描述>
     * @param objectId String
     * @return InterfCompintroduction
     * @see
     * @since 1.0
     */
//    TODO
//    public InterfCompintroduction selectInterfCompintroduction(String objectId){
//        return interfCompintroductionMapper.selectByPrimaryKey(objectId);
//    }

    
    /**
     * 
     * 通过客户代码查询客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return List<PaRskCustomerRating>
     * @see
     * @since 1.0
     */
    public List<SvrPaRskCustomerRatingDto> selectPaRskCustomerRatingByPrimaryKey(String customerId){
        return mstMarketInstitutionsMapper.selectPaRskCustomerRatingByPrimaryKey(customerId);
        
    }

    public List<SvrMstMarketInstitutionsDto> selectForExport(Map<String, Object> param) {
		List<SvrMstMarketInstitutionsDto> investList = null;
        try {
        	investList = (List<SvrMstMarketInstitutionsDto>)param.get("investList");
            return mstMarketInstitutionsMapper.selectForExport(investList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<SvrMstMarketInstitutionsDto>();
	}
}
