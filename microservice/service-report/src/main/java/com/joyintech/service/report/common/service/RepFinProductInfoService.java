/*
 * 文件名：RepFinProductInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.common.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.report.common.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.RepProdDeclareMapper;
import com.joyintech.tams.common.base.dao.RepProdIssueMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.tams.component.base.report.dto.BaseRepFinProductDto;
import com.joyintech.tams.component.base.report.dto.BaseRepFinProductInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepFinProductInfoMapper;
import com.joyintech.tams.component.base.report.prod.BaseRepFinProductInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品中债报送业务组件微服务
 * 
 * @author zhangliang
 * @version 1.0 2018年10月18日
 * @see RepFinProductInfoService
 * @since 1.0
 */
@Service
public class RepFinProductInfoService {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private RepProdDeclareMapper repProdDeclareMapper;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private RepProdIssueMapper repProdIssueMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BaseRepFinProductInfoService baseRepFinProductInfoService;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinProductMapper finProductMapper;
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;

    /**
     * 产品端中债报备信息组件Mapper
     */
    @Autowired
    private BaseRepFinProductInfoMapper baseRepFinProductInfoMapper;
    /**
     * 根据金融产品代码查询中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return 中债报送信息
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public BussRepFinProductInfoDto select(String finprodId) throws Exception {
        BussRepFinProductInfoDto bussRepFinProductInfoDto = new BussRepFinProductInfoDto();
        RepProdDeclare repProdDeclare = repProdDeclareMapper.selectByPrimaryKey(finprodId);
        if (repProdDeclare != null){
            BeanUtil.copyProperties(bussRepFinProductInfoDto, repProdDeclare);
        }
        RepProdIssue repProdIssue = repProdIssueMapper.selectByPrimaryKey(finprodId);
        if (repProdIssue != null){
            bussRepFinProductInfoDto.setRepExpAvgYield(repProdIssue.getRepExpAvgYield());
            bussRepFinProductInfoDto.setRepYieldComment(repProdIssue.getRepYieldComment());
            bussRepFinProductInfoDto.setRepOpenCircle(repProdIssue.getRepOpenCircle());
            bussRepFinProductInfoDto.setRepOpenCircleDays(repProdIssue.getRepOpenCircleDays());
            bussRepFinProductInfoDto.setRepRuleComment(repProdIssue.getRepRuleComment());
            bussRepFinProductInfoDto.setRepFirstOpenDay(repProdIssue.getRepFirstOpenDay());
            bussRepFinProductInfoDto.setRepAvgOpenCount(repProdIssue.getRepAvgOpenCount());
            bussRepFinProductInfoDto.setRepOpenBuss(repProdIssue.getRepOpenBuss());
            bussRepFinProductInfoDto.setRepOpenBussRemark(repProdIssue.getRepOpenBussRemark());
            bussRepFinProductInfoDto.setRepOpenMode(repProdIssue.getRepOpenMode());
            bussRepFinProductInfoDto.setRepIsConstracte(repProdIssue.getRepIsConstracte());
            bussRepFinProductInfoDto.setRepGradeType(repProdIssue.getRepGradeType());
            //fixed BS-691 BUG add by zhouxiuping at 20200720
            bussRepFinProductInfoDto.setRepExpMaxYield(repProdIssue.getRepExpMaxYield());//业绩比较基准上限%
            bussRepFinProductInfoDto.setRepExpMinYield(repProdIssue.getRepExpMinYield());//业绩比较基准下限%
            bussRepFinProductInfoDto.setRepGradeType(repProdIssue.getRepGradeType());//层级类别
            //fixed BS-691 BUG add by zhouxiuping at 20200720
            bussRepFinProductInfoDto.setRepHolidayOnFlag(repProdIssue.getRepHolidayOnFlag());
        }
        //从标的类金融产品表获取管理方式
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        if(finProduct!=null){
        	bussRepFinProductInfoDto.setRepManageMode(finProduct.getEntrustType());
        }
        //从标的类金融产品附属表获取是否合规新产品、是否养老理财产品
        FinProductAdd finProductadd = finProductAddMapper.selectByPrimaryKey(finprodId);
        if(finProductadd!=null){
        	bussRepFinProductInfoDto.setIsFhxgcp(finProductadd.getIsFhxgcp());
        	bussRepFinProductInfoDto.setIsYllccp(finProductadd.getIsYllccp());
        }
        return bussRepFinProductInfoDto;
    }
    
    /**
     * 根据金融产品代码删除中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public void delete(String finprodId){
        BaseRepFinProductDto dto = new BaseRepFinProductDto();
        List<String> repFinprodIds = new ArrayList<>();
        repFinprodIds.add(finprodId);
        dto.setRepFinprodIds(repFinprodIds);
        baseRepFinProductInfoService.deleteRepFinProductInfo(dto);
    }
    
    /**
     * 插入中债报送信息
     * 
     * @param baseRepFinProductInfoDto 中债报送信息
     * @return 是否成功
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    public void insert(BussRepFinProductInfoDto bussRepFinProductInfoDto) throws Exception{
        this.delete(bussRepFinProductInfoDto.getRepFinprodId());
        
        BaseRepFinProductInfoDto baseRepFinProductInfoDto = new BaseRepFinProductInfoDto();
        baseRepFinProductInfoDto.setRepProdDeclare(bussRepFinProductInfoDto);
        
        RepProdIssue repProdIssue = new RepProdIssue();
        BeanUtil.copyProperties(repProdIssue, bussRepFinProductInfoDto);
        repProdIssue.setRepBankProdCode(bussRepFinProductInfoDto.getRepFinprodInsId());
        repProdIssue.setRepRaiseVdate(bussRepFinProductInfoDto.getRepRaiseVdate());
        repProdIssue.setRepRaiseEnddate(bussRepFinProductInfoDto.getRepRaiseEnddate());
        repProdIssue.setRepPrdVdate(bussRepFinProductInfoDto.getRepPrdVdate());
        repProdIssue.setRepPrdMdate(bussRepFinProductInfoDto.getRepPrdMdate());
        repProdIssue.setRepExpMaxYield(bussRepFinProductInfoDto.getRepExpMaxYield());
        repProdIssue.setRepExpMinYield(bussRepFinProductInfoDto.getRepExpMinYield());
//        if (StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
//            || StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue())){
//            repProdIssue.setRepOpenMode("01");
//        }
        repProdIssue.setRepOpenMode(bussRepFinProductInfoDto.getRepOpenMode());
        repProdIssue.setRepOpenCircle(bussRepFinProductInfoDto.getRepOpenCircle());
        repProdIssue.setRepOpenCircleDays(bussRepFinProductInfoDto.getRepOpenCircleDays());
        repProdIssue.setRepRuleComment(bussRepFinProductInfoDto.getRepRuleComment());
        repProdIssue.setRepFirstOpenDay(bussRepFinProductInfoDto.getRepFirstOpenDay());
        repProdIssue.setRepAvgOpenCount(bussRepFinProductInfoDto.getRepAvgOpenCount());
        repProdIssue.setRepOpenBuss(bussRepFinProductInfoDto.getRepOpenBuss());
        repProdIssue.setRepOpenBussRemark(bussRepFinProductInfoDto.getRepOpenBussRemark());
        repProdIssue.setRepHolidayOnFlag(bussRepFinProductInfoDto.getRepHolidayOnFlag());
        if(bussRepFinProductInfoDto.getRepIsConstracte()!=null){//fixed BS-674 bug at begin add by zhouxiuping
            repProdIssue.setRepIsConstracte(bussRepFinProductInfoDto.getRepIsConstracte());
        }else{
            repProdIssue.setRepIsConstracte(DictCodeList.是否_中债_否.getValue());
        }
        //fixed BS-674 bug at end add by zhouxiuping
        repProdIssue.setRepGradeType(bussRepFinProductInfoDto.getRepGradeType());
        baseRepFinProductInfoDto.setRepProdIssue(repProdIssue);
        
        //标的类金融产品(管理方式存到FIN_PRODUCT.ENTRUST_TYPE里)
        FinProduct finProduct = new FinProduct();
        finProduct.setFinprodId(bussRepFinProductInfoDto.getRepFinprodId());
        finProduct.setEntrustType(bussRepFinProductInfoDto.getRepManageMode());
        baseRepFinProductInfoDto.setFinProduct(finProduct);
        
        List<BaseRepFinProductInfoDto> dtos = new ArrayList<>();
        dtos.add(baseRepFinProductInfoDto);
        // 将产品端中债报送Dto.产品申报报送信息Entity插入产品申报报送信息
        repProdDeclareMapper.insertSelective(baseRepFinProductInfoDto.getRepProdDeclare());
        // 将产品端中债报送Dto.产品发行报送信息
        repProdIssueMapper.insertSelective(baseRepFinProductInfoDto.getRepProdIssue());

        //标的类金融产品
        // 此处的更新操作，在修改产品信息时和common服务处理冲突 delete by heyangyang 2019-09-29
        //baseRepFinProductInfoMapper.updateFinProduct(baseRepFinProductInfoDto.getFinProduct());
    }
    
    /**
     * 更新中债报送信息
     *
     * @param bussRepFinProductInfoDto 中债报送信息
     * @return 是否成功
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void update(BussRepFinProductInfoDto bussRepFinProductInfoDto) throws Exception{
    	if(bussRepFinProductInfoDto.getIsMerger()!=null
    			&& bussRepFinProductInfoDto.getIsMerger().equals(DictCodeList.是否_否.getValue())){
    		//删除发行要素
        	repProdIssueMapper.deleteByPrimaryKey(bussRepFinProductInfoDto.getRepFinprodId());
        	//生成新的金融产品代码
        	bussRepFinProductInfoDto.setRepFinprodId(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue().concat(GlobalConstant.UNDER_LINE).concat(
                    bussRepFinProductInfoDto.getRepFinprodInsId()));
        	
            BaseRepFinProductInfoDto baseRepFinProductInfoDto = new BaseRepFinProductInfoDto();
            baseRepFinProductInfoDto.setRepProdDeclare(bussRepFinProductInfoDto);
            
            RepProdIssue repProdIssue = new RepProdIssue();
            BeanUtil.copyProperties(repProdIssue, bussRepFinProductInfoDto);
            repProdIssue.setRepBankProdCode(bussRepFinProductInfoDto.getRepFinprodInsId());
            repProdIssue.setRepRaiseVdate(bussRepFinProductInfoDto.getRepRaiseVdate());
            repProdIssue.setRepRaiseEnddate(bussRepFinProductInfoDto.getRepRaiseEnddate());
            repProdIssue.setRepPrdVdate(bussRepFinProductInfoDto.getRepPrdVdate());
            repProdIssue.setRepPrdMdate(bussRepFinProductInfoDto.getRepPrdMdate());
            repProdIssue.setRepExpMaxYield(bussRepFinProductInfoDto.getRepExpMaxYield());
            repProdIssue.setRepExpMinYield(bussRepFinProductInfoDto.getRepExpMinYield());
//            if (StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
//                || StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue())){
//                repProdIssue.setRepOpenMode("01");
//            }
            repProdIssue.setRepOpenMode(bussRepFinProductInfoDto.getRepOpenMode());
            repProdIssue.setRepOpenCircle(bussRepFinProductInfoDto.getRepOpenCircle());
            repProdIssue.setRepOpenCircleDays(bussRepFinProductInfoDto.getRepOpenCircleDays());
            repProdIssue.setRepRuleComment(bussRepFinProductInfoDto.getRepRuleComment());
            repProdIssue.setRepFirstOpenDay(bussRepFinProductInfoDto.getRepFirstOpenDay());
            repProdIssue.setRepAvgOpenCount(bussRepFinProductInfoDto.getRepAvgOpenCount());
            repProdIssue.setRepOpenBuss(bussRepFinProductInfoDto.getRepOpenBuss());
            repProdIssue.setRepOpenBussRemark(bussRepFinProductInfoDto.getRepOpenBussRemark());
            repProdIssue.setRepHolidayOnFlag(bussRepFinProductInfoDto.getRepHolidayOnFlag());
            if(bussRepFinProductInfoDto.getRepIsConstracte()!=null){//fixed BS-674 bug at begin add by zhouxiuping
                repProdIssue.setRepIsConstracte(bussRepFinProductInfoDto.getRepIsConstracte());
            }else{
                repProdIssue.setRepIsConstracte(DictCodeList.是否_中债_否.getValue());
            }
            //fixed BS-674 bug at end add by zhouxiuping
            repProdIssue.setRepGradeType(bussRepFinProductInfoDto.getRepGradeType());
            baseRepFinProductInfoDto.setRepProdIssue(repProdIssue);
            
            //标的类金融产品(管理方式存到FIN_PRODUCT.ENTRUST_TYPE里)
            FinProduct finProduct = new FinProduct();
            finProduct.setFinprodId(bussRepFinProductInfoDto.getRepFinprodId());
            finProduct.setEntrustType(bussRepFinProductInfoDto.getRepManageMode());
            baseRepFinProductInfoDto.setFinProduct(finProduct);
            
            List<BaseRepFinProductInfoDto> dtos = new ArrayList<>();
            dtos.add(baseRepFinProductInfoDto);
            // 申报要素更新中债信息
            RepProdDeclare repProdDeclare = baseRepFinProductInfoDto.getRepProdDeclare();//fixed BS-691 BUG add by zhouxiuping at 20200720
            repProdDeclare.setRepFinprodId(bussRepFinProductInfoDto.getRepFinprodId());
            repProdDeclare.setRepFinprodInsId(bussRepFinProductInfoDto.getRepFinprodInsId());
            repProdDeclare.setCustomerType(bussRepFinProductInfoDto.getCustomerType());
            repProdDeclare.setRepIsYieldBasis(bussRepFinProductInfoDto.getRepIsYieldBasis());
            repProdDeclare.setRepFiduciaryDuty(bussRepFinProductInfoDto.getRepFiduciaryDuty());//fixed BS-691 BUG add by zhouxiuping at 20200720

            //repProdDeclareMapper.updateByPrimaryKeySelective(repProdDeclare);
            repProdDeclareMapper.updateByRepFinprodInsId(repProdDeclare);
            // 将产品端中债报送Dto.产品发行报送信息
            repProdIssueMapper.insertSelective(baseRepFinProductInfoDto.getRepProdIssue());
    	}
    	else{
            this.insert(bussRepFinProductInfoDto);
    	}
    }
    /**
     * 根据行内标识码查询中债报送信息
     * 
     * @param finprodId 行内标识码
     * @return 中债报送申报要素信息
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public BussRepFinProductInfoDto selectRepFinprodId(String repFinprodInsId) throws Exception {
        BussRepFinProductInfoDto bussRepFinProductInfoDto = new BussRepFinProductInfoDto();
        RepProdDeclare repProdDeclare = baseRepFinProductInfoMapper.selectRepFinprodId(repFinprodInsId);
        if (repProdDeclare != null){
            BeanUtil.copyProperties(bussRepFinProductInfoDto, repProdDeclare);
//            RepProdIssue repProdIssue = repProdIssueMapper.selectByPrimaryKey(repProdDeclare.getRepFinprodId());
//            if (repProdIssue != null){
//                bussRepFinProductInfoDto.setRepExpAvgYield(repProdIssue.getRepExpAvgYield());;
//                bussRepFinProductInfoDto.setRepYieldComment(repProdIssue.getRepYieldComment());;
//                bussRepFinProductInfoDto.setRepOpenCircle(repProdIssue.getRepOpenCircle());;
//                bussRepFinProductInfoDto.setRepOpenCircleDays(repProdIssue.getRepOpenCircleDays());;
//                bussRepFinProductInfoDto.setRepRuleComment(repProdIssue.getRepRuleComment());;
//                bussRepFinProductInfoDto.setRepFirstOpenDay(repProdIssue.getRepFirstOpenDay());;
//                bussRepFinProductInfoDto.setRepAvgOpenCount(repProdIssue.getRepAvgOpenCount());;
//                bussRepFinProductInfoDto.setRepOpenBuss(repProdIssue.getRepOpenBuss());;
//                bussRepFinProductInfoDto.setRepOpenBussRemark(repProdIssue.getRepOpenBussRemark());;
//                bussRepFinProductInfoDto.setRepOpenMode(repProdIssue.getRepOpenMode());
//                bussRepFinProductInfoDto.setRepIsConstracte(repProdIssue.getRepIsConstracte());
//                bussRepFinProductInfoDto.setRepGradeType(repProdIssue.getRepGradeType());
//            }
//            //从标的类金融产品表获取管理方式
//            FinProduct finProduct = finProductMapper.selectByPrimaryKey(repProdDeclare.getRepFinprodId());
//            if(finProduct!=null){
//            	bussRepFinProductInfoDto.setRepManageMode(finProduct.getEntrustType());
//            }
        }
        
        return bussRepFinProductInfoDto;
    }
    /**
     * 插入中债报送信息
     * 
     * @param baseRepFinProductInfoDto 中债报送信息
     * @return 是否成功
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    public void insertMergerNo(BussRepFinProductInfoDto bussRepFinProductInfoDto) throws Exception{
    	//删除发行要素
    	repProdIssueMapper.deleteByPrimaryKey(bussRepFinProductInfoDto.getRepFinprodId());
    	
        BaseRepFinProductInfoDto baseRepFinProductInfoDto = new BaseRepFinProductInfoDto();
        baseRepFinProductInfoDto.setRepProdDeclare(bussRepFinProductInfoDto);
        
        RepProdIssue repProdIssue = new RepProdIssue();
        BeanUtil.copyProperties(repProdIssue, bussRepFinProductInfoDto);
        repProdIssue.setRepBankProdCode(bussRepFinProductInfoDto.getRepFinprodInsId());
        repProdIssue.setRepRaiseVdate(bussRepFinProductInfoDto.getRepRaiseVdate());
        repProdIssue.setRepRaiseEnddate(bussRepFinProductInfoDto.getRepRaiseEnddate());
        repProdIssue.setRepPrdVdate(bussRepFinProductInfoDto.getRepPrdVdate());
        repProdIssue.setRepPrdMdate(bussRepFinProductInfoDto.getRepPrdMdate());
        repProdIssue.setRepExpMaxYield(bussRepFinProductInfoDto.getRepExpMaxYield());
        repProdIssue.setRepExpMinYield(bussRepFinProductInfoDto.getRepExpMinYield());
//        if (StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
//            || StringUtil.equals(bussRepFinProductInfoDto.getRepOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue())){
//            repProdIssue.setRepOpenMode("01");
//        }
        repProdIssue.setRepOpenMode(bussRepFinProductInfoDto.getRepOpenMode());
        repProdIssue.setRepOpenCircle(bussRepFinProductInfoDto.getRepOpenCircle());
        repProdIssue.setRepOpenCircleDays(bussRepFinProductInfoDto.getRepOpenCircleDays());
        repProdIssue.setRepRuleComment(bussRepFinProductInfoDto.getRepRuleComment());
        repProdIssue.setRepFirstOpenDay(bussRepFinProductInfoDto.getRepFirstOpenDay());
        repProdIssue.setRepAvgOpenCount(bussRepFinProductInfoDto.getRepAvgOpenCount());
        repProdIssue.setRepOpenBuss(bussRepFinProductInfoDto.getRepOpenBuss());
        repProdIssue.setRepOpenBussRemark(bussRepFinProductInfoDto.getRepOpenBussRemark());
        repProdIssue.setRepHolidayOnFlag(bussRepFinProductInfoDto.getRepHolidayOnFlag());
        if(bussRepFinProductInfoDto.getRepIsConstracte()!=null){//fixed BS-674 bug at begin add by zhouxiuping
            repProdIssue.setRepIsConstracte(bussRepFinProductInfoDto.getRepIsConstracte());
        }else{
            repProdIssue.setRepIsConstracte(DictCodeList.是否_中债_否.getValue());
        }
        //fixed BS-674 bug at end add by zhouxiuping
        repProdIssue.setRepGradeType(bussRepFinProductInfoDto.getRepGradeType());
        baseRepFinProductInfoDto.setRepProdIssue(repProdIssue);
        
        //标的类金融产品(管理方式存到FIN_PRODUCT.ENTRUST_TYPE里)
        FinProduct finProduct = new FinProduct();
        finProduct.setFinprodId(bussRepFinProductInfoDto.getRepFinprodId());
        finProduct.setEntrustType(bussRepFinProductInfoDto.getRepManageMode());
        baseRepFinProductInfoDto.setFinProduct(finProduct);
        
        List<BaseRepFinProductInfoDto> dtos = new ArrayList<>();
        dtos.add(baseRepFinProductInfoDto);
        // 申报要素更新人行字段
        RepProdDeclare repProdDeclare = new RepProdDeclare();
        repProdDeclare.setRepFinprodId(bussRepFinProductInfoDto.getRepFinprodId());
        repProdDeclare.setRepFinprodInsId(bussRepFinProductInfoDto.getRepFinprodInsId());
        repProdDeclare.setCustomerType(bussRepFinProductInfoDto.getCustomerType());
        repProdDeclare.setRepHighYield(bussRepFinProductInfoDto.getRepHighYield());
        repProdDeclare.setRepLowYield(bussRepFinProductInfoDto.getRepLowYield());
        repProdDeclare.setRepIsYieldBasis(bussRepFinProductInfoDto.getRepIsYieldBasis());
        repProdDeclare.setRepFiduciaryDuty(bussRepFinProductInfoDto.getRepFiduciaryDuty());
        //repProdDeclareMapper.updateByPrimaryKeySelective(repProdDeclare);
        repProdDeclareMapper.updateByRepFinprodInsId(repProdDeclare);

        // 将产品端中债报送Dto.产品发行报送信息
        repProdIssueMapper.insertSelective(baseRepFinProductInfoDto.getRepProdIssue());

        //标的类金融产品
        // 此处的更新操作，在修改产品信息时和common服务处理冲突 delete by heyangyang 2019-09-29
        //baseRepFinProductInfoMapper.updateFinProduct(baseRepFinProductInfoDto.getFinProduct());
    }
}
