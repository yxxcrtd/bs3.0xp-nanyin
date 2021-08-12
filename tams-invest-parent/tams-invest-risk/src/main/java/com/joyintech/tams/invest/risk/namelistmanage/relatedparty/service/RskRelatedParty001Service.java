/*
 * 文件名：RskRelatedParty001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.namelistmanage.relatedparty.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskRelatedParty;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.redisinit.dto.IParaRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import com.joyintech.tams.invest.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;

/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedParty001Service
 * @since 1.0
 */
@Service
public class RskRelatedParty001Service extends CommonExportService<RskRelatedPartyDto> {

    public static final String RSK_RELATED_PARTY_E_002 = "RSK_RELATED_PARTY_E_002";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RiskRefreshRedisService<IParaRds> riskRefreshRedisService;

    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws FileNotFoundException 未找到文件异常
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName) throws FileNotFoundException {
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName), null);
            List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
            // 检查读出信息是否为空
            if (!CollectionUtils.isEmpty(dataList)) {
                // 将excel表格中的数据转化为dto
                List<ExcelValidDto> checkResult = checkExcelData(dataList);
                // 格式正确转化为dto
                if (CollectionUtils.isEmpty(checkResult)) {
                    List<RskRelatedPartyDto> equityList = converToBean(dataList);
                    // 校验重复
                    checkResult = checkRepeat(equityList);
                    if (!checkResult.isEmpty()) {
                        return checkResult;
                    }
                    // 校验社会统一信用代码是否存在
                    checkResult = checkInstituteCode(equityList);
                    if (!checkResult.isEmpty()) {
                        return checkResult;
                    }
                    dealAfterCheck(equityList);
                }
                return checkResult;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 导入文件校验
     * 
     * @param dataList 文件内容
     * @return 校验信息
     */
    protected List<ExcelValidDto> checkExcelData(List<List<String>> dataList) {
        List<ExcelValidDto> resList = new ArrayList<>();
        if (!StringUtil.isEmptyList(dataList)) {
            for (int i = 0; i < dataList.size(); i++ ) {
                // 检查表内数据每行是否合法
                resList.addAll(checkExcelRow(i, dataList.get(i)));
            }
        }
        return resList;
    }

    /**
     * 导入文件行校验
     * 
     * @param row 行号
     * @param data 内容
     * @return 校验信息
     */
    protected List<ExcelValidDto> checkExcelRow(int row, List<String> data) {
        List<ExcelValidDto> resList = new ArrayList<>();
        String rowNum = String.valueOf(row + 2);
        // 列数变为3
        if (null == data || data.size() != 3) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, rowNum,
                this.getMessage("RSK_RELATED_PARTY_E_001"));
            resList.add(dto);
            return resList;
        }
        // 必填check
        if (StringUtil.isEmpty(StringUtil.trimToEmpty(data.get(0)))) {
            resList.add(new ExcelValidDto(data.get(0), rowNum, //
                getMessage002("RskRelatedParty001.instituteName")));
        }
        if (StringUtil.isEmpty(StringUtil.trimToEmpty(data.get(1)))) {
            resList.add(new ExcelValidDto(data.get(1), rowNum, //
                getMessage002("RskRelatedParty001.instituteCode")));
        }
        if (StringUtil.isEmpty(StringUtil.trimToEmpty(data.get(2)))) {
            resList.add(new ExcelValidDto(data.get(2), rowNum, //
                getMessage002("RskRelatedParty001.limitAmt")));
        }
        // 金额格式check
        else {
            try {
                new BigDecimal(StringUtil.trimToEmpty(data.get(2)));
            }
            catch (Exception e) {
                resList.add(new ExcelValidDto(data.get(2), rowNum, //
                    this.getMessage("RSK_RELATED_PARTY_E_003",
                        this.getMessage("RskRelatedParty001.limitAmt"))));
            }
        }
        return resList;
    }

    protected String getMessage002(String field) {
        return this.getMessage(RSK_RELATED_PARTY_E_002, this.getMessage(field));
    }

    /**
     * 校验社会统一信用代码是否存在
     * 
     * @param dtoList 关联方管理信息
     * @return 校验结果
     */
    protected List<ExcelValidDto> checkInstituteCode(List<RskRelatedPartyDto> dtoList) {
        List<ExcelValidDto> resList = new ArrayList<>();
        // 如果为空则返回
        if (CollectionUtils.isEmpty(dtoList)) {
            return resList;
        }
        // 从机构信息中取社会统一信用代码
        List<String> codeList = selectInstituteCode(
            dtoList.stream().map(RskRelatedPartyDto::getInstituteCode).collect(
                Collectors.toList()));
        // 存在check
        dtoList.forEach(dto -> {
            // 当不存在时提示异常
            if (!codeList.contains(dto.getInstituteCode())) {
                resList.add(
                    new ExcelValidDto(dto.getInstituteCode(), String.valueOf(dto.getRow() + 2), //
                        this.getMessage("RSK_RELATED_PARTY_E_005", dto.getInstituteCode())));
            }
        });
        return resList;
    }

    protected List<String> selectInstituteCode(List<String> codeList) {
        List<String> resList = new ArrayList<>();
        // 分批次查询，每次20条
        CommonUtil.doBatchList(codeList, 20, list -> {
            // 调用关联方管理服务查询关联方
            List<?> infoList = restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/selectInstituteCode",
                list, List.class);
            if (!CollectionUtils.isEmpty(infoList)) {
                resList.addAll(
                    infoList.stream().map(Object::toString).collect(Collectors.toList()));
            }
        });
        return resList;
    }

    /**
     * 重复check
     * 
     * @param dtoList 关联方管理信息
     * @return 校验结果
     */
    protected List<ExcelValidDto> checkRepeat(List<RskRelatedPartyDto> dtoList) {
        List<ExcelValidDto> resList = new ArrayList<>();
        // 如果为空则返回
        if (CollectionUtils.isEmpty(dtoList)) {
            return resList;
        }
        // 查询数据库所有关联方信息
        Page<RskRelatedPartyDto> page = findPage(new HashMap<>());
        List<RskRelatedPartyDto> all = new ArrayList<>();
        all.addAll(dtoList);
        List<RskRelatedPartyDto> root = page.getRoot();
        if (!CollectionUtils.isEmpty(root)) {
            all.addAll(root);
        }
        // 重复check
        dtoList.forEach(dto -> {
            // 当重复时提示异常
            if (isRepeat(dto.getInstituteCode(), all)) {
                resList.add(
                    new ExcelValidDto(dto.getInstituteCode(), String.valueOf(dto.getRow() + 2), //
                        this.getMessage("RSK_RELATED_PARTY_E_004", dto.getInstituteCode())));
            }
        });
        return resList;
    }

    /**
     * 判断社会统一信用代码是否重复
     * 
     * @param code 社会统一信用代码
     * @param dtoList 全部关联方信息
     * @return true : 重复
     */
    protected boolean isRepeat(String code, List<RskRelatedPartyDto> dtoList) {
        return !CollectionUtils.isEmpty(dtoList) && dtoList.stream().filter(
            dto -> dto != null && StringUtil.equals(code, dto.getInstituteCode())).count() > 1;
    }

    /**
     * 数据类型转换
     * 
     * @param dtoList 文档内容
     * @return 转化后数据
     */
    protected List<RskRelatedPartyDto> converToBean(List<List<String>> dtoList) {
        List<RskRelatedPartyDto> resList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoList)) {
            for (int row = 0; row < dtoList.size(); row++ ) {
                List<String> data = dtoList.get(row);
                RskRelatedPartyDto dto = new RskRelatedPartyDto();
                // 关联方名称
                dto.setInstituteName(StringUtil.trimToEmpty(data.get(0)));
                // 社会统一信用代码
                dto.setInstituteCode(StringUtil.trimToEmpty(data.get(1)));
                // 单笔交易额上限 * 10000
                dto.setLimitAmt(ArithmeticUtil.multiply(
                    new BigDecimal(StringUtil.trimToEmpty(data.get(2))), new BigDecimal(10000)));
                dto.setRow(row);
                resList.add(dto);
            }
        }
        return resList;
    }

    protected void dealAfterCheck(List<RskRelatedPartyDto> dtoList) {
        CommonUtil.doBatchList(dtoList, 20, this::insertList);
        // 更新Redis参数
        try {
            ImplParaRds implParaRds = new ImplParaRds();
            implParaRds.setCommonBusinessType(DictCodeListExt3.共通业务类型_关联方管理.getValue());
            riskRefreshRedisService.refreshParamRedis(implParaRds);
        }
        catch (Exception e) {
            logger.error("===========关联方管理相关参数更新进入redis异常=============");
        }
    }

    public void insertList(List<RskRelatedPartyDto> dtoList) {
        // 登录数据
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/insertList", dtoList,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/namelistmanage/relatedparty/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
        exportParam.select(() -> doExport(exportParam.getSelectParam()));
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    public Page<RskRelatedPartyDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 导出数据查询
     * 
     * @param param 查询条件
     * @return 查询结果
     */
    protected List<RskRelatedPartyDto> doExport(Map<String, Object> param) {
        Page<RskRelatedPartyDto> page = findPage(param);
        if (page != null && !CollectionUtils.isEmpty(page.getRoot())) {
            return page.getRoot();
        }
        return new ArrayList<>();
    }

    /**
     * 分页查询
     * 
     * @param param 查询条件
     * @return 查询结果
     */
    public Page<RskRelatedPartyDto> findPage(Map<String, Object> param) {
        Page<RskRelatedPartyDto> page = restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/findPage", param,
            RskRelatedPartyDto.class);
        // 查询结果中的单笔交易额上限 / 10000
        if (page != null && !CollectionUtils.isEmpty(page.getRoot())) {
            page.getRoot().forEach(dto ->
                dto.setLimitAmt(
                ArithmeticUtil.divide(dto.getLimitAmt(), new BigDecimal(10000), 2)));
        }
        return page;
    }

    /**
     * 分页查询关联方名称
     * 
     * @param param 查询条件
     * @return 查询结果
     */
    public Page<SuggestDto> queryInstituteName(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/queryInstituteName", param,
            SuggestDto.class);
    }

    /**
     * 删除
     * 
     * @param dto
     * @return 处理结果
     */
    public void delete(RskRelatedPartyDto dto) {
        List<RskRelatedPartyDto> seletedItem = dto.getSelectedItem();
        if (CollectionUtils.isEmpty(seletedItem)) {
            return;
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/deleteList",
            seletedItem.stream().map(RskRelatedParty::getInstituteCode).collect(Collectors.toList()),
            Integer.class);
    }

    public void edit(RskRelatedPartyDto dto) {
        restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/edit",
                dto, Integer.class);
    }
}
