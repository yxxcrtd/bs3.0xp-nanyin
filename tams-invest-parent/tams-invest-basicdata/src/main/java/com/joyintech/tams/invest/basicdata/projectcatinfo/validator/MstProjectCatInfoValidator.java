/*
 * 文件名：MstProjectCatInfoValidator.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.657
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.mstnetassetquotationinfo.service.MstNetAssetQuotationInfo001Service;
import com.joyintech.tams.invest.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.657
 * @see MstProjectCatInfoValidator
 * @since 1.0
 */
@Component
public class MstProjectCatInfoValidator implements BeanValidator {

    @Autowired
    private MstNetAssetQuotationInfo001Service mstNetAssetQuotationInfo001Service;
    @Override
    public List<ValidMessage> valid(Object value) {
        MstProjectCatInfoDto dto = (MstProjectCatInfoDto) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
                    //删除时估值日期小于平台日期，报错
                    for (MstProjectCatInfoDto selectDto : dto.getSelectedItem()) {
                        if (StringUtil.isNotEmpty(selectDto.getImportDate()) && DateUtil.compareDate(selectDto.getImportDate(), sysDate) != 0) {
                            errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                        }
                    }
                }
            } else {
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if (!StringUtil.isNotEmpty(dto.getFinProdName()) ||
                            !StringUtil.isNotEmpty(dto.getValDate()) || !StringUtil.isNotEmpty(dto.getFinProdMarketId())) {
                        return errorList;
                    }
                    Map<String, Object> map = RequestUtil.getPageParam();
                    //Map<String,Object> map = new HashMap<>();
                    map.put("finprodId", dto.getFinprodId());
                    map.put("valDate", dto.getValDateStr());
                    map.put("finprodType2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
                    //新建时，资产代码+估值日期检索货币型基金行情信息，数据已存在的情况下，报错
                    if (mstNetAssetQuotationInfo001Service.selectByIdAndDate(map) != null) {
                        errorList.add(new ValidMessage("finprodId", getMessage("BUSS_COM_E_0006",
                                getMessage("MstNetAssetQuotationInfo002.assetCode") + ":" + dto.getFinProdMarketId() ,
                                        getMessage("MstNetAssetQuotationInfo002.valuationDate") + ":" + dto.getValDateStr())));
                    }
                    //新建时估值日期小于平台日期，报错
                    /*
                     * if (dto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
                     * ValidMessage("valDate",getMessage("BUSS_COM_E_0004",getMessage(
                     * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
                     */
                } else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                    //编辑时估值日期小于平台日期，报错
                    /*
                     * if (dto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
                     * ValidMessage(getMessage("BUSS_COM_E_0018",getMessage(
                     * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
                     */

                    //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                    //修改/删除时，仅能修改/删除导入日为平台日的数据
                    if (StringUtil.isNotEmpty(dto.getImportDate()) && DateUtil.compareDate(dto.getImportDate(), sysDate) != 0) {
                        errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                    }

                }
            }
        } else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if (!StringUtil.isNotEmpty(dto.getFinProdName()) ||
                        !StringUtil.isNotEmpty(dto.getValDate()) || !StringUtil.isNotEmpty(dto.getFinProdMarketId())) {
                    return errorList;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("finprodId", dto.getFinprodId());
                map.put("valDate", dto.getValDateStr());
                //新建时，资产代码+估值日期检索货币型基金行情信息，数据已存在的情况下，报错
                if (mstNetAssetQuotationInfo001Service.selectByIdAndDate(map) != null) {
                    errorList.add(new ValidMessage("finprodId", getMessage("BUSS_COM_E_0006",
                            getMessage("MstNetAssetQuotationInfo002.assetCode") + ":" + dto.getFinProdMarketId() +
                                    getMessage("MstNetAssetQuotationInfo002.valuationDate") + ":" + dto.getValDateStr())));
                }


                /*add wangwei1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                 * //新建时估值日期小于平台日期，报错
                 *  if (dto.getValDate().compareTo(sysDate) < 0) {
                 * errorList.add(new
                 * ValidMessage("valDate",getMessage("BUSS_COM_E_0004",getMessage(
                 * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
                 */
            }
        }
        return errorList;
    }
}
