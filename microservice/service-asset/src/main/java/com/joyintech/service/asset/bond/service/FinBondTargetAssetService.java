/*
 * 文件名：FinBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.bond.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetEquityInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepOtherInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetEquityInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepOtherDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.bond.dao.FinBondTargetAssetMapper;
import com.joyintech.service.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.service.asset.common.dto.BusiFinBondRatingInfoDto;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.service.asset.common.service.BusiFinBondRatingInfoService;
import com.joyintech.service.asset.common.service.FinInterestFactorService;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepBondAssetInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepBondAssetInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetService
 * @since 1.0
 */
@Service("backFinBondTargetAssetService")
public class FinBondTargetAssetService extends BaseService<FinBondTargetAssetDto>{
    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    /**
     * 基础 --附件
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;
    
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;
    
    @Autowired(required = false)
    private BusiFinBondRatingInfoService busiFinBondRatingInfoService;
    
    @Autowired
    private FinBondTargetAssetMapper finBondTargetAssetMapper;
    
    @Autowired
    private MstBondCreditRatingMapper  mstBondCreditRatingMapper;
    
    /**
     * 基础--中债报送(负债)
     */
    @Autowired(required = false)
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;

    /**
     * 权益类资产中债报送(基础组件)
     */
    @Autowired(required = false)
    private BaseRepAssetEquityInfoService baseRepAssetEquityInfoService;

    /**
     * 基础--中债报送(资产)
     */
    @Autowired(required = false)
    private BaseRepBondAssetInfoService baseRepBondAssetInfoService;
  //标的共通service
    @Autowired(required=false)
    private AssetCommonService assetCommonService;
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

    @Autowired(required = false)
    private BaseRepOtherInfoService baseRepOtherInfoService;
    
    /**
     * 分页
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinBondTargetAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("MAIN_GRADE_TYPE_01", DictCodeList.评级类型_主体评级.getValue());
        pageParam.put("MAIN_GRADE_TYPE_02", DictCodeList.评级类型_债项评级.getValue());
        Page<FinBondTargetAssetDto> pageDto = super.queryForPage(page, length, () -> finBondTargetAssetMapper.queryForPage(pageParam));
        List<FinBondTargetAssetDto> pageList = pageDto.getRoot();
       // List<FinBondTargetAssetDto> finaList = new ArrayList<FinBondTargetAssetDto>();
        /*for(FinBondTargetAssetDto dto : pageList ){
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("secId", dto.getFinprodMarketId());
            param.put("ZTPI", DictCodeList.评级类型_主体评级.getValue());
            param.put("ZXPJ", DictCodeList.评级类型_债项评级.getValue());
            //查询评级类型为主体评级的数据
            List<FinBondTargetAssetDto> ztList = finBondTargetAssetMapper.getZTPJ(param);
            if(ztList != null && ztList.size()>0){
                //只取第一条数据
                dto.setZtGradeOrgId(ztList.get(0).getZtGradeOrgId());
                dto.setZtGradeResult(ztList.get(0).getZtGradeResult());
            }
            //查询评级类型是债项评级的数据
            List<FinBondTargetAssetDto> zxList = finBondTargetAssetMapper.getZXPJ(param);
            if(zxList != null && zxList.size()>0){
                //只取第一条数据
                dto.setZxGradeOrgId(zxList.get(0).getZxGradeOrgId());
                dto.setZxGradeResult(zxList.get(0).getZxGradeResult());
            }
            finaList.add(dto);
        }*/
        pageDto.setRoot(pageList);
        return pageDto;
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(FinBondTargetAssetDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        //计算规则扩展--单独针对平安
        if(null!=finInterestFactorService){
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }
        //债券评级信息
        if(null!=busiFinBondRatingInfoService){
            if(null!=entity.getBusiFinBondRatingInfoDto()){
                if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                    List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
                    for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                    	//20200327 taoran add   此处只可修改债项评级  不可修改主体评级   否则会导致评级表数据有问题
                    	if(DictCodeList.评级类型_债项评级.getValue().equals(mstBondCreditRatingEntity.getoType())) {
                    		mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
                    	}
                    }
                }
            }
        }
        //权益类资产中债报送
        if (StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股权.getValue())
                ||StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())) {
            if(null!=baseRepAssetEquityInfoService){
                if(null!=entity.getBaseRepAssetEquityInfoDto()){
                    List<RepAssetEquity> list = entity.getBaseRepAssetEquityInfoDto().getRepAssetEquityList();
                    baseRepAssetEquityInfoService.insert(list);
                }
            }
            //资产负债
            if(null!=baseRepAssetDeptInfoService){
                if(null!=entity.getBaseRepAssetDeptInfoDto()){
                    baseRepAssetDeptInfoService.insert(entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList());
                }
            }
        }else{
            if(null!=baseRepAssetDeptInfoService){
                // add by lizhi 判断系统参数ASSET_INFO_CHB_HIDE是否显示中债信息 2020-2-28
                if(!StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ASSET_INFO_CHB_HIDE), DictCodeListExt1.是或否_否.getValue())){
                    //中债报送
                    baseRepAssetDeptInfoService.insert(entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList());
                    baseRepBondAssetInfoService.insert(entity.getBaseRepBondAssetInfoDto().getRepAssetBondList());
                }
            }
        }
        //add by zhangnian 20191014 start
        //中债新增信息
        if(null!=baseRepOtherInfoService){
            if(null!=entity.getBaseRepOtherDto()){
                baseRepOtherInfoService.insert(entity.getBaseRepOtherDto().getRepAssetOtherList());
            }
        }
        //add by zhangnian 20191014 end
      //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct,entity);
        //将数据插入到标的类金融产品扩展表
        paFinProductMapper.insertSelective(paFinProduct);
        /**
         * 增加授信关联
         */
        if(entity.getPflsh() != null && !StringUtil.equals(entity.getPflsh(), "")){
            CreditLines creditLines = new CreditLines();
            creditLines.setCustomerId(entity.getFinprodId());
            creditLines.setSeqno(entity.getSeqno());
            creditLines.setCreditMstId(entity.getCreditMstId());
            assetCommonService.insertCredit(creditLines);
        }
        //转债信息
        if((StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue()))&&!StringUtil.isEmpty(entity.getConFinprodMarketId())){
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
    public void update(FinBondTargetAssetDto entity){
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //计算规则扩展--单独针对平安
        if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }
      //债券评级信息
        if(null!=busiFinBondRatingInfoService){
            
        	/**
        	busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
            if(null!=entity.getBusiFinBondRatingInfoDto()){
                if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                    List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
                    for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                    	//20200327 taoran add   此处只可修改债项评级  不可修改主体评级   否则会导致评级表数据有问题
                    	if(DictCodeList.评级类型_债项评级.getValue().equals(mstBondCreditRatingEntity.getoType())) {
                    		mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
                    	}
                    }
                }
            }
            */

        	// modify by wj  20200427  修改bug 没修改评级也会进行债券评级变动提醒
        	
            if(null!=entity.getBusiFinBondRatingInfoDto() && null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                
                List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();

            	//1. 先删除掉 跟新list 不一样的数据。。 说明有数据被删/修改
            	busiFinBondRatingInfoService.delDifferentDateByUnique(mstBondCreditRatingList,entity.getFinprodMarketId());
            	for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                	
            		int cnt = busiFinBondRatingInfoService.getMstBondCreditRatingByUnique(mstBondCreditRatingEntity);
            		// 如果能查到说明已存在  查不到则新增。
            		if(cnt<=0){
            			if(DictCodeList.评级类型_债项评级.getValue().equals(mstBondCreditRatingEntity.getoType())) {
                    		mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
                    	}
                        //主体评级根据债券发行人在市场机构里维护，债券标的评级信息里不维护主体评级  modify bu caojin 20201004
//            			else if(DictCodeList.评级类型_主体评级.getValue().equals(mstBondCreditRatingEntity.getoType())) {
//                    	    mstBondCreditRatingEntity.setoCode(entity.getIssuer());
//                            mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
//                    	}
            		}
                	
                }
            	
            }else{
            	//更新的数据中评级信息为空
                busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());

            }
            
        }
        //权益类资产中债报送
        if (StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股权.getValue())
                ||StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())) {
            if(null!=baseRepAssetEquityInfoService){
                if(null!=entity.getBaseRepAssetEquityInfoDto()){
                    List<RepAssetEquity> list = entity.getBaseRepAssetEquityInfoDto().getRepAssetEquityList();
                    entity.getBaseRepAssetEquityInfoDto().setRepAssetEquityList(list);
                    baseRepAssetEquityInfoService.update(entity.getBaseRepAssetEquityInfoDto());
                    //修改债券资产二级分类，比如从债券修改为权益类，REP_ASSET_BOND表数据删除不掉bug修改
                    BaseRepBondAssetInfoDto baseRepBondAssetInfoDto =new BaseRepBondAssetInfoDto();
                    List<String> repFinprodIdList = new ArrayList<String>();
                    repFinprodIdList.add(list.get(0).getRepFinprodId());
                    baseRepBondAssetInfoDto.setRepFinprodIdList(repFinprodIdList);
                    List<RepAssetBond> repAssetBondTemp = baseRepBondAssetInfoService.select(baseRepBondAssetInfoDto);
                    if(!StringUtil.isEmptyList(repAssetBondTemp)){
                        baseRepBondAssetInfoService.delete(baseRepBondAssetInfoDto);
                    }
                }
            }
            //资产负债
            if(null!=baseRepAssetDeptInfoService){
                if(null!=entity.getBaseRepAssetDeptInfoDto()){
                    baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(entity.getBaseRepAssetDeptInfoDto());
                }
            }
        }else{
            if(null!=baseRepAssetDeptInfoService){
                //中债报送
                baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(entity.getBaseRepAssetDeptInfoDto());
                baseRepBondAssetInfoService.update(entity.getBaseRepBondAssetInfoDto());
            }
        }
        //add by zhangnian 20191014 start
        //中债新增信息
        if(null!=baseRepOtherInfoService){
            if(null!=entity.getBaseRepOtherDto()){
                baseRepOtherInfoService.update(entity.getBaseRepOtherDto());
            }
        }
        //add by zhangnian 20191014 end
      //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct,entity);
        //修改标的类金融产品扩展表
        paFinProductMapper.updateByPrimaryKey(paFinProduct);
        /**
         * 修改授信关联
         */
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        creditLines.setSeqno(entity.getSeqno());
        creditLines.setCreditMstId(entity.getCreditMstId());

       /*
        *del by xr 和红红确认了，债券标的修改不做授信修改，测试时，请注意测试此部分类容
       *assetCommonService.updateCredit(creditLines);
       */
        //转债信息
        if(StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(entity.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            finProductConvertibleMapper.deleteByPrimaryKey(entity.getFinprodId());
            if(StringUtil.isNotEmpty(entity.getConFinprodMarketId())){
                finProductConvertibleMapper.insert(entity.getFinProductConvertible());
            }
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
    public void delete(FinBondTargetAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        // added by wangzhiang BUG修复，债券删除时漏删一张表 2019-08-24 start
        paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
        // added by wangzhiang BUG修复，债券删除时漏删一张表 2019-08-24 end
        //计算规则扩展--单独针对平安
        if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule()); 
        }
        //债券评级信息
        if(null!=busiFinBondRatingInfoService){
            //删除的时候评级信息表数据sec_id 是市场代码，不是金融产品代码
            busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
        }
        //附件信息
        mstFileCommonService.updateBusinessId(entity.getMstFileInputParameter());
        //权益类资产中债报送
        if (StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股权.getValue())
                ||StringUtil.equals(entity.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())) {
            if(null!=baseRepAssetEquityInfoService){
                if(null!=entity.getBaseRepAssetEquityInfoDto()){
                    baseRepAssetEquityInfoService.delete(entity.getBaseRepAssetEquityInfoDto());
                }
            }
            if(null!=baseRepAssetDeptInfoService){
                if(null!=entity.getBaseRepAssetDeptInfoDto()){
                    baseRepAssetDeptInfoService.deleteByPrimaryKey(entity.getBaseRepAssetDeptInfoDto());
                }
            }
        }else{
                //中债报送信息
                baseRepAssetDeptInfoService.deleteByPrimaryKey(entity.getBaseRepAssetDeptInfoDto());
                baseRepBondAssetInfoService.delete(entity.getBaseRepBondAssetInfoDto());
        }
        //add by zhangnian 20191014 start
        //中债新增信息
        if(null!=baseRepOtherInfoService){
            if(null!=entity.getBaseRepOtherDto()){
                baseRepOtherInfoService.delete(entity.getBaseRepOtherDto());
            }
        }
        //add by zhangnian 20191014 end
      //删除授信关联
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        assetCommonService.deleteCredit(creditLines);
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
    public FinBondTargetAssetDto getBasicInfo(String finprodId) {
        FinBondTargetAssetDto finBondTargetAssetDto = new FinBondTargetAssetDto();
        finBondTargetAssetDto = finBondTargetAssetMapper.getBasicInfo(finprodId, DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        //转债信息
        if(null!=finBondTargetAssetDto){
            FinProductConvertible finProductConvertible = finProductConvertibleMapper.selectByPrimaryKey(finBondTargetAssetDto.getFinprodId());
            if (null != finProductConvertible) {
                FinProduct finProduct = finProductMapper.selectByPrimaryKey(finProductConvertible.getConFinprodId());
                if(null!=finProduct){
                    finBondTargetAssetDto.setConFinprodMarketId(finProduct.getFinprodMarketId());
                }
                finBondTargetAssetDto.setFinProductConvertible(finProductConvertible);
            }
        }
        return finBondTargetAssetDto;
    }
        /*return finBondTargetAssetMapper.getBasicInfo(finprodId,
            // 债券新建新增快捷录入功能 add by renping 20191010 start
            DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            // 债券新建新增快捷录入功能 add by renping 20191010 end
        }

    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * 查询各个组件业务表数据
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public void searchData(FinBondTargetAssetDto entity){
        //计算规则扩展--单独针对平安
        if (null != finInterestFactorService) {
            entity.setFinInterestFactorDto(
                finInterestFactorService.getFinInterestFactorByProductId(entity.getFinprodId(),
                    DateUtil.format(entity.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue()));
        }
        //债券评级信息
        /*if(null!=busiFinBondRatingInfoService){
            entity.setBusiFinBondRatingInfoDto(new BusiFinBondRatingInfoDto());;
            List<MstBondCreditRating> mstBondCreditRatingList = busiFinBondRatingInfoService.getmstBondCreditRatingByProductId(
                entity.getFinprodId());
            entity.getBusiFinBondRatingInfoDto().setMstBondCreditRatingList(
                mstBondCreditRatingList);
        }*/
        //中债报送
        //根据金融产品代码查询资产负债报送数据
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        BaseRepAssetDeptInfoDto dto2 = new BaseRepAssetDeptInfoDto();
        dto2.setFinprodIdList(finprodIdList);
        entity.setBaseRepAssetDeptInfoDto(new BaseRepAssetDeptInfoDto());
        List<RepAssetDept> repAssetDeptList = baseRepAssetDeptInfoService.selectRepAssetDeptInfo(
            dto2);
        entity.getBaseRepAssetDeptInfoDto().setRepAssetDeptList(repAssetDeptList);

        // 债券资产报送信息
        entity.setBaseRepBondAssetInfoDto(new BaseRepBondAssetInfoDto());
        entity.getBaseRepBondAssetInfoDto().setRepFinprodIdList(finprodIdList);
        entity.getBaseRepBondAssetInfoDto().setRepAssetBondList(
            baseRepBondAssetInfoService.select(entity.getBaseRepBondAssetInfoDto()));
        //权益类报送信息
        entity.setBaseRepAssetEquityInfoDto(new BaseRepAssetEquityInfoDto());
        entity.getBaseRepAssetEquityInfoDto().setRepFinprodIdList(finprodIdList);
        entity.getBaseRepAssetEquityInfoDto().setRepAssetEquityList(baseRepAssetEquityInfoService.select(entity.getBaseRepAssetEquityInfoDto()));
        //报送其他信息
        entity.setBaseRepOtherDto(new BaseRepOtherDto());
        entity.getBaseRepOtherDto().setRepFinprodIdList(finprodIdList);
        entity.getBaseRepOtherDto().setRepAssetOtherList(baseRepOtherInfoService.select(entity.getBaseRepOtherDto()));
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
        param.put("assestType", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        return this.queryForPageForSelectReturn(page, length,()->finBondTargetAssetMapper.getfinprodIdForAdd(param));
    }

	public int isHqTrade(String finprodId) {
		return finBondTargetAssetMapper.isHqTrade(finprodId);
	}

    /**
     * 金融产品代码是否重复
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodId(String finprodId) {
        return finBondTargetAssetMapper.selectCountByfinprodId(finprodId);
    }

    /**
     * 市场投标债券 是否重复
     * <功能详细描述>
     * @param oFinprodId
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByOFinprodId(String oFinprodId,String finprodId) {
        return finBondTargetAssetMapper.selectCountByOFinprodId(oFinprodId,finprodId);
    }

    /**
     * 根据资产代码查询是否是上市标的
     *
     * @param finprodId
     * @return 是否是上市标的，大于0则为是
     * @see
     * @since 1.0
     */
    public int selectIsIssueAsset(String finprodId) {
        return finBondTargetAssetMapper.selectIsIssueAsset(finprodId);
    }
}
