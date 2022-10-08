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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.util.SysCommDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dao.DisProdBaseInfoMapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


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


    public ProdInfoDto selectByPProdId(String prodId) {
        // return  prodInfoMapper.selectByProdId(prodId,DictCodeListExt3.信披_数据状态_有效.getValue());
        return prodInfoMapper.selectByPrimaryKeyPT(prodId);
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
        //NYXPUAT-3:新建导入修改时，如果开放频率为空，库里需要存空格，不能存null add by zhouqiang 20220824
        dto.setOpenFreq(StringUtil.isEmpty(dto.getOpenFreq()) ? " ":dto.getOpenFreq());
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
            dto.setProdName(dto.getpProdName());
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
        //NYXPUAT-3:新建导入修改时，如果开放频率为空，库里需要存空格，不能存null add by zhouqiang 20220824
        dto.setOpenFreq(StringUtil.isEmpty(dto.getOpenFreq())  ? " ":dto.getOpenFreq());
        //是分级产品，子产品
        if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsGrade())) {
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
            //插入业绩比较基准信息，若“是否指数型产品”为“是”，则需要对录入的产品业绩比较基准进行截取
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), ppDto.getIsIndex()) && StringUtils.isNotEmpty(ppDto.getPerformCompare())) {
                if (prodBasicChangeService.insertProdBasicChangeFromExcel(ppDto.getPerformCompare(), ppDto.getProdId())) {
                    //成功之后清空产品业绩比较基准
                    ppDto.setPerformCompare(null);
                }
            }
            // ProdInfoDto ppDto = prodInfoMapper.selectByPrimaryKey(dto.getpProdId());
            if (dto.isSynchFlag()) { // 则同步对母产品下其余子产品的产品募集金额的值进行调整
                //ppDto.setRaiseAmt(dto.getRaiseAmt());
                prodInfoMapper.updateRaiseAmtByPprodId(dto);
            }
            // 更新母产品信息
            prodInfoMapper.updateByPrimaryKeySelective(ppDto);
        } else {
            //插入业绩比较基准信息，若“是否指数型产品”为“是”，则需要对录入的产品业绩比较基准进行截取
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsIndex()) && StringUtils.isNotEmpty(dto.getPerformCompare())) {
                if (prodBasicChangeService.insertProdBasicChangeFromExcel(dto.getPerformCompare(), dto.getProdId())) {
                    //成功之后清空产品业绩比较基准
                    dto.setPerformCompare(null);
                }
            }
            dto.setProdName(dto.getpProdName());
            dto.setRepProdType(DictCodeListExt5.信批产品类别_普通产品.getValue());
        }
        prodInfoMapper.updateByPrimaryKeySelective(dto);

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
        AjaxResult webResult = new AjaxResult();
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> tempList = ExcelUtil.getTempList(fileName);
        if (StringUtil.isEmptyList(tempList) || CollectionUtils.isEmpty(tempList)) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        if (null == tempList.get(0) || tempList.get(0).size() != 26) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 26), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            if (dataList.size() > 50000) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<ProdInfoDto> entities = this.convertToBean(dataList);
            List<ProdInfoDto> data = this.selectProdInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<ProdInfoDto>> prodInfoMap = entities.stream().collect(
                        JoyinCollectors.groupingBy(ProdInfoDto::getProdId));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (ProdInfoDto prodInfoDto : data) {
                    List<ProdInfoDto> listTemp = prodInfoMap.get(prodInfoDto.getProdId());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;
    }

    /**
     * 分批查询
     * @param dataList
     * @return
     */
    private List<ProdInfoDto> selectProdInfoByKey(List<ProdInfoDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return prodInfoMapper.selectProdList(dataList);
        }
        // 导入大于1000时，返回值
        List<ProdInfoDto> equtiyList = new ArrayList<>();

        List<ProdInfoDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<ProdInfoDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = prodInfoMapper.selectProdList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = prodInfoMapper.selectProdList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    /**
     * excel数据转为java对象
     * @return
     */
    private List<ProdInfoDto> convertToBean(List<List<String>> dataList) {
        List<ProdInfoDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            ProdInfoDto entity = new ProdInfoDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
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

    //NYLCXP-977 产品募集金额、最低持有天数只能为大于等于0的整数 add by zhouqiang
    public static boolean isIntegerNumber(String str) {
        String reg = "^[1-9]\\d*$";
        return str.matches(reg) || "0".equals(str);
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<ProdInfoDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    ProdInfoDto prodInfo = this.selectByProdId(item.getProdId());
                    if (null != prodInfo) {
                        this.update(item);
                    } else {
                        this.insert(item);
                    }
                });
            }
            return checkResult;
        } else {
            List<ExcelValidDto> checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }
    }

    private List<ExcelValidDto> convertToEntitys(List<List<String>> dataList,
                                                List<ProdInfoDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> prodMap = new HashMap<>();
        Map<String,String>  messagemap=new HashMap<>();
        //查询产品登记编码
        List<ProdInfoDto>   reprodList=prodInfoMapper.selectReAll();
        //产品登记编码作key，父产品代码作为value
        Map<String,String>  frepMap=(reprodList.stream().filter(s -> s.getpProdId()!=null).collect(Collectors.toList())).
                stream().collect(JoyinCollectors.toMap(x -> x.getProdRe(), x -> x.getpProdId()));
        //产品登记编码作key，母产品的产品代码作为value
        Map<String,String>  repMap=(reprodList.stream().filter(s -> s.getpProdId()==null).collect(Collectors.toList())).
                stream().collect(JoyinCollectors.toMap(x -> x.getProdRe(), x -> x.getProdId()));

        //校验文件里产品登记编码重复的容器
        Map<String,String>  remap=new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            if (null == list || list.size() != 26) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 26)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if(messagemap.containsKey(dataList.get(i).get(8))){
                message.add("存在数据与其产品主代码相同,则除子产品代码、子产品名称、子产品成立日期外，其余字段的值必须相同! ");
            }else{
                for (int j = i + 1; j < dataList.size(); j++) {
                    if (dataList.get(i).get(8).equals(dataList.get(j).get(8))) {
                        for (int k = 3; k < 26; k++) {
                            if (!dataList.get(i).get(k).equals(dataList.get(j).get(k))) {
                                message.add("存在数据与其产品主代码相同,则除子产品代码、子产品名称、子产品成立日期外，其余字段的值必须相同! ");
                                messagemap.put(dataList.get(i).get(8),"存在数据与其产品主代码相同,则除子产品代码、子产品名称、子产品成立日期外，其余字段的值必须相同! ");
                                if (CollectionUtils.isNotEmpty(message)) {
                                    break;
                                }
                            }
                            if (CollectionUtils.isNotEmpty(message)) {
                                break;
                            }
                        }
                    }
                }
            }
            // 子产品代码
            if (StringUtil.isEmpty(list.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            }
            // 子产品名称
            if (StringUtil.isEmpty(list.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品名称"));
            }
            // 子产品成立日期
            if (StringUtil.isEmpty(list.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品成立日期"));
            } else if (!isDateStr(list.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "子产品成立日期"));
            }
            // 产品状态
            if (StringUtil.isEmpty(list.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品状态"));
            }
            // 产品系列
            if (StringUtil.isEmpty(list.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品系列"));
            }
            // 产品名称
            if (StringUtil.isEmpty(list.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品名称"));
            }
            // 产品简称
            if (StringUtil.isEmpty(list.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品简称"));
            }
            // 产品登记编码
            if (StringUtil.isEmpty(list.get(7))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            }
//            ProdInfoDto dto = new ProdInfoDto();
//            dto.setpProdId(list.get(8));
//            dto.setProdRe(list.get(7));
//            if (this.hasReapetData(dto)>0){
//                message.add("该主产品代码已存在相同的产品登记代码");
//            }
            // 产品主代码
            if (StringUtil.isEmpty(list.get(8))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            }else{
//                if(StringUtils.equals(DictCodeListExt1.是或否_否.getCodeName(), list.get(21))){
//                    //查詢是否存在相同的产品主代码信息，若存在則給出提示
//                    ProdInfoDto prodInfoDto = this.getParentProdInfo(list.get(8));
//                    if(prodInfoDto != null){
//                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0029", "产品主代码"));
//                    }
//                }else{
//                    //查询是否存在普通、相同的产品主代码信息，若存在則給出提示
//                    List<ProdInfoDto> prodInfoDtoList = this.getPtParentProdInfo(list.get(8));
//                    if(prodInfoDtoList!=null && prodInfoDtoList.size()>0){
//                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0029", "产品主代码"));
//                    }
//                }
            }
            /**校验产品登记编码重复性 start*/
            if(frepMap.keySet().contains(list.get(7))){
                //主产品代码不一致
                if(!StringUtil.equals(list.get(8),frepMap.get(list.get(7)))){
                    message.add("产品登记编码具有唯一性， 产品登记编码跟产品主代码是唯一对应的。");
                }
            }
//            if(repMap.keySet().contains(list.get(7))){
//                //母产品代码不一致
//                if(!StringUtil.equals(list.get(0),repMap.get(list.get(7)))){
//                    if(!message.contains("产品登记编码具有唯一性， 产品登记编码跟产品主代码是唯一对应的。")){
//                        message.add("产品登记编码具有唯一性， 产品登记编码跟产品主代码是唯一对应的。");
//                    }
//                }
//            }
            if(StringUtil.isNotEmpty(list.get(7))&&
                    StringUtil.isNotEmpty(list.get(8))){
                if(remap.keySet().contains(list.get(7))){
                    if(!StringUtil.equals(list.get(8),remap.get(list.get(7)))){
                        message.add("产品登记编码重复");
                    }
                }else {
                    remap.put(list.get(7),list.get(8));
                }
            }
            /**校验产品登记编码重复性 end*/
            // 募集起始日期
            if (StringUtil.isEmpty(list.get(9))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "募集起始日期"));
            } else if (!isDateStr(list.get(9))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "募集起始日期"));
            }
            // 募集结束日期
            if (StringUtil.isEmpty(list.get(10))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "募集结束日期"));
            } else if (!isDateStr(list.get(10))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "募集结束日期"));
            }
            // 产品募集金额
            if (StringUtil.isEmpty(list.get(11))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品募集金额"));
            } else if (!isIntegerNumber(list.get(11))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0031", "产品募集金额"));
            }
            // 产品成立日期
            if (StringUtil.isEmpty(list.get(12))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品成立日期"));
            } else if (!isDateStr(list.get(12))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "产品成立日期"));
            }
            // 产品到期日
            if (StringUtil.isEmpty(list.get(13))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品到期日"));
            } else if (!isDateStr(list.get(13))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "产品到期日"));
            }
            // 募集方式
            if (StringUtil.isEmpty(list.get(14))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "募集方式"));
            }
            if (isDateStr(list.get(10)) && isDateStr(list.get(9))
                    && isDateStr(list.get(2)) && isDateStr(list.get(12))
                    && (DateUtil.compareDate(list.get(10),list.get(9)) < 0
                    || DateUtil.compareDate(list.get(10),list.get(2)) > 0
                    || DateUtil.compareDate(list.get(10),list.get(12)) > 0)) {
                message.add("募集结束日期必须大于等于募集起始日期，且必须小于子产品成立日期和产品成立日。");
            }
            if (isDateStr(list.get(13)) && isDateStr(list.get(12)) && isDateStr(list.get(2))
                    && (DateUtil.compareDate(list.get(13),list.get(2)) < 0
                    || DateUtil.compareDate(list.get(13),list.get(12)) < 0 )) {
                message.add("产品到期日必须大于产品成立日及子产品成立日期。");
            }
            if (StringUtils.equals(DictCodeListExt5.信披募集方式_公募.getCodeName(), list.get(14))
                    && list.get(7).length() != 14) {
                message.add("募集方式为公募,产品登记编码为14位");
            } else if (StringUtils.equals(DictCodeListExt5.信披募集方式_私募.getCodeName(), list.get(14))
                    && list.get(7).length() != 15) {
                message.add("募集方式为私募,产品登记编码为15位");
            }
            //产品类型
            if (StringUtil.isEmpty(list.get(15))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品类型"));
            }

            // 发行方式
            if (StringUtil.isEmpty(list.get(16))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "发行方式"));
            }
            if (StringUtil.equals(list.get(16),"开放式") && StringUtil.isNotEmpty(list.get(19))) {
                if (list.get(19).contains("封闭式")) {
                    message.add("发行方式是开放式时，产品运作方式不能是封闭式。");
                }
            }
            if (StringUtil.equals(list.get(16),"封闭式") && StringUtil.isNotEmpty(list.get(19))) {
                if (list.get(19).contains("开放式")) {
                    message.add("发行方式是封闭式时，产品运作方式不能是开放式。");
                }
            }
            if (StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), list.get(16))
                    && StringUtils.isBlank(list.get(17))) {
                message.add("发行方式为开放式，开放频率必填。");
            } else if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), list.get(16))
                    && StringUtils.isNotBlank(list.get(17))) {
                message.add("发行方式不是开放式，开放频率不需要填写。");
            }
            if (StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), list.get(15))
                    && StringUtils.isEmpty(list.get(18))
                    && StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), list.get(17))
                    && StringUtils.isEmpty(list.get(18))) {
                message.add("开放频率为日开且产品类型为净值型，最低持有天数必填。");
            }

            //校验最低持有天数是否必填
//            if (StringUtil.equals(list.get(15),"现管类产品") && StringUtil.isNotEmpty(list.get(18))) {
//                message.add("产品类型是现管类产品时，最低持有天数不可填。");
//            }
            if ((!StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), list.get(15))
                    || !StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), list.get(17)))
                    && StringUtil.isNotEmpty(list.get(18))) {
                message.add("产品的开放频率不为日开或者产品类型不为净值型时，最低持有天数不可填。");
            }

            //校验产品运作方式
            if (StringUtil.equals(list.get(15),"净值型产品") && StringUtil.isNotEmpty(list.get(19))) {
                if (list.get(19).contains("非")) {
                    message.add("产品类型是净值型产品时，产品运作方式不能是非净值型。");
                }
            }

            if (StringUtil.isNotEmpty(list.get(18)) && !isIntegerNumber(list.get(18))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0031", "最低持有天数"));
            }

            if (StringUtil.isEmpty(list.get(19))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品运作方式"));
            }
            if (StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), list.get(16))
                    && !StringUtils.equals(DictCodeListExt5.产品运作方式_开放式净值型.getCodeName(), list.get(19))
                    && !StringUtils.equals(DictCodeListExt5.产品运作方式_开放式非净值型.getCodeName(), list.get(19))) {
                message.add("发行方式为开放式，产品运作方式只能选择“开放式净值型”或“开放式非净值型”。");

            }
            if (StringUtils.equals(DictCodeListExt5.发行方式_封闭式.getCodeName(), list.get(16))
                    && !StringUtils.equals(DictCodeListExt5.产品运作方式_封闭式净值型.getCodeName(), list.get(19))
                    && !StringUtils.equals(DictCodeListExt5.产品运作方式_封闭式非净值型.getCodeName(), list.get(19))) {
                message.add("发行方式为封闭式，产品运作方式只能选择“封闭式净值型”或“封闭式非净值型“。");
            }
            if (StringUtil.isEmpty(list.get(20))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "是否是客户周期型产品"));
            }
            if (StringUtil.isEmpty(list.get(21))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "是否是分级产品"));
            }
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getCodeName(), list.get(21))
                    && StringUtils.equals(list.get(0), list.get(8))) {
                message.add("分级产品，子产品代码和产品主代码不能一样");
            }
            if (StringUtils.equals(DictCodeListExt1.是或否_否.getCodeName(), list.get(21))){
                if (!StringUtils.equals(list.get(2), list.get(12))) {
                    message.add("非分级产品，子产品成立日期和产品成立日期需要一致。");
                }
            }

            if (StringUtil.isEmpty(list.get(22))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "是否是指数型产品"));
            }
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getCodeName(), list.get(22))
                    && StringUtil.isEmpty(list.get(23))) {

            }
            //校验产品业绩比较基准格式
//            if (StringUtils.equals(DictCodeListExt1.是或否_是.getCodeName(), list.get(22))
//                    && StringUtil.isNotEmpty(list.get(23))) {
//                List<ProdBasicChangeDto> changeList = prodBasicChangeService.analyseDate(list.get(23));
//                if (CollectionUtils.isEmpty(changeList)) {
//                    message.add("产品业绩比较基准格式不正确。");
//                }
//            }
            if (StringUtil.isEmpty(list.get(23))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "投资性质"));
            }
            if (StringUtil.isEmpty(list.get(24))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品托管人名称"));
            }
            if (StringUtil.isEmpty(list.get(25))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "托管部门"));
            }

            // 现管类产品校验
            if (StringUtils.equals(DictCodeListExt5.信披产品类型_现管类产品.getCodeName(), list.get(15))) {
                if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), list.get(16))) {
                    message.add("产品类型为“现管类产品”时，发行方式为“开放式”。");
                }
                if (!StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), list.get(17))) {
                    message.add("产品类型为“现管类产品”时，开放频率为“日开”。");
                }
                if (!StringUtils.equals(DictCodeListExt5.产品运作方式_开放式净值型.getCodeName(), list.get(19))) {
                    message.add("产品类型为“现管类产品”时，产品运作方式为“开放式净值型”。");
                }
            }
            if (StringUtil.isNotEmpty(list.get(0)) && prodMap.containsKey(list.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            if (StringUtil.isNotEmpty(list.get(0))) {
                prodMap.put(list.get(0), list);
            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(0),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(list));
            }
        }
        return checkResult;
    }

    private ProdInfoDto convertToEntity(List<String> list) {
        ProdInfoDto entity = new ProdInfoDto();
        if (StringUtil.isNotEmpty(list.get(0))) {
            //子产品代码
            entity.setProdId(list.get(0));
        }
        if (StringUtil.isNotEmpty(list.get(1))) {
            //子产品名称
            entity.setProdName(list.get(1));
        }
        if (StringUtil.isNotEmpty(list.get(2))) {
            //子产品成立日期
            entity.setSetDate(DateUtil.parse(list.get(2)));
        }
        if (StringUtil.isNotEmpty(list.get(3))) {
            //产品状态
            entity.setPdStatus(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品状态, list.get(3)));
        }
        if (StringUtil.isNotEmpty(list.get(4))) {
            //产品系列
            entity.setProdSeries(list.get(4));
        }
        if (StringUtil.isNotEmpty(list.get(5))) {
            //产品名称
            entity.setpProdName(list.get(5));
        }
        if (StringUtil.isNotEmpty(list.get(6))) {
            //产品简称
            entity.setProdAbbr(list.get(6));
        }
        if (StringUtil.isNotEmpty(list.get(7))) {
            //产品登记编码
            entity.setProdRe(list.get(7));
        }
        if (StringUtil.isNotEmpty(list.get(8))) {
            //产品主代码
            entity.setpProdId(list.get(8));
        }
        if (StringUtil.isNotEmpty(list.get(9))) {//
            //募集起始日期
            entity.setRaiseStrdate(DateUtil.parse(list.get(9)));
        }
        if (StringUtil.isNotEmpty(list.get(10))) {
            //募集结束日期
            entity.setRaiseEnddate(DateUtil.parse(list.get(10)));
        }
        if (StringUtil.isNotEmpty(list.get(11))) {
            //产品募集金额
            entity.setRaiseAmt(new BigDecimal(list.get(11)));
        }
        if (StringUtil.isNotEmpty(list.get(12))) {
            //产品成立日期
            entity.setpSetDate(DateUtil.parse(list.get(12)));
        }
        if (StringUtil.isNotEmpty(list.get(13))) {
            //产品到期日期
            entity.setMdate(DateUtil.parse(list.get(13)));
        }
        if (StringUtil.isNotEmpty(list.get(14))) {
            //募集方式
            entity.setRaiseType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_募集方式, list.get(14)));
        }
        if (StringUtil.isNotEmpty(list.get(15))) {
            //产品类型
            entity.setProdType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品类型, list.get(15)));
        }
        if (StringUtil.isNotEmpty(list.get(16))) {
            //发行方式
            entity.setIssuingWay(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.发行方式_信批, list.get(16)));
        }
        if (StringUtil.isNotEmpty(list.get(17))) {
            //开放频率
            entity.setOpenFreq(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.开放频率, list.get(17)));
            if (!StringUtils.equals(DictCodeListExt5.发行方式_开放式.getCodeName(), list.get(16))) {
                // 发行方式不为开放式，开放频率不必填");
                entity.setOpenFreq(null);
            }
        }
        //NYXPUAT-3:新建导入修改时，如果开放频率为空，库里需要存空格，不能存null add by zhouqiang 20220824
        entity.setOpenFreq(StringUtil.isEmpty(entity.getOpenFreq()) ? " ":entity.getOpenFreq());
        if (StringUtil.isNotEmpty(list.get(18))) {
            //最低持有天数
            entity.setHoldday(new BigDecimal(list.get(18)));
            // 最低持有天数”字段，当产品的开放频率为“日开”且“产品类型”为“净值型”时必填，否则不可填
            if (!StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getCodeName(), list.get(15))
                    && StringUtil.isNotEmpty(list.get(17))
                    && !StringUtils.equals(DictCodeListExt5.开放频率_日开.getCodeName(), list.get(17))
                    && StringUtil.isNotEmpty(list.get(18))) {
                entity.setHoldday(null);
            }

        }
        if (StringUtil.isNotEmpty(list.get(19))) {
            //产品运作方式
            entity.setProfitType(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_产品运作方式, list.get(19)));
        }
        if (StringUtil.isNotEmpty(list.get(20))) {
            //是否是客户周期型产品
            entity.setIsPeriod(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, list.get(20)));
        }
        if (StringUtil.isNotEmpty(list.get(21))) {
            //是否是分级产品
            entity.setIsGrade(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, list.get(21)));
        }
        if (StringUtil.isNotEmpty(list.get(22))) {
            //是否是指数型产品
            entity.setIsIndex(SysCommDataUtil.getCodeFromDataDictByName(DictCode.是或否, list.get(22)));
        }
//        if (StringUtil.isNotEmpty(list.get(23))) {
//            //产品业绩比较基准
//            entity.setPerformCompare(list.get(23).toString());
//        }
        if (StringUtil.isNotEmpty(list.get(23))) {
            //投资性质
            entity.setNatureInvest(SysCommDataUtil.getCodeFromDataDictByName(DictCodeExt1.信披_投资性质, list.get(23)));
        }
        if (StringUtil.isNotEmpty(list.get(24))) {
            //产品托管人名称
            entity.setAcctChnname(list.get(24));
        }
        if (StringUtil.isNotEmpty(list.get(25))) {
            //托管部门
            entity.setTrustpart(list.get(25));
        }
        return entity;
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
            prodBasicChangeService.updateProdBasicChangeList(entity.getBasicChangeList(), entity.getpProdId(), entity.getIsIndex(),null);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            ProdInfoDto prodInfoDto = prodInfoMapper.selectByPrimaryKey(entity.getProdId());
            this.update(entity);
            // 更新行情代码信息
            prodBasicChangeService.updateProdBasicChangeList(entity.getBasicChangeList(), entity.getpProdId(), entity.getIsIndex(),prodInfoDto.getIsIndex());
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

    /**
     * 根据产品主代码查询产品基本信息
     * @param prodId
     * @return
     */
    public ProdInfoDto getParentProdInfo(String prodId) {
        return prodInfoMapper.selectParentProdInfo(prodId);
    }

    public List<ProdInfoDto> getPtParentProdInfo(String pProdId) {
        return prodInfoMapper.getPtParentProdInfo(pProdId);
    }

    /**
     * 判断子产品代码是否在流程中
     *
     * @param prodId 子产品代码
     * @return true：存在流程中数据
     */
    public boolean isInWorkflow(String prodId){
        try {
            ProcessMark processMark = new ProcessMark();
            processMark.setPageId("ProdInfo002");
            processMark.setBusinessDataPk1(prodId);
            List<String> resultList = WorkflowContext.getProcessMarkService().getBusinessDataInFlow(
                    processMark);
            if (!StringUtil.isEmptyList(resultList)) {
                return true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("处理失败", e);
        }
        return false;
    }

    public int hasReapetData(ProdInfoDto dto) {
        return prodInfoMapper.hasReapetData(dto);
    }
}
