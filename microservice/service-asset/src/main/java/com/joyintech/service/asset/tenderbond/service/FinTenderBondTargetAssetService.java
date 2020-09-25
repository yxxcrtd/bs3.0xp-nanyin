/*
 * 文件名：FinTenderBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.tenderbond.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.MstBondCreditRatingMapper;
import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.service.asset.common.service.BusiFinBondRatingInfoService;
import com.joyintech.service.asset.common.service.FinInterestFactorService;
import com.joyintech.service.asset.tenderbond.dao.FinTenderBondTargetAssetMapper;
import com.joyintech.service.asset.tenderbond.dto.FinTenderBondTargetAssetDto;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepBondAssetInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 一级债券标的信息
 * @author wangpeng
 * @version 1.0 2019年7月4日
 * @see FinTenderBondTargetAssetService
 * @since 1.0
 */
@Service
public class FinTenderBondTargetAssetService extends BaseService<FinTenderBondTargetAssetDto>{
    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    /**
     * 基础 --附件
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    @Autowired
    private FinTenderBondTargetAssetMapper finTenderBondTargetAssetMapper;

  //标的共通service
    @Autowired(required=false)
    private AssetCommonService assetCommonService;
    @Autowired(required=false)
    private PaFinProductMapper paFinProductMapper;
    
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
    public Page<FinTenderBondTargetAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("MAIN_GRADE_TYPE_01", DictCodeList.评级类型_主体评级.getValue());
        pageParam.put("MAIN_GRADE_TYPE_02", DictCodeList.评级类型_债项评级.getValue());
        Page<FinTenderBondTargetAssetDto> pageDto = super.queryForPage(page, length, () -> finTenderBondTargetAssetMapper.queryForPage(pageParam));
        List<FinTenderBondTargetAssetDto> pageList = pageDto.getRoot();
        List<FinTenderBondTargetAssetDto> finaList = new ArrayList<FinTenderBondTargetAssetDto>();
        for(FinTenderBondTargetAssetDto dto : pageList ){
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("secId", dto.getFinprodMarketId());
            param.put("ZTPI", DictCodeList.评级类型_主体评级.getValue());
            param.put("ZXPJ", DictCodeList.评级类型_债项评级.getValue());
            //查询评级类型为主体评级的数据
            List<FinTenderBondTargetAssetDto> ztList = finTenderBondTargetAssetMapper.getZTPJ(param);
            if(ztList != null && ztList.size()>0){
                //只取第一条数据
                dto.setZtGradeOrgId(ztList.get(0).getZtGradeOrgId());
                dto.setZtGradeResult(ztList.get(0).getZtGradeResult());
            }
            //查询评级类型是债项评级的数据
            List<FinTenderBondTargetAssetDto> zxList = finTenderBondTargetAssetMapper.getZXPJ(param);
            if(zxList != null && zxList.size()>0){
                //只取第一条数据
                dto.setZxGradeOrgId(zxList.get(0).getZxGradeOrgId());
                dto.setZxGradeResult(zxList.get(0).getZxGradeResult());
            }
            finaList.add(dto);
        }
        pageDto.setRoot(finaList);
        return pageDto;
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(FinTenderBondTargetAssetDto entity) {
        //标的金融产品信息
    	if(StringUtil.isNotEmpty(entity.getBaseFinProductBaseInfoList())) {
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
    	}
        //计算规则扩展--单独针对平安
        /*if(null!=finInterestFactorService){
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }*/
        //债券评级信息
       /* if(null!=busiFinBondRatingInfoService){
            if(null!=entity.getBusiFinBondRatingInfoDto()){
                if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                    List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
                    for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                        mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
                    }
                }
            }
        }
        //中债报送
        baseRepAssetDeptInfoService.insert(entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList());
        baseRepBondAssetInfoService.insert(entity.getBaseRepBondAssetInfoDto().getRepAssetBondList());*/
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
    }
    // 一级市场新债日历插入 by zhaodongdong 20191116 start
    /**
     * 一级市场新债日历插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void getInsert(FinTenderBondTargetAssetDto entity) {
        //标的金融产品信息
    	FinProduct finProduct = entity.getFinProduct();
    	finProductMapper.insert(finProduct);
    	FinProductType finProductType = entity.getFinProductType();
    	finProductTypeMapper.insert(finProductType);
    	FinProductAdd finProductAdd = entity.getFinProductAdd();
    	finProductAddMapper.insert(finProductAdd);
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
    }
 // 一级市场新债日历插入 by zhaodongdong 20191116 end
    /**
     * 修改
     * @param entity
     * @see
     * @since 1.0
     */
    public void update(FinTenderBondTargetAssetDto entity){
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //计算规则扩展--单独针对平安
       /* if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }*/
      //债券评级信息
       /* if(null!=busiFinBondRatingInfoService){
            busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
            if(null!=entity.getBusiFinBondRatingInfoDto()){
                if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                    List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
                    for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                        mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
                    }
                }
            }
        }*/
        //中债报送
       /* baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(entity.getBaseRepAssetDeptInfoDto());
        baseRepBondAssetInfoService.update(entity.getBaseRepBondAssetInfoDto());*/
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
        assetCommonService.updateCredit(creditLines);
    }
    
    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(FinTenderBondTargetAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        //计算规则扩展--单独针对平安
       /* if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule()); 
        }*/
        //债券评级信息
        /*if(null!=busiFinBondRatingInfoService){
            busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodId());
        }*/
        //附件信息
        mstFileCommonService.updateBusinessId(entity.getMstFileInputParameter());
        //中债报送信息
       /* baseRepAssetDeptInfoService.deleteByPrimaryKey(entity.getBaseRepAssetDeptInfoDto());
        baseRepBondAssetInfoService.delete(entity.getBaseRepBondAssetInfoDto());*/
      //删除授信关联
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        assetCommonService.deleteCredit(creditLines);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinTenderBondTargetAssetDto getBasicInfo(String finprodId){
        return finTenderBondTargetAssetMapper.getBasicInfo(finprodId);
    }
    
    /**
     * 市场代码是否重复
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodMarketId(String finprodMarketId){
        return finTenderBondTargetAssetMapper.selectCountByfinprodMarketId(finprodMarketId);
    }
}
