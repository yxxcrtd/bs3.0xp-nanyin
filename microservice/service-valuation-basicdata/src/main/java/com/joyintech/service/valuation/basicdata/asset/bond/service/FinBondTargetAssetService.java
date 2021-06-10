/*
 * 文件名：FinBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.bond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.asset.bond.dao.FinBondTargetAssetMapper;
import com.joyintech.service.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.service.valuation.basicdata.base.service.BaseFinProductBaseInfoService;
import com.joyintech.service.valuation.component.asset.service.FinInterestFactorService;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetService
 * @since 1.0
 */
@Service(value="serviceFinBondTargetAssetService")
public class FinBondTargetAssetService extends BaseService<FinBondTargetAssetDto>{

    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
//    /**
//     * 基础--标的类金融产品
//     */
//    @Autowired(required = false)
//    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;



    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;



    @Autowired
    private FinBondTargetAssetMapper finBondTargetAssetMapper;

    @Autowired
    private MstBondCreditRatingMapper  mstBondCreditRatingMapper;





//    //标的共通service
//    @Autowired(required=false)
//    private AssetCommonService assetCommonService;
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
        return pageDto;
    }

    /**
     * 查询所有数据
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<FinBondTargetAssetDto> selectAllList(Map<String, Object> pageParam) throws Exception {
        pageParam.put("MAIN_GRADE_TYPE_01", DictCodeList.评级类型_主体评级.getValue());
        pageParam.put("MAIN_GRADE_TYPE_02", DictCodeList.评级类型_债项评级.getValue());
        return finBondTargetAssetMapper.queryForPage(pageParam);
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
        //转债信息
        if(StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可转债.getValue())
                ||StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可交换债.getValue())){
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
        //转债信息
        if(StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可转债.getValue())
                ||StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可交换债.getValue())){
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
    public void delete(FinBondTargetAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        //转债信息
        if(StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可转债.getValue())
                ||StringUtil.equals(entity.getAssetType(),DictCodeList.债券类型_可交换债.getValue())){
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
        finBondTargetAssetDto = finBondTargetAssetMapper.getBasicInfo(finprodId, DictCodeListExt1.金融产品类型_债券.getValue());
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

        BaseFinProductBaseInfoDto baseDto = new BaseFinProductBaseInfoDto();
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
     * 验证上市流通代码是否重复
     * @param circulationCode
     * @param finprodId
     * @return
     */
    public int selectCountByCirculationCode(String circulationCode, String finprodId) {
        return finBondTargetAssetMapper.selectCountByCirculationCode(circulationCode, finprodId);
    }
}
