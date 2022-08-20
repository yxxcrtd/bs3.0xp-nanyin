/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repprodinfo.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.util.SysCommDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dao.DisProdBaseInfoMapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdInfoService
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdInfoService extends CommonExportService<ProdInfoDto> implements BaseProcessService<ProdInfoDto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdInfoService.class);


    @Autowired
    ProdBasicChangeService prodBasicChangeService;

    @Autowired
    private DisProdBaseInfoMapper prodInfoMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;


    public List<ProdInfoDto> findAll(Map<String, Object> params) {
        List<ProdInfoDto> result = prodInfoMapper.searchProdList(params);
        return result;
    }


    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param params
     * @return
     */
    public Page<ProdInfoDto> findByPage(Map<String, Object> params) {
        String status = (String) params.get("status");
        if (StringUtil.isNotEmpty(status)) {
            params.put("dataStatusArr", StringUtil.splitToList(GlobalConstant.COMMA, status));
        }

        String prodRegistNo = (String) params.get("prodRegistNo");
        if (StringUtil.isNotEmpty(prodRegistNo)) {
            params.put("prodReNoArr", StringUtil.splitToList(GlobalConstant.COMMA, prodRegistNo));
        }

        String raiseType = (String) params.get("raiseType");
        if (StringUtil.isNotEmpty(raiseType)) {
            params.put("raiseTypeArr", StringUtil.splitToList(GlobalConstant.COMMA, raiseType));
        }

        String isGrade = (String) params.get("isGrade");
        if (StringUtil.isNotEmpty(isGrade)) {
            params.put("isGradeArr", StringUtil.splitToList(GlobalConstant.COMMA, isGrade));
        }

        String profitType = (String) params.get("profitType");
        if (StringUtil.isNotEmpty(profitType)) {
            params.put("profitTypeArr", StringUtil.splitToList(GlobalConstant.COMMA, profitType));
        }

        /* NYLCXP-411 产品名称字段改为多选下拉框 add by kongzhifang 2022-08-12 start */
        String prodName = (String) params.get("prodName");
        if (StringUtil.isNotEmpty(prodName)) {
            params.put("prodNameArr", StringUtil.splitToList(GlobalConstant.COMMA, prodName));
        }
        /* NYLCXP-411 产品名称字段改为多选下拉框 add by kongzhifang 2022-08-12 start */

        Page<ProdInfoDto> pages = super.queryForPageForSelectReturn(Integer.parseInt((String) params.get("page")),
                Integer.parseInt((String) params.get("length")),
                () -> prodInfoMapper.searchProdList(params));
        return pages;
    }


    /**
     * 查询产品明细信息
     *
     * @param prodId
     * @return
     */
    public ProdInfoDto selectByProdId(String prodId) {
        // return  prodInfoMapper.selectByProdId(prodId,DictCodeListExt3.信披_数据状态_有效.getValue());
        return prodInfoMapper.selectByPrimaryKey(prodId);
    }

    /**
     * 查询产品明细信息-普通产品
     *
     * @param prodId
     * @return
     */
    public ProdInfoDto selectByCommonProdId(String prodId) {
        return prodInfoMapper.selectByCommonProdId(prodId);
    }

    /**
     * 查询产品明细信息-子产品
     *
     * @param prodId
     * @return
     */
    public ProdInfoDto selectBySonProdId(String prodId) {
        return prodInfoMapper.selectBySonProdId(prodId);
    }

    /**
     * 查询正常状态的产品明细信息
     *
     * @param prodId
     * @return
     */
    public ProdInfoDto selectByProdStatus(String prodId) {
        return prodInfoMapper.selectByProdStatus(prodId, "E");
    }


    /**
     * 查询母产品下所有的子产品信息
     *
     * @param prodId
     * @return
     */
    public List<ProdInfoDto> selectListByPprodId(String prodId) {
        // return  prodInfoMapper.selectByProdId(prodId,DictCodeListExt3.信披_数据状态_有效.getValue());
        return prodInfoMapper.selectListByPprodId(prodId);
    }

    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(ProdInfoDto dto) {
        Set<String> message = new HashSet<String>();

        AjaxResult result = new AjaxResult();
        if (CollectionUtils.isNotEmpty(message)) {
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action -> {
                errorList.add(new ValidMessage(action));
            });
            result.setErrorList(errorList);
        }
        return result;
    }


    /**
     * 插入数据
     * TODO 新增的几个字段还需要再进行处理 ，暂时还没有加到数据库中
     *
     * @param dto
     */
    public void insert(ProdInfoDto dto) {

        dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        dto.setIssueStatus(DictCodeListExt1.发行状态_确认发行.getValue());
        if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsGrade())) {//是分级产品，子产品
            //保存母产品信息，判断母产品是否已存在
            ProdInfoDto existppDto = prodInfoMapper.selectByPrimaryKey(dto.getpProdId());
            if (existppDto == null) {
                ProdInfoDto ppDto = new ProdInfoDto();
                BeanUtils.copyProperties(dto, ppDto);
                ppDto.setRepProdType(DictCodeListExt5.信批产品类别_母产品.getValue());
                ppDto.setProdId(dto.getpProdId());
                ppDto.setProdName(dto.getpProdName());
                ppDto.setSetDate(dto.getpSetDate());
                ppDto.setpProdId(null);
                ppDto.setpProdName(null);
                ppDto.setpSetDate(null);
                //插入业绩比较基准信息，若“是否指数型产品”为“是”，则需要对录入的产品业绩比较基准进行截取
                if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), ppDto.getIsIndex()) && StringUtils.isNotEmpty(ppDto.getPerformCompare())) {
                    if (prodBasicChangeService.insertProdBasicChangeFromExcel(ppDto.getPerformCompare(), ppDto.getProdId())) {
                        //成功之后清空产品业绩比较基准
                        ppDto.setPerformCompare(null);
                    }
                }
                prodInfoMapper.insert(ppDto);
            }
            //保存子产品信息
            dto.setRepProdType(DictCodeListExt5.信批产品类别_子产品.getValue());
            /*dto.setProdAbbr(null);
            dto.setProdRe(null);
            dto.setRaiseStrdate(null);
            dto.setRaiseEnddate(null);
            dto.setMdate(null);
            dto.setRaiseType(null);
            dto.setIsPeriod(null);
            dto.setIsGrade(null);
            dto.setIsIndex(null);
            dto.setOpenFreq(null);
            dto.setNatureInvest(null);
            dto.setProdType(null);
            dto.setIssuingWay(null);
            dto.setAcctChnname(null);
            dto.setProfitType(null);
            dto.setPdStatus(null);
            dto.setProdSeries(null);*/

        } else {//普通产品
            /* NYLCXP-895 普通和分层指数型产品都要处理业绩比较基准 modify by kongzhifang 2022-08-19 */
            //插入业绩比较基准信息，若“是否指数型产品”为“是”，则需要对录入的产品业绩比较基准进行截取
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsIndex()) && StringUtils.isNotEmpty(dto.getPerformCompare())) {
                if (prodBasicChangeService.insertProdBasicChangeFromExcel(dto.getPerformCompare(), dto.getProdId())) {
                    //成功之后清空产品业绩比较基准
                    dto.setPerformCompare(null);
                }
            }
            dto.setRepProdType(DictCodeListExt5.信批产品类别_普通产品.getValue());
        }
        //重新插入已删除的数据
        ProdInfoDto prodInfoDto=prodInfoMapper.selectByPrimaryKey(dto.getProdId());
        if(prodInfoDto !=null && prodInfoDto.getStatus().equals("D")){
            dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            prodInfoMapper.updateByPrimaryKeySelective(dto);
        }else {
            prodInfoMapper.insert(dto);
        }


    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(ProdInfoDto dto) {
        dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        prodInfoMapper.updateByPrimaryKeySelective(dto);

        if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsGrade())) {//是分级产品，子产品
            // 更新母产品信息
            ProdInfoDto ppDto = new ProdInfoDto();
            BeanUtils.copyProperties(dto, ppDto);
            ppDto.setProdId(ppDto.getpProdId());
            ppDto.setProdName(ppDto.getpProdName());
            ppDto.setSetDate(ppDto.getpSetDate());
            ppDto.setpProdId(null);
            ppDto.setpProdName(null);
            ppDto.setpSetDate(null);
            ppDto.setRepProdType(DictCodeListExt5.信批产品类别_母产品.getValue());
            // ProdInfoDto ppDto = prodInfoMapper.selectByPrimaryKey(dto.getpProdId());
            if (dto.isSynchFlag()) { // 则同步对母产品下其余子产品的产品募集金额的值进行调整
                //ppDto.setRaiseAmt(dto.getRaiseAmt());
                prodInfoMapper.updateRaiseAmtByPprodId(dto);
            }
            // 更新母产品信息
            prodInfoMapper.updateByPrimaryKeySelective(ppDto);
        }

    }

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(ProdInfoDto entity) {
        String keyId = entity.getProdId();
        if (StringUtils.isNotEmpty(keyId)) {
            ProdInfoDto dto = new ProdInfoDto();
            dto.setProdId(keyId);
            dto.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
            prodInfoMapper.updateByPrimaryKeySelective(dto);
            /* NYLCXP-388 子产品全部删除后母产品状态更新为删除 add by kongzhifang 2022-08-12 start */
            if(StringUtil.isNotEmpty(entity.getpProdId())){
                List<ProdInfoDto> dtoList = prodInfoMapper.selectListByPprodId(entity.getpProdId());
                if(StringUtil.isEmptyList(dtoList)){
                    ProdInfoDto dto2 = new ProdInfoDto();
                    dto2.setProdId(entity.getpProdId());
                    dto2.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
                    prodInfoMapper.updateByPrimaryKeySelective(dto2);
                }
            }
            /* NYLCXP-388 子产品全部删除后母产品状态更新为删除 add by kongzhifang 2022-08-12 start */
        }
    }

    public ProdInfoDto getPrdInfo(String prodId) {
        ProdInfoDto prodInfo = prodInfoMapper.selectByPrimaryKey(prodId);
        return prodInfo;
    }


    /**
     * 导入check
     *
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
//        List<String> findList =  this.queryFinprod(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 50000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "单次最多导入50000条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        String totalMsg = "";
        List<List<String>> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() == 30) {//NYLCXP-937
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要30列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            } else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {

                    StringBuilder errorMsg = new StringBuilder("");
                    for(int j=i+1;j<objects.size();j++){
                        if(objects.get(i).get(8).equals(objects.get(j).get(8))){
                            for(int k=3;k<27;k++){
                                if(!objects.get(i).get(k).equals(objects.get(j).get(k))){
                                    errorMsg.append("存在数据与其产品主代码相同,则除子产品代码、子产品名称、子产品成立日期外，其余字段的值必须相同! ");
                                    if (StringUtil.isNotEmpty(errorMsg.toString())) {
                                        break;
                                    }
                                }
                                if (StringUtil.isNotEmpty(errorMsg.toString())) {
                                    break;
                                }
                            }
                        }
                    }
                    List<String> rowData = objects.get(i);
                    if (StringUtil.isEmpty(rowData.get(0))) {
                        errorMsg.append("子产品代码 必填！");
                    }else{
                        ProdInfoDto prodInfo = this.selectByProdId(rowData.get(0));
//                        if (prodInfo != null && StringUtils.equals(DictCodeListExt1.维护数据状态_有效.getValue(),prodInfo.getStatus())) {
//                            errorMsg.append("子产品代码 已存在");
//                        }
                    }

                    if (StringUtil.isEmpty(rowData.get(1))) {
                        errorMsg.append("子产品名称 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(2))) {
                        errorMsg.append("子产品成立日期 必填！");
                    } else if (!this.isDateStr(rowData.get(2))) {
                        errorMsg.append("子产品成立日期 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(3))) {
                        errorMsg.append("产品状态 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(4))) {
                        errorMsg.append("产品系列 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(5))) {
                        errorMsg.append("产品名称 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(6))) {
                        errorMsg.append("产品简称 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(7))) {
                        errorMsg.append("产品登记编码 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(8))) {
                        errorMsg.append("产品主代码 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(9))) {//
                        errorMsg.append("募集起始日期 必填！");
                    } else if (!this.isDateStr(rowData.get(9))) {
                        errorMsg.append("募集起始日期 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(10))) {
                        errorMsg.append("募集结束日期 必填！");
                    } else if (!this.isDateStr(rowData.get(10))) {
                        errorMsg.append("募集结束日期 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(11))) {
                        errorMsg.append("产品募集金额 必填！");
                    } else if (!isNumber(rowData.get(11))) {
                        errorMsg.append("产品募集金额 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(12))) {
                        errorMsg.append("产品成立日期 必填！");
                    } else if (!this.isDateStr(rowData.get(12))) {
                        errorMsg.append("产品成立日期 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(13))) {
                        errorMsg.append("产品到期日 必填！");
                    } else if (!this.isDateStr(rowData.get(13))) {
                        errorMsg.append("产品到期日 格式不正确！");
                    }
                    if (StringUtil.isEmpty(rowData.get(14))) {
                        errorMsg.append("募集方式 必填！");
                    }

                    if (DateUtil.compareDate(rowData.get(10),rowData.get(9)) < 0
                            || DateUtil.compareDate(rowData.get(10),rowData.get(2)) > 0
                            || DateUtil.compareDate(rowData.get(10),rowData.get(12)) > 0) {
                        errorMsg.append("募集结束日期必须大于等于募集起始日期，且必须小于子产品成立日期和产品成立日！");
                    }
                    if (DateUtil.compareDate(rowData.get(13),rowData.get(2)) < 0
                            || DateUtil.compareDate(rowData.get(13),rowData.get(12)) < 0 ) {
                        errorMsg.append("产品到期日必须大于产品成立日及子产品成立日期！");
                    }

                    if (StringUtils.equals(DictCodeListExt5.信披募集方式_公募.getCodeName(), rowData.get(14))
                            && rowData.get(7).length() != 14) {
                        errorMsg.append("募集方式为公募,产品登记编码为14位");
                    }
                    if (StringUtils.equals(DictCodeListExt5.信披募集方式_私募.getCodeName(), rowData.get(14))
                            && rowData.get(7).length() != 15) {
                        errorMsg.append("募集方式为私募,产品登记编码为15位");
                    }

                    if (StringUtil.isEmpty(rowData.get(15))) {
                        errorMsg.append("产品类型 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(16))) {
                        errorMsg.append("发行方式 必填！");
                    }

                    if (StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), rowData.get(16))
                            && StringUtils.isBlank(rowData.get(17))) {
                        errorMsg.append("发行方式为开放式，开放频率必填！");
                    }
                    if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), rowData.get(16))
                            && StringUtils.isNotBlank(rowData.get(17))) {
                        errorMsg.append("发行方式不是开放式，开放频率不需要填写！");
                    }
                    /*if (StringUtil.isEmpty(rowData.get(17))){
                        errorMsg.append("开放频率 必填！");
                    }*/
                    /*if (StringUtil.isEmpty(rowData.get(18))){
                        errorMsg.append("固定持有天数 必填！");
                    }*/
                    if (StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), rowData.get(15))
                            && StringUtils.isNotEmpty(rowData.get(17))
                            && StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), rowData.get(17))
                            && StringUtils.isBlank(rowData.get(18))) {
                        errorMsg.append("开放频率为日开且产品类型为净值型,固定持有天数必填");
                    } /*else {
                        errorMsg.append("开放频率不为日开且产品类型不为净值型,固定持有天数不需要填写");
                    }*/

                    /*if (!StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), rowData.get(15))
                            || (StringUtils.isNotEmpty(rowData.get(17)) && !StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), rowData.get(17)))
                            && StringUtils.isNotEmpty(rowData.get(18))) {
                        errorMsg.append("开放频率不为日开或产品类型不为净值型,固定持有天数不需要填写");
                    }*/

                    if (StringUtil.isNotEmpty(rowData.get(18)) && !isNumber(rowData.get(18))) {
                        errorMsg.append("固定持有天数 格式不正确，请输入数字！");
                    }
                    if (StringUtil.isEmpty(rowData.get(19))) {
                        errorMsg.append("产品运作方式 必填！");
                    }
                    if (StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), rowData.get(16))
                            && !StringUtils.equals(DictCodeListExt5.产品运作方式_开放式净值型.getCodeName(), rowData.get(19))
                            && !StringUtils.equals(DictCodeListExt5.产品运作方式_开放式非净值型.getCodeName(), rowData.get(19))) {
                        errorMsg.append("发行方式为开放式，产品运作方式只能选择“开放式净值型”或“开放式非净值型”！");

                    }
                    if (StringUtils.equals(DictCodeListExt5.发行方式_封闭式.getCodeName(), rowData.get(16))
                            && !StringUtils.equals(DictCodeListExt5.产品运作方式_封闭式净值型.getCodeName(), rowData.get(19))
                            && !StringUtils.equals(DictCodeListExt5.产品运作方式_封闭式非净值型.getCodeName(), rowData.get(19))) {
                        errorMsg.append("若发行方式为封闭式，“产品运作方式只能选择“封闭式净值型”或“封闭式非净值型 ！");

                    }


                    if (StringUtil.isEmpty(rowData.get(20))) {
                        errorMsg.append("是否是客户周期型产品 必填！");
                    }

                    if (StringUtil.isEmpty(rowData.get(21))) {
                        errorMsg.append("是否是分级产品 必填！");
                    }

                    if (StringUtils.equals(DictCodeListExt1.是或否_是.getCodeName(), rowData.get(21))
                            && StringUtils.equals(rowData.get(0), rowData.get(8))) {
                        errorMsg.append("分级产品，子产品代码和产品主代码不能一样");
                    }
                    if (StringUtils.equals(DictCodeListExt1.是或否_否.getCodeName(), rowData.get(21))){
                        /*if (!StringUtils.equals(rowData.get(0), rowData.get(8))) {
                            return new AjaxResult("非分级产品，子产品代码和产品主代码需要一致", MessageType.错误消息);
                        }*/
                        // NYLCXP-246 产品基本信息，非分级产品新建时存在错误check modify by kongzhifang 2022-08-12 start
                        /*if (!StringUtils.equals(rowData.get(1), rowData.get(5))) {
                            errorMsg.append("非分级产品，子产品名称和产品名称需要一致");
                        }*/
                        // NYLCXP-246 产品基本信息，非分级产品新建时存在错误check modify by kongzhifang 2022-08-12 start
                        if (!StringUtils.equals(rowData.get(2), rowData.get(12))) {
                            errorMsg.append("非分级产品，子产品成立日期和产品成立日期需要一致");
                        }
                    }

                    if (StringUtil.isEmpty(rowData.get(22))) {
                        errorMsg.append("是否是指数型产品 必填！");
                    }
                    /* add by kongzhifang NYLCXP-912 当“是否指数型产品”为“是”的时候，业绩基准需要加个必填校验 2022-08-19 */
                    if (StringUtils.equals(DictCodeListExt1.是或否_是.getCodeName(), rowData.get(22))
                            && StringUtil.isEmpty(rowData.get(23))) {
                        errorMsg.append("指数型产品，产品业绩比较基准 必填！");
                    }
                   /* if (StringUtil.isEmpty(rowData.get(23))){
                        errorMsg.append("产品业绩比较基准 必填！");
                    }*/
                    if (StringUtil.isEmpty(rowData.get(24))) {
                        errorMsg.append("投资性质 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(25))) {
                        errorMsg.append("产品托管人名称 必填！");
                    }
                    if (StringUtil.isEmpty(rowData.get(26))) {
                        errorMsg.append("托管部门 必填！");
                    }

                    // 现管类产品校验
                    if (StringUtils.equals(DictCodeListExt5.信披产品类型_现管类产品.getCodeName(), rowData.get(15))) {
                        if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), rowData.get(16))) {
                            errorMsg.append("产品类型为“现管类产品”时，发行方式为“开放式”！ ");
                        }
                        if (!StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), rowData.get(17))) {
                            errorMsg.append("产品类型为“现管类产品”时，开放频率为“日开”！ ");
                        }
                        if (!StringUtils.equals(DictCodeListExt5.产品运作方式_开放式非净值型.getCodeName(), rowData.get(19))) {
                            errorMsg.append("产品类型为“现管类产品”时，产品运作方式为“开放式非净值型”！ ");
                        }
                    }
                    if (StringUtil.isNotEmpty(errorMsg.toString())) {
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第" + (i + 1) + "行数据：" + errorMsg.toString());
                    }
                }

                if (StringUtil.isNotEmpty(returnerrorMsg.toString())) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //子产品代码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat) {
                        repeatNum++;
                    } else {
                        jsonArrays.add(list1);
                    }
                }
            }
        }
        if (repeatNum > 0) {
            totalMsg += "导入文件存在" + String.valueOf(repeatNum) + "条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        String tempList = (String) resultMap.get("correctData");
        ProdInfoDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProdInfoDto> selectedItem = dto.getSelectedItem();
        List<ProdInfoDto>  existData = new ArrayList<>();
        for (ProdInfoDto temp:selectedItem) {
            if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))){
                existData.add(temp);
            }
        }
        if(existData.size() > 0){
            resultMap.put("confirmMsg2", "有重复信息，是否覆盖？");
        }
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ProdInfoDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProdInfoDto> selectedItem = dto.getSelectedItem();
        List<ProdInfoDto> existData = new ArrayList<>();
        List<ProdInfoDto> newData = new ArrayList<>();
        List<ProdInfoDto> existDelData = new ArrayList<>();
        // for (ProdInfoDto temp : selectedItem) {
        //     //数据已存在 【产品编码 相同】，数据被过滤。
        //     ProdInfoDto prodInfo = this.selectByProdStatus(temp.getProdId());
        //     if (prodInfo == null) {
        //         temp.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        //         newData.add(temp);
        //     }
        // }
        // NYLCXP-580 两次判断都为空会导致newData中重复塞入数据 modify by kongzhifang 2022-08-11
        for (ProdInfoDto temp:selectedItem) {
            if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))){
                existData.add(temp);
            }
            else if(!StringUtil.isNotEmpty(this.selectByProdStatus(temp.getProdId()))) {
                temp.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                newData.add(temp);
            }
        }
        //更新操作
        for (ProdInfoDto existDataDto : existData) {
            //this.doComplete(existDataDto, OperationType.MODIFY);
            this.update(existDataDto);
        }

        //插入操作
        for (ProdInfoDto newDataDto : newData
        ) {
            this.insert(newDataDto);
            //this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        ajaxResult.setData("noInfoShowOut");
        ajaxResult.setMessage(getMessage("FW_I_0002"), MessageType.提示消息);
        return ajaxResult;
    }


    public ProdInfoDto getValNetAssetQuotationInfoListData(String tempList) {
        ProdInfoDto dto = new ProdInfoDto();
        // 行情信息列表
        List<ProdInfoDto> prodBonusplanDtoList = new ArrayList<>();
        // dto.setSelectedItem(prodBonusplanDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            ProdInfoDto prodDto = new ProdInfoDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                //子产品代码
                prodDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                //子产品名称
                prodDto.setProdName(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                //子产品成立日期
                prodDto.setSetDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                //产品状态
                prodDto.setPdStatus(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品状态, rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                //产品系列
                prodDto.setProdSeries(rowData.get(4).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                //产品名称
                prodDto.setpProdName(rowData.get(5).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                //产品简称
                prodDto.setProdAbbr(rowData.get(6).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                //产品登记编码
                prodDto.setProdRe(rowData.get(7).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(8))) {
                //产品主代码
                prodDto.setpProdId(rowData.get(8).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(9))) {//
                //募集起始日期
                prodDto.setRaiseStrdate(DateUtil.parse(rowData.get(9).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(10))) {
                //募集结束日期
                prodDto.setRaiseEnddate(DateUtil.parse(rowData.get(10).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(11))) {
                //产品募集金额
                prodDto.setRaiseAmt(new BigDecimal(rowData.get(11).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(12))) {
                //产品成立日期
                prodDto.setpSetDate(DateUtil.parse(rowData.get(12).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(13))) {
                //产品到期日期
                prodDto.setMdate(DateUtil.parse(rowData.get(13).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(14))) {
                //募集方式
                prodDto.setRaiseType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_募集方式, rowData.get(14).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(15))) {
                //产品类型
                prodDto.setProdType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品类型, rowData.get(15).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(16))) {
                //发行方式
                prodDto.setIssuingWay(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.发行方式_信批, rowData.get(16).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(17))) {
                //开放频率
                prodDto.setOpenFreq(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.开放频率, rowData.get(17).toString()));
                if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), rowData.get(16).toString())) {
                    // 发行方式不为开放式，开放频率不必填");
                    prodDto.setOpenFreq(null);
                }
            }
            if (StringUtil.isNotEmpty(rowData.get(18))) {
                //固定持有天数
                prodDto.setHoldday(new BigDecimal(rowData.get(18).toString()));
                // 固定持有天数”字段，当产品的开放频率为“日开”且“产品类型”为“净值型”时必填，否则不可填
                if (!StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), rowData.get(15).toString())
                        && StringUtil.isNotEmpty(rowData.get(17))
                        && !StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), rowData.get(17).toString())
                        && StringUtil.isNotEmpty(rowData.get(18))) {
                    prodDto.setHoldday(null);
                }

            }
            if (StringUtil.isNotEmpty(rowData.get(19))) {
                //产品运作方式
                prodDto.setProfitType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品运作方式, rowData.get(19).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(20))) {
                //是否是客户周期型产品
                prodDto.setIsPeriod(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, rowData.get(20).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(21))) {
                //是否是分级产品
                prodDto.setIsGrade(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, rowData.get(21).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(22))) {
                //是否是指数型产品
                prodDto.setIsIndex(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, rowData.get(22).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(23))) {
                //产品业绩比较基准
                prodDto.setPerformCompare(rowData.get(23).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(24))) {
                //投资性质
                prodDto.setNatureInvest(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_投资性质, rowData.get(24).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(25))) {
                //产品托管人名称
                prodDto.setAcctChnname(rowData.get(25).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(26))) {
                //托管部门
                prodDto.setTrustpart(rowData.get(26).toString());
            }
            prodBonusplanDtoList.add(prodDto);
        }
        dto.setSelectedItem(prodBonusplanDtoList);
        return dto;
    }

    @Override
    public void doComplete(ProdInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        entity.setStatus(null);
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            /* entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());*/
            this.insert(entity);
            // 更新行情代码信息
            prodBasicChangeService.updateProdBasicChangeList(entity.getBasicChangeList(), entity.getpProdId(), entity.getIsIndex());
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
            // 更新行情代码信息
            prodBasicChangeService.updateProdBasicChangeList(entity.getBasicChangeList(), entity.getpProdId(), entity.getIsIndex());
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);
        }
    }


    public void updateIssuingStatus(ProdInfoDto dto) {
        //如果子产品发布状态为发布成功，则更新母产品的发布状态为:发布成功
        if (StringUtils.equals(DictCodeListExt5.信批产品类别_子产品.getValue(), dto.getRepProdType())
                && StringUtils.equals(DictCodeListExt1.发行状态_发行成功.getValue(), dto.getIssuingStatusNew())) {
            ProdInfoDto ppdto = prodInfoMapper.selectByPrimaryKey(dto.getpProdId());
            if (!StringUtils.equals(DictCodeListExt1.发行状态_发行成功.getValue(), ppdto.getIssueStatus())) {
                ppdto.setIssueStatus(DictCodeListExt1.发行状态_发行成功.getValue());
                prodInfoMapper.updateByPrimaryKey(ppdto);
            }
        }
        prodInfoMapper.updateIssuingStatus(dto);
    }

    public List<ProdInfoDto> queryForList(String prodIdStr) {
        return prodInfoMapper.queryForList(prodIdStr.split(","));
    }

    public Page<SelectOptionDto> queryPprodIds(Map<String, Object> map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> prodInfoMapper.queryPprodIds(map));
        return result;
    }

    /**
     * 获取所有的正常状态的产品信息，包含母产品和子产品，支持根据产品代码和产品名称 进行模糊查询
     * 支持筛选只查询 母产品 sonFlag 标识不为空
     * @param paraMap
     * @return
     */
    public Page<SelectOptionDto> queryPprodName(Map<String, Object> paraMap){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> prodInfoMapper.queryPprodName(paraMap));
        return result;
    }
}
