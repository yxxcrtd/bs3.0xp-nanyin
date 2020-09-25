/*
 * 文件名：BussRepFinProductInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.prod.impl;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.report.prod.BussRepFinProductInfoService;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 产品中债报送业务组件service
 * 
 * @author zhangliang
 * @version 1.0 2018年10月18日
 * @see BussRepFinProductInfoImplService
 * @since 1.0
 */
@Service
public class BussRepFinProductInfoImplService extends BaseService<BussRepFinProductInfoDto> implements BussRepFinProductInfoService {

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepFinProductInfoDto select(String finprodId) {
        BussRepFinProductInfoDto bussRepFinProductInfoDto = restTemplate.getForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductInfo/select?finprodId="
                                                                                      + finprodId,
            BussRepFinProductInfoDto.class);
//        if (bussRepFinProductInfoDto != null) {
//            if (StringUtil.isNotEmpty(bussRepFinProductInfoDto.getRepLowYield())) {
//                bussRepFinProductInfoDto.setRepLowYield(ArithmeticUtil.multiply(
//                    bussRepFinProductInfoDto.getRepLowYield(), new BigDecimal(100)));
//            }
//            if (StringUtil.isNotEmpty(bussRepFinProductInfoDto.getRepExpAvgYield())) {
//                bussRepFinProductInfoDto.setRepExpAvgYield(ArithmeticUtil.multiply(
//                    bussRepFinProductInfoDto.getRepExpAvgYield(), new BigDecimal(100)));
//            }
//            if (StringUtil.isNotEmpty(bussRepFinProductInfoDto.getRepHighYield())) {
//                bussRepFinProductInfoDto.setRepHighYield(ArithmeticUtil.multiply(
//                    bussRepFinProductInfoDto.getRepHighYield(), new BigDecimal(100)));
//            }
//        }

        return bussRepFinProductInfoDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String finprodId) {
        restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME
                                         + "/RepFinProductInfo/delete?finprodId=" + finprodId,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(BussRepFinProductInfoDto dto) {
        restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductInfo/insert", dto,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(BussRepFinProductInfoDto dto) {
        restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductInfo/update", dto,
            Integer.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String calcProductTermInfo(BussRepFinProductInfoDto inputDto) {
        // 发行方式
        String operationType = inputDto.getOperationType();
        // 期限品种
        String termType = null;
        
        if (StringUtil.equals(DictCodeList.开放类型_封闭式.getValue(), operationType)) {
            if (StringUtil.isNotEmpty(inputDto.getProdVdate()) && StringUtil.isNotEmpty(inputDto.getProdMdate())){
                // 4.1.2 [发行方式] = "封闭式" 的场合，期限品种由[产品期限(天)]项目值算出
                int termNum = (int)DateUtil.getBetweenDayNumber(inputDto.getProdVdate(), inputDto.getProdMdate());
                // 根据 产品期限（天） 设定 [期限品种]项目值
                termType = this.getTermTypeByTermNum(termNum);
            }
        }
        // 4.1.3 [发行方式] = "开放式" 的场合，期限品种由[开放申购周期]、[开放申购周期单位]、[开放模式] 项目值算出
        else if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(), operationType)) {
            // 开放模式
            String openMode = inputDto.getOpenMode();
            if (StringUtil.equals(DictCodeList.开放模式_高频开放.getValue(), openMode)) {
                // [开放模式] = "高频开放" 的场合, 期限品种 = “T+0”
                termType = DictCodeListExt2.产品期限_T_0产品.getValue();
            }
            else if (StringUtil.equals(DictCodeList.开放模式_定期开放.getValue(), openMode)
                || StringUtil.equals(DictCodeList.开放模式_周期开放.getValue(), openMode)) {
                // [开放模式] = "定期开放" 的场合
                String openCycleUnit = inputDto.getOpenCycleUnit();

                // [开放周期单位] = "日" or "周" 的场合
                if (StringUtil.equals(DictCodeListExt1.周期单位_日.getValue(), openCycleUnit)
                    || StringUtil.equals(DictCodeListExt1.周期单位_周.getValue(), openCycleUnit)) {
                    // 临时变量.期限天数 = [开放周期]值
                    Integer tempTermDayNum = StringUtil.toInt(inputDto.getOpenCycle());
                    // "周"的场合，按照1周=7天换算
                    if (StringUtil.equals(DictCodeListExt1.周期单位_周.getValue(), openCycleUnit)) {
                        tempTermDayNum = tempTermDayNum * 7;
                    }
                    // 根据 期限天数 设定 [期限品种]项目值
                    termType = this.getTermTypeByTermNum(tempTermDayNum);
                }
                else if (StringUtil.equals(DictCodeListExt1.周期单位_月.getValue(), openCycleUnit)
                         || StringUtil.equals(DictCodeListExt1.周期单位_年.getValue(), openCycleUnit)) {
                    // [开放周期单位] = "月" or "季" or "年" 的场合
                    // 临时变量.期限月数 = [开放周期]值（按照1"季" = 3"月"; 1"年" = 12"月"换算）
                    Integer tempTermMonthNum = StringUtil.toInt(inputDto.getOpenCycle());
                    // "周"的场合，按照1周=7天换算
                    if (StringUtil.equals(DictCodeListExt1.周期单位_年.getValue(),
                        openCycleUnit)) {
                        tempTermMonthNum = tempTermMonthNum * 12;
                    }

                    // 根据 期限月数 设定 [期限品种]项目值
                    termType = this.getTermTypeByTermMonthNum(tempTermMonthNum);
                }
            }
            else {
                // 以上以外场合, 期限品种 = 空
                termType = null;
            }
        }
        // 4.1.4 [发行方式] = 空 的场合
        else if (StringUtil.isEmpty(operationType)) {
            // 期限品种 = 空
            termType = null;
        }


        return StringUtil.defaultString(termType, StringUtil.EMPTY);
    }
    
    /**
     * 根据 产品期限（天） 设定 [期限品种]项目值
     *
     * @param termNum 产品期限（天）
     * @return [期限品种]项目值
     * @see
     * @since 1.0
     */
    private String getTermTypeByTermNum(Integer termNum) {
        if (termNum == null) {
            return null;
        }

        String termType = StringUtil.EMPTY;

        if (termNum >= 0 && termNum <= 7) {
            // 产品期限(天) >= 0 && 产品期限(天) <= 7 的场合, 期限品种 = “7天以内”
            termType = DictCodeListExt2.产品期限_7天_含_以内.getValue();
        }
        else if (termNum > 7
                 && termNum <= 31) {
            // 产品期限(天) > 7 && 产品期限(天) <= 31 的场合, 期限品种 = “1个月以内”
            termType = DictCodeListExt2.产品期限_7天_1个月_含.getValue();
        }
        else if (termNum > 31
                 && termNum <= 92) {
            // 产品期限(天) > 31 && 产品期限(天) <= 92 的场合, 期限品种 = “3个月以内”
            termType = DictCodeListExt2.产品期限_1_3个月_含.getValue();
        }
        else if (termNum > 92
                 && termNum <= 184) {
            // 产品期限(天) > 92 && 产品期限(天) <= 184 的场合, 期限品种 = “6个月以内”
            termType = DictCodeListExt2.产品期限_3_6个月_含.getValue();
        }
        else if (termNum > 184
                 && termNum <= 366) {
            // 产品期限(天) > 184 && 产品期限(天) <= 366 的场合, 期限品种 = “1年以内”
            termType = DictCodeListExt2.产品期限_6_12个月_含.getValue();
        }
        else if (termNum > 366 && termNum <= 1096) {
            // 产品期限(天) > 366时, 期限品种 = “1年以上”
            termType = DictCodeListExt2.产品期限_1_3年_含.getValue();
        }
        else if (termNum > 1096) {
            // 产品期限(天) > 1096时, 期限品种 = “3年以上”
            termType = DictCodeListExt2.产品期限_3年以上.getValue();
        }
        return termType;
    }
    
    /**
     * 根据 期限月数 设定 [期限品种]项目值
     *
     * @param tempTermMonthNum 期限月数
     * @return [期限品种]项目值
     * @see
     * @since 1.0
     */
    private String getTermTypeByTermMonthNum(Integer tempTermMonthNum) {
        if (tempTermMonthNum == null) {
            return null;
        }
        String termType = null;

        if (tempTermMonthNum > 0 && tempTermMonthNum <= 1) {
            // 期限月数 > 0 && 期限月数 <= 1 的场合, 期限品种 = “1个月以内”
            termType = DictCodeListExt2.产品期限_7天_1个月_含.getValue();
        }
        else if (tempTermMonthNum > 1 && tempTermMonthNum <= 3) {
            // 期限月数 > 1 && 期限月数 <= 3 的场合, 期限品种 = “3个月以内”
            termType = DictCodeListExt2.产品期限_1_3个月_含.getValue();
        }
        else if (tempTermMonthNum > 3 && tempTermMonthNum <= 6) {
            // 期限月数 > 3 && 期限月数 <= 6 的场合, 期限品种 = “6个月以内”
            termType = DictCodeListExt2.产品期限_3_6个月_含.getValue();
        }
        else if (tempTermMonthNum > 6 && tempTermMonthNum <= 12) {
            // 期限月数 > 6 && 期限月数 <= 12 的场合, 期限品种 = “1年以内”
            termType = DictCodeListExt2.产品期限_6_12个月_含.getValue();
        }
        else if (tempTermMonthNum > 12 && tempTermMonthNum <= 36) {
            // 期限月数 > 12的场合, 期限品种 = “1年以上”
            termType = DictCodeListExt2.产品期限_1_3年_含.getValue();
        } else if (tempTermMonthNum > 36) {
            // 期限月数 > 36的场合, 期限品种 = “3年以上”
            termType = DictCodeListExt2.产品期限_3年以上.getValue();
        }
        return termType;
    }

	@Override
	public BussRepFinProductInfoDto selectRepFinprodId(String repFinprodInsId) {
		return restTemplate.getForObject(
	            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductInfo/selectRepFinprodId?repFinprodInsId="
                        + repFinprodInsId,
                        BussRepFinProductInfoDto.class);
	}

}
