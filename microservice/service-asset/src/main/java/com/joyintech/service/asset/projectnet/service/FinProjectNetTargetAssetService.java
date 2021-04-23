/*
 * 文件名：FinPorjectTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.projectnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.dao.BussFinProductCreditMapper;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.service.asset.common.service.BusiFinBondRatingInfoService;
import com.joyintech.service.asset.project.dao.FinProjectTargetAssetMapper;
import com.joyintech.service.asset.projectnet.dao.FinProjectNetTargetAssetMapper;
import com.joyintech.service.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import com.joyintech.tams.common.base.dao.FinProductCreditMapper;
import com.joyintech.tams.common.base.dao.MstBondCreditRatingMapper;
import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * 项目类资产标的信息管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see
 * @since 1.0
 */
@Service("backFinProjectNetTargetAssetService")
public class FinProjectNetTargetAssetService extends BaseService<FinProjectNetTargetAssetDto>{

    @Autowired
    private FinProjectNetTargetAssetMapper finProjectNetTargetAssetMapper;
    
    @Autowired
    private FinProjectTargetAssetMapper finProjectTargetAssetMapper;
    
    @Autowired
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    

    /**
     * 金融产品扩展(平安)
     */
    @Autowired
    private PaFinProductMapper  paFinProductMapper;

    @Autowired(required=false)
    private AssetCommonService assetCommonService;
    
    /**
     * 组合表mapper
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    
    /**
     * 增信信息
     */
    @Autowired
    private FinProductCreditMapper finProductCreditMapper;

    @Autowired
    private BussFinProductCreditMapper bussFinProductCreditMapper;

    @Autowired
    private MstBondCreditRatingMapper  mstBondCreditRatingMapper;

    @Autowired(required = false)
    private BusiFinBondRatingInfoService busiFinBondRatingInfoService;

    /**
     * 查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinProjectNetTargetAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finProjectNetTargetAssetMapper.queryForPage(pageParam));
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(FinProjectNetTargetAssetDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        //金融产品扩展(单独针对平安)
        if(null!=entity.getPaFinProduct()){
            paFinProductMapper.insert(entity.getPaFinProduct());
        }
        //增信信息
        if(null!=entity.getBussFinProductCreditDto()){
            for(FinProductCredit finProductCredit:entity.getBussFinProductCreditDto().getFinProductCreditList()){
                finProductCreditMapper.insert(finProductCredit);
            }
        }
        //add 2019.7.15 wangpeng 根据配置 决定是否入库组合表信息
        boolean isCreatePortfolio = false;
        if(entity.getBaseFinProductBaseInfoList()!=null&& !entity.getBaseFinProductBaseInfoList().isEmpty()  ){
        	FinProductType type = entity.getBaseFinProductBaseInfoList().get(0).getFinProductType();
        	isCreatePortfolio = assetCommonService.selectIsCreatePortfolio(type);
        }
        if(isCreatePortfolio){
        	 //组合表插入
            ptlPortfolioMapper.deleteByPrimaryKey(entity.getPtlPortfolio().getPortfolioId());
            ptlPortfolioMapper.insert(entity.getPtlPortfolio());
        }
        //end
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
        /**
         * 20191126	add
         * 评级信息
         */
        if(entity.getBusiFinBondRatingInfoDto() != null
                && !entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
            List<MstBondCreditRating> MstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
            for(MstBondCreditRating mstBondCreditRating : MstBondCreditRatingList) {
//        		if("02".equals(mstBondCreditRating.getoType())) {
                mstBondCreditRating.setoCode(entity.getFinprodMarketId());
                mstBondCreditRatingMapper.insert(mstBondCreditRating);
//            	}
            }
        }
//        if(entity.getBusiFinBondRatingInfoDto() != null
//                && !entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
//            List<MstBondCreditRating> MstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
//            for(MstBondCreditRating mstBondCreditRating : MstBondCreditRatingList) {
////                if("02".equals(mstBondCreditRating.getoType())) {
////                    mstBondCreditRating.setoCode(entity.getFinprodMarketId());
////                    mstBondCreditRatingMapper.insert(mstBondCreditRating);
////                }
//                //填写的资产评级数据入库
//                if(StringUtil.equals(DictCodeListExt3.评级类型_资产评级.getValue(),mstBondCreditRating.getoType())) {
//                    mstBondCreditRating.setoCode(entity.getFinprodMarketId());
//                    if(StringUtil.equals(mstBondCreditRating.getGradeOrgId(), DictCodeListExt1.评级机构_行内.getValue())
//                            ||StringUtil.equals(mstBondCreditRating.getGradeOrgId(), DictCodeListExt1.评级机构_资管内部.getValue())){
//                            finProjectTargetAssetMapper.insertOut(mstBondCreditRating);
//                        }else{
//                            mstBondCreditRatingMapper.insert(mstBondCreditRating);
//                        }
//                }
//            }
//        }
    }
    
    /**
     * 修改
     * @param entity
     * @see
     * @since 1.0
     */
    public void update(FinProjectNetTargetAssetDto entity){
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //金融产品扩展(单独针对平安)
        if(null!=entity.getPaFinProduct()){
            paFinProductMapper.deleteByPrimaryKey(entity.getPaFinProduct().getFinprodId());
            paFinProductMapper.insert(entity.getPaFinProduct());
        }
        //增信信息
        if(null!=entity.getBussFinProductCreditDto()){
            bussFinProductCreditMapper.deleteFinProductCreditByProductId(entity.getFinprodId());
            for(FinProductCredit finProductCredit:entity.getBussFinProductCreditDto().getFinProductCreditList()){
                finProductCreditMapper.insert(finProductCredit);
            }
        }
        /**
         * 修改授信关联
         */
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        creditLines.setSeqno(entity.getSeqno());
        creditLines.setCreditMstId(entity.getCreditMstId());
        assetCommonService.updateCredit(creditLines);
//        //删除批复号
//        if(StringUtil.isEmpty(entity.getPflsh())){
//            assetCommonService.deleteCreditAssetpoolDetailByFinprodId(entity.getFinprodId());
//        }
        /**
         * 评级数据修改
         * 强删强增	20191126
         */
        busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
        if(null!=entity.getBusiFinBondRatingInfoDto()){
            if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
                List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
                for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
                    //20191125	add  债券标的评级修改，只处理债项数据。
//                	if("02".equals(mstBondCreditRatingEntity.getoType())) {
                    mstBondCreditRatingEntity.setoCode(entity.getFinprodMarketId());
                    mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
//                	}
                }
            }
        }
//        /**
//         * 评级数据修改
//         * 强删强增	20191126
//         */
//        busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
//        busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductIdOut(entity.getFinprodMarketId());
//        if(null!=entity.getBusiFinBondRatingInfoDto()){
//            if(null!=entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()
//                    &&!entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList().isEmpty()){
//                List<MstBondCreditRating> mstBondCreditRatingList = entity.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
//                for(MstBondCreditRating mstBondCreditRatingEntity:mstBondCreditRatingList){
//                    //20191125	add  债券标的评级修改，只处理债项数据。
////                    if("02".equals(mstBondCreditRatingEntity.getoType())) {
////                        mstBondCreditRatingEntity.setoCode(entity.getFinprodMarketId());
////                        mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
////                    }
//                    if(StringUtil.equals(DictCodeListExt3.评级类型_资产评级.getValue(),mstBondCreditRatingEntity.getoType())) {
//                        mstBondCreditRatingEntity.setoCode(entity.getFinprodMarketId());
//                        if(StringUtil.equals(mstBondCreditRatingEntity.getGradeOrgId(), DictCodeListExt1.评级机构_行内.getValue())
//                                ||StringUtil.equals(mstBondCreditRatingEntity.getGradeOrgId(), DictCodeListExt1.评级机构_资管内部.getValue())){
//                                finProjectTargetAssetMapper.insertOut(mstBondCreditRatingEntity);
//                        }else{
//                                mstBondCreditRatingMapper.insert(mstBondCreditRatingEntity);
//                        }
//                    }
//                }
//            }
//        }
    }
    
    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(FinProjectNetTargetAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        //金融产品扩展(单独针对平安)
        if(null!=entity.getPaFinProduct()){
            for(String finprodId:finprodIdList){
                paFinProductMapper.deleteByPrimaryKey(finprodId);
            }
        }
        //增信信息
        if(null!=entity.getBussFinProductCreditDto()){
            bussFinProductCreditMapper.deleteFinProductCreditByProductId(entity.getFinprodId());
        }
        //删除委外组合 add wangpeng 2019.07.23
        if(StringUtil.isNotEmpty(entity.getFinprodMarketId())){
        	 ptlPortfolioMapper.deleteByPrimaryKey(entity.getFinprodMarketId());
        }
        //add end 2019.07.23
      //删除授信关联
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
//        assetCommonService.deleteCreditAssetpoolDetailByFinprodId(entity.getFinprodId());
        assetCommonService.deleteCredit(creditLines);
        // 评级删除	20191126
        busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductId(entity.getFinprodMarketId());
//        busiFinBondRatingInfoService.deleteMstBondCreditRatingByProductIdOut(entity.getFinprodMarketId());
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinProjectNetTargetAssetDto getBasicInfo(@RequestBody Map<String,Object> param){
        return finProjectNetTargetAssetMapper.getBasicInfo(param);
    }
    

    public String checkMarketExists(String finprodMarketId) {
        Map<String, Object> map = new HashMap<>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("finprodType2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        return finProjectNetTargetAssetMapper.checkMarketExists(map);
    }
}
