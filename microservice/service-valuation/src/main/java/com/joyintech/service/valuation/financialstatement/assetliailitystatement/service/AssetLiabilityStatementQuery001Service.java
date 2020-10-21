/*
 * 文件名：AssetLiabilityStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.common.AssetLiabilityStatementUtil;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dao.AssetLiabilityStatementQuery001Mapper;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQueryDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.jasypt.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产负债表
 * 
 * @author zhangwei
 * @version 1.0 2018年9月25日
 * @see AssetLiabilityStatementQuery001Service
 * @since 1.0
 */
@Service(value="serviceAssetLiabilityStatementQuery001Service")
public class AssetLiabilityStatementQuery001Service extends CommonExportService<AssetLiabilityStatementQuery001Dto> {

	@Autowired
	AssetLiabilityStatementQuery001Mapper mapper;

	/**
	 * 通用联动查询
	 * 
	 * @param param : dict_code_parent,dict_code_child,item_code_parent
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@DataSource(DataSourceType.OTHER)
	public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
		return mapper.getLinkSelectOption(param);
	}

	/**
	 * 查询科目余额表
	 * 
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public List<AssetLiabilityStatementQueryDto> findBokBalance(AssetLiabilityStatementQuery001SearchDto dto){
		List<AssetLiabilityStatementQueryDto> list = mapper.selectBokBalance(dto);
		return list;
	}
	
	/**
	 * 查询科目余额表
	 * 
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public List<AssetLiabilityStatementQueryDto> findBokBalanceAll(AssetLiabilityStatementQuery001SearchDto dto)
			{
		List<AssetLiabilityStatementQueryDto> list = mapper.selectBokBalanceAll(dto);
		return list;
	}

	/**
	 * 分析余额日期
	 * 
	 * @param dto         查询条件
	 * @param balanceType 期末、年初
	 * @return
	 * @throws ParseException
	 */
	public Date analyseBalanceDate(AssetLiabilityStatementQuery001SearchDto dto, String balanceType)
			throws ParseException {
		int year = 0;
		int month = -1;
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
		calendar.set(Calendar.MINUTE, 0);// 分
		calendar.set(Calendar.SECOND, 0);// 秒
		calendar.set(Calendar.MILLISECOND, 0);// 毫秒

		String[] strArr = dto.getReportDate().split(",")[0].split("-");
		if (DictCodeListExt1.报表期限类型_年报.getValue().equals(dto.getReportTermType())) {// 年底
			year = Integer.valueOf(strArr[0]);
			month += 12;
			day = 31;
		}
		if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			if (DictCodeListExt1.半年_上半年.getValue().equals(dto.getReportTerm())) {
				month += 6;
				day = 30;
			} else {
				month += 12;
				day = 31;
			}
		}
		if (DictCodeListExt1.报表期限类型_季报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			if (DictCodeListExt1.季度_第一季度.getValue().equals(dto.getReportTerm())) {
				month += 3;
				day = 31;
			} else if (DictCodeListExt1.季度_第二季度.getValue().equals(dto.getReportTerm())) {
				month += 6;
				day = 30;
			} else if (DictCodeListExt1.季度_第三季度.getValue().equals(dto.getReportTerm())) {
				month += 9;
				day = 30;
			} else {
				month += 12;
				day = 31;
			}
		}
		if (DictCodeListExt1.报表期限类型_月报.getValue().equals(dto.getReportTermType())) {// 月底：当前月的下个月的第0天
			year = Integer.valueOf(strArr[0]);
			month += Integer.valueOf(strArr[1]) + 1;
			day = 0;
		}
		if (DictCodeListExt1.报表期限类型_日报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			month += Integer.valueOf(strArr[1]);
			day = Integer.valueOf(strArr[2]);
		}

		if (AssetLiabilityStatementUtil.ENDING_BALANCE.equals(balanceType)) {// 期末
			calendar.set(year, month, day);
			return calendar.getTime();
		} else {// 年初
			calendar.set(year - 1, 11, 31);
			return calendar.getTime();
		}
	}
	
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return AssetLiabilityStatementQuery001SearchDto
     */
    public AssetLiabilityStatementQuery001SearchDto getDateInfo(Map<String, Object> pageParam) {
        String booksetId = String.valueOf(pageParam.get("booksetId"));
        String reportTermType = String.valueOf(pageParam.get("reportTermType"));
        AssetLiabilityStatementQuery001SearchDto datedto = new AssetLiabilityStatementQuery001SearchDto();
        if(booksetId != "null" && booksetId != ""){
            datedto = mapper.getDateInfo1(pageParam);
        }else{
            List<AssetLiabilityStatementQuery001SearchDto> dateList = mapper.getDateInfo2(pageParam);
            if(dateList.size()>0){
				for(AssetLiabilityStatementQuery001SearchDto item:dateList){
					if(datedto.getReportDate() == null || datedto.getReportDate() == ""){
						datedto.setReportDate(item.getReportDate());
					}else{
						if(datedto.getReportDate().compareTo(item.getReportDate()) > 0){
							datedto.setReportDate(item.getReportDate());
						}
					}
				}
			// add by caiqun 20201021 报表维度选择全账套，配置产品组时没有选择任何产品，datedto.setReportDate取值为空下面格式日期会报错
			// 改成为空时取平台日
			}else{
				datedto.setReportDate(DateUtil.getSysDate());
			}
        }
        if(DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)){
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, 0, -1)));
//            datedto.setReportDate(DateUtil.addDate(datedto.getReportDate(), 0, 0, -1));
        }else if(DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)){
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, -1, 0), "yyyy-MM"));
        }else if(DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)){
            int season = DateUtil.getSeason(DateUtil.parse(datedto.getReportDate()));
            if(season == 1){
                datedto.setReportTerm(DictCodeListExt1.季度_第四季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            }else if(season == 2){
                datedto.setReportTerm(DictCodeListExt1.季度_第一季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }else if(season == 3){
                datedto.setReportTerm(DictCodeListExt1.季度_第二季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }else if(season == 4){
                datedto.setReportTerm(DictCodeListExt1.季度_第三季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)){
            int month = DateUtil.getMonth(datedto.getReportDate());
            if(month<=6){
                datedto.setReportTerm(DictCodeListExt1.半年_下半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            }else{
                datedto.setReportTerm(DictCodeListExt1.半年_上半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)){
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
        }
        return datedto;
    }

}
