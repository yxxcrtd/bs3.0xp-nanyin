/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodfeediscount.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dao.ProdFeediscountMapper;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @see ProdFeediscountService
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdFeediscountService extends CommonExportService<ProdFeediscountDto> implements BaseProcessService<ProdFeediscountDto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdFeediscountService.class);


    @Autowired
    private RepProdInfoMapper repProdInfoMapper;

    @Autowired
    private ProdFeediscountMapper prodFeediscountMapper;

    @Autowired
    ProdInfoService prodInfoService ;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;


    public List<ProdFeediscountDto> findAll(Map<String,Object> params) {
        List<ProdFeediscountDto> result = prodFeediscountMapper.searchReports(params);
        return result;
    }

    /**
     * 分页查询费率名称下拉
     *
     * @param
     * @return
     * @throws Exception
     */
/*    public Page<SelectOptionDto> getProdFeenameSelect(Map<String, Object> paraMap){
        Page<SelectOptionDto> page = this.queryForPage(ProdFeediscountMapper.class, "getProdFeenameSelect", paraMap);
        return page;
    }*/
    public Page<SelectOptionDto> getProdFeenameSelect(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(
                Integer.parseInt((String) pageParam.get("page")),
                Integer.parseInt((String) pageParam.get("length")),
                ()->prodFeediscountMapper.getProdFeenameSelect(map));

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
    public Page<ProdFeediscountDto> findByPage(Map<String, Object> params)
    {
        String dataStatus = (String)params.get("dataStatus");
        String[] dataStatusArr = null;
        if(StringUtil.isNotEmpty(dataStatus)){
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);
        String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        String prodRegistNo = (String)params.get("prodRegistNo");
        String[] prodRegistNoArr = null;
        if(StringUtil.isNotEmpty(prodRegistNo)){
            prodRegistNoArr = prodRegistNo.split(",");
        }
        params.put("prodRegistNoArr", prodRegistNoArr);

        String feename = (String)params.get("feename");
        String[] feenameArr = null;
        if(StringUtil.isNotEmpty(feename)){
            feenameArr = feename.split(",");
        }
        params.put("feenameArr", feenameArr);

            Page<ProdFeediscountDto> pages = super.queryForPageForSelectReturn(Integer.parseInt( params.get("page").toString()),
                Integer.parseInt(params.get("length").toString()),
                () -> prodFeediscountMapper.searchReports(params));
        return pages;
    }


    /**
     *  查询产品明细信息
     * @param prodId
     * @return
     */
    public ProdFeediscountDto selectByProdId(String prodId) {
        return  prodFeediscountMapper.selectByProdId(prodId,DictCodeListExt3.信披_数据状态_有效E.getValue());
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/prodfeediscount/export_prodfeediscount001.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProdFeediscountDto> selectPageData(Map<String, Object> selectParam) {
        /*queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> hugeInfoProMapper.select(selectParam));*/
        return this.findByPage(selectParam);
    }


    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")),
                Integer.parseInt((String) pageParam.get("length")),
                ()->prodFeediscountMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")),
                Integer.parseInt((String) pageParam.get("length")),
                ()->prodFeediscountMapper.getProdIdlist(map));
    }

    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(ProdFeediscountDto dto) {
        AjaxResult result = new AjaxResult();
        Set<String> message = new HashSet<String>();
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
     * 根据主键id查询明细信息
     * @param keyId
     * @return
     */
    public ProdFeediscountDto selectByKeyId(String keyId) {
        ProdFeediscountDto dto = prodFeediscountMapper.selectByKeyId(keyId);
        return dto;
    }


    /**
     * 插入数据
     *
     * @param dto
     */
    public void insert(ProdFeediscountDto dto) {
        dto.setProdDiscountId(IDGenerator.generate(36));
        prodFeediscountMapper.insert(dto);
    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(ProdFeediscountDto dto) {
        prodFeediscountMapper.updateByPrimaryKeySelective(dto);
    }

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(ProdFeediscountDto entity) {
        String bonusPlanId = entity.getProdDiscountId();
        if (StringUtils.isNotEmpty(bonusPlanId)) {
            ProdFeediscountDto dto = new ProdFeediscountDto();
            dto.setProdDiscountId(bonusPlanId);
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除D.getValue());
            this.update(dto);
        }
    }

    public RepProdInfo getPrdInfo(String prodId){
        RepProdInfo prodInfo =  repProdInfoMapper.selectByPrimaryKey(prodId);
        return  prodInfo;
    }

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
        if (null == tempList.get(0) || tempList.get(0).size() != 13) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 13), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<ProdFeediscountDto> entities = this.convertToBean(dataList);
            List<String> excelNumList = new ArrayList<>();
            entities.forEach(x -> {
                if ( CollectionUtils.isNotEmpty(this.getProdFeeDiscount(x))) {
                    excelNumList.add(x.getExcelNum());
                }
            });
            if (!excelNumList.isEmpty()) {
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
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
     * excel数据转为java对象
     * @return
     */
    private List<ProdFeediscountDto> convertToBean(List<List<String>> dataList) {
        List<ProdFeediscountDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            ProdFeediscountDto entity = new ProdFeediscountDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdRegistNo(list.get(0));
            } else {
                entity.setProdRegistNo(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setProdId(list.get(1));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setProdDisRemark(list.get(2));
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                entity.setFeename(list.get(3));
            } else {
                entity.setFeename(" ");
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                entity.setPreFeerate(list.get(4));
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                entity.setAfterFeerate(list.get(5));
            }
            if (StringUtil.isNotEmpty(list.get(6)) && isDateStr(list.get(6))) {
                entity.setStartDate(list.get(6));
            }
            if (StringUtil.isNotEmpty(list.get(7)) && isDateStr(list.get(7))) {
                entity.setEndDate(list.get(7));
            }

            if (StringUtil.isNotEmpty(list.get(8)) && isNumber(list.get(8))) {
                entity.setThisPreFeerate(list.get(8));
            }
            if (StringUtil.isNotEmpty(list.get(9)) && isNumber(list.get(9))) {
                entity.setThisAfterFeerate(list.get(9));
            }
            if (StringUtil.isNotEmpty(list.get(10)) && isNumber(list.get(10))) {
                entity.setThisEndFeerate(list.get(10));
            }
            if (StringUtil.isNotEmpty(list.get(11))) {
                entity.setFeeDisRemark(list.get(11));
            }
            if (StringUtil.isNotEmpty(list.get(12)) && isDateStr(list.get(12))) {
                entity.setRptIssueDateStr(list.get(12));
            } else {
                entity.setRptIssueDateStr(" ");
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

    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<ProdFeediscountDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    List<ProdFeediscountDto> prodFeeDiscount = getProdFeeDiscount(item);
                    if (CollectionUtils.isNotEmpty(prodFeeDiscount)) {
                        List<String> prodDiscountIds = prodFeeDiscount.stream().map(ProdFeediscountDto::getProdDiscountId).collect(Collectors.toList());
                        prodDiscountIds.forEach(id->{
                            prodFeediscountMapper.deleteByPrimaryKey(id);
                        });
                    }
                    item.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
                    item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                    this.insert(item);
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
                                                 List<ProdFeediscountDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        List<String> map = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 13) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 13)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (StringUtil.isEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            }
            if (StringUtil.isEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else {
                if (!this.getProdId(rowData.get(1))) {
                    message.add("子产品代码不存在。");
                }
            }
            if (StringUtil.isEmpty(rowData.get(12))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "报告发布日期"));
            }else if(!isDateStr(rowData.get(12))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "报告发布日期"));
            }
            if (StringUtil.isNotBlank(rowData.get(2)) && rowData.get(2).length()>500){
                message.add("此产品优惠调整（补充性）描述文字字符不能超过500");
            }
            if (StringUtil.isNotBlank(rowData.get(11)) && rowData.get(11).length()>500){
                message.add("对此费率优惠调整（补充性）描述文字字符不能超过500");
            }

            String key = rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3)
                    + GlobalConstant.UNDER_LINE + rowData.get(4)
                    + GlobalConstant.UNDER_LINE + rowData.get(5)
                    + GlobalConstant.UNDER_LINE + rowData.get(6)
                    + GlobalConstant.UNDER_LINE + rowData.get(7)
                    + GlobalConstant.UNDER_LINE + rowData.get(8)
                    + GlobalConstant.UNDER_LINE + rowData.get(9)
                    + GlobalConstant.UNDER_LINE + rowData.get(10)
                    + GlobalConstant.UNDER_LINE + rowData.get(11)
                    + GlobalConstant.UNDER_LINE + rowData.get(12);
            if (map.contains(key)) {
                message.add("不可创建重复数据");
            } else {
                map.add(key);
            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private ProdFeediscountDto convertToEntity(List<String> rowData) {
        ProdFeediscountDto feediscountDto = new ProdFeediscountDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            feediscountDto.setProdRegistNo(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            feediscountDto.setProdId(rowData.get(1));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            feediscountDto.setProdDisRemark(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            feediscountDto.setFeename(rowData.get(3));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            feediscountDto.setPreFeerate(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            feediscountDto.setAfterFeerate( rowData.get(5));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            feediscountDto.setStartDate(rowData.get(6));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            feediscountDto.setEndDate(rowData.get(7));
        }
        if (StringUtil.isNotEmpty(rowData.get(8))) {
            feediscountDto.setThisPreFeerate(rowData.get(8));
        }
        if (StringUtil.isNotEmpty(rowData.get(9))) {
            feediscountDto.setThisAfterFeerate(rowData.get(9));
        }
        if (StringUtil.isNotEmpty(rowData.get(10))) {
            feediscountDto.setThisEndFeerate(rowData.get(10));
        }
        if (StringUtil.isNotEmpty(rowData.get(11))) {
            feediscountDto.setFeeDisRemark(rowData.get(11));
        }
        if (StringUtil.isNotEmpty(rowData.get(12))) {
            feediscountDto.setRptIssueDate(DateUtil.parse(rowData.get(12)));
        }
        return feediscountDto;
    }

    @Override
    public void doComplete(ProdFeediscountDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            this.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);
        }
    }

    public ProdFeediscountDto selectData(ProdFeediscountDto dto) {
        List<ProdFeediscountDto> list = prodFeediscountMapper.selectData(dto);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public Boolean getProdId(String prodId) {
        String r = prodFeediscountMapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public List<ProdFeediscountDto> getProdFeeDiscount(ProdFeediscountDto record){
        return prodFeediscountMapper.getProdFeeDiscount(record);
    }
}
