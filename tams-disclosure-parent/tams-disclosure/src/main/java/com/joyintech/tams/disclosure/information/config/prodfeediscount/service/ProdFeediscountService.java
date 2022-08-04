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

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectMapper;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dao.ProdFeediscountMapper;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
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
            if (null == objects.get(0) || objects.get(0).size() != 13) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要13列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);

                    if (!StringUtil.isNotEmpty(rowData.get(0))){
                        errorMsg.append("产品登记编码 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("子产品代码 必填！");
                    }else{
                        ProdInfoDto prodInfo = prodInfoService.selectByProdStatus(rowData.get(1));
                        if(prodInfo == null){
                            errorMsg.append("子产品代码 不存在");
                        }
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("优惠的费用 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4)) && !isNumber(rowData.get(4))){
                        errorMsg.append("优惠前费率 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5)) && !isNumber(rowData.get(5))){
                        errorMsg.append("优惠后费率 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6)) && !this.isDateStr(rowData.get(6))){
                        errorMsg.append("优惠起始时间 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(7)) && !this.isDateStr(rowData.get(7))){
                        errorMsg.append("优惠截止时间 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(8)) && !isNumber(rowData.get(8))){
                        errorMsg.append("本次优惠前费率 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(9)) && !isNumber(rowData.get(9))){
                        errorMsg.append("本次优惠后费率 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(10)) && !isNumber(rowData.get(10))){
                        errorMsg.append("本次优惠结束后费率 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(12))){
                        errorMsg.append("报告发布日期 必填！");
                    }else if(!this.isDateStr(rowData.get(12))){
                        errorMsg.append("报告发布日期 格式不正确！");
                    }

                    if (StringUtil.isNotEmpty(rowData.get(6)) && this.isDateStr(rowData.get(6))
                        && StringUtil.isNotEmpty(rowData.get(7)) && this.isDateStr(rowData.get(7))){
                        if (DateUtil.compareDate(rowData.get(7),rowData.get(6)) < 0) {
                            errorMsg.append("优惠截止时间必须大于优惠起始时间");
                        }
                    }

                    if (StringUtil.isNotEmpty(errorMsg.toString())){
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第"+(i+1)+"行数据："+errorMsg.toString());
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())){
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    /*List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //子产品代码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat){
                        repeatNum++;
                    }else {
                        jsonArrays.add(list1);
                    }*/
                    jsonArrays.add(objects.get(i));
                }
            }
        }
        if (repeatNum > 0){
            totalMsg += "导入文件存在"+String.valueOf(repeatNum)+"条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));

        String tempList = (String) resultMap.get("correctData");
        ProdFeediscountDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProdFeediscountDto> selectedItem = dto.getSelectedItem();
        List<ProdFeediscountDto>  existData = new ArrayList<>();
        for (ProdFeediscountDto temp:selectedItem) {
            if (StringUtil.isNotEmpty(this.selectData(temp))){
                existData.add(temp);
            }
        }
        if(existData.size() > 0){
            resultMap.put("errorMsg", "不可创建重复数据!");
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

    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ProdFeediscountDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProdFeediscountDto> selectedItem = dto.getSelectedItem();
        List<ProdFeediscountDto>  existData = new ArrayList<>();
        List<ProdFeediscountDto>  newData = new ArrayList<>();
        for (ProdFeediscountDto temp:selectedItem) {
            //数据已存在 【产品编码 相同】，做更新操作。
           /* if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))){
                existData.add(temp);
            }else {*/
                temp.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
                temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                newData.add(temp);
           // }
        }
        //更新操作
        /*for (ProdFeediscountDto existDataDto : existData) {
            //this.doComplete(existDataDto, OperationType.MODIFY);
            this.update(existDataDto);
        }*/
        //插入操作
        for (ProdFeediscountDto newDataDto : newData
        ) {
            this.insert(newDataDto);
            //this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }



    public ProdFeediscountDto getValNetAssetQuotationInfoListData(String tempList) {
        ProdFeediscountDto dto = new ProdFeediscountDto();
        // 行情信息列表
        List<ProdFeediscountDto> prodBonusplanDtoList = new ArrayList<>();
       // dto.setSelectedItem(prodBonusplanDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            ProdFeediscountDto feediscountDto = new ProdFeediscountDto();
			/*if (StringUtil.isNotEmpty(rowData.get(0))) {
				prodBonusplanDto.setDataStatus(rowData.get(0).toString());
			}*/
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                feediscountDto.setProdRegistNo(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                feediscountDto.setProdId(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                feediscountDto.setProdDisRemark(rowData.get(2).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                feediscountDto.setFeename(rowData.get(3).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                feediscountDto.setPreFeerate(new BigDecimal(rowData.get(4).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                feediscountDto.setAfterFeerate( new BigDecimal(rowData.get(5).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                feediscountDto.setStartDate(DateUtil.parse(rowData.get(6).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                feediscountDto.setEndDate(DateUtil.parse(rowData.get(7).toString()));
            }

            if (StringUtil.isNotEmpty(rowData.get(8))) {
                feediscountDto.setThisPreFeerate(new BigDecimal(rowData.get(8).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(9))) {
                feediscountDto.setThisAfterFeerate( new BigDecimal(rowData.get(9).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(10))) {
                feediscountDto.setThisEndFeerate( new BigDecimal(rowData.get(10).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(11))) {
                feediscountDto.setFeeDisRemark(rowData.get(11).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(12))) {
                feediscountDto.setRptIssueDate(DateUtil.parse(rowData.get(12 ).toString()));
            }
            prodBonusplanDtoList.add(feediscountDto);
        }
        dto.setSelectedItem(prodBonusplanDtoList);
        return dto;
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
        return prodFeediscountMapper.selectData(dto);
    }
}
