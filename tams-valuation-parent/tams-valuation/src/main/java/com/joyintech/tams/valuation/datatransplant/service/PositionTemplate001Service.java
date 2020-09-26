/*
 * 文件名：PositionTemplate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.datatransplant.dao.PositionTemplate001Mapper;
import com.joyintech.tams.valuation.datatransplant.dto.PositionTemplateDto;
import com.joyintech.tams.valuation.datatransplant.dto.TemplateCheckDto;
import com.joyintech.tams.common.base.dao.BokStatBokPositionMapper;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.DataTransplantCommonService;

/**
 * 头寸模板导入-service
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月8日
 * @see PositionTemplate001Service
 * @since 1.0
 */
@Service
public class PositionTemplate001Service {

    @Autowired
    private BokStatBokPositionMapper bokStatBokPositionMapper;

    @Autowired
    private PositionTemplate001Mapper positionTemplate001Mapper;

    /**
     * 数据移植公用方法
     */
    @Autowired
    private DataTransplantCommonService dataTransplantCommonService;

    /**
     * 头寸模板导入
     * 
     * @param sheetDataList
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<TemplateCheckDto> insert(List<List<String>> sheetDataList) {

        // 将excel中的数据转换为实体类
        List<PositionTemplateDto> dtoList = this.convertToDto(sheetDataList);
        // 存储校验信息
        List<TemplateCheckDto> checkList = new ArrayList<>();
        // 类型
        String typeError = "错误";
        String typeWarn = "警告";
        // 错误条数
        int number = 0;
        // 校验头寸模板
        Map<String, Object> map = checkTransplantTemple(dtoList, checkList, typeError, typeWarn,
            number);
        checkList = (List<TemplateCheckDto>)map.get("checkList");
        number = (int)map.get("number");
        if (number == 0) {
            // 统计分析账套汇总表（BOK_STAT_BOK_POSITION）数据入库
            this.insertBokStatBokPosition(dtoList);
        }

        return checkList;
    }

    /**
     * 统计分析账套汇总表（BOK_STAT_BOK_POSITION）数据入库
     * 
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    public int insertBokStatBokPosition(List<PositionTemplateDto> dtoList) {

        // 插入数据条数
        int insertNum = 0;

        for (PositionTemplateDto dto : dtoList) {

            BokStatBokPosition bokStatBokPosition = new BokStatBokPosition();
            // 统计分析账套汇总表.账套代码 = 导入数据-账套代码
            bokStatBokPosition.setBooksetId(dto.getBooksetId());
            // 统计分析账套汇总表.金融产品代码 = NULL
            bokStatBokPosition.setFinprodId(null);
            // 统计分析账套汇总表.发生日期 = 导入数据-日期
            bokStatBokPosition.setHappenDate(dto.getDate());
            // 统计分析账套汇总表.入账日期 = 导入数据-日期
            bokStatBokPosition.setBookDate(dto.getDate());
            // 统计分析账套汇总表.账套日期 = 导入数据-日期
            bokStatBokPosition.setBooksetDate(dto.getDate());
            // 统计分析账套汇总表.收益类型 = 导入数据-收益类型
            bokStatBokPosition.setProfitType(dto.getProfitType());
            // 统计分析账套汇总表.实收资本 = 导入数据-实收资本
            bokStatBokPosition.setCapital(dto.getCapital());
            // 统计分析账套汇总表.应付利润 = NULL
            bokStatBokPosition.setProfitAmt(null);
            // 统计分析账套汇总表.累计应付利润 = NULL
            bokStatBokPosition.setTotProfitAmt(null);
            // 统计分析账套汇总表.计提利率 = NULL
            bokStatBokPosition.setIntRate(null);
            // 统计分析账套汇总表.费前资产净值 = 导入数据-资产净值
            bokStatBokPosition.setNetAssetValueBef(dto.getNetAssetValue());
            // 统计分析账套汇总表.资产净值 = 导入数据-资产净值
            bokStatBokPosition.setNetAssetValue(dto.getNetAssetValue());
            // 统计分析账套汇总表.费前单位净值 = 导入数据-单位净值
            bokStatBokPosition.setNetUnitValueBef(dto.getNetUnitValue());
            // 统计分析账套汇总表.单位净值 = 导入数据-单位净值
            bokStatBokPosition.setNetUnitValue(dto.getNetUnitValue());
            // 统计分析账套汇总表.万份收益_费前 = 导入数据-万份收益
            bokStatBokPosition.setpYieldBef(dto.getpYield());
            // 统计分析账套汇总表.万份收益 = 导入数据-万份收益
            bokStatBokPosition.setpYield(dto.getpYield());
            // 统计分析账套汇总表.当日年化收益率_费前 = NULL
            bokStatBokPosition.setTdyYieldBef(null);
            // 统计分析账套汇总表.当日年化收益率 = NULL
            bokStatBokPosition.setTdyYield(null);
            // 统计分析账套汇总表.周期年化收益率_费前 = NULL
            bokStatBokPosition.setYieldTermBef(null);
            // 统计分析账套汇总表.周期年化收益率 = NULL
            bokStatBokPosition.setYieldTerm(null);
            // 统计分析账套汇总表.当日7日年化收益率_费前 = NULL
            bokStatBokPosition.setYield7Bef(null);
            // 统计分析账套汇总表.当日7日年化收益率 = NULL
            bokStatBokPosition.setYield7(null);
            // 统计分析账套汇总表.累计分红金额 = 导入数据-累加分红金额
            bokStatBokPosition.setTotBounsAmt(dto.getTotBounsAmt());
            // 统计分析账套汇总表.当日分红金额 = 0
            bokStatBokPosition.setTdyBounsAmt(BigDecimal.ZERO);
            // 统计分析账套汇总表.累计分红净值 = 导入数据-累加分红净值 ;
            bokStatBokPosition.setTotBounsNet(dto.getTotBounsNet());
            // 统计分析账套汇总表.当日分红净值 = 0
            bokStatBokPosition.setTdyBounsNet(BigDecimal.ZERO);
            // 创建人
            bokStatBokPosition.setCreateUser(GlobalConstant.数据移植账户);
            // 创建时间
            bokStatBokPosition.setCreateTime(new Date());
            // 更新人
            bokStatBokPosition.setUpdateUser(GlobalConstant.数据移植账户);
            // 更新时间
            bokStatBokPosition.setCreateTime(new Date());

            // 查询是否子产品
            Map<String, Object> params = new HashMap<>();
            params.put("booksetId", dto.getBooksetId());
            // 获取核算主体代码
            String acctSubjectId = positionTemplate001Mapper.getAcctSubjectId(params);
            if (dto.getProductId().equals(acctSubjectId)) {
                // 统计分析账套汇总表.是否子产品 = '否:N'
                bokStatBokPosition.setIsSubPrd(DictCodeList.是否_否.getValue());
            }
            else {
                // 统计分析账套汇总表.是否子产品 = '是:Y'
                bokStatBokPosition.setIsSubPrd(DictCodeList.是否_是.getValue());
            }

            // 统计分析账套汇总表.分层代码 = 导入数据-产品/组合代码
            bokStatBokPosition.setLayeringId(dto.getProductId());

            insertNum = insertNum + bokStatBokPositionMapper.insert(bokStatBokPosition);
        }

        // 数据入库
        return insertNum;
    }

    /**
     * 将excel中的数据转换为实体类
     * 
     * @param sheetDataList
     * @return
     * @see
     * @since 1.0
     */
    public List<PositionTemplateDto> convertToDto(List<List<String>> sheetDataList) {
        // 定义返回实体类
        List<PositionTemplateDto> dtoList = new ArrayList<PositionTemplateDto>();

        // 循环进行数据处理
        for (List<String> row : sheetDataList) {
            // 定义实体类
            PositionTemplateDto dto = new PositionTemplateDto();
            // 塞值
            dto.setBooksetId(row.get(0) == "" ? null : row.get(0).trim());// 账套代码
            dto.setProductId(row.get(1) == "" ? null : row.get(1).trim());// 产品/组合代码
            dto.setDate(row.get(2) == "" ? null : DateUtil.parse(row.get(2).trim()));// 日期
            dto.setProfitType(
                row.get(3) == "" ? null : row.get(3).trim().split(GlobalConstant.分号)[0]);// 收益类型
            dto.setCapital(row.get(4) == "" ? null : new BigDecimal(row.get(4).trim()));// 实收资本
            dto.setNetAssetValue(row.get(5) == "" ? null : new BigDecimal(row.get(5).trim()));// 资产净值
            dto.setNetUnitValue(row.get(6) == "" ? null : new BigDecimal(row.get(6).trim()));// 单位净值
            dto.setpYield(row.get(7) == "" ? null : new BigDecimal(row.get(7).trim()));// 万份收益
            dto.setTotBounsAmt(row.get(8) == "" ? null : new BigDecimal(row.get(8).trim()));// 累加分红金额
            dto.setTotBounsNet(row.get(9) == "" ? null : new BigDecimal(row.get(9).trim()));// 累加分红净值

            // 塞入list
            dtoList.add(dto);

        }

        return dtoList;
    }

    // /**
    // * 字符串时间转换为Date
    // *
    // * @param DateString 格式字符串
    // * @return
    // * @see
    // * @since 1.0
    // */
    // public Date DateStringToDate(String DateString) {
    // // 定义返回时间
    // Date resultDate = null;
    //
    // // 传递字符串不为空，进行时间转换
    // if (DateString != null) {
    // try {
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    // resultDate = formatter.parse(DateString);
    // }
    // catch (ParseException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // return resultDate;
    // }

    /**
     * 校验头寸模板
     */
    private Map<String, Object> checkTransplantTemple(List<PositionTemplateDto> dtoList,
                                                      List<TemplateCheckDto> checkList,
                                                      String typeError, String typeWarn,
                                                      int number) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < dtoList.size(); i++ ) {
            // 判断是否有错误
            boolean error = false;
            // 校验核算分层中是否存在该分层
            if (StringUtil.isNotEmpty(dtoList.get(i).getProductId())) {
                boolean flag = dataTransplantCommonService.checkLayering(
                    dtoList.get(i).getProductId());
                if (!flag) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage("核算分层中不存在该分层");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (!StringUtil.isNotEmpty(dtoList.get(i).getBooksetId())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("账套代码不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (StringUtil.isNotEmpty(dtoList.get(i).getBooksetId())
                && StringUtil.isNotEmpty(dtoList.get(i).getDate())
                && StringUtil.isNotEmpty(dtoList.get(i).getProductId())) {
                // 判断数据是否重复插入
                boolean flag = positionTemplate001Mapper.checkInsertRepeat(
                    dtoList.get(i).getBooksetId(), dtoList.get(i).getDate(),
                    dtoList.get(i).getProductId());
                if (flag) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeWarn);
                    templateCheckDto.setErrorMessage("数据重复");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }
            if (!StringUtil.isNotEmpty(dtoList.get(i).getProductId())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("产品/组合代码不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (dtoList.get(i).getDate() == null) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("日期不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            if (!StringUtil.isNotEmpty(dtoList.get(i).getProfitType())) {
                TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                templateCheckDto.setErrorLineNum(i + 2);
                templateCheckDto.setErrorType(typeError);
                templateCheckDto.setErrorMessage("收益类型不能为空");
                checkList.add(templateCheckDto);
                error = true;
            }
            // 校验枚举字段是否有效
            if (StringUtil.isNotEmpty(dtoList.get(i).getProfitType())) {
                DataDictItem from = SysDataUtil.getDataDictItem(DictCode.收益类型.getValue(),dtoList.get(i).getProfitType());
                if (!StringUtil.isNotEmpty(from)) {
                    TemplateCheckDto templateCheckDto = new TemplateCheckDto();
                    templateCheckDto.setErrorLineNum(i + 2);
                    templateCheckDto.setErrorType(typeError);
                    templateCheckDto.setErrorMessage(
                        "收益类型枚举值：" + dtoList.get(i).getProfitType() + "无效");
                    checkList.add(templateCheckDto);
                    error = true;
                }
            }

            if (error) {
                number++ ;
            }

        }
        map.put("checkList", checkList);
        map.put("number", number);

        return map;
    }

}
