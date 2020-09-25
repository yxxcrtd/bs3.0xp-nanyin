/*
 * 文件名：ChannelAssetQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.channel.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.dao.StlFinProductAcctMapper;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.common.base.entity.StlFinProductAcctKey;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.channel.dao.ChannelAssetMapper;
import com.joyintech.service.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepMngAssetPlanInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepMngAssetPlanInfoDto;

/**
 * 
 * 通道资产查询service
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see ChannelAssetQueryService
 * @since 1.0
 */
@Service
public class ChannelAssetService extends BaseService<ChannelAssetDto> {

    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
    @Autowired(required=false)
    private PaFinProductMapper paFinProductMapper;
    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
    /**
     * 通道资产查询sql
     */
    @Autowired
    private ChannelAssetMapper channelAssetMapper;
    
    @Autowired
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    @Autowired
    private StlFinProductAcctMapper stlFinProductAcctMapper;
    
    /**
     * 资产管理产品中债报送信息组件
     */
    @Autowired
    private BaseRepMngAssetPlanInfoService baseRepMngAssetPlanInfoService;
    /**
     * 资产负债报送信息组件
     */
    @Autowired
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;
    
    /**
     * 分页查询
     * 
     * @param length 长度
     * @param page 页数
     * @param pageParam 分页查询参数
     * @return 组合管理集合
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ChannelAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodId", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }else{
            pageParam.put("finprodId",null);
        }
        String finprodName = (String)pageParam.get("finprodName");
        if(StringUtil.isNotEmpty(finprodName)){
            pageParam.put("finprodName", StringUtil.splitToList(GlobalConstant.COMMA,finprodName));
        }else{
            pageParam.put("finprodName",null);
        }
        return super.queryForPage(page, length,() -> channelAssetMapper.selectForPage(pageParam));
    }
    /**
     * 获取通道代
     * @param search 查询条件
     * @return 通道代码 金融产品代码
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcctId(Map<String,Object> parm) {
        return channelAssetMapper.getSecAcctId(parm);
    }
    
    /**
     * 获取通道名称
     * @param search 查询条件
     * @return 通道名称 金融产品代码
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcctName(Map<String,Object> parm) {
        return channelAssetMapper.getSecAcctName(parm);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(ChannelAssetDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        //金融产品账户表
        stlFinProductAcctMapper.insert(entity.getStlFinProductAcct());
        BaseRepMngAssetPlanInfoDto baseRepMngAssetPlanInfoDto = new BaseRepMngAssetPlanInfoDto();
        List<RepAssetMngPlan> repAssetMngPlanList = new ArrayList<>();
        repAssetMngPlanList.add(entity.getRepAssetMngPlan());
        baseRepMngAssetPlanInfoDto.setRepAssetMngPlanList(repAssetMngPlanList);
        baseRepMngAssetPlanInfoService.insert(baseRepMngAssetPlanInfoDto);
        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
        //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct,entity);
        paFinProduct.setVatTaxRate(
            ArithmeticUtil.divide(paFinProduct.getVatTaxRate(), new BigDecimal(100), 6));
        paFinProduct.setAdtTaxRate(
            ArithmeticUtil.divide(paFinProduct.getAdtTaxRate(), new BigDecimal(100), 6));
        //将数据插入到标的类金融产品扩展表
        paFinProductMapper.insertSelective(paFinProduct);
        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
        //调用资产负债报送信息组件插入数据
        List<RepAssetDept> repAssetDeptList = new ArrayList<>();
        repAssetDeptList.add(entity.getRepAssetDept());
        baseRepAssetDeptInfoService.insert(repAssetDeptList);
    }
    
    /**
     * 修改
     * @param entity
     * @see
     * @since 1.0
     */
    public void update(ChannelAssetDto entity){
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        StlFinProductAcctKey key = new StlFinProductAcctKey();
        key.setFinprodId(entity.getFinprodId());
        key.setAcctNo(entity.getOldBusiAcctNo());
        key.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        stlFinProductAcctMapper.deleteByPrimaryKey(key);
        stlFinProductAcctMapper.insert(entity.getStlFinProductAcct());
        BaseRepMngAssetPlanInfoDto baseRepMngAssetPlanInfoDto = new BaseRepMngAssetPlanInfoDto();
        List<RepAssetMngPlan> repAssetMngPlanList = new ArrayList<>();
        repAssetMngPlanList.add(entity.getRepAssetMngPlan());
        baseRepMngAssetPlanInfoDto.setRepAssetMngPlanList(repAssetMngPlanList);
        List<String> repFinprodIdList = new ArrayList<>();
        repFinprodIdList.add(entity.getFinprodId());
        baseRepMngAssetPlanInfoDto.setRepFinprodIdList(repFinprodIdList);
        baseRepMngAssetPlanInfoService.update(baseRepMngAssetPlanInfoDto);
      //调用资产负债报送信息组件修改数据
        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        List<RepAssetDept> repAssetDeptList = new ArrayList<>();
        repAssetDeptList.add(entity.getRepAssetDept());
        baseRepAssetDeptInfoDto.setRepAssetDeptList(repAssetDeptList);
        List<String> finProdIdList = new ArrayList<>();
        finProdIdList.add(entity.getFinprodId());
        baseRepAssetDeptInfoDto.setFinprodIdList(finProdIdList);
        baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);
        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
        //构建标的类金融产品扩展dto
        PaFinProduct paFinProduct = new PaFinProduct();
        BeanUtil.copyPropertiesIgnoreNull(paFinProduct, entity);
        paFinProduct.setVatTaxRate(
            ArithmeticUtil.divide(paFinProduct.getVatTaxRate(), new BigDecimal(100), 6));
        paFinProduct.setAdtTaxRate(
            ArithmeticUtil.divide(paFinProduct.getAdtTaxRate(), new BigDecimal(100), 6));
        //修改标的类金融产品扩展表
        int num = paFinProductMapper.updateByPrimaryKey(paFinProduct);
        if (num == 0) {
            paFinProductMapper.insertSelective(paFinProduct);
        }
        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
    }
    
    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(ChannelAssetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
//        StlFinProductAcctKey key = new StlFinProductAcctKey();
//        key.setFinprodId(entity.getFinprodId());
//        key.setAcctNo(entity.getOldBusiAcctNo());
//        key.setCcy(DictCodeList.币种_人民币_CNY.getValue());
//        stlFinProductAcctMapper.deleteByPrimaryKey(key);
        channelAssetMapper.deleteStlFinProdAcctByFinprodId(entity.getFinprodId());
        BaseRepMngAssetPlanInfoDto baseRepMngAssetPlanInfoDto = new BaseRepMngAssetPlanInfoDto();
        baseRepMngAssetPlanInfoDto.setRepFinprodIdList(finprodIdList);
        baseRepMngAssetPlanInfoService.delete(baseRepMngAssetPlanInfoDto);
        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        baseRepAssetDeptInfoDto.setFinprodIdList(finprodIdList);
        baseRepAssetDeptInfoService.deleteByPrimaryKey(baseRepAssetDeptInfoDto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public ChannelAssetDto getBasicInfo(Map<String,Object> param){
        return channelAssetMapper.getBasicInfo(param);
    }
    /**
     * 判断通道是否存在交易
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int isTransInAsset(String finprodId) {
        Map<String, Object> param = new HashMap<>();
        param.put("finprodId", finprodId);
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("NO", DictCodeList.是否_否.getValue());
        return channelAssetMapper.isTransInAsset(param);
    }


    /**
     * 资金账户已经有通道挂的话，不允许再挂
     * @param map
     * @return
     */
    public int isReBusiAcctNo(Map<String, Object> map) {
        return channelAssetMapper.isReBusiAcctNo(map);
    }
}
