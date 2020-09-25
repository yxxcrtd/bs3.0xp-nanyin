/*
 * 文件名：FinProductSchedulingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.scheduling.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PaPrdIssueScheduleMapper;
import com.joyintech.tams.common.base.dao.RepProdDeclareMapper;
import com.joyintech.tams.common.base.dao.RepProdIssueMapper;
import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.tams.component.base.report.dto.BaseRepFinProductDto;
import com.joyintech.tams.component.base.report.dto.BaseRepFinProductInfoDto;
import com.joyintech.tams.component.base.report.prod.BaseRepFinProductInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.product.productelement.scheduling.dao.FinProductSchedulingMapper;
import com.joyintech.service.product.productelement.scheduling.dto.BussRepFinProductInfoDto;
import com.joyintech.service.product.productelement.scheduling.dto.PrdIssueScheduleDto;

/**
 * 理财产品排期信息管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月13日
 * @see FinProductSchedulingService
 * @since 1.0
 */
@Service("backFinProductSchedulingService")
public class FinProductSchedulingService extends BaseService<PaPrdIssueSchedule> {

    /**
     * {@link FinProductSchedulingMapper}
     */
    @Autowired
    private FinProductSchedulingMapper finProductSchedulingMapper;

    /**
     * {@link PaPrdIssueScheduleMapper}
     */
    @Autowired
    private PaPrdIssueScheduleMapper paPrdIssueScheduleMapper;

    /**
     * {@link RepProdDeclareMapper}
     */
    @Autowired
    private RepProdDeclareMapper repProdDeclareMapper;

    /**
     * {@link RepProdIssueMapper}
     */
    @Autowired
    private RepProdIssueMapper repProdIssueMapper;

    /**
     * {@link BaseRepFinProductInfoService}
     */
    @Autowired
    private BaseRepFinProductInfoService baseRepFinProductInfoService;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<PaPrdIssueSchedule> selectForPage(int page, int length,
                                                  Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> finProductSchedulingMapper.queryAllData(pageParam));
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param param 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SuggestDto> querySchedulingProdInfo(String param) {
        return finProductSchedulingMapper.querySchedulingProdInfo(param);
    }

    /**
     * 查询产品登记编码是否已存在
     * 
     * @param finprodId 产品登记编码
     * @return 是否已存在
     * @see
     * @since 1.0
     */
    public int checkRegistCodeExists(String finprodId) {
        return finProductSchedulingMapper.checkRegistCodeExists(finprodId);
    }

    /**
     * 根据金融产品代码删除排期信息
     * 
     * @param finprodId 金融产品代码
     * @return 影响行数
     * @see
     * @since 1.0
     */
    @Transactional
    public int delete(String finprodId) {
        BaseRepFinProductDto dto = new BaseRepFinProductDto();
        List<String> repFinprodIds = new ArrayList<String>();
        repFinprodIds.add(finprodId);
        dto.setRepFinprodIds(repFinprodIds);
        baseRepFinProductInfoService.deleteRepFinProductInfo(dto);
        return paPrdIssueScheduleMapper.deleteByPrimaryKey(finprodId);
    }

    /**
     * 插入产品排期信息
     * 
     * @param entity 产品排期信息对象
     * @return 影响行数
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @Transactional
    public int insert(PrdIssueScheduleDto entity) throws Exception {
        List<BaseRepFinProductInfoDto> dtos = new ArrayList<BaseRepFinProductInfoDto>();
        BaseRepFinProductInfoDto repDto = new BaseRepFinProductInfoDto();
        // 产品申报登记
        RepProdDeclare repProdDeclare = entity.getBussRepFinProductInfoDto();
        repProdDeclare.setRepFinprodId(entity.getFinprodId());
        repProdDeclare.setRepFinprodName(entity.getFinprodName());
        repProdDeclare.setRepFinprodInsId(entity.getFinprodMarketId());
        repProdDeclare.setRepTerm(entity.getTermType());
        // 产品发行登记
        RepProdIssue repProdIssue = new RepProdIssue();
        BeanUtil.copyProperties(repProdIssue, repProdDeclare);
        repProdIssue.setRepBankProdCode(entity.getFinprodMarketId());
        repProdIssue.setRepRaiseVdate(entity.getRaiseStrdate());
        repProdIssue.setRepRaiseEnddate(entity.getRaiseEnddate());
        repProdIssue.setRepPrdVdate(entity.getVdate());
        repProdIssue.setRepPrdMdate(entity.getMdate());
        repProdIssue.setRepExpMaxYield(repProdDeclare.getRepHighYield());
        repProdIssue.setRepExpMinYield(repProdDeclare.getRepLowYield());
        // repProdIssue.setRepOpenCircle(entity.getOpenCycle());
        // if (entity.getOpenCycleOth() != null) {
        // repProdIssue.setRepOpenCircleDays(entity.getOpenCycleOth().shortValue());
        // }
        // repProdIssue.setRepRuleComment(entity.getOpenRemark());
        // repProdIssue.setRepFirstOpenDay(entity.getFirstOpenDay());
        // if (entity.getAveOpenTime() != null) {
        // repProdIssue.setRepAvgOpenCount(new BigDecimal(entity.getAveOpenTime()));
        // }
        // repProdIssue.setRepOpenBuss(entity.getOpenBuss());
        // repProdIssue.setRepOpenBussRemark(entity.getOpenBussRemark());
        // repProdIssue.setRepHolidayOnFlag(entity.getHdayOpenFlag());

        repDto.setRepProdDeclare(repProdDeclare);
        repDto.setRepProdIssue(repProdIssue);
        dtos.add(repDto);
        baseRepFinProductInfoService.newRepFinProductInfo(dtos);

        return paPrdIssueScheduleMapper.insert(entity);
    }

    /**
     * 修改产品排期信息
     * 
     * @param entity 产品排期信息对象
     * @return 影响行数
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    public int update(PrdIssueScheduleDto entity)
        throws IllegalAccessException, InvocationTargetException {
        List<BaseRepFinProductInfoDto> dtos = new ArrayList<BaseRepFinProductInfoDto>();
        BaseRepFinProductInfoDto repDto = new BaseRepFinProductInfoDto();
        // 产品申报登记
        RepProdDeclare repProdDeclare = entity.getBussRepFinProductInfoDto();
        repProdDeclare.setRepFinprodId(entity.getFinprodId());
        repProdDeclare.setRepFinprodName(entity.getFinprodName());
        repProdDeclare.setRepFinprodInsId(entity.getFinprodMarketId());
        repProdDeclare.setRepTerm(entity.getTermType());
        // 产品发行登记
        RepProdIssue repProdIssue = new RepProdIssue();
        BeanUtil.copyProperties(repProdIssue, repProdDeclare);
        repProdIssue.setRepBankProdCode(entity.getFinprodMarketId());
        repProdIssue.setRepRaiseVdate(entity.getRaiseStrdate());
        repProdIssue.setRepRaiseEnddate(entity.getRaiseEnddate());
        repProdIssue.setRepPrdVdate(entity.getVdate());
        repProdIssue.setRepPrdMdate(entity.getMdate());
        repProdIssue.setRepExpMaxYield(repProdDeclare.getRepHighYield());
        repProdIssue.setRepExpMinYield(repProdDeclare.getRepLowYield());
        // repProdIssue.setRepOpenCircle(entity.getOpenCycle());
        // if (entity.getOpenCycleOth() != null) {
        // repProdIssue.setRepOpenCircleDays(entity.getOpenCycleOth().shortValue());
        // }
        // repProdIssue.setRepRuleComment(entity.getOpenRemark());
        // repProdIssue.setRepFirstOpenDay(entity.getFirstOpenDay());
        // if (entity.getAveOpenTime() != null) {
        // repProdIssue.setRepAvgOpenCount(new BigDecimal(entity.getAveOpenTime()));
        // }
        // repProdIssue.setRepOpenBuss(entity.getOpenBuss());
        // repProdIssue.setRepOpenBussRemark(entity.getOpenBussRemark());
        // repProdIssue.setRepHolidayOnFlag(entity.getHdayOpenFlag());

        repDto.setRepProdDeclare(repProdDeclare);
        repDto.setRepProdIssue(repProdIssue);
        dtos.add(repDto);

        BaseRepFinProductDto dto = new BaseRepFinProductDto();
        List<String> repFinprodIds = new ArrayList<String>();
        repFinprodIds.add(entity.getFinprodId());
        dto.setRepFinprodIds(repFinprodIds);
        baseRepFinProductInfoService.updateRepFinProductInfo(dto, dtos);
        return paPrdIssueScheduleMapper.updateByPrimaryKey(entity);
    }

    /**
     * 查询产品模板下拉框
     * 
     * @param param 参数map
     * @return 产品模板
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdTempleteInfo(Map<String, Object> param,int page,int length) {
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        return this.queryForPageForSelectReturn(page, length, ()-> finProductSchedulingMapper.queryProdTempleteInfo(param));
    }

    /**
     * 查询产品申报方案下拉框
     * 
     * @param param 参数map
     * @return 产品申报方案
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryDeclareSchemeInfo(Map<String, Object> param) {
        return finProductSchedulingMapper.queryDeclareSchemeInfo(param);
    }

    /**
     * 根据产品金融代码查询排期信息
     * 
     * @param FinprodId 产品金融代码
     * @return 排期信息
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public PrdIssueScheduleDto queryIssueInfoByFinprodId(String finprodId)
        throws IllegalAccessException, InvocationTargetException {
        PrdIssueScheduleDto dto = new PrdIssueScheduleDto();
        PaPrdIssueSchedule issueDto = paPrdIssueScheduleMapper.selectByPrimaryKey(finprodId);
        if (issueDto != null){
            BeanUtil.copyProperties(dto, issueDto);
        }

        BussRepFinProductInfoDto bussRepFinProductInfoDto = new BussRepFinProductInfoDto();
        RepProdDeclare repProdDeclare = repProdDeclareMapper.selectByPrimaryKey(finprodId);
        if (repProdDeclare != null){
            BeanUtil.copyProperties(bussRepFinProductInfoDto, repProdDeclare);
        }

        RepProdIssue repProdIssue = repProdIssueMapper.selectByPrimaryKey(finprodId);
        if (repProdIssue != null){
            bussRepFinProductInfoDto.setRepExpAvgYield(repProdIssue.getRepExpAvgYield());
            bussRepFinProductInfoDto.setRepYieldComment(repProdIssue.getRepYieldComment());
        }

        dto.setBussRepFinProductInfoDto(bussRepFinProductInfoDto);
        return dto;
    }

    /**
     * 查询产品基本信息是否存在
     * 
     * @param finprodMarketId 产品代码
     * @param finprodAbbr 产品简称
     * @param finprodName 产品名称
     * @param finprodId 金融产品代码
     * @return 是否存在
     * @see
     * @since 1.0
     */
    public int checkProdInfoExist(String finprodMarketId, String finprodAbbr, String finprodName,
                                  String finprodId) {
        //OPEX-598 产品代码输入类似字符￥%￥%￥，系统提示产品代码已存在，提示有误 guyunfei start
        if(StringUtil.isEmpty(finprodMarketId) && StringUtil.isEmpty(finprodAbbr) && StringUtil.isEmpty(finprodName)){
            return 0;
        }
        //OPEX-598 产品代码输入类似字符￥%￥%￥，系统提示产品代码已存在，提示有误 guyunfei end
        return finProductSchedulingMapper.checkProdInfoExist(finprodMarketId, finprodAbbr,
            finprodName, finprodId, DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
    }
}
